package net.servicestack.client.sse;

import net.servicestack.client.Log;
import net.servicestack.client.Utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mythz on 3/3/2017.
 */

public class EventStream implements Runnable {
    static int BufferSize = 1024 * 64;

    ServerEventsClient client;
    ServerEventMessage currentMsg;

    public EventStream(ServerEventsClient client) {
        this.client = client;
    }

    protected InputStream getInputStream(URL streamUri) throws IOException {
        HttpURLConnection req = (HttpURLConnection) streamUri.openConnection();
        return new BufferedInputStream(req.getInputStream());
    }

    public void cancel(){
    }

    @Override
    public void run() {
        InputStream is = null;
        try {
            if (client.running.get())
                return;
            client.running.set(true);

            URL streamUri = new URL(client.getEventStreamUri());
            is = getInputStream(streamUri);
            client.errorsCount.set(0);
            readStream(is);
        } catch (InterruptedException ie){
            //Caused by stopBackgroundThread(), exit without restarting as the new bgThread does it
            Log.i("EventStream.run(): Caught InterruptedException");
            return;
        } catch (Exception e) {
            Log.e("Error reading from event-stream, continuous errors: " + client.errorsCount.incrementAndGet(), e);
            Log.e(Utils.getStackTrace(e));
        } finally {
            Utils.closeQuietly(is);
            client.running.set(false);
        }

        if (!client.running.get()){
            client.restart();
        }
    }

    protected int readFromStream(InputStream inputStream, byte[] buffer) throws IOException, InterruptedException {
        int len;
        while (true) {
            //Checking if bytes are available to read to avoid performing a blocking read
            int available = inputStream.available();
            if (available > 0) break;
            Thread.sleep(1); //If bgThread was interrupted this throws an InterruptedException
        }

        len = inputStream.read(buffer);
        return len;
    }

    protected void readStream(InputStream inputStream) throws IOException, InterruptedException {
        byte[] buffer = new byte[BufferSize];
        String overflowText = "";

        int len = 0;
        while (true) {
            len = readFromStream(inputStream, buffer);

            if (len <= 0)
                break;

            String text = overflowText + new String(buffer, 0, len, "UTF-8");

            int pos;
            while ((pos = text.indexOf('\n')) >= 0) {
                if (pos == 0) {
                    if (currentMsg != null)
                        processEventMessage(currentMsg);
                    currentMsg = null;

                    text = text.substring(pos + 1);

                    if (!Utils.isEmpty(text))
                        continue;

                    break;
                }

                String line = text.substring(0, pos);
                if (!Utils.isNullOrWhiteSpace(line))
                    processLine(line);
                if (text.length() > pos + 1)
                    text = text.substring(pos + 1);
            }

            overflowText = text;
        }

        if (Log.isDebugEnabled())
            Log.d("Connection ended on " + client.getConnectionDisplayName());
    }

    protected void processLine(String line) {
        if (line == null || line.length() == 0)
            return;

        if (currentMsg == null)
            currentMsg = new ServerEventMessage();

        String[] parts = Utils.splitOnFirst(line, ':');
        String label = parts[0];
        String data = parts[1];
        if (data.length() > 0 && data.charAt(0) == ' ')
            data = data.substring(1);

        if ("id".equals(label)) {
            currentMsg.setEventId(Long.parseLong(data));
        } else if ("data".equals(label)) {
            currentMsg.setData(data);
        }
    }

    protected void processEventMessage(ServerEventMessage e) {
        String[] parts = Utils.splitOnFirst(e.getData(), ' ');
        e.setSelector(parts[0]);
        String[] selParts = Utils.splitOnFirst(e.getSelector(), '@');
        if (selParts.length > 1) {
            e.setChannel(selParts[0]);
            e.setSelector(selParts[1]);
        }

        e.setJson(parts[1]);

        if (!Utils.isNullOrEmpty(e.getSelector())) {
            parts = Utils.splitOnFirst(e.getSelector(), '.');
            if (parts.length < 2)
                throw new IllegalArgumentException("Invalid Selector '" + e.getSelector() + "'");

            e.setOp(parts[0]);
            String target = parts[1].replace("%20", " ");

            String[] tokens = Utils.splitOnFirst(target, '$');
            e.setTarget(tokens[0]);
            if (tokens.length > 1)
                e.setCssSelector(tokens[1]);

            if ("cmd".equals(e.getOp())) {
                target = e.getTarget();
                if ("onConnect".equals(target)) {
                    client.processOnConnectMessage(e);
                    return;
                } else if ("onJoin".equals(target)) {
                    client.processOnJoinMessage(e);
                    return;
                } else if ("onLeave".equals(target)) {
                    client.processOnLeaveMessage(e);
                    return;
                } else if ("onUpdate".equals(target)) {
                    client.processOnUpdateMessage(e);
                    return;
                } else if ("onHeartbeat".equals(target)) {
                    client.processOnHeartbeatMessage(e);
                    return;
                } else {
                    ServerEventCallback cb = client.getHandlers().get(e.getTarget());
                    if (cb != null) {
                        cb.execute(this.client, e);
                    }
                }
            } else if ("trigger".equals(e.getOp())){
                client.onTriggerReceived(e);
            }

            ServerEventCallback receiver = client.getNamedReceivers().get(e.getOp());
            if (receiver != null) {
                receiver.execute(this.client, e);
            }
        }

        client.onMessageReceived(e);
    }
}

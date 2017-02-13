package net.servicestack.client.sse;

import com.google.gson.JsonObject;

import net.servicestack.client.IReceiver;
import net.servicestack.client.IResolver;
import net.servicestack.client.JsonServiceClient;
import net.servicestack.client.JsonUtils;
import net.servicestack.client.Log;
import net.servicestack.client.Utils;
import net.servicestack.func.Func;
import net.servicestack.func.Function;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mythz on 2/9/2017.
 */

public class ServerEventsClient implements AutoCloseable {
    private String baseUri;
    private String[] channels;
    private String eventStreamPath;
    private String eventStreamUri;
    private JsonServiceClient serviceClient;
    private IResolver resolver;

    private Map<String,ServerEventCallback> handlers;
    private Map<String,ServerEventCallback> namedReceivers;

    private ServerEventConnectCallback onConnect;
    private ServerEventMessageCallback onMessage;
    private ServerEventMessageCallback onCommand;
    private ServerEventMessageCallback onHeartbeat;
    private ExceptionCallback onException;
    private HttpRequestFilter heartbeatRequestFilter;

    private ServerEventConnect connectionInfo;

    private Date lastPulseAt;
    private Thread bgThread;
    private final AtomicBoolean stopped = new AtomicBoolean(false);

    static int BufferSize = 1024 * 64;
    static int DefaultHeartbeatMs = 10 * 1000;
    static int DefaultIdleTimeoutMs = 30 * 1000;
    public static String UnknownChannel = "*";

    public ServerEventsClient(String baseUri, String... channels) {
        setBaseUri(baseUri);
        setChannels(channels);
        this.serviceClient = new JsonServiceClient(baseUri);
        this.resolver = new NewInstanceResolver();

        this.handlers = new HashMap<>();
        this.namedReceivers = new HashMap<>();
    }

    public ServerEventsClient(String baseUrl, String channel) {
        this(baseUrl, new String[]{ channel });
    }

    public ServerEventsClient(String baseUrl) {
        this(baseUrl, new String[]{});
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
        this.eventStreamPath = Utils.combinePath(baseUri, "event-stream");
        buildEventStreamUri();

        if (this.serviceClient != null)
            this.serviceClient.setBaseUrl(baseUri);
    }

    public String[] getChannels() {
        return channels;
    }

    public void setChannels(String[] channels) {
        if (channels == null)
            channels = new String[0];

        this.channels = channels;
        buildEventStreamUri();
    }

    private void buildEventStreamUri() {
        //Encode channel names to avoid URLEncoder encoding ',' separator
        List<String> encodedChannels = Func.map(channels != null ? channels : new String[0], new Function<String, String>() {
            @Override
            public String apply(String x) {
                try {
                    return URLEncoder.encode(x, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    return x;
                }
            }
        });
        this.eventStreamUri = Utils.addQueryParam(this.eventStreamPath, "channels", Utils.join(encodedChannels, ","), false);
    }

    public String getEventStreamUri() {
        return eventStreamUri;
    }

    public JsonServiceClient getServiceClient() {
        return this.serviceClient;
    }

    public IResolver getResolver() {
        return resolver;
    }

    public ServerEventsClient setResolver(IResolver resolver) {
        this.resolver = resolver;
        return this;
    }

    public ServerEventsClient setOnConnect(ServerEventConnectCallback onConnect) {
        this.onConnect = onConnect;
        return this;
    }

    public ServerEventsClient setOnMessage(ServerEventMessageCallback onMessage) {
        this.onMessage = onMessage;
        return this;
    }

    public ServerEventsClient setOnCommand(ServerEventMessageCallback onCommand) {
        this.onCommand = onCommand;
        return this;
    }

    public ServerEventsClient setOnException(ExceptionCallback onException) {
        this.onException = onException;
        return this;
    }

    public ServerEventsClient setHeartbeatRequestFilter(HttpRequestFilter heartbeatRequestFilter) {
        this.heartbeatRequestFilter = heartbeatRequestFilter;
        return this;
    }

    public ServerEventsClient setOnHeartbeat(ServerEventMessageCallback onHeartbeat) {
        this.onHeartbeat = onHeartbeat;
        return this;
    }

    public Map<String, ServerEventCallback> getHandlers() {
        return handlers;
    }

    public void setHandlers(Map<String, ServerEventCallback> handlers) {
        this.handlers = handlers;
    }

    public ServerEventsClient registerHandler(String name, ServerEventCallback handler){
        this.handlers.put(name, handler);
        return this;
    }

    public Map<String, ServerEventCallback> getNamedReceivers() {
        return namedReceivers;
    }

    public ServerEventsClient registerReceiver(Class<?> receiverClass) {
        return registerNamedReceiver("cmd", receiverClass);
    }

    public ServerEventsClient registerNamedReceiver(String name, Class<?> namedReceiverClass) {

        if (!IReceiver.class.isAssignableFrom(namedReceiverClass))
            throw new IllegalArgumentException(namedReceiverClass.getSimpleName() + " must implement IReceiver");

        namedReceivers.put(name, new ServerEventCallback() {
            @Override
            public void execute(ServerEventsClient client, ServerEventMessage msg) {
                try {
                    IReceiver receiver = (IReceiver)resolver.TryResolve(namedReceiverClass);

                    if (receiver instanceof ServerEventReceiver){
                        ServerEventReceiver injectReceiver = (ServerEventReceiver)receiver;
                        injectReceiver.setClient(client);
                        injectReceiver.setRequest(msg);
                    }

                    String target = msg.getTarget().replace("-",""); //css bg-image

                    for (Method mi : namedReceiverClass.getDeclaredMethods()){
                        if (!Modifier.isPublic(mi.getModifiers()) || Modifier.isStatic(mi.getModifiers()))
                            continue;
                        if (mi.getParameterCount() != 1)
                            continue;
                        if ("equals".equals(mi.getName()))
                            continue;

                        Class[] args = mi.getParameterTypes();

                        Class requestType = args[0];

                        if (target.equals(requestType.getSimpleName())) {
                            Object request = !Utils.isNullOrEmpty(msg.getJson())
                                ? JsonUtils.fromJson(msg.getJson(), requestType)
                                : requestType.newInstance();
                            mi.invoke(receiver, request);
                            return;
                        }

                        String actionName = mi.getName();
                        if (!target.equalsIgnoreCase(actionName) && actionName.startsWith("set"))
                            actionName = actionName.substring(3); //= "set".length()

                        if (target.equalsIgnoreCase(actionName)) {
                            Object request = !Utils.isNullOrEmpty(msg.getJson())
                                ? JsonUtils.fromJson(msg.getJson(), requestType)
                                : requestType.newInstance();
                            mi.invoke(receiver, request);
                            return;
                        }
                    }

                    receiver.noSuchMethod(msg.getTarget(), msg);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return this;
    }

    public ServerEventConnect getConnectionInfo() {
        return connectionInfo;
    }

    public String getSubscriptionId(){
        return connectionInfo != null
            ? connectionInfo.getId()
            : null;
    }

    public String getConnectionDisplayName() {
        return connectionInfo != null
            ? connectionInfo.getDisplayName()
            : "(not connected)";
    }

    public ServerEventsClient start(){
        if (bgThread != null){
            bgThread.interrupt();
            bgThread = null;
        }

        stopped.set(false);
        bgThread = new Thread(new EventStream(this));
        bgThread.start();
        lastPulseAt = new Date();

        return this;
    }

    public synchronized void restart() {
        try {
            internalStop();

            if (stopped.get())
                return;

            try {
                sleepBackOffMultiplier(errorsCount);
                start();
            } catch (Exception e){
                onExceptionReceived(e);
            }

        } catch (Exception ex){
            Log.e("[SSE-CLIENT] Error whilst restarting: " + ex.getMessage(), ex);
            ex.printStackTrace();
        }
    }

    private void sleepBackOffMultiplier(int continuousErrorsCount) throws InterruptedException {
        if (continuousErrorsCount <= 1)
            return;

        final int MaxSleepMs = 60 * 1000;

        Random rand = new Random();
        int min = (int)Math.pow(continuousErrorsCount, 3);
        int max = (int)Math.pow(continuousErrorsCount + 1, 3);

        int nextTry = Math.min(
            rand.nextInt(max - min + 1) + min,
            MaxSleepMs
        );

        if (Log.isDebugEnabled())
            Log.d("Sleeping for " + nextTry + "ms after " + continuousErrorsCount + " continuous errors");

        Thread.sleep(nextTry);
    }

    public synchronized void stop(){
        stopped.set(true);
        internalStop();
    }

    public ServerEventsClient waitTillConnected() throws Exception {
        return waitTillConnected(Integer.MAX_VALUE);
    }

    public ServerEventsClient waitTillConnected(int timeoutMs) throws Exception {
        Date startedAt = new Date();
        while (connectionInfo == null) {
            Thread.sleep(50);

            if ((new Date().getTime() - startedAt.getTime()) > timeoutMs)
                throw new TimeoutException("Not connected after " + timeoutMs + "ms");
        }
        return this;
    }

    private synchronized void internalStop() {
        if (Log.isDebugEnabled())
            Log.d("Stop() " + getConnectionDisplayName());

        if (connectionInfo != null && connectionInfo.getUnRegisterUrl() != null) {
            try {
                Utils.readToEnd(connectionInfo.getUnRegisterUrl());
            } catch (Exception ignore) {}
        }

        connectionInfo = null;
        if (bgThread != null)
            bgThread.interrupt();

        bgThread = null;
    }

    private void onCommandReceived(ServerEventMessage e) {
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] OnCommandReceived: ("
                    + e.getClass().getSimpleName() + ") #"
                    + e.getEventId() + " on #"
                    + getConnectionDisplayName() + " ("
                    + Utils.join(channels, ",") + ")");

        if (onCommand != null)
            onCommand.execute(e);
    }

    private void onHeartbeatReceived(ServerEventMessage e) {
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] OnHeartbeatReceived: ("
                    + e.getClass().getSimpleName() + ") #"
                    + e.getEventId() + " on #"
                    + getConnectionDisplayName() + " ("
                    + Utils.join(channels, ",") + ")");

        if (onHeartbeat != null)
            onHeartbeat.execute(e);
    }

    private void onMessageReceived(ServerEventMessage e) {
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] OnMessageReceived: " + e.getEventId() + " on #"
                    + getConnectionDisplayName() + " " +  Utils.join(channels, ","));

        if (onMessage != null)
            onMessage.execute(e);
    }

    private int errorsCount;
    protected void onExceptionReceived(Exception ex) {
        errorsCount++;

        Log.e("[SSE-CLIENT] OnExceptionReceived: "
                + ex.getMessage() + " on #" + getConnectionDisplayName(), ex);

        if (Log.isDebugEnabled())
            Log.d(Utils.getStackTrace(ex));

        if (onException != null)
            onException.execute(ex);

        restart();
    }

    private void onConnectReceived() {
        if (Log.isDebugEnabled())
            Log.d(String.format("[SSE-CLIENT] OnConnectReceived: %s on #%s / %s on (%s)",
                    connectionInfo.getEventId(),
                    getConnectionDisplayName(),
                    connectionInfo.getId(),
                    Utils.join(channels, ",")));

        if (onConnect != null)
            onConnect.execute(connectionInfo);

        startNewHeartbeat();
    }

    Timer heratbeatTimer;

    private void startNewHeartbeat() {
        if (connectionInfo == null || connectionInfo.getHeartbeatUrl() == null)
            return;

        if (stopped.get())
            return;

        if (heratbeatTimer == null)
            heratbeatTimer = new Timer("ServerEventsClient Heartbeat");

        //reschedule timer on every heartbeat
        heratbeatTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Heartbeat();
            }
        }, connectionInfo.getHeartbeatIntervalMs(), Integer.MAX_VALUE);
    }

    public void Heartbeat(){
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] Prep for Heartbeat...");

        if (connectionInfo == null)
            return;

        if (stopped.get())
            return;

        long elapsedMs = (new Date().getTime() - lastPulseAt.getTime());
        if (elapsedMs > connectionInfo.getIdleTimeoutMs())
        {
            onExceptionReceived(new TimeoutException("Last Heartbeat Pulse was " + elapsedMs + "ms ago"));
            return;
        }

        try {
            URL heartbeatUrl = new URL(connectionInfo.getHeartbeatUrl());
            HttpURLConnection conn = (HttpURLConnection)heartbeatUrl.openConnection();
            if (heartbeatRequestFilter != null)
                heartbeatRequestFilter.execute(conn);

            if (Log.isDebugEnabled())
                Log.d("[SSE-CLIENT] Sending Heartbeat...");

            try {
                String response = Utils.readToEnd(conn.getInputStream(), "UTF-8");
            } catch (FileNotFoundException notFound) {

                if (stopped.get())
                    return;

                Log.e(conn.getResponseMessage(), notFound);
                throw notFound;
            }

            if (Log.isDebugEnabled())
                Log.d("[SSE-CLIENT] Heartbeat sent to: " + heartbeatUrl);

            startNewHeartbeat();

        } catch (Exception e) {
            if (Log.isDebugEnabled())
                Log.d("[SSE-CLIENT] Error from Heartbeat: " + e);

            onExceptionReceived(e);
        }
    }

    private void processOnConnectMessage(ServerEventMessage e) {
        JsonObject msg = JsonUtils.toJsonObject(e.getJson());
        connectionInfo = new ServerEventConnect();

        connectionInfo.setId(JsonUtils.asString(msg, "id"));
        connectionInfo.setHeartbeatUrl(JsonUtils.asString(msg, "heartbeatUrl"));
        connectionInfo.setHeartbeatIntervalMs(JsonUtils.asLong(msg, "heartbeatIntervalMs", DefaultHeartbeatMs));
        connectionInfo.setIdleTimeoutMs(JsonUtils.asLong(msg, "idleTimeoutMs", DefaultIdleTimeoutMs));
        connectionInfo.setUnRegisterUrl(JsonUtils.asString(msg, "unRegisterUrl"));
        connectionInfo.setUserId(JsonUtils.asString(msg, "userId"));
        connectionInfo.setDisplayName(JsonUtils.asString(msg, "displayName"));
        connectionInfo.setAuthenticated("true".equals(JsonUtils.asString(msg, "isAuthenticated")));
        connectionInfo.setProfileUrl(JsonUtils.asString(msg, "profileUrl"));

        onConnectReceived();
    }

    private void processOnJoinMessage(ServerEventMessage e) {
        onCommandReceived(new ServerEventJoin().populate(e, JsonUtils.toJsonObject(e.getJson())));
    }

    private void processOnLeaveMessage(ServerEventMessage e) {
        onCommandReceived(new ServerEventLeave().populate(e, JsonUtils.toJsonObject(e.getJson())));
    }

    private void processOnUpdateMessage(ServerEventMessage e) {
        onCommandReceived(new ServerEventUpdate().populate(e, JsonUtils.toJsonObject(e.getJson())));
    }

    private void processOnHeartbeatMessage(ServerEventMessage e) {
        lastPulseAt = new Date();
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] LastPulseAt: " + new SimpleDateFormat("HH:mm:ss.SSS", Locale.US).format(lastPulseAt));

        onHeartbeatReceived(new ServerEventHeartbeat().populate(e, JsonUtils.toJsonObject(e.getJson())));
    }

    @Override
    public void close() throws Exception {
        stop();
    }

    class EventStream implements Runnable {

        ServerEventsClient client;
        ServerEventMessage currentMsg;

        public EventStream(ServerEventsClient client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                URL streamUri = new URL(client.getEventStreamUri());
                HttpURLConnection req = (HttpURLConnection) streamUri.openConnection();

                InputStream is = new BufferedInputStream(req.getInputStream());
                readStream(is);
            } catch (IOException e) {
                Log.e("Error reading from event-stream", e);
                Log.e(Utils.getStackTrace(e));
            } finally {
                client.restart();
            }
        }

        private void readStream(InputStream inputStream) throws IOException {
            byte[] buffer = new byte[BufferSize];
            String overflowText = "";

            int len = 0;
            while (true) {
                len = inputStream.read(buffer);
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

        private void processLine(String line) {
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

        private void processEventMessage(ServerEventMessage e) {
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
                }

                ServerEventCallback receiver = client.getNamedReceivers().get(e.getOp());
                if (receiver != null) {
                    receiver.execute(this.client, e);
                }
            }

            client.onMessageReceived(e);
        }
    }

    public List<ServerEventUser> getChannelSubscribers(){
        ArrayList<HashMap<String,String>> response = this.serviceClient.get(
            new GetEventSubscribers().setChannels(Func.toList(this.getChannels())));

        return Func.map(response, new Function<HashMap<String, String>, ServerEventUser>() {
            @Override
            public ServerEventUser apply(HashMap<String, String> map) {
                String channels = map.get("channels");
                ServerEventUser to = new ServerEventUser()
                    .setUserId(map.get("userId"))
                    .setDisplayName(map.get("displayName"))
                    .setProfileUrl(map.get("profileUrl"))
                    .setChannels(Utils.isNullOrEmpty(channels) ? channels.split(",") : null);

                final ArrayList<String> reservedNames = Func.toList("userId", "displayName", "profileUrl", "channels");
                for (Map.Entry<String,String> entry : map.entrySet()){
                    if (reservedNames.contains(entry.getKey()))
                        continue;

                    if (to.getMeta() == null)
                        to.setMeta(new HashMap<String,String>());

                    to.getMeta().put(entry.getKey(), entry.getValue());
                }

                return to;
            }
        });
    }

    public void updateSubscriber(UpdateEventSubscriber request){
        if (request.getId() == null)
            request.setId(connectionInfo.getId());

        serviceClient.post(request);

        update(
            Func.toArray(request.getSubscribeChannels(), String.class),
            Func.toArray(request.getUnsubscribeChannels(), String.class));
    }

    public void subscribeToChannels(String... channels)
    {
        serviceClient.post(new UpdateEventSubscriber()
            .setId(connectionInfo.getId())
            .setSubscribeChannels(Func.toList(channels)));

        update(channels, null);
    }

    public void unSubscribeFromChannels(String... channels)
    {
        serviceClient.post(new UpdateEventSubscriber()
                .setId(connectionInfo.getId())
                .setUnsubscribeChannels(Func.toList(channels)));

        update(null, channels);
    }

    public void update(String[] subscribe, String[] unsubscribe)
    {
        List<String> snapshot = Func.toList(getChannels());
        if (subscribe != null)
        {
            for (String channel : subscribe){
                if (!snapshot.contains(channel))
                    snapshot.add(channel);
            }
        }
        if (unsubscribe != null)
        {
            snapshot.removeAll(Func.toList(unsubscribe));
        }
        setChannels(Func.toArray(snapshot, String.class));
    }

}


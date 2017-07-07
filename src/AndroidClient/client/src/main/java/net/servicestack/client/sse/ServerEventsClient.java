package net.servicestack.client.sse;

import com.google.gson.JsonObject;

import net.servicestack.client.IReceiver;
import net.servicestack.client.IResolver;
import net.servicestack.client.JsonServiceClient;
import net.servicestack.client.JsonUtils;
import net.servicestack.client.Log;
import net.servicestack.client.Utils;
import net.servicestack.func.Action;
import net.servicestack.func.ActionVoid;
import net.servicestack.func.Func;
import net.servicestack.func.Function;

import java.io.Closeable;
import java.io.FileNotFoundException;
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
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mythz on 2/9/2017.
 */

public class ServerEventsClient implements Closeable {
    protected String baseUri;
    protected String[] channels;
    protected String eventStreamPath;
    protected String eventStreamUri;
    protected JsonServiceClient serviceClient;
    protected IResolver resolver;

    protected Map<String,ServerEventCallback> handlers;
    protected Map<String,ServerEventCallback> namedReceivers;
    protected Map<String, List<Action<ServerEventMessage>>> listeners;

    protected ServerEventConnectCallback onConnect;
    protected ServerEventMessageCallback onMessage;
    protected ServerEventJoinCallback onJoin;
    protected ServerEventLeaveCallback onLeave;
    protected ServerEventUpdateCallback onUpdate;
    protected ServerEventMessageCallback onCommand;
    protected ServerEventMessageCallback onHeartbeat;
    protected ActionVoid onReconnect;
    protected ExceptionCallback onException;
    protected HttpRequestFilter heartbeatRequestFilter;

    protected ServerEventConnect connectionInfo;

    protected Date lastPulseAt;
    protected Thread bgThread;
    protected EventStream bgEventStream;
    protected final AtomicBoolean stopped = new AtomicBoolean(false);
    protected final AtomicBoolean running = new AtomicBoolean(false);
    protected final AtomicInteger errorsCount = new AtomicInteger();

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
        this.listeners = new HashMap<>();
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

    public ServerEventsClient setOnJoin(ServerEventJoinCallback onJoin) {
        this.onJoin = onJoin;
        return this;
    }

    public ServerEventsClient setOnLeave(ServerEventLeaveCallback onLeave) {
        this.onLeave = onLeave;
        return this;
    }

    public ServerEventsClient setOnUpdate(ServerEventUpdateCallback onUpdate) {
        this.onUpdate = onUpdate;
        return this;
    }

    public ServerEventsClient setOnCommand(ServerEventMessageCallback onCommand) {
        this.onCommand = onCommand;
        return this;
    }

    public ServerEventsClient setOnReconnect(ActionVoid onReconnect) {
        this.onReconnect = onReconnect;
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

    public ServerEventsClient registerNamedReceiver(String name, final Class<?> namedReceiverClass) {

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
                        Class[] args = mi.getParameterTypes();
                        if (args.length > 1)
                            continue;
                        if ("equals".equals(mi.getName()))
                            continue;

                        String actionName = mi.getName();
                        if (!target.equalsIgnoreCase(actionName) && actionName.startsWith("set"))
                            actionName = actionName.substring(3); //= "set".length()

                        if (args.length == 0){
                            if (target.equalsIgnoreCase(actionName)) {
                                mi.invoke(receiver, null);
                                return;
                            }
                            continue;
                        }

                        Class requestType = args[0];

                        if (target.equals(requestType.getSimpleName()) &&
                            mi.getName().toLowerCase().equals(target.toLowerCase())) {
                            Object request = !Utils.isNullOrEmpty(msg.getJson())
                                ? JsonUtils.fromJson(msg.getJson(), requestType)
                                : requestType.newInstance();
                            mi.invoke(receiver, request);
                            return;
                        }

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

    protected synchronized void stopBackgroundThread() {
        if (bgThread != null){
            bgEventStream.cancel();
            bgThread.interrupt(); //notify the bgThread to exit
            try {
                bgThread.join();
            } catch (InterruptedException ignore) {}
            bgThread = null;
        }
    }

    protected EventStream createEventStream(){
        return new EventStream(this);
    }

    public synchronized ServerEventsClient start(){
        stopBackgroundThread();

        stopped.set(false);
        bgEventStream = createEventStream();
        bgThread = new Thread(bgEventStream);
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
                sleepBackOffMultiplier(errorsCount.intValue());
                start();
            } catch (Exception e){
                onExceptionReceived(e);
            }

            if (onReconnect != null){
                onReconnect.apply();
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

        Log.info("Sleeping for " + nextTry + "ms after " + continuousErrorsCount + " continuous errors");

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

        if (heartbeatTimer != null)
        {
            try {
                heartbeatTimer.shutdown();
            } catch (Exception ignore) {}
            heartbeatTimer = null;
        }

        connectionInfo = null;
        stopBackgroundThread();
    }

    private void onJoinReceived(ServerEventJoin e) {
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] OnJoinReceived: ("
                    + e.getClass().getSimpleName() + ") #"
                    + e.getEventId() + " on #"
                    + getConnectionDisplayName() + " ("
                    + Utils.join(channels, ",") + ")");

        if (onJoin != null)
            onJoin.execute(e);
    }

    private void onLeaveReceived(ServerEventLeave e) {
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] OnLeaveReceived: ("
                    + e.getClass().getSimpleName() + ") #"
                    + e.getEventId() + " on #"
                    + getConnectionDisplayName() + " ("
                    + Utils.join(channels, ",") + ")");

        if (onLeave != null)
            onLeave.execute(e);
    }

    private void onUpdateReceived(ServerEventUpdate e) {
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] OnUpdateReceived: ("
                    + e.getClass().getSimpleName() + ") #"
                    + e.getEventId() + " on #"
                    + getConnectionDisplayName() + " ("
                    + Utils.join(channels, ",") + ")");

        if (onUpdate != null)
            onUpdate.execute(e);
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

    protected void onTriggerReceived(ServerEventMessage e) {
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] OnTriggerReceived: ("
                    + e.getClass().getSimpleName() + ") #"
                    + e.getEventId() + " on #"
                    + getConnectionDisplayName() + " ("
                    + Utils.join(channels, ",") + ")");

        raiseEvent(e.getTarget(), e);
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

    protected void onMessageReceived(ServerEventMessage e) {
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] OnMessageReceived: " + e.getEventId() + " on #"
                    + getConnectionDisplayName() + " " +  Utils.join(channels, ","));

        if (onMessage != null)
            onMessage.execute(e);
    }

    protected void onExceptionReceived(Exception ex) {
        errorsCount.incrementAndGet();

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

    public synchronized ServerEventsClient addListener(String eventName, Action<ServerEventMessage> handler){
        List<Action<ServerEventMessage>> handlers = listeners.get(eventName);
        if (handlers == null){
            handlers = new ArrayList<>();
            listeners.put(eventName, handlers);
        }
        handlers.add(handler);
        return this;
    }

    public synchronized ServerEventsClient removeListener(String eventName, Action<ServerEventMessage> handler){
        List<Action<ServerEventMessage>> handlers = listeners.get(eventName);
        if (handlers != null){
            handlers.remove(handler);
        }
        return this;
    }

    public synchronized void raiseEvent(String eventName, ServerEventMessage message) {
        List<Action<ServerEventMessage>> handlers = listeners.get(eventName);
        if (handlers != null){
            for (Action<ServerEventMessage> handler : handlers) {
                try {
                    handler.apply(message);
                } catch (Exception e) {
                    Log.e("Error whilst executing '" + eventName + "' handler", e);
                }
            }
        }
    }

    ScheduledThreadPoolExecutor heartbeatTimer;

    private void startNewHeartbeat() {
        if (connectionInfo == null || connectionInfo.getHeartbeatUrl() == null)
            return;

        if (stopped.get())
            return;

        if (heartbeatTimer == null)
            heartbeatTimer = new ScheduledThreadPoolExecutor(1);

        heartbeatTimer.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Heartbeat();
            }
        }, connectionInfo.getHeartbeatIntervalMs(), connectionInfo.getHeartbeatIntervalMs(), TimeUnit.MILLISECONDS);
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

        } catch (Exception e) {
            if (Log.isDebugEnabled())
                Log.d("[SSE-CLIENT] Error from Heartbeat: " + e);

            onExceptionReceived(e);
        }
    }

    protected void processOnConnectMessage(ServerEventMessage e) {
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

    protected void processOnJoinMessage(ServerEventMessage e) {
        ServerEventJoin m = new ServerEventJoin();
        m.populate(e, JsonUtils.toJsonObject(e.getJson()));
        onJoinReceived(m);
        onCommandReceived(m);
    }

    protected void processOnLeaveMessage(ServerEventMessage e) {
        ServerEventLeave m = new ServerEventLeave();
        m.populate(e, JsonUtils.toJsonObject(e.getJson()));
        onLeaveReceived(m);
        onCommandReceived(m);
    }

    protected void processOnUpdateMessage(ServerEventMessage e) {
        ServerEventUpdate m = new ServerEventUpdate();
        m.populate(e, JsonUtils.toJsonObject(e.getJson()));
        onUpdateReceived(m);
        onCommandReceived(m);
    }

    protected void processOnHeartbeatMessage(ServerEventMessage e) {
        lastPulseAt = new Date();
        if (Log.isDebugEnabled())
            Log.d("[SSE-CLIENT] LastPulseAt: " + new SimpleDateFormat("HH:mm:ss.SSS", Locale.US).format(lastPulseAt));

        onHeartbeatReceived(new ServerEventHeartbeat().populate(e, JsonUtils.toJsonObject(e.getJson())));
    }

    @Override
    public void close() {
        stop();
    }

    public List<ServerEventUser> getChannelSubscribers(){
        ArrayList<HashMap<String,String>> response = this.serviceClient.get(
            new GetEventSubscribers().setChannels(Func.toList(this.getChannels())));

        return toServerEventUser(response);
    }

    protected ArrayList<ServerEventUser> toServerEventUser(ArrayList<HashMap<String, String>> response) {
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


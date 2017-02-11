package net.servicestack.client.tests;

import junit.framework.TestCase;

import net.servicestack.client.JsonUtils;
import net.servicestack.client.Log;
import net.servicestack.client.LogProvider;
import net.servicestack.client.Utils;
import net.servicestack.client.sse.ServerEventConnect;
import net.servicestack.client.sse.ServerEventJoin;
import net.servicestack.client.sse.ServerEventLeave;
import net.servicestack.client.sse.ServerEventMessage;
import net.servicestack.client.sse.ServerEventsClient;
import net.servicestack.func.Func;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static chat.chatdtos.ChatMessage;
import static chat.chatdtos.PostChatToChannel;

/**
 * Created by mythz on 2/10/2017.
 */

public class ServerEventClientTests extends TestCase {

    public ServerEventClientTests() {
        Log.setInstance(new LogProvider("", true));
    }

    public void test_Can_connect_to_ServerEventsStream() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        try (ServerEventsClient client = new ServerEventsClient("http://chat.servicestack.net", "home")
                .setOnConnect(e -> {
                    System.out.print("onConnect: " + e);
                    signal.countDown();
                }).start())
        {
            assertTrue(signal.await(5, TimeUnit.SECONDS));
        }
    }

    public void test_Does_fire_onJoin_events() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        try(ServerEventsClient client = new ServerEventsClient("http://chat.servicestack.net", "home"))
        {
            client
                .setOnConnect(e -> {
                    assertTrue(e.getHeartbeatUrl().startsWith("http://chat.servicestack.net"));
                })
                .setOnCommand(e -> {
                    System.out.print("onCommand: " + e);
                    assertTrue(e instanceof ServerEventJoin);
                    ServerEventJoin joinMsg = (ServerEventJoin)e;
                    assertEquals(client.getConnectionInfo().getDisplayName(), joinMsg.getDisplayName());
                    signal.countDown();
                })
                .start();

            assertTrue(signal.await(5, TimeUnit.SECONDS));
        }
    }

    public void test_Does_fire_onJoin_events_for_multiple_Channels() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        String[] channels = new String[] { "A", "B", "C" };
        List<ServerEventJoin> joinMsgs = new ArrayList<>();

        try (ServerEventsClient client = new ServerEventsClient("http://chat.servicestack.net", channels))
        {
            client
                .setOnCommand(e -> {
                    System.out.print("onCommand: " + e);

                    if (e instanceof ServerEventJoin) {
                        ServerEventJoin joinMsg = (ServerEventJoin)e;
                        joinMsgs.add(joinMsg);

                        assertEquals(channels[joinMsgs.size() - 1], joinMsg.getChannel());
                        assertEquals(client.getConnectionInfo().getDisplayName(), joinMsg.getDisplayName());

                        if (joinMsgs.size() == channels.length)
                            signal.countDown();
                    }
                })
                .start();

            assertTrue(signal.await(5, TimeUnit.SECONDS));
        }
    }

    private void clearPreviousRun(String[] channels) throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        try (ServerEventsClient client = new ServerEventsClient("http://chat.servicestack.net", channels)
                .setOnConnect(e -> {
                    signal.countDown();
                })
                .start())
        {
            assertTrue(signal.await(5, TimeUnit.SECONDS));
        }
    }

    public void ignore_test_ClearPreviousRun() throws Exception {
        clearPreviousRun(null);
    }

    public void test_Does_fire_all_callbacks() throws Exception {
//        clearPreviousRun(null);

        List<ServerEventConnect> connectMsgs = new ArrayList<>();
        List<ServerEventMessage> msgs = new ArrayList<>();
        List<ServerEventMessage> commands = new ArrayList<>();
        List<Exception> errors = new ArrayList<>();

        try(ServerEventsClient client1 = new ServerEventsClient("http://chat.servicestack.net")
            .setOnConnect(connectMsgs::add)
            .setOnCommand(commands::add)
            .setOnMessage(msgs::add)
            .setOnException(errors::add)
            .start())
        {
            while (connectMsgs.size() == 0 || commands.size() == 0) {
                Thread.sleep(100);
            }

            ServerEventConnect connectMsg = Func.first(connectMsgs);
            ServerEventJoin joinMsg = Func.first(Func.ofType(commands, ServerEventJoin.class));
            assertNotNull(connectMsg);
            assertNotNull(joinMsg);

            assertEquals(0, msgs.size());
            assertEquals(0, errors.size());
            assertEquals(1, commands.size()); //join

            connectMsgs.clear();
            commands.clear();

            try(ServerEventsClient client2 = new ServerEventsClient("http://chat.servicestack.net")
                .setOnConnect(connectMsgs::add)
                .start())
            {
                while (connectMsgs.size() == 0 || commands.size() == 0) {
                    Thread.sleep(100);
                }

                connectMsg = Func.first(connectMsgs);
                joinMsg = Func.first(Func.ofType(commands, ServerEventJoin.class));

                Utils.readToEnd(connectMsg.getUnRegisterUrl());
            }

            while (commands.size() < 2) {
                Thread.sleep(100);
            }

            ServerEventLeave leaveMsg = Func.first(Func.ofType(commands, ServerEventLeave.class));

            assertNotNull(joinMsg);
            assertNotNull(leaveMsg);
            assertTrue(commands.size() >= 2);
            assertEquals(errors.size(), 0);
        }
    }

    private void postChat(ServerEventsClient client, String message) {
        postChat(client, message, null);
    }

    private void postChat(ServerEventsClient client, String message, String channel) {
        client.getServiceClient().post(new PostChatToChannel()
            .setFrom(client.getSubscriptionId())
            .setMessage(message)
            .setChannel(channel != null ? channel : ServerEventsClient.UnknownChannel)
            .setSelector("cmd.chat"));
    }

    public void test_Does_receive_messages() throws Exception {

        List<ServerEventConnect> connectMsgs = new ArrayList<>();
        List<ServerEventMessage> commands = new ArrayList<>();
        List<ServerEventMessage> msgs1 = new ArrayList<>();
        List<ServerEventMessage> msgs2 = new ArrayList<>();

        try (
            ServerEventsClient client1 = new ServerEventsClient("http://chat.servicestack.net")
                .setOnConnect(connectMsgs::add)
                .setOnCommand(commands::add)
                .setOnMessage(msgs1::add);

            ServerEventsClient client2 = new ServerEventsClient("http://chat.servicestack.net")
                .setOnConnect(connectMsgs::add)
                .setOnMessage(msgs2::add))
        {
            client1.start();

            while (connectMsgs.size() == 0 || commands.size() == 0) {
                Thread.sleep(100);
            }

            client2.start();

            while (connectMsgs.size() < 2 || commands.size() < 2) {
                Thread.sleep(100);
            }

            ServerEventConnect info1 = connectMsgs.get(0);
            postChat(client1, "hello from client1");

            while (msgs1.size() == 0 || msgs2.size() == 0) {
                Thread.sleep(100);
            }

            ServerEventMessage msg1 = Func.first(msgs1);
            ServerEventMessage msg2 = Func.first(msgs2);

            assertTrue(msg1.getEventId() > 0);
            assertTrue(msg2.getEventId() > 0);
            assertEquals("cmd.chat", msg1.getSelector());
            assertEquals("cmd.chat", msg2.getSelector());

            ChatMessage chatMsg1 = JsonUtils.fromJson(msg1.getJson(), ChatMessage.class);
            ChatMessage chatMsg2 = JsonUtils.fromJson(msg2.getJson(), ChatMessage.class);

            for (ChatMessage chatMsg : new ChatMessage[]{ chatMsg1, chatMsg2 }){
                assertTrue(chatMsg.getId() > 0);
                assertEquals(info1.getUserId(), chatMsg.getFromUserId());
                assertEquals(info1.getDisplayName(), chatMsg.getFromName());
                assertEquals(info1.getDisplayName(), chatMsg.getFromName());
            }

            assertEquals(1, msgs1.size());
            assertEquals(1, msgs2.size());

            ServerEventConnect info2 = connectMsgs.get(1);
            postChat(client2, "hello from client2");

            while (msgs1.size() < 2 || msgs2.size() < 2) {
                Thread.sleep(100);
            }

            msg1 = msgs1.get(1);
            msg2 = msgs2.get(1);

            chatMsg1 = JsonUtils.fromJson(msg1.getJson(), ChatMessage.class);
            chatMsg2 = JsonUtils.fromJson(msg2.getJson(), ChatMessage.class);

            for (ChatMessage chatMsg : new ChatMessage[]{ chatMsg1, chatMsg2 }){
                assertTrue(chatMsg.getId() > 0);
                assertEquals(info2.getUserId(), chatMsg.getFromUserId());
                assertEquals(info2.getDisplayName(), chatMsg.getFromName());
                assertEquals(info2.getDisplayName(), chatMsg.getFromName());
            }

            assertEquals(2, msgs1.size());
            assertEquals(2, msgs2.size());
        }
    }
}

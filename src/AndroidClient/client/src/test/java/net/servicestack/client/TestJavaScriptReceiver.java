package net.servicestack.client;

import net.servicestack.client.sse.ServerEventMessage;
import net.servicestack.client.sse.ServerEventReceiver;

import chat.chatdtos;

import static chat.chatdtos.*;

/**
 * Created by mythz on 2/12/2017.
 */

public class TestJavaScriptReceiver extends ServerEventReceiver {
    public static ChatMessage ChatReceived;
    public static String AnnounceReceived;
    public String AnnounceInstance;
    public static String ToggleReceived;
    public static ServerEventMessage ToggleRequestReceived;
    public static String BackgroundImageReceived;
    public static ServerEventMessage BackgroundImageRequestReceived;

    public void chat(ChatMessage message)
    {
        ChatReceived = message;
    }

    public void announce(String message)
    {
        AnnounceReceived = message;
        AnnounceInstance = message;
    }

    public void toggle()
    {
        ToggleReceived = "";
        ToggleRequestReceived = super.getRequest();
    }

    public void backgroundImage(String cssRule)
    {
        BackgroundImageReceived = cssRule;
        BackgroundImageRequestReceived = super.getRequest();
    }
}

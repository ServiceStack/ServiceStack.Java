package servicestack.net.androidchat;

import net.servicestack.client.sse.ServerEventReceiver;

/**
 * Created by mythz on 2/15/2017.
 */

public class CssReceiver extends ServerEventReceiver {
    private ChatCommandHandler chatMessageHandler;

    public CssReceiver(ChatCommandHandler chatMessageHandler){
        this.chatMessageHandler = chatMessageHandler;
    }

    public void backgroundImage(String message){
        chatMessageHandler.changeBackground(message);
    }

    public void background(String message){
        chatMessageHandler.changeBackgroundColor(message, super.getRequest().getCssSelector());
    }
}

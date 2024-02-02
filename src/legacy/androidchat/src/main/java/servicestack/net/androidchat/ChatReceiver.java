package servicestack.net.androidchat;

import net.servicestack.client.sse.ServerEventReceiver;

import static servicestack.net.androidchat.dtos.*;

/**
 * Created by mythz on 2/15/2017.
 */

public class ChatReceiver extends ServerEventReceiver {
    private ChatCommandHandler chatMessageHandler;

    public ChatReceiver(ChatCommandHandler chatMessageHandler) {
        this.chatMessageHandler = chatMessageHandler;
    }

    public void chat(ChatMessage chatMessage){
        chatMessageHandler.appendMessage(chatMessage);
    }

    public void announce(String message){
        chatMessageHandler.announce(message);
    }
}

package servicestack.net.androidchat;

import net.servicestack.client.sse.ServerEventReceiver;

/**
 * Created by mythz on 2/15/2017.
 */

public class TvReciever extends ServerEventReceiver {
    private ChatCommandHandler chatMessageHandler;

    public TvReciever(ChatCommandHandler chatMessageHandler) {
        this.chatMessageHandler = chatMessageHandler;
    }

    public void watch(String videoUrl) {
        chatMessageHandler.showVideo(videoUrl);
    }
}

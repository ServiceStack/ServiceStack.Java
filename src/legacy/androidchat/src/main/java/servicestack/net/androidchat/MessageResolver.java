package servicestack.net.androidchat;

import net.servicestack.client.IResolver;

/**
 * Created by mythz on 2/15/2017.
 */

public class MessageResolver implements IResolver {
    ChatCommandHandler messageHandler;

    public MessageResolver(ChatCommandHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public Object TryResolve(Class cls){
        if (cls == ChatReceiver.class){
            return new ChatReceiver(this.messageHandler);
        } else if (cls == TvReciever.class){
            return new TvReciever(this.messageHandler);
        } else if (cls == CssReceiver.class){
            return new CssReceiver(this.messageHandler);
        }

        try {
            return cls.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

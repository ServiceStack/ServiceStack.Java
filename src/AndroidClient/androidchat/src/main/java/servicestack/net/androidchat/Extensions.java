package servicestack.net.androidchat;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import net.servicestack.android.AndroidServerEventsClient;
import net.servicestack.android.AsyncUtils;
import net.servicestack.client.Utils;
import net.servicestack.client.sse.ServerEventConnect;
import net.servicestack.client.sse.ServerEventsClient;
import net.servicestack.func.Func;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static servicestack.net.androidchat.dtos.GetChatHistory;
import static servicestack.net.androidchat.dtos.PostRawToChannel;

/**
 * Created by mythz on 2/15/2017.
 */

public class Extensions {

    public static void updateChatHistory(AndroidServerEventsClient client, ChatCommandHandler cmdReceiver){

        client.getAndroidClient().getAsync(new GetChatHistory()
            .setChannels(Func.toList(client.getChannels())),
            chatHistory -> {
                cmdReceiver.setFullHistory(new HashMap<>());
                for (String channel : client.getChannels()){
                    cmdReceiver.getFullHistory().put(
                        channel,
                        Func.filter(chatHistory.getResults(), x ->
                            Objects.equals(x.getChannel(), channel)));
                }
                cmdReceiver.syncAdapter();
            });
    }

    public static void changeChannel(AndroidServerEventsClient client, String channel, ChatCommandHandler cmdReceiver){
        List<String> currentChannels = Func.toList(client.getChannels());

        if (cmdReceiver.getFullHistory().containsKey(channel) && currentChannels.contains(channel)){
            cmdReceiver.changeChannel(channel);
        } else {

            if (!currentChannels.contains(channel))
                currentChannels.add(channel);

            client.subscribeToChannels(Func.toArray(currentChannels, String.class));
            cmdReceiver.setCurrentChannel(channel);
            updateChatHistory(client, cmdReceiver);
        }
    }

    public static void sendMessage(ServerEventsClient client, PostRawToChannel request){
        client.getServiceClient().post(request);
    }

    public static void updateUserProfile(ServerEventConnect connectMsg, MainActivity activity){
        activity.runOnUiThread(() -> {
            TextView txtUser = (TextView)activity.findViewById(R.id.txtUserName);
            txtUser.setText(connectMsg.getDisplayName());

            App.get().readBitmap(connectMsg.getProfileUrl(), bitmap -> {
                ImageView imgProfile = (ImageView)activity.findViewById(R.id.imgProfile);
                imgProfile.setImageBitmap(bitmap);
            });
        });
    }
}

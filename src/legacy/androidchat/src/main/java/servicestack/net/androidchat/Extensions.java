package servicestack.net.androidchat;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import net.servicestack.android.AndroidServerEventsClient;
import net.servicestack.android.AsyncUtils;
import net.servicestack.client.AsyncSuccess;
import net.servicestack.client.AsyncSuccessVoid;
import net.servicestack.client.Utils;
import net.servicestack.client.sse.ServerEventConnect;
import net.servicestack.client.sse.ServerEventsClient;
import net.servicestack.func.Func;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static servicestack.net.androidchat.dtos.GetChatHistory;
import static servicestack.net.androidchat.dtos.PostRawToChannel;

/**
 * Created by mythz on 2/15/2017.
 */

public class Extensions {

    public static void updateChatHistory(AndroidServerEventsClient client, ChatCommandHandler cmdReceiver, AsyncSuccessVoid cb){

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
                cb.success();
            });
    }

    public static void changeChannel(AndroidServerEventsClient client, String channel, ChatCommandHandler cmdReceiver, AsyncSuccessVoid cb){
        if (cmdReceiver.getFullHistory().containsKey(channel) && Arrays.asList(client.getChannels()).contains(channel)){
            cmdReceiver.changeChannel(channel);
            cb.success();
        } else {
            client.subscribeToChannelsAsync(new String[]{ channel }, () -> {
                cmdReceiver.setCurrentChannel(channel);
                updateChatHistory(client, cmdReceiver, cb);
            });
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

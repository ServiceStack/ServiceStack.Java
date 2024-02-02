package servicestack.net.androidchat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import net.servicestack.android.AsyncUtils;
import net.servicestack.client.Utils;
import net.servicestack.func.Func;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static servicestack.net.androidchat.dtos.ChatMessage;

/**
 * Created by mythz on 2/15/2017.
 */

public class ChatCommandHandler {
    private MainActivity parentActivity;
    private MessageListViewAdapter messageAdapter;
    private Map<String, List<ChatMessage>> fullHistory;
    private String currentChannel;

    public ChatCommandHandler(MainActivity parentActivity, MessageListViewAdapter messageAdapter, String initialChannel) {
        this.parentActivity = parentActivity;
        this.messageAdapter = messageAdapter;
        this.fullHistory = new HashMap<>();
        this.currentChannel = initialChannel;
    }

    public Map<String,List<ChatMessage>> getFullHistory(){
        return this.fullHistory;
    }

    public void setFullHistory(Map<String, List<ChatMessage>> value){
        this.fullHistory = value;
    }

    public String getCurrentChannel(){
        return currentChannel;
    }

    public void setCurrentChannel(String value){
        this.currentChannel = value;
    }

    public void appendMessage(ChatMessage chatMessage)
    {
        if (!fullHistory.containsKey(chatMessage.getChannel())){
            fullHistory.put(chatMessage.getChannel(), new ArrayList<>());
        }
        fullHistory.get(chatMessage.getChannel()).add(chatMessage);
        if (Objects.equals(chatMessage.getChannel(), this.currentChannel)){
            parentActivity.runOnUiThread(() -> messageAdapter.add(chatMessage));
        }
    }

    public void changeChannel(String channel)
    {
        this.currentChannel = channel;
        messageAdapter.clear();

        if (fullHistory.containsKey(channel)){
            Func.each(fullHistory.get(channel), messageAdapter::add);
        }
    }

    public void syncAdapter(){
        changeChannel(this.currentChannel);
    }

    public void showVideo(String videoUrl){
        parentActivity.startActivity(new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(videoUrl)));
    }

    public void announce(String message)
    {
        Notification.Builder builder = new Notification.Builder(parentActivity)
            .setLocalOnly(true)
            .setAutoCancel(true)
            .setContentTitle("Chat (Xamarin)")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_stat_icon);

        // Build the notification:
        Notification notification = builder.build();

        // Get the notification manager:
        NotificationManager notificationManager = (NotificationManager)
            parentActivity.getSystemService(Context.NOTIFICATION_SERVICE);

        // Publish the notification:
        final int notificationId = 0;
        if (notificationManager != null){
            notificationManager.notify(notificationId, notification);

            Vibrator vibrator = (Vibrator)parentActivity.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
            cancelNotification(notificationManager);
        }
    }

    private void cancelNotification(NotificationManager notificationManager)
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {}
        notificationManager.cancelAll();
    }

    public void changeBackground(String message)
    {
        String url = message.startsWith("url(")
            ? message.substring(4, message.length() - 1)
            : message;

        App.get().readBitmap(url, bitmap -> {
            ImageView chatBackground = (ImageView)parentActivity.findViewById(R.id.chat_background);
            parentActivity.runOnUiThread(() -> chatBackground.setImageBitmap(bitmap));
        });
    }

    public void changeBackgroundColor(String message, String cssSelector){
        // Inject alpha values
        String color = message.replace("#", "#AA");
        ListView chatLayout = (ListView)parentActivity.findViewById(R.id.messageHistory);
        EditText editText = (EditText)parentActivity.findViewById(R.id.message);
        Button sendButton = (Button)parentActivity.findViewById(R.id.sendMessageButton);

        int colorVal = Color.parseColor(color);
        parentActivity.runOnUiThread(() -> {
            if (Objects.equals(cssSelector, "#top")){
                parentActivity.getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(colorVal)
                );
            }

            if (Objects.equals(cssSelector, "#body") || cssSelector == null){
                chatLayout.setBackgroundColor(colorVal);
                ImageView chatBackground = (ImageView)parentActivity.findViewById(R.id.chat_background);
                chatBackground.setImageDrawable(null);
            }

            if (Objects.equals(cssSelector, "#bottom")){
                editText.setBackgroundColor(colorVal);
                sendButton.setBackgroundColor(colorVal);
            }
        });
    }
}

package servicestack.net.androidchat;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.servicestack.android.AsyncUtils;
import net.servicestack.client.Utils;
import net.servicestack.client.sse.ServerEventCommand;
import net.servicestack.client.sse.ServerEventUser;
import net.servicestack.func.FunctionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static servicestack.net.androidchat.dtos.ChatMessage;

/**
 * Created by mythz on 2/15/2017.
 */

public class MessageListViewAdapter extends BaseAdapter {
    private Context context;
    private List<dtos.ChatMessage> items;
    private FunctionResult<List<ServerEventUser>> subscribers;

    public MessageListViewAdapter(Context context, List<ChatMessage> items, FunctionResult<List<ServerEventUser>> subscribers) {
        this.context = context;
        this.items = items;
        this.subscribers = subscribers;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null)
            row = LayoutInflater.from(context).inflate(R.layout.chat_message_item, null, false);
        TextView label = (TextView)row.findViewById(R.id.txtMessage);
        ChatMessage msg = items.get(position);
        String profileUrl = null;
        List<ServerEventUser> subs = new ArrayList<>(subscribers.apply());
        for (ServerEventUser subscriber : subs)
        {
            if (Objects.equals(msg.getFromUserId(), subscriber.getUserId()))
            {
                profileUrl = subscriber.getProfileUrl();
            }
        }
        if (profileUrl == null)
        profileUrl = "https://raw.githubusercontent.com/ServiceStack/Assets/master/img/apps/no-profile64.png";
        String displayMessage = Utils.unescapeHtml(msg.getFromName() + ": " + msg.getMessage() + "\n");
        label.setText(displayMessage);

        ImageView image = (ImageView)row.findViewById(R.id.imgUser);
        App.get().readBitmap(profileUrl, image::setImageBitmap);
        return row;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    public void add(ChatMessage msg){
        items.add(msg);
        this.notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }
}

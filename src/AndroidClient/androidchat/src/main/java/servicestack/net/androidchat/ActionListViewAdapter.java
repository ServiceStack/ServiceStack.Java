package servicestack.net.androidchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mythz on 2/15/2017.
 */

public class ActionListViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> items;

    public ActionListViewAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null)
            row = LayoutInflater.from(context).inflate(R.layout.action_row_item, null, false);
        TextView label = (TextView)row.findViewById(R.id.actionLabel);
        label.setText(items.get(position));
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
}

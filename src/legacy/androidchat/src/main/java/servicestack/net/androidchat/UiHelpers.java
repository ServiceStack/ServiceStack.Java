package servicestack.net.androidchat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import net.servicestack.client.AsyncSuccess;
import net.servicestack.client.Log;

import org.w3c.dom.Text;

import java.util.Objects;

/**
 * Created by mythz on 2/15/2017.
 */

public class UiHelpers {
    public static final String CreateChannelLabel = "      Join";

    public static void showChannelDialog(Activity activity, AsyncSuccess<String> success)
    {
        Builder inputDialog = new AlertDialog.Builder(activity);
        EditText userInput = new EditText(inputDialog.getContext());
        userInput.setHint("Join Channel");
        String selectedInput = "Join Channel";
        userInput.setText("general");
        //SetEditTextStylings(userInput);
        userInput.setInputType(InputType.TYPE_CLASS_TEXT);
        inputDialog.setTitle(selectedInput);
        inputDialog.setView(userInput);
        inputDialog.setPositiveButton("Ok", (see, ess) -> {
            success.success(!Objects.equals(userInput.getText().toString(), "")
                ? userInput.getText().toString()
                : "");

            hideKeyboard(activity, userInput);
        });
        inputDialog.setNegativeButton("Cancel", (afk, kfa) -> hideKeyboard(activity, userInput));
        inputDialog.show();
    }

    private static void hideKeyboard(Context parentActivity, View userInput){
        new Handler(parentActivity.getMainLooper()).post(() -> {
            InputMethodManager imm = (InputMethodManager)parentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(userInput.getWindowToken(), 0);
        });
    }

    public static void resetChannelDrawer(MainActivity parentActivity, NavigationView navigationView, String[] channels){
        parentActivity.runOnUiThread(() -> {
            SubMenu subMenu = navigationView.getMenu().getItem(0).getSubMenu();
            subMenu.clear();
            for (String channel : channels){
                MenuItem chanMenuItem = subMenu.add(R.id.channelsGroup, Menu.NONE, Menu.NONE, channel);
                chanMenuItem.setIcon(R.drawable.ic_discuss);
                chanMenuItem.setCheckable(true);
                chanMenuItem.setEnabled(true);
            }
            MenuItem createChanMenuItem = subMenu.add(R.id.channelsGroup, Menu.NONE, Menu.NONE, CreateChannelLabel);
            createChanMenuItem.setIcon(R.drawable.ic_plus_circle_white_24dp);
            navigationView.refreshDrawableState();
        });
    }

    public static void setStatus(TextView txtStatus, String message){
        txtStatus.setTextColor(Color.BLACK);
        txtStatus.setText(message);
        Log.i(message);
    }

    public static void setStatusError(TextView txtStatus, String message, Exception ex){
        txtStatus.setTextColor(Color.RED);
        txtStatus.setText(message);
        Log.e(message, ex);
    }
}

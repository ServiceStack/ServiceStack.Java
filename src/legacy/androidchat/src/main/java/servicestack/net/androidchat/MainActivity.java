package servicestack.net.androidchat;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import net.servicestack.android.AndroidServerEventsClient;
import net.servicestack.client.Utils;
import net.servicestack.client.sse.ServerEventJoin;
import net.servicestack.client.sse.ServerEventUser;
import net.servicestack.func.Func;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static servicestack.net.androidchat.dtos.PostChatToChannel;
import static servicestack.net.androidchat.dtos.PostRawToChannel;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private ChatActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private ListView rightDrawer;
    private NavigationView navigationView;

    private EditText messageBox;

    private List<Exception> errors = new ArrayList<>();

    private ChatCommandHandler cmdReceiver;

    private List<ServerEventUser> subscriberList = new ArrayList<>();

    private static Map<String, String> commands = Func.toDictionary(
        "Announce Hello",     "/cmd.announce Hello from Java Android",
        "Play YouTube",       "/tv.watch https://youtu.be/u5CVsCnxyXg",
        "Background Image",   "/css.background-image url(http://bit.ly/2lZxbHv)",
        "Background Top",     "/css.background$#top #86B951",
        "Background Color",   "/css.background #eceff1",
        "Background Bottom",  "/css.background$#bottom #91C654",
        "Logout",             "/logout"
    );

    public AndroidServerEventsClient getClient(){
        return App.get().getServerEventsClient();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = (Button)findViewById(R.id.sendMessageButton);
        messageBox = (EditText)findViewById(R.id.message);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        rightDrawer = (ListView)findViewById(R.id.right_drawer);
        ListView messageHistoryList = (ListView)findViewById(R.id.messageHistory);
        ImageView chatBackground = (ImageView)findViewById(R.id.chat_background);
        InitDefaultBackground(chatBackground);

        navigationView.setTag(0);
        rightDrawer.setTag(1);

        MessageListViewAdapter messageHistoryAdapter = new MessageListViewAdapter(
            this, new ArrayList<>(), () -> this.subscriberList);
        messageHistoryList.setAdapter(messageHistoryAdapter);

        MainActivity mainActivity = this;
        cmdReceiver = new ChatCommandHandler(mainActivity, messageHistoryAdapter, "home");

        getClient()
            .setOnConnect(connectMsg -> {
                Extensions.updateChatHistory(getClient(), cmdReceiver, () -> {
                    Extensions.updateUserProfile(connectMsg, mainActivity);
                });
            })
            .setOnCommand(command -> {
                if (command instanceof ServerEventJoin){
                    getClient().getChannelSubscribersAsync(r -> {
                        subscriberList = r;
                        // Refresh profile icons when users join
                        messageHistoryAdapter.notifyDataSetChanged();
                    });
                }
            })
            .setOnException(error -> mainActivity.runOnUiThread(() ->
                Toast.makeText(this, "Error : " + error.getMessage(), Toast.LENGTH_LONG)))
            .setResolver(new MessageResolver(cmdReceiver))
            .registerNamedReceiver("cmd", ChatReceiver.class)
            .registerNamedReceiver("tv", TvReciever.class)
            .registerNamedReceiver("css", CssReceiver.class);

        setSupportActionBar(toolbar);

        List<String> rightDataSet = Func.toList(commands.keySet());
        ActionListViewAdapter rightAdapter = new ActionListViewAdapter(this, rightDataSet);
        rightDrawer.setAdapter(rightAdapter);
        rightDrawer.setOnItemClickListener((sender, view, position, id) -> {
            mainActivity.runOnUiThread(() -> {
                messageBox.setText(commands.get(rightDataSet.get(position)));
                drawerLayout.closeDrawer(rightDrawer);
            });
        });

        drawerToggle = new ChatActionBarDrawerToggle(
            this,              //Host Activity
            drawerLayout,      //DrawerLayout
            toolbar,           //Instance of toolbar, if you use other ctor, the hamburger icon/arrow animation won't work..
            R.string.openDrawer, //Opened Message
            R.string.closeDrawer     //Closed Message
        );

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this::onChannelClick);
        sendButton.setOnClickListener(this::onSendClick);
    }

    private void InitDefaultBackground(final ImageView chatBackground){
        final String url = "https://servicestack.net/img/slide/image01.jpg";
        App.get().readBitmap(url, chatBackground::setImageBitmap);
    }

    public boolean onChannelClick(MenuItem menuItem)
    {
        String itemText = menuItem.getTitle().toString();
        if (Objects.equals(itemText, UiHelpers.CreateChannelLabel)){
            UiHelpers.showChannelDialog(this, nChannel -> {
                try {
                    List<String> nChannels = Func.toList(getClient().getChannels());
                    nChannels.add(nChannel);
                    UiHelpers.resetChannelDrawer(this, navigationView, Func.toArray(nChannels, String.class));
                    Extensions.changeChannel(getClient(), nChannel, cmdReceiver, () -> {
                        cmdReceiver.syncAdapter();
                    });
                } catch (Exception ex){
                    errors.add(ex);
                }
            });
        } else {
            Extensions.changeChannel(getClient(), itemText, cmdReceiver, () -> {});
        }
        drawerLayout.closeDrawer(navigationView);
        return true;
    }

    public void onSendClick(View v)
    {
        boolean hasSelector = messageBox.getText().toString().startsWith("/");
        String selector = hasSelector
            ? Utils.splitOnFirst(messageBox.getText().toString().substring(1), " ")[0]
            : "cmd.chat";
        String message = hasSelector && messageBox.getText().toString().contains(" ")
            ? Utils.splitOnFirst(messageBox.getText().toString().substring(1), " ")[1]
            : messageBox.getText().toString();

        if (Objects.equals(selector, "cmd.chat")){
            App.get().getServiceClient().postAsync(new PostChatToChannel()
                .setChannel(cmdReceiver.getCurrentChannel())
                .setFrom(getClient().getSubscriptionId())
                .setMessage(message)
                .setSelector(selector),
                ignore -> {});
        }
        else if (Objects.equals(selector, "logout")){
            performLogout();
        } else {
            App.get().getServiceClient().postAsync(new PostRawToChannel()
                .setChannel(cmdReceiver.getCurrentChannel())
                .setFrom(getClient().getSubscriptionId())
                .setMessage(message)
                .setSelector(selector),
                () -> {});
        }

        messageBox.setText("");
    }

    private void performLogout() {
        App.get().logout();
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_help:
                if (drawerLayout.isDrawerOpen(rightDrawer)){
                    //Right Drawer is already open, close it
                    drawerLayout.closeDrawer(rightDrawer);
                } else {
                    //Right Drawer is closed, open it and just in case close left drawer
                    drawerLayout.openDrawer(rightDrawer);
                    drawerLayout.closeDrawer(navigationView);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
        UiHelpers.resetChannelDrawer(this, navigationView, getClient().getChannels());
        getClient().start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        getClient().stop();
        cmdReceiver = null;
        super.onDestroy();
    }
}

package servicestack.net.androidchat;

import android.app.Application;
import android.content.Context;

import net.servicestack.android.AndroidServerEventsClient;
import net.servicestack.android.AndroidServiceClient;

/**
 * Created by mythz on 2/19/2017.
 */

public class App extends Application {
    public static final String BaseUrl = "http://chat.servicestack.net/";

    private Context context;
    private AndroidServerEventsClient serverEventsClient;
    private static App instance;

    public App() {}

    public App(Context context, String... channels) {
        this.context = context;
        serverEventsClient = new AndroidServerEventsClient(BaseUrl, channels);
    }

    public void onCreate() {
        super.onCreate();
        instance = new App(getApplicationContext(), "home");
    }

    public static App get() {
        return instance;
    }

    public Context getContext(){
        return context;
    }

    public AndroidServerEventsClient getServerEventsClient(){
        return serverEventsClient;
    }

    public AndroidServiceClient getServiceClient(){
        return serverEventsClient.getAndroidClient();
    }
}

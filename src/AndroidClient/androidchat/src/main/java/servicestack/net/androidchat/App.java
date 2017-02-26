package servicestack.net.androidchat;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.twitter.sdk.android.core.TwitterAuthToken;

import net.servicestack.android.AndroidServerEventsClient;
import net.servicestack.android.AndroidServiceClient;
import net.servicestack.android.AsyncUtils;
import net.servicestack.client.AsyncSuccess;

/**
 * Created by mythz on 2/19/2017.
 */

public class App extends Application {
    public static final String BaseUrl = "http://chat.servicestack.net/";

    private Context context;
    private SharedPreferences prefs;
    private AndroidServerEventsClient serverEventsClient;
    private static App instance;
    private LruCache bitmapCache = new LruCache(4 * 1024 * 1024) {// 4MiB
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };

    public App() {}

    public App(Context context, String... channels) {
        this.context = context;
        this.prefs = context.getSharedPreferences("servicestack.net.androidchat",Context.MODE_PRIVATE);
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

    public dtos.Authenticate getSavedAccessToken(){
        AccessToken facebookAccessToken = AccessToken.getCurrentAccessToken();
        if (facebookAccessToken != null){
            return new dtos.Authenticate()
                .setProvider("facebook")
                .setAccessToken(facebookAccessToken.getToken())
                .setRememberMe(true);
        }

        String twitterAccessToken = prefs.getString("twitter.AccessToken", null);
        String twitterAccessSecret = prefs.getString("twitter.AccessTokenSecret", null);

        if (twitterAccessToken == null || twitterAccessSecret == null)
            return null;

        return new dtos.Authenticate()
            .setProvider("twitter")
            .setAccessToken(twitterAccessToken)
            .setAccessTokenSecret(twitterAccessSecret)
            .setRememberMe(true);
    }

    public void saveTwitterAccessToken(TwitterAuthToken authToken){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("twitter.AccessToken", authToken.token);
        editor.putString("twitter.AccessTokenSecret", authToken.secret);
        editor.apply();
    }

    public void logout(){
        App.get().getServiceClient().clearCookies();    //Logout server

        LoginManager.getInstance().logOut();            //Logout facebook

        SharedPreferences.Editor editor = prefs.edit(); //Logout twitter
        editor.remove("twitter.AccessToken");
        editor.remove("twitter.AccessTokenSecret");
        editor.apply();
    }

    public void readBitmap(final String url, final AsyncSuccess<Bitmap> success){
        Bitmap cachedBitmap = (Bitmap)bitmapCache.get(url);
        if (cachedBitmap != null){
            success.success(cachedBitmap);
            return;
        }

        AsyncUtils.readBitmap(url, imageBitmap -> {
            bitmapCache.put(url, imageBitmap);
            success.success(imageBitmap);
        },
        error -> {
            error.printStackTrace();
        });
    }

}

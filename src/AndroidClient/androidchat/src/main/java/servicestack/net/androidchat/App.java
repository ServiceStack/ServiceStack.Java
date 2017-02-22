package servicestack.net.androidchat;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

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
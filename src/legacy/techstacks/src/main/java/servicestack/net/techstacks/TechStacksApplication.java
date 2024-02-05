package servicestack.net.techstacks;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

/**
 * Created by mythz on 12/8/2015.
 */
public class TechStacksApplication extends Application {
    private static Context ctx;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = getApplicationContext();
    }

    public static Context getContext() {
        return ctx;
    }

    static ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
    public static ActivityManager.MemoryInfo getMemoryInfo(){
        ActivityManager mgr = (ActivityManager) TechStacksApplication.getContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        mgr.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }
}
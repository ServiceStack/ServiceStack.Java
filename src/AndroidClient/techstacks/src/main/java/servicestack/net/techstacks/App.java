package servicestack.net.techstacks;

import net.servicestack.android.AndroidServiceClient;
import net.servicestack.client.AsyncResult;

import servicestack.net.techstacks.techstacksdtos.*;

import java.util.ArrayList;

public class App {

    public static App Instance = new App();

    AndroidServiceClient client;
    AppData appData;

    public static App get(){
        return Instance;
    }

    public static AppData getData(){
        return get().appData;
    }

    public App() {
        client = new AndroidServiceClient("http://techstacks.io");
        appData = new AppData(client);
    }

    public static class AppData
    {
        AndroidServiceClient client;

        ArrayList<AppDataListener> listeners = new ArrayList<>();
        AppOverviewResponse appOverviewResponse;

        public AppData addListener(AppDataListener callback){
            if (!listeners.contains(callback)){
                listeners.add(callback);
            }
            return this;
        }

        public AppData(AndroidServiceClient client){
            this.client = client;
        }

        public void onUpdate(DataType dataType){
            for (AppDataListener listener : listeners){
                listener.onUpdate(this, dataType);
            }
        }

        public AppData loadAppOverview(){
            if (appOverviewResponse != null){
                onUpdate(DataType.AppOverview);
            }
            client.getAsync(new AppOverview(), new AsyncResult<AppOverviewResponse>() {
                @Override
                public void success(AppOverviewResponse response){
                    appOverviewResponse = response;
                    onUpdate(DataType.AppOverview);
                }
            });
            return this;
        }

        public AppOverviewResponse getAppOverviewResponse(){
            return appOverviewResponse;
        }
    }

    public static interface AppDataListener
    {
        public void onUpdate(AppData data, DataType dataType);
    }

    public static enum DataType
    {
        AppOverview,
    }


}

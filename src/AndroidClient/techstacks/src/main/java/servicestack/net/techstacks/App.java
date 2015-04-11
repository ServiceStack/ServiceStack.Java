package servicestack.net.techstacks;

import android.graphics.Bitmap;

import net.servicestack.android.AndroidServiceClient;
import net.servicestack.android.AndroidUtils;
import net.servicestack.client.AsyncResult;
import net.servicestack.client.Utils;

import servicestack.net.techstacks.dto.*;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

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

        AppOverviewResponse appOverviewResponse;
        public AppOverviewResponse getAppOverviewResponse(){
            return appOverviewResponse;
        }

        String lastTechStacksQuery = null;

        public AppData searchTechStacks(final String query){
            if (techStacksResponse != null && Utils.equals(query,lastTechStacksQuery)){
                onUpdate(DataType.SearchTechStacks);
            }

            lastTechStacksQuery = query;
            client.getAsync(new FindTechStacks(),
                    Utils.createMap("NameContains",query,"DescriptionContains",query),
                    new AsyncResult<QueryResponse<TechnologyStack>>() {
                        @Override
                        public void success(QueryResponse<TechnologyStack> response) {
                            if (Utils.equals(query,lastTechStacksQuery)){
                                techStacksResponse = response;
                                onUpdate(DataType.SearchTechStacks);
                            }
                        }
                    });

            return this;
        }

        QueryResponse<TechnologyStack> techStacksResponse;
        public QueryResponse<TechnologyStack> getSearchTechStacksResponse() {
            return techStacksResponse;
        }

        String lastTechnologiesQuery = null;

        public AppData searchTechnologies(final String query){
            if (technologiesResponse != null && Utils.equals(query,lastTechnologiesQuery)){
                onUpdate(DataType.SearchTechnologies);
            }

            lastTechnologiesQuery = query;
            client.getAsync(new FindTechnologies(),
                    Utils.createMap("NameContains",query,"DescriptionContains",query),
                    new AsyncResult<QueryResponse<Technology>>() {
                        @Override
                        public void success(QueryResponse<Technology> response) {
                            if (Utils.equals(query,lastTechnologiesQuery)){
                                technologiesResponse = response;
                                onUpdate(DataType.SearchTechnologies);
                            }
                        }
                    });

            return this;
        }

        QueryResponse<Technology> technologiesResponse;
        public QueryResponse<Technology> getSearchTechnologiesResponse() {
            return technologiesResponse;
        }

        GetTechnologyResponse technology = null;
        public GetTechnologyResponse getTechnology() {
            return technology;
        }

        public void loadTechnology(String slug) {
            if (technology != null){
                onUpdate(DataType.Technology);
            }

            client.getAsync(new GetTechnology().setSlug(slug),
                new AsyncResult<GetTechnologyResponse>() {
                    @Override
                    public void success(GetTechnologyResponse response) {
                        technology = response;
                        onUpdate(DataType.Technology);
                    }
                });
        }

        HashMap<String,Bitmap> imgCache = new HashMap<>();
        public void loadImage(final String imgUrl, final ImageResult callback) {
            Bitmap img = imgCache.get(imgUrl);
            if (img != null){
                callback.success(img);
                return;
            }

            client.getAsync(imgUrl, new AsyncResult<byte[]>() {
                @Override
                public void success(byte[] imgBytes) {
                    Bitmap img = AndroidUtils.readBitmap(imgBytes);
                    imgCache.put(imgUrl, img);
                    callback.success(img);
                }
            });
        }
    }

    public static interface AppDataListener
    {
        public void onUpdate(AppData data, DataType dataType);
    }

    public static class ImageResult
    {
        public void success(Bitmap img){}
    }

    public static enum DataType
    {
        AppOverview,
        SearchTechStacks,
        SearchTechnologies,
        Technology,
        TechStack,
    }


}

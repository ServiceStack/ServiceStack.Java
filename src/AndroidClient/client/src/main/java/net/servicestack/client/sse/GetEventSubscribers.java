package net.servicestack.client.sse;

import com.google.gson.reflect.TypeToken;

import net.servicestack.client.IGet;
import net.servicestack.client.IReturn;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mythz on 2/12/2017.
 */

public class GetEventSubscribers implements IReturn<ArrayList<HashMap<String,String>>>, IGet
{
    public ArrayList<String> Channels = null;

    public ArrayList<String> getChannels() { return Channels; }
    public GetEventSubscribers setChannels(ArrayList<String> value) { this.Channels = value; return this; }
    private static Object responseType = new TypeToken<ArrayList<HashMap<String,String>>>(){}.getType();
    public Object getResponseType() { return responseType; }
}

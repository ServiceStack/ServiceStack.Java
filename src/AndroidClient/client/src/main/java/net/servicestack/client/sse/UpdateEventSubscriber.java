package net.servicestack.client.sse;

import net.servicestack.client.DataMember;
import net.servicestack.client.IPost;
import net.servicestack.client.IReturn;
import net.servicestack.client.Route;

import java.util.ArrayList;

/**
 * Created by mythz on 2/12/2017.
 */

@Route(Path="/event-subscribers/{Id}", Verbs="POST")
public class UpdateEventSubscriber implements IReturn<UpdateEventSubscriberResponse>, IPost
{
    @DataMember(Order=1)
    public String Id = null;

    @DataMember(Order=2)
    public ArrayList<String> SubscribeChannels = null;

    @DataMember(Order=3)
    public ArrayList<String> UnsubscribeChannels = null;

    public String getId() { return Id; }
    public UpdateEventSubscriber setId(String value) { this.Id = value; return this; }
    public ArrayList<String> getSubscribeChannels() { return SubscribeChannels; }
    public UpdateEventSubscriber setSubscribeChannels(ArrayList<String> value) { this.SubscribeChannels = value; return this; }
    public ArrayList<String> getUnsubscribeChannels() { return UnsubscribeChannels; }
    public UpdateEventSubscriber setUnsubscribeChannels(ArrayList<String> value) { this.UnsubscribeChannels = value; return this; }
    private static Object responseType = UpdateEventSubscriberResponse.class;
    public Object getResponseType() { return responseType; }
}

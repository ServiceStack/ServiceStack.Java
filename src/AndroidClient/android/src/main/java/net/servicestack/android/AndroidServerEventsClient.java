package net.servicestack.android;

import net.servicestack.client.AsyncError;
import net.servicestack.client.AsyncResult;
import net.servicestack.client.AsyncResultVoid;
import net.servicestack.client.AsyncSuccess;
import net.servicestack.client.AsyncSuccessVoid;
import net.servicestack.client.sse.GetEventSubscribers;
import net.servicestack.client.sse.ServerEventUser;
import net.servicestack.client.sse.ServerEventsClient;
import net.servicestack.client.sse.UpdateEventSubscriber;
import net.servicestack.client.sse.UpdateEventSubscriberResponse;
import net.servicestack.func.Func;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mythz on 2/15/2017.
 */

public class AndroidServerEventsClient extends ServerEventsClient {
    public AndroidServerEventsClient(String baseUri, String... channels) {
        super(baseUri, channels);
        this.serviceClient = new AndroidServiceClient(this.baseUri);
    }

    public AndroidServerEventsClient(String baseUrl, String channel) {
        this(baseUrl, new String[]{ channel });
    }

    public AndroidServerEventsClient(String baseUrl) {
        this(baseUrl, new String[]{});
    }

    public AndroidServiceClient getAndroidClient(){
        return (AndroidServiceClient) this.serviceClient;
    }

    public void getChannelSubscribersAsync(final AsyncResult<List<ServerEventUser>> asyncResult){
        this.getAndroidClient().getAsync(
            new GetEventSubscribers().setChannels(Func.toList(this.getChannels())),
            new AsyncResult<ArrayList<HashMap<String,String>>>(){
                @Override
                public void success(ArrayList<HashMap<String,String>> response) {
                    asyncResult.success(toServerEventUser(response));
                }
                @Override
                public void error(Exception ex) {
                    asyncResult.error(ex);
                }
                @Override
                public void complete() {
                    asyncResult.complete();
                }
            }
        );
    }

    public void getChannelSubscribersAsync(AsyncSuccess<List<ServerEventUser>> success){
        getChannelSubscribersAsync(AsyncUtils.createAsyncResult(success, null));
    }

    public void getChannelSubscribersAsync(AsyncSuccess<List<ServerEventUser>> success, AsyncError error){
        getChannelSubscribersAsync(AsyncUtils.createAsyncResult(success, error));
    }

    public void updateSubscriberAsync(final UpdateEventSubscriber request, final AsyncResultVoid asyncResult){
        if (request.getId() == null)
            request.setId(connectionInfo.getId());

        getAndroidClient().postAsync(request, new AsyncResult<UpdateEventSubscriberResponse>() {
            @Override
            public void success(UpdateEventSubscriberResponse response) {
                asyncResult.success();
                update(
                    Func.toArray(request.getSubscribeChannels(), String.class),
                    Func.toArray(request.getUnsubscribeChannels(), String.class));
            }
            @Override
            public void error(Exception ex) {
                asyncResult.error(ex);
            }
            @Override
            public void complete() {
                asyncResult.complete();
            }
        });
    }

    public void updateSubscriberAsync(final UpdateEventSubscriber request, final AsyncSuccessVoid success){
        updateSubscriberAsync(request, AsyncUtils.createAsyncResult(success, null));
    }

    public void updateSubscriberAsync(final UpdateEventSubscriber request, final AsyncSuccessVoid success, AsyncError error){
        updateSubscriberAsync(request, AsyncUtils.createAsyncResult(success, error));
    }

    public void subscribeToChannelsAsync(final String[] channels, final AsyncResultVoid asyncResult)
    {
        getAndroidClient().postAsync(new UpdateEventSubscriber()
            .setId(connectionInfo.getId())
            .setSubscribeChannels(Func.toList(channels)),
            new AsyncResult<UpdateEventSubscriberResponse>() {
                @Override
                public void success(UpdateEventSubscriberResponse response) {
                    asyncResult.success();
                    update(channels, null);
                }
                @Override
                public void error(Exception ex) {
                    asyncResult.error(ex);
                }
                @Override
                public void complete() {
                    asyncResult.complete();
                }
            });
    }

    public void subscribeToChannelsAsync(final String[] channels, final AsyncSuccess success){
        subscribeToChannelsAsync(channels, AsyncUtils.createAsyncResult(success, null));
    }

    public void subscribeToChannelsAsync(final String[] channels, final AsyncSuccess success, final AsyncError error){
        subscribeToChannelsAsync(channels, AsyncUtils.createAsyncResult(success, error));
    }

    public void unSubscribeFromChannelsAsync(final String[] channels, final AsyncResultVoid asyncResult)
    {
        getAndroidClient().postAsync(new UpdateEventSubscriber()
            .setId(connectionInfo.getId())
            .setUnsubscribeChannels(Func.toList(channels)),
            new AsyncResult<UpdateEventSubscriberResponse>() {
                @Override
                public void success(UpdateEventSubscriberResponse response) {
                    asyncResult.success();
                    update(null, channels);
                }
                @Override
                public void error(Exception ex) {
                    asyncResult.error(ex);
                }
                @Override
                public void complete() {
                    asyncResult.complete();
                }
            });
    }

    public void unSubscribeFromChannelsAsync(final String[] channels, final AsyncSuccess success){
        unSubscribeFromChannelsAsync(channels, AsyncUtils.createAsyncResult(success, null));
    }

    public void unSubscribeFromChannelsAsync(final String[] channels, final AsyncSuccess success, final AsyncError error){
        unSubscribeFromChannelsAsync(channels, AsyncUtils.createAsyncResult(success, error));
    }
}

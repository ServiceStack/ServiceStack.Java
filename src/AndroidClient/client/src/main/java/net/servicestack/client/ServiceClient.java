//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client;

public interface ServiceClient {
    public <TResponse> TResponse get(IReturn<TResponse> request);

    public <TResponse> TResponse post(IReturn<TResponse> request);

    public <TResponse> TResponse put(IReturn<TResponse> request);

    public <TResponse> TResponse delete(IReturn<TResponse> request);
}

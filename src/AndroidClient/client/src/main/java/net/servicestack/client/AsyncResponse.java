package net.servicestack.client;

public abstract class AsyncResponse<T> {
    public void success(T response){}
    public void error(Exception ex){}
    public void complete(){}
}

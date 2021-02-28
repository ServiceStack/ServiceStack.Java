package net.servicestack.client;

import java.util.HashMap;

@DataContract
public class GetAccessTokenResponse
{
    @DataMember(Order=1)
    public String accessToken = null;

    @DataMember(Order=2)
    public HashMap<String,String> meta = null;

    @DataMember(Order=3)
    public ResponseStatus responseStatus = null;

    public String getAccessToken() { return accessToken; }
    public GetAccessTokenResponse setAccessToken(String value) { this.accessToken = value; return this; }
    public HashMap<String,String> getMeta() { return meta; }
    public GetAccessTokenResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    public ResponseStatus getResponseStatus() { return responseStatus; }
    public GetAccessTokenResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
}
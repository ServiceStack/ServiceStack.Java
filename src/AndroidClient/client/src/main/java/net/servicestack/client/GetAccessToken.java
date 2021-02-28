package net.servicestack.client;

import java.util.HashMap;

@Route("/access-token")
@DataContract
public class GetAccessToken implements IReturn<GetAccessTokenResponse>, IPost
{
    @DataMember(Order=1)
    public String refreshToken = null;

    @DataMember(Order=2)
    public Boolean useTokenCookie = null;

    @DataMember(Order=3)
    public HashMap<String,String> meta = null;

    public String getRefreshToken() { return refreshToken; }
    public GetAccessToken setRefreshToken(String value) { this.refreshToken = value; return this; }
    public Boolean isUseTokenCookie() { return useTokenCookie; }
    public GetAccessToken setUseTokenCookie(Boolean value) { this.useTokenCookie = value; return this; }
    public HashMap<String,String> getMeta() { return meta; }
    public GetAccessToken setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    private static Object responseType = GetAccessTokenResponse.class;
    public Object getResponseType() { return responseType; }
}

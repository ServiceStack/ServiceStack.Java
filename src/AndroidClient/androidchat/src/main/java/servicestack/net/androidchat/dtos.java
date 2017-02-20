/* Options:
Date: 2017-02-19 23:57:28
Version: 4.00
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: http://chat.servicestack.net

Package: servicestack.net.androidchat
GlobalNamespace: dtos
//AddPropertyAccessors: True
//SettersReturnThis: True
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddDescriptionAsComments: True
//AddImplicitVersion: 
//IncludeTypes: 
//ExcludeTypes: 
//TreatTypesAsStrings: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*,com.google.gson.reflect.*
*/

package servicestack.net.androidchat;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;
import com.google.gson.annotations.*;
import com.google.gson.reflect.*;

public class dtos
{

    @Route("/channels/{Channel}/raw")
    public static class PostRawToChannel implements IReturnVoid
    {
        public String from = null;
        public String toUserId = null;
        public String channel = null;
        public String message = null;
        public String selector = null;
        
        public String getFrom() { return from; }
        public PostRawToChannel setFrom(String value) { this.from = value; return this; }
        public String getToUserId() { return toUserId; }
        public PostRawToChannel setToUserId(String value) { this.toUserId = value; return this; }
        public String getChannel() { return channel; }
        public PostRawToChannel setChannel(String value) { this.channel = value; return this; }
        public String getMessage() { return message; }
        public PostRawToChannel setMessage(String value) { this.message = value; return this; }
        public String getSelector() { return selector; }
        public PostRawToChannel setSelector(String value) { this.selector = value; return this; }
    }

    @Route("/channels/{Channel}/chat")
    public static class PostChatToChannel implements IReturn<ChatMessage>
    {
        public String from = null;
        public String toUserId = null;
        public String channel = null;
        public String message = null;
        public String selector = null;
        
        public String getFrom() { return from; }
        public PostChatToChannel setFrom(String value) { this.from = value; return this; }
        public String getToUserId() { return toUserId; }
        public PostChatToChannel setToUserId(String value) { this.toUserId = value; return this; }
        public String getChannel() { return channel; }
        public PostChatToChannel setChannel(String value) { this.channel = value; return this; }
        public String getMessage() { return message; }
        public PostChatToChannel setMessage(String value) { this.message = value; return this; }
        public String getSelector() { return selector; }
        public PostChatToChannel setSelector(String value) { this.selector = value; return this; }
        private static Object responseType = ChatMessage.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/chathistory")
    public static class GetChatHistory implements IReturn<GetChatHistoryResponse>
    {
        public ArrayList<String> channels = null;
        public Long afterId = null;
        public Integer take = null;
        
        public ArrayList<String> getChannels() { return channels; }
        public GetChatHistory setChannels(ArrayList<String> value) { this.channels = value; return this; }
        public Long getAfterId() { return afterId; }
        public GetChatHistory setAfterId(Long value) { this.afterId = value; return this; }
        public Integer getTake() { return take; }
        public GetChatHistory setTake(Integer value) { this.take = value; return this; }
        private static Object responseType = GetChatHistoryResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/reset")
    public static class ClearChatHistory implements IReturnVoid
    {
        
    }

    @Route("/reset-serverevents")
    public static class ResetServerEvents implements IReturnVoid
    {
        
    }

    @Route("/channels/{Channel}/object")
    public static class PostObjectToChannel implements IReturnVoid
    {
        public String toUserId = null;
        public String channel = null;
        public String selector = null;
        public CustomType customType = null;
        public SetterType setterType = null;
        
        public String getToUserId() { return toUserId; }
        public PostObjectToChannel setToUserId(String value) { this.toUserId = value; return this; }
        public String getChannel() { return channel; }
        public PostObjectToChannel setChannel(String value) { this.channel = value; return this; }
        public String getSelector() { return selector; }
        public PostObjectToChannel setSelector(String value) { this.selector = value; return this; }
        public CustomType getCustomType() { return customType; }
        public PostObjectToChannel setCustomType(CustomType value) { this.customType = value; return this; }
        public SetterType getSetterType() { return setterType; }
        public PostObjectToChannel setSetterType(SetterType value) { this.setterType = value; return this; }
    }

    @Route("/account")
    public static class GetUserDetails implements IReturn<GetUserDetailsResponse>
    {
        
        private static Object responseType = GetUserDetailsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/auth")
    // @Route("/auth/{provider}")
    // @Route("/authenticate")
    // @Route("/authenticate/{provider}")
    @DataContract
    public static class Authenticate implements IReturn<AuthenticateResponse>, IPost
    {
        @DataMember(Order=1)
        public String provider = null;

        @DataMember(Order=2)
        public String state = null;

        @DataMember(Order=3)
        public String oauth_token = null;

        @DataMember(Order=4)
        public String oauth_verifier = null;

        @DataMember(Order=5)
        public String userName = null;

        @DataMember(Order=6)
        public String password = null;

        @DataMember(Order=7)
        public Boolean rememberMe = null;

        @DataMember(Order=8)
        @SerializedName("continue") public String Continue = null;

        @DataMember(Order=9)
        public String nonce = null;

        @DataMember(Order=10)
        public String uri = null;

        @DataMember(Order=11)
        public String response = null;

        @DataMember(Order=12)
        public String qop = null;

        @DataMember(Order=13)
        public String nc = null;

        @DataMember(Order=14)
        public String cnonce = null;

        @DataMember(Order=15)
        public Boolean useTokenCookie = null;

        @DataMember(Order=16)
        public String accessToken = null;

        @DataMember(Order=17)
        public String accessTokenSecret = null;

        @DataMember(Order=18)
        public HashMap<String,String> meta = null;
        
        public String getProvider() { return provider; }
        public Authenticate setProvider(String value) { this.provider = value; return this; }
        public String getState() { return state; }
        public Authenticate setState(String value) { this.state = value; return this; }
        public String getOauthToken() { return oauth_token; }
        public Authenticate setOauthToken(String value) { this.oauth_token = value; return this; }
        public String getOauthVerifier() { return oauth_verifier; }
        public Authenticate setOauthVerifier(String value) { this.oauth_verifier = value; return this; }
        public String getUserName() { return userName; }
        public Authenticate setUserName(String value) { this.userName = value; return this; }
        public String getPassword() { return password; }
        public Authenticate setPassword(String value) { this.password = value; return this; }
        public Boolean isRememberMe() { return rememberMe; }
        public Authenticate setRememberMe(Boolean value) { this.rememberMe = value; return this; }
        public String getContinue() { return Continue; }
        public Authenticate setContinue(String value) { this.Continue = value; return this; }
        public String getNonce() { return nonce; }
        public Authenticate setNonce(String value) { this.nonce = value; return this; }
        public String getUri() { return uri; }
        public Authenticate setUri(String value) { this.uri = value; return this; }
        public String getResponse() { return response; }
        public Authenticate setResponse(String value) { this.response = value; return this; }
        public String getQop() { return qop; }
        public Authenticate setQop(String value) { this.qop = value; return this; }
        public String getNc() { return nc; }
        public Authenticate setNc(String value) { this.nc = value; return this; }
        public String getCnonce() { return cnonce; }
        public Authenticate setCnonce(String value) { this.cnonce = value; return this; }
        public Boolean isUseTokenCookie() { return useTokenCookie; }
        public Authenticate setUseTokenCookie(Boolean value) { this.useTokenCookie = value; return this; }
        public String getAccessToken() { return accessToken; }
        public Authenticate setAccessToken(String value) { this.accessToken = value; return this; }
        public String getAccessTokenSecret() { return accessTokenSecret; }
        public Authenticate setAccessTokenSecret(String value) { this.accessTokenSecret = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public Authenticate setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        private static Object responseType = AuthenticateResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/assignroles")
    @DataContract
    public static class AssignRoles implements IReturn<AssignRolesResponse>, IPost
    {
        @DataMember(Order=1)
        public String userName = null;

        @DataMember(Order=2)
        public ArrayList<String> permissions = null;

        @DataMember(Order=3)
        public ArrayList<String> roles = null;
        
        public String getUserName() { return userName; }
        public AssignRoles setUserName(String value) { this.userName = value; return this; }
        public ArrayList<String> getPermissions() { return permissions; }
        public AssignRoles setPermissions(ArrayList<String> value) { this.permissions = value; return this; }
        public ArrayList<String> getRoles() { return roles; }
        public AssignRoles setRoles(ArrayList<String> value) { this.roles = value; return this; }
        private static Object responseType = AssignRolesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/unassignroles")
    @DataContract
    public static class UnAssignRoles implements IReturn<UnAssignRolesResponse>, IPost
    {
        @DataMember(Order=1)
        public String userName = null;

        @DataMember(Order=2)
        public ArrayList<String> permissions = null;

        @DataMember(Order=3)
        public ArrayList<String> roles = null;
        
        public String getUserName() { return userName; }
        public UnAssignRoles setUserName(String value) { this.userName = value; return this; }
        public ArrayList<String> getPermissions() { return permissions; }
        public UnAssignRoles setPermissions(ArrayList<String> value) { this.permissions = value; return this; }
        public ArrayList<String> getRoles() { return roles; }
        public UnAssignRoles setRoles(ArrayList<String> value) { this.roles = value; return this; }
        private static Object responseType = UnAssignRolesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class ChatMessage
    {
        public Long id = null;
        public String channel = null;
        public String fromUserId = null;
        public String fromName = null;
        public String displayName = null;
        public String message = null;
        public String userAuthId = null;
        @SerializedName("private") public Boolean Private = null;
        
        public Long getId() { return id; }
        public ChatMessage setId(Long value) { this.id = value; return this; }
        public String getChannel() { return channel; }
        public ChatMessage setChannel(String value) { this.channel = value; return this; }
        public String getFromUserId() { return fromUserId; }
        public ChatMessage setFromUserId(String value) { this.fromUserId = value; return this; }
        public String getFromName() { return fromName; }
        public ChatMessage setFromName(String value) { this.fromName = value; return this; }
        public String getDisplayName() { return displayName; }
        public ChatMessage setDisplayName(String value) { this.displayName = value; return this; }
        public String getMessage() { return message; }
        public ChatMessage setMessage(String value) { this.message = value; return this; }
        public String getUserAuthId() { return userAuthId; }
        public ChatMessage setUserAuthId(String value) { this.userAuthId = value; return this; }
        public Boolean isPrivate() { return Private; }
        public ChatMessage setPrivate(Boolean value) { this.Private = value; return this; }
    }

    public static class GetChatHistoryResponse
    {
        public ArrayList<ChatMessage> results = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<ChatMessage> getResults() { return results; }
        public GetChatHistoryResponse setResults(ArrayList<ChatMessage> value) { this.results = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetChatHistoryResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetUserDetailsResponse
    {
        public String provider = null;
        public String userId = null;
        public String userName = null;
        public String fullName = null;
        public String displayName = null;
        public String firstName = null;
        public String lastName = null;
        public String company = null;
        public String email = null;
        public String phoneNumber = null;
        public Date birthDate = null;
        public String birthDateRaw = null;
        public String address = null;
        public String address2 = null;
        public String city = null;
        public String state = null;
        public String country = null;
        public String culture = null;
        public String gender = null;
        public String language = null;
        public String mailAddress = null;
        public String nickname = null;
        public String postalCode = null;
        public String timeZone = null;
        
        public String getProvider() { return provider; }
        public GetUserDetailsResponse setProvider(String value) { this.provider = value; return this; }
        public String getUserId() { return userId; }
        public GetUserDetailsResponse setUserId(String value) { this.userId = value; return this; }
        public String getUserName() { return userName; }
        public GetUserDetailsResponse setUserName(String value) { this.userName = value; return this; }
        public String getFullName() { return fullName; }
        public GetUserDetailsResponse setFullName(String value) { this.fullName = value; return this; }
        public String getDisplayName() { return displayName; }
        public GetUserDetailsResponse setDisplayName(String value) { this.displayName = value; return this; }
        public String getFirstName() { return firstName; }
        public GetUserDetailsResponse setFirstName(String value) { this.firstName = value; return this; }
        public String getLastName() { return lastName; }
        public GetUserDetailsResponse setLastName(String value) { this.lastName = value; return this; }
        public String getCompany() { return company; }
        public GetUserDetailsResponse setCompany(String value) { this.company = value; return this; }
        public String getEmail() { return email; }
        public GetUserDetailsResponse setEmail(String value) { this.email = value; return this; }
        public String getPhoneNumber() { return phoneNumber; }
        public GetUserDetailsResponse setPhoneNumber(String value) { this.phoneNumber = value; return this; }
        public Date getBirthDate() { return birthDate; }
        public GetUserDetailsResponse setBirthDate(Date value) { this.birthDate = value; return this; }
        public String getBirthDateRaw() { return birthDateRaw; }
        public GetUserDetailsResponse setBirthDateRaw(String value) { this.birthDateRaw = value; return this; }
        public String getAddress() { return address; }
        public GetUserDetailsResponse setAddress(String value) { this.address = value; return this; }
        public String getAddress2() { return address2; }
        public GetUserDetailsResponse setAddress2(String value) { this.address2 = value; return this; }
        public String getCity() { return city; }
        public GetUserDetailsResponse setCity(String value) { this.city = value; return this; }
        public String getState() { return state; }
        public GetUserDetailsResponse setState(String value) { this.state = value; return this; }
        public String getCountry() { return country; }
        public GetUserDetailsResponse setCountry(String value) { this.country = value; return this; }
        public String getCulture() { return culture; }
        public GetUserDetailsResponse setCulture(String value) { this.culture = value; return this; }
        public String getGender() { return gender; }
        public GetUserDetailsResponse setGender(String value) { this.gender = value; return this; }
        public String getLanguage() { return language; }
        public GetUserDetailsResponse setLanguage(String value) { this.language = value; return this; }
        public String getMailAddress() { return mailAddress; }
        public GetUserDetailsResponse setMailAddress(String value) { this.mailAddress = value; return this; }
        public String getNickname() { return nickname; }
        public GetUserDetailsResponse setNickname(String value) { this.nickname = value; return this; }
        public String getPostalCode() { return postalCode; }
        public GetUserDetailsResponse setPostalCode(String value) { this.postalCode = value; return this; }
        public String getTimeZone() { return timeZone; }
        public GetUserDetailsResponse setTimeZone(String value) { this.timeZone = value; return this; }
    }

    @DataContract
    public static class AuthenticateResponse
    {
        @DataMember(Order=1)
        public String userId = null;

        @DataMember(Order=2)
        public String sessionId = null;

        @DataMember(Order=3)
        public String userName = null;

        @DataMember(Order=4)
        public String displayName = null;

        @DataMember(Order=5)
        public String referrerUrl = null;

        @DataMember(Order=6)
        public String bearerToken = null;

        @DataMember(Order=7)
        public ResponseStatus responseStatus = null;

        @DataMember(Order=8)
        public HashMap<String,String> meta = null;
        
        public String getUserId() { return userId; }
        public AuthenticateResponse setUserId(String value) { this.userId = value; return this; }
        public String getSessionId() { return sessionId; }
        public AuthenticateResponse setSessionId(String value) { this.sessionId = value; return this; }
        public String getUserName() { return userName; }
        public AuthenticateResponse setUserName(String value) { this.userName = value; return this; }
        public String getDisplayName() { return displayName; }
        public AuthenticateResponse setDisplayName(String value) { this.displayName = value; return this; }
        public String getReferrerUrl() { return referrerUrl; }
        public AuthenticateResponse setReferrerUrl(String value) { this.referrerUrl = value; return this; }
        public String getBearerToken() { return bearerToken; }
        public AuthenticateResponse setBearerToken(String value) { this.bearerToken = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public AuthenticateResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public AuthenticateResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    }

    @DataContract
    public static class AssignRolesResponse
    {
        @DataMember(Order=1)
        public ArrayList<String> allRoles = null;

        @DataMember(Order=2)
        public ArrayList<String> allPermissions = null;

        @DataMember(Order=3)
        public ResponseStatus responseStatus = null;
        
        public ArrayList<String> getAllRoles() { return allRoles; }
        public AssignRolesResponse setAllRoles(ArrayList<String> value) { this.allRoles = value; return this; }
        public ArrayList<String> getAllPermissions() { return allPermissions; }
        public AssignRolesResponse setAllPermissions(ArrayList<String> value) { this.allPermissions = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public AssignRolesResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    @DataContract
    public static class UnAssignRolesResponse
    {
        @DataMember(Order=1)
        public ArrayList<String> allRoles = null;

        @DataMember(Order=2)
        public ArrayList<String> allPermissions = null;

        @DataMember(Order=3)
        public ResponseStatus responseStatus = null;
        
        public ArrayList<String> getAllRoles() { return allRoles; }
        public UnAssignRolesResponse setAllRoles(ArrayList<String> value) { this.allRoles = value; return this; }
        public ArrayList<String> getAllPermissions() { return allPermissions; }
        public UnAssignRolesResponse setAllPermissions(ArrayList<String> value) { this.allPermissions = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UnAssignRolesResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CustomType
    {
        public Integer id = null;
        public String name = null;
        
        public Integer getId() { return id; }
        public CustomType setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public CustomType setName(String value) { this.name = value; return this; }
    }

    public static class SetterType
    {
        public Integer id = null;
        public String name = null;
        
        public Integer getId() { return id; }
        public SetterType setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public SetterType setName(String value) { this.name = value; return this; }
    }

}

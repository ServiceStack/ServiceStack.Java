/* Options:
Date: 2017-02-11 06:41:16
Version: 4.00
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: http://chat.servicestack.net

Package: chat
GlobalNamespace: chatdtos
//AddPropertyAccessors: True
//SettersReturnThis: True
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddDescriptionAsComments: True
//AddImplicitVersion: 
//IncludeTypes: 
ExcludeTypes: Authenticate,AuthenticateResponse,AssignRoles,AssignRolesResponse,UnAssignRoles,UnAssignRolesResponse
//TreatTypesAsStrings: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*,com.google.gson.reflect.*
*/

package chat;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;
import com.google.gson.annotations.*;
import com.google.gson.reflect.*;

public class chatdtos
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

    @Route("/account")
    public static class GetUserDetails implements IReturn<GetUserDetailsResponse>
    {
        
        private static Object responseType = GetUserDetailsResponse.class;
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

}

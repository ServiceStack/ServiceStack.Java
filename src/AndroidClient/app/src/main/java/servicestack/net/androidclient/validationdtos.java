/* Options:
Date: 2020-03-03 06:32:48
Version: 5.81
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: http://validation.web-app.io

Package: servicestack.net.androidclient
GlobalNamespace: validationdtos
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

package servicestack.net.androidclient;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;
import com.google.gson.annotations.*;
import com.google.gson.reflect.*;

public class validationdtos
{

    @Route(Path="/contacts", Verbs="GET")
    public static class GetContacts implements IReturn<GetContactsResponse>
    {
        
        private static Object responseType = GetContactsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/contacts/{Id}", Verbs="GET")
    public static class GetContact implements IReturn<GetContactResponse>
    {
        public Integer id = null;
        
        public Integer getId() { return id; }
        public GetContact setId(Integer value) { this.id = value; return this; }
        private static Object responseType = GetContactResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/contacts", Verbs="POST")
    public static class CreateContact implements IReturn<CreateContactResponse>
    {
        public Title title = null;
        public String name = null;
        public String color = null;
        public ArrayList<FilmGenres> filmGenres = null;
        public Integer age = null;
        public Boolean agree = null;
        @SerializedName("continue") public String Continue = null;
        public String errorView = null;
        
        public Title getTitle() { return title; }
        public CreateContact setTitle(Title value) { this.title = value; return this; }
        public String getName() { return name; }
        public CreateContact setName(String value) { this.name = value; return this; }
        public String getColor() { return color; }
        public CreateContact setColor(String value) { this.color = value; return this; }
        public ArrayList<FilmGenres> getFilmGenres() { return filmGenres; }
        public CreateContact setFilmGenres(ArrayList<FilmGenres> value) { this.filmGenres = value; return this; }
        public Integer getAge() { return age; }
        public CreateContact setAge(Integer value) { this.age = value; return this; }
        public Boolean isAgree() { return agree; }
        public CreateContact setAgree(Boolean value) { this.agree = value; return this; }
        public String getContinue() { return Continue; }
        public CreateContact setContinue(String value) { this.Continue = value; return this; }
        public String getErrorView() { return errorView; }
        public CreateContact setErrorView(String value) { this.errorView = value; return this; }
        private static Object responseType = CreateContactResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/contacts/{Id}", Verbs="DELETE")
    // @Route(Path="/contacts/{Id}/delete", Verbs="POST")
    public static class DeleteContact implements IReturnVoid
    {
        public Integer id = null;
        @SerializedName("continue") public String Continue = null;
        
        public Integer getId() { return id; }
        public DeleteContact setId(Integer value) { this.id = value; return this; }
        public String getContinue() { return Continue; }
        public DeleteContact setContinue(String value) { this.Continue = value; return this; }
    }

    @Route(Path="/contacts/{Id}", Verbs="POST PUT")
    public static class UpdateContact implements IReturn<UpdateContactResponse>
    {
        public Integer id = null;
        public Title title = null;
        public String name = null;
        public String color = null;
        public ArrayList<FilmGenres> filmGenres = null;
        public Integer age = null;
        @SerializedName("continue") public String Continue = null;
        public String errorView = null;
        
        public Integer getId() { return id; }
        public UpdateContact setId(Integer value) { this.id = value; return this; }
        public Title getTitle() { return title; }
        public UpdateContact setTitle(Title value) { this.title = value; return this; }
        public String getName() { return name; }
        public UpdateContact setName(String value) { this.name = value; return this; }
        public String getColor() { return color; }
        public UpdateContact setColor(String value) { this.color = value; return this; }
        public ArrayList<FilmGenres> getFilmGenres() { return filmGenres; }
        public UpdateContact setFilmGenres(ArrayList<FilmGenres> value) { this.filmGenres = value; return this; }
        public Integer getAge() { return age; }
        public UpdateContact setAge(Integer value) { this.age = value; return this; }
        public String getContinue() { return Continue; }
        public UpdateContact setContinue(String value) { this.Continue = value; return this; }
        public String getErrorView() { return errorView; }
        public UpdateContact setErrorView(String value) { this.errorView = value; return this; }
        private static Object responseType = UpdateContactResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/auth")
    // @Route("/auth/{provider}")
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

        @DataMember(Order=9)
        public String errorView = null;

        @DataMember(Order=10)
        public String nonce = null;

        @DataMember(Order=11)
        public String uri = null;

        @DataMember(Order=12)
        public String response = null;

        @DataMember(Order=13)
        public String qop = null;

        @DataMember(Order=14)
        public String nc = null;

        @DataMember(Order=15)
        public String cnonce = null;

        @DataMember(Order=16)
        public Boolean useTokenCookie = null;

        @DataMember(Order=17)
        public String accessToken = null;

        @DataMember(Order=18)
        public String accessTokenSecret = null;

        @DataMember(Order=19)
        public String scope = null;

        @DataMember(Order=20)
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
        public String getErrorView() { return errorView; }
        public Authenticate setErrorView(String value) { this.errorView = value; return this; }
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
        public String getScope() { return scope; }
        public Authenticate setScope(String value) { this.scope = value; return this; }
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

        @DataMember(Order=4)
        public HashMap<String,String> meta = null;
        
        public String getUserName() { return userName; }
        public AssignRoles setUserName(String value) { this.userName = value; return this; }
        public ArrayList<String> getPermissions() { return permissions; }
        public AssignRoles setPermissions(ArrayList<String> value) { this.permissions = value; return this; }
        public ArrayList<String> getRoles() { return roles; }
        public AssignRoles setRoles(ArrayList<String> value) { this.roles = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public AssignRoles setMeta(HashMap<String,String> value) { this.meta = value; return this; }
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

        @DataMember(Order=4)
        public HashMap<String,String> meta = null;
        
        public String getUserName() { return userName; }
        public UnAssignRoles setUserName(String value) { this.userName = value; return this; }
        public ArrayList<String> getPermissions() { return permissions; }
        public UnAssignRoles setPermissions(ArrayList<String> value) { this.permissions = value; return this; }
        public ArrayList<String> getRoles() { return roles; }
        public UnAssignRoles setRoles(ArrayList<String> value) { this.roles = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public UnAssignRoles setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        private static Object responseType = UnAssignRolesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/register")
    @DataContract
    public static class Register implements IReturn<RegisterResponse>, IPost
    {
        @DataMember(Order=1)
        public String userName = null;

        @DataMember(Order=2)
        public String firstName = null;

        @DataMember(Order=3)
        public String lastName = null;

        @DataMember(Order=4)
        public String displayName = null;

        @DataMember(Order=5)
        public String email = null;

        @DataMember(Order=6)
        public String password = null;

        @DataMember(Order=7)
        public String confirmPassword = null;

        @DataMember(Order=8)
        public Boolean autoLogin = null;

        @DataMember(Order=10)
        public String errorView = null;

        @DataMember(Order=11)
        public HashMap<String,String> meta = null;
        
        public String getUserName() { return userName; }
        public Register setUserName(String value) { this.userName = value; return this; }
        public String getFirstName() { return firstName; }
        public Register setFirstName(String value) { this.firstName = value; return this; }
        public String getLastName() { return lastName; }
        public Register setLastName(String value) { this.lastName = value; return this; }
        public String getDisplayName() { return displayName; }
        public Register setDisplayName(String value) { this.displayName = value; return this; }
        public String getEmail() { return email; }
        public Register setEmail(String value) { this.email = value; return this; }
        public String getPassword() { return password; }
        public Register setPassword(String value) { this.password = value; return this; }
        public String getConfirmPassword() { return confirmPassword; }
        public Register setConfirmPassword(String value) { this.confirmPassword = value; return this; }
        public Boolean isAutoLogin() { return autoLogin; }
        public Register setAutoLogin(Boolean value) { this.autoLogin = value; return this; }
        public String getErrorView() { return errorView; }
        public Register setErrorView(String value) { this.errorView = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public Register setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        private static Object responseType = RegisterResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class GetContactsResponse
    {
        public ArrayList<Contact> results = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<Contact> getResults() { return results; }
        public GetContactsResponse setResults(ArrayList<Contact> value) { this.results = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetContactsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetContactResponse
    {
        public ResponseStatus responseStatus = null;
        public Contact result = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetContactResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
        public Contact getResult() { return result; }
        public GetContactResponse setResult(Contact value) { this.result = value; return this; }
    }

    public static class CreateContactResponse
    {
        public ResponseStatus responseStatus = null;
        public Contact result = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public CreateContactResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
        public Contact getResult() { return result; }
        public CreateContactResponse setResult(Contact value) { this.result = value; return this; }
    }

    public static class UpdateContactResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UpdateContactResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    @DataContract
    public static class AuthenticateResponse implements IHasSessionId, IHasBearerToken
    {
        @DataMember(Order=11)
        public ResponseStatus responseStatus = null;

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
        public String refreshToken = null;

        @DataMember(Order=8)
        public String profileUrl = null;

        @DataMember(Order=9)
        public ArrayList<String> roles = null;

        @DataMember(Order=10)
        public ArrayList<String> permissions = null;

        @DataMember(Order=12)
        public HashMap<String,String> meta = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public AuthenticateResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
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
        public String getRefreshToken() { return refreshToken; }
        public AuthenticateResponse setRefreshToken(String value) { this.refreshToken = value; return this; }
        public String getProfileUrl() { return profileUrl; }
        public AuthenticateResponse setProfileUrl(String value) { this.profileUrl = value; return this; }
        public ArrayList<String> getRoles() { return roles; }
        public AuthenticateResponse setRoles(ArrayList<String> value) { this.roles = value; return this; }
        public ArrayList<String> getPermissions() { return permissions; }
        public AuthenticateResponse setPermissions(ArrayList<String> value) { this.permissions = value; return this; }
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
        public HashMap<String,String> meta = null;

        @DataMember(Order=4)
        public ResponseStatus responseStatus = null;
        
        public ArrayList<String> getAllRoles() { return allRoles; }
        public AssignRolesResponse setAllRoles(ArrayList<String> value) { this.allRoles = value; return this; }
        public ArrayList<String> getAllPermissions() { return allPermissions; }
        public AssignRolesResponse setAllPermissions(ArrayList<String> value) { this.allPermissions = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public AssignRolesResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
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
        public HashMap<String,String> meta = null;

        @DataMember(Order=4)
        public ResponseStatus responseStatus = null;
        
        public ArrayList<String> getAllRoles() { return allRoles; }
        public UnAssignRolesResponse setAllRoles(ArrayList<String> value) { this.allRoles = value; return this; }
        public ArrayList<String> getAllPermissions() { return allPermissions; }
        public UnAssignRolesResponse setAllPermissions(ArrayList<String> value) { this.allPermissions = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public UnAssignRolesResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UnAssignRolesResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    @DataContract
    public static class RegisterResponse
    {
        @DataMember(Order=7)
        public ResponseStatus responseStatus = null;

        @DataMember(Order=1)
        public String userId = null;

        @DataMember(Order=2)
        public String sessionId = null;

        @DataMember(Order=3)
        public String userName = null;

        @DataMember(Order=4)
        public String referrerUrl = null;

        @DataMember(Order=5)
        public String bearerToken = null;

        @DataMember(Order=6)
        public String refreshToken = null;

        @DataMember(Order=8)
        public HashMap<String,String> meta = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public RegisterResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
        public String getUserId() { return userId; }
        public RegisterResponse setUserId(String value) { this.userId = value; return this; }
        public String getSessionId() { return sessionId; }
        public RegisterResponse setSessionId(String value) { this.sessionId = value; return this; }
        public String getUserName() { return userName; }
        public RegisterResponse setUserName(String value) { this.userName = value; return this; }
        public String getReferrerUrl() { return referrerUrl; }
        public RegisterResponse setReferrerUrl(String value) { this.referrerUrl = value; return this; }
        public String getBearerToken() { return bearerToken; }
        public RegisterResponse setBearerToken(String value) { this.bearerToken = value; return this; }
        public String getRefreshToken() { return refreshToken; }
        public RegisterResponse setRefreshToken(String value) { this.refreshToken = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public RegisterResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    }

    public static class Contact
    {
        public Integer id = null;
        public Integer userAuthId = null;
        public Title title = null;
        public String name = null;
        public String color = null;
        public ArrayList<FilmGenres> filmGenres = null;
        public Integer age = null;
        
        public Integer getId() { return id; }
        public Contact setId(Integer value) { this.id = value; return this; }
        public Integer getUserAuthId() { return userAuthId; }
        public Contact setUserAuthId(Integer value) { this.userAuthId = value; return this; }
        public Title getTitle() { return title; }
        public Contact setTitle(Title value) { this.title = value; return this; }
        public String getName() { return name; }
        public Contact setName(String value) { this.name = value; return this; }
        public String getColor() { return color; }
        public Contact setColor(String value) { this.color = value; return this; }
        public ArrayList<FilmGenres> getFilmGenres() { return filmGenres; }
        public Contact setFilmGenres(ArrayList<FilmGenres> value) { this.filmGenres = value; return this; }
        public Integer getAge() { return age; }
        public Contact setAge(Integer value) { this.age = value; return this; }
    }

    public static enum Title
    {
        Unspecified,
        Mr,
        Mrs,
        Miss;
    }

    public static enum FilmGenres
    {
        Action,
        Adventure,
        Comedy,
        Drama;
    }

}

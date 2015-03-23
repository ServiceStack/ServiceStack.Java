/* Options:
Date: 2015-03-23 06:59:25
Version: 1
BaseUrl: http://localhost:55799

Package: test
//GlobalNamespace: dto
//AddPropertyAccessors: True
//SettersReturnThis: True
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion: 
//IncludeTypes: 
//ExcludeTypes: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*
*/

package test;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;
import com.google.gson.annotations.*;

public class dto
{

    public static class QueryBase_1<T> extends QueryBase
    {

    }

    public static class Rockstar
    {
        public Integer id = null;
        public String firstName = null;
        public String lastName = null;
        public Integer age = null;

        public Integer getId() { return id; }
        public Rockstar setId(Integer value) { this.id = value; return this; }
        public String getFirstName() { return firstName; }
        public Rockstar setFirstName(String value) { this.firstName = value; return this; }
        public String getLastName() { return lastName; }
        public Rockstar setLastName(String value) { this.lastName = value; return this; }
        public Integer getAge() { return age; }
        public Rockstar setAge(Integer value) { this.age = value; return this; }
    }

    @DataContract
    public static class ResponseStatus
    {
        @DataMember(Order=1)
        public String errorCode = null;

        @DataMember(Order=2)
        public String message = null;

        @DataMember(Order=3)
        public String stackTrace = null;

        @DataMember(Order=4)
        public ArrayList<ResponseError> errors = null;

        public String getErrorCode() { return errorCode; }
        public ResponseStatus setErrorCode(String value) { this.errorCode = value; return this; }
        public String getMessage() { return message; }
        public ResponseStatus setMessage(String value) { this.message = value; return this; }
        public String getStackTrace() { return stackTrace; }
        public ResponseStatus setStackTrace(String value) { this.stackTrace = value; return this; }
        public ArrayList<ResponseError> getErrors() { return errors; }
        public ResponseStatus setErrors(ArrayList<ResponseError> value) { this.errors = value; return this; }
    }

    public static class MetadataTestChild
    {
        public String name = null;
        public ArrayList<MetadataTestNestedChild> results = null;

        public String getName() { return name; }
        public MetadataTestChild setName(String value) { this.name = value; return this; }
        public ArrayList<MetadataTestNestedChild> getResults() { return results; }
        public MetadataTestChild setResults(ArrayList<MetadataTestNestedChild> value) { this.results = value; return this; }
    }

    @DataContract
    public static class MenuExample
    {
        @DataMember(Order=1)
        @ApiMember()
        public MenuItemExample menuItemExample1 = null;

        public MenuItemExample getMenuItemExample1() { return menuItemExample1; }
        public MenuExample setMenuItemExample1(MenuItemExample value) { this.menuItemExample1 = value; return this; }
    }

    public static class MetadataType
    {
        public String name = null;
        public String namespace = null;
        public ArrayList<String> genericArgs = null;
        public MetadataTypeName inherits = null;
        public String displayType = null;
        public String description = null;
        public Boolean returnVoidMarker = null;
        public Boolean isNested = null;
        public Boolean isEnum = null;
        public Boolean isInterface = null;
        public Boolean isAbstract = null;
        public MetadataTypeName returnMarkerTypeName = null;
        public ArrayList<MetadataRoute> routes = null;
        public MetadataDataContract dataContract = null;
        public ArrayList<MetadataPropertyType> properties = null;
        public ArrayList<MetadataAttribute> attributes = null;
        public ArrayList<MetadataTypeName> innerTypes = null;
        public ArrayList<String> enumNames = null;
        public ArrayList<String> enumValues = null;

        public String getName() { return name; }
        public MetadataType setName(String value) { this.name = value; return this; }
        public String getNamespace() { return namespace; }
        public MetadataType setNamespace(String value) { this.namespace = value; return this; }
        public ArrayList<String> getGenericArgs() { return genericArgs; }
        public MetadataType setGenericArgs(ArrayList<String> value) { this.genericArgs = value; return this; }
        public MetadataTypeName getInherits() { return inherits; }
        public MetadataType setInherits(MetadataTypeName value) { this.inherits = value; return this; }
        public String getDisplayType() { return displayType; }
        public MetadataType setDisplayType(String value) { this.displayType = value; return this; }
        public String getDescription() { return description; }
        public MetadataType setDescription(String value) { this.description = value; return this; }
        public Boolean isReturnVoidMarker() { return returnVoidMarker; }
        public MetadataType setReturnVoidMarker(Boolean value) { this.returnVoidMarker = value; return this; }
        public Boolean getIsNested() { return isNested; }
        public MetadataType setIsNested(Boolean value) { this.isNested = value; return this; }
        public Boolean getIsEnum() { return isEnum; }
        public MetadataType setIsEnum(Boolean value) { this.isEnum = value; return this; }
        public Boolean getIsInterface() { return isInterface; }
        public MetadataType setIsInterface(Boolean value) { this.isInterface = value; return this; }
        public Boolean getIsAbstract() { return isAbstract; }
        public MetadataType setIsAbstract(Boolean value) { this.isAbstract = value; return this; }
        public MetadataTypeName getReturnMarkerTypeName() { return returnMarkerTypeName; }
        public MetadataType setReturnMarkerTypeName(MetadataTypeName value) { this.returnMarkerTypeName = value; return this; }
        public ArrayList<MetadataRoute> getRoutes() { return routes; }
        public MetadataType setRoutes(ArrayList<MetadataRoute> value) { this.routes = value; return this; }
        public MetadataDataContract getDataContract() { return dataContract; }
        public MetadataType setDataContract(MetadataDataContract value) { this.dataContract = value; return this; }
        public ArrayList<MetadataPropertyType> getProperties() { return properties; }
        public MetadataType setProperties(ArrayList<MetadataPropertyType> value) { this.properties = value; return this; }
        public ArrayList<MetadataAttribute> getAttributes() { return attributes; }
        public MetadataType setAttributes(ArrayList<MetadataAttribute> value) { this.attributes = value; return this; }
        public ArrayList<MetadataTypeName> getInnerTypes() { return innerTypes; }
        public MetadataType setInnerTypes(ArrayList<MetadataTypeName> value) { this.innerTypes = value; return this; }
        public ArrayList<String> getEnumNames() { return enumNames; }
        public MetadataType setEnumNames(ArrayList<String> value) { this.enumNames = value; return this; }
        public ArrayList<String> getEnumValues() { return enumValues; }
        public MetadataType setEnumValues(ArrayList<String> value) { this.enumValues = value; return this; }
    }

    public static class AutoQueryViewerConfig
    {
        public String serviceBaseUrl = null;
        public String serviceName = null;
        public String serviceDescription = null;
        public String serviceIconUrl = null;
        public Boolean isPublic = null;
        public Boolean onlyShowAnnotatedServices = null;
        public ArrayList<Property> implicitConventions = null;
        public String defaultSearchField = null;
        public String defaultSearchType = null;
        public String defaultSearchText = null;
        public String brandUrl = null;
        public String brandImageUrl = null;
        public String textColor = null;
        public String linkColor = null;
        public String backgroundColor = null;
        public String backgroundImageUrl = null;
        public String iconUrl = null;

        public String getServiceBaseUrl() { return serviceBaseUrl; }
        public AutoQueryViewerConfig setServiceBaseUrl(String value) { this.serviceBaseUrl = value; return this; }
        public String getServiceName() { return serviceName; }
        public AutoQueryViewerConfig setServiceName(String value) { this.serviceName = value; return this; }
        public String getServiceDescription() { return serviceDescription; }
        public AutoQueryViewerConfig setServiceDescription(String value) { this.serviceDescription = value; return this; }
        public String getServiceIconUrl() { return serviceIconUrl; }
        public AutoQueryViewerConfig setServiceIconUrl(String value) { this.serviceIconUrl = value; return this; }
        public Boolean getIsPublic() { return isPublic; }
        public AutoQueryViewerConfig setIsPublic(Boolean value) { this.isPublic = value; return this; }
        public Boolean isOnlyShowAnnotatedServices() { return onlyShowAnnotatedServices; }
        public AutoQueryViewerConfig setOnlyShowAnnotatedServices(Boolean value) { this.onlyShowAnnotatedServices = value; return this; }
        public ArrayList<Property> getImplicitConventions() { return implicitConventions; }
        public AutoQueryViewerConfig setImplicitConventions(ArrayList<Property> value) { this.implicitConventions = value; return this; }
        public String getDefaultSearchField() { return defaultSearchField; }
        public AutoQueryViewerConfig setDefaultSearchField(String value) { this.defaultSearchField = value; return this; }
        public String getDefaultSearchType() { return defaultSearchType; }
        public AutoQueryViewerConfig setDefaultSearchType(String value) { this.defaultSearchType = value; return this; }
        public String getDefaultSearchText() { return defaultSearchText; }
        public AutoQueryViewerConfig setDefaultSearchText(String value) { this.defaultSearchText = value; return this; }
        public String getBrandUrl() { return brandUrl; }
        public AutoQueryViewerConfig setBrandUrl(String value) { this.brandUrl = value; return this; }
        public String getBrandImageUrl() { return brandImageUrl; }
        public AutoQueryViewerConfig setBrandImageUrl(String value) { this.brandImageUrl = value; return this; }
        public String getTextColor() { return textColor; }
        public AutoQueryViewerConfig setTextColor(String value) { this.textColor = value; return this; }
        public String getLinkColor() { return linkColor; }
        public AutoQueryViewerConfig setLinkColor(String value) { this.linkColor = value; return this; }
        public String getBackgroundColor() { return backgroundColor; }
        public AutoQueryViewerConfig setBackgroundColor(String value) { this.backgroundColor = value; return this; }
        public String getBackgroundImageUrl() { return backgroundImageUrl; }
        public AutoQueryViewerConfig setBackgroundImageUrl(String value) { this.backgroundImageUrl = value; return this; }
        public String getIconUrl() { return iconUrl; }
        public AutoQueryViewerConfig setIconUrl(String value) { this.iconUrl = value; return this; }
    }

    public static class AutoQueryOperation
    {
        public String request = null;
        public String from = null;
        public String to = null;

        public String getRequest() { return request; }
        public AutoQueryOperation setRequest(String value) { this.request = value; return this; }
        public String getFrom() { return from; }
        public AutoQueryOperation setFrom(String value) { this.from = value; return this; }
        public String getTo() { return to; }
        public AutoQueryOperation setTo(String value) { this.to = value; return this; }
    }

    public static class Issue221Base<T>
    {
        public T id = null;

        public T getId() { return id; }
        public Issue221Base<T> setId(T value) { this.id = value; return this; }
    }

    public static class NativeTypesTestService
    {

    }

    public static class NestedClass
    {
        public String value = null;

        public String getValue() { return value; }
        public NestedClass setValue(String value) { this.value = value; return this; }
    }

    public static class ListResult
    {
        public String result = null;

        public String getResult() { return result; }
        public ListResult setResult(String value) { this.result = value; return this; }
    }

    public static class ArrayResult
    {
        public String result = null;

        public String getResult() { return result; }
        public ArrayResult setResult(String value) { this.result = value; return this; }
    }

    public static enum EnumType
    {
        Value1,
        Value2;
    }

    @Flags()
    public static enum EnumFlags
    {
        Value1(1),
        Value2(2),
        Value3(4);

        private final int value;
        EnumFlags(final int intValue) { value = intValue; }
        public int getValue() { return value; }
    }

    public static class AllTypes
    {
        public Integer id = null;
        public Integer nullableId = null;
        @SerializedName("byte") public Short Byte = null;
        @SerializedName("short") public Short Short = null;
        @SerializedName("int") public Integer Int = null;
        @SerializedName("long") public Long Long = null;
        public Integer uShort = null;
        public Long uInt = null;
        public BigInteger uLong = null;
        @SerializedName("float") public Float Float = null;
        @SerializedName("double") public Double Double = null;
        public BigDecimal decimal = null;
        public String string = null;
        public Date dateTime = null;
        public TimeSpan timeSpan = null;
        public Date dateTimeOffset = null;
        public UUID guid = null;
        @SerializedName("char") public String Char = null;
        public Date nullableDateTime = null;
        public TimeSpan nullableTimeSpan = null;
        public ArrayList<String> stringList = null;
        public ArrayList<String> stringArray = null;
        public HashMap<String,String> stringMap = null;
        public HashMap<Integer,String> intStringMap = null;
        public SubType subType = null;

        public Integer getId() { return id; }
        public AllTypes setId(Integer value) { this.id = value; return this; }
        public Integer getNullableId() { return nullableId; }
        public AllTypes setNullableId(Integer value) { this.nullableId = value; return this; }
        public Short getByte() { return Byte; }
        public AllTypes setByte(Short value) { this.Byte = value; return this; }
        public Short getShort() { return Short; }
        public AllTypes setShort(Short value) { this.Short = value; return this; }
        public Integer getInt() { return Int; }
        public AllTypes setInt(Integer value) { this.Int = value; return this; }
        public Long getLong() { return Long; }
        public AllTypes setLong(Long value) { this.Long = value; return this; }
        public Integer getUShort() { return uShort; }
        public AllTypes setUShort(Integer value) { this.uShort = value; return this; }
        public Long getUInt() { return uInt; }
        public AllTypes setUInt(Long value) { this.uInt = value; return this; }
        public BigInteger getULong() { return uLong; }
        public AllTypes setULong(BigInteger value) { this.uLong = value; return this; }
        public Float getFloat() { return Float; }
        public AllTypes setFloat(Float value) { this.Float = value; return this; }
        public Double getDouble() { return Double; }
        public AllTypes setDouble(Double value) { this.Double = value; return this; }
        public BigDecimal getDecimal() { return decimal; }
        public AllTypes setDecimal(BigDecimal value) { this.decimal = value; return this; }
        public String getString() { return string; }
        public AllTypes setString(String value) { this.string = value; return this; }
        public Date getDateTime() { return dateTime; }
        public AllTypes setDateTime(Date value) { this.dateTime = value; return this; }
        public TimeSpan getTimeSpan() { return timeSpan; }
        public AllTypes setTimeSpan(TimeSpan value) { this.timeSpan = value; return this; }
        public Date getDateTimeOffset() { return dateTimeOffset; }
        public AllTypes setDateTimeOffset(Date value) { this.dateTimeOffset = value; return this; }
        public UUID getGuid() { return guid; }
        public AllTypes setGuid(UUID value) { this.guid = value; return this; }
        public String getChar() { return Char; }
        public AllTypes setChar(String value) { this.Char = value; return this; }
        public Date getNullableDateTime() { return nullableDateTime; }
        public AllTypes setNullableDateTime(Date value) { this.nullableDateTime = value; return this; }
        public TimeSpan getNullableTimeSpan() { return nullableTimeSpan; }
        public AllTypes setNullableTimeSpan(TimeSpan value) { this.nullableTimeSpan = value; return this; }
        public ArrayList<String> getStringList() { return stringList; }
        public AllTypes setStringList(ArrayList<String> value) { this.stringList = value; return this; }
        public ArrayList<String> getStringArray() { return stringArray; }
        public AllTypes setStringArray(ArrayList<String> value) { this.stringArray = value; return this; }
        public HashMap<String,String> getStringMap() { return stringMap; }
        public AllTypes setStringMap(HashMap<String,String> value) { this.stringMap = value; return this; }
        public HashMap<Integer,String> getIntStringMap() { return intStringMap; }
        public AllTypes setIntStringMap(HashMap<Integer,String> value) { this.intStringMap = value; return this; }
        public SubType getSubType() { return subType; }
        public AllTypes setSubType(SubType value) { this.subType = value; return this; }
    }

    public static class AllCollectionTypes
    {
        public ArrayList<Integer> intArray = null;
        public ArrayList<Integer> intList = null;
        public ArrayList<String> stringArray = null;
        public ArrayList<String> stringList = null;
        public ArrayList<Poco> pocoArray = null;
        public ArrayList<Poco> pocoList = null;
        public HashMap<String,ArrayList<Poco>> pocoLookup = null;
        public HashMap<String,ArrayList<HashMap<String,Poco>>> pocoLookupMap = null;

        public ArrayList<Integer> getIntArray() { return intArray; }
        public AllCollectionTypes setIntArray(ArrayList<Integer> value) { this.intArray = value; return this; }
        public ArrayList<Integer> getIntList() { return intList; }
        public AllCollectionTypes setIntList(ArrayList<Integer> value) { this.intList = value; return this; }
        public ArrayList<String> getStringArray() { return stringArray; }
        public AllCollectionTypes setStringArray(ArrayList<String> value) { this.stringArray = value; return this; }
        public ArrayList<String> getStringList() { return stringList; }
        public AllCollectionTypes setStringList(ArrayList<String> value) { this.stringList = value; return this; }
        public ArrayList<Poco> getPocoArray() { return pocoArray; }
        public AllCollectionTypes setPocoArray(ArrayList<Poco> value) { this.pocoArray = value; return this; }
        public ArrayList<Poco> getPocoList() { return pocoList; }
        public AllCollectionTypes setPocoList(ArrayList<Poco> value) { this.pocoList = value; return this; }
        public HashMap<String,ArrayList<Poco>> getPocoLookup() { return pocoLookup; }
        public AllCollectionTypes setPocoLookup(HashMap<String,ArrayList<Poco>> value) { this.pocoLookup = value; return this; }
        public HashMap<String,ArrayList<HashMap<String,Poco>>> getPocoLookupMap() { return pocoLookupMap; }
        public AllCollectionTypes setPocoLookupMap(HashMap<String,ArrayList<HashMap<String,Poco>>> value) { this.pocoLookupMap = value; return this; }
    }

    public static class HelloBase
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public HelloBase setId(Integer value) { this.id = value; return this; }
    }

    public static class HelloResponseBase
    {
        public Integer refId = null;

        public Integer getRefId() { return refId; }
        public HelloResponseBase setRefId(Integer value) { this.refId = value; return this; }
    }

    public static class Poco
    {
        public String name = null;

        public String getName() { return name; }
        public Poco setName(String value) { this.name = value; return this; }
    }

    public static class HelloBase_1<T>
    {
        public ArrayList<T> items = null;
        public ArrayList<Integer> counts = null;

        public ArrayList<T> getItems() { return items; }
        public HelloBase_1<T> setItems(ArrayList<T> value) { this.items = value; return this; }
        public ArrayList<Integer> getCounts() { return counts; }
        public HelloBase_1<T> setCounts(ArrayList<Integer> value) { this.counts = value; return this; }
    }

    public static class Item
    {
        public String value = null;

        public String getValue() { return value; }
        public Item setValue(String value) { this.value = value; return this; }
    }

    public static class InheritedItem
    {
        public String name = null;

        public String getName() { return name; }
        public InheritedItem setName(String value) { this.name = value; return this; }
    }

    public static class HelloWithReturnResponse
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloWithReturnResponse setResult(String value) { this.result = value; return this; }
    }

    public static class HelloType
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloType setResult(String value) { this.result = value; return this; }
    }

    @DataContract
    public static class AuthUserSession
    {
        @DataMember(Order=1)
        public String referrerUrl = null;

        @DataMember(Order=2)
        public String id = null;

        @DataMember(Order=3)
        public String userAuthId = null;

        @DataMember(Order=4)
        public String userAuthName = null;

        @DataMember(Order=5)
        public String userName = null;

        @DataMember(Order=6)
        public String twitterUserId = null;

        @DataMember(Order=7)
        public String twitterScreenName = null;

        @DataMember(Order=8)
        public String facebookUserId = null;

        @DataMember(Order=9)
        public String facebookUserName = null;

        @DataMember(Order=10)
        public String firstName = null;

        @DataMember(Order=11)
        public String lastName = null;

        @DataMember(Order=12)
        public String displayName = null;

        @DataMember(Order=13)
        public String company = null;

        @DataMember(Order=14)
        public String email = null;

        @DataMember(Order=15)
        public String primaryEmail = null;

        @DataMember(Order=16)
        public String phoneNumber = null;

        @DataMember(Order=17)
        public Date birthDate = null;

        @DataMember(Order=18)
        public String birthDateRaw = null;

        @DataMember(Order=19)
        public String address = null;

        @DataMember(Order=20)
        public String address2 = null;

        @DataMember(Order=21)
        public String city = null;

        @DataMember(Order=22)
        public String state = null;

        @DataMember(Order=23)
        public String country = null;

        @DataMember(Order=24)
        public String culture = null;

        @DataMember(Order=25)
        public String fullName = null;

        @DataMember(Order=26)
        public String gender = null;

        @DataMember(Order=27)
        public String language = null;

        @DataMember(Order=28)
        public String mailAddress = null;

        @DataMember(Order=29)
        public String nickname = null;

        @DataMember(Order=30)
        public String postalCode = null;

        @DataMember(Order=31)
        public String timeZone = null;

        @DataMember(Order=32)
        public String requestTokenSecret = null;

        @DataMember(Order=33)
        public Date createdAt = null;

        @DataMember(Order=34)
        public Date lastModified = null;

        @DataMember(Order=35)
        public ArrayList<String> roles = null;

        @DataMember(Order=36)
        public ArrayList<String> permissions = null;

        @DataMember(Order=37)
        public Boolean isAuthenticated = null;

        @DataMember(Order=38)
        public String sequence = null;

        @DataMember(Order=39)
        public Long tag = null;

        @DataMember(Order=40)
        public ArrayList<IAuthTokens> providerOAuthAccess = null;

        public String getReferrerUrl() { return referrerUrl; }
        public AuthUserSession setReferrerUrl(String value) { this.referrerUrl = value; return this; }
        public String getId() { return id; }
        public AuthUserSession setId(String value) { this.id = value; return this; }
        public String getUserAuthId() { return userAuthId; }
        public AuthUserSession setUserAuthId(String value) { this.userAuthId = value; return this; }
        public String getUserAuthName() { return userAuthName; }
        public AuthUserSession setUserAuthName(String value) { this.userAuthName = value; return this; }
        public String getUserName() { return userName; }
        public AuthUserSession setUserName(String value) { this.userName = value; return this; }
        public String getTwitterUserId() { return twitterUserId; }
        public AuthUserSession setTwitterUserId(String value) { this.twitterUserId = value; return this; }
        public String getTwitterScreenName() { return twitterScreenName; }
        public AuthUserSession setTwitterScreenName(String value) { this.twitterScreenName = value; return this; }
        public String getFacebookUserId() { return facebookUserId; }
        public AuthUserSession setFacebookUserId(String value) { this.facebookUserId = value; return this; }
        public String getFacebookUserName() { return facebookUserName; }
        public AuthUserSession setFacebookUserName(String value) { this.facebookUserName = value; return this; }
        public String getFirstName() { return firstName; }
        public AuthUserSession setFirstName(String value) { this.firstName = value; return this; }
        public String getLastName() { return lastName; }
        public AuthUserSession setLastName(String value) { this.lastName = value; return this; }
        public String getDisplayName() { return displayName; }
        public AuthUserSession setDisplayName(String value) { this.displayName = value; return this; }
        public String getCompany() { return company; }
        public AuthUserSession setCompany(String value) { this.company = value; return this; }
        public String getEmail() { return email; }
        public AuthUserSession setEmail(String value) { this.email = value; return this; }
        public String getPrimaryEmail() { return primaryEmail; }
        public AuthUserSession setPrimaryEmail(String value) { this.primaryEmail = value; return this; }
        public String getPhoneNumber() { return phoneNumber; }
        public AuthUserSession setPhoneNumber(String value) { this.phoneNumber = value; return this; }
        public Date getBirthDate() { return birthDate; }
        public AuthUserSession setBirthDate(Date value) { this.birthDate = value; return this; }
        public String getBirthDateRaw() { return birthDateRaw; }
        public AuthUserSession setBirthDateRaw(String value) { this.birthDateRaw = value; return this; }
        public String getAddress() { return address; }
        public AuthUserSession setAddress(String value) { this.address = value; return this; }
        public String getAddress2() { return address2; }
        public AuthUserSession setAddress2(String value) { this.address2 = value; return this; }
        public String getCity() { return city; }
        public AuthUserSession setCity(String value) { this.city = value; return this; }
        public String getState() { return state; }
        public AuthUserSession setState(String value) { this.state = value; return this; }
        public String getCountry() { return country; }
        public AuthUserSession setCountry(String value) { this.country = value; return this; }
        public String getCulture() { return culture; }
        public AuthUserSession setCulture(String value) { this.culture = value; return this; }
        public String getFullName() { return fullName; }
        public AuthUserSession setFullName(String value) { this.fullName = value; return this; }
        public String getGender() { return gender; }
        public AuthUserSession setGender(String value) { this.gender = value; return this; }
        public String getLanguage() { return language; }
        public AuthUserSession setLanguage(String value) { this.language = value; return this; }
        public String getMailAddress() { return mailAddress; }
        public AuthUserSession setMailAddress(String value) { this.mailAddress = value; return this; }
        public String getNickname() { return nickname; }
        public AuthUserSession setNickname(String value) { this.nickname = value; return this; }
        public String getPostalCode() { return postalCode; }
        public AuthUserSession setPostalCode(String value) { this.postalCode = value; return this; }
        public String getTimeZone() { return timeZone; }
        public AuthUserSession setTimeZone(String value) { this.timeZone = value; return this; }
        public String getRequestTokenSecret() { return requestTokenSecret; }
        public AuthUserSession setRequestTokenSecret(String value) { this.requestTokenSecret = value; return this; }
        public Date getCreatedAt() { return createdAt; }
        public AuthUserSession setCreatedAt(Date value) { this.createdAt = value; return this; }
        public Date getLastModified() { return lastModified; }
        public AuthUserSession setLastModified(Date value) { this.lastModified = value; return this; }
        public ArrayList<String> getRoles() { return roles; }
        public AuthUserSession setRoles(ArrayList<String> value) { this.roles = value; return this; }
        public ArrayList<String> getPermissions() { return permissions; }
        public AuthUserSession setPermissions(ArrayList<String> value) { this.permissions = value; return this; }
        public Boolean getIsAuthenticated() { return isAuthenticated; }
        public AuthUserSession setIsAuthenticated(Boolean value) { this.isAuthenticated = value; return this; }
        public String getSequence() { return sequence; }
        public AuthUserSession setSequence(String value) { this.sequence = value; return this; }
        public Long getTag() { return tag; }
        public AuthUserSession setTag(Long value) { this.tag = value; return this; }
        public ArrayList<IAuthTokens> getProviderOAuthAccess() { return providerOAuthAccess; }
        public AuthUserSession setProviderOAuthAccess(ArrayList<IAuthTokens> value) { this.providerOAuthAccess = value; return this; }
    }

    public static interface IPoco
    {
        public String name = null;
    }

    public static interface IEmptyInterface
    {
    }

    public static class EmptyClass
    {

    }

    public static class TypeA
    {
        public ArrayList<TypeB> bar = null;

        public ArrayList<TypeB> getBar() { return bar; }
        public TypeA setBar(ArrayList<TypeB> value) { this.bar = value; return this; }
    }

    public static class InnerType
    {
        public Long id = null;
        public String name = null;

        public Long getId() { return id; }
        public InnerType setId(Long value) { this.id = value; return this; }
        public String getName() { return name; }
        public InnerType setName(String value) { this.name = value; return this; }
    }

    public static enum InnerEnum
    {
        Foo,
        Bar,
        Baz;
    }

    public static interface IAuthTokens
    {
        public String provider = null;
        public String userId = null;
        public String accessToken = null;
        public String accessTokenSecret = null;
        public String refreshToken = null;
        public Date refreshTokenExpiry = null;
        public String requestToken = null;
        public String requestTokenSecret = null;
        public HashMap<String,String> items = null;
    }

    public static class QueryBase_2<From, Into> extends QueryBase
    {

    }

    public static class CustomRockstar
    {
        @AutoQueryViewerField(Title="Name")
        public String firstName = null;

        @AutoQueryViewerField(HideInSummary=true)
        public String lastName = null;

        public Integer age = null;
        @AutoQueryViewerField(Title="Album")
        public String rockstarAlbumName = null;

        @AutoQueryViewerField(Title="Genre")
        public String rockstarGenreName = null;

        public String getFirstName() { return firstName; }
        public CustomRockstar setFirstName(String value) { this.firstName = value; return this; }
        public String getLastName() { return lastName; }
        public CustomRockstar setLastName(String value) { this.lastName = value; return this; }
        public Integer getAge() { return age; }
        public CustomRockstar setAge(Integer value) { this.age = value; return this; }
        public String getRockstarAlbumName() { return rockstarAlbumName; }
        public CustomRockstar setRockstarAlbumName(String value) { this.rockstarAlbumName = value; return this; }
        public String getRockstarGenreName() { return rockstarGenreName; }
        public CustomRockstar setRockstarGenreName(String value) { this.rockstarGenreName = value; return this; }
    }

    public static class Movie
    {
        public Integer id = null;
        public String imdbId = null;
        public String title = null;
        public String rating = null;
        public BigDecimal score = null;
        public String director = null;
        public Date releaseDate = null;
        public String tagLine = null;
        public ArrayList<String> genres = null;

        public Integer getId() { return id; }
        public Movie setId(Integer value) { this.id = value; return this; }
        public String getImdbId() { return imdbId; }
        public Movie setImdbId(String value) { this.imdbId = value; return this; }
        public String getTitle() { return title; }
        public Movie setTitle(String value) { this.title = value; return this; }
        public String getRating() { return rating; }
        public Movie setRating(String value) { this.rating = value; return this; }
        public BigDecimal getScore() { return score; }
        public Movie setScore(BigDecimal value) { this.score = value; return this; }
        public String getDirector() { return director; }
        public Movie setDirector(String value) { this.director = value; return this; }
        public Date getReleaseDate() { return releaseDate; }
        public Movie setReleaseDate(Date value) { this.releaseDate = value; return this; }
        public String getTagLine() { return tagLine; }
        public Movie setTagLine(String value) { this.tagLine = value; return this; }
        public ArrayList<String> getGenres() { return genres; }
        public Movie setGenres(ArrayList<String> value) { this.genres = value; return this; }
    }

    public static class RockstarReference
    {
        public Integer id = null;
        public String firstName = null;
        public String lastName = null;
        public Integer age = null;
        public ArrayList<RockstarAlbum> albums = null;

        public Integer getId() { return id; }
        public RockstarReference setId(Integer value) { this.id = value; return this; }
        public String getFirstName() { return firstName; }
        public RockstarReference setFirstName(String value) { this.firstName = value; return this; }
        public String getLastName() { return lastName; }
        public RockstarReference setLastName(String value) { this.lastName = value; return this; }
        public Integer getAge() { return age; }
        public RockstarReference setAge(Integer value) { this.age = value; return this; }
        public ArrayList<RockstarAlbum> getAlbums() { return albums; }
        public RockstarReference setAlbums(ArrayList<RockstarAlbum> value) { this.albums = value; return this; }
    }

    public static class QueryBase
    {
        @DataMember(Order=1)
        public Integer skip = null;

        @DataMember(Order=2)
        public Integer take = null;

        @DataMember(Order=3)
        public String orderBy = null;

        @DataMember(Order=4)
        public String orderByDesc = null;

        public Integer getSkip() { return skip; }
        public QueryBase setSkip(Integer value) { this.skip = value; return this; }
        public Integer getTake() { return take; }
        public QueryBase setTake(Integer value) { this.take = value; return this; }
        public String getOrderBy() { return orderBy; }
        public QueryBase setOrderBy(String value) { this.orderBy = value; return this; }
        public String getOrderByDesc() { return orderByDesc; }
        public QueryBase setOrderByDesc(String value) { this.orderByDesc = value; return this; }
    }

    @DataContract
    public static class ResponseError
    {
        @DataMember(Order=1, EmitDefaultValue=false)
        public String errorCode = null;

        @DataMember(Order=2, EmitDefaultValue=false)
        public String fieldName = null;

        @DataMember(Order=3, EmitDefaultValue=false)
        public String message = null;

        public String getErrorCode() { return errorCode; }
        public ResponseError setErrorCode(String value) { this.errorCode = value; return this; }
        public String getFieldName() { return fieldName; }
        public ResponseError setFieldName(String value) { this.fieldName = value; return this; }
        public String getMessage() { return message; }
        public ResponseError setMessage(String value) { this.message = value; return this; }
    }

    public static class MetadataTestNestedChild
    {
        public String name = null;

        public String getName() { return name; }
        public MetadataTestNestedChild setName(String value) { this.name = value; return this; }
    }

    public static class MenuItemExample
    {
        @DataMember(Order=1)
        @ApiMember()
        public String name1 = null;

        public MenuItemExampleItem menuItemExampleItem = null;

        public String getName1() { return name1; }
        public MenuItemExample setName1(String value) { this.name1 = value; return this; }
        public MenuItemExampleItem getMenuItemExampleItem() { return menuItemExampleItem; }
        public MenuItemExample setMenuItemExampleItem(MenuItemExampleItem value) { this.menuItemExampleItem = value; return this; }
    }

    public static class MetadataTypeName
    {
        public String name = null;
        public String namespace = null;
        public ArrayList<String> genericArgs = null;

        public String getName() { return name; }
        public MetadataTypeName setName(String value) { this.name = value; return this; }
        public String getNamespace() { return namespace; }
        public MetadataTypeName setNamespace(String value) { this.namespace = value; return this; }
        public ArrayList<String> getGenericArgs() { return genericArgs; }
        public MetadataTypeName setGenericArgs(ArrayList<String> value) { this.genericArgs = value; return this; }
    }

    public static class MetadataRoute
    {
        public String path = null;
        public String verbs = null;
        public String notes = null;
        public String summary = null;

        public String getPath() { return path; }
        public MetadataRoute setPath(String value) { this.path = value; return this; }
        public String getVerbs() { return verbs; }
        public MetadataRoute setVerbs(String value) { this.verbs = value; return this; }
        public String getNotes() { return notes; }
        public MetadataRoute setNotes(String value) { this.notes = value; return this; }
        public String getSummary() { return summary; }
        public MetadataRoute setSummary(String value) { this.summary = value; return this; }
    }

    public static class MetadataDataContract
    {
        public String name = null;
        public String namespace = null;

        public String getName() { return name; }
        public MetadataDataContract setName(String value) { this.name = value; return this; }
        public String getNamespace() { return namespace; }
        public MetadataDataContract setNamespace(String value) { this.namespace = value; return this; }
    }

    public static class MetadataPropertyType
    {
        public String name = null;
        public String type = null;
        public Boolean isValueType = null;
        public String typeNamespace = null;
        public ArrayList<String> genericArgs = null;
        public String value = null;
        public String description = null;
        public MetadataDataMember dataMember = null;
        public Boolean readOnly = null;
        public String paramType = null;
        public String displayType = null;
        public Boolean isRequired = null;
        public ArrayList<String> allowableValues = null;
        public Integer allowableMin = null;
        public Integer allowableMax = null;
        public ArrayList<MetadataAttribute> attributes = null;

        public String getName() { return name; }
        public MetadataPropertyType setName(String value) { this.name = value; return this; }
        public String getType() { return type; }
        public MetadataPropertyType setType(String value) { this.type = value; return this; }
        public Boolean getIsValueType() { return isValueType; }
        public MetadataPropertyType setIsValueType(Boolean value) { this.isValueType = value; return this; }
        public String getTypeNamespace() { return typeNamespace; }
        public MetadataPropertyType setTypeNamespace(String value) { this.typeNamespace = value; return this; }
        public ArrayList<String> getGenericArgs() { return genericArgs; }
        public MetadataPropertyType setGenericArgs(ArrayList<String> value) { this.genericArgs = value; return this; }
        public String getValue() { return value; }
        public MetadataPropertyType setValue(String value) { this.value = value; return this; }
        public String getDescription() { return description; }
        public MetadataPropertyType setDescription(String value) { this.description = value; return this; }
        public MetadataDataMember getDataMember() { return dataMember; }
        public MetadataPropertyType setDataMember(MetadataDataMember value) { this.dataMember = value; return this; }
        public Boolean isReadOnly() { return readOnly; }
        public MetadataPropertyType setReadOnly(Boolean value) { this.readOnly = value; return this; }
        public String getParamType() { return paramType; }
        public MetadataPropertyType setParamType(String value) { this.paramType = value; return this; }
        public String getDisplayType() { return displayType; }
        public MetadataPropertyType setDisplayType(String value) { this.displayType = value; return this; }
        public Boolean getIsRequired() { return isRequired; }
        public MetadataPropertyType setIsRequired(Boolean value) { this.isRequired = value; return this; }
        public ArrayList<String> getAllowableValues() { return allowableValues; }
        public MetadataPropertyType setAllowableValues(ArrayList<String> value) { this.allowableValues = value; return this; }
        public Integer getAllowableMin() { return allowableMin; }
        public MetadataPropertyType setAllowableMin(Integer value) { this.allowableMin = value; return this; }
        public Integer getAllowableMax() { return allowableMax; }
        public MetadataPropertyType setAllowableMax(Integer value) { this.allowableMax = value; return this; }
        public ArrayList<MetadataAttribute> getAttributes() { return attributes; }
        public MetadataPropertyType setAttributes(ArrayList<MetadataAttribute> value) { this.attributes = value; return this; }
    }

    public static class MetadataAttribute
    {
        public String name = null;
        public ArrayList<MetadataPropertyType> constructorArgs = null;
        public ArrayList<MetadataPropertyType> args = null;

        public String getName() { return name; }
        public MetadataAttribute setName(String value) { this.name = value; return this; }
        public ArrayList<MetadataPropertyType> getConstructorArgs() { return constructorArgs; }
        public MetadataAttribute setConstructorArgs(ArrayList<MetadataPropertyType> value) { this.constructorArgs = value; return this; }
        public ArrayList<MetadataPropertyType> getArgs() { return args; }
        public MetadataAttribute setArgs(ArrayList<MetadataPropertyType> value) { this.args = value; return this; }
    }

    @DataContract
    public static class Property
    {
        @DataMember
        public String name = null;

        @DataMember
        public String value = null;

        public String getName() { return name; }
        public Property setName(String value) { this.name = value; return this; }
        public String getValue() { return value; }
        public Property setValue(String value) { this.value = value; return this; }
    }

    public static class SubType
    {
        public Integer id = null;
        public String name = null;

        public Integer getId() { return id; }
        public SubType setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public SubType setName(String value) { this.name = value; return this; }
    }

    public static class TypeB
    {
        public String foo = null;

        public String getFoo() { return foo; }
        public TypeB setFoo(String value) { this.foo = value; return this; }
    }

    public static class TypesGroup
    {

    }

    public static class RockstarAlbum
    {
        public Integer id = null;
        public Integer rockstarId = null;
        public String name = null;

        public Integer getId() { return id; }
        public RockstarAlbum setId(Integer value) { this.id = value; return this; }
        public Integer getRockstarId() { return rockstarId; }
        public RockstarAlbum setRockstarId(Integer value) { this.rockstarId = value; return this; }
        public String getName() { return name; }
        public RockstarAlbum setName(String value) { this.name = value; return this; }
    }

    public static class MenuItemExampleItem
    {
        @DataMember(Order=1)
        @ApiMember()
        public String name1 = null;

        public String getName1() { return name1; }
        public MenuItemExampleItem setName1(String value) { this.name1 = value; return this; }
    }

    public static class MetadataDataMember
    {
        public String name = null;
        public Integer order = null;
        public Boolean isRequired = null;
        public Boolean emitDefaultValue = null;

        public String getName() { return name; }
        public MetadataDataMember setName(String value) { this.name = value; return this; }
        public Integer getOrder() { return order; }
        public MetadataDataMember setOrder(Integer value) { this.order = value; return this; }
        public Boolean getIsRequired() { return isRequired; }
        public MetadataDataMember setIsRequired(Boolean value) { this.isRequired = value; return this; }
        public Boolean isEmitDefaultValue() { return emitDefaultValue; }
        public MetadataDataMember setEmitDefaultValue(Boolean value) { this.emitDefaultValue = value; return this; }
    }

    @DataContract
    public static class QueryResponse<Rockstar>
    {
        @DataMember(Order=1)
        public Integer offset = null;

        @DataMember(Order=2)
        public Integer total = null;

        @DataMember(Order=3)
        public ArrayList<Rockstar> results = null;

        @DataMember(Order=4)
        public HashMap<String,String> meta = null;

        @DataMember(Order=5)
        public ResponseStatus responseStatus = null;

        public Integer getOffset() { return offset; }
        public QueryResponse<Rockstar> setOffset(Integer value) { this.offset = value; return this; }
        public Integer getTotal() { return total; }
        public QueryResponse<Rockstar> setTotal(Integer value) { this.total = value; return this; }
        public ArrayList<Rockstar> getResults() { return results; }
        public QueryResponse<Rockstar> setResults(ArrayList<Rockstar> value) { this.results = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public QueryResponse<Rockstar> setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public QueryResponse<Rockstar> setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class ChangeRequestResponse
    {
        public String contentType = null;
        public String header = null;
        public String queryString = null;
        public String form = null;
        public ResponseStatus responseStatus = null;

        public String getContentType() { return contentType; }
        public ChangeRequestResponse setContentType(String value) { this.contentType = value; return this; }
        public String getHeader() { return header; }
        public ChangeRequestResponse setHeader(String value) { this.header = value; return this; }
        public String getQueryString() { return queryString; }
        public ChangeRequestResponse setQueryString(String value) { this.queryString = value; return this; }
        public String getForm() { return form; }
        public ChangeRequestResponse setForm(String value) { this.form = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public ChangeRequestResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CustomHttpErrorResponse
    {
        public String custom = null;
        public ResponseStatus responseStatus = null;

        public String getCustom() { return custom; }
        public CustomHttpErrorResponse setCustom(String value) { this.custom = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public CustomHttpErrorResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CustomFieldHttpErrorResponse
    {
        public String custom = null;
        public ResponseStatus responseStatus = null;

        public String getCustom() { return custom; }
        public CustomFieldHttpErrorResponse setCustom(String value) { this.custom = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public CustomFieldHttpErrorResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class NoRepeatResponse
    {
        public UUID id = null;

        public UUID getId() { return id; }
        public NoRepeatResponse setId(UUID value) { this.id = value; return this; }
    }

    public static class BatchThrowsResponse
    {
        public String result = null;
        public ResponseStatus responseStatus = null;

        public String getResult() { return result; }
        public BatchThrowsResponse setResult(String value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public BatchThrowsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class MetadataTestResponse
    {
        public Integer id = null;
        public ArrayList<MetadataTestChild> results = null;

        public Integer getId() { return id; }
        public MetadataTestResponse setId(Integer value) { this.id = value; return this; }
        public ArrayList<MetadataTestChild> getResults() { return results; }
        public MetadataTestResponse setResults(ArrayList<MetadataTestChild> value) { this.results = value; return this; }
    }

    @DataContract
    public static class GetExampleResponse
    {
        @DataMember(Order=1)
        public ResponseStatus responseStatus = null;

        @DataMember(Order=2)
        @ApiMember()
        public MenuExample menuExample1 = null;

        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetExampleResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
        public MenuExample getMenuExample1() { return menuExample1; }
        public GetExampleResponse setMenuExample1(MenuExample value) { this.menuExample1 = value; return this; }
    }

    public static class AutoQueryMetadataResponse
    {
        public AutoQueryViewerConfig config = null;
        public ArrayList<AutoQueryOperation> operations = null;
        public ArrayList<MetadataType> types = null;
        public ResponseStatus responseStatus = null;

        public AutoQueryViewerConfig getConfig() { return config; }
        public AutoQueryMetadataResponse setConfig(AutoQueryViewerConfig value) { this.config = value; return this; }
        public ArrayList<AutoQueryOperation> getOperations() { return operations; }
        public AutoQueryMetadataResponse setOperations(ArrayList<AutoQueryOperation> value) { this.operations = value; return this; }
        public ArrayList<MetadataType> getTypes() { return types; }
        public AutoQueryMetadataResponse setTypes(ArrayList<MetadataType> value) { this.types = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public AutoQueryMetadataResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class HelloResponse
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloResponse setResult(String value) { this.result = value; return this; }
    }

    /**
     * Description on HelloAllResponse type
     */
    @DataContract
    public static class HelloAnnotatedResponse
    {
        @DataMember
        public String result = null;

        public String getResult() { return result; }
        public HelloAnnotatedResponse setResult(String value) { this.result = value; return this; }
    }

    public static class HelloExistingResponse
    {
        public HelloList helloList = null;
        public HelloArray helloArray = null;
        public ArrayList<ArrayResult> arrayResults = null;
        public ArrayList<ListResult> listResults = null;

        public HelloList getHelloList() { return helloList; }
        public HelloExistingResponse setHelloList(HelloList value) { this.helloList = value; return this; }
        public HelloArray getHelloArray() { return helloArray; }
        public HelloExistingResponse setHelloArray(HelloArray value) { this.helloArray = value; return this; }
        public ArrayList<ArrayResult> getArrayResults() { return arrayResults; }
        public HelloExistingResponse setArrayResults(ArrayList<ArrayResult> value) { this.arrayResults = value; return this; }
        public ArrayList<ListResult> getListResults() { return listResults; }
        public HelloExistingResponse setListResults(ArrayList<ListResult> value) { this.listResults = value; return this; }
    }

    public static class HelloAllTypesResponse
    {
        public String result = null;
        public AllTypes allTypes = null;
        public AllCollectionTypes allCollectionTypes = null;

        public String getResult() { return result; }
        public HelloAllTypesResponse setResult(String value) { this.result = value; return this; }
        public AllTypes getAllTypes() { return allTypes; }
        public HelloAllTypesResponse setAllTypes(AllTypes value) { this.allTypes = value; return this; }
        public AllCollectionTypes getAllCollectionTypes() { return allCollectionTypes; }
        public HelloAllTypesResponse setAllCollectionTypes(AllCollectionTypes value) { this.allCollectionTypes = value; return this; }
    }

    @DataContract
    public static class HelloWithDataContractResponse
    {
        @DataMember(Name="result", Order=1, IsRequired=true, EmitDefaultValue=false)
        public String result = null;

        public String getResult() { return result; }
        public HelloWithDataContractResponse setResult(String value) { this.result = value; return this; }
    }

    /**
     * Description on HelloWithDescriptionResponse type
     */
    public static class HelloWithDescriptionResponse
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloWithDescriptionResponse setResult(String value) { this.result = value; return this; }
    }

    public static class HelloWithInheritanceResponse extends HelloResponseBase
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloWithInheritanceResponse setResult(String value) { this.result = value; return this; }
    }

    public static class HelloWithAlternateReturnResponse extends HelloWithReturnResponse
    {
        public String altResult = null;

        public String getAltResult() { return altResult; }
        public HelloWithAlternateReturnResponse setAltResult(String value) { this.altResult = value; return this; }
    }

    public static class HelloWithRouteResponse
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloWithRouteResponse setResult(String value) { this.result = value; return this; }
    }

    public static class HelloWithTypeResponse
    {
        public HelloType result = null;

        public HelloType getResult() { return result; }
        public HelloWithTypeResponse setResult(HelloType value) { this.result = value; return this; }
    }

    public static class HelloSessionResponse
    {
        public AuthUserSession result = null;

        public AuthUserSession getResult() { return result; }
        public HelloSessionResponse setResult(AuthUserSession value) { this.result = value; return this; }
    }

    public static class Request1Response
    {
        public TypeA test = null;

        public TypeA getTest() { return test; }
        public Request1Response setTest(TypeA value) { this.test = value; return this; }
    }

    public static class Request2Response
    {
        public TypeA test = null;

        public TypeA getTest() { return test; }
        public Request2Response setTest(TypeA value) { this.test = value; return this; }
    }

    public static class HelloInnerTypesResponse
    {
        public InnerType innerType = null;
        public InnerEnum innerEnum = null;

        public InnerType getInnerType() { return innerType; }
        public HelloInnerTypesResponse setInnerType(InnerType value) { this.innerType = value; return this; }
        public InnerEnum getInnerEnum() { return innerEnum; }
        public HelloInnerTypesResponse setInnerEnum(InnerEnum value) { this.innerEnum = value; return this; }
    }

    public static class CustomUserSession extends AuthUserSession
    {
        @DataMember
        public String customName = null;

        @DataMember
        public String customInfo = null;

        public String getCustomName() { return customName; }
        public CustomUserSession setCustomName(String value) { this.customName = value; return this; }
        public String getCustomInfo() { return customInfo; }
        public CustomUserSession setCustomInfo(String value) { this.customInfo = value; return this; }
    }

    public static class Echo
    {
        public String sentence = null;

        public String getSentence() { return sentence; }
        public Echo setSentence(String value) { this.sentence = value; return this; }
    }

    public static class ThrowTypeResponse
    {
        public ResponseStatus responseStatus = null;

        public ResponseStatus getResponseStatus() { return responseStatus; }
        public ThrowTypeResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class acsprofileResponse
    {
        public String profileId = null;

        public String getProfileId() { return profileId; }
        public acsprofileResponse setProfileId(String value) { this.profileId = value; return this; }
    }

    @Route("/anontype")
    public static class AnonType
    {

    }

    @Route("/query/rockstars")
    public static class QueryRockstars extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public Integer age = null;

        public Integer getAge() { return age; }
        public QueryRockstars setAge(Integer value) { this.age = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    @Route("/changerequest/{Id}")
    public static class ChangeRequest implements IReturn<ChangeRequestResponse>
    {
        public String id = null;

        public String getId() { return id; }
        public ChangeRequest setId(String value) { this.id = value; return this; }
        private static Class responseType = ChangeRequestResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/Routing/LeadPost.aspx")
    public static class LegacyLeadPost
    {
        public String leadType = null;
        public Integer myId = null;

        public String getLeadType() { return leadType; }
        public LegacyLeadPost setLeadType(String value) { this.leadType = value; return this; }
        public Integer getMyId() { return myId; }
        public LegacyLeadPost setMyId(Integer value) { this.myId = value; return this; }
    }

    @Route("/info/{Id}")
    public static class Info
    {
        public String id = null;

        public String getId() { return id; }
        public Info setId(String value) { this.id = value; return this; }
    }

    public static class CustomHttpError implements IReturn<CustomHttpErrorResponse>
    {
        public Integer statusCode = null;
        public String statusDescription = null;

        public Integer getStatusCode() { return statusCode; }
        public CustomHttpError setStatusCode(Integer value) { this.statusCode = value; return this; }
        public String getStatusDescription() { return statusDescription; }
        public CustomHttpError setStatusDescription(String value) { this.statusDescription = value; return this; }
        private static Class responseType = CustomHttpErrorResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class CustomFieldHttpError implements IReturn<CustomFieldHttpErrorResponse>
    {

        private static Class responseType = CustomFieldHttpErrorResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("{PathInfo*}")
    public static class FallbackRoute
    {
        public String pathInfo = null;

        public String getPathInfo() { return pathInfo; }
        public FallbackRoute setPathInfo(String value) { this.pathInfo = value; return this; }
    }

    public static class NoRepeat implements IReturn<NoRepeatResponse>
    {
        public UUID id = null;

        public UUID getId() { return id; }
        public NoRepeat setId(UUID value) { this.id = value; return this; }
        private static Class responseType = NoRepeatResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class BatchThrows implements IReturn<BatchThrowsResponse>
    {
        public Integer id = null;
        public String name = null;

        public Integer getId() { return id; }
        public BatchThrows setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public BatchThrows setName(String value) { this.name = value; return this; }
        private static Class responseType = BatchThrowsResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class BatchThrowsAsync implements IReturn<BatchThrowsResponse>
    {
        public Integer id = null;
        public String name = null;

        public Integer getId() { return id; }
        public BatchThrowsAsync setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public BatchThrowsAsync setName(String value) { this.name = value; return this; }
        private static Class responseType = BatchThrowsResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class MetadataTest implements IReturn<MetadataTestResponse>
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public MetadataTest setId(Integer value) { this.id = value; return this; }
        private static Class responseType = MetadataTestResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route(Path="/example", Verbs="GET")
    @DataContract
    public static class GetExample implements IReturn<GetExampleResponse>
    {

        private static Class responseType = GetExampleResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class MetadataRequest implements IReturn<AutoQueryMetadataResponse>
    {
        public MetadataType metadataType = null;

        public MetadataType getMetadataType() { return metadataType; }
        public MetadataRequest setMetadataType(MetadataType value) { this.metadataType = value; return this; }
        private static Class responseType = AutoQueryMetadataResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/namedconnection")
    public static class NamedConnection
    {
        public String emailAddresses = null;

        public String getEmailAddresses() { return emailAddresses; }
        public NamedConnection setEmailAddresses(String value) { this.emailAddresses = value; return this; }
    }

    public static class Issue221Long extends Issue221Base<Long>
    {

    }

    public static class HelloInService implements IReturn<HelloResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloInService setName(String value) { this.name = value; return this; }
        private static Class responseType = HelloResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/hello/{Name}")
    public static class Hello implements IReturn<HelloResponse>
    {
        @Required()
        public String name = null;

        public String title = null;

        public String getName() { return name; }
        public Hello setName(String value) { this.name = value; return this; }
        public String getTitle() { return title; }
        public Hello setTitle(String value) { this.title = value; return this; }
        private static Class responseType = HelloResponse.class;
        public Class getResponseType() { return responseType; }
    }

    /**
     * Description on HelloAll type
     */
    @DataContract
    public static class HelloAnnotated implements IReturn<HelloAnnotatedResponse>
    {
        @DataMember
        public String name = null;

        public String getName() { return name; }
        public HelloAnnotated setName(String value) { this.name = value; return this; }
        private static Class responseType = HelloAnnotatedResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithNestedClass implements IReturn<HelloResponse>
    {
        public String name = null;
        public NestedClass nestedClassProp = null;

        public String getName() { return name; }
        public HelloWithNestedClass setName(String value) { this.name = value; return this; }
        public NestedClass getNestedClassProp() { return nestedClassProp; }
        public HelloWithNestedClass setNestedClassProp(NestedClass value) { this.nestedClassProp = value; return this; }
        private static Class responseType = HelloResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloList implements IReturn<ArrayList<ListResult>>
    {
        public ArrayList<String> names = null;

        public ArrayList<String> getNames() { return names; }
        public HelloList setNames(ArrayList<String> value) { this.names = value; return this; }
        private static Class responseType = new ArrayList<ListResult>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class HelloArray implements IReturn<ArrayList<ArrayResult>>
    {
        public ArrayList<String> names = null;

        public ArrayList<String> getNames() { return names; }
        public HelloArray setNames(ArrayList<String> value) { this.names = value; return this; }
        private static Class responseType = new ArrayList<ArrayResult>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class HelloExisting implements IReturn<HelloExistingResponse>
    {
        public ArrayList<String> names = null;

        public ArrayList<String> getNames() { return names; }
        public HelloExisting setNames(ArrayList<String> value) { this.names = value; return this; }
        private static Class responseType = HelloExistingResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithEnum
    {
        public EnumType enumProp = null;
        public EnumType nullableEnumProp = null;
        public EnumFlags enumFlags = null;

        public EnumType getEnumProp() { return enumProp; }
        public HelloWithEnum setEnumProp(EnumType value) { this.enumProp = value; return this; }
        public EnumType getNullableEnumProp() { return nullableEnumProp; }
        public HelloWithEnum setNullableEnumProp(EnumType value) { this.nullableEnumProp = value; return this; }
        public EnumFlags getEnumFlags() { return enumFlags; }
        public HelloWithEnum setEnumFlags(EnumFlags value) { this.enumFlags = value; return this; }
    }

    public static class RestrictedAttributes
    {
        public Integer id = null;
        public String name = null;
        public Hello hello = null;

        public Integer getId() { return id; }
        public RestrictedAttributes setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public RestrictedAttributes setName(String value) { this.name = value; return this; }
        public Hello getHello() { return hello; }
        public RestrictedAttributes setHello(Hello value) { this.hello = value; return this; }
    }

    /**
     * AllowedAttributes Description
     */
    @Route(Path="/allowed-attributes", Verbs="GET")
    // @ApiResponse(400, "Your request was not understood")
    @Api("AllowedAttributes Description")
    @DataContract
    public static class AllowedAttributes
    {
        @DataMember(Name="Aliased")
        @ApiMember(Description="Range Description", ParameterType="path", DataType="double", IsRequired=true)
        public Double range = null;

        public Double getRange() { return range; }
        public AllowedAttributes setRange(Double value) { this.range = value; return this; }
    }

    public static class HelloAllTypes implements IReturn<HelloAllTypesResponse>
    {
        public String name = null;
        public AllTypes allTypes = null;
        public AllCollectionTypes allCollectionTypes = null;

        public String getName() { return name; }
        public HelloAllTypes setName(String value) { this.name = value; return this; }
        public AllTypes getAllTypes() { return allTypes; }
        public HelloAllTypes setAllTypes(AllTypes value) { this.allTypes = value; return this; }
        public AllCollectionTypes getAllCollectionTypes() { return allCollectionTypes; }
        public HelloAllTypes setAllCollectionTypes(AllCollectionTypes value) { this.allCollectionTypes = value; return this; }
        private static Class responseType = HelloAllTypesResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloString
    {
        public String name = null;

        public String getName() { return name; }
        public HelloString setName(String value) { this.name = value; return this; }
    }

    public static class HelloVoid implements IReturnVoid
    {
        public String name = null;

        public String getName() { return name; }
        public HelloVoid setName(String value) { this.name = value; return this; }
    }

    @DataContract
    public static class HelloWithDataContract implements IReturn<HelloWithDataContractResponse>
    {
        @DataMember(Name="name", Order=1, IsRequired=true, EmitDefaultValue=false)
        public String name = null;

        @DataMember(Name="id", Order=2, EmitDefaultValue=false)
        public Integer id = null;

        public String getName() { return name; }
        public HelloWithDataContract setName(String value) { this.name = value; return this; }
        public Integer getId() { return id; }
        public HelloWithDataContract setId(Integer value) { this.id = value; return this; }
        private static Class responseType = HelloWithDataContractResponse.class;
        public Class getResponseType() { return responseType; }
    }

    /**
     * Description on HelloWithDescription type
     */
    public static class HelloWithDescription implements IReturn<HelloWithDescriptionResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithDescription setName(String value) { this.name = value; return this; }
        private static Class responseType = HelloWithDescriptionResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithInheritance extends HelloBase implements IReturn<HelloWithInheritanceResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithInheritance setName(String value) { this.name = value; return this; }
        private static Class responseType = HelloWithInheritanceResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithGenericInheritance extends HelloBase_1<Poco>
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloWithGenericInheritance setResult(String value) { this.result = value; return this; }
    }

    public static class HelloWithGenericInheritance2 extends HelloBase_1<Hello>
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloWithGenericInheritance2 setResult(String value) { this.result = value; return this; }
    }

    public static class HelloWithNestedInheritance extends HelloBase_1<Item>
    {

    }

    public static class HelloWithListInheritance extends ArrayList<InheritedItem>
    {

    }

    public static class HelloWithReturn implements IReturn<HelloWithAlternateReturnResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithReturn setName(String value) { this.name = value; return this; }
        private static Class responseType = HelloWithAlternateReturnResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/helloroute")
    public static class HelloWithRoute implements IReturn<HelloWithRouteResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithRoute setName(String value) { this.name = value; return this; }
        private static Class responseType = HelloWithRouteResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithType implements IReturn<HelloWithTypeResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithType setName(String value) { this.name = value; return this; }
        private static Class responseType = HelloWithTypeResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloSession implements IReturn<HelloSessionResponse>
    {

        private static Class responseType = HelloSessionResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloInterface
    {
        public IPoco poco = null;
        public IEmptyInterface emptyInterface = null;
        public EmptyClass emptyClass = null;
        public String value = null;

        public IPoco getPoco() { return poco; }
        public HelloInterface setPoco(IPoco value) { this.poco = value; return this; }
        public IEmptyInterface getEmptyInterface() { return emptyInterface; }
        public HelloInterface setEmptyInterface(IEmptyInterface value) { this.emptyInterface = value; return this; }
        public EmptyClass getEmptyClass() { return emptyClass; }
        public HelloInterface setEmptyClass(EmptyClass value) { this.emptyClass = value; return this; }
        public String getValue() { return value; }
        public HelloInterface setValue(String value) { this.value = value; return this; }
    }

    public static class Request1 implements IReturn<Request1Response>
    {
        public TypeA test = null;

        public TypeA getTest() { return test; }
        public Request1 setTest(TypeA value) { this.test = value; return this; }
        private static Class responseType = Request1Response.class;
        public Class getResponseType() { return responseType; }
    }

    public static class Request2 implements IReturn<Request2Response>
    {
        public TypeA test = null;

        public TypeA getTest() { return test; }
        public Request2 setTest(TypeA value) { this.test = value; return this; }
        private static Class responseType = Request2Response.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloInnerTypes implements IReturn<HelloInnerTypesResponse>
    {

        private static Class responseType = HelloInnerTypesResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class GetUserSession implements IReturn<CustomUserSession>
    {

        private static Class responseType = CustomUserSession.class;
        public Class getResponseType() { return responseType; }
    }

    /**
     * Echoes a sentence
     */
    @Route(Path="/echoes", Verbs="POST")
    @Api("Echoes a sentence")
    public static class Echoes implements IReturn<Echo>
    {
        @ApiMember(Description="The sentence to echo.", ParameterType="form", DataType="string", IsRequired=true, Name="Sentence")
        public String sentence = null;

        public String getSentence() { return sentence; }
        public Echoes setSentence(String value) { this.sentence = value; return this; }
        private static Class responseType = Echo.class;
        public Class getResponseType() { return responseType; }
    }

    public static class CachedEcho
    {
        public Boolean reload = null;
        public String sentence = null;

        public Boolean isReload() { return reload; }
        public CachedEcho setReload(Boolean value) { this.reload = value; return this; }
        public String getSentence() { return sentence; }
        public CachedEcho setSentence(String value) { this.sentence = value; return this; }
    }

    public static class AsyncTest implements IReturn<Echo>
    {

        private static Class responseType = Echo.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/throwhttperror/{Status}")
    public static class ThrowHttpError
    {
        public Integer status = null;
        public String message = null;

        public Integer getStatus() { return status; }
        public ThrowHttpError setStatus(Integer value) { this.status = value; return this; }
        public String getMessage() { return message; }
        public ThrowHttpError setMessage(String value) { this.message = value; return this; }
    }

    @Route("/throw404")
    // @Route("/throw404/{Message}")
    public static class Throw404
    {
        public String message = null;

        public String getMessage() { return message; }
        public Throw404 setMessage(String value) { this.message = value; return this; }
    }

    @Route("/throw/{Type}")
    public static class ThrowType implements IReturn<ThrowTypeResponse>
    {
        public String type = null;
        public String message = null;

        public String getType() { return type; }
        public ThrowType setType(String value) { this.type = value; return this; }
        public String getMessage() { return message; }
        public ThrowType setMessage(String value) { this.message = value; return this; }
        private static Class responseType = ThrowTypeResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/api/acsprofiles/{profileId}")
    // @Route(Path="/api/acsprofiles", Verbs="POST,PUT,PATCH,DELETE")
    public static class ACSProfile implements IReturn<acsprofileResponse>
    {
        public String profileId = null;
        @Required()
        @StringLength(20)
        public String shortName = null;

        @StringLength(60)
        public String longName = null;

        @StringLength(20)
        public String regionId = null;

        @StringLength(20)
        public String groupId = null;

        @StringLength(12)
        public String deviceID = null;

        public Date lastUpdated = null;
        public Boolean enabled = null;

        public String getProfileId() { return profileId; }
        public ACSProfile setProfileId(String value) { this.profileId = value; return this; }
        public String getShortName() { return shortName; }
        public ACSProfile setShortName(String value) { this.shortName = value; return this; }
        public String getLongName() { return longName; }
        public ACSProfile setLongName(String value) { this.longName = value; return this; }
        public String getRegionId() { return regionId; }
        public ACSProfile setRegionId(String value) { this.regionId = value; return this; }
        public String getGroupId() { return groupId; }
        public ACSProfile setGroupId(String value) { this.groupId = value; return this; }
        public String getDeviceID() { return deviceID; }
        public ACSProfile setDeviceID(String value) { this.deviceID = value; return this; }
        public Date getLastUpdated() { return lastUpdated; }
        public ACSProfile setLastUpdated(Date value) { this.lastUpdated = value; return this; }
        public Boolean isEnabled() { return enabled; }
        public ACSProfile setEnabled(Boolean value) { this.enabled = value; return this; }
        private static Class responseType = acsprofileResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class TestMiniverView
    {

    }

    public static class QueryRockstarsConventions extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public ArrayList<Integer> ids = null;
        public Integer ageOlderThan = null;
        public Integer ageGreaterThanOrEqualTo = null;
        public Integer ageGreaterThan = null;
        public Integer greaterThanAge = null;
        public String firstNameStartsWith = null;
        public String lastNameEndsWith = null;
        public String lastNameContains = null;
        public String rockstarAlbumNameContains = null;
        public Integer rockstarIdAfter = null;
        public Integer rockstarIdOnOrAfter = null;

        public ArrayList<Integer> getIds() { return ids; }
        public QueryRockstarsConventions setIds(ArrayList<Integer> value) { this.ids = value; return this; }
        public Integer getAgeOlderThan() { return ageOlderThan; }
        public QueryRockstarsConventions setAgeOlderThan(Integer value) { this.ageOlderThan = value; return this; }
        public Integer getAgeGreaterThanOrEqualTo() { return ageGreaterThanOrEqualTo; }
        public QueryRockstarsConventions setAgeGreaterThanOrEqualTo(Integer value) { this.ageGreaterThanOrEqualTo = value; return this; }
        public Integer getAgeGreaterThan() { return ageGreaterThan; }
        public QueryRockstarsConventions setAgeGreaterThan(Integer value) { this.ageGreaterThan = value; return this; }
        public Integer getGreaterThanAge() { return greaterThanAge; }
        public QueryRockstarsConventions setGreaterThanAge(Integer value) { this.greaterThanAge = value; return this; }
        public String getFirstNameStartsWith() { return firstNameStartsWith; }
        public QueryRockstarsConventions setFirstNameStartsWith(String value) { this.firstNameStartsWith = value; return this; }
        public String getLastNameEndsWith() { return lastNameEndsWith; }
        public QueryRockstarsConventions setLastNameEndsWith(String value) { this.lastNameEndsWith = value; return this; }
        public String getLastNameContains() { return lastNameContains; }
        public QueryRockstarsConventions setLastNameContains(String value) { this.lastNameContains = value; return this; }
        public String getRockstarAlbumNameContains() { return rockstarAlbumNameContains; }
        public QueryRockstarsConventions setRockstarAlbumNameContains(String value) { this.rockstarAlbumNameContains = value; return this; }
        public Integer getRockstarIdAfter() { return rockstarIdAfter; }
        public QueryRockstarsConventions setRockstarIdAfter(Integer value) { this.rockstarIdAfter = value; return this; }
        public Integer getRockstarIdOnOrAfter() { return rockstarIdOnOrAfter; }
        public QueryRockstarsConventions setRockstarIdOnOrAfter(Integer value) { this.rockstarIdOnOrAfter = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    @AutoQueryViewer(Title="Search for Rockstars", Description="Use this option to search for Rockstars!")
    public static class QueryCustomRockstars extends QueryBase_2<Rockstar, CustomRockstar> implements IReturn<QueryResponse<CustomRockstar>>
    {
        public Integer age = null;

        public Integer getAge() { return age; }
        public QueryCustomRockstars setAge(Integer value) { this.age = value; return this; }
        private static Class responseType = new QueryResponse<CustomRockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    @Route("/customrockstars")
    public static class QueryRockstarAlbums extends QueryBase_2<Rockstar, CustomRockstar> implements IReturn<QueryResponse<CustomRockstar>>
    {
        public Integer age = null;
        public String rockstarAlbumName = null;

        public Integer getAge() { return age; }
        public QueryRockstarAlbums setAge(Integer value) { this.age = value; return this; }
        public String getRockstarAlbumName() { return rockstarAlbumName; }
        public QueryRockstarAlbums setRockstarAlbumName(String value) { this.rockstarAlbumName = value; return this; }
        private static Class responseType = new QueryResponse<CustomRockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryRockstarAlbumsImplicit extends QueryBase_2<Rockstar, CustomRockstar> implements IReturn<QueryResponse<CustomRockstar>>
    {

        private static Class responseType = new QueryResponse<CustomRockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryRockstarAlbumsLeftJoin extends QueryBase_2<Rockstar, CustomRockstar> implements IReturn<QueryResponse<CustomRockstar>>
    {
        public Integer age = null;
        public String albumName = null;

        public Integer getAge() { return age; }
        public QueryRockstarAlbumsLeftJoin setAge(Integer value) { this.age = value; return this; }
        public String getAlbumName() { return albumName; }
        public QueryRockstarAlbumsLeftJoin setAlbumName(String value) { this.albumName = value; return this; }
        private static Class responseType = new QueryResponse<CustomRockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryOverridedRockstars extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public Integer age = null;

        public Integer getAge() { return age; }
        public QueryOverridedRockstars setAge(Integer value) { this.age = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryOverridedCustomRockstars extends QueryBase_2<Rockstar, CustomRockstar> implements IReturn<QueryResponse<CustomRockstar>>
    {
        public Integer age = null;

        public Integer getAge() { return age; }
        public QueryOverridedCustomRockstars setAge(Integer value) { this.age = value; return this; }
        private static Class responseType = new QueryResponse<CustomRockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryFieldRockstars extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public String firstName = null;
        public ArrayList<String> firstNames = null;
        public Integer age = null;
        public String firstNameCaseInsensitive = null;
        public String firstNameStartsWith = null;
        public String lastNameEndsWith = null;
        public ArrayList<String> firstNameBetween = null;
        public String orLastName = null;

        public String getFirstName() { return firstName; }
        public QueryFieldRockstars setFirstName(String value) { this.firstName = value; return this; }
        public ArrayList<String> getFirstNames() { return firstNames; }
        public QueryFieldRockstars setFirstNames(ArrayList<String> value) { this.firstNames = value; return this; }
        public Integer getAge() { return age; }
        public QueryFieldRockstars setAge(Integer value) { this.age = value; return this; }
        public String getFirstNameCaseInsensitive() { return firstNameCaseInsensitive; }
        public QueryFieldRockstars setFirstNameCaseInsensitive(String value) { this.firstNameCaseInsensitive = value; return this; }
        public String getFirstNameStartsWith() { return firstNameStartsWith; }
        public QueryFieldRockstars setFirstNameStartsWith(String value) { this.firstNameStartsWith = value; return this; }
        public String getLastNameEndsWith() { return lastNameEndsWith; }
        public QueryFieldRockstars setLastNameEndsWith(String value) { this.lastNameEndsWith = value; return this; }
        public ArrayList<String> getFirstNameBetween() { return firstNameBetween; }
        public QueryFieldRockstars setFirstNameBetween(ArrayList<String> value) { this.firstNameBetween = value; return this; }
        public String getOrLastName() { return orLastName; }
        public QueryFieldRockstars setOrLastName(String value) { this.orLastName = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryFieldRockstarsDynamic extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public Integer age = null;

        public Integer getAge() { return age; }
        public QueryFieldRockstarsDynamic setAge(Integer value) { this.age = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryRockstarsFilter extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public Integer age = null;

        public Integer getAge() { return age; }
        public QueryRockstarsFilter setAge(Integer value) { this.age = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryCustomRockstarsFilter extends QueryBase_2<Rockstar, CustomRockstar> implements IReturn<QueryResponse<CustomRockstar>>
    {
        public Integer age = null;

        public Integer getAge() { return age; }
        public QueryCustomRockstarsFilter setAge(Integer value) { this.age = value; return this; }
        private static Class responseType = new QueryResponse<CustomRockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryRockstarsIFilter extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public Integer age = null;

        public Integer getAge() { return age; }
        public QueryRockstarsIFilter setAge(Integer value) { this.age = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    @Route("/OrRockstars")
    public static class QueryOrRockstars extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public Integer age = null;
        public String firstName = null;

        public Integer getAge() { return age; }
        public QueryOrRockstars setAge(Integer value) { this.age = value; return this; }
        public String getFirstName() { return firstName; }
        public QueryOrRockstars setFirstName(String value) { this.firstName = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryGetRockstars extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public ArrayList<Integer> ids = null;
        public ArrayList<Integer> ages = null;
        public ArrayList<String> firstNames = null;
        public ArrayList<Integer> idsBetween = null;

        public ArrayList<Integer> getIds() { return ids; }
        public QueryGetRockstars setIds(ArrayList<Integer> value) { this.ids = value; return this; }
        public ArrayList<Integer> getAges() { return ages; }
        public QueryGetRockstars setAges(ArrayList<Integer> value) { this.ages = value; return this; }
        public ArrayList<String> getFirstNames() { return firstNames; }
        public QueryGetRockstars setFirstNames(ArrayList<String> value) { this.firstNames = value; return this; }
        public ArrayList<Integer> getIdsBetween() { return idsBetween; }
        public QueryGetRockstars setIdsBetween(ArrayList<Integer> value) { this.idsBetween = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryGetRockstarsDynamic extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {

        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    @Route("/movies/search")
    public static class SearchMovies extends QueryBase_1<Movie> implements IReturn<QueryResponse<Movie>>
    {

        private static Class responseType = new QueryResponse<Movie>().getClass();
        public Class getResponseType() { return responseType; }
    }

    @Route("/movies")
    public static class QueryMovies extends QueryBase_1<Movie> implements IReturn<QueryResponse<Movie>>
    {
        public ArrayList<Integer> ids = null;
        public ArrayList<String> imdbIds = null;
        public ArrayList<String> ratings = null;

        public ArrayList<Integer> getIds() { return ids; }
        public QueryMovies setIds(ArrayList<Integer> value) { this.ids = value; return this; }
        public ArrayList<String> getImdbIds() { return imdbIds; }
        public QueryMovies setImdbIds(ArrayList<String> value) { this.imdbIds = value; return this; }
        public ArrayList<String> getRatings() { return ratings; }
        public QueryMovies setRatings(ArrayList<String> value) { this.ratings = value; return this; }
        private static Class responseType = new QueryResponse<Movie>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class StreamMovies extends QueryBase_1<Movie> implements IReturn<QueryResponse<Movie>>
    {
        public ArrayList<String> ratings = null;

        public ArrayList<String> getRatings() { return ratings; }
        public StreamMovies setRatings(ArrayList<String> value) { this.ratings = value; return this; }
        private static Class responseType = new QueryResponse<Movie>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class QueryUnknownRockstars extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public Integer unknownInt = null;
        public String unknownProperty = null;

        public Integer getUnknownInt() { return unknownInt; }
        public QueryUnknownRockstars setUnknownInt(Integer value) { this.unknownInt = value; return this; }
        public String getUnknownProperty() { return unknownProperty; }
        public QueryUnknownRockstars setUnknownProperty(String value) { this.unknownProperty = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    @Route("/query/rockstar-references")
    public static class QueryRockstarsWithReferences extends QueryBase_1<RockstarReference> implements IReturn<QueryResponse<RockstarReference>>
    {
        public Integer age = null;

        public Integer getAge() { return age; }
        public QueryRockstarsWithReferences setAge(Integer value) { this.age = value; return this; }
        private static Class responseType = new QueryResponse<RockstarReference>().getClass();
        public Class getResponseType() { return responseType; }
    }

}
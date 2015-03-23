/* Options:
Date: 2015-03-21 19:28:31
Version: 1
BaseUrl: http://localhost:2020

Package: servicestack.net.client.tests
//GlobalNamespace: dto
//AddPropertyAccessors: True
//SettersReturnThis: True
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion: 
//IncludeTypes: 
//ExcludeTypes: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.*
*/

package servicestack.net.client.tests;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;

public class dto
{

    public static class QueryBase_1<T> extends QueryBase
    {

    }

    public static class Rockstar
    {
        public Integer Id = null;
        public String FirstName = null;
        public String LastName = null;
        public Integer Age = null;

        public Integer getId() { return Id; }
        public Rockstar setId(Integer value) { this.Id = value; return this; }
        public String getFirstName() { return FirstName; }
        public Rockstar setFirstName(String value) { this.FirstName = value; return this; }
        public String getLastName() { return LastName; }
        public Rockstar setLastName(String value) { this.LastName = value; return this; }
        public Integer getAge() { return Age; }
        public Rockstar setAge(Integer value) { this.Age = value; return this; }
    }

    @DataContract
    public static class ResponseStatus
    {
        @DataMember(Order=1)
        public String ErrorCode = null;

        @DataMember(Order=2)
        public String Message = null;

        @DataMember(Order=3)
        public String StackTrace = null;

        @DataMember(Order=4)
        public ArrayList<ResponseError> Errors = null;

        public String getErrorCode() { return ErrorCode; }
        public ResponseStatus setErrorCode(String value) { this.ErrorCode = value; return this; }
        public String getMessage() { return Message; }
        public ResponseStatus setMessage(String value) { this.Message = value; return this; }
        public String getStackTrace() { return StackTrace; }
        public ResponseStatus setStackTrace(String value) { this.StackTrace = value; return this; }
        public ArrayList<ResponseError> getErrors() { return Errors; }
        public ResponseStatus setErrors(ArrayList<ResponseError> value) { this.Errors = value; return this; }
    }

    public static class MetadataTestChild
    {
        public String Name = null;
        public ArrayList<MetadataTestNestedChild> Results = null;

        public String getName() { return Name; }
        public MetadataTestChild setName(String value) { this.Name = value; return this; }
        public ArrayList<MetadataTestNestedChild> getResults() { return Results; }
        public MetadataTestChild setResults(ArrayList<MetadataTestNestedChild> value) { this.Results = value; return this; }
    }

    @DataContract
    public static class MenuExample
    {
        @DataMember(Order=1)
        @ApiMember()
        public MenuItemExample MenuItemExample1 = null;

        public MenuItemExample getMenuItemExample1() { return MenuItemExample1; }
        public MenuExample setMenuItemExample1(MenuItemExample value) { this.MenuItemExample1 = value; return this; }
    }

    public static class MetadataType
    {
        public String Name = null;
        public String Namespace = null;
        public ArrayList<String> GenericArgs = null;
        public MetadataTypeName Inherits = null;
        public String DisplayType = null;
        public String Description = null;
        public Boolean ReturnVoidMarker = null;
        public Boolean IsNested = null;
        public Boolean IsEnum = null;
        public Boolean IsInterface = null;
        public Boolean IsAbstract = null;
        public MetadataTypeName ReturnMarkerTypeName = null;
        public ArrayList<MetadataRoute> Routes = null;
        public MetadataDataContract DataContract = null;
        public ArrayList<MetadataPropertyType> Properties = null;
        public ArrayList<MetadataAttribute> Attributes = null;
        public ArrayList<MetadataTypeName> InnerTypes = null;
        public ArrayList<String> EnumNames = null;
        public ArrayList<String> EnumValues = null;

        public String getName() { return Name; }
        public MetadataType setName(String value) { this.Name = value; return this; }
        public String getNamespace() { return Namespace; }
        public MetadataType setNamespace(String value) { this.Namespace = value; return this; }
        public ArrayList<String> getGenericArgs() { return GenericArgs; }
        public MetadataType setGenericArgs(ArrayList<String> value) { this.GenericArgs = value; return this; }
        public MetadataTypeName getInherits() { return Inherits; }
        public MetadataType setInherits(MetadataTypeName value) { this.Inherits = value; return this; }
        public String getDisplayType() { return DisplayType; }
        public MetadataType setDisplayType(String value) { this.DisplayType = value; return this; }
        public String getDescription() { return Description; }
        public MetadataType setDescription(String value) { this.Description = value; return this; }
        public Boolean isReturnVoidMarker() { return ReturnVoidMarker; }
        public MetadataType setReturnVoidMarker(Boolean value) { this.ReturnVoidMarker = value; return this; }
        public Boolean getIsNested() { return IsNested; }
        public MetadataType setIsNested(Boolean value) { this.IsNested = value; return this; }
        public Boolean getIsEnum() { return IsEnum; }
        public MetadataType setIsEnum(Boolean value) { this.IsEnum = value; return this; }
        public Boolean getIsInterface() { return IsInterface; }
        public MetadataType setIsInterface(Boolean value) { this.IsInterface = value; return this; }
        public Boolean getIsAbstract() { return IsAbstract; }
        public MetadataType setIsAbstract(Boolean value) { this.IsAbstract = value; return this; }
        public MetadataTypeName getReturnMarkerTypeName() { return ReturnMarkerTypeName; }
        public MetadataType setReturnMarkerTypeName(MetadataTypeName value) { this.ReturnMarkerTypeName = value; return this; }
        public ArrayList<MetadataRoute> getRoutes() { return Routes; }
        public MetadataType setRoutes(ArrayList<MetadataRoute> value) { this.Routes = value; return this; }
        public MetadataDataContract getDataContract() { return DataContract; }
        public MetadataType setDataContract(MetadataDataContract value) { this.DataContract = value; return this; }
        public ArrayList<MetadataPropertyType> getProperties() { return Properties; }
        public MetadataType setProperties(ArrayList<MetadataPropertyType> value) { this.Properties = value; return this; }
        public ArrayList<MetadataAttribute> getAttributes() { return Attributes; }
        public MetadataType setAttributes(ArrayList<MetadataAttribute> value) { this.Attributes = value; return this; }
        public ArrayList<MetadataTypeName> getInnerTypes() { return InnerTypes; }
        public MetadataType setInnerTypes(ArrayList<MetadataTypeName> value) { this.InnerTypes = value; return this; }
        public ArrayList<String> getEnumNames() { return EnumNames; }
        public MetadataType setEnumNames(ArrayList<String> value) { this.EnumNames = value; return this; }
        public ArrayList<String> getEnumValues() { return EnumValues; }
        public MetadataType setEnumValues(ArrayList<String> value) { this.EnumValues = value; return this; }
    }

    public static class AutoQueryViewerConfig
    {
        public String ServiceBaseUrl = null;
        public String ServiceName = null;
        public String ServiceDescription = null;
        public String ServiceIconUrl = null;
        public Boolean IsPublic = null;
        public Boolean OnlyShowAnnotatedServices = null;
        public ArrayList<Property> ImplicitConventions = null;
        public String DefaultSearchField = null;
        public String DefaultSearchType = null;
        public String DefaultSearchText = null;
        public String BrandUrl = null;
        public String BrandImageUrl = null;
        public String TextColor = null;
        public String LinkColor = null;
        public String BackgroundColor = null;
        public String BackgroundImageUrl = null;
        public String IconUrl = null;

        public String getServiceBaseUrl() { return ServiceBaseUrl; }
        public AutoQueryViewerConfig setServiceBaseUrl(String value) { this.ServiceBaseUrl = value; return this; }
        public String getServiceName() { return ServiceName; }
        public AutoQueryViewerConfig setServiceName(String value) { this.ServiceName = value; return this; }
        public String getServiceDescription() { return ServiceDescription; }
        public AutoQueryViewerConfig setServiceDescription(String value) { this.ServiceDescription = value; return this; }
        public String getServiceIconUrl() { return ServiceIconUrl; }
        public AutoQueryViewerConfig setServiceIconUrl(String value) { this.ServiceIconUrl = value; return this; }
        public Boolean getIsPublic() { return IsPublic; }
        public AutoQueryViewerConfig setIsPublic(Boolean value) { this.IsPublic = value; return this; }
        public Boolean isOnlyShowAnnotatedServices() { return OnlyShowAnnotatedServices; }
        public AutoQueryViewerConfig setOnlyShowAnnotatedServices(Boolean value) { this.OnlyShowAnnotatedServices = value; return this; }
        public ArrayList<Property> getImplicitConventions() { return ImplicitConventions; }
        public AutoQueryViewerConfig setImplicitConventions(ArrayList<Property> value) { this.ImplicitConventions = value; return this; }
        public String getDefaultSearchField() { return DefaultSearchField; }
        public AutoQueryViewerConfig setDefaultSearchField(String value) { this.DefaultSearchField = value; return this; }
        public String getDefaultSearchType() { return DefaultSearchType; }
        public AutoQueryViewerConfig setDefaultSearchType(String value) { this.DefaultSearchType = value; return this; }
        public String getDefaultSearchText() { return DefaultSearchText; }
        public AutoQueryViewerConfig setDefaultSearchText(String value) { this.DefaultSearchText = value; return this; }
        public String getBrandUrl() { return BrandUrl; }
        public AutoQueryViewerConfig setBrandUrl(String value) { this.BrandUrl = value; return this; }
        public String getBrandImageUrl() { return BrandImageUrl; }
        public AutoQueryViewerConfig setBrandImageUrl(String value) { this.BrandImageUrl = value; return this; }
        public String getTextColor() { return TextColor; }
        public AutoQueryViewerConfig setTextColor(String value) { this.TextColor = value; return this; }
        public String getLinkColor() { return LinkColor; }
        public AutoQueryViewerConfig setLinkColor(String value) { this.LinkColor = value; return this; }
        public String getBackgroundColor() { return BackgroundColor; }
        public AutoQueryViewerConfig setBackgroundColor(String value) { this.BackgroundColor = value; return this; }
        public String getBackgroundImageUrl() { return BackgroundImageUrl; }
        public AutoQueryViewerConfig setBackgroundImageUrl(String value) { this.BackgroundImageUrl = value; return this; }
        public String getIconUrl() { return IconUrl; }
        public AutoQueryViewerConfig setIconUrl(String value) { this.IconUrl = value; return this; }
    }

    public static class AutoQueryOperation
    {
        public String Request = null;
        public String From = null;
        public String To = null;

        public String getRequest() { return Request; }
        public AutoQueryOperation setRequest(String value) { this.Request = value; return this; }
        public String getFrom() { return From; }
        public AutoQueryOperation setFrom(String value) { this.From = value; return this; }
        public String getTo() { return To; }
        public AutoQueryOperation setTo(String value) { this.To = value; return this; }
    }

    public static class Issue221Base<T>
    {
        public T Id = null;

        public T getId() { return Id; }
        public Issue221Base<T> setId(T value) { this.Id = value; return this; }
    }

    public static class NativeTypesTestService
    {

    }

    public static class NestedClass
    {
        public String Value = null;

        public String getValue() { return Value; }
        public NestedClass setValue(String value) { this.Value = value; return this; }
    }

    public static class ListResult
    {
        public String Result = null;

        public String getResult() { return Result; }
        public ListResult setResult(String value) { this.Result = value; return this; }
    }

    public static class ArrayResult
    {
        public String Result = null;

        public String getResult() { return Result; }
        public ArrayResult setResult(String value) { this.Result = value; return this; }
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
        public Integer Id = null;
        public Integer NullableId = null;
        public Short Byte = null;
        public Short Short = null;
        public Integer Int = null;
        public Long Long = null;
        public Integer UShort = null;
        public Long UInt = null;
        public BigInteger ULong = null;
        public Float Float = null;
        public Double Double = null;
        public BigDecimal Decimal = null;
        public String String = null;
        public Date DateTime = null;
        public String TimeSpan = null;
        public String DateTimeOffset = null;
        public String Guid = null;
        public Integer Char = null;
        public Date NullableDateTime = null;
        public String NullableTimeSpan = null;
        public ArrayList<String> StringList = null;
        public ArrayList<String> StringArray = null;
        public HashMap<String,String> StringMap = null;
        public HashMap<Integer,String> IntStringMap = null;
        public SubType SubType = null;

        public Integer getId() { return Id; }
        public AllTypes setId(Integer value) { this.Id = value; return this; }
        public Integer getNullableId() { return NullableId; }
        public AllTypes setNullableId(Integer value) { this.NullableId = value; return this; }
        public Short getByte() { return Byte; }
        public AllTypes setByte(Short value) { this.Byte = value; return this; }
        public Short getShort() { return Short; }
        public AllTypes setShort(Short value) { this.Short = value; return this; }
        public Integer getInt() { return Int; }
        public AllTypes setInt(Integer value) { this.Int = value; return this; }
        public Long getLong() { return Long; }
        public AllTypes setLong(Long value) { this.Long = value; return this; }
        public Integer getUShort() { return UShort; }
        public AllTypes setUShort(Integer value) { this.UShort = value; return this; }
        public Long getUInt() { return UInt; }
        public AllTypes setUInt(Long value) { this.UInt = value; return this; }
        public BigInteger getULong() { return ULong; }
        public AllTypes setULong(BigInteger value) { this.ULong = value; return this; }
        public Float getFloat() { return Float; }
        public AllTypes setFloat(Float value) { this.Float = value; return this; }
        public Double getDouble() { return Double; }
        public AllTypes setDouble(Double value) { this.Double = value; return this; }
        public BigDecimal getDecimal() { return Decimal; }
        public AllTypes setDecimal(BigDecimal value) { this.Decimal = value; return this; }
        public String getString() { return String; }
        public AllTypes setString(String value) { this.String = value; return this; }
        public Date getDateTime() { return DateTime; }
        public AllTypes setDateTime(Date value) { this.DateTime = value; return this; }
        public String getTimeSpan() { return TimeSpan; }
        public AllTypes setTimeSpan(String value) { this.TimeSpan = value; return this; }
        public String getDateTimeOffset() { return DateTimeOffset; }
        public AllTypes setDateTimeOffset(String value) { this.DateTimeOffset = value; return this; }
        public String getGuid() { return Guid; }
        public AllTypes setGuid(String value) { this.Guid = value; return this; }
        public Integer getChar() { return Char; }
        public AllTypes setChar(Integer value) { this.Char = value; return this; }
        public Date getNullableDateTime() { return NullableDateTime; }
        public AllTypes setNullableDateTime(Date value) { this.NullableDateTime = value; return this; }
        public String getNullableTimeSpan() { return NullableTimeSpan; }
        public AllTypes setNullableTimeSpan(String value) { this.NullableTimeSpan = value; return this; }
        public ArrayList<String> getStringList() { return StringList; }
        public AllTypes setStringList(ArrayList<String> value) { this.StringList = value; return this; }
        public ArrayList<String> getStringArray() { return StringArray; }
        public AllTypes setStringArray(ArrayList<String> value) { this.StringArray = value; return this; }
        public HashMap<String,String> getStringMap() { return StringMap; }
        public AllTypes setStringMap(HashMap<String,String> value) { this.StringMap = value; return this; }
        public HashMap<Integer,String> getIntStringMap() { return IntStringMap; }
        public AllTypes setIntStringMap(HashMap<Integer,String> value) { this.IntStringMap = value; return this; }
        public SubType getSubType() { return SubType; }
        public AllTypes setSubType(SubType value) { this.SubType = value; return this; }
    }

    public static class AllCollectionTypes
    {
        public ArrayList<Integer> IntArray = null;
        public ArrayList<Integer> IntList = null;
        public ArrayList<String> StringArray = null;
        public ArrayList<String> StringList = null;
        public ArrayList<Poco> PocoArray = null;
        public ArrayList<Poco> PocoList = null;
        public HashMap<String,ArrayList<Poco>> PocoLookup = null;
        public HashMap<String,ArrayList<HashMap<String,Poco>>> PocoLookupMap = null;

        public ArrayList<Integer> getIntArray() { return IntArray; }
        public AllCollectionTypes setIntArray(ArrayList<Integer> value) { this.IntArray = value; return this; }
        public ArrayList<Integer> getIntList() { return IntList; }
        public AllCollectionTypes setIntList(ArrayList<Integer> value) { this.IntList = value; return this; }
        public ArrayList<String> getStringArray() { return StringArray; }
        public AllCollectionTypes setStringArray(ArrayList<String> value) { this.StringArray = value; return this; }
        public ArrayList<String> getStringList() { return StringList; }
        public AllCollectionTypes setStringList(ArrayList<String> value) { this.StringList = value; return this; }
        public ArrayList<Poco> getPocoArray() { return PocoArray; }
        public AllCollectionTypes setPocoArray(ArrayList<Poco> value) { this.PocoArray = value; return this; }
        public ArrayList<Poco> getPocoList() { return PocoList; }
        public AllCollectionTypes setPocoList(ArrayList<Poco> value) { this.PocoList = value; return this; }
        public HashMap<String,ArrayList<Poco>> getPocoLookup() { return PocoLookup; }
        public AllCollectionTypes setPocoLookup(HashMap<String,ArrayList<Poco>> value) { this.PocoLookup = value; return this; }
        public HashMap<String,ArrayList<HashMap<String,Poco>>> getPocoLookupMap() { return PocoLookupMap; }
        public AllCollectionTypes setPocoLookupMap(HashMap<String,ArrayList<HashMap<String,Poco>>> value) { this.PocoLookupMap = value; return this; }
    }

    public static class HelloBase
    {
        public Integer Id = null;

        public Integer getId() { return Id; }
        public HelloBase setId(Integer value) { this.Id = value; return this; }
    }

    public static class HelloResponseBase
    {
        public Integer RefId = null;

        public Integer getRefId() { return RefId; }
        public HelloResponseBase setRefId(Integer value) { this.RefId = value; return this; }
    }

    public static class Poco
    {
        public String Name = null;

        public String getName() { return Name; }
        public Poco setName(String value) { this.Name = value; return this; }
    }

    public static class HelloBase_1<T>
    {
        public ArrayList<T> Items = null;
        public ArrayList<Integer> Counts = null;

        public ArrayList<T> getItems() { return Items; }
        public HelloBase_1<T> setItems(ArrayList<T> value) { this.Items = value; return this; }
        public ArrayList<Integer> getCounts() { return Counts; }
        public HelloBase_1<T> setCounts(ArrayList<Integer> value) { this.Counts = value; return this; }
    }

    public static class Item
    {
        public String Value = null;

        public String getValue() { return Value; }
        public Item setValue(String value) { this.Value = value; return this; }
    }

    public static class InheritedItem
    {
        public String Name = null;

        public String getName() { return Name; }
        public InheritedItem setName(String value) { this.Name = value; return this; }
    }

    public static class HelloWithReturnResponse
    {
        public String Result = null;

        public String getResult() { return Result; }
        public HelloWithReturnResponse setResult(String value) { this.Result = value; return this; }
    }

    public static class HelloType
    {
        public String Result = null;

        public String getResult() { return Result; }
        public HelloType setResult(String value) { this.Result = value; return this; }
    }

    @DataContract
    public static class AuthUserSession
    {
        @DataMember(Order=1)
        public String ReferrerUrl = null;

        @DataMember(Order=2)
        public String Id = null;

        @DataMember(Order=3)
        public String UserAuthId = null;

        @DataMember(Order=4)
        public String UserAuthName = null;

        @DataMember(Order=5)
        public String UserName = null;

        @DataMember(Order=6)
        public String TwitterUserId = null;

        @DataMember(Order=7)
        public String TwitterScreenName = null;

        @DataMember(Order=8)
        public String FacebookUserId = null;

        @DataMember(Order=9)
        public String FacebookUserName = null;

        @DataMember(Order=10)
        public String FirstName = null;

        @DataMember(Order=11)
        public String LastName = null;

        @DataMember(Order=12)
        public String DisplayName = null;

        @DataMember(Order=13)
        public String Company = null;

        @DataMember(Order=14)
        public String Email = null;

        @DataMember(Order=15)
        public String PrimaryEmail = null;

        @DataMember(Order=16)
        public String PhoneNumber = null;

        @DataMember(Order=17)
        public Date BirthDate = null;

        @DataMember(Order=18)
        public String BirthDateRaw = null;

        @DataMember(Order=19)
        public String Address = null;

        @DataMember(Order=20)
        public String Address2 = null;

        @DataMember(Order=21)
        public String City = null;

        @DataMember(Order=22)
        public String State = null;

        @DataMember(Order=23)
        public String Country = null;

        @DataMember(Order=24)
        public String Culture = null;

        @DataMember(Order=25)
        public String FullName = null;

        @DataMember(Order=26)
        public String Gender = null;

        @DataMember(Order=27)
        public String Language = null;

        @DataMember(Order=28)
        public String MailAddress = null;

        @DataMember(Order=29)
        public String Nickname = null;

        @DataMember(Order=30)
        public String PostalCode = null;

        @DataMember(Order=31)
        public String TimeZone = null;

        @DataMember(Order=32)
        public String RequestTokenSecret = null;

        @DataMember(Order=33)
        public Date CreatedAt = null;

        @DataMember(Order=34)
        public Date LastModified = null;

        @DataMember(Order=35)
        public ArrayList<String> Roles = null;

        @DataMember(Order=36)
        public ArrayList<String> Permissions = null;

        @DataMember(Order=37)
        public Boolean IsAuthenticated = null;

        @DataMember(Order=38)
        public String Sequence = null;

        @DataMember(Order=39)
        public Long Tag = null;

        @DataMember(Order=40)
        public ArrayList<IAuthTokens> ProviderOAuthAccess = null;

        public String getReferrerUrl() { return ReferrerUrl; }
        public AuthUserSession setReferrerUrl(String value) { this.ReferrerUrl = value; return this; }
        public String getId() { return Id; }
        public AuthUserSession setId(String value) { this.Id = value; return this; }
        public String getUserAuthId() { return UserAuthId; }
        public AuthUserSession setUserAuthId(String value) { this.UserAuthId = value; return this; }
        public String getUserAuthName() { return UserAuthName; }
        public AuthUserSession setUserAuthName(String value) { this.UserAuthName = value; return this; }
        public String getUserName() { return UserName; }
        public AuthUserSession setUserName(String value) { this.UserName = value; return this; }
        public String getTwitterUserId() { return TwitterUserId; }
        public AuthUserSession setTwitterUserId(String value) { this.TwitterUserId = value; return this; }
        public String getTwitterScreenName() { return TwitterScreenName; }
        public AuthUserSession setTwitterScreenName(String value) { this.TwitterScreenName = value; return this; }
        public String getFacebookUserId() { return FacebookUserId; }
        public AuthUserSession setFacebookUserId(String value) { this.FacebookUserId = value; return this; }
        public String getFacebookUserName() { return FacebookUserName; }
        public AuthUserSession setFacebookUserName(String value) { this.FacebookUserName = value; return this; }
        public String getFirstName() { return FirstName; }
        public AuthUserSession setFirstName(String value) { this.FirstName = value; return this; }
        public String getLastName() { return LastName; }
        public AuthUserSession setLastName(String value) { this.LastName = value; return this; }
        public String getDisplayName() { return DisplayName; }
        public AuthUserSession setDisplayName(String value) { this.DisplayName = value; return this; }
        public String getCompany() { return Company; }
        public AuthUserSession setCompany(String value) { this.Company = value; return this; }
        public String getEmail() { return Email; }
        public AuthUserSession setEmail(String value) { this.Email = value; return this; }
        public String getPrimaryEmail() { return PrimaryEmail; }
        public AuthUserSession setPrimaryEmail(String value) { this.PrimaryEmail = value; return this; }
        public String getPhoneNumber() { return PhoneNumber; }
        public AuthUserSession setPhoneNumber(String value) { this.PhoneNumber = value; return this; }
        public Date getBirthDate() { return BirthDate; }
        public AuthUserSession setBirthDate(Date value) { this.BirthDate = value; return this; }
        public String getBirthDateRaw() { return BirthDateRaw; }
        public AuthUserSession setBirthDateRaw(String value) { this.BirthDateRaw = value; return this; }
        public String getAddress() { return Address; }
        public AuthUserSession setAddress(String value) { this.Address = value; return this; }
        public String getAddress2() { return Address2; }
        public AuthUserSession setAddress2(String value) { this.Address2 = value; return this; }
        public String getCity() { return City; }
        public AuthUserSession setCity(String value) { this.City = value; return this; }
        public String getState() { return State; }
        public AuthUserSession setState(String value) { this.State = value; return this; }
        public String getCountry() { return Country; }
        public AuthUserSession setCountry(String value) { this.Country = value; return this; }
        public String getCulture() { return Culture; }
        public AuthUserSession setCulture(String value) { this.Culture = value; return this; }
        public String getFullName() { return FullName; }
        public AuthUserSession setFullName(String value) { this.FullName = value; return this; }
        public String getGender() { return Gender; }
        public AuthUserSession setGender(String value) { this.Gender = value; return this; }
        public String getLanguage() { return Language; }
        public AuthUserSession setLanguage(String value) { this.Language = value; return this; }
        public String getMailAddress() { return MailAddress; }
        public AuthUserSession setMailAddress(String value) { this.MailAddress = value; return this; }
        public String getNickname() { return Nickname; }
        public AuthUserSession setNickname(String value) { this.Nickname = value; return this; }
        public String getPostalCode() { return PostalCode; }
        public AuthUserSession setPostalCode(String value) { this.PostalCode = value; return this; }
        public String getTimeZone() { return TimeZone; }
        public AuthUserSession setTimeZone(String value) { this.TimeZone = value; return this; }
        public String getRequestTokenSecret() { return RequestTokenSecret; }
        public AuthUserSession setRequestTokenSecret(String value) { this.RequestTokenSecret = value; return this; }
        public Date getCreatedAt() { return CreatedAt; }
        public AuthUserSession setCreatedAt(Date value) { this.CreatedAt = value; return this; }
        public Date getLastModified() { return LastModified; }
        public AuthUserSession setLastModified(Date value) { this.LastModified = value; return this; }
        public ArrayList<String> getRoles() { return Roles; }
        public AuthUserSession setRoles(ArrayList<String> value) { this.Roles = value; return this; }
        public ArrayList<String> getPermissions() { return Permissions; }
        public AuthUserSession setPermissions(ArrayList<String> value) { this.Permissions = value; return this; }
        public Boolean getIsAuthenticated() { return IsAuthenticated; }
        public AuthUserSession setIsAuthenticated(Boolean value) { this.IsAuthenticated = value; return this; }
        public String getSequence() { return Sequence; }
        public AuthUserSession setSequence(String value) { this.Sequence = value; return this; }
        public Long getTag() { return Tag; }
        public AuthUserSession setTag(Long value) { this.Tag = value; return this; }
        public ArrayList<IAuthTokens> getProviderOAuthAccess() { return ProviderOAuthAccess; }
        public AuthUserSession setProviderOAuthAccess(ArrayList<IAuthTokens> value) { this.ProviderOAuthAccess = value; return this; }
    }

    public static interface IPoco
    {
        public String Name = null;
    }

    public static interface IEmptyInterface
    {
    }

    public static class EmptyClass
    {

    }

    public static class TypeA
    {
        public ArrayList<TypeB> Bar = null;

        public ArrayList<TypeB> getBar() { return Bar; }
        public TypeA setBar(ArrayList<TypeB> value) { this.Bar = value; return this; }
    }

    public static class InnerType
    {
        public Long Id = null;
        public String Name = null;

        public Long getId() { return Id; }
        public InnerType setId(Long value) { this.Id = value; return this; }
        public String getName() { return Name; }
        public InnerType setName(String value) { this.Name = value; return this; }
    }

    public static enum InnerEnum
    {
        Foo,
        Bar,
        Baz;
    }

    public static interface IAuthTokens
    {
        public String Provider = null;
        public String UserId = null;
        public String AccessToken = null;
        public String AccessTokenSecret = null;
        public String RefreshToken = null;
        public Date RefreshTokenExpiry = null;
        public String RequestToken = null;
        public String RequestTokenSecret = null;
        public HashMap<String,String> Items = null;
    }

    public static class QueryBase
    {
        @DataMember(Order=1)
        public Integer Skip = null;

        @DataMember(Order=2)
        public Integer Take = null;

        @DataMember(Order=3)
        public String OrderBy = null;

        @DataMember(Order=4)
        public String OrderByDesc = null;

        public Integer getSkip() { return Skip; }
        public QueryBase setSkip(Integer value) { this.Skip = value; return this; }
        public Integer getTake() { return Take; }
        public QueryBase setTake(Integer value) { this.Take = value; return this; }
        public String getOrderBy() { return OrderBy; }
        public QueryBase setOrderBy(String value) { this.OrderBy = value; return this; }
        public String getOrderByDesc() { return OrderByDesc; }
        public QueryBase setOrderByDesc(String value) { this.OrderByDesc = value; return this; }
    }

    @DataContract
    public static class ResponseError
    {
        @DataMember(Order=1, EmitDefaultValue=false)
        public String ErrorCode = null;

        @DataMember(Order=2, EmitDefaultValue=false)
        public String FieldName = null;

        @DataMember(Order=3, EmitDefaultValue=false)
        public String Message = null;

        public String getErrorCode() { return ErrorCode; }
        public ResponseError setErrorCode(String value) { this.ErrorCode = value; return this; }
        public String getFieldName() { return FieldName; }
        public ResponseError setFieldName(String value) { this.FieldName = value; return this; }
        public String getMessage() { return Message; }
        public ResponseError setMessage(String value) { this.Message = value; return this; }
    }

    public static class MetadataTestNestedChild
    {
        public String Name = null;

        public String getName() { return Name; }
        public MetadataTestNestedChild setName(String value) { this.Name = value; return this; }
    }

    public static class MenuItemExample
    {
        @DataMember(Order=1)
        @ApiMember()
        public String Name1 = null;

        public MenuItemExampleItem MenuItemExampleItem = null;

        public String getName1() { return Name1; }
        public MenuItemExample setName1(String value) { this.Name1 = value; return this; }
        public MenuItemExampleItem getMenuItemExampleItem() { return MenuItemExampleItem; }
        public MenuItemExample setMenuItemExampleItem(MenuItemExampleItem value) { this.MenuItemExampleItem = value; return this; }
    }

    public static class MetadataTypeName
    {
        public String Name = null;
        public String Namespace = null;
        public ArrayList<String> GenericArgs = null;

        public String getName() { return Name; }
        public MetadataTypeName setName(String value) { this.Name = value; return this; }
        public String getNamespace() { return Namespace; }
        public MetadataTypeName setNamespace(String value) { this.Namespace = value; return this; }
        public ArrayList<String> getGenericArgs() { return GenericArgs; }
        public MetadataTypeName setGenericArgs(ArrayList<String> value) { this.GenericArgs = value; return this; }
    }

    public static class MetadataRoute
    {
        public String Path = null;
        public String Verbs = null;
        public String Notes = null;
        public String Summary = null;

        public String getPath() { return Path; }
        public MetadataRoute setPath(String value) { this.Path = value; return this; }
        public String getVerbs() { return Verbs; }
        public MetadataRoute setVerbs(String value) { this.Verbs = value; return this; }
        public String getNotes() { return Notes; }
        public MetadataRoute setNotes(String value) { this.Notes = value; return this; }
        public String getSummary() { return Summary; }
        public MetadataRoute setSummary(String value) { this.Summary = value; return this; }
    }

    public static class MetadataDataContract
    {
        public String Name = null;
        public String Namespace = null;

        public String getName() { return Name; }
        public MetadataDataContract setName(String value) { this.Name = value; return this; }
        public String getNamespace() { return Namespace; }
        public MetadataDataContract setNamespace(String value) { this.Namespace = value; return this; }
    }

    public static class MetadataPropertyType
    {
        public String Name = null;
        public String Type = null;
        public Boolean IsValueType = null;
        public String TypeNamespace = null;
        public ArrayList<String> GenericArgs = null;
        public String Value = null;
        public String Description = null;
        public MetadataDataMember DataMember = null;
        public Boolean ReadOnly = null;
        public String ParamType = null;
        public String DisplayType = null;
        public Boolean IsRequired = null;
        public ArrayList<String> AllowableValues = null;
        public Integer AllowableMin = null;
        public Integer AllowableMax = null;
        public ArrayList<MetadataAttribute> Attributes = null;

        public String getName() { return Name; }
        public MetadataPropertyType setName(String value) { this.Name = value; return this; }
        public String getType() { return Type; }
        public MetadataPropertyType setType(String value) { this.Type = value; return this; }
        public Boolean getIsValueType() { return IsValueType; }
        public MetadataPropertyType setIsValueType(Boolean value) { this.IsValueType = value; return this; }
        public String getTypeNamespace() { return TypeNamespace; }
        public MetadataPropertyType setTypeNamespace(String value) { this.TypeNamespace = value; return this; }
        public ArrayList<String> getGenericArgs() { return GenericArgs; }
        public MetadataPropertyType setGenericArgs(ArrayList<String> value) { this.GenericArgs = value; return this; }
        public String getValue() { return Value; }
        public MetadataPropertyType setValue(String value) { this.Value = value; return this; }
        public String getDescription() { return Description; }
        public MetadataPropertyType setDescription(String value) { this.Description = value; return this; }
        public MetadataDataMember getDataMember() { return DataMember; }
        public MetadataPropertyType setDataMember(MetadataDataMember value) { this.DataMember = value; return this; }
        public Boolean isReadOnly() { return ReadOnly; }
        public MetadataPropertyType setReadOnly(Boolean value) { this.ReadOnly = value; return this; }
        public String getParamType() { return ParamType; }
        public MetadataPropertyType setParamType(String value) { this.ParamType = value; return this; }
        public String getDisplayType() { return DisplayType; }
        public MetadataPropertyType setDisplayType(String value) { this.DisplayType = value; return this; }
        public Boolean getIsRequired() { return IsRequired; }
        public MetadataPropertyType setIsRequired(Boolean value) { this.IsRequired = value; return this; }
        public ArrayList<String> getAllowableValues() { return AllowableValues; }
        public MetadataPropertyType setAllowableValues(ArrayList<String> value) { this.AllowableValues = value; return this; }
        public Integer getAllowableMin() { return AllowableMin; }
        public MetadataPropertyType setAllowableMin(Integer value) { this.AllowableMin = value; return this; }
        public Integer getAllowableMax() { return AllowableMax; }
        public MetadataPropertyType setAllowableMax(Integer value) { this.AllowableMax = value; return this; }
        public ArrayList<MetadataAttribute> getAttributes() { return Attributes; }
        public MetadataPropertyType setAttributes(ArrayList<MetadataAttribute> value) { this.Attributes = value; return this; }
    }

    public static class MetadataAttribute
    {
        public String Name = null;
        public ArrayList<MetadataPropertyType> ConstructorArgs = null;
        public ArrayList<MetadataPropertyType> Args = null;

        public String getName() { return Name; }
        public MetadataAttribute setName(String value) { this.Name = value; return this; }
        public ArrayList<MetadataPropertyType> getConstructorArgs() { return ConstructorArgs; }
        public MetadataAttribute setConstructorArgs(ArrayList<MetadataPropertyType> value) { this.ConstructorArgs = value; return this; }
        public ArrayList<MetadataPropertyType> getArgs() { return Args; }
        public MetadataAttribute setArgs(ArrayList<MetadataPropertyType> value) { this.Args = value; return this; }
    }

    @DataContract
    public static class Property
    {
        @DataMember
        public String Name = null;

        @DataMember
        public String Value = null;

        public String getName() { return Name; }
        public Property setName(String value) { this.Name = value; return this; }
        public String getValue() { return Value; }
        public Property setValue(String value) { this.Value = value; return this; }
    }

    public static class SubType
    {
        public Integer Id = null;
        public String Name = null;

        public Integer getId() { return Id; }
        public SubType setId(Integer value) { this.Id = value; return this; }
        public String getName() { return Name; }
        public SubType setName(String value) { this.Name = value; return this; }
    }

    public static class TypeB
    {
        public String Foo = null;

        public String getFoo() { return Foo; }
        public TypeB setFoo(String value) { this.Foo = value; return this; }
    }

    public static class TypesGroup
    {

    }

    public static class MenuItemExampleItem
    {
        @DataMember(Order=1)
        @ApiMember()
        public String Name1 = null;

        public String getName1() { return Name1; }
        public MenuItemExampleItem setName1(String value) { this.Name1 = value; return this; }
    }

    public static class MetadataDataMember
    {
        public String Name = null;
        public Integer Order = null;
        public Boolean IsRequired = null;
        public Boolean EmitDefaultValue = null;

        public String getName() { return Name; }
        public MetadataDataMember setName(String value) { this.Name = value; return this; }
        public Integer getOrder() { return Order; }
        public MetadataDataMember setOrder(Integer value) { this.Order = value; return this; }
        public Boolean getIsRequired() { return IsRequired; }
        public MetadataDataMember setIsRequired(Boolean value) { this.IsRequired = value; return this; }
        public Boolean isEmitDefaultValue() { return EmitDefaultValue; }
        public MetadataDataMember setEmitDefaultValue(Boolean value) { this.EmitDefaultValue = value; return this; }
    }

    @DataContract
    public static class QueryResponse<Rockstar>
    {
        @DataMember(Order=1)
        public Integer Offset = null;

        @DataMember(Order=2)
        public Integer Total = null;

        @DataMember(Order=3)
        public ArrayList<Rockstar> Results = null;

        @DataMember(Order=4)
        public HashMap<String,String> Meta = null;

        @DataMember(Order=5)
        public ResponseStatus ResponseStatus = null;

        public Integer getOffset() { return Offset; }
        public QueryResponse<Rockstar> setOffset(Integer value) { this.Offset = value; return this; }
        public Integer getTotal() { return Total; }
        public QueryResponse<Rockstar> setTotal(Integer value) { this.Total = value; return this; }
        public ArrayList<Rockstar> getResults() { return Results; }
        public QueryResponse<Rockstar> setResults(ArrayList<Rockstar> value) { this.Results = value; return this; }
        public HashMap<String,String> getMeta() { return Meta; }
        public QueryResponse<Rockstar> setMeta(HashMap<String,String> value) { this.Meta = value; return this; }
        public ResponseStatus getResponseStatus() { return ResponseStatus; }
        public QueryResponse<Rockstar> setResponseStatus(ResponseStatus value) { this.ResponseStatus = value; return this; }
    }

    public static class ChangeRequestResponse
    {
        public String ContentType = null;
        public String Header = null;
        public String QueryString = null;
        public String Form = null;
        public ResponseStatus ResponseStatus = null;

        public String getContentType() { return ContentType; }
        public ChangeRequestResponse setContentType(String value) { this.ContentType = value; return this; }
        public String getHeader() { return Header; }
        public ChangeRequestResponse setHeader(String value) { this.Header = value; return this; }
        public String getQueryString() { return QueryString; }
        public ChangeRequestResponse setQueryString(String value) { this.QueryString = value; return this; }
        public String getForm() { return Form; }
        public ChangeRequestResponse setForm(String value) { this.Form = value; return this; }
        public ResponseStatus getResponseStatus() { return ResponseStatus; }
        public ChangeRequestResponse setResponseStatus(ResponseStatus value) { this.ResponseStatus = value; return this; }
    }

    public static class CustomHttpErrorResponse
    {
        public String Custom = null;
        public ResponseStatus ResponseStatus = null;

        public String getCustom() { return Custom; }
        public CustomHttpErrorResponse setCustom(String value) { this.Custom = value; return this; }
        public ResponseStatus getResponseStatus() { return ResponseStatus; }
        public CustomHttpErrorResponse setResponseStatus(ResponseStatus value) { this.ResponseStatus = value; return this; }
    }

    public static class CustomFieldHttpErrorResponse
    {
        public String Custom = null;
        public ResponseStatus ResponseStatus = null;

        public String getCustom() { return Custom; }
        public CustomFieldHttpErrorResponse setCustom(String value) { this.Custom = value; return this; }
        public ResponseStatus getResponseStatus() { return ResponseStatus; }
        public CustomFieldHttpErrorResponse setResponseStatus(ResponseStatus value) { this.ResponseStatus = value; return this; }
    }

    public static class NoRepeatResponse
    {
        public String Id = null;

        public String getId() { return Id; }
        public NoRepeatResponse setId(String value) { this.Id = value; return this; }
    }

    public static class BatchThrowsResponse
    {
        public String Result = null;
        public ResponseStatus ResponseStatus = null;

        public String getResult() { return Result; }
        public BatchThrowsResponse setResult(String value) { this.Result = value; return this; }
        public ResponseStatus getResponseStatus() { return ResponseStatus; }
        public BatchThrowsResponse setResponseStatus(ResponseStatus value) { this.ResponseStatus = value; return this; }
    }

    public static class MetadataTestResponse
    {
        public Integer Id = null;
        public ArrayList<MetadataTestChild> Results = null;

        public Integer getId() { return Id; }
        public MetadataTestResponse setId(Integer value) { this.Id = value; return this; }
        public ArrayList<MetadataTestChild> getResults() { return Results; }
        public MetadataTestResponse setResults(ArrayList<MetadataTestChild> value) { this.Results = value; return this; }
    }

    @DataContract
    public static class GetExampleResponse
    {
        @DataMember(Order=1)
        public ResponseStatus ResponseStatus = null;

        @DataMember(Order=2)
        @ApiMember()
        public MenuExample MenuExample1 = null;

        public ResponseStatus getResponseStatus() { return ResponseStatus; }
        public GetExampleResponse setResponseStatus(ResponseStatus value) { this.ResponseStatus = value; return this; }
        public MenuExample getMenuExample1() { return MenuExample1; }
        public GetExampleResponse setMenuExample1(MenuExample value) { this.MenuExample1 = value; return this; }
    }

    public static class AutoQueryMetadataResponse
    {
        public AutoQueryViewerConfig Config = null;
        public ArrayList<AutoQueryOperation> Operations = null;
        public ArrayList<MetadataType> Types = null;
        public ResponseStatus ResponseStatus = null;

        public AutoQueryViewerConfig getConfig() { return Config; }
        public AutoQueryMetadataResponse setConfig(AutoQueryViewerConfig value) { this.Config = value; return this; }
        public ArrayList<AutoQueryOperation> getOperations() { return Operations; }
        public AutoQueryMetadataResponse setOperations(ArrayList<AutoQueryOperation> value) { this.Operations = value; return this; }
        public ArrayList<MetadataType> getTypes() { return Types; }
        public AutoQueryMetadataResponse setTypes(ArrayList<MetadataType> value) { this.Types = value; return this; }
        public ResponseStatus getResponseStatus() { return ResponseStatus; }
        public AutoQueryMetadataResponse setResponseStatus(ResponseStatus value) { this.ResponseStatus = value; return this; }
    }

    public static class HelloResponse
    {
        public String Result = null;

        public String getResult() { return Result; }
        public HelloResponse setResult(String value) { this.Result = value; return this; }
    }

    /**
     * Description on HelloAllResponse type
     */
    @DataContract
    public static class HelloAnnotatedResponse
    {
        @DataMember
        public String Result = null;

        public String getResult() { return Result; }
        public HelloAnnotatedResponse setResult(String value) { this.Result = value; return this; }
    }

    public static class HelloExistingResponse
    {
        public HelloList HelloList = null;
        public HelloArray HelloArray = null;
        public ArrayList<ArrayResult> ArrayResults = null;
        public ArrayList<ListResult> ListResults = null;

        public HelloList getHelloList() { return HelloList; }
        public HelloExistingResponse setHelloList(HelloList value) { this.HelloList = value; return this; }
        public HelloArray getHelloArray() { return HelloArray; }
        public HelloExistingResponse setHelloArray(HelloArray value) { this.HelloArray = value; return this; }
        public ArrayList<ArrayResult> getArrayResults() { return ArrayResults; }
        public HelloExistingResponse setArrayResults(ArrayList<ArrayResult> value) { this.ArrayResults = value; return this; }
        public ArrayList<ListResult> getListResults() { return ListResults; }
        public HelloExistingResponse setListResults(ArrayList<ListResult> value) { this.ListResults = value; return this; }
    }

    public static class HelloAllTypesResponse
    {
        public String Result = null;
        public AllTypes AllTypes = null;
        public AllCollectionTypes AllCollectionTypes = null;

        public String getResult() { return Result; }
        public HelloAllTypesResponse setResult(String value) { this.Result = value; return this; }
        public AllTypes getAllTypes() { return AllTypes; }
        public HelloAllTypesResponse setAllTypes(AllTypes value) { this.AllTypes = value; return this; }
        public AllCollectionTypes getAllCollectionTypes() { return AllCollectionTypes; }
        public HelloAllTypesResponse setAllCollectionTypes(AllCollectionTypes value) { this.AllCollectionTypes = value; return this; }
    }

    @DataContract
    public static class HelloWithDataContractResponse
    {
        @DataMember(Name="result", Order=1, IsRequired=true, EmitDefaultValue=false)
        public String Result = null;

        public String getResult() { return Result; }
        public HelloWithDataContractResponse setResult(String value) { this.Result = value; return this; }
    }

    /**
     * Description on HelloWithDescriptionResponse type
     */
    public static class HelloWithDescriptionResponse
    {
        public String Result = null;

        public String getResult() { return Result; }
        public HelloWithDescriptionResponse setResult(String value) { this.Result = value; return this; }
    }

    public static class HelloWithInheritanceResponse extends HelloResponseBase
    {
        public String Result = null;

        public String getResult() { return Result; }
        public HelloWithInheritanceResponse setResult(String value) { this.Result = value; return this; }
    }

    public static class HelloWithAlternateReturnResponse extends HelloWithReturnResponse
    {
        public String AltResult = null;

        public String getAltResult() { return AltResult; }
        public HelloWithAlternateReturnResponse setAltResult(String value) { this.AltResult = value; return this; }
    }

    public static class HelloWithRouteResponse
    {
        public String Result = null;

        public String getResult() { return Result; }
        public HelloWithRouteResponse setResult(String value) { this.Result = value; return this; }
    }

    public static class HelloWithTypeResponse
    {
        public HelloType Result = null;

        public HelloType getResult() { return Result; }
        public HelloWithTypeResponse setResult(HelloType value) { this.Result = value; return this; }
    }

    public static class HelloSessionResponse
    {
        public AuthUserSession Result = null;

        public AuthUserSession getResult() { return Result; }
        public HelloSessionResponse setResult(AuthUserSession value) { this.Result = value; return this; }
    }

    public static class Request1Response
    {
        public TypeA Test = null;

        public TypeA getTest() { return Test; }
        public Request1Response setTest(TypeA value) { this.Test = value; return this; }
    }

    public static class Request2Response
    {
        public TypeA Test = null;

        public TypeA getTest() { return Test; }
        public Request2Response setTest(TypeA value) { this.Test = value; return this; }
    }

    public static class HelloInnerTypesResponse
    {
        public InnerType InnerType = null;
        public InnerEnum InnerEnum = null;

        public InnerType getInnerType() { return InnerType; }
        public HelloInnerTypesResponse setInnerType(InnerType value) { this.InnerType = value; return this; }
        public InnerEnum getInnerEnum() { return InnerEnum; }
        public HelloInnerTypesResponse setInnerEnum(InnerEnum value) { this.InnerEnum = value; return this; }
    }

    public static class CustomUserSession extends AuthUserSession
    {
        @DataMember
        public String CustomName = null;

        @DataMember
        public String CustomInfo = null;

        public String getCustomName() { return CustomName; }
        public CustomUserSession setCustomName(String value) { this.CustomName = value; return this; }
        public String getCustomInfo() { return CustomInfo; }
        public CustomUserSession setCustomInfo(String value) { this.CustomInfo = value; return this; }
    }

    public static class Echo
    {
        public String Sentence = null;

        public String getSentence() { return Sentence; }
        public Echo setSentence(String value) { this.Sentence = value; return this; }
    }

    public static class ThrowTypeResponse
    {
        public ResponseStatus ResponseStatus = null;

        public ResponseStatus getResponseStatus() { return ResponseStatus; }
        public ThrowTypeResponse setResponseStatus(ResponseStatus value) { this.ResponseStatus = value; return this; }
    }

    public static class acsprofileResponse
    {
        public String profileId = null;

        public String getProfileId() { return profileId; }
        public acsprofileResponse setProfileId(String value) { this.profileId = value; return this; }
    }

    public static class AddressResponse
    {
        public String Address = null;

        public String getAddress() { return Address; }
        public AddressResponse setAddress(String value) { this.Address = value; return this; }
    }

    @Route("/anontype")
    public static class AnonType
    {

    }

    @Route("/query/rockstars")
    public static class QueryRockstars extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {
        public Integer Age = null;

        public Integer getAge() { return Age; }
        public QueryRockstars setAge(Integer value) { this.Age = value; return this; }
        private static Class responseType = new QueryResponse<Rockstar>().getClass();
        public Class getResponseType() { return responseType; }
    }

    @Route("/changerequest/{Id}")
    public static class ChangeRequest implements IReturn<ChangeRequestResponse>
    {
        public String Id = null;

        public String getId() { return Id; }
        public ChangeRequest setId(String value) { this.Id = value; return this; }
        private static Class responseType = ChangeRequestResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/Routing/LeadPost.aspx")
    public static class LegacyLeadPost
    {
        public String LeadType = null;
        public Integer MyId = null;

        public String getLeadType() { return LeadType; }
        public LegacyLeadPost setLeadType(String value) { this.LeadType = value; return this; }
        public Integer getMyId() { return MyId; }
        public LegacyLeadPost setMyId(Integer value) { this.MyId = value; return this; }
    }

    @Route("/info/{Id}")
    public static class Info
    {
        public String Id = null;

        public String getId() { return Id; }
        public Info setId(String value) { this.Id = value; return this; }
    }

    public static class CustomHttpError implements IReturn<CustomHttpErrorResponse>
    {
        public Integer StatusCode = null;
        public String StatusDescription = null;

        public Integer getStatusCode() { return StatusCode; }
        public CustomHttpError setStatusCode(Integer value) { this.StatusCode = value; return this; }
        public String getStatusDescription() { return StatusDescription; }
        public CustomHttpError setStatusDescription(String value) { this.StatusDescription = value; return this; }
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
        public String PathInfo = null;

        public String getPathInfo() { return PathInfo; }
        public FallbackRoute setPathInfo(String value) { this.PathInfo = value; return this; }
    }

    public static class NoRepeat implements IReturn<NoRepeatResponse>
    {
        public String Id = null;

        public String getId() { return Id; }
        public NoRepeat setId(String value) { this.Id = value; return this; }
        private static Class responseType = NoRepeatResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class BatchThrows implements IReturn<BatchThrowsResponse>
    {
        public Integer Id = null;
        public String Name = null;

        public Integer getId() { return Id; }
        public BatchThrows setId(Integer value) { this.Id = value; return this; }
        public String getName() { return Name; }
        public BatchThrows setName(String value) { this.Name = value; return this; }
        private static Class responseType = BatchThrowsResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class BatchThrowsAsync implements IReturn<BatchThrowsResponse>
    {
        public Integer Id = null;
        public String Name = null;

        public Integer getId() { return Id; }
        public BatchThrowsAsync setId(Integer value) { this.Id = value; return this; }
        public String getName() { return Name; }
        public BatchThrowsAsync setName(String value) { this.Name = value; return this; }
        private static Class responseType = BatchThrowsResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class MetadataTest implements IReturn<MetadataTestResponse>
    {
        public Integer Id = null;

        public Integer getId() { return Id; }
        public MetadataTest setId(Integer value) { this.Id = value; return this; }
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
        public MetadataType MetadataType = null;

        public MetadataType getMetadataType() { return MetadataType; }
        public MetadataRequest setMetadataType(MetadataType value) { this.MetadataType = value; return this; }
        private static Class responseType = AutoQueryMetadataResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/namedconnection")
    public static class NamedConnection
    {
        public String EmailAddresses = null;

        public String getEmailAddresses() { return EmailAddresses; }
        public NamedConnection setEmailAddresses(String value) { this.EmailAddresses = value; return this; }
    }

    public static class Issue221Long extends Issue221Base<Long>
    {

    }

    public static class HelloInService implements IReturn<HelloResponse>
    {
        public String Name = null;

        public String getName() { return Name; }
        public HelloInService setName(String value) { this.Name = value; return this; }
        private static Class responseType = HelloResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/hello/{Name}")
    public static class Hello implements IReturn<HelloResponse>
    {
        @Required()
        public String Name = null;

        public String Title = null;

        public String getName() { return Name; }
        public Hello setName(String value) { this.Name = value; return this; }
        public String getTitle() { return Title; }
        public Hello setTitle(String value) { this.Title = value; return this; }
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
        public String Name = null;

        public String getName() { return Name; }
        public HelloAnnotated setName(String value) { this.Name = value; return this; }
        private static Class responseType = HelloAnnotatedResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithNestedClass implements IReturn<HelloResponse>
    {
        public String Name = null;
        public NestedClass NestedClassProp = null;

        public String getName() { return Name; }
        public HelloWithNestedClass setName(String value) { this.Name = value; return this; }
        public NestedClass getNestedClassProp() { return NestedClassProp; }
        public HelloWithNestedClass setNestedClassProp(NestedClass value) { this.NestedClassProp = value; return this; }
        private static Class responseType = HelloResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloList implements IReturn<ArrayList<ListResult>>
    {
        public ArrayList<String> Names = null;

        public ArrayList<String> getNames() { return Names; }
        public HelloList setNames(ArrayList<String> value) { this.Names = value; return this; }
        private static Class responseType = new ArrayList<ListResult>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class HelloArray implements IReturn<ArrayList<ArrayResult>>
    {
        public ArrayList<String> Names = null;

        public ArrayList<String> getNames() { return Names; }
        public HelloArray setNames(ArrayList<String> value) { this.Names = value; return this; }
        private static Class responseType = new ArrayList<ArrayResult>().getClass();
        public Class getResponseType() { return responseType; }
    }

    public static class HelloExisting implements IReturn<HelloExistingResponse>
    {
        public ArrayList<String> Names = null;

        public ArrayList<String> getNames() { return Names; }
        public HelloExisting setNames(ArrayList<String> value) { this.Names = value; return this; }
        private static Class responseType = HelloExistingResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithEnum
    {
        public EnumType EnumProp = null;
        public EnumType NullableEnumProp = null;
        public EnumFlags EnumFlags = null;

        public EnumType getEnumProp() { return EnumProp; }
        public HelloWithEnum setEnumProp(EnumType value) { this.EnumProp = value; return this; }
        public EnumType getNullableEnumProp() { return NullableEnumProp; }
        public HelloWithEnum setNullableEnumProp(EnumType value) { this.NullableEnumProp = value; return this; }
        public EnumFlags getEnumFlags() { return EnumFlags; }
        public HelloWithEnum setEnumFlags(EnumFlags value) { this.EnumFlags = value; return this; }
    }

    public static class RestrictedAttributes
    {
        public Integer Id = null;
        public String Name = null;
        public Hello Hello = null;

        public Integer getId() { return Id; }
        public RestrictedAttributes setId(Integer value) { this.Id = value; return this; }
        public String getName() { return Name; }
        public RestrictedAttributes setName(String value) { this.Name = value; return this; }
        public Hello getHello() { return Hello; }
        public RestrictedAttributes setHello(Hello value) { this.Hello = value; return this; }
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
        public Double Range = null;

        public Double getRange() { return Range; }
        public AllowedAttributes setRange(Double value) { this.Range = value; return this; }
    }

    public static class HelloAllTypes implements IReturn<HelloAllTypesResponse>
    {
        public String Name = null;
        public AllTypes AllTypes = null;
        public AllCollectionTypes AllCollectionTypes = null;

        public String getName() { return Name; }
        public HelloAllTypes setName(String value) { this.Name = value; return this; }
        public AllTypes getAllTypes() { return AllTypes; }
        public HelloAllTypes setAllTypes(AllTypes value) { this.AllTypes = value; return this; }
        public AllCollectionTypes getAllCollectionTypes() { return AllCollectionTypes; }
        public HelloAllTypes setAllCollectionTypes(AllCollectionTypes value) { this.AllCollectionTypes = value; return this; }
        private static Class responseType = HelloAllTypesResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloString
    {
        public String Name = null;

        public String getName() { return Name; }
        public HelloString setName(String value) { this.Name = value; return this; }
    }

    public static class HelloVoid implements IReturnVoid
    {
        public String Name = null;

        public String getName() { return Name; }
        public HelloVoid setName(String value) { this.Name = value; return this; }
    }

    @DataContract
    public static class HelloWithDataContract implements IReturn<HelloWithDataContractResponse>
    {
        @DataMember(Name="name", Order=1, IsRequired=true, EmitDefaultValue=false)
        public String Name = null;

        @DataMember(Name="id", Order=2, EmitDefaultValue=false)
        public Integer Id = null;

        public String getName() { return Name; }
        public HelloWithDataContract setName(String value) { this.Name = value; return this; }
        public Integer getId() { return Id; }
        public HelloWithDataContract setId(Integer value) { this.Id = value; return this; }
        private static Class responseType = HelloWithDataContractResponse.class;
        public Class getResponseType() { return responseType; }
    }

    /**
     * Description on HelloWithDescription type
     */
    public static class HelloWithDescription implements IReturn<HelloWithDescriptionResponse>
    {
        public String Name = null;

        public String getName() { return Name; }
        public HelloWithDescription setName(String value) { this.Name = value; return this; }
        private static Class responseType = HelloWithDescriptionResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithInheritance extends HelloBase implements IReturn<HelloWithInheritanceResponse>
    {
        public String Name = null;

        public String getName() { return Name; }
        public HelloWithInheritance setName(String value) { this.Name = value; return this; }
        private static Class responseType = HelloWithInheritanceResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithGenericInheritance extends HelloBase_1<Poco>
    {
        public String Result = null;

        public String getResult() { return Result; }
        public HelloWithGenericInheritance setResult(String value) { this.Result = value; return this; }
    }

    public static class HelloWithGenericInheritance2 extends HelloBase_1<Hello>
    {
        public String Result = null;

        public String getResult() { return Result; }
        public HelloWithGenericInheritance2 setResult(String value) { this.Result = value; return this; }
    }

    public static class HelloWithNestedInheritance extends HelloBase_1<Item>
    {

    }

    public static class HelloWithListInheritance extends ArrayList<InheritedItem>
    {

    }

    public static class HelloWithReturn implements IReturn<HelloWithAlternateReturnResponse>
    {
        public String Name = null;

        public String getName() { return Name; }
        public HelloWithReturn setName(String value) { this.Name = value; return this; }
        private static Class responseType = HelloWithAlternateReturnResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/helloroute")
    public static class HelloWithRoute implements IReturn<HelloWithRouteResponse>
    {
        public String Name = null;

        public String getName() { return Name; }
        public HelloWithRoute setName(String value) { this.Name = value; return this; }
        private static Class responseType = HelloWithRouteResponse.class;
        public Class getResponseType() { return responseType; }
    }

    public static class HelloWithType implements IReturn<HelloWithTypeResponse>
    {
        public String Name = null;

        public String getName() { return Name; }
        public HelloWithType setName(String value) { this.Name = value; return this; }
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
        public IPoco Poco = null;
        public IEmptyInterface EmptyInterface = null;
        public EmptyClass EmptyClass = null;
        public String Value = null;

        public IPoco getPoco() { return Poco; }
        public HelloInterface setPoco(IPoco value) { this.Poco = value; return this; }
        public IEmptyInterface getEmptyInterface() { return EmptyInterface; }
        public HelloInterface setEmptyInterface(IEmptyInterface value) { this.EmptyInterface = value; return this; }
        public EmptyClass getEmptyClass() { return EmptyClass; }
        public HelloInterface setEmptyClass(EmptyClass value) { this.EmptyClass = value; return this; }
        public String getValue() { return Value; }
        public HelloInterface setValue(String value) { this.Value = value; return this; }
    }

    public static class Request1 implements IReturn<Request1Response>
    {
        public TypeA Test = null;

        public TypeA getTest() { return Test; }
        public Request1 setTest(TypeA value) { this.Test = value; return this; }
        private static Class responseType = Request1Response.class;
        public Class getResponseType() { return responseType; }
    }

    public static class Request2 implements IReturn<Request2Response>
    {
        public TypeA Test = null;

        public TypeA getTest() { return Test; }
        public Request2 setTest(TypeA value) { this.Test = value; return this; }
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
        public String Sentence = null;

        public String getSentence() { return Sentence; }
        public Echoes setSentence(String value) { this.Sentence = value; return this; }
        private static Class responseType = Echo.class;
        public Class getResponseType() { return responseType; }
    }

    public static class CachedEcho
    {
        public Boolean Reload = null;
        public String Sentence = null;

        public Boolean isReload() { return Reload; }
        public CachedEcho setReload(Boolean value) { this.Reload = value; return this; }
        public String getSentence() { return Sentence; }
        public CachedEcho setSentence(String value) { this.Sentence = value; return this; }
    }

    public static class AsyncTest implements IReturn<Echo>
    {

        private static Class responseType = Echo.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/throwhttperror/{Status}")
    public static class ThrowHttpError
    {
        public Integer Status = null;
        public String Message = null;

        public Integer getStatus() { return Status; }
        public ThrowHttpError setStatus(Integer value) { this.Status = value; return this; }
        public String getMessage() { return Message; }
        public ThrowHttpError setMessage(String value) { this.Message = value; return this; }
    }

    @Route("/throw404")
    // @Route("/throw404/{Message}")
    public static class Throw404
    {
        public String Message = null;

        public String getMessage() { return Message; }
        public Throw404 setMessage(String value) { this.Message = value; return this; }
    }

    @Route("/throw/{Type}")
    public static class ThrowType implements IReturn<ThrowTypeResponse>
    {
        public String Type = null;
        public String Message = null;

        public String getType() { return Type; }
        public ThrowType setType(String value) { this.Type = value; return this; }
        public String getMessage() { return Message; }
        public ThrowType setMessage(String value) { this.Message = value; return this; }
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

    @Route("/user/{UserId}/Address")
    public static class UpdateAddress implements IReturn<AddressResponse>
    {
        public Integer UserId = null;
        public String Address = null;

        public Integer getUserId() { return UserId; }
        public UpdateAddress setUserId(Integer value) { this.UserId = value; return this; }
        public String getAddress() { return Address; }
        public UpdateAddress setAddress(String value) { this.Address = value; return this; }
        private static Class responseType = AddressResponse.class;
        public Class getResponseType() { return responseType; }
    }

    @Route("/user/{UserId}/AddressVoid")
    public static class UpdateAddressVoid implements IReturnVoid
    {
        public Integer UserId = null;
        public String Address = null;

        public Integer getUserId() { return UserId; }
        public UpdateAddressVoid setUserId(Integer value) { this.UserId = value; return this; }
        public String getAddress() { return Address; }
        public UpdateAddressVoid setAddress(String value) { this.Address = value; return this; }
    }

}
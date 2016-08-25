/* Options:
Date: 2016-08-11 04:35:31
Version: 4.00
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: http://test.servicestack.net

Package: net.servicestack.client.tests
//GlobalNamespace: testdtos
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

package net.servicestack.client.tests;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;
import com.google.gson.annotations.*;
import com.google.gson.reflect.*;

public class testdtos
{

    public static class CustomHttpError implements IReturn<CustomHttpErrorResponse>
    {
        public Integer statusCode = null;
        public String statusDescription = null;

        public Integer getStatusCode() { return statusCode; }
        public CustomHttpError setStatusCode(Integer value) { this.statusCode = value; return this; }
        public String getStatusDescription() { return statusDescription; }
        public CustomHttpError setStatusDescription(String value) { this.statusDescription = value; return this; }
        private static Object responseType = CustomHttpErrorResponse.class;
        public Object getResponseType() { return responseType; }
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

    @Route("/throwcustom400")
    // @Route("/throwcustom400/{Message}")
    public static class ThrowCustom400
    {
        public String message = null;

        public String getMessage() { return message; }
        public ThrowCustom400 setMessage(String value) { this.message = value; return this; }
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
        private static Object responseType = ThrowTypeResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/throwvalidation")
    public static class ThrowValidation implements IReturn<ThrowValidationResponse>
    {
        public Integer age = null;
        public String required = null;
        public String email = null;

        public Integer getAge() { return age; }
        public ThrowValidation setAge(Integer value) { this.age = value; return this; }
        public String getRequired() { return required; }
        public ThrowValidation setRequired(String value) { this.required = value; return this; }
        public String getEmail() { return email; }
        public ThrowValidation setEmail(String value) { this.email = value; return this; }
        private static Object responseType = ThrowValidationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/throwbusinesserror")
    public static class ThrowBusinessError implements IReturn<ThrowBusinessErrorResponse>
    {

        private static Object responseType = ThrowBusinessErrorResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class ExternalOperation implements IReturn<ExternalOperationResponse>
    {
        public Integer id = null;
        public String name = null;
        public ExternalEnum externalEnum = null;

        public Integer getId() { return id; }
        public ExternalOperation setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public ExternalOperation setName(String value) { this.name = value; return this; }
        public ExternalEnum getExternalEnum() { return externalEnum; }
        public ExternalOperation setExternalEnum(ExternalEnum value) { this.externalEnum = value; return this; }
        private static Object responseType = ExternalOperationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class ExternalOperation2 implements IReturn<ExternalOperation2Response>
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public ExternalOperation2 setId(Integer value) { this.id = value; return this; }
        private static Object responseType = ExternalOperation2Response.class;
        public Object getResponseType() { return responseType; }
    }

    public static class ExternalOperation3 implements IReturn<ExternalReturnTypeResponse>
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public ExternalOperation3 setId(Integer value) { this.id = value; return this; }
        private static Object responseType = ExternalReturnTypeResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class ExternalOperation4
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public ExternalOperation4 setId(Integer value) { this.id = value; return this; }
    }

    @Route("/{Path*}")
    public static class RootPathRoutes
    {
        public String path = null;

        public String getPath() { return path; }
        public RootPathRoutes setPath(String value) { this.path = value; return this; }
    }

    public static class GetAccount implements IReturn<Account>
    {
        public String account = null;

        public String getAccount() { return account; }
        public GetAccount setAccount(String value) { this.account = value; return this; }
        private static Object responseType = Account.class;
        public Object getResponseType() { return responseType; }
    }

    public static class GetProject implements IReturn<Project>
    {
        public String account = null;
        public String project = null;

        public String getAccount() { return account; }
        public GetProject setAccount(String value) { this.account = value; return this; }
        public String getProject() { return project; }
        public GetProject setProject(String value) { this.project = value; return this; }
        private static Object responseType = Project.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/image-stream")
    public static class ImageAsStream
    {
        public String format = null;

        public String getFormat() { return format; }
        public ImageAsStream setFormat(String value) { this.format = value; return this; }
    }

    @Route("/image-bytes")
    public static class ImageAsBytes
    {
        public String format = null;

        public String getFormat() { return format; }
        public ImageAsBytes setFormat(String value) { this.format = value; return this; }
    }

    @Route("/image-custom")
    public static class ImageAsCustomResult
    {
        public String format = null;

        public String getFormat() { return format; }
        public ImageAsCustomResult setFormat(String value) { this.format = value; return this; }
    }

    @Route("/image-response")
    public static class ImageWriteToResponse
    {
        public String format = null;

        public String getFormat() { return format; }
        public ImageWriteToResponse setFormat(String value) { this.format = value; return this; }
    }

    @Route("/image-file")
    public static class ImageAsFile
    {
        public String format = null;

        public String getFormat() { return format; }
        public ImageAsFile setFormat(String value) { this.format = value; return this; }
    }

    @Route("/image-redirect")
    public static class ImageAsRedirect
    {
        public String format = null;

        public String getFormat() { return format; }
        public ImageAsRedirect setFormat(String value) { this.format = value; return this; }
    }

    @Route("/image-draw/{Name}")
    public static class DrawImage
    {
        public String name = null;
        public String format = null;
        public Integer width = null;
        public Integer height = null;
        public Integer fontSize = null;
        public String foreground = null;
        public String background = null;

        public String getName() { return name; }
        public DrawImage setName(String value) { this.name = value; return this; }
        public String getFormat() { return format; }
        public DrawImage setFormat(String value) { this.format = value; return this; }
        public Integer getWidth() { return width; }
        public DrawImage setWidth(Integer value) { this.width = value; return this; }
        public Integer getHeight() { return height; }
        public DrawImage setHeight(Integer value) { this.height = value; return this; }
        public Integer getFontSize() { return fontSize; }
        public DrawImage setFontSize(Integer value) { this.fontSize = value; return this; }
        public String getForeground() { return foreground; }
        public DrawImage setForeground(String value) { this.foreground = value; return this; }
        public String getBackground() { return background; }
        public DrawImage setBackground(String value) { this.background = value; return this; }
    }

    @Route("/metadatatest")
    public static class MetadataTest implements IReturn<MetadataTestResponse>
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public MetadataTest setId(Integer value) { this.id = value; return this; }
        private static Object responseType = MetadataTestResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/metadatatest-array")
    public static class MetadataTestArray implements IReturn<ArrayList<MetadataTestChild>>
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public MetadataTestArray setId(Integer value) { this.id = value; return this; }
        private static Object responseType = new TypeToken<ArrayList<MetadataTestChild>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/example", Verbs="GET")
    @DataContract
    public static class GetExample implements IReturn<GetExampleResponse>
    {

        private static Object responseType = GetExampleResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/randomids")
    public static class GetRandomIds implements IReturn<GetRandomIdsResponse>
    {
        public Integer take = null;

        public Integer getTake() { return take; }
        public GetRandomIds setTake(Integer value) { this.take = value; return this; }
        private static Object responseType = GetRandomIdsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/textfile-test")
    public static class TextFileTest
    {
        public Boolean asAttachment = null;

        public Boolean isAsAttachment() { return asAttachment; }
        public TextFileTest setAsAttachment(Boolean value) { this.asAttachment = value; return this; }
    }

    @Route("/hello")
    // @Route("/hello/{Name}")
    public static class Hello implements IReturn<HelloResponse>
    {
        @Required()
        public String name = null;

        public String title = null;

        public String getName() { return name; }
        public Hello setName(String value) { this.name = value; return this; }
        public String getTitle() { return title; }
        public Hello setTitle(String value) { this.title = value; return this; }
        private static Object responseType = HelloResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloWithNestedClass implements IReturn<HelloResponse>
    {
        public String name = null;
        public NestedClass nestedClassProp = null;

        public String getName() { return name; }
        public HelloWithNestedClass setName(String value) { this.name = value; return this; }
        public NestedClass getNestedClassProp() { return nestedClassProp; }
        public HelloWithNestedClass setNestedClassProp(NestedClass value) { this.nestedClassProp = value; return this; }
        private static Object responseType = HelloResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloList implements IReturn<ArrayList<ListResult>>
    {
        public ArrayList<String> names = null;

        public ArrayList<String> getNames() { return names; }
        public HelloList setNames(ArrayList<String> value) { this.names = value; return this; }
        private static Object responseType = new TypeToken<ArrayList<ListResult>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    public static class HelloArray implements IReturn<ArrayList<ArrayResult>>
    {
        public ArrayList<String> names = null;

        public ArrayList<String> getNames() { return names; }
        public HelloArray setNames(ArrayList<String> value) { this.names = value; return this; }
        private static Object responseType = new TypeToken<ArrayList<ArrayResult>>(){}.getType();
        public Object getResponseType() { return responseType; }
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

    public static class HelloExternal
    {
        public String name = null;

        public String getName() { return name; }
        public HelloExternal setName(String value) { this.name = value; return this; }
    }

    /**
     * AllowedAttributes Description
     */
    @Route(Path="/allowed-attributes", Verbs="GET")
    @Api("AllowedAttributes Description")
    // @ApiResponse(400, "Your request was not understood")
    @DataContract
    public static class AllowedAttributes
    {
        /**
         * Range Description
         */
        @DataMember(Name="Aliased")
        @SerializedName("Aliased")
        @ApiMember(ParameterType="path", Description="Range Description", DataType="double", IsRequired=true)
        public Double range = null;

        public Double getRange() { return range; }
        public AllowedAttributes setRange(Double value) { this.range = value; return this; }
    }

    @Route("/all-types")
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
        private static Object responseType = HelloAllTypesResponse.class;
        public Object getResponseType() { return responseType; }
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

    public static class HelloString implements IReturn<String>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloString setName(String value) { this.name = value; return this; }
        private static Object responseType = String.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloDateTime implements IReturn<HelloDateTime>
    {
        public Date dateTime = null;

        public Date getDateTime() { return dateTime; }
        public HelloDateTime setDateTime(Date value) { this.dateTime = value; return this; }
        private static Object responseType = HelloDateTime.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloVoid
    {
        public String name = null;

        public String getName() { return name; }
        public HelloVoid setName(String value) { this.name = value; return this; }
    }

    @DataContract
    public static class HelloWithDataContract implements IReturn<HelloWithDataContractResponse>
    {
        @DataMember(Name="name", Order=1, IsRequired=true, EmitDefaultValue=false)
        @SerializedName("name")
        public String name = null;

        @DataMember(Name="id", Order=2, EmitDefaultValue=false)
        @SerializedName("id")
        public Integer id = null;

        public String getName() { return name; }
        public HelloWithDataContract setName(String value) { this.name = value; return this; }
        public Integer getId() { return id; }
        public HelloWithDataContract setId(Integer value) { this.id = value; return this; }
        private static Object responseType = HelloWithDataContractResponse.class;
        public Object getResponseType() { return responseType; }
    }

    /**
     * Description on HelloWithDescription type
     */
    public static class HelloWithDescription implements IReturn<HelloWithDescriptionResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithDescription setName(String value) { this.name = value; return this; }
        private static Object responseType = HelloWithDescriptionResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloWithInheritance extends HelloBase implements IReturn<HelloWithInheritanceResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithInheritance setName(String value) { this.name = value; return this; }
        private static Object responseType = HelloWithInheritanceResponse.class;
        public Object getResponseType() { return responseType; }
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

    public static class HelloWithReturn implements IReturn<HelloWithAlternateReturnResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithReturn setName(String value) { this.name = value; return this; }
        private static Object responseType = HelloWithAlternateReturnResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/helloroute")
    public static class HelloWithRoute implements IReturn<HelloWithRouteResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithRoute setName(String value) { this.name = value; return this; }
        private static Object responseType = HelloWithRouteResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloWithType implements IReturn<HelloWithTypeResponse>
    {
        public String name = null;

        public String getName() { return name; }
        public HelloWithType setName(String value) { this.name = value; return this; }
        private static Object responseType = HelloWithTypeResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloInterface
    {
        public IPoco poco = null;
        public IEmptyInterface emptyInterface = null;
        public EmptyClass emptyClass = null;

        public IPoco getPoco() { return poco; }
        public HelloInterface setPoco(IPoco value) { this.poco = value; return this; }
        public IEmptyInterface getEmptyInterface() { return emptyInterface; }
        public HelloInterface setEmptyInterface(IEmptyInterface value) { this.emptyInterface = value; return this; }
        public EmptyClass getEmptyClass() { return emptyClass; }
        public HelloInterface setEmptyClass(EmptyClass value) { this.emptyClass = value; return this; }
    }

    public static class HelloInnerTypes implements IReturn<HelloInnerTypesResponse>
    {

        private static Object responseType = HelloInnerTypesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloBuiltin
    {
        public DayOfWeek dayOfWeek = null;

        public DayOfWeek getDayOfWeek() { return dayOfWeek; }
        public HelloBuiltin setDayOfWeek(DayOfWeek value) { this.dayOfWeek = value; return this; }
    }

    public static class HelloGet implements IReturn<HelloVerbResponse>, IGet
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public HelloGet setId(Integer value) { this.id = value; return this; }
        private static Object responseType = HelloVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloPost extends HelloBase implements IReturn<HelloVerbResponse>, IPost
    {

        private static Object responseType = HelloVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloPut implements IReturn<HelloVerbResponse>, IPut
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public HelloPut setId(Integer value) { this.id = value; return this; }
        private static Object responseType = HelloVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloDelete implements IReturn<HelloVerbResponse>, IDelete
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public HelloDelete setId(Integer value) { this.id = value; return this; }
        private static Object responseType = HelloVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloPatch implements IReturn<HelloVerbResponse>, IPatch
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public HelloPatch setId(Integer value) { this.id = value; return this; }
        private static Object responseType = HelloVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloReturnVoid implements IReturnVoid
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public HelloReturnVoid setId(Integer value) { this.id = value; return this; }
    }

    public static class EnumRequest implements IReturn<EnumResponse>, IPut
    {
        public ScopeType operator = null;

        public ScopeType getOperator() { return operator; }
        public EnumRequest setOperator(ScopeType value) { this.operator = value; return this; }
        private static Object responseType = EnumResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/ping")
    public static class Ping implements IReturn<PingResponse>
    {

        private static Object responseType = PingResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/reset-connections")
    public static class ResetConnections
    {

    }

    @Route("/requires-role")
    public static class RequiresRole implements IReturn<RequiresRoleResponse>
    {

        private static Object responseType = RequiresRoleResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class SendDefault implements IReturn<SendVerbResponse>
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public SendDefault setId(Integer value) { this.id = value; return this; }
        private static Object responseType = SendVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/sendrestget/{Id}", Verbs="GET")
    public static class SendRestGet implements IReturn<SendVerbResponse>, IGet
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public SendRestGet setId(Integer value) { this.id = value; return this; }
        private static Object responseType = SendVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class SendGet implements IReturn<SendVerbResponse>, IGet
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public SendGet setId(Integer value) { this.id = value; return this; }
        private static Object responseType = SendVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class SendPost implements IReturn<SendVerbResponse>, IPost
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public SendPost setId(Integer value) { this.id = value; return this; }
        private static Object responseType = SendVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class SendPut implements IReturn<SendVerbResponse>, IPut
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public SendPut setId(Integer value) { this.id = value; return this; }
        private static Object responseType = SendVerbResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/session")
    public static class GetSession implements IReturn<GetSessionResponse>
    {

        private static Object responseType = GetSessionResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/session/edit/{CustomName}")
    public static class UpdateSession implements IReturn<GetSessionResponse>
    {
        public String customName = null;

        public String getCustomName() { return customName; }
        public UpdateSession setCustomName(String value) { this.customName = value; return this; }
        private static Object responseType = GetSessionResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class StoreLogs implements IReturn<StoreLogsResponse>
    {
        public ArrayList<Logger> loggers = null;

        public ArrayList<Logger> getLoggers() { return loggers; }
        public StoreLogs setLoggers(ArrayList<Logger> value) { this.loggers = value; return this; }
        private static Object responseType = StoreLogsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/testauth")
    public static class TestAuth implements IReturn<TestAuthResponse>
    {

        private static Object responseType = TestAuthResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/void-response")
    public static class TestVoidResponse
    {

    }

    @Route("/null-response")
    public static class TestNullResponse
    {

    }

    @Route("/wait/{ForMs}")
    public static class Wait implements IReturn<Wait>
    {
        public Integer forMs = null;

        public Integer getForMs() { return forMs; }
        public Wait setForMs(Integer value) { this.forMs = value; return this; }
        private static Object responseType = Wait.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/echo/types")
    public static class EchoTypes implements IReturn<EchoTypes>
    {
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

        public Short getByte() { return Byte; }
        public EchoTypes setByte(Short value) { this.Byte = value; return this; }
        public Short getShort() { return Short; }
        public EchoTypes setShort(Short value) { this.Short = value; return this; }
        public Integer getInt() { return Int; }
        public EchoTypes setInt(Integer value) { this.Int = value; return this; }
        public Long getLong() { return Long; }
        public EchoTypes setLong(Long value) { this.Long = value; return this; }
        public Integer getUShort() { return uShort; }
        public EchoTypes setUShort(Integer value) { this.uShort = value; return this; }
        public Long getUInt() { return uInt; }
        public EchoTypes setUInt(Long value) { this.uInt = value; return this; }
        public BigInteger getULong() { return uLong; }
        public EchoTypes setULong(BigInteger value) { this.uLong = value; return this; }
        public Float getFloat() { return Float; }
        public EchoTypes setFloat(Float value) { this.Float = value; return this; }
        public Double getDouble() { return Double; }
        public EchoTypes setDouble(Double value) { this.Double = value; return this; }
        public BigDecimal getDecimal() { return decimal; }
        public EchoTypes setDecimal(BigDecimal value) { this.decimal = value; return this; }
        public String getString() { return string; }
        public EchoTypes setString(String value) { this.string = value; return this; }
        public Date getDateTime() { return dateTime; }
        public EchoTypes setDateTime(Date value) { this.dateTime = value; return this; }
        public TimeSpan getTimeSpan() { return timeSpan; }
        public EchoTypes setTimeSpan(TimeSpan value) { this.timeSpan = value; return this; }
        public Date getDateTimeOffset() { return dateTimeOffset; }
        public EchoTypes setDateTimeOffset(Date value) { this.dateTimeOffset = value; return this; }
        public UUID getGuid() { return guid; }
        public EchoTypes setGuid(UUID value) { this.guid = value; return this; }
        public String getChar() { return Char; }
        public EchoTypes setChar(String value) { this.Char = value; return this; }
        private static Object responseType = EchoTypes.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/echo/collections")
    public static class EchoCollections implements IReturn<EchoCollections>
    {
        public ArrayList<String> stringList = null;
        public ArrayList<String> stringArray = null;
        public HashMap<String,String> stringMap = null;
        public HashMap<Integer,String> intStringMap = null;

        public ArrayList<String> getStringList() { return stringList; }
        public EchoCollections setStringList(ArrayList<String> value) { this.stringList = value; return this; }
        public ArrayList<String> getStringArray() { return stringArray; }
        public EchoCollections setStringArray(ArrayList<String> value) { this.stringArray = value; return this; }
        public HashMap<String,String> getStringMap() { return stringMap; }
        public EchoCollections setStringMap(HashMap<String,String> value) { this.stringMap = value; return this; }
        public HashMap<Integer,String> getIntStringMap() { return intStringMap; }
        public EchoCollections setIntStringMap(HashMap<Integer,String> value) { this.intStringMap = value; return this; }
        private static Object responseType = EchoCollections.class;
        public Object getResponseType() { return responseType; }
    }

    public static class EchoComplexTypes implements IReturn<EchoComplexTypes>
    {
        public SubType subType = null;

        public SubType getSubType() { return subType; }
        public EchoComplexTypes setSubType(SubType value) { this.subType = value; return this; }
        private static Object responseType = EchoComplexTypes.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/requestlogs")
    @DataContract
    public static class RequestLogs implements IReturn<RequestLogsResponse>
    {
        @DataMember(Order=1)
        public Integer beforeSecs = null;

        @DataMember(Order=2)
        public Integer afterSecs = null;

        @DataMember(Order=3)
        public String ipAddress = null;

        @DataMember(Order=4)
        public String forwardedFor = null;

        @DataMember(Order=5)
        public String userAuthId = null;

        @DataMember(Order=6)
        public String sessionId = null;

        @DataMember(Order=7)
        public String referer = null;

        @DataMember(Order=8)
        public String pathInfo = null;

        @DataMember(Order=9)
        public ArrayList<Long> ids = null;

        @DataMember(Order=10)
        public Integer beforeId = null;

        @DataMember(Order=11)
        public Integer afterId = null;

        @DataMember(Order=12)
        public Boolean hasResponse = null;

        @DataMember(Order=13)
        public Boolean withErrors = null;

        @DataMember(Order=14)
        public Integer skip = null;

        @DataMember(Order=15)
        public Integer take = null;

        @DataMember(Order=16)
        public Boolean enableSessionTracking = null;

        @DataMember(Order=17)
        public Boolean enableResponseTracking = null;

        @DataMember(Order=18)
        public Boolean enableErrorTracking = null;

        @DataMember(Order=19)
        public TimeSpan durationLongerThan = null;

        @DataMember(Order=20)
        public TimeSpan durationLessThan = null;

        public Integer getBeforeSecs() { return beforeSecs; }
        public RequestLogs setBeforeSecs(Integer value) { this.beforeSecs = value; return this; }
        public Integer getAfterSecs() { return afterSecs; }
        public RequestLogs setAfterSecs(Integer value) { this.afterSecs = value; return this; }
        public String getIpAddress() { return ipAddress; }
        public RequestLogs setIpAddress(String value) { this.ipAddress = value; return this; }
        public String getForwardedFor() { return forwardedFor; }
        public RequestLogs setForwardedFor(String value) { this.forwardedFor = value; return this; }
        public String getUserAuthId() { return userAuthId; }
        public RequestLogs setUserAuthId(String value) { this.userAuthId = value; return this; }
        public String getSessionId() { return sessionId; }
        public RequestLogs setSessionId(String value) { this.sessionId = value; return this; }
        public String getReferer() { return referer; }
        public RequestLogs setReferer(String value) { this.referer = value; return this; }
        public String getPathInfo() { return pathInfo; }
        public RequestLogs setPathInfo(String value) { this.pathInfo = value; return this; }
        public ArrayList<Long> getIds() { return ids; }
        public RequestLogs setIds(ArrayList<Long> value) { this.ids = value; return this; }
        public Integer getBeforeId() { return beforeId; }
        public RequestLogs setBeforeId(Integer value) { this.beforeId = value; return this; }
        public Integer getAfterId() { return afterId; }
        public RequestLogs setAfterId(Integer value) { this.afterId = value; return this; }
        public Boolean isHasResponse() { return hasResponse; }
        public RequestLogs setHasResponse(Boolean value) { this.hasResponse = value; return this; }
        public Boolean isWithErrors() { return withErrors; }
        public RequestLogs setWithErrors(Boolean value) { this.withErrors = value; return this; }
        public Integer getSkip() { return skip; }
        public RequestLogs setSkip(Integer value) { this.skip = value; return this; }
        public Integer getTake() { return take; }
        public RequestLogs setTake(Integer value) { this.take = value; return this; }
        public Boolean isEnableSessionTracking() { return enableSessionTracking; }
        public RequestLogs setEnableSessionTracking(Boolean value) { this.enableSessionTracking = value; return this; }
        public Boolean isEnableResponseTracking() { return enableResponseTracking; }
        public RequestLogs setEnableResponseTracking(Boolean value) { this.enableResponseTracking = value; return this; }
        public Boolean isEnableErrorTracking() { return enableErrorTracking; }
        public RequestLogs setEnableErrorTracking(Boolean value) { this.enableErrorTracking = value; return this; }
        public TimeSpan getDurationLongerThan() { return durationLongerThan; }
        public RequestLogs setDurationLongerThan(TimeSpan value) { this.durationLongerThan = value; return this; }
        public TimeSpan getDurationLessThan() { return durationLessThan; }
        public RequestLogs setDurationLessThan(TimeSpan value) { this.durationLessThan = value; return this; }
        private static Object responseType = RequestLogsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/auth")
    // @Route("/auth/{provider}")
    // @Route("/authenticate")
    // @Route("/authenticate/{provider}")
    @DataContract
    public static class Authenticate implements IReturn<AuthenticateResponse>
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
        public HashMap<String,String> getMeta() { return meta; }
        public Authenticate setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        private static Object responseType = AuthenticateResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/assignroles")
    @DataContract
    public static class AssignRoles implements IReturn<AssignRolesResponse>
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
    public static class UnAssignRoles implements IReturn<UnAssignRolesResponse>
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

    public static class QueryPocoBase extends QueryBase_1<OnlyDefinedInGenericType> implements IReturn<QueryResponse<OnlyDefinedInGenericType>>
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public QueryPocoBase setId(Integer value) { this.id = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<OnlyDefinedInGenericType>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    public static class QueryPocoIntoBase extends QueryBase_2<OnlyDefinedInGenericTypeFrom, OnlyDefinedInGenericTypeInto> implements IReturn<QueryResponse<OnlyDefinedInGenericTypeInto>>
    {
        public Integer id = null;

        public Integer getId() { return id; }
        public QueryPocoIntoBase setId(Integer value) { this.id = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<OnlyDefinedInGenericTypeInto>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route("/rockstars")
    public static class QueryRockstars extends QueryBase_1<Rockstar> implements IReturn<QueryResponse<Rockstar>>
    {

        private static Object responseType = new TypeToken<QueryResponse<Rockstar>>(){}.getType();
        public Object getResponseType() { return responseType; }
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

    public static class ThrowTypeResponse
    {
        public ResponseStatus responseStatus = null;

        public ResponseStatus getResponseStatus() { return responseStatus; }
        public ThrowTypeResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class ThrowValidationResponse
    {
        public Integer age = null;
        public String required = null;
        public String email = null;
        public ResponseStatus responseStatus = null;

        public Integer getAge() { return age; }
        public ThrowValidationResponse setAge(Integer value) { this.age = value; return this; }
        public String getRequired() { return required; }
        public ThrowValidationResponse setRequired(String value) { this.required = value; return this; }
        public String getEmail() { return email; }
        public ThrowValidationResponse setEmail(String value) { this.email = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public ThrowValidationResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class ThrowBusinessErrorResponse
    {
        public ResponseStatus responseStatus = null;

        public ResponseStatus getResponseStatus() { return responseStatus; }
        public ThrowBusinessErrorResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class ExternalOperationResponse
    {
        public String result = null;

        public String getResult() { return result; }
        public ExternalOperationResponse setResult(String value) { this.result = value; return this; }
    }

    public static class ExternalOperation2Response
    {
        public ExternalType externalType = null;

        public ExternalType getExternalType() { return externalType; }
        public ExternalOperation2Response setExternalType(ExternalType value) { this.externalType = value; return this; }
    }

    public static class ExternalReturnTypeResponse
    {
        public ExternalEnum3 externalEnum3 = null;

        public ExternalEnum3 getExternalEnum3() { return externalEnum3; }
        public ExternalReturnTypeResponse setExternalEnum3(ExternalEnum3 value) { this.externalEnum3 = value; return this; }
    }

    public static class Account
    {
        public String name = null;

        public String getName() { return name; }
        public Account setName(String value) { this.name = value; return this; }
    }

    public static class Project
    {
        public String account = null;
        public String name = null;

        public String getAccount() { return account; }
        public Project setAccount(String value) { this.account = value; return this; }
        public String getName() { return name; }
        public Project setName(String value) { this.name = value; return this; }
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

    public static class GetRandomIdsResponse
    {
        public ArrayList<String> results = null;

        public ArrayList<String> getResults() { return results; }
        public GetRandomIdsResponse setResults(ArrayList<String> value) { this.results = value; return this; }
    }

    public static class HelloResponse
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloResponse setResult(String value) { this.result = value; return this; }
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
        @SerializedName("result")
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

    public static class HelloInnerTypesResponse
    {
        public InnerType innerType = null;
        public InnerEnum innerEnum = null;

        public InnerType getInnerType() { return innerType; }
        public HelloInnerTypesResponse setInnerType(InnerType value) { this.innerType = value; return this; }
        public InnerEnum getInnerEnum() { return innerEnum; }
        public HelloInnerTypesResponse setInnerEnum(InnerEnum value) { this.innerEnum = value; return this; }
    }

    public static class HelloVerbResponse
    {
        public String result = null;

        public String getResult() { return result; }
        public HelloVerbResponse setResult(String value) { this.result = value; return this; }
    }

    public static class EnumResponse
    {
        public ScopeType operator = null;

        public ScopeType getOperator() { return operator; }
        public EnumResponse setOperator(ScopeType value) { this.operator = value; return this; }
    }

    public static class PingResponse
    {
        public HashMap<String,ResponseStatus> responses = null;
        public ResponseStatus responseStatus = null;

        public HashMap<String,ResponseStatus> getResponses() { return responses; }
        public PingResponse setResponses(HashMap<String,ResponseStatus> value) { this.responses = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public PingResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class RequiresRoleResponse
    {
        public String result = null;
        public ResponseStatus responseStatus = null;

        public String getResult() { return result; }
        public RequiresRoleResponse setResult(String value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public RequiresRoleResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class SendVerbResponse
    {
        public Integer id = null;
        public String pathInfo = null;
        public String requestMethod = null;

        public Integer getId() { return id; }
        public SendVerbResponse setId(Integer value) { this.id = value; return this; }
        public String getPathInfo() { return pathInfo; }
        public SendVerbResponse setPathInfo(String value) { this.pathInfo = value; return this; }
        public String getRequestMethod() { return requestMethod; }
        public SendVerbResponse setRequestMethod(String value) { this.requestMethod = value; return this; }
    }

    public static class GetSessionResponse
    {
        public CustomUserSession result = null;
        public UnAuthInfo unAuthInfo = null;
        public ResponseStatus responseStatus = null;

        public CustomUserSession getResult() { return result; }
        public GetSessionResponse setResult(CustomUserSession value) { this.result = value; return this; }
        public UnAuthInfo getUnAuthInfo() { return unAuthInfo; }
        public GetSessionResponse setUnAuthInfo(UnAuthInfo value) { this.unAuthInfo = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetSessionResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class StoreLogsResponse
    {
        public ArrayList<Logger> existingLogs = null;
        public ResponseStatus responseStatus = null;

        public ArrayList<Logger> getExistingLogs() { return existingLogs; }
        public StoreLogsResponse setExistingLogs(ArrayList<Logger> value) { this.existingLogs = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public StoreLogsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class TestAuthResponse
    {
        public String userId = null;
        public String sessionId = null;
        public String userName = null;
        public String displayName = null;
        public ResponseStatus responseStatus = null;

        public String getUserId() { return userId; }
        public TestAuthResponse setUserId(String value) { this.userId = value; return this; }
        public String getSessionId() { return sessionId; }
        public TestAuthResponse setSessionId(String value) { this.sessionId = value; return this; }
        public String getUserName() { return userName; }
        public TestAuthResponse setUserName(String value) { this.userName = value; return this; }
        public String getDisplayName() { return displayName; }
        public TestAuthResponse setDisplayName(String value) { this.displayName = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public TestAuthResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    @DataContract
    public static class RequestLogsResponse
    {
        @DataMember(Order=1)
        public ArrayList<RequestLogEntry> results = null;

        @DataMember(Order=2)
        public HashMap<String,String> usage = null;

        @DataMember(Order=3)
        public ResponseStatus responseStatus = null;

        public ArrayList<RequestLogEntry> getResults() { return results; }
        public RequestLogsResponse setResults(ArrayList<RequestLogEntry> value) { this.results = value; return this; }
        public HashMap<String,String> getUsage() { return usage; }
        public RequestLogsResponse setUsage(HashMap<String,String> value) { this.usage = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public RequestLogsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
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

    @DataContract
    public static class QueryResponse<T>
    {
        @DataMember(Order=1)
        public Integer offset = null;

        @DataMember(Order=2)
        public Integer total = null;

        @DataMember(Order=3)
        public ArrayList<T> results = null;

        @DataMember(Order=4)
        public HashMap<String,String> meta = null;

        @DataMember(Order=5)
        public ResponseStatus responseStatus = null;

        public Integer getOffset() { return offset; }
        public QueryResponse<T> setOffset(Integer value) { this.offset = value; return this; }
        public Integer getTotal() { return total; }
        public QueryResponse<T> setTotal(Integer value) { this.total = value; return this; }
        public ArrayList<T> getResults() { return results; }
        public QueryResponse<T> setResults(ArrayList<T> value) { this.results = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public QueryResponse<T> setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public QueryResponse<T> setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static enum ExternalEnum
    {
        Foo,
        Bar,
        Baz;
    }

    public static class ExternalType
    {
        public ExternalEnum2 externalEnum2 = null;

        public ExternalEnum2 getExternalEnum2() { return externalEnum2; }
        public ExternalType setExternalEnum2(ExternalEnum2 value) { this.externalEnum2 = value; return this; }
    }

    public static enum ExternalEnum3
    {
        Un,
        Deux,
        Trois;
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
        @SerializedName("1") Value1(1),
        @SerializedName("2") Value2(2),
        @SerializedName("4") Value3(4);

        private final int value;
        EnumFlags(final int intValue) { value = intValue; }
        public int getValue() { return value; }
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

    public static class SubType
    {
        public Integer id = null;
        public String name = null;

        public Integer getId() { return id; }
        public SubType setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public SubType setName(String value) { this.name = value; return this; }
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

    public static enum DayOfWeek
    {
        Sunday,
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday;
    }

    @DataContract
    public static enum ScopeType
    {
        Global(1),
        Sale(2);

        private final int value;
        ScopeType(final int intValue) { value = intValue; }
        public int getValue() { return value; }
    }

    public static class PingService
    {

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

    public static class UnAuthInfo
    {
        public String customInfo = null;

        public String getCustomInfo() { return customInfo; }
        public UnAuthInfo setCustomInfo(String value) { this.customInfo = value; return this; }
    }

    public static class Logger
    {
        public Long id = null;
        public ArrayList<Device> devices = null;

        public Long getId() { return id; }
        public Logger setId(Long value) { this.id = value; return this; }
        public ArrayList<Device> getDevices() { return devices; }
        public Logger setDevices(ArrayList<Device> value) { this.devices = value; return this; }
    }

    public static class RequestLogEntry
    {
        public Long id = null;
        public Date dateTime = null;
        public String httpMethod = null;
        public String absoluteUri = null;
        public String pathInfo = null;
        public String requestBody = null;
        public Object requestDto = null;
        public String userAuthId = null;
        public String sessionId = null;
        public String ipAddress = null;
        public String forwardedFor = null;
        public String referer = null;
        public HashMap<String,String> headers = null;
        public HashMap<String,String> formData = null;
        public HashMap<String,String> items = null;
        public Object session = null;
        public Object responseDto = null;
        public Object errorResponse = null;
        public TimeSpan requestDuration = null;

        public Long getId() { return id; }
        public RequestLogEntry setId(Long value) { this.id = value; return this; }
        public Date getDateTime() { return dateTime; }
        public RequestLogEntry setDateTime(Date value) { this.dateTime = value; return this; }
        public String getHttpMethod() { return httpMethod; }
        public RequestLogEntry setHttpMethod(String value) { this.httpMethod = value; return this; }
        public String getAbsoluteUri() { return absoluteUri; }
        public RequestLogEntry setAbsoluteUri(String value) { this.absoluteUri = value; return this; }
        public String getPathInfo() { return pathInfo; }
        public RequestLogEntry setPathInfo(String value) { this.pathInfo = value; return this; }
        public String getRequestBody() { return requestBody; }
        public RequestLogEntry setRequestBody(String value) { this.requestBody = value; return this; }
        public Object getRequestDto() { return requestDto; }
        public RequestLogEntry setRequestDto(Object value) { this.requestDto = value; return this; }
        public String getUserAuthId() { return userAuthId; }
        public RequestLogEntry setUserAuthId(String value) { this.userAuthId = value; return this; }
        public String getSessionId() { return sessionId; }
        public RequestLogEntry setSessionId(String value) { this.sessionId = value; return this; }
        public String getIpAddress() { return ipAddress; }
        public RequestLogEntry setIpAddress(String value) { this.ipAddress = value; return this; }
        public String getForwardedFor() { return forwardedFor; }
        public RequestLogEntry setForwardedFor(String value) { this.forwardedFor = value; return this; }
        public String getReferer() { return referer; }
        public RequestLogEntry setReferer(String value) { this.referer = value; return this; }
        public HashMap<String,String> getHeaders() { return headers; }
        public RequestLogEntry setHeaders(HashMap<String,String> value) { this.headers = value; return this; }
        public HashMap<String,String> getFormData() { return formData; }
        public RequestLogEntry setFormData(HashMap<String,String> value) { this.formData = value; return this; }
        public HashMap<String,String> getItems() { return items; }
        public RequestLogEntry setItems(HashMap<String,String> value) { this.items = value; return this; }
        public Object getSession() { return session; }
        public RequestLogEntry setSession(Object value) { this.session = value; return this; }
        public Object getResponseDto() { return responseDto; }
        public RequestLogEntry setResponseDto(Object value) { this.responseDto = value; return this; }
        public Object getErrorResponse() { return errorResponse; }
        public RequestLogEntry setErrorResponse(Object value) { this.errorResponse = value; return this; }
        public TimeSpan getRequestDuration() { return requestDuration; }
        public RequestLogEntry setRequestDuration(TimeSpan value) { this.requestDuration = value; return this; }
    }

    public static class QueryBase_1<T> extends QueryBase
    {

    }

    public static class OnlyDefinedInGenericType
    {
        public Integer id = null;
        public String name = null;

        public Integer getId() { return id; }
        public OnlyDefinedInGenericType setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public OnlyDefinedInGenericType setName(String value) { this.name = value; return this; }
    }

    public static class QueryBase_2<From, Into> extends QueryBase
    {

    }

    public static class OnlyDefinedInGenericTypeFrom
    {
        public Integer id = null;
        public String name = null;

        public Integer getId() { return id; }
        public OnlyDefinedInGenericTypeFrom setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public OnlyDefinedInGenericTypeFrom setName(String value) { this.name = value; return this; }
    }

    public static class OnlyDefinedInGenericTypeInto
    {
        public Integer id = null;
        public String name = null;

        public Integer getId() { return id; }
        public OnlyDefinedInGenericTypeInto setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public OnlyDefinedInGenericTypeInto setName(String value) { this.name = value; return this; }
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

    public static enum ExternalEnum2
    {
        Uno,
        Due,
        Tre;
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

    public static class TypesGroup
    {

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
        public Boolean fromToken = null;

        @DataMember(Order=39)
        public String profileUrl = null;

        @DataMember(Order=40)
        public String sequence = null;

        @DataMember(Order=41)
        public Long tag = null;

        @DataMember(Order=42)
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
        public Boolean isFromToken() { return fromToken; }
        public AuthUserSession setFromToken(Boolean value) { this.fromToken = value; return this; }
        public String getProfileUrl() { return profileUrl; }
        public AuthUserSession setProfileUrl(String value) { this.profileUrl = value; return this; }
        public String getSequence() { return sequence; }
        public AuthUserSession setSequence(String value) { this.sequence = value; return this; }
        public Long getTag() { return tag; }
        public AuthUserSession setTag(Long value) { this.tag = value; return this; }
        public ArrayList<IAuthTokens> getProviderOAuthAccess() { return providerOAuthAccess; }
        public AuthUserSession setProviderOAuthAccess(ArrayList<IAuthTokens> value) { this.providerOAuthAccess = value; return this; }
    }

    public static class Device
    {
        public Long id = null;
        public String type = null;
        public Long timeStamp = null;
        public ArrayList<Channel> channels = null;

        public Long getId() { return id; }
        public Device setId(Long value) { this.id = value; return this; }
        public String getType() { return type; }
        public Device setType(String value) { this.type = value; return this; }
        public Long getTimeStamp() { return timeStamp; }
        public Device setTimeStamp(Long value) { this.timeStamp = value; return this; }
        public ArrayList<Channel> getChannels() { return channels; }
        public Device setChannels(ArrayList<Channel> value) { this.channels = value; return this; }
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

        @DataMember(Order=5)
        public String include = null;

        @DataMember(Order=6)
        public String fields = null;

        @DataMember(Order=7)
        public HashMap<String,String> meta = null;

        public Integer getSkip() { return skip; }
        public QueryBase setSkip(Integer value) { this.skip = value; return this; }
        public Integer getTake() { return take; }
        public QueryBase setTake(Integer value) { this.take = value; return this; }
        public String getOrderBy() { return orderBy; }
        public QueryBase setOrderBy(String value) { this.orderBy = value; return this; }
        public String getOrderByDesc() { return orderByDesc; }
        public QueryBase setOrderByDesc(String value) { this.orderByDesc = value; return this; }
        public String getInclude() { return include; }
        public QueryBase setInclude(String value) { this.include = value; return this; }
        public String getFields() { return fields; }
        public QueryBase setFields(String value) { this.fields = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public QueryBase setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    }

    public static class MenuItemExampleItem
    {
        @DataMember(Order=1)
        @ApiMember()
        public String name1 = null;

        public String getName1() { return name1; }
        public MenuItemExampleItem setName1(String value) { this.name1 = value; return this; }
    }

    public static class Channel
    {
        public String name = null;
        public String value = null;

        public String getName() { return name; }
        public Channel setName(String value) { this.name = value; return this; }
        public String getValue() { return value; }
        public Channel setValue(String value) { this.value = value; return this; }
    }

}
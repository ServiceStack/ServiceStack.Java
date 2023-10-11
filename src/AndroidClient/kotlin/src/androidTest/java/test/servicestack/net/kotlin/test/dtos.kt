/* Options:
Date: 2021-03-11 05:48:19
Version: 5.105
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: https://test.servicestack.net

Package: test.servicestack.net.kotlin.test
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion: 
//AddDescriptionAsComments: True
//IncludeTypes: 
//ExcludeTypes: 
//InitializeCollections: True
//TreatTypesAsStrings: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*,com.google.gson.reflect.*,java.io.*
*/

package test.servicestack.net.kotlin.test

import java.math.*
import java.util.*
import net.servicestack.client.*
import com.google.gson.annotations.*
import com.google.gson.reflect.*
import java.io.*


@Route("/channels/{Channel}/raw")
open class PostRawToChannel : IReturnVoid
{
    var from:String? = null
    var toUserId:String? = null
    var channel:String? = null
    var message:String? = null
    var selector:String? = null
}

@Route("/channels/{Channel}/chat")
open class PostChatToChannel : IReturn<ChatMessage>
{
    var from:String? = null
    var toUserId:String? = null
    var channel:String? = null
    var message:String? = null
    var selector:String? = null
    companion object { private val responseType = ChatMessage::class.java }
    override fun getResponseType(): Any? = PostChatToChannel.responseType
}

@Route("/chathistory")
open class GetChatHistory : IReturn<GetChatHistoryResponse>
{
    var channels:ArrayList<String>? = null
    var afterId:Long? = null
    var take:Int? = null
    companion object { private val responseType = GetChatHistoryResponse::class.java }
    override fun getResponseType(): Any? = GetChatHistory.responseType
}

@Route("/reset")
open class ClearChatHistory : IReturnVoid
{
}

@Route("/reset-serverevents")
open class ResetServerEvents : IReturnVoid
{
}

@Route("/channels/{Channel}/object")
open class PostObjectToChannel : IReturnVoid
{
    var toUserId:String? = null
    var channel:String? = null
    var selector:String? = null
    var customType:CustomType? = null
    var setterType:SetterType? = null
}

@Route("/account")
open class GetUserDetails : IReturn<GetUserDetailsResponse>
{
    companion object { private val responseType = GetUserDetailsResponse::class.java }
    override fun getResponseType(): Any? = GetUserDetails.responseType
}

open class CustomHttpError : IReturn<CustomHttpErrorResponse>
{
    var statusCode:Int? = null
    var statusDescription:String? = null
    companion object { private val responseType = CustomHttpErrorResponse::class.java }
    override fun getResponseType(): Any? = CustomHttpError.responseType
}

open class AltQueryItems : IReturn<QueryResponseAlt<Item>>
{
    var name:String? = null
    companion object { private val responseType = object : TypeToken<QueryResponseAlt<Item>>(){}.type }
    override fun getResponseType(): Any? = AltQueryItems.responseType
}

open class GetItems : IReturn<Items>
{
    companion object { private val responseType = Items::class.java }
    override fun getResponseType(): Any? = GetItems.responseType
}

open class GetNakedItems : IReturn<ArrayList<Item>>
{
    companion object { private val responseType = object : TypeToken<ArrayList<Item>>(){}.type }
    override fun getResponseType(): Any? = GetNakedItems.responseType
}

open class DummyTypes
{
    var helloResponses:ArrayList<HelloResponse> = ArrayList<HelloResponse>()
    var listResult:ArrayList<ListResult> = ArrayList<ListResult>()
    var arrayResult:ArrayList<ArrayResult>? = null
}

@Route("/throwhttperror/{Status}")
open class ThrowHttpError
{
    var status:Int? = null
    var message:String? = null
}

@Route("/throw404")
// @Route("/throw404/{Message}")
open class Throw404
{
    var message:String? = null
}

@Route("/throwcustom400")
// @Route("/throwcustom400/{Message}")
open class ThrowCustom400
{
    var message:String? = null
}

@Route("/throw/{Type}")
open class ThrowType : IReturn<ThrowTypeResponse>
{
    @SerializedName("type") var Type:String? = null
    var message:String? = null
    companion object { private val responseType = ThrowTypeResponse::class.java }
    override fun getResponseType(): Any? = ThrowType.responseType
}

@Route("/throwvalidation")
open class ThrowValidation : IReturn<ThrowValidationResponse>
{
    var age:Int? = null
    var required:String? = null
    var email:String? = null
    companion object { private val responseType = ThrowValidationResponse::class.java }
    override fun getResponseType(): Any? = ThrowValidation.responseType
}

@Route("/throwbusinesserror")
open class ThrowBusinessError : IReturn<ThrowBusinessErrorResponse>
{
    companion object { private val responseType = ThrowBusinessErrorResponse::class.java }
    override fun getResponseType(): Any? = ThrowBusinessError.responseType
}

open class RootPathRoutes
{
    var path:String? = null
}

open class GetAccount : IReturn<Account>
{
    var account:String? = null
    companion object { private val responseType = Account::class.java }
    override fun getResponseType(): Any? = GetAccount.responseType
}

open class GetProject : IReturn<Project>
{
    var account:String? = null
    var project:String? = null
    companion object { private val responseType = Project::class.java }
    override fun getResponseType(): Any? = GetProject.responseType
}

@Route("/image-stream")
open class ImageAsStream : IReturn<InputStream>
{
    var format:String? = null
    companion object { private val responseType = InputStream::class.java }
    override fun getResponseType(): Any? = ImageAsStream.responseType
}

@Route("/image-bytes")
open class ImageAsBytes : IReturn<ByteArray>
{
    var format:String? = null
    companion object { private val responseType = ByteArray::class.java }
    override fun getResponseType(): Any? = ImageAsBytes.responseType
}

@Route("/image-custom")
open class ImageAsCustomResult : IReturn<ByteArray>
{
    var format:String? = null
    companion object { private val responseType = ByteArray::class.java }
    override fun getResponseType(): Any? = ImageAsCustomResult.responseType
}

@Route("/image-response")
open class ImageWriteToResponse : IReturn<ByteArray>
{
    var format:String? = null
    companion object { private val responseType = ByteArray::class.java }
    override fun getResponseType(): Any? = ImageWriteToResponse.responseType
}

@Route("/image-file")
open class ImageAsFile : IReturn<ByteArray>
{
    var format:String? = null
    companion object { private val responseType = ByteArray::class.java }
    override fun getResponseType(): Any? = ImageAsFile.responseType
}

@Route("/image-redirect")
open class ImageAsRedirect
{
    var format:String? = null
}

@Route("/hello-image/{Name}")
open class HelloImage : IReturn<ByteArray>
{
    var name:String? = null
    var format:String? = null
    var width:Int? = null
    var height:Int? = null
    var fontSize:Int? = null
    var fontFamily:String? = null
    var foreground:String? = null
    var background:String? = null
    companion object { private val responseType = ByteArray::class.java }
    override fun getResponseType(): Any? = HelloImage.responseType
}

@Route("/secured")
@ValidateRequest(Validator="IsAuthenticated")
open class Secured : IReturn<SecuredResponse>
{
    var name:String? = null
    companion object { private val responseType = SecuredResponse::class.java }
    override fun getResponseType(): Any? = Secured.responseType
}

@Route("/jwt")
open class CreateJwt : AuthUserSession(), IReturn<CreateJwtResponse>
{
    var jwtExpiry:Date? = null
    companion object { private val responseType = CreateJwtResponse::class.java }
    override fun getResponseType(): Any? = CreateJwt.responseType
}

@Route("/jwt-refresh")
open class CreateRefreshJwt : IReturn<CreateRefreshJwtResponse>
{
    var userAuthId:String? = null
    var jwtExpiry:Date? = null
    companion object { private val responseType = CreateRefreshJwtResponse::class.java }
    override fun getResponseType(): Any? = CreateRefreshJwt.responseType
}

@Route("/jwt-invalidate")
open class InvalidateLastAccessToken : IReturn<EmptyResponse>
{
    companion object { private val responseType = EmptyResponse::class.java }
    override fun getResponseType(): Any? = InvalidateLastAccessToken.responseType
}

@Route("/logs")
open class ViewLogs : IReturn<String>
{
    var clear:Boolean? = null
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = ViewLogs.responseType
}

@Route("/metadatatest")
open class MetadataTest : IReturn<MetadataTestResponse>
{
    var id:Int? = null
    companion object { private val responseType = MetadataTestResponse::class.java }
    override fun getResponseType(): Any? = MetadataTest.responseType
}

@Route("/metadatatest-array")
open class MetadataTestArray : IReturn<ArrayList<MetadataTestChild>>
{
    var id:Int? = null
    companion object { private val responseType = object : TypeToken<ArrayList<MetadataTestChild>>(){}.type }
    override fun getResponseType(): Any? = MetadataTestArray.responseType
}

@Route(Path="/example", Verbs="GET")
@DataContract
open class GetExample : IReturn<GetExampleResponse>
{
    companion object { private val responseType = GetExampleResponse::class.java }
    override fun getResponseType(): Any? = GetExample.responseType
}

@Route("/randomids")
open class GetRandomIds : IReturn<GetRandomIdsResponse>
{
    var take:Int? = null
    companion object { private val responseType = GetRandomIdsResponse::class.java }
    override fun getResponseType(): Any? = GetRandomIds.responseType
}

@Route("/textfile-test")
open class TextFileTest
{
    var asAttachment:Boolean? = null
}

@Route("/return/text")
open class ReturnText
{
    var text:String? = null
}

@Route("/return/html")
open class ReturnHtml
{
    var text:String? = null
}

@Route("/hello")
// @Route("/hello/{Name}")
open class Hello : IReturn<HelloResponse>
{
    @Required()
    var name:String? = null

    var title:String? = null
    companion object { private val responseType = HelloResponse::class.java }
    override fun getResponseType(): Any? = Hello.responseType
}

/**
* Description on HelloAll type
*/
@DataContract
open class HelloAnnotated : IReturn<HelloAnnotatedResponse>
{
    @DataMember
    var name:String? = null
    companion object { private val responseType = HelloAnnotatedResponse::class.java }
    override fun getResponseType(): Any? = HelloAnnotated.responseType
}

open class HelloWithNestedClass : IReturn<HelloResponse>
{
    var name:String? = null
    var nestedClassProp:NestedClass? = null
    companion object { private val responseType = HelloResponse::class.java }
    override fun getResponseType(): Any? = HelloWithNestedClass.responseType
}

open class HelloList : IReturn<ArrayList<ListResult>>
{
    var names:ArrayList<String> = ArrayList<String>()
    companion object { private val responseType = object : TypeToken<ArrayList<ListResult>>(){}.type }
    override fun getResponseType(): Any? = HelloList.responseType
}

open class HelloArray : IReturn<ArrayList<ArrayResult>>
{
    var names:ArrayList<String> = ArrayList<String>()
    companion object { private val responseType = object : TypeToken<ArrayList<ArrayResult>>(){}.type }
    override fun getResponseType(): Any? = HelloArray.responseType
}

open class HelloWithEnum
{
    var enumProp:EnumType? = null
    var enumTypeFlags:EnumTypeFlags? = null
    var enumWithValues:EnumWithValues? = null
    var nullableEnumProp:EnumType? = null
    var enumFlags:EnumFlags? = null
    var enumAsInt:EnumAsInt? = null
    var enumStyle:EnumStyle? = null
    var enumStyleMembers:EnumStyleMembers? = null
}

open class HelloWithEnumList
{
    var enumProp:ArrayList<EnumType> = ArrayList<EnumType>()
    var enumWithValues:ArrayList<EnumWithValues> = ArrayList<EnumWithValues>()
    var nullableEnumProp:ArrayList<EnumType?> = ArrayList<EnumType?>()
    var enumFlags:ArrayList<EnumFlags> = ArrayList<EnumFlags>()
    var enumStyle:ArrayList<EnumStyle> = ArrayList<EnumStyle>()
}

open class HelloWithEnumMap
{
    var enumProp:HashMap<EnumType,EnumType> = HashMap<EnumType,EnumType>()
    var enumWithValues:HashMap<EnumWithValues,EnumWithValues> = HashMap<EnumWithValues,EnumWithValues>()
    var nullableEnumProp:HashMap<EnumType?,EnumType?> = HashMap<EnumType?,EnumType?>()
    var enumFlags:HashMap<EnumFlags,EnumFlags> = HashMap<EnumFlags,EnumFlags>()
    var enumStyle:HashMap<EnumStyle,EnumStyle> = HashMap<EnumStyle,EnumStyle>()
}

open class RestrictedAttributes
{
    var id:Int? = null
    var name:String? = null
    var hello:Hello? = null
}

/**
* AllowedAttributes Description
*/
@Route(Path="/allowed-attributes", Verbs="GET")
@Api(Description="AllowedAttributes Description")
@ApiResponse(Description="Your request was not understood", StatusCode=400)
@DataContract
open class AllowedAttributes
{
    /**
    * Range Description
    */
    @DataMember(Name="Aliased")
    @SerializedName("Aliased")
    @ApiMember(DataType="double", Description="Range Description", IsRequired=true, ParameterType="path")
    var range:Double? = null
}

@Route("/all-types")
open class HelloAllTypes : IReturn<HelloAllTypesResponse>
{
    var name:String? = null
    var allTypes:AllTypes? = null
    var allCollectionTypes:AllCollectionTypes? = null
    companion object { private val responseType = HelloAllTypesResponse::class.java }
    override fun getResponseType(): Any? = HelloAllTypes.responseType
}

open class HelloSubAllTypes : AllTypesBase(), IReturn<SubAllTypes>
{
    var hierarchy:Int? = null
    companion object { private val responseType = SubAllTypes::class.java }
    override fun getResponseType(): Any? = HelloSubAllTypes.responseType
}

open class AllTypes : IReturn<AllTypes>
{
    var id:Int? = null
    var nullableId:Int? = null
    @SerializedName("byte") var Byte:Short? = null
    @SerializedName("short") var Short:Short? = null
    @SerializedName("int") var Int:Int? = null
    @SerializedName("long") var Long:Long? = null
    var uShort:Int? = null
    var uInt:Long? = null
    var uLong:BigInteger? = null
    @SerializedName("float") var Float:Float? = null
    @SerializedName("double") var Double:Double? = null
    var decimal:BigDecimal? = null
    var string:String? = null
    var dateTime:Date? = null
    var timeSpan:TimeSpan? = null
    var dateTimeOffset:Date? = null
    var guid:UUID? = null
    @SerializedName("char") var Char:String? = null
    var keyValuePair:KeyValuePair<String, String>? = null
    var nullableDateTime:Date? = null
    var nullableTimeSpan:TimeSpan? = null
    var stringList:ArrayList<String> = ArrayList<String>()
    var stringArray:ArrayList<String>? = null
    var stringMap:HashMap<String,String> = HashMap<String,String>()
    var intStringMap:HashMap<Int,String> = HashMap<Int,String>()
    var subType:SubType? = null
    companion object { private val responseType = AllTypes::class.java }
    override fun getResponseType(): Any? = AllTypes.responseType
}

open class AllCollectionTypes : IReturn<AllCollectionTypes>
{
    var intArray:ArrayList<Int>? = null
    var intList:ArrayList<Int> = ArrayList<Int>()
    var stringArray:ArrayList<String>? = null
    var stringList:ArrayList<String> = ArrayList<String>()
    var floatArray:ArrayList<Float>? = null
    var doubleList:ArrayList<Double> = ArrayList<Double>()
    var byteArray:ByteArray? = null
    var charArray:ArrayList<String>? = null
    var decimalList:ArrayList<BigDecimal> = ArrayList<BigDecimal>()
    var pocoArray:ArrayList<Poco>? = null
    var pocoList:ArrayList<Poco> = ArrayList<Poco>()
    var pocoLookup:HashMap<String,ArrayList<Poco>> = HashMap<String,ArrayList<Poco>>()
    var pocoLookupMap:HashMap<String,ArrayList<HashMap<String,Poco>>> = HashMap<String,ArrayList<HashMap<String,Poco>>>()
    companion object { private val responseType = AllCollectionTypes::class.java }
    override fun getResponseType(): Any? = AllCollectionTypes.responseType
}

open class HelloString : IReturn<String>
{
    var name:String? = null
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = HelloString.responseType
}

open class HelloDateTime : IReturn<HelloDateTime>
{
    var dateTime:Date? = null
    companion object { private val responseType = HelloDateTime::class.java }
    override fun getResponseType(): Any? = HelloDateTime.responseType
}

open class HelloVoid
{
    var name:String? = null
}

@DataContract
open class HelloWithDataContract : IReturn<HelloWithDataContractResponse>
{
    @DataMember(Name="name", Order=1, IsRequired=true, EmitDefaultValue=false)
    @SerializedName("name")
    var name:String? = null

    @DataMember(Name="id", Order=2, EmitDefaultValue=false)
    @SerializedName("id")
    var id:Int? = null
    companion object { private val responseType = HelloWithDataContractResponse::class.java }
    override fun getResponseType(): Any? = HelloWithDataContract.responseType
}

/**
* Description on HelloWithDescription type
*/
open class HelloWithDescription : IReturn<HelloWithDescriptionResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloWithDescriptionResponse::class.java }
    override fun getResponseType(): Any? = HelloWithDescription.responseType
}

open class HelloWithInheritance : HelloBase(), IReturn<HelloWithInheritanceResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloWithInheritanceResponse::class.java }
    override fun getResponseType(): Any? = HelloWithInheritance.responseType
}

open class HelloWithGenericInheritance : HelloBase_1<Poco>()
{
    var result:String? = null
}

open class HelloWithGenericInheritance2 : HelloBase_1<Hello>()
{
    var result:String? = null
}

open class HelloWithNestedInheritance : HelloBase_1<Item>()
{
}

open class HelloWithReturn : IReturn<HelloWithAlternateReturnResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloWithAlternateReturnResponse::class.java }
    override fun getResponseType(): Any? = HelloWithReturn.responseType
}

@Route("/helloroute")
open class HelloWithRoute : IReturn<HelloWithRouteResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloWithRouteResponse::class.java }
    override fun getResponseType(): Any? = HelloWithRoute.responseType
}

open class HelloWithType : IReturn<HelloWithTypeResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloWithTypeResponse::class.java }
    override fun getResponseType(): Any? = HelloWithType.responseType
}

open class HelloInterface
{
    var poco:IPoco? = null
    var emptyInterface:IEmptyInterface? = null
    var emptyClass:EmptyClass? = null
}

open class HelloInnerTypes : IReturn<HelloInnerTypesResponse>
{
    companion object { private val responseType = HelloInnerTypesResponse::class.java }
    override fun getResponseType(): Any? = HelloInnerTypes.responseType
}

open class HelloBuiltin
{
    var dayOfWeek:DayOfWeek? = null
}

open class HelloGet : IReturn<HelloVerbResponse>, IGet
{
    var id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = HelloGet.responseType
}

open class HelloPost : HelloBase(), IReturn<HelloVerbResponse>, IPost
{
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = HelloPost.responseType
}

open class HelloPut : IReturn<HelloVerbResponse>, IPut
{
    var id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = HelloPut.responseType
}

open class HelloDelete : IReturn<HelloVerbResponse>, IDelete
{
    var id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = HelloDelete.responseType
}

open class HelloPatch : IReturn<HelloVerbResponse>, IPatch
{
    var id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = HelloPatch.responseType
}

open class HelloReturnVoid : IReturnVoid
{
    var id:Int? = null
}

open class EnumRequest : IReturn<EnumResponse>, IPut
{
    @SerializedName("operator") var Operator:ScopeType? = null
    companion object { private val responseType = EnumResponse::class.java }
    override fun getResponseType(): Any? = EnumRequest.responseType
}

@Route("/hellotypes/{Name}")
open class HelloTypes : IReturn<HelloTypes>
{
    var string:String? = null
    var bool:Boolean? = null
    @SerializedName("int") var Int:Int? = null
    companion object { private val responseType = HelloTypes::class.java }
    override fun getResponseType(): Any? = HelloTypes.responseType
}

@Route("/hellozip")
@DataContract
open class HelloZip : IReturn<HelloZipResponse>
{
    @DataMember
    var name:String? = null

    @DataMember
    var test:ArrayList<String> = ArrayList<String>()
    companion object { private val responseType = HelloZipResponse::class.java }
    override fun getResponseType(): Any? = HelloZip.responseType
}

@Route("/ping")
open class Ping : IReturn<PingResponse>
{
    companion object { private val responseType = PingResponse::class.java }
    override fun getResponseType(): Any? = Ping.responseType
}

@Route("/reset-connections")
open class ResetConnections
{
}

@Route("/requires-role")
open class RequiresRole : IReturn<RequiresRoleResponse>
{
    companion object { private val responseType = RequiresRoleResponse::class.java }
    override fun getResponseType(): Any? = RequiresRole.responseType
}

@Route("/return/string")
open class ReturnString : IReturn<String>
{
    @SerializedName("data") var Data:String? = null
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = ReturnString.responseType
}

@Route("/return/bytes")
open class ReturnBytes : IReturn<ByteArray>
{
    @SerializedName("data") var Data:ByteArray? = null
    companion object { private val responseType = ByteArray::class.java }
    override fun getResponseType(): Any? = ReturnBytes.responseType
}

@Route("/return/stream")
open class ReturnStream : IReturn<InputStream>
{
    @SerializedName("data") var Data:ByteArray? = null
    companion object { private val responseType = InputStream::class.java }
    override fun getResponseType(): Any? = ReturnStream.responseType
}

@Route(Path="/Request1", Verbs="GET")
open class GetRequest1 : IReturn<ArrayList<ReturnedDto>>, IGet
{
    companion object { private val responseType = object : TypeToken<ArrayList<ReturnedDto>>(){}.type }
    override fun getResponseType(): Any? = GetRequest1.responseType
}

@Route(Path="/Request2", Verbs="GET")
open class GetRequest2 : IReturn<ArrayList<ReturnedDto>>, IGet
{
    companion object { private val responseType = object : TypeToken<ArrayList<ReturnedDto>>(){}.type }
    override fun getResponseType(): Any? = GetRequest2.responseType
}

@Route("/sendjson")
open class SendJson : IReturn<String>
{
    var id:Int? = null
    var name:String? = null
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = SendJson.responseType
}

@Route("/sendtext")
open class SendText : IReturn<String>
{
    var id:Int? = null
    var name:String? = null
    var contentType:String? = null
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = SendText.responseType
}

@Route("/sendraw")
open class SendRaw : IReturn<ByteArray>
{
    var id:Int? = null
    var name:String? = null
    var contentType:String? = null
    companion object { private val responseType = ByteArray::class.java }
    override fun getResponseType(): Any? = SendRaw.responseType
}

open class SendDefault : IReturn<SendVerbResponse>
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = SendDefault.responseType
}

@Route(Path="/sendrestget/{Id}", Verbs="GET")
open class SendRestGet : IReturn<SendVerbResponse>, IGet
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = SendRestGet.responseType
}

open class SendGet : IReturn<SendVerbResponse>, IGet
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = SendGet.responseType
}

open class SendPost : IReturn<SendVerbResponse>, IPost
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = SendPost.responseType
}

open class SendPut : IReturn<SendVerbResponse>, IPut
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = SendPut.responseType
}

open class SendReturnVoid : IReturnVoid
{
    var id:Int? = null
}

@Route("/session")
open class GetSession : IReturn<GetSessionResponse>
{
    companion object { private val responseType = GetSessionResponse::class.java }
    override fun getResponseType(): Any? = GetSession.responseType
}

@Route("/session/edit/{CustomName}")
open class UpdateSession : IReturn<GetSessionResponse>
{
    var customName:String? = null
    companion object { private val responseType = GetSessionResponse::class.java }
    override fun getResponseType(): Any? = UpdateSession.responseType
}

@Route("/Stuff")
@DataContract(Namespace="http://schemas.servicestack.net/types")
open class GetStuff : IReturn<GetStuffResponse>
{
    @DataMember
    @ApiMember(DataType="DateTime", Name="Summary Date")
    var summaryDate:Date? = null

    @DataMember
    @ApiMember(DataType="DateTime", Name="Summary End Date")
    var summaryEndDate:Date? = null

    @DataMember
    @ApiMember(DataType="string", Name="Symbol")
    var symbol:String? = null

    @DataMember
    @ApiMember(DataType="string", Name="Email")
    var email:String? = null

    @DataMember
    @ApiMember(DataType="bool", Name="Is Enabled")
    var isEnabled:Boolean? = null
    companion object { private val responseType = GetStuffResponse::class.java }
    override fun getResponseType(): Any? = GetStuff.responseType
}

open class StoreLogs : IReturn<StoreLogsResponse>
{
    var loggers:ArrayList<Logger> = ArrayList<Logger>()
    companion object { private val responseType = StoreLogsResponse::class.java }
    override fun getResponseType(): Any? = StoreLogs.responseType
}

open class HelloAuth : IReturn<HelloResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloResponse::class.java }
    override fun getResponseType(): Any? = HelloAuth.responseType
}

@Route("/testauth")
open class TestAuth : IReturn<TestAuthResponse>
{
    companion object { private val responseType = TestAuthResponse::class.java }
    override fun getResponseType(): Any? = TestAuth.responseType
}

open class RequiresAdmin : IReturn<RequiresAdmin>
{
    var id:Int? = null
    companion object { private val responseType = RequiresAdmin::class.java }
    override fun getResponseType(): Any? = RequiresAdmin.responseType
}

@Route("/testdata/AllTypes")
open class TestDataAllTypes : IReturn<AllTypes>
{
    companion object { private val responseType = AllTypes::class.java }
    override fun getResponseType(): Any? = TestDataAllTypes.responseType
}

@Route("/testdata/AllCollectionTypes")
open class TestDataAllCollectionTypes : IReturn<AllCollectionTypes>
{
    companion object { private val responseType = AllCollectionTypes::class.java }
    override fun getResponseType(): Any? = TestDataAllCollectionTypes.responseType
}

@Route("/custom")
// @Route("/custom/{Data}")
open class CustomRoute : IReturn<CustomRoute>
{
    @SerializedName("data") var Data:String? = null
    companion object { private val responseType = CustomRoute::class.java }
    override fun getResponseType(): Any? = CustomRoute.responseType
}

@Route("/void-response")
open class TestVoidResponse
{
}

@Route("/null-response")
open class TestNullResponse
{
}

@Route("/wait/{ForMs}")
open class Wait : IReturn<Wait>
{
    var forMs:Int? = null
    companion object { private val responseType = Wait::class.java }
    override fun getResponseType(): Any? = Wait.responseType
}

@Route("/echo/types")
open class EchoTypes : IReturn<EchoTypes>
{
    @SerializedName("byte") var Byte:Short? = null
    @SerializedName("short") var Short:Short? = null
    @SerializedName("int") var Int:Int? = null
    @SerializedName("long") var Long:Long? = null
    var uShort:Int? = null
    var uInt:Long? = null
    var uLong:BigInteger? = null
    @SerializedName("float") var Float:Float? = null
    @SerializedName("double") var Double:Double? = null
    var decimal:BigDecimal? = null
    var string:String? = null
    var dateTime:Date? = null
    var timeSpan:TimeSpan? = null
    var dateTimeOffset:Date? = null
    var guid:UUID? = null
    @SerializedName("char") var Char:String? = null
    companion object { private val responseType = EchoTypes::class.java }
    override fun getResponseType(): Any? = EchoTypes.responseType
}

@Route("/echo/collections")
open class EchoCollections : IReturn<EchoCollections>
{
    var stringList:ArrayList<String> = ArrayList<String>()
    var stringArray:ArrayList<String>? = null
    var stringMap:HashMap<String,String> = HashMap<String,String>()
    var intStringMap:HashMap<Int,String> = HashMap<Int,String>()
    companion object { private val responseType = EchoCollections::class.java }
    override fun getResponseType(): Any? = EchoCollections.responseType
}

@Route("/echo/complex")
open class EchoComplexTypes : IReturn<EchoComplexTypes>
{
    var subType:SubType? = null
    var subTypes:ArrayList<SubType> = ArrayList<SubType>()
    var subTypeMap:HashMap<String,SubType> = HashMap<String,SubType>()
    var stringMap:HashMap<String,String> = HashMap<String,String>()
    var intStringMap:HashMap<Int,String> = HashMap<Int,String>()
    companion object { private val responseType = EchoComplexTypes::class.java }
    override fun getResponseType(): Any? = EchoComplexTypes.responseType
}

@Route(Path="/rockstars", Verbs="POST")
open class StoreRockstars : ArrayList<Rockstar>(), IReturn<StoreRockstars>
{
    companion object { private val responseType = StoreRockstars::class.java }
    override fun getResponseType(): Any? = StoreRockstars.responseType
}

@Route("/auth")
// @Route("/auth/{provider}")
@DataContract
open class Authenticate : IReturn<AuthenticateResponse>, IPost
{
    @DataMember(Order=1)
    var provider:String? = null

    @DataMember(Order=2)
    var state:String? = null

    @DataMember(Order=3)
    var oauth_token:String? = null

    @DataMember(Order=4)
    var oauth_verifier:String? = null

    @DataMember(Order=5)
    var userName:String? = null

    @DataMember(Order=6)
    var password:String? = null

    @DataMember(Order=7)
    var rememberMe:Boolean? = null

    @DataMember(Order=9)
    var errorView:String? = null

    @DataMember(Order=10)
    var nonce:String? = null

    @DataMember(Order=11)
    var uri:String? = null

    @DataMember(Order=12)
    var response:String? = null

    @DataMember(Order=13)
    var qop:String? = null

    @DataMember(Order=14)
    var nc:String? = null

    @DataMember(Order=15)
    var cnonce:String? = null

    @DataMember(Order=16)
    var useTokenCookie:Boolean? = null

    @DataMember(Order=17)
    var accessToken:String? = null

    @DataMember(Order=18)
    var accessTokenSecret:String? = null

    @DataMember(Order=19)
    var scope:String? = null

    @DataMember(Order=20)
    var meta:HashMap<String,String> = HashMap<String,String>()
    companion object { private val responseType = AuthenticateResponse::class.java }
    override fun getResponseType(): Any? = Authenticate.responseType
}

@Route("/assignroles")
@DataContract
open class AssignRoles : IReturn<AssignRolesResponse>, IPost
{
    @DataMember(Order=1)
    var userName:String? = null

    @DataMember(Order=2)
    var permissions:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=3)
    var roles:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=4)
    var meta:HashMap<String,String> = HashMap<String,String>()
    companion object { private val responseType = AssignRolesResponse::class.java }
    override fun getResponseType(): Any? = AssignRoles.responseType
}

@Route("/unassignroles")
@DataContract
open class UnAssignRoles : IReturn<UnAssignRolesResponse>, IPost
{
    @DataMember(Order=1)
    var userName:String? = null

    @DataMember(Order=2)
    var permissions:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=3)
    var roles:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=4)
    var meta:HashMap<String,String> = HashMap<String,String>()
    companion object { private val responseType = UnAssignRolesResponse::class.java }
    override fun getResponseType(): Any? = UnAssignRoles.responseType
}

@Route("/session-to-token")
@DataContract
open class ConvertSessionToToken : IReturn<ConvertSessionToTokenResponse>, IPost
{
    @DataMember(Order=1)
    var preserveSession:Boolean? = null

    @DataMember(Order=2)
    var meta:HashMap<String,String> = HashMap<String,String>()
    companion object { private val responseType = ConvertSessionToTokenResponse::class.java }
    override fun getResponseType(): Any? = ConvertSessionToToken.responseType
}

@Route("/access-token")
@DataContract
open class GetAccessToken : IReturn<GetAccessTokenResponse>, IPost
{
    @DataMember(Order=1)
    var refreshToken:String? = null

    @DataMember(Order=2)
    var useTokenCookie:Boolean? = null

    @DataMember(Order=3)
    var meta:HashMap<String,String> = HashMap<String,String>()
    companion object { private val responseType = GetAccessTokenResponse::class.java }
    override fun getResponseType(): Any? = GetAccessToken.responseType
}

open class QueryRockstarAudit : QueryDbTenant_2<RockstarAuditTenant, RockstarAuto>(), IReturn<QueryResponse<RockstarAuto>>
{
    var id:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<RockstarAuto>>(){}.type }
    override fun getResponseType(): Any? = QueryRockstarAudit.responseType
}

open class QueryRockstarAuditSubOr : QueryDb_2<RockstarAuditTenant, RockstarAuto>(), IReturn<QueryResponse<RockstarAuto>>
{
    var firstNameStartsWith:String? = null
    var ageOlderThan:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<RockstarAuto>>(){}.type }
    override fun getResponseType(): Any? = QueryRockstarAuditSubOr.responseType
}

open class QueryPocoBase : QueryDb_1<OnlyDefinedInGenericType>(), IReturn<QueryResponse<OnlyDefinedInGenericType>>
{
    var id:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<OnlyDefinedInGenericType>>(){}.type }
    override fun getResponseType(): Any? = QueryPocoBase.responseType
}

open class QueryPocoIntoBase : QueryDb_2<OnlyDefinedInGenericTypeFrom, OnlyDefinedInGenericTypeInto>(), IReturn<QueryResponse<OnlyDefinedInGenericTypeInto>>
{
    var id:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<OnlyDefinedInGenericTypeInto>>(){}.type }
    override fun getResponseType(): Any? = QueryPocoIntoBase.responseType
}

@Route(Path="/rockstars", Verbs="GET")
open class QueryRockstars : QueryDb_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = QueryRockstars.responseType
}

open class CreateRockstarAudit : RockstarBase(), IReturn<RockstarWithIdResponse>, ICreateDb<RockstarAudit>
{
    companion object { private val responseType = RockstarWithIdResponse::class.java }
    override fun getResponseType(): Any? = CreateRockstarAudit.responseType
}

open class CreateRockstarAuditTenant : CreateAuditTenantBase<RockstarAuditTenant, RockstarWithIdAndResultResponse>(), IReturn<RockstarWithIdAndResultResponse>, IHasSessionId
{
    var sessionId:String? = null
    var firstName:String? = null
    var lastName:String? = null
    var age:Int? = null
    var dateOfBirth:Date? = null
    var dateDied:Date? = null
    var livingStatus:LivingStatus? = null
    companion object { private val responseType = RockstarWithIdAndResultResponse::class.java }
    override fun getResponseType(): Any? = CreateRockstarAuditTenant.responseType
}

open class UpdateRockstarAuditTenant : UpdateAuditTenantBase<RockstarAuditTenant, RockstarWithIdAndResultResponse>(), IReturn<RockstarWithIdAndResultResponse>, IHasSessionId
{
    var sessionId:String? = null
    var id:Int? = null
    var firstName:String? = null
    var livingStatus:LivingStatus? = null
    companion object { private val responseType = RockstarWithIdAndResultResponse::class.java }
    override fun getResponseType(): Any? = UpdateRockstarAuditTenant.responseType
}

open class PatchRockstarAuditTenant : PatchAuditTenantBase<RockstarAuditTenant, RockstarWithIdAndResultResponse>(), IReturn<RockstarWithIdAndResultResponse>, IHasSessionId
{
    var sessionId:String? = null
    var id:Int? = null
    var firstName:String? = null
    var livingStatus:LivingStatus? = null
    companion object { private val responseType = RockstarWithIdAndResultResponse::class.java }
    override fun getResponseType(): Any? = PatchRockstarAuditTenant.responseType
}

open class SoftDeleteAuditTenant : SoftDeleteAuditTenantBase<RockstarAuditTenant, RockstarWithIdAndResultResponse>(), IReturn<RockstarWithIdAndResultResponse>
{
    var id:Int? = null
    companion object { private val responseType = RockstarWithIdAndResultResponse::class.java }
    override fun getResponseType(): Any? = SoftDeleteAuditTenant.responseType
}

open class CreateRockstarAuditMqToken : RockstarBase(), IReturn<RockstarWithIdResponse>, ICreateDb<RockstarAudit>, IHasBearerToken
{
    var bearerToken:String? = null
    companion object { private val responseType = RockstarWithIdResponse::class.java }
    override fun getResponseType(): Any? = CreateRockstarAuditMqToken.responseType
}

open class RealDeleteAuditTenant : IReturn<RockstarWithIdAndCountResponse>, IDeleteDb<RockstarAuditTenant>, IHasSessionId
{
    var sessionId:String? = null
    var id:Int? = null
    var age:Int? = null
    companion object { private val responseType = RockstarWithIdAndCountResponse::class.java }
    override fun getResponseType(): Any? = RealDeleteAuditTenant.responseType
}

open class CreateRockstarVersion : RockstarBase(), IReturn<RockstarWithIdAndRowVersionResponse>, ICreateDb<RockstarVersion>
{
    companion object { private val responseType = RockstarWithIdAndRowVersionResponse::class.java }
    override fun getResponseType(): Any? = CreateRockstarVersion.responseType
}

open class ChatMessage
{
    var id:Long? = null
    var channel:String? = null
    var fromUserId:String? = null
    var fromName:String? = null
    var displayName:String? = null
    var message:String? = null
    var userAuthId:String? = null
    @SerializedName("private") var Private:Boolean? = null
}

open class GetChatHistoryResponse
{
    var results:ArrayList<ChatMessage> = ArrayList<ChatMessage>()
    var responseStatus:ResponseStatus? = null
}

open class GetUserDetailsResponse
{
    var provider:String? = null
    var userId:String? = null
    var userName:String? = null
    var fullName:String? = null
    var displayName:String? = null
    var firstName:String? = null
    var lastName:String? = null
    var company:String? = null
    var email:String? = null
    var phoneNumber:String? = null
    var birthDate:Date? = null
    var birthDateRaw:String? = null
    var address:String? = null
    var address2:String? = null
    var city:String? = null
    var state:String? = null
    var country:String? = null
    var culture:String? = null
    var gender:String? = null
    var language:String? = null
    var mailAddress:String? = null
    var nickname:String? = null
    var postalCode:String? = null
    var timeZone:String? = null
}

open class CustomHttpErrorResponse
{
    var custom:String? = null
    var responseStatus:ResponseStatus? = null
}

open class QueryResponseAlt<T>
{
    var offset:Int? = null
    var total:Int? = null
    var results:ArrayList<T> = ArrayList<T>()
    var meta:HashMap<String,String> = HashMap<String,String>()
    var responseStatus:ResponseStatus? = null
}

open class Items
{
    var results:ArrayList<Item> = ArrayList<Item>()
}

open class ThrowTypeResponse
{
    var responseStatus:ResponseStatus? = null
}

open class ThrowValidationResponse
{
    var age:Int? = null
    var required:String? = null
    var email:String? = null
    var responseStatus:ResponseStatus? = null
}

open class ThrowBusinessErrorResponse
{
    var responseStatus:ResponseStatus? = null
}

open class Account
{
    var name:String? = null
}

open class Project
{
    var account:String? = null
    var name:String? = null
}

open class SecuredResponse
{
    var result:String? = null
    var responseStatus:ResponseStatus? = null
}

open class CreateJwtResponse
{
    var token:String? = null
    var responseStatus:ResponseStatus? = null
}

open class CreateRefreshJwtResponse
{
    var token:String? = null
    var responseStatus:ResponseStatus? = null
}

@DataContract
open class EmptyResponse
{
    @DataMember(Order=1)
    var responseStatus:ResponseStatus? = null
}

open class MetadataTestResponse
{
    var id:Int? = null
    var results:ArrayList<MetadataTestChild> = ArrayList<MetadataTestChild>()
}

@DataContract
open class GetExampleResponse
{
    @DataMember(Order=1)
    var responseStatus:ResponseStatus? = null

    @DataMember(Order=2)
    @ApiMember()
    var menuExample1:MenuExample? = null
}

open class GetRandomIdsResponse
{
    var results:ArrayList<String> = ArrayList<String>()
}

open class HelloResponse
{
    var result:String? = null
}

/**
* Description on HelloAllResponse type
*/
@DataContract
open class HelloAnnotatedResponse
{
    @DataMember
    var result:String? = null
}

open class HelloAllTypesResponse
{
    var result:String? = null
    var allTypes:AllTypes? = null
    var allCollectionTypes:AllCollectionTypes? = null
}

open class SubAllTypes : AllTypesBase()
{
    var hierarchy:Int? = null
}

@DataContract
open class HelloWithDataContractResponse
{
    @DataMember(Name="result", Order=1, IsRequired=true, EmitDefaultValue=false)
    @SerializedName("result")
    var result:String? = null
}

/**
* Description on HelloWithDescriptionResponse type
*/
open class HelloWithDescriptionResponse
{
    var result:String? = null
}

open class HelloWithInheritanceResponse : HelloResponseBase()
{
    var result:String? = null
}

open class HelloWithAlternateReturnResponse : HelloWithReturnResponse()
{
    var altResult:String? = null
}

open class HelloWithRouteResponse
{
    var result:String? = null
}

open class HelloWithTypeResponse
{
    var result:HelloType? = null
}

open class HelloInnerTypesResponse
{
    var innerType:InnerType? = null
    var innerEnum:InnerEnum? = null
}

open class HelloVerbResponse
{
    var result:String? = null
}

open class EnumResponse
{
    @SerializedName("operator") var Operator:ScopeType? = null
}

@DataContract
open class HelloZipResponse
{
    @DataMember
    var result:String? = null
}

open class PingResponse
{
    var responses:HashMap<String,ResponseStatus> = HashMap<String,ResponseStatus>()
    var responseStatus:ResponseStatus? = null
}

open class RequiresRoleResponse
{
    var result:String? = null
    var responseStatus:ResponseStatus? = null
}

open class SendVerbResponse
{
    var id:Int? = null
    var pathInfo:String? = null
    var requestMethod:String? = null
}

open class GetSessionResponse
{
    var result:CustomUserSession? = null
    var unAuthInfo:UnAuthInfo? = null
    var responseStatus:ResponseStatus? = null
}

@DataContract(Namespace="http://schemas.servicestack.net/types")
open class GetStuffResponse
{
    @DataMember
    var summaryDate:Date? = null

    @DataMember
    var summaryEndDate:Date? = null

    @DataMember
    var symbol:String? = null

    @DataMember
    var email:String? = null

    @DataMember
    var isEnabled:Boolean? = null
}

open class StoreLogsResponse
{
    var existingLogs:ArrayList<Logger> = ArrayList<Logger>()
    var responseStatus:ResponseStatus? = null
}

open class TestAuthResponse
{
    var userId:String? = null
    var sessionId:String? = null
    var userName:String? = null
    var displayName:String? = null
    var responseStatus:ResponseStatus? = null
}

@DataContract
open class AuthenticateResponse : IHasSessionId, IHasBearerToken
{
    @DataMember(Order=1)
    var userId:String? = null

    @DataMember(Order=2)
    var sessionId:String? = null

    @DataMember(Order=3)
    var userName:String? = null

    @DataMember(Order=4)
    var displayName:String? = null

    @DataMember(Order=5)
    var referrerUrl:String? = null

    @DataMember(Order=6)
    var bearerToken:String? = null

    @DataMember(Order=7)
    var refreshToken:String? = null

    @DataMember(Order=8)
    var profileUrl:String? = null

    @DataMember(Order=9)
    var roles:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=10)
    var permissions:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=11)
    var responseStatus:ResponseStatus? = null

    @DataMember(Order=12)
    var meta:HashMap<String,String> = HashMap<String,String>()
}

@DataContract
open class AssignRolesResponse
{
    @DataMember(Order=1)
    var allRoles:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=2)
    var allPermissions:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=3)
    var meta:HashMap<String,String> = HashMap<String,String>()

    @DataMember(Order=4)
    var responseStatus:ResponseStatus? = null
}

@DataContract
open class UnAssignRolesResponse
{
    @DataMember(Order=1)
    var allRoles:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=2)
    var allPermissions:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=3)
    var meta:HashMap<String,String> = HashMap<String,String>()

    @DataMember(Order=4)
    var responseStatus:ResponseStatus? = null
}

@DataContract
open class ConvertSessionToTokenResponse
{
    @DataMember(Order=1)
    var meta:HashMap<String,String> = HashMap<String,String>()

    @DataMember(Order=2)
    var accessToken:String? = null

    @DataMember(Order=3)
    var refreshToken:String? = null

    @DataMember(Order=4)
    var responseStatus:ResponseStatus? = null
}

@DataContract
open class GetAccessTokenResponse
{
    @DataMember(Order=1)
    var accessToken:String? = null

    @DataMember(Order=2)
    var meta:HashMap<String,String> = HashMap<String,String>()

    @DataMember(Order=3)
    var responseStatus:ResponseStatus? = null
}

@DataContract
open class QueryResponse<T>
{
    @DataMember(Order=1)
    var offset:Int? = null

    @DataMember(Order=2)
    var total:Int? = null

    @DataMember(Order=3)
    var results:ArrayList<T> = ArrayList<T>()

    @DataMember(Order=4)
    var meta:HashMap<String,String> = HashMap<String,String>()

    @DataMember(Order=5)
    var responseStatus:ResponseStatus? = null
}

open class RockstarWithIdResponse
{
    var id:Int? = null
    var responseStatus:ResponseStatus? = null
}

open class RockstarWithIdAndResultResponse
{
    var id:Int? = null
    var result:RockstarAuto? = null
    var responseStatus:ResponseStatus? = null
}

open class RockstarWithIdAndCountResponse
{
    var id:Int? = null
    var count:Int? = null
    var responseStatus:ResponseStatus? = null
}

open class RockstarWithIdAndRowVersionResponse
{
    var id:Int? = null
    var rowVersion:Long? = null
    var responseStatus:ResponseStatus? = null
}

open class CustomType
{
    var id:Int? = null
    var name:String? = null
}

open class SetterType
{
    var id:Int? = null
    var name:String? = null
}

open class Item
{
    var name:String? = null
    var description:String? = null
}

open interface IAuthTokens
{
    var provider:String?
    var userId:String?
    var accessToken:String?
    var accessTokenSecret:String?
    var refreshToken:String?
    var refreshTokenExpiry:Date?
    var requestToken:String?
    var requestTokenSecret:String?
    var items:HashMap<String,String>?
}

@DataContract
open class AuthUserSession
{
    @DataMember(Order=1)
    var referrerUrl:String? = null

    @DataMember(Order=2)
    var id:String? = null

    @DataMember(Order=3)
    var userAuthId:String? = null

    @DataMember(Order=4)
    var userAuthName:String? = null

    @DataMember(Order=5)
    var userName:String? = null

    @DataMember(Order=6)
    var twitterUserId:String? = null

    @DataMember(Order=7)
    var twitterScreenName:String? = null

    @DataMember(Order=8)
    var facebookUserId:String? = null

    @DataMember(Order=9)
    var facebookUserName:String? = null

    @DataMember(Order=10)
    var firstName:String? = null

    @DataMember(Order=11)
    var lastName:String? = null

    @DataMember(Order=12)
    var displayName:String? = null

    @DataMember(Order=13)
    var company:String? = null

    @DataMember(Order=14)
    var email:String? = null

    @DataMember(Order=15)
    var primaryEmail:String? = null

    @DataMember(Order=16)
    var phoneNumber:String? = null

    @DataMember(Order=17)
    var birthDate:Date? = null

    @DataMember(Order=18)
    var birthDateRaw:String? = null

    @DataMember(Order=19)
    var address:String? = null

    @DataMember(Order=20)
    var address2:String? = null

    @DataMember(Order=21)
    var city:String? = null

    @DataMember(Order=22)
    var state:String? = null

    @DataMember(Order=23)
    var country:String? = null

    @DataMember(Order=24)
    var culture:String? = null

    @DataMember(Order=25)
    var fullName:String? = null

    @DataMember(Order=26)
    var gender:String? = null

    @DataMember(Order=27)
    var language:String? = null

    @DataMember(Order=28)
    var mailAddress:String? = null

    @DataMember(Order=29)
    var nickname:String? = null

    @DataMember(Order=30)
    var postalCode:String? = null

    @DataMember(Order=31)
    var timeZone:String? = null

    @DataMember(Order=32)
    var requestTokenSecret:String? = null

    @DataMember(Order=33)
    var createdAt:Date? = null

    @DataMember(Order=34)
    var lastModified:Date? = null

    @DataMember(Order=35)
    var roles:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=36)
    var permissions:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=37)
    var isAuthenticated:Boolean? = null

    @DataMember(Order=38)
    var fromToken:Boolean? = null

    @DataMember(Order=39)
    var profileUrl:String? = null

    @DataMember(Order=40)
    var sequence:String? = null

    @DataMember(Order=41)
    var tag:Long? = null

    @DataMember(Order=42)
    var authProvider:String? = null

    @DataMember(Order=43)
    var providerOAuthAccess:ArrayList<IAuthTokens> = ArrayList<IAuthTokens>()

    @DataMember(Order=44)
    var meta:HashMap<String,String> = HashMap<String,String>()

    @DataMember(Order=45)
    var audiences:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=46)
    var scopes:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=47)
    var dns:String? = null

    @DataMember(Order=48)
    var rsa:String? = null

    @DataMember(Order=49)
    var sid:String? = null

    @DataMember(Order=50)
    var hash:String? = null

    @DataMember(Order=51)
    var homePhone:String? = null

    @DataMember(Order=52)
    var mobilePhone:String? = null

    @DataMember(Order=53)
    var webpage:String? = null

    @DataMember(Order=54)
    var emailConfirmed:Boolean? = null

    @DataMember(Order=55)
    var phoneNumberConfirmed:Boolean? = null

    @DataMember(Order=56)
    var twoFactorEnabled:Boolean? = null

    @DataMember(Order=57)
    var securityStamp:String? = null

    @DataMember(Order=58)
    @SerializedName("type") var Type:String? = null
}

open class MetadataTestChild
{
    var name:String? = null
    var results:ArrayList<MetadataTestNestedChild> = ArrayList<MetadataTestNestedChild>()
}

@DataContract
open class MenuExample
{
    @DataMember(Order=1)
    @ApiMember()
    var menuItemExample1:MenuItemExample? = null
}

open class NestedClass
{
    var value:String? = null
}

open class ListResult
{
    var result:String? = null
}

open class ArrayResult
{
    var result:String? = null
}

enum class EnumType
{
    Value1,
    Value2,
    Value3,
}

@Flags()
enum class EnumTypeFlags(val value:Int)
{
    @SerializedName("0") Value1(0),
    @SerializedName("1") Value2(1),
    @SerializedName("2") Value3(2),
}

enum class EnumWithValues
{
    None,
    Value1,
    Value2,
}

@Flags()
enum class EnumFlags(val value:Int)
{
    @SerializedName("0") Value0(0),
    @SerializedName("1") Value1(1),
    @SerializedName("2") Value2(2),
    @SerializedName("4") Value3(4),
    @SerializedName("7") Value123(7),
}

enum class EnumAsInt(val value:Int)
{
    Value1(1000),
    Value2(2000),
    Value3(3000),
}

enum class EnumStyle
{
    Lower,
    Upper,
    PascalCase,
    CamelCase,
    CamelUPPER,
    PascalUPPER,
}

enum class EnumStyleMembers
{
    Lower,
    Upper,
    PascalCase,
    CamelCase,
    CamelUpper,
    PascalUpper,
}

open class KeyValuePair<TKey, TValue>
{
    var key:TKey? = null
    var value:TValue? = null
}

open class SubType
{
    var id:Int? = null
    var name:String? = null
}

open class AllTypesBase
{
    var id:Int? = null
    var nullableId:Int? = null
    @SerializedName("byte") var Byte:Short? = null
    @SerializedName("short") var Short:Short? = null
    @SerializedName("int") var Int:Int? = null
    @SerializedName("long") var Long:Long? = null
    var uShort:Int? = null
    var uInt:Long? = null
    var uLong:BigInteger? = null
    @SerializedName("float") var Float:Float? = null
    @SerializedName("double") var Double:Double? = null
    var decimal:BigDecimal? = null
    var string:String? = null
    var dateTime:Date? = null
    var timeSpan:TimeSpan? = null
    var dateTimeOffset:Date? = null
    var guid:UUID? = null
    @SerializedName("char") var Char:String? = null
    var keyValuePair:KeyValuePair<String, String>? = null
    var nullableDateTime:Date? = null
    var nullableTimeSpan:TimeSpan? = null
    var stringList:ArrayList<String> = ArrayList<String>()
    var stringArray:ArrayList<String>? = null
    var stringMap:HashMap<String,String> = HashMap<String,String>()
    var intStringMap:HashMap<Int,String> = HashMap<Int,String>()
    var subType:SubType? = null
}

open class Poco
{
    var name:String? = null
}

open class HelloBase
{
    var id:Int? = null
}

open class HelloResponseBase
{
    var refId:Int? = null
}

open class HelloBase_1<T>
{
    var items:ArrayList<T> = ArrayList<T>()
    var counts:ArrayList<Int> = ArrayList<Int>()
}

open class HelloWithReturnResponse
{
    var result:String? = null
}

open class HelloType
{
    var result:String? = null
}

open interface IPoco
{
    var name:String?
}

open interface IEmptyInterface
{
}

open class EmptyClass
{
}

open class InnerType
{
    var id:Long? = null
    var name:String? = null
}

enum class InnerEnum
{
    Foo,
    Bar,
    Baz,
}

enum class DayOfWeek
{
    Sunday,
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
}

@DataContract
enum class ScopeType(val value:Int)
{
    Global(1),
    Sale(2),
}

open class PingService
{
}

open class ReturnedDto
{
    var id:Int? = null
}

open class CustomUserSession : AuthUserSession()
{
    @DataMember
    var customName:String? = null

    @DataMember
    var customInfo:String? = null
}

open class UnAuthInfo
{
    var customInfo:String? = null
}

open class Logger
{
    var id:Long? = null
    var devices:ArrayList<Device> = ArrayList<Device>()
}

open class Rockstar
{
    var id:Int? = null
    var firstName:String? = null
    var lastName:String? = null
    var age:Int? = null
}

open class QueryDbTenant_2<From, Into> : QueryDb_2<From, Into>()
{
}

open class RockstarAuditTenant : AuditBase()
{
    var tenantId:Int? = null
    var id:Int? = null
    var firstName:String? = null
    var lastName:String? = null
    var age:Int? = null
    var dateOfBirth:Date? = null
    var dateDied:Date? = null
    var livingStatus:LivingStatus? = null
}

open class RockstarAuto : RockstarBase()
{
    var id:Int? = null
}

open class QueryDb_2<From, Into> : QueryBase()
{
}

open class QueryDb_1<T> : QueryBase()
{
}

open class OnlyDefinedInGenericType
{
    var id:Int? = null
    var name:String? = null
}

open class OnlyDefinedInGenericTypeFrom
{
    var id:Int? = null
    var name:String? = null
}

open class OnlyDefinedInGenericTypeInto
{
    var id:Int? = null
    var name:String? = null
}

enum class LivingStatus
{
    Alive,
    Dead,
}

open class RockstarBase
{
    var firstName:String? = null
    var lastName:String? = null
    var age:Int? = null
    var dateOfBirth:Date? = null
    var dateDied:Date? = null
    var livingStatus:LivingStatus? = null
}

open class RockstarAudit : RockstarBase()
{
    var id:Int? = null
    var createdDate:Date? = null
    var createdBy:String? = null
    var createdInfo:String? = null
    var modifiedDate:Date? = null
    var modifiedBy:String? = null
    var modifiedInfo:String? = null
}

open class CreateAuditTenantBase<Table, TResponse> : CreateAuditBase<Table, TResponse>()
{
}

open class UpdateAuditTenantBase<Table, TResponse> : UpdateAuditBase<Table, TResponse>()
{
}

open class PatchAuditTenantBase<Table, TResponse> : PatchAuditBase<Table, TResponse>()
{
}

open class SoftDeleteAuditTenantBase<Table, TResponse> : SoftDeleteAuditBase<Table, TResponse>()
{
}

open class RockstarVersion : RockstarBase()
{
    var id:Int? = null
    var rowVersion:BigInteger? = null
}

open class MetadataTestNestedChild
{
    var name:String? = null
}

open class MenuItemExample
{
    @DataMember(Order=1)
    @ApiMember()
    var name1:String? = null

    var menuItemExampleItem:MenuItemExampleItem? = null
}

open class TypesGroup
{
}

open class Device
{
    var id:Long? = null
    @SerializedName("type") var Type:String? = null
    var timeStamp:Long? = null
    var channels:ArrayList<Channel> = ArrayList<Channel>()
}

@DataContract
open class AuditBase
{
    @DataMember(Order=1)
    var createdDate:Date? = null

    @DataMember(Order=2)
    @Required()
    var createdBy:String? = null

    @DataMember(Order=3)
    var modifiedDate:Date? = null

    @DataMember(Order=4)
    @Required()
    var modifiedBy:String? = null

    @DataMember(Order=5)
    var deletedDate:Date? = null

    @DataMember(Order=6)
    var deletedBy:String? = null
}

@DataContract
open class QueryBase
{
    @DataMember(Order=1)
    var skip:Int? = null

    @DataMember(Order=2)
    var take:Int? = null

    @DataMember(Order=3)
    var orderBy:String? = null

    @DataMember(Order=4)
    var orderByDesc:String? = null

    @DataMember(Order=5)
    var include:String? = null

    @DataMember(Order=6)
    var fields:String? = null

    @DataMember(Order=7)
    var meta:HashMap<String,String> = HashMap<String,String>()
}

open class CreateAuditBase<Table, TResponse> : ICreateDb<Table>
{
}

open class UpdateAuditBase<Table, TResponse> : IUpdateDb<Table>
{
}

open class PatchAuditBase<Table, TResponse> : IPatchDb<Table>
{
}

open class SoftDeleteAuditBase<Table, TResponse> : IUpdateDb<Table>
{
}

open class MenuItemExampleItem
{
    @DataMember(Order=1)
    @ApiMember()
    var name1:String? = null
}

open class Channel
{
    var name:String? = null
    var value:String? = null
}

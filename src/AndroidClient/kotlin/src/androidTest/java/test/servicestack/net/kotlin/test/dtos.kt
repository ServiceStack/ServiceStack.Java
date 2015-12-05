/* Options:
Date: 2015-12-04 23:03:42
Version: 4.00
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: http://test.servicestack.net

Package: test.servicestack.net.kotlin.test
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion: 
//IncludeTypes: 
//ExcludeTypes: 
//TreatTypesAsStrings: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*,com.google.gson.reflect.*
*/

package test.servicestack.net.kotlin.test

import java.math.*
import java.util.*
import net.servicestack.client.*
import com.google.gson.annotations.*
import com.google.gson.reflect.*


open class CustomHttpError : IReturn<CustomHttpErrorResponse>
{
    var statusCode:Int? = null
    var statusDescription:String? = null
    companion object { private val responseType = CustomHttpErrorResponse::class.java }
    override fun getResponseType(): Any? = responseType
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
    override fun getResponseType(): Any? = responseType
}

@Route("/throwvalidation")
open class ThrowValidation : IReturn<ThrowValidationResponse>
{
    var age:Int? = null
    var required:String? = null
    var email:String? = null
    companion object { private val responseType = ThrowValidationResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/throwbusinesserror")
open class ThrowBusinessError : IReturn<ThrowBusinessErrorResponse>
{
    companion object { private val responseType = ThrowBusinessErrorResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class ExternalOperation : IReturn<ExternalOperationResponse>
{
    var id:Int? = null
    var name:String? = null
    var externalEnum:ExternalEnum? = null
    companion object { private val responseType = ExternalOperationResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class ExternalOperation2 : IReturn<ExternalOperation2Response>
{
    var id:Int? = null
    companion object { private val responseType = ExternalOperation2Response::class.java }
    override fun getResponseType(): Any? = responseType
}

open class ExternalOperation3 : IReturn<ExternalReturnTypeResponse>
{
    var id:Int? = null
    companion object { private val responseType = ExternalReturnTypeResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class ExternalOperation4
{
    var id:Int? = null
}

@Route("/{Path*}")
open class RootPathRoutes
{
    var path:String? = null
}

open class GetAccount : IReturn<Account>
{
    var account:String? = null
    companion object { private val responseType = Account::class.java }
    override fun getResponseType(): Any? = responseType
}

open class GetProject : IReturn<Project>
{
    var account:String? = null
    var project:String? = null
    companion object { private val responseType = Project::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/image-stream")
open class ImageAsStream
{
    var format:String? = null
}

@Route("/image-bytes")
open class ImageAsBytes
{
    var format:String? = null
}

@Route("/image-custom")
open class ImageAsCustomResult
{
    var format:String? = null
}

@Route("/image-response")
open class ImageWriteToResponse
{
    var format:String? = null
}

@Route("/image-file")
open class ImageAsFile
{
    var format:String? = null
}

@Route("/image-redirect")
open class ImageAsRedirect
{
    var format:String? = null
}

@Route("/image-draw/{Name}")
open class DrawImage
{
    var name:String? = null
    var format:String? = null
    var width:Int? = null
    var height:Int? = null
    var fontSize:Int? = null
    var foreground:String? = null
    var background:String? = null
}

@Route("/metadatatest")
open class MetadataTest : IReturn<MetadataTestResponse>
{
    var id:Int? = null
    companion object { private val responseType = MetadataTestResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/metadatatest-array")
open class MetadataTestArray : IReturn<ArrayList<MetadataTestChild>>
{
    var id:Int? = null
    companion object { private val responseType = object : TypeToken<ArrayList<MetadataTestChild>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@Route(Path="/example", Verbs="GET")
@DataContract
open class GetExample : IReturn<GetExampleResponse>
{
    companion object { private val responseType = GetExampleResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/randomids")
open class GetRandomIds : IReturn<GetRandomIdsResponse>
{
    var take:Int? = null
    companion object { private val responseType = GetRandomIdsResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/textfile-test")
open class TextFileTest
{
    var asAttachment:Boolean? = null
}

@Route("/hello")
// @Route("/hello/{Name}")
open class Hello : IReturn<HelloResponse>
{
    @Required()
    var name:String? = null

    var title:String? = null
    companion object { private val responseType = HelloResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloWithNestedClass : IReturn<HelloResponse>
{
    var name:String? = null
    var nestedClassProp:NestedClass? = null
    companion object { private val responseType = HelloResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloList : IReturn<ArrayList<ListResult>>
{
    var names:ArrayList<String>? = null
    companion object { private val responseType = object : TypeToken<ArrayList<ListResult>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class HelloArray : IReturn<ArrayList<ArrayResult>>
{
    var names:ArrayList<String>? = null
    companion object { private val responseType = object : TypeToken<ArrayList<ArrayResult>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class HelloWithEnum
{
    var enumProp:EnumType? = null
    var nullableEnumProp:EnumType? = null
    var enumFlags:EnumFlags? = null
}

open class HelloExternal
{
    var name:String? = null
}

/**
 * AllowedAttributes Description
 */
@Route(Path="/allowed-attributes", Verbs="GET")
@Api("AllowedAttributes Description")
// @ApiResponse(400, "Your request was not understood")
@DataContract
open class AllowedAttributes
{
    @DataMember(Name="Aliased")
    @SerializedName("Aliased")
    @ApiMember(ParameterType="path", Description="Range Description", DataType="double", IsRequired=true)
    var range:Double? = null
}

@Route("/all-types")
open class HelloAllTypes : IReturn<HelloAllTypesResponse>
{
    var name:String? = null
    var allTypes:AllTypes? = null
    var allCollectionTypes:AllCollectionTypes? = null
    companion object { private val responseType = HelloAllTypesResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class AllTypes
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
    var nullableDateTime:Date? = null
    var nullableTimeSpan:TimeSpan? = null
    var stringList:ArrayList<String>? = null
    var stringArray:ArrayList<String>? = null
    var stringMap:HashMap<String,String>? = null
    var intStringMap:HashMap<Int,String>? = null
    var subType:SubType? = null
}

open class HelloString : IReturn<String>
{
    var name:String? = null
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloDateTime : IReturn<HelloDateTime>
{
    var dateTime:Date? = null
    companion object { private val responseType = HelloDateTime::class.java }
    override fun getResponseType(): Any? = responseType
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
    override fun getResponseType(): Any? = responseType
}

/**
 * Description on HelloWithDescription type
 */
open class HelloWithDescription : IReturn<HelloWithDescriptionResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloWithDescriptionResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloWithInheritance : HelloBase(), IReturn<HelloWithInheritanceResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloWithInheritanceResponse::class.java }
    override fun getResponseType(): Any? = responseType
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
    override fun getResponseType(): Any? = responseType
}

@Route("/helloroute")
open class HelloWithRoute : IReturn<HelloWithRouteResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloWithRouteResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloWithType : IReturn<HelloWithTypeResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloWithTypeResponse::class.java }
    override fun getResponseType(): Any? = responseType
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
    override fun getResponseType(): Any? = responseType
}

open class HelloBuiltin
{
    var dayOfWeek:DayOfWeek? = null
}

open class HelloGet : IReturn<HelloVerbResponse>, IGet
{
    var id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloPost : HelloBase(), IReturn<HelloVerbResponse>, IPost
{
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloPut : IReturn<HelloVerbResponse>, IPut
{
    var id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloDelete : IReturn<HelloVerbResponse>, IDelete
{
    var id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloPatch : IReturn<HelloVerbResponse>, IPatch
{
    var id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloReturnVoid : IReturnVoid
{
    var id:Int? = null
}

open class EnumRequest : IReturn<EnumResponse>, IPut
{
    @SerializedName("operator") var Operator:ScopeType? = null
    companion object { private val responseType = EnumResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/ping")
open class Ping : IReturn<PingResponse>
{
    companion object { private val responseType = PingResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/reset-connections")
open class ResetConnections
{
}

@Route("/requires-role")
open class RequiresRole : IReturn<RequiresRoleResponse>
{
    companion object { private val responseType = RequiresRoleResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class SendDefault : IReturn<SendVerbResponse>
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route(Path="/sendrestget/{Id}", Verbs="GET")
open class SendRestGet : IReturn<SendVerbResponse>, IGet
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class SendGet : IReturn<SendVerbResponse>, IGet
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class SendPost : IReturn<SendVerbResponse>, IPost
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class SendPut : IReturn<SendVerbResponse>, IPut
{
    var id:Int? = null
    companion object { private val responseType = SendVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/session")
open class GetSession : IReturn<GetSessionResponse>
{
    companion object { private val responseType = GetSessionResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/session/edit/{CustomName}")
open class UpdateSession : IReturn<GetSessionResponse>
{
    var customName:String? = null
    companion object { private val responseType = GetSessionResponse::class.java }
    override fun getResponseType(): Any? = responseType
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
    override fun getResponseType(): Any? = responseType
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
    override fun getResponseType(): Any? = responseType
}

@Route("/echo/collections")
open class EchoCollections : IReturn<EchoCollections>
{
    var stringList:ArrayList<String>? = null
    var stringArray:ArrayList<String>? = null
    var stringMap:HashMap<String,String>? = null
    var intStringMap:HashMap<Int,String>? = null
    companion object { private val responseType = EchoCollections::class.java }
    override fun getResponseType(): Any? = responseType
}

open class EchoComplexTypes : IReturn<EchoComplexTypes>
{
    var subType:SubType? = null
    companion object { private val responseType = EchoComplexTypes::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/requestlogs")
@DataContract
open class RequestLogs : IReturn<RequestLogsResponse>
{
    @DataMember(Order=1)
    var beforeSecs:Int? = null

    @DataMember(Order=2)
    var afterSecs:Int? = null

    @DataMember(Order=3)
    var ipAddress:String? = null

    @DataMember(Order=4)
    var forwardedFor:String? = null

    @DataMember(Order=5)
    var userAuthId:String? = null

    @DataMember(Order=6)
    var sessionId:String? = null

    @DataMember(Order=7)
    var referer:String? = null

    @DataMember(Order=8)
    var pathInfo:String? = null

    @DataMember(Order=9)
    var ids:ArrayList<Long>? = null

    @DataMember(Order=10)
    var beforeId:Int? = null

    @DataMember(Order=11)
    var afterId:Int? = null

    @DataMember(Order=12)
    var hasResponse:Boolean? = null

    @DataMember(Order=13)
    var withErrors:Boolean? = null

    @DataMember(Order=14)
    var skip:Int? = null

    @DataMember(Order=15)
    var take:Int? = null

    @DataMember(Order=16)
    var enableSessionTracking:Boolean? = null

    @DataMember(Order=17)
    var enableResponseTracking:Boolean? = null

    @DataMember(Order=18)
    var enableErrorTracking:Boolean? = null

    @DataMember(Order=19)
    var durationLongerThan:TimeSpan? = null

    @DataMember(Order=20)
    var durationLessThan:TimeSpan? = null
    companion object { private val responseType = RequestLogsResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/auth")
// @Route("/auth/{provider}")
// @Route("/authenticate")
// @Route("/authenticate/{provider}")
@DataContract
open class Authenticate : IReturn<AuthenticateResponse>
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

    @DataMember(Order=8)
    @SerializedName("continue") var Continue:String? = null

    @DataMember(Order=9)
    var nonce:String? = null

    @DataMember(Order=10)
    var uri:String? = null

    @DataMember(Order=11)
    var response:String? = null

    @DataMember(Order=12)
    var qop:String? = null

    @DataMember(Order=13)
    var nc:String? = null

    @DataMember(Order=14)
    var cnonce:String? = null

    @DataMember(Order=15)
    var meta:HashMap<String,String>? = null
    companion object { private val responseType = AuthenticateResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/assignroles")
@DataContract
open class AssignRoles : IReturn<AssignRolesResponse>
{
    @DataMember(Order=1)
    var userName:String? = null

    @DataMember(Order=2)
    var permissions:ArrayList<String>? = null

    @DataMember(Order=3)
    var roles:ArrayList<String>? = null
    companion object { private val responseType = AssignRolesResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/unassignroles")
@DataContract
open class UnAssignRoles : IReturn<UnAssignRolesResponse>
{
    @DataMember(Order=1)
    var userName:String? = null

    @DataMember(Order=2)
    var permissions:ArrayList<String>? = null

    @DataMember(Order=3)
    var roles:ArrayList<String>? = null
    companion object { private val responseType = UnAssignRolesResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class QueryPocoBase : QueryBase_1<OnlyDefinedInGenericType>(), IReturn<QueryResponse<OnlyDefinedInGenericType>>
{
    var id:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<OnlyDefinedInGenericType>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryPocoIntoBase : QueryBase_2<OnlyDefinedInGenericTypeFrom, OnlyDefinedInGenericTypeInto>(), IReturn<QueryResponse<OnlyDefinedInGenericTypeInto>>
{
    var id:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<OnlyDefinedInGenericTypeInto>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@Route("/rockstars")
open class QueryRockstars : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class CustomHttpErrorResponse
{
    var custom:String? = null
    var responseStatus:ResponseStatus? = null
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

open class ExternalOperationResponse
{
    var result:String? = null
}

open class ExternalOperation2Response
{
    var externalType:ExternalType? = null
}

open class ExternalReturnTypeResponse
{
    var externalEnum3:ExternalEnum3? = null
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

open class MetadataTestResponse
{
    var id:Int? = null
    var results:ArrayList<MetadataTestChild>? = null
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
    var results:ArrayList<String>? = null
}

open class HelloResponse
{
    var result:String? = null
}

open class HelloAllTypesResponse
{
    var result:String? = null
    var allTypes:AllTypes? = null
    var allCollectionTypes:AllCollectionTypes? = null
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

open class PingResponse
{
    var responses:HashMap<String,ResponseStatus>? = null
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

@DataContract
open class RequestLogsResponse
{
    @DataMember(Order=1)
    var results:ArrayList<RequestLogEntry>? = null

    @DataMember(Order=2)
    var usage:HashMap<String,String>? = null

    @DataMember(Order=3)
    var responseStatus:ResponseStatus? = null
}

@DataContract
open class AuthenticateResponse
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
    var responseStatus:ResponseStatus? = null

    @DataMember(Order=7)
    var meta:HashMap<String,String>? = null
}

@DataContract
open class AssignRolesResponse
{
    @DataMember(Order=1)
    var allRoles:ArrayList<String>? = null

    @DataMember(Order=2)
    var allPermissions:ArrayList<String>? = null

    @DataMember(Order=3)
    var responseStatus:ResponseStatus? = null
}

@DataContract
open class UnAssignRolesResponse
{
    @DataMember(Order=1)
    var allRoles:ArrayList<String>? = null

    @DataMember(Order=2)
    var allPermissions:ArrayList<String>? = null

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
    var results:ArrayList<T>? = null

    @DataMember(Order=4)
    var meta:HashMap<String,String>? = null

    @DataMember(Order=5)
    var responseStatus:ResponseStatus? = null
}

enum class ExternalEnum
{
    Foo,
    Bar,
    Baz,
}

open class ExternalType
{
    var externalEnum2:ExternalEnum2? = null
}

enum class ExternalEnum3
{
    Un,
    Deux,
    Trois,
}

open class MetadataTestChild
{
    var name:String? = null
    var results:ArrayList<MetadataTestNestedChild>? = null
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
}

@Flags()
enum class EnumFlags(val value:Int)
{
    @SerializedName("1") Value1(1),
    @SerializedName("2") Value2(2),
    @SerializedName("4") Value3(4),
}

open class AllCollectionTypes
{
    var intArray:ArrayList<Int>? = null
    var intList:ArrayList<Int>? = null
    var stringArray:ArrayList<String>? = null
    var stringList:ArrayList<String>? = null
    var pocoArray:ArrayList<Poco>? = null
    var pocoList:ArrayList<Poco>? = null
    var pocoLookup:HashMap<String,ArrayList<Poco>>? = null
    var pocoLookupMap:HashMap<String,ArrayList<HashMap<String,Poco>>>? = null
}

open class SubType
{
    var id:Int? = null
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

open class Poco
{
    var name:String? = null
}

open class HelloBase_1<T>
{
    var items:ArrayList<T>? = null
    var counts:ArrayList<Int>? = null
}

open class Item
{
    var value:String? = null
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

open class RequestLogEntry
{
    var id:Long? = null
    var dateTime:Date? = null
    var httpMethod:String? = null
    var absoluteUri:String? = null
    var pathInfo:String? = null
    var requestBody:String? = null
    var requestDto:Object? = null
    var userAuthId:String? = null
    var sessionId:String? = null
    var ipAddress:String? = null
    var forwardedFor:String? = null
    var referer:String? = null
    var headers:HashMap<String,String>? = null
    var formData:HashMap<String,String>? = null
    var items:HashMap<String,String>? = null
    var session:Object? = null
    var responseDto:Object? = null
    var errorResponse:Object? = null
    var requestDuration:TimeSpan? = null
}

open class QueryBase_1<T> : QueryBase()
{
}

open class OnlyDefinedInGenericType
{
    var id:Int? = null
    var name:String? = null
}

open class QueryBase_2<From, Into> : QueryBase()
{
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

open class Rockstar
{
    var id:Int? = null
    var firstName:String? = null
    var lastName:String? = null
    var age:Int? = null
}

enum class ExternalEnum2
{
    Uno,
    Due,
    Tre,
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
    var roles:ArrayList<String>? = null

    @DataMember(Order=36)
    var permissions:ArrayList<String>? = null

    @DataMember(Order=37)
    var isAuthenticated:Boolean? = null

    @DataMember(Order=38)
    var sequence:String? = null

    @DataMember(Order=39)
    var tag:Long? = null

    @DataMember(Order=40)
    var providerOAuthAccess:ArrayList<IAuthTokens>? = null
}

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
    var meta:HashMap<String,String>? = null
}

open class MenuItemExampleItem
{
    @DataMember(Order=1)
    @ApiMember()
    var name1:String? = null
}
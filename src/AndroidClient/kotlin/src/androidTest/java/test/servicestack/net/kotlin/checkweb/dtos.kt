/* Options:
Date: 2015-12-08 19:48:41
Version: 4.00
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: http://localhost:55799

Package: test.servicestack.net.kotlin.checkweb
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion: 
//IncludeTypes: 
//ExcludeTypes: 
//InitializeCollections: True
//TreatTypesAsStrings: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*,com.google.gson.reflect.*
*/

package test.servicestack.net.kotlin.checkweb

import java.math.*
import java.util.*
import net.servicestack.client.*
import com.google.gson.annotations.*
import com.google.gson.reflect.*


@Route("/anontype")
open class AnonType
{
}

@Route("/query/rockstars")
open class QueryRockstars : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var Age:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@Route("/changerequest/{Id}")
open class ChangeRequest : IReturn<ChangeRequestResponse>
{
    var Id:String? = null
    companion object { private val responseType = ChangeRequestResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/Routing/LeadPost.aspx")
open class LegacyLeadPost
{
    var LeadType:String? = null
    var MyId:Int? = null
}

@Route("/info/{Id}")
open class Info
{
    var Id:String? = null
}

open class CustomHttpError : IReturn<CustomHttpErrorResponse>
{
    var StatusCode:Int? = null
    var StatusDescription:String? = null
    companion object { private val responseType = CustomHttpErrorResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class CustomFieldHttpError : IReturn<CustomFieldHttpErrorResponse>
{
    companion object { private val responseType = CustomFieldHttpErrorResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("{PathInfo*}")
open class FallbackRoute
{
    var PathInfo:String? = null
}

open class NoRepeat : IReturn<NoRepeatResponse>
{
    var Id:UUID? = null
    companion object { private val responseType = NoRepeatResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class BatchThrows : IReturn<BatchThrowsResponse>
{
    var Id:Int? = null
    var Name:String? = null
    companion object { private val responseType = BatchThrowsResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class BatchThrowsAsync : IReturn<BatchThrowsResponse>
{
    var Id:Int? = null
    var Name:String? = null
    companion object { private val responseType = BatchThrowsResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class MetadataTest : IReturn<MetadataTestResponse>
{
    var Id:Int? = null
    companion object { private val responseType = MetadataTestResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route(Path="/example", Verbs="GET")
@DataContract
open class GetExample : IReturn<GetExampleResponse>
{
    companion object { private val responseType = GetExampleResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class MetadataRequest : IReturn<AutoQueryMetadataResponse>
{
    var MetadataType:MetadataType? = null
    companion object { private val responseType = AutoQueryMetadataResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/namedconnection")
open class NamedConnection
{
    var EmailAddresses:String? = null
}

open class Issue221Long : Issue221Base<Long>()
{
}

open class HelloInService : IReturn<HelloResponse>
{
    var Name:String? = null
    companion object { private val responseType = HelloResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/hello")
// @Route("/hello/{Name}")
open class Hello : IReturn<HelloResponse>
{
    @Required()
    var Name:String? = null

    var Title:String? = null
    companion object { private val responseType = HelloResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

/**
 * Description on HelloAll type
 */
@DataContract
open class HelloAnnotated : IReturn<HelloAnnotatedResponse>
{
    @DataMember
    var Name:String? = null
    companion object { private val responseType = HelloAnnotatedResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloWithNestedClass : IReturn<HelloResponse>
{
    var Name:String? = null
    var NestedClassProp:NestedClass? = null
    companion object { private val responseType = HelloResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloList : IReturn<ArrayList<ListResult>>
{
    var Names:ArrayList<String> = ArrayList<String>()
    companion object { private val responseType = object : TypeToken<ArrayList<ListResult>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class HelloReturnList : IReturn<ArrayList<OnlyInReturnListArg>>
{
    var Names:ArrayList<String> = ArrayList<String>()
    companion object { private val responseType = object : TypeToken<ArrayList<OnlyInReturnListArg>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class HelloArray : IReturn<ArrayList<ArrayResult>>
{
    var Names:ArrayList<String> = ArrayList<String>()
    companion object { private val responseType = object : TypeToken<ArrayList<ArrayResult>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class HelloExisting : IReturn<HelloExistingResponse>
{
    var Names:ArrayList<String> = ArrayList<String>()
    companion object { private val responseType = HelloExistingResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloWithEnum
{
    var EnumProp:EnumType? = null
    var EnumWithValues:EnumWithValues? = null
    var NullableEnumProp:EnumType? = null
    var EnumFlags:EnumFlags? = null
}

open class RestrictedAttributes
{
    var Id:Int? = null
    var Name:String? = null
    var Hello:Hello? = null
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
    @ApiMember(Description="Range Description", ParameterType="path", DataType="double", IsRequired=true)
    var Range:Double? = null
}

/**
 * Multi Line Class
 */
@Api("Multi Line Class")
open class HelloMultiline
{
    @ApiMember(Description="Multi Line Property")
    var Overflow:String? = null
}

open class HelloAllTypes : IReturn<HelloAllTypesResponse>
{
    var Name:String? = null
    var AllTypes:AllTypes? = null
    var AllCollectionTypes:AllCollectionTypes? = null
    companion object { private val responseType = HelloAllTypesResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class AllTypes : IReturn<AllTypes>
{
    var Id:Int? = null
    var NullableId:Int? = null
    var Byte:Short? = null
    var Short:Short? = null
    var Int:Int? = null
    var Long:Long? = null
    var UShort:Int? = null
    var UInt:Long? = null
    var ULong:BigInteger? = null
    var Float:Float? = null
    var Double:Double? = null
    var Decimal:BigDecimal? = null
    var String:String? = null
    var DateTime:Date? = null
    var TimeSpan:TimeSpan? = null
    var DateTimeOffset:Date? = null
    var Guid:UUID? = null
    var Char:String? = null
    var NullableDateTime:Date? = null
    var NullableTimeSpan:TimeSpan? = null
    var StringList:ArrayList<String> = ArrayList<String>()
    var StringArray:ArrayList<String>? = null
    var StringMap:HashMap<String,String> = HashMap<String,String>()
    var IntStringMap:HashMap<Int,String> = HashMap<Int,String>()
    var SubType:SubType? = null
    @DataMember(Name="aliasedName")
    @SerializedName("aliasedName")
    var OriginalName:String? = null
    companion object { private val responseType = AllTypes::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloString : IReturn<String>
{
    var Name:String? = null
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloVoid : IReturnVoid
{
    var Name:String? = null
}

@DataContract
open class HelloWithDataContract : IReturn<HelloWithDataContractResponse>
{
    @DataMember(Name="name", Order=1, IsRequired=true, EmitDefaultValue=false)
    @SerializedName("name")
    var Name:String? = null

    @DataMember(Name="id", Order=2, EmitDefaultValue=false)
    @SerializedName("id")
    var Id:Int? = null
    companion object { private val responseType = HelloWithDataContractResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

/**
 * Description on HelloWithDescription type
 */
open class HelloWithDescription : IReturn<HelloWithDescriptionResponse>
{
    var Name:String? = null
    companion object { private val responseType = HelloWithDescriptionResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloWithInheritance : HelloBase(), IReturn<HelloWithInheritanceResponse>
{
    var Name:String? = null
    companion object { private val responseType = HelloWithInheritanceResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloWithGenericInheritance : HelloBase_1<Poco>()
{
    var Result:String? = null
}

open class HelloWithGenericInheritance2 : HelloBase_1<Hello>()
{
    var Result:String? = null
}

open class HelloWithNestedInheritance : HelloBase_1<Item>()
{
}

open class HelloWithListInheritance : ArrayList<InheritedItem>()
{
}

open class HelloWithReturn : IReturn<HelloWithAlternateReturnResponse>
{
    var Name:String? = null
    companion object { private val responseType = HelloWithAlternateReturnResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/helloroute")
open class HelloWithRoute : IReturn<HelloWithRouteResponse>
{
    var Name:String? = null
    companion object { private val responseType = HelloWithRouteResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloWithType : IReturn<HelloWithTypeResponse>
{
    var Name:String? = null
    companion object { private val responseType = HelloWithTypeResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloSession : IReturn<HelloSessionResponse>
{
    companion object { private val responseType = HelloSessionResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloInterface
{
    var Poco:IPoco? = null
    var EmptyInterface:IEmptyInterface? = null
    var EmptyClass:EmptyClass? = null
    var Value:String? = null
}

open class Request1 : IReturn<Request1Response>
{
    var Test:TypeA? = null
    companion object { private val responseType = Request1Response::class.java }
    override fun getResponseType(): Any? = responseType
}

open class Request2 : IReturn<Request2Response>
{
    var Test:TypeA? = null
    companion object { private val responseType = Request2Response::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloInnerTypes : IReturn<HelloInnerTypesResponse>
{
    companion object { private val responseType = HelloInnerTypesResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class GetUserSession : IReturn<CustomUserSession>
{
    companion object { private val responseType = CustomUserSession::class.java }
    override fun getResponseType(): Any? = responseType
}

open class QueryTemplate : IReturn<QueryResponseTemplate<Poco>>
{
    companion object { private val responseType = object : TypeToken<QueryResponseTemplate<Poco>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class HelloReserved
{
    var Class:String? = null
    var Type:String? = null
    var extension:String? = null
}

open class HelloDictionary : IReturn<HashMap<String,String>>
{
    var Key:String? = null
    var Value:String? = null
    companion object { private val responseType = object : TypeToken<HashMap<String,String>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class HelloBuiltin
{
    var DayOfWeek:DayOfWeek? = null
}

open class HelloGet : IReturn<HelloVerbResponse>, IGet
{
    var Id:Int? = null
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
    var Id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloDelete : IReturn<HelloVerbResponse>, IDelete
{
    var Id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloPatch : IReturn<HelloVerbResponse>, IPatch
{
    var Id:Int? = null
    companion object { private val responseType = HelloVerbResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class HelloReturnVoid : IReturnVoid
{
    var Id:Int? = null
}

open class EnumRequest : IReturn<EnumResponse>, IPut
{
    var Operator:ScopeType? = null
    companion object { private val responseType = EnumResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class ExcludeTest1 : IReturn<ExcludeTestNested>
{
    companion object { private val responseType = ExcludeTestNested::class.java }
    override fun getResponseType(): Any? = responseType
}

open class ExcludeTest2 : IReturn<String>
{
    var ExcludeTestNested:ExcludeTestNested? = null
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = responseType
}

/**
 * Echoes a sentence
 */
@Route(Path="/echoes", Verbs="POST")
@Api("Echoes a sentence")
open class Echoes : IReturn<Echo>
{
    @ApiMember(Description="The sentence to echo.", ParameterType="form", DataType="string", IsRequired=true, Name="Sentence")
    var Sentence:String? = null
    companion object { private val responseType = Echo::class.java }
    override fun getResponseType(): Any? = responseType
}

open class CachedEcho
{
    var Reload:Boolean? = null
    var Sentence:String? = null
}

open class AsyncTest : IReturn<Echo>
{
    companion object { private val responseType = Echo::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/throwhttperror/{Status}")
open class ThrowHttpError : IReturn<ThrowHttpErrorResponse>
{
    var Status:Int? = null
    var Message:String? = null
    companion object { private val responseType = ThrowHttpErrorResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route("/throw404")
// @Route("/throw404/{Message}")
open class Throw404
{
    var Message:String? = null
}

@Route("/return404")
open class Return404
{
}

@Route("/return404result")
open class Return404Result
{
}

@Route("/throw/{Type}")
open class ThrowType : IReturn<ThrowTypeResponse>
{
    var Type:String? = null
    var Message:String? = null
    companion object { private val responseType = ThrowTypeResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

@Route(Path="/api/acsprofiles", Verbs="POST,PUT,PATCH,DELETE")
// @Route("/api/acsprofiles/{profileId}")
open class ACSProfile : IReturn<acsprofileResponse>
{
    var profileId:String? = null
    @Required()
    @StringLength(20)
    var shortName:String? = null

    @StringLength(60)
    var longName:String? = null

    @StringLength(20)
    var regionId:String? = null

    @StringLength(20)
    var groupId:String? = null

    @StringLength(12)
    var deviceID:String? = null

    var lastUpdated:Date? = null
    var enabled:Boolean? = null
    var Version:Int? = null
    var SessionId:String? = null
    companion object { private val responseType = acsprofileResponse::class.java }
    override fun getResponseType(): Any? = responseType
}

open class TestMiniverView
{
}

@Route("/testexecproc")
open class TestExecProc
{
}

open class QueryRockstarsConventions : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var Ids:ArrayList<Int>? = null
    var AgeOlderThan:Int? = null
    var AgeGreaterThanOrEqualTo:Int? = null
    var AgeGreaterThan:Int? = null
    var GreaterThanAge:Int? = null
    var FirstNameStartsWith:String? = null
    var LastNameEndsWith:String? = null
    var LastNameContains:String? = null
    var RockstarAlbumNameContains:String? = null
    var RockstarIdAfter:Int? = null
    var RockstarIdOnOrAfter:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@AutoQueryViewer(Title="Search for Rockstars", Description="Use this option to search for Rockstars!")
open class QueryCustomRockstars : QueryBase_2<Rockstar, CustomRockstar>(), IReturn<QueryResponse<CustomRockstar>>
{
    var Age:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<CustomRockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@Route("/customrockstars")
open class QueryRockstarAlbums : QueryBase_2<Rockstar, CustomRockstar>(), IReturn<QueryResponse<CustomRockstar>>
{
    var Age:Int? = null
    var RockstarAlbumName:String? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<CustomRockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryRockstarAlbumsImplicit : QueryBase_2<Rockstar, CustomRockstar>(), IReturn<QueryResponse<CustomRockstar>>
{
    companion object { private val responseType = object : TypeToken<QueryResponse<CustomRockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryRockstarAlbumsLeftJoin : QueryBase_2<Rockstar, CustomRockstar>(), IReturn<QueryResponse<CustomRockstar>>
{
    var Age:Int? = null
    var AlbumName:String? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<CustomRockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryOverridedRockstars : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var Age:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryOverridedCustomRockstars : QueryBase_2<Rockstar, CustomRockstar>(), IReturn<QueryResponse<CustomRockstar>>
{
    var Age:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<CustomRockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryFieldRockstars : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var FirstName:String? = null
    var FirstNames:ArrayList<String>? = null
    var Age:Int? = null
    var FirstNameCaseInsensitive:String? = null
    var FirstNameStartsWith:String? = null
    var LastNameEndsWith:String? = null
    var FirstNameBetween:ArrayList<String>? = null
    var OrLastName:String? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryFieldRockstarsDynamic : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var Age:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryRockstarsFilter : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var Age:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryCustomRockstarsFilter : QueryBase_2<Rockstar, CustomRockstar>(), IReturn<QueryResponse<CustomRockstar>>
{
    var Age:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<CustomRockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryRockstarsIFilter : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var Age:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@Route("/OrRockstars")
open class QueryOrRockstars : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var Age:Int? = null
    var FirstName:String? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryGetRockstars : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var Ids:ArrayList<Int>? = null
    var Ages:ArrayList<Int> = ArrayList<Int>()
    var FirstNames:ArrayList<String> = ArrayList<String>()
    var IdsBetween:ArrayList<Int>? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryGetRockstarsDynamic : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@Route("/movies/search")
open class SearchMovies : QueryBase_1<Movie>(), IReturn<QueryResponse<Movie>>
{
    companion object { private val responseType = object : TypeToken<QueryResponse<Movie>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@Route("/movies")
open class QueryMovies : QueryBase_1<Movie>(), IReturn<QueryResponse<Movie>>
{
    var Ids:ArrayList<Int>? = null
    var ImdbIds:ArrayList<String>? = null
    var Ratings:ArrayList<String>? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Movie>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class StreamMovies : QueryBase_1<Movie>(), IReturn<QueryResponse<Movie>>
{
    var Ratings:ArrayList<String>? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Movie>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryUnknownRockstars : QueryBase_1<Rockstar>(), IReturn<QueryResponse<Rockstar>>
{
    var UnknownInt:Int? = null
    var UnknownProperty:String? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Rockstar>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@Route("/query/rockstar-references")
open class QueryRockstarsWithReferences : QueryBase_1<RockstarReference>(), IReturn<QueryResponse<RockstarReference>>
{
    var Age:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<RockstarReference>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryPocoBase : QueryBase_1<OnlyDefinedInGenericType>(), IReturn<QueryResponse<OnlyDefinedInGenericType>>
{
    var Id:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<OnlyDefinedInGenericType>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

open class QueryPocoIntoBase : QueryBase_2<OnlyDefinedInGenericTypeFrom, OnlyDefinedInGenericTypeInto>(), IReturn<QueryResponse<OnlyDefinedInGenericTypeInto>>
{
    var Id:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<OnlyDefinedInGenericTypeInto>>(){}.type }
    override fun getResponseType(): Any? = responseType
}

@DataContract
open class QueryResponse<T>
{
    @DataMember(Order=1)
    var Offset:Int? = null

    @DataMember(Order=2)
    var Total:Int? = null

    @DataMember(Order=3)
    var Results:ArrayList<T> = ArrayList<T>()

    @DataMember(Order=4)
    var Meta:HashMap<String,String> = HashMap<String,String>()

    @DataMember(Order=5)
    var ResponseStatus:ResponseStatus? = null
}

open class ChangeRequestResponse
{
    var ContentType:String? = null
    var Header:String? = null
    var QueryString:String? = null
    var Form:String? = null
    var ResponseStatus:ResponseStatus? = null
}

open class CustomHttpErrorResponse
{
    var Custom:String? = null
    var ResponseStatus:ResponseStatus? = null
}

open class CustomFieldHttpErrorResponse
{
    var Custom:String? = null
    var ResponseStatus:ResponseStatus? = null
}

open class NoRepeatResponse
{
    var Id:UUID? = null
}

open class BatchThrowsResponse
{
    var Result:String? = null
    var ResponseStatus:ResponseStatus? = null
}

open class MetadataTestResponse
{
    var Id:Int? = null
    var Results:ArrayList<MetadataTestChild> = ArrayList<MetadataTestChild>()
}

@DataContract
open class GetExampleResponse
{
    @DataMember(Order=1)
    var ResponseStatus:ResponseStatus? = null

    @DataMember(Order=2)
    @ApiMember()
    var MenuExample1:MenuExample? = null
}

open class AutoQueryMetadataResponse
{
    var Config:AutoQueryViewerConfig? = null
    var Operations:ArrayList<AutoQueryOperation> = ArrayList<AutoQueryOperation>()
    var Types:ArrayList<MetadataType> = ArrayList<MetadataType>()
    var ResponseStatus:ResponseStatus? = null
}

open class HelloResponse
{
    var Result:String? = null
}

/**
 * Description on HelloAllResponse type
 */
@DataContract
open class HelloAnnotatedResponse
{
    @DataMember
    var Result:String? = null
}

open class HelloExistingResponse
{
    var HelloList:HelloList? = null
    var HelloArray:HelloArray? = null
    var ArrayResults:ArrayList<ArrayResult>? = null
    var ListResults:ArrayList<ListResult> = ArrayList<ListResult>()
}

open class HelloAllTypesResponse
{
    var Result:String? = null
    var AllTypes:AllTypes? = null
    var AllCollectionTypes:AllCollectionTypes? = null
}

@DataContract
open class HelloWithDataContractResponse
{
    @DataMember(Name="result", Order=1, IsRequired=true, EmitDefaultValue=false)
    @SerializedName("result")
    var Result:String? = null
}

/**
 * Description on HelloWithDescriptionResponse type
 */
open class HelloWithDescriptionResponse
{
    var Result:String? = null
}

open class HelloWithInheritanceResponse : HelloResponseBase()
{
    var Result:String? = null
}

open class HelloWithAlternateReturnResponse : HelloWithReturnResponse()
{
    var AltResult:String? = null
}

open class HelloWithRouteResponse
{
    var Result:String? = null
}

open class HelloWithTypeResponse
{
    var Result:HelloType? = null
}

open class HelloSessionResponse
{
    var Result:AuthUserSession? = null
}

open class Request1Response
{
    var Test:TypeA? = null
}

open class Request2Response
{
    var Test:TypeA? = null
}

open class HelloInnerTypesResponse
{
    var InnerType:InnerType? = null
    var InnerEnum:InnerEnum? = null
}

open class CustomUserSession : AuthUserSession()
{
    @DataMember
    var CustomName:String? = null

    @DataMember
    var CustomInfo:String? = null
}

@DataContract
open class QueryResponseTemplate<T>
{
    @DataMember(Order=1)
    var Offset:Int? = null

    @DataMember(Order=2)
    var Total:Int? = null

    @DataMember(Order=3)
    var Results:ArrayList<T> = ArrayList<T>()

    @DataMember(Order=4)
    var Meta:HashMap<String,String> = HashMap<String,String>()

    @DataMember(Order=5)
    var ResponseStatus:ResponseStatus? = null
}

open class HelloVerbResponse
{
    var Result:String? = null
}

open class EnumResponse
{
    var Operator:ScopeType? = null
}

open class ExcludeTestNested
{
    var Id:Int? = null
}

open class Echo
{
    var Sentence:String? = null
}

open class ThrowHttpErrorResponse
{
}

open class ThrowTypeResponse
{
    var ResponseStatus:ResponseStatus? = null
}

open class acsprofileResponse
{
    var profileId:String? = null
}

open class QueryBase_1<T> : QueryBase()
{
}

open class Rockstar
{
    var Id:Int? = null
    var FirstName:String? = null
    var LastName:String? = null
    var Age:Int? = null
}

open class MetadataTestChild
{
    var Name:String? = null
    var Results:ArrayList<MetadataTestNestedChild> = ArrayList<MetadataTestNestedChild>()
}

@DataContract
open class MenuExample
{
    @DataMember(Order=1)
    @ApiMember()
    var MenuItemExample1:MenuItemExample? = null
}

open class MetadataType
{
    var Name:String? = null
    var Namespace:String? = null
    var GenericArgs:ArrayList<String>? = null
    var Inherits:MetadataTypeName? = null
    var Implements:ArrayList<MetadataTypeName>? = null
    var DisplayType:String? = null
    var Description:String? = null
    var ReturnVoidMarker:Boolean? = null
    var IsNested:Boolean? = null
    var IsEnum:Boolean? = null
    var IsInterface:Boolean? = null
    var IsAbstract:Boolean? = null
    var ReturnMarkerTypeName:MetadataTypeName? = null
    var Routes:ArrayList<MetadataRoute> = ArrayList<MetadataRoute>()
    var DataContract:MetadataDataContract? = null
    var Properties:ArrayList<MetadataPropertyType> = ArrayList<MetadataPropertyType>()
    var Attributes:ArrayList<MetadataAttribute> = ArrayList<MetadataAttribute>()
    var InnerTypes:ArrayList<MetadataTypeName> = ArrayList<MetadataTypeName>()
    var EnumNames:ArrayList<String> = ArrayList<String>()
    var EnumValues:ArrayList<String> = ArrayList<String>()
}

open class AutoQueryViewerConfig
{
    var ServiceBaseUrl:String? = null
    var ServiceName:String? = null
    var ServiceDescription:String? = null
    var ServiceIconUrl:String? = null
    var IsPublic:Boolean? = null
    var OnlyShowAnnotatedServices:Boolean? = null
    var ImplicitConventions:ArrayList<Property> = ArrayList<Property>()
    var DefaultSearchField:String? = null
    var DefaultSearchType:String? = null
    var DefaultSearchText:String? = null
    var BrandUrl:String? = null
    var BrandImageUrl:String? = null
    var TextColor:String? = null
    var LinkColor:String? = null
    var BackgroundColor:String? = null
    var BackgroundImageUrl:String? = null
    var IconUrl:String? = null
}

open class AutoQueryOperation
{
    var Request:String? = null
    var From:String? = null
    var To:String? = null
}

open class Issue221Base<T>
{
    var Id:T? = null
}

open class NativeTypesTestService
{
}

open class NestedClass
{
    var Value:String? = null
}

open class ListResult
{
    var Result:String? = null
}

open class OnlyInReturnListArg
{
    var Result:String? = null
}

open class ArrayResult
{
    var Result:String? = null
}

enum class EnumType
{
    Value1,
    Value2,
}

enum class EnumWithValues(val value:Int)
{
    Value1(1),
    Value2(2),
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
    var IntArray:ArrayList<Int>? = null
    var IntList:ArrayList<Int> = ArrayList<Int>()
    var StringArray:ArrayList<String>? = null
    var StringList:ArrayList<String> = ArrayList<String>()
    var PocoArray:ArrayList<Poco>? = null
    var PocoList:ArrayList<Poco> = ArrayList<Poco>()
    var PocoLookup:HashMap<String,ArrayList<Poco>> = HashMap<String,ArrayList<Poco>>()
    var PocoLookupMap:HashMap<String,ArrayList<HashMap<String,Poco>>> = HashMap<String,ArrayList<HashMap<String,Poco>>>()
}

open class SubType
{
    var Id:Int? = null
    var Name:String? = null
}

open class HelloBase
{
    var Id:Int? = null
}

open class HelloResponseBase
{
    var RefId:Int? = null
}

open class Poco
{
    var Name:String? = null
}

open class HelloBase_1<T>
{
    var Items:ArrayList<T> = ArrayList<T>()
    var Counts:ArrayList<Int> = ArrayList<Int>()
}

open class Item
{
    var Value:String? = null
}

open class InheritedItem
{
    var Name:String? = null
}

open class HelloWithReturnResponse
{
    var Result:String? = null
}

open class HelloType
{
    var Result:String? = null
}

@DataContract
open class AuthUserSession
{
    @DataMember(Order=1)
    var ReferrerUrl:String? = null

    @DataMember(Order=2)
    var Id:String? = null

    @DataMember(Order=3)
    var UserAuthId:String? = null

    @DataMember(Order=4)
    var UserAuthName:String? = null

    @DataMember(Order=5)
    var UserName:String? = null

    @DataMember(Order=6)
    var TwitterUserId:String? = null

    @DataMember(Order=7)
    var TwitterScreenName:String? = null

    @DataMember(Order=8)
    var FacebookUserId:String? = null

    @DataMember(Order=9)
    var FacebookUserName:String? = null

    @DataMember(Order=10)
    var FirstName:String? = null

    @DataMember(Order=11)
    var LastName:String? = null

    @DataMember(Order=12)
    var DisplayName:String? = null

    @DataMember(Order=13)
    var Company:String? = null

    @DataMember(Order=14)
    var Email:String? = null

    @DataMember(Order=15)
    var PrimaryEmail:String? = null

    @DataMember(Order=16)
    var PhoneNumber:String? = null

    @DataMember(Order=17)
    var BirthDate:Date? = null

    @DataMember(Order=18)
    var BirthDateRaw:String? = null

    @DataMember(Order=19)
    var Address:String? = null

    @DataMember(Order=20)
    var Address2:String? = null

    @DataMember(Order=21)
    var City:String? = null

    @DataMember(Order=22)
    var State:String? = null

    @DataMember(Order=23)
    var Country:String? = null

    @DataMember(Order=24)
    var Culture:String? = null

    @DataMember(Order=25)
    var FullName:String? = null

    @DataMember(Order=26)
    var Gender:String? = null

    @DataMember(Order=27)
    var Language:String? = null

    @DataMember(Order=28)
    var MailAddress:String? = null

    @DataMember(Order=29)
    var Nickname:String? = null

    @DataMember(Order=30)
    var PostalCode:String? = null

    @DataMember(Order=31)
    var TimeZone:String? = null

    @DataMember(Order=32)
    var RequestTokenSecret:String? = null

    @DataMember(Order=33)
    var CreatedAt:Date? = null

    @DataMember(Order=34)
    var LastModified:Date? = null

    @DataMember(Order=35)
    var Roles:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=36)
    var Permissions:ArrayList<String> = ArrayList<String>()

    @DataMember(Order=37)
    var IsAuthenticated:Boolean? = null

    @DataMember(Order=38)
    var Sequence:String? = null

    @DataMember(Order=39)
    var Tag:Long? = null

    @DataMember(Order=40)
    var ProviderOAuthAccess:ArrayList<IAuthTokens> = ArrayList<IAuthTokens>()
}

open interface IPoco
{
    var Name:String?
}

open interface IEmptyInterface
{
}

open class EmptyClass
{
}

open class TypeA
{
    var Bar:ArrayList<TypeB> = ArrayList<TypeB>()
}

open class InnerType
{
    var Id:Long? = null
    var Name:String? = null
}

enum class InnerEnum
{
    Foo,
    Bar,
    Baz,
}

open interface IAuthTokens
{
    var Provider:String?
    var UserId:String?
    var AccessToken:String?
    var AccessTokenSecret:String?
    var RefreshToken:String?
    var RefreshTokenExpiry:Date?
    var RequestToken:String?
    var RequestTokenSecret:String?
    var Items:HashMap<String,String>?
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

open class QueryBase_2<From, Into> : QueryBase()
{
}

open class CustomRockstar
{
    @AutoQueryViewerField(Title="Name")
    var FirstName:String? = null

    @AutoQueryViewerField(HideInSummary=true)
    var LastName:String? = null

    var Age:Int? = null
    @AutoQueryViewerField(Title="Album")
    var RockstarAlbumName:String? = null

    @AutoQueryViewerField(Title="Genre")
    var RockstarGenreName:String? = null
}

open class Movie
{
    var Id:Int? = null
    var ImdbId:String? = null
    var Title:String? = null
    var Rating:String? = null
    var Score:BigDecimal? = null
    var Director:String? = null
    var ReleaseDate:Date? = null
    var TagLine:String? = null
    var Genres:ArrayList<String> = ArrayList<String>()
}

open class RockstarReference
{
    var Id:Int? = null
    var FirstName:String? = null
    var LastName:String? = null
    var Age:Int? = null
    var Albums:ArrayList<RockstarAlbum> = ArrayList<RockstarAlbum>()
}

open class OnlyDefinedInGenericType
{
    var Id:Int? = null
    var Name:String? = null
}

open class OnlyDefinedInGenericTypeFrom
{
    var Id:Int? = null
    var Name:String? = null
}

open class OnlyDefinedInGenericTypeInto
{
    var Id:Int? = null
    var Name:String? = null
}

open class QueryBase
{
    @DataMember(Order=1)
    var Skip:Int? = null

    @DataMember(Order=2)
    var Take:Int? = null

    @DataMember(Order=3)
    var OrderBy:String? = null

    @DataMember(Order=4)
    var OrderByDesc:String? = null

    @DataMember(Order=5)
    var Include:String? = null

    @DataMember(Order=6)
    var Meta:HashMap<String,String> = HashMap<String,String>()
}

open class MetadataTestNestedChild
{
    var Name:String? = null
}

open class MenuItemExample
{
    @DataMember(Order=1)
    @ApiMember()
    var Name1:String? = null

    var MenuItemExampleItem:MenuItemExampleItem? = null
}

open class MetadataTypeName
{
    var Name:String? = null
    var Namespace:String? = null
    var GenericArgs:ArrayList<String>? = null
}

open class MetadataRoute
{
    var Path:String? = null
    var Verbs:String? = null
    var Notes:String? = null
    var Summary:String? = null
}

open class MetadataDataContract
{
    var Name:String? = null
    var Namespace:String? = null
}

open class MetadataPropertyType
{
    var Name:String? = null
    var Type:String? = null
    var IsValueType:Boolean? = null
    var TypeNamespace:String? = null
    var GenericArgs:ArrayList<String>? = null
    var Value:String? = null
    var Description:String? = null
    var DataMember:MetadataDataMember? = null
    var ReadOnly:Boolean? = null
    var ParamType:String? = null
    var DisplayType:String? = null
    var IsRequired:Boolean? = null
    var AllowableValues:ArrayList<String>? = null
    var AllowableMin:Int? = null
    var AllowableMax:Int? = null
    var Attributes:ArrayList<MetadataAttribute> = ArrayList<MetadataAttribute>()
}

open class MetadataAttribute
{
    var Name:String? = null
    var ConstructorArgs:ArrayList<MetadataPropertyType> = ArrayList<MetadataPropertyType>()
    var Args:ArrayList<MetadataPropertyType> = ArrayList<MetadataPropertyType>()
}

@DataContract
open class Property
{
    @DataMember
    var Name:String? = null

    @DataMember
    var Value:String? = null
}

open class TypeB
{
    var Foo:String? = null
}

open class TypesGroup
{
}

open class RockstarAlbum
{
    var Id:Int? = null
    var RockstarId:Int? = null
    var Name:String? = null
}

open class MenuItemExampleItem
{
    @DataMember(Order=1)
    @ApiMember()
    var Name1:String? = null
}

open class MetadataDataMember
{
    var Name:String? = null
    var Order:Int? = null
    var IsRequired:Boolean? = null
    var EmitDefaultValue:Boolean? = null
}
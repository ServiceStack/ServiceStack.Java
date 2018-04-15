/* Options:
Date: 2018-04-15 04:44:25
Version: 5.03
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: https://www.techstacks.io

Package: test.servicestack.net.kotlin.techstacks
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion: 
//AddDescriptionAsComments: True
//IncludeTypes: 
//ExcludeTypes: 
//InitializeCollections: True
//TreatTypesAsStrings: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*,com.google.gson.reflect.*
*/

package test.servicestack.net.kotlin.techstacks

import java.math.*
import java.util.*
import net.servicestack.client.*
import com.google.gson.annotations.*
import com.google.gson.reflect.*


@Route("/ping")
open class Ping
{
}

@Route(Path="/orgs/{Id}", Verbs="GET")
open class GetOrganization : IReturn<GetOrganizationResponse>
{
    var id:Int? = null
    companion object { private val responseType = GetOrganizationResponse::class.java }
    override fun getResponseType(): Any? = GetOrganization.responseType
}

@Route(Path="/organizations/{Slug}", Verbs="GET")
open class GetOrganizationBySlug : IReturn<GetOrganizationResponse>
{
    var slug:String? = null
    companion object { private val responseType = GetOrganizationResponse::class.java }
    override fun getResponseType(): Any? = GetOrganizationBySlug.responseType
}

@Route(Path="/orgs/{Id}/members", Verbs="GET")
open class GetOrganizationMembers : IReturn<GetOrganizationMembersResponse>
{
    var id:Int? = null
    companion object { private val responseType = GetOrganizationMembersResponse::class.java }
    override fun getResponseType(): Any? = GetOrganizationMembers.responseType
}

@Route(Path="/orgs/{Id}/admin", Verbs="GET")
open class GetOrganizationAdmin : IReturn<GetOrganizationAdminResponse>
{
    var id:Int? = null
    companion object { private val responseType = GetOrganizationAdminResponse::class.java }
    override fun getResponseType(): Any? = GetOrganizationAdmin.responseType
}

@Route(Path="/orgs/posts/new", Verbs="POST")
open class CreateOrganizationForTechnology : IReturn<CreateOrganizationForTechnologyResponse>
{
    var technologyId:Long? = null
    var techStackId:Long? = null
    companion object { private val responseType = CreateOrganizationForTechnologyResponse::class.java }
    override fun getResponseType(): Any? = CreateOrganizationForTechnology.responseType
}

@Route(Path="/orgs", Verbs="POST")
open class CreateOrganization : IReturn<CreateOrganizationResponse>
{
    var name:String? = null
    var slug:String? = null
    var description:String? = null
    var refId:Long? = null
    var refSource:String? = null
    var refUrn:String? = null
    companion object { private val responseType = CreateOrganizationResponse::class.java }
    override fun getResponseType(): Any? = CreateOrganization.responseType
}

@Route(Path="/orgs/{Id}", Verbs="PUT")
open class UpdateOrganization : IReturn<UpdateOrganizationResponse>
{
    var id:Int? = null
    var slug:String? = null
    var name:String? = null
    var description:String? = null
    var color:String? = null
    var textColor:String? = null
    var linkColor:String? = null
    var backgroundColor:String? = null
    var backgroundUrl:String? = null
    var logoUrl:String? = null
    var heroUrl:String? = null
    var lang:String? = null
    var deletePostsWithReportCount:Int? = null
    var disableInvites:Boolean? = null
    var defaultPostType:String? = null
    var defaultSubscriptionPostTypes:ArrayList<String>? = null
    var postTypes:ArrayList<String>? = null
    var moderatorPostTypes:ArrayList<String>? = null
    var technologyIds:ArrayList<Int>? = null
    companion object { private val responseType = UpdateOrganizationResponse::class.java }
    override fun getResponseType(): Any? = UpdateOrganization.responseType
}

@Route(Path="/orgs/{Id}", Verbs="DELETE")
open class DeleteOrganization : IReturnVoid
{
    var id:Int? = null
}

@Route(Path="/orgs/{Id}/lock", Verbs="PUT")
open class LockOrganization : IReturnVoid
{
    var id:Int? = null
    var lock:Boolean? = null
    var reason:String? = null
}

@Route(Path="/orgs/{OrganizationId}/labels", Verbs="POST")
open class AddOrganizationLabel : IReturn<OrganizationLabelResponse>
{
    var organizationId:Int? = null
    var slug:String? = null
    var description:String? = null
    var color:String? = null
    companion object { private val responseType = OrganizationLabelResponse::class.java }
    override fun getResponseType(): Any? = AddOrganizationLabel.responseType
}

@Route(Path="/orgs/{OrganizationId}/members/{Slug}", Verbs="PUT")
open class UpdateOrganizationLabel : IReturn<OrganizationLabelResponse>
{
    var organizationId:Int? = null
    var slug:String? = null
    var description:String? = null
    var color:String? = null
    companion object { private val responseType = OrganizationLabelResponse::class.java }
    override fun getResponseType(): Any? = UpdateOrganizationLabel.responseType
}

@Route(Path="/orgs/{OrganizationId}/labels/{Slug}", Verbs="DELETE")
open class RemoveOrganizationLabel : IReturnVoid
{
    var organizationId:Int? = null
    var slug:String? = null
}

@Route(Path="/orgs/{OrganizationId}/categories", Verbs="POST")
open class AddOrganizationCategory : IReturn<AddCategoryResponse>
{
    var organizationId:Int? = null
    var slug:String? = null
    var name:String? = null
    var description:String? = null
    var technologyIds:ArrayList<Int>? = null
    companion object { private val responseType = AddCategoryResponse::class.java }
    override fun getResponseType(): Any? = AddOrganizationCategory.responseType
}

@Route(Path="/orgs/{OrganizationId}/categories/{Id}", Verbs="PUT")
open class UpdateOrganizationCategory : IReturn<UpdateCategoryResponse>
{
    var organizationId:Int? = null
    var id:Int? = null
    var name:String? = null
    var slug:String? = null
    var description:String? = null
    var technologyIds:ArrayList<Int>? = null
    companion object { private val responseType = UpdateCategoryResponse::class.java }
    override fun getResponseType(): Any? = UpdateOrganizationCategory.responseType
}

@Route(Path="/orgs/{OrganizationId}/categories/{Id}", Verbs="DELETE")
open class DeleteOrganizationCategory : IReturnVoid
{
    var organizationId:Int? = null
    var id:Int? = null
}

@Route(Path="/orgs/{OrganizationId}/members", Verbs="POST")
open class AddOrganizationMember : IReturn<AddOrganizationMemberResponse>
{
    var organizationId:Int? = null
    var userName:String? = null
    var isOwner:Boolean? = null
    var isModerator:Boolean? = null
    var denyPosts:Boolean? = null
    var denyComments:Boolean? = null
    var denyAll:Boolean? = null
    var notes:String? = null
    companion object { private val responseType = AddOrganizationMemberResponse::class.java }
    override fun getResponseType(): Any? = AddOrganizationMember.responseType
}

@Route(Path="/orgs/{OrganizationId}/members/{Id}", Verbs="PUT")
open class UpdateOrganizationMember : IReturn<UpdateOrganizationMemberResponse>
{
    var organizationId:Int? = null
    var userId:Int? = null
    var isOwner:Boolean? = null
    var isModerator:Boolean? = null
    var denyPosts:Boolean? = null
    var denyComments:Boolean? = null
    var denyAll:Boolean? = null
    var notes:String? = null
    companion object { private val responseType = UpdateOrganizationMemberResponse::class.java }
    override fun getResponseType(): Any? = UpdateOrganizationMember.responseType
}

@Route(Path="/orgs/{OrganizationId}/members/{UserId}", Verbs="DELETE")
open class RemoveOrganizationMember : IReturnVoid
{
    var organizationId:Int? = null
    var userId:Int? = null
}

@Route(Path="/orgs/{OrganizationId}/members/set", Verbs="POST")
open class SetOrganizationMembers : IReturn<SetOrganizationMembersResponse>
{
    var organizationId:Int? = null
    var githubUserNames:ArrayList<String>? = null
    var twitterUserNames:ArrayList<String>? = null
    var emails:ArrayList<String>? = null
    var removeUnspecifiedMembers:Boolean? = null
    var isOwner:Boolean? = null
    var isModerator:Boolean? = null
    var denyPosts:Boolean? = null
    var denyComments:Boolean? = null
    var denyAll:Boolean? = null
    companion object { private val responseType = SetOrganizationMembersResponse::class.java }
    override fun getResponseType(): Any? = SetOrganizationMembers.responseType
}

@Route(Path="/orgs/{OrganizationId}/invites", Verbs="GET")
open class GetOrganizationMemberInvites : IReturn<GetOrganizationMemberInvitesResponse>
{
    var organizationId:Int? = null
    companion object { private val responseType = GetOrganizationMemberInvitesResponse::class.java }
    override fun getResponseType(): Any? = GetOrganizationMemberInvites.responseType
}

@Route(Path="/orgs/{OrganizationId}/invites", Verbs="POST")
open class RequestOrganizationMemberInvite : IReturn<RequestOrganizationMemberInviteResponse>
{
    var organizationId:Int? = null
    companion object { private val responseType = RequestOrganizationMemberInviteResponse::class.java }
    override fun getResponseType(): Any? = RequestOrganizationMemberInvite.responseType
}

@Route(Path="/orgs/{OrganizationId}/invites/{UserId}", Verbs="PUT")
open class UpdateOrganizationMemberInvite : IReturn<UpdateOrganizationMemberInviteResponse>
{
    var organizationId:Int? = null
    var userName:String? = null
    var approve:Boolean? = null
    var dismiss:Boolean? = null
    companion object { private val responseType = UpdateOrganizationMemberInviteResponse::class.java }
    override fun getResponseType(): Any? = UpdateOrganizationMemberInvite.responseType
}

@Route(Path="/posts", Verbs="GET")
open class QueryPosts : QueryDb<Post>(), IReturn<QueryResponse<Post>>
{
    var ids:ArrayList<Int>? = null
    var organizationId:Int? = null
    var organizationIds:ArrayList<Int>? = null
    var types:ArrayList<String>? = null
    var anyTechnologyIds:ArrayList<Int>? = null
    @SerializedName("is") var Is:ArrayList<String>? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Post>>(){}.type }
    override fun getResponseType(): Any? = QueryPosts.responseType
}

@Route(Path="/posts/{Id}", Verbs="GET")
open class GetPost : IReturn<GetPostResponse>
{
    var id:Long? = null
    var include:String? = null
    companion object { private val responseType = GetPostResponse::class.java }
    override fun getResponseType(): Any? = GetPost.responseType
}

@Route(Path="/posts", Verbs="POST")
open class CreatePost : IReturn<CreatePostResponse>
{
    var organizationId:Int? = null
    @SerializedName("type") var Type:PostType? = null
    var categoryId:Int? = null
    var title:String? = null
    var url:String? = null
    var imageUrl:String? = null
    var content:String? = null
    var lock:Boolean? = null
    var technologyIds:ArrayList<Int>? = null
    var labels:ArrayList<String>? = null
    var fromDate:Date? = null
    var toDate:Date? = null
    var metaType:String? = null
    var meta:String? = null
    var refId:Long? = null
    var refSource:String? = null
    var refUrn:String? = null
    companion object { private val responseType = CreatePostResponse::class.java }
    override fun getResponseType(): Any? = CreatePost.responseType
}

@Route(Path="/posts/{Id}", Verbs="PUT")
open class UpdatePost : IReturn<UpdatePostResponse>
{
    var id:Long? = null
    var organizationId:Int? = null
    @SerializedName("type") var Type:PostType? = null
    var categoryId:Int? = null
    var title:String? = null
    var url:String? = null
    var imageUrl:String? = null
    var content:String? = null
    var lock:Boolean? = null
    var technologyIds:ArrayList<Int>? = null
    var labels:ArrayList<String>? = null
    var fromDate:Date? = null
    var toDate:Date? = null
    var metaType:String? = null
    var meta:String? = null
    companion object { private val responseType = UpdatePostResponse::class.java }
    override fun getResponseType(): Any? = UpdatePost.responseType
}

@Route(Path="/posts/{Id}", Verbs="DELETE")
open class DeletePost : IReturn<DeletePostResponse>
{
    var id:Long? = null
    companion object { private val responseType = DeletePostResponse::class.java }
    override fun getResponseType(): Any? = DeletePost.responseType
}

@Route(Path="/posts/{Id}/lock", Verbs="PUT")
open class LockPost : IReturnVoid
{
    var id:Long? = null
    var lock:Boolean? = null
    var reason:String? = null
}

@Route(Path="/posts/{Id}/hide", Verbs="PUT")
open class HidePost : IReturnVoid
{
    var id:Long? = null
    var hide:Boolean? = null
    var reason:String? = null
}

@Route(Path="/posts/{Id}/status/{Status}", Verbs="PUT")
open class ChangeStatusPost : IReturnVoid
{
    var id:Long? = null
    var status:String? = null
    var reason:String? = null
}

@Route(Path="/posts/{PostId}/report/{Id}", Verbs="POST")
open class ActionPostReport : IReturnVoid
{
    var postId:Long? = null
    var id:Long? = null
    var reportAction:ReportAction? = null
}

@Route(Path="/posts/{PostId}/comments", Verbs="POST")
open class CreatePostComment : IReturn<CreatePostCommentResponse>
{
    var postId:Long? = null
    var replyId:Long? = null
    var content:String? = null
    companion object { private val responseType = CreatePostCommentResponse::class.java }
    override fun getResponseType(): Any? = CreatePostComment.responseType
}

@Route(Path="/posts/{PostId}/comments/{Id}", Verbs="PUT")
open class UpdatePostComment : IReturn<UpdatePostCommentResponse>
{
    var id:Long? = null
    var postId:Long? = null
    var content:String? = null
    companion object { private val responseType = UpdatePostCommentResponse::class.java }
    override fun getResponseType(): Any? = UpdatePostComment.responseType
}

@Route(Path="/posts/{PostId}/comments/{Id}", Verbs="DELETE")
open class DeletePostComment : IReturn<DeletePostCommentResponse>
{
    var id:Long? = null
    var postId:Long? = null
    companion object { private val responseType = DeletePostCommentResponse::class.java }
    override fun getResponseType(): Any? = DeletePostComment.responseType
}

@Route(Path="/posts/{PostId}/comments/{PostCommentId}/report/{Id}", Verbs="POST")
open class ActionPostCommentReport : IReturnVoid
{
    var id:Long? = null
    var postCommentId:Long? = null
    var postId:Long? = null
    var reportAction:ReportAction? = null
}

@Route("/user/comments/votes")
open class GetUserPostCommentVotes : IReturn<GetUserPostCommentVotesResponse>
{
    var postId:Long? = null
    companion object { private val responseType = GetUserPostCommentVotesResponse::class.java }
    override fun getResponseType(): Any? = GetUserPostCommentVotes.responseType
}

@Route(Path="/posts/{PostId}/comments/{Id}/pin", Verbs="UPDATE")
open class PinPostComment : IReturn<PinPostCommentResponse>
{
    var id:Long? = null
    var postId:Long? = null
    var pin:Boolean? = null
    companion object { private val responseType = PinPostCommentResponse::class.java }
    override fun getResponseType(): Any? = PinPostComment.responseType
}

@Route("/users/by-email")
open class GetUsersByEmails : IReturn<GetUsersByEmailsResponse>
{
    var emails:ArrayList<String>? = null
    companion object { private val responseType = GetUsersByEmailsResponse::class.java }
    override fun getResponseType(): Any? = GetUsersByEmails.responseType
}

@Route("/user/posts/activity")
open class GetUserPostActivity : IReturn<GetUserPostActivityResponse>
{
    companion object { private val responseType = GetUserPostActivityResponse::class.java }
    override fun getResponseType(): Any? = GetUserPostActivity.responseType
}

@Route("/user/organizations")
open class GetUserOrganizations : IReturn<GetUserOrganizationsResponse>
{
    companion object { private val responseType = GetUserOrganizationsResponse::class.java }
    override fun getResponseType(): Any? = GetUserOrganizations.responseType
}

@Route(Path="/posts/{Id}/vote", Verbs="PUT")
open class UserPostVote : IReturn<UserPostVoteResponse>
{
    var id:Long? = null
    var weight:Int? = null
    companion object { private val responseType = UserPostVoteResponse::class.java }
    override fun getResponseType(): Any? = UserPostVote.responseType
}

@Route(Path="/posts/{Id}/favorite", Verbs="PUT")
open class UserPostFavorite : IReturn<UserPostFavoriteResponse>
{
    var id:Long? = null
    companion object { private val responseType = UserPostFavoriteResponse::class.java }
    override fun getResponseType(): Any? = UserPostFavorite.responseType
}

@Route(Path="/posts/{Id}/report", Verbs="PUT")
open class UserPostReport : IReturn<UserPostReportResponse>
{
    var id:Long? = null
    var flagType:FlagType? = null
    var reportNotes:String? = null
    companion object { private val responseType = UserPostReportResponse::class.java }
    override fun getResponseType(): Any? = UserPostReport.responseType
}

@Route(Path="/posts/{PostId}/comments/{Id}", Verbs="GET")
open class UserPostCommentVote : IReturn<UserPostCommentVoteResponse>
{
    var id:Long? = null
    var postId:Long? = null
    var weight:Int? = null
    companion object { private val responseType = UserPostCommentVoteResponse::class.java }
    override fun getResponseType(): Any? = UserPostCommentVote.responseType
}

@Route(Path="/posts/{PostId}/comments/{Id}/report", Verbs="PUT")
open class UserPostCommentReport : IReturn<UserPostCommentReportResponse>
{
    var id:Long? = null
    var postId:Long? = null
    var flagType:FlagType? = null
    var reportNotes:String? = null
    companion object { private val responseType = UserPostCommentReportResponse::class.java }
    override fun getResponseType(): Any? = UserPostCommentReport.responseType
}

@Route(Path="/prerender/{Path*}", Verbs="PUT")
open class StorePreRender : IReturnVoid
{
    var path:String? = null
}

@Route(Path="/prerender/{Path*}", Verbs="GET")
open class GetPreRender : IReturn<String>
{
    var path:String? = null
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = GetPreRender.responseType
}

@Route("/my-session")
open class SessionInfo : IReturn<SessionInfoResponse>
{
    companion object { private val responseType = SessionInfoResponse::class.java }
    override fun getResponseType(): Any? = SessionInfo.responseType
}

@Route(Path="/orgs/{OrganizationId}/subscribe", Verbs="PUT")
open class SubscribeToOrganization : IReturnVoid
{
    var organizationId:Int? = null
    var postTypes:ArrayList<PostType>? = null
    var frequency:Frequency? = null
}

@Route(Path="/posts/{PostId}/subscribe", Verbs="PUT")
open class SubscribeToPost : IReturnVoid
{
    var postId:Long? = null
}

@Route(Path="/orgs/{OrganizationId}/subscribe", Verbs="DELETE")
open class DeleteOrganizationSubscription : IReturnVoid
{
    var organizationId:Long? = null
}

@Route(Path="/posts/{PostId}/subscribe", Verbs="DELETE")
open class DeletePostSubscription : IReturnVoid
{
    var postId:Long? = null
}

@Route(Path="/technology/{Slug}/previous-versions", Verbs="GET")
open class GetTechnologyPreviousVersions : IReturn<GetTechnologyPreviousVersionsResponse>
{
    var slug:String? = null
    companion object { private val responseType = GetTechnologyPreviousVersionsResponse::class.java }
    override fun getResponseType(): Any? = GetTechnologyPreviousVersions.responseType
}

@Route(Path="/technology", Verbs="GET")
open class GetAllTechnologies : IReturn<GetAllTechnologiesResponse>
{
    companion object { private val responseType = GetAllTechnologiesResponse::class.java }
    override fun getResponseType(): Any? = GetAllTechnologies.responseType
}

@Route("/technology/search")
@AutoQueryViewer(DefaultSearchField="Tier", DefaultSearchText="Data", DefaultSearchType="=", Description="Explore different Technologies", IconUrl="octicon:database", Title="Find Technologies")
open class FindTechnologies : QueryDb<Technology>(), IReturn<QueryResponse<Technology>>
{
    var name:String? = null
    var nameContains:String? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Technology>>(){}.type }
    override fun getResponseType(): Any? = FindTechnologies.responseType
}

@Route("/technology/query")
open class QueryTechnology : QueryDb<Technology>(), IReturn<QueryResponse<Technology>>
{
    companion object { private val responseType = object : TypeToken<QueryResponse<Technology>>(){}.type }
    override fun getResponseType(): Any? = QueryTechnology.responseType
}

@Route("/technology/{Slug}")
open class GetTechnology : IReturn<GetTechnologyResponse>, IRegisterStats
{
    var slug:String? = null
    companion object { private val responseType = GetTechnologyResponse::class.java }
    override fun getResponseType(): Any? = GetTechnology.responseType
}

@Route("/technology/{Slug}/favorites")
open class GetTechnologyFavoriteDetails : IReturn<GetTechnologyFavoriteDetailsResponse>
{
    var slug:String? = null
    companion object { private val responseType = GetTechnologyFavoriteDetailsResponse::class.java }
    override fun getResponseType(): Any? = GetTechnologyFavoriteDetails.responseType
}

@Route(Path="/technology", Verbs="POST")
open class CreateTechnology : IReturn<CreateTechnologyResponse>
{
    var name:String? = null
    var slug:String? = null
    var vendorName:String? = null
    var vendorUrl:String? = null
    var productUrl:String? = null
    var logoUrl:String? = null
    var description:String? = null
    var isLocked:Boolean? = null
    var tier:TechnologyTier? = null
    companion object { private val responseType = CreateTechnologyResponse::class.java }
    override fun getResponseType(): Any? = CreateTechnology.responseType
}

@Route(Path="/technology/{Id}", Verbs="PUT")
open class UpdateTechnology : IReturn<UpdateTechnologyResponse>
{
    var id:Long? = null
    var name:String? = null
    var vendorName:String? = null
    var vendorUrl:String? = null
    var productUrl:String? = null
    var logoUrl:String? = null
    var description:String? = null
    var isLocked:Boolean? = null
    var tier:TechnologyTier? = null
    companion object { private val responseType = UpdateTechnologyResponse::class.java }
    override fun getResponseType(): Any? = UpdateTechnology.responseType
}

@Route(Path="/technology/{Id}", Verbs="DELETE")
open class DeleteTechnology : IReturn<DeleteTechnologyResponse>
{
    var id:Long? = null
    companion object { private val responseType = DeleteTechnologyResponse::class.java }
    override fun getResponseType(): Any? = DeleteTechnology.responseType
}

@Route(Path="/techstacks/{Slug}/previous-versions", Verbs="GET")
open class GetTechnologyStackPreviousVersions : IReturn<GetTechnologyStackPreviousVersionsResponse>
{
    var slug:String? = null
    companion object { private val responseType = GetTechnologyStackPreviousVersionsResponse::class.java }
    override fun getResponseType(): Any? = GetTechnologyStackPreviousVersions.responseType
}

@Route("/pagestats/{Type}/{Slug}")
open class GetPageStats : IReturn<GetPageStatsResponse>
{
    @SerializedName("type") var Type:String? = null
    var slug:String? = null
    var id:Int? = null
    companion object { private val responseType = GetPageStatsResponse::class.java }
    override fun getResponseType(): Any? = GetPageStats.responseType
}

@Route("/cache/clear")
open class ClearCache : IReturn<String>
{
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = ClearCache.responseType
}

@Route("/tasks/hourly")
open class HourlyTask : IReturn<HourlyTaskResponse>
{
    var force:Boolean? = null
    companion object { private val responseType = HourlyTaskResponse::class.java }
    override fun getResponseType(): Any? = HourlyTask.responseType
}

@Route("/techstacks/search")
@AutoQueryViewer(DefaultSearchField="Description", DefaultSearchText="ServiceStack", DefaultSearchType="Contains", Description="Explore different Technology Stacks", IconUrl="material-icons:cloud", Title="Find Technology Stacks")
open class FindTechStacks : QueryDb<TechnologyStack>(), IReturn<QueryResponse<TechnologyStack>>
{
    var nameContains:String? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<TechnologyStack>>(){}.type }
    override fun getResponseType(): Any? = FindTechStacks.responseType
}

@Route("/techstacks/query")
open class QueryTechStacks : QueryDb<TechnologyStack>(), IReturn<QueryResponse<TechnologyStack>>
{
    companion object { private val responseType = object : TypeToken<QueryResponse<TechnologyStack>>(){}.type }
    override fun getResponseType(): Any? = QueryTechStacks.responseType
}

@Route("/overview")
open class Overview : IReturn<OverviewResponse>
{
    var reload:Boolean? = null
    companion object { private val responseType = OverviewResponse::class.java }
    override fun getResponseType(): Any? = Overview.responseType
}

@Route("/app-overview")
open class AppOverview : IReturn<AppOverviewResponse>
{
    var reload:Boolean? = null
    companion object { private val responseType = AppOverviewResponse::class.java }
    override fun getResponseType(): Any? = AppOverview.responseType
}

@Route(Path="/techstacks", Verbs="GET")
open class GetAllTechnologyStacks : IReturn<GetAllTechnologyStacksResponse>
{
    companion object { private val responseType = GetAllTechnologyStacksResponse::class.java }
    override fun getResponseType(): Any? = GetAllTechnologyStacks.responseType
}

@Route(Path="/techstacks/{Slug}", Verbs="GET")
open class GetTechnologyStack : IReturn<GetTechnologyStackResponse>, IRegisterStats
{
    var slug:String? = null
    companion object { private val responseType = GetTechnologyStackResponse::class.java }
    override fun getResponseType(): Any? = GetTechnologyStack.responseType
}

@Route("/techstacks/{Slug}/favorites")
open class GetTechnologyStackFavoriteDetails : IReturn<GetTechnologyStackFavoriteDetailsResponse>
{
    var slug:String? = null
    companion object { private val responseType = GetTechnologyStackFavoriteDetailsResponse::class.java }
    override fun getResponseType(): Any? = GetTechnologyStackFavoriteDetails.responseType
}

@Route("/config")
open class GetConfig : IReturn<GetConfigResponse>
{
    companion object { private val responseType = GetConfigResponse::class.java }
    override fun getResponseType(): Any? = GetConfig.responseType
}

@Route(Path="/techstacks", Verbs="POST")
open class CreateTechnologyStack : IReturn<CreateTechnologyStackResponse>
{
    var name:String? = null
    var slug:String? = null
    var vendorName:String? = null
    var appUrl:String? = null
    var screenshotUrl:String? = null
    var description:String? = null
    var details:String? = null
    var isLocked:Boolean? = null
    var technologyIds:ArrayList<Long> = ArrayList<Long>()
    companion object { private val responseType = CreateTechnologyStackResponse::class.java }
    override fun getResponseType(): Any? = CreateTechnologyStack.responseType
}

@Route(Path="/techstacks/{Id}", Verbs="PUT")
open class UpdateTechnologyStack : IReturn<UpdateTechnologyStackResponse>
{
    var id:Long? = null
    var name:String? = null
    var vendorName:String? = null
    var appUrl:String? = null
    var screenshotUrl:String? = null
    var description:String? = null
    var details:String? = null
    var isLocked:Boolean? = null
    var technologyIds:ArrayList<Long> = ArrayList<Long>()
    companion object { private val responseType = UpdateTechnologyStackResponse::class.java }
    override fun getResponseType(): Any? = UpdateTechnologyStack.responseType
}

@Route(Path="/techstacks/{Id}", Verbs="DELETE")
open class DeleteTechnologyStack : IReturn<DeleteTechnologyStackResponse>
{
    var id:Long? = null
    companion object { private val responseType = DeleteTechnologyStackResponse::class.java }
    override fun getResponseType(): Any? = DeleteTechnologyStack.responseType
}

@Route(Path="/favorites/techtacks", Verbs="GET")
open class GetFavoriteTechStack : IReturn<GetFavoriteTechStackResponse>
{
    var technologyStackId:Int? = null
    companion object { private val responseType = GetFavoriteTechStackResponse::class.java }
    override fun getResponseType(): Any? = GetFavoriteTechStack.responseType
}

@Route(Path="/favorites/techtacks/{TechnologyStackId}", Verbs="PUT")
open class AddFavoriteTechStack : IReturn<FavoriteTechStackResponse>
{
    var technologyStackId:Int? = null
    companion object { private val responseType = FavoriteTechStackResponse::class.java }
    override fun getResponseType(): Any? = AddFavoriteTechStack.responseType
}

@Route(Path="/favorites/techtacks/{TechnologyStackId}", Verbs="DELETE")
open class RemoveFavoriteTechStack : IReturn<FavoriteTechStackResponse>
{
    var technologyStackId:Int? = null
    companion object { private val responseType = FavoriteTechStackResponse::class.java }
    override fun getResponseType(): Any? = RemoveFavoriteTechStack.responseType
}

@Route(Path="/favorites/technology", Verbs="GET")
open class GetFavoriteTechnologies : IReturn<GetFavoriteTechnologiesResponse>
{
    var technologyId:Int? = null
    companion object { private val responseType = GetFavoriteTechnologiesResponse::class.java }
    override fun getResponseType(): Any? = GetFavoriteTechnologies.responseType
}

@Route(Path="/favorites/technology/{TechnologyId}", Verbs="PUT")
open class AddFavoriteTechnology : IReturn<FavoriteTechnologyResponse>
{
    var technologyId:Int? = null
    companion object { private val responseType = FavoriteTechnologyResponse::class.java }
    override fun getResponseType(): Any? = AddFavoriteTechnology.responseType
}

@Route(Path="/favorites/technology/{TechnologyId}", Verbs="DELETE")
open class RemoveFavoriteTechnology : IReturn<FavoriteTechnologyResponse>
{
    var technologyId:Int? = null
    companion object { private val responseType = FavoriteTechnologyResponse::class.java }
    override fun getResponseType(): Any? = RemoveFavoriteTechnology.responseType
}

@Route("/my-feed")
open class GetUserFeed : IReturn<GetUserFeedResponse>
{
    companion object { private val responseType = GetUserFeedResponse::class.java }
    override fun getResponseType(): Any? = GetUserFeed.responseType
}

@Route(Path="/users/karma", Verbs="GET")
open class GetUsersKarma : IReturn<GetUsersKarmaResponse>
{
    var userIds:ArrayList<Int>? = null
    companion object { private val responseType = GetUsersKarmaResponse::class.java }
    override fun getResponseType(): Any? = GetUsersKarma.responseType
}

@Route("/userinfo/{UserName}")
open class GetUserInfo : IReturn<GetUserInfoResponse>
{
    var userName:String? = null
    companion object { private val responseType = GetUserInfoResponse::class.java }
    override fun getResponseType(): Any? = GetUserInfo.responseType
}

@Route(Path="/users/{UserName}/avatar", Verbs="GET")
open class UserAvatar
{
    var userName:String? = null
}

@Route("/mq/start")
open class MqStart : IReturn<String>
{
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = MqStart.responseType
}

@Route("/mq/stop")
open class MqStop : IReturn<String>
{
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = MqStop.responseType
}

@Route("/mq/stats")
open class MqStats : IReturn<String>
{
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = MqStats.responseType
}

@Route("/mq/status")
open class MqStatus : IReturn<String>
{
    companion object { private val responseType = String::class.java }
    override fun getResponseType(): Any? = MqStatus.responseType
}

@Route("/sync/discourse/{Site}")
open class SyncDiscourseSite : IReturn<SyncDiscourseSiteResponse>
{
    var site:String? = null
    companion object { private val responseType = SyncDiscourseSiteResponse::class.java }
    override fun getResponseType(): Any? = SyncDiscourseSite.responseType
}

@Route("/admin/technology/{TechnologyId}/logo")
open class LogoUrlApproval : IReturn<LogoUrlApprovalResponse>
{
    var technologyId:Long? = null
    var approved:Boolean? = null
    companion object { private val responseType = LogoUrlApprovalResponse::class.java }
    override fun getResponseType(): Any? = LogoUrlApproval.responseType
}

@Route("/admin/techstacks/{TechnologyStackId}/lock")
open class LockTechStack : IReturn<LockStackResponse>
{
    var technologyStackId:Long? = null
    var isLocked:Boolean? = null
    companion object { private val responseType = LockStackResponse::class.java }
    override fun getResponseType(): Any? = LockTechStack.responseType
}

@Route("/admin/technology/{TechnologyId}/lock")
open class LockTech : IReturn<LockStackResponse>
{
    var technologyId:Long? = null
    var isLocked:Boolean? = null
    companion object { private val responseType = LockStackResponse::class.java }
    override fun getResponseType(): Any? = LockTech.responseType
}

@Route("/email/post/{PostId}")
open class EmailTest : IReturn<EmailTestRespoonse>
{
    var postId:Int? = null
    companion object { private val responseType = EmailTestRespoonse::class.java }
    override fun getResponseType(): Any? = EmailTest.responseType
}

open class ImportUser : IReturn<ImportUserResponse>
{
    var userName:String? = null
    var email:String? = null
    var firstName:String? = null
    var lastName:String? = null
    var displayName:String? = null
    var company:String? = null
    var refSource:String? = null
    var refId:Int? = null
    var refIdStr:String? = null
    var refUrn:String? = null
    var defaultProfileUrl:String? = null
    var meta:HashMap<String,String> = HashMap<String,String>()
    companion object { private val responseType = ImportUserResponse::class.java }
    override fun getResponseType(): Any? = ImportUser.responseType
}

@Route("/import/uservoice/suggestion")
open class ImportUserVoiceSuggestion : IReturn<ImportUserVoiceSuggestionResponse>
{
    var organizationId:Int? = null
    var url:String? = null
    var id:Int? = null
    var topicId:Int? = null
    var state:String? = null
    var title:String? = null
    var slug:String? = null
    var category:String? = null
    var text:String? = null
    var formattedText:String? = null
    var voteCount:Int? = null
    var closedAt:Date? = null
    var statusKey:String? = null
    var statusHexColor:String? = null
    var statusChangedBy:UserVoiceUser? = null
    var creator:UserVoiceUser? = null
    var response:UserVoiceComment? = null
    var createdAt:Date? = null
    var updatedAt:Date? = null
    companion object { private val responseType = ImportUserVoiceSuggestionResponse::class.java }
    override fun getResponseType(): Any? = ImportUserVoiceSuggestion.responseType
}

@Route("/auth")
// @Route("/auth/{provider}")
// @Route("/authenticate")
// @Route("/authenticate/{provider}")
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
    var useTokenCookie:Boolean? = null

    @DataMember(Order=16)
    var accessToken:String? = null

    @DataMember(Order=17)
    var accessTokenSecret:String? = null

    @DataMember(Order=18)
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
    companion object { private val responseType = UnAssignRolesResponse::class.java }
    override fun getResponseType(): Any? = UnAssignRoles.responseType
}

@Route("/session-to-token")
@DataContract
open class ConvertSessionToToken : IReturn<ConvertSessionToTokenResponse>, IPost
{
    @DataMember(Order=1)
    var preserveSession:Boolean? = null
    companion object { private val responseType = ConvertSessionToTokenResponse::class.java }
    override fun getResponseType(): Any? = ConvertSessionToToken.responseType
}

@Route("/access-token")
@DataContract
open class GetAccessToken : IReturn<GetAccessTokenResponse>, IPost
{
    @DataMember(Order=1)
    var refreshToken:String? = null
    companion object { private val responseType = GetAccessTokenResponse::class.java }
    override fun getResponseType(): Any? = GetAccessToken.responseType
}

@Route(Path="/posts/comment", Verbs="GET")
open class QueryPostComments : QueryDb<PostComment>(), IReturn<QueryResponse<PostComment>>
{
    var userId:Int? = null
    var postId:Int? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<PostComment>>(){}.type }
    override fun getResponseType(): Any? = QueryPostComments.responseType
}

@Route("/admin/technology/search")
@AutoQueryViewer(DefaultSearchField="Tier", DefaultSearchText="Data", DefaultSearchType="=", Description="Explore different Technologies", IconUrl="octicon:database", Title="Find Technologies Admin")
open class FindTechnologiesAdmin : QueryDb<Technology>(), IReturn<QueryResponse<Technology>>
{
    var name:String? = null
    companion object { private val responseType = object : TypeToken<QueryResponse<Technology>>(){}.type }
    override fun getResponseType(): Any? = FindTechnologiesAdmin.responseType
}

open class GetOrganizationResponse
{
    var cache:Long? = null
    var id:Int? = null
    var slug:String? = null
    var organization:Organization? = null
    var labels:ArrayList<OrganizationLabel> = ArrayList<OrganizationLabel>()
    var categories:ArrayList<Category> = ArrayList<Category>()
    var owners:ArrayList<OrganizationMember> = ArrayList<OrganizationMember>()
    var moderators:ArrayList<OrganizationMember> = ArrayList<OrganizationMember>()
    var membersCount:Long? = null
    var responseStatus:ResponseStatus? = null
}

open class GetOrganizationMembersResponse
{
    var organizationId:Int? = null
    var results:ArrayList<OrganizationMember> = ArrayList<OrganizationMember>()
    var responseStatus:ResponseStatus? = null
}

open class GetOrganizationAdminResponse
{
    var labels:ArrayList<OrganizationLabel> = ArrayList<OrganizationLabel>()
    var members:ArrayList<OrganizationMember> = ArrayList<OrganizationMember>()
    var memberInvites:ArrayList<OrganizationMemberInvite> = ArrayList<OrganizationMemberInvite>()
    var reportedPosts:ArrayList<PostReportInfo> = ArrayList<PostReportInfo>()
    var reportedPostComments:ArrayList<PostCommentReportInfo> = ArrayList<PostCommentReportInfo>()
    var responseStatus:ResponseStatus? = null
}

open class CreateOrganizationForTechnologyResponse
{
    var organizationId:Int? = null
    var organizationSlug:String? = null
    var commentsPostId:Long? = null
    var commentsPostSlug:String? = null
    var responseStatus:ResponseStatus? = null
}

open class CreateOrganizationResponse
{
    var id:Int? = null
    var slug:String? = null
    var responseStatus:ResponseStatus? = null
}

open class UpdateOrganizationResponse
{
    var responseStatus:ResponseStatus? = null
}

open class OrganizationLabelResponse
{
    var responseStatus:ResponseStatus? = null
}

open class AddCategoryResponse
{
    var id:Int? = null
    var slug:String? = null
    var responseStatus:ResponseStatus? = null
}

open class UpdateCategoryResponse
{
    var responseStatus:ResponseStatus? = null
}

open class AddOrganizationMemberResponse
{
    var responseStatus:ResponseStatus? = null
}

open class UpdateOrganizationMemberResponse
{
    var responseStatus:ResponseStatus? = null
}

open class SetOrganizationMembersResponse
{
    var userIdsAdded:ArrayList<Int>? = null
    var userIdsRemoved:ArrayList<Int>? = null
    var responseStatus:ResponseStatus? = null
}

open class GetOrganizationMemberInvitesResponse
{
    var results:ArrayList<OrganizationMemberInvite> = ArrayList<OrganizationMemberInvite>()
    var responseStatus:ResponseStatus? = null
}

open class RequestOrganizationMemberInviteResponse
{
    var organizationId:Int? = null
    var responseStatus:ResponseStatus? = null
}

open class UpdateOrganizationMemberInviteResponse
{
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

open class GetPostResponse
{
    var cache:Long? = null
    var post:Post? = null
    var comments:ArrayList<PostComment> = ArrayList<PostComment>()
    var responseStatus:ResponseStatus? = null
}

open class CreatePostResponse
{
    var id:Long? = null
    var slug:String? = null
    var responseStatus:ResponseStatus? = null
}

open class UpdatePostResponse
{
    var responseStatus:ResponseStatus? = null
}

open class DeletePostResponse
{
    var id:Long? = null
    var responseStatus:ResponseStatus? = null
}

open class CreatePostCommentResponse
{
    var id:Long? = null
    var postId:Long? = null
    var responseStatus:ResponseStatus? = null
}

open class UpdatePostCommentResponse
{
    var responseStatus:ResponseStatus? = null
}

open class DeletePostCommentResponse
{
    var id:Long? = null
    var postId:Long? = null
    var responseStatus:ResponseStatus? = null
}

open class GetUserPostCommentVotesResponse
{
    var postId:Long? = null
    var upVotedCommentIds:ArrayList<Long> = ArrayList<Long>()
    var downVotedCommentIds:ArrayList<Long> = ArrayList<Long>()
}

open class PinPostCommentResponse
{
    var responseStatus:ResponseStatus? = null
}

open class GetUsersByEmailsResponse
{
    var results:ArrayList<UserRef> = ArrayList<UserRef>()
    var responseStatus:ResponseStatus? = null
}

open class GetUserPostActivityResponse
{
    var upVotedPostIds:ArrayList<Long> = ArrayList<Long>()
    var downVotedPostIds:ArrayList<Long> = ArrayList<Long>()
    var favoritePostIds:ArrayList<Long> = ArrayList<Long>()
    var responseStatus:ResponseStatus? = null
}

open class GetUserOrganizationsResponse
{
    var members:ArrayList<OrganizationMember> = ArrayList<OrganizationMember>()
    var memberInvites:ArrayList<OrganizationMemberInvite> = ArrayList<OrganizationMemberInvite>()
    var subscriptions:ArrayList<OrganizationSubscription> = ArrayList<OrganizationSubscription>()
}

open class UserPostVoteResponse
{
    var responseStatus:ResponseStatus? = null
}

open class UserPostFavoriteResponse
{
    var responseStatus:ResponseStatus? = null
}

open class UserPostReportResponse
{
    var responseStatus:ResponseStatus? = null
}

open class UserPostCommentVoteResponse
{
    var responseStatus:ResponseStatus? = null
}

open class UserPostCommentReportResponse
{
    var responseStatus:ResponseStatus? = null
}

open class SessionInfoResponse
{
    var created:Date? = null
    var id:String? = null
    var referrerUrl:String? = null
    var userAuthId:String? = null
    var userAuthName:String? = null
    var userName:String? = null
    var displayName:String? = null
    var firstName:String? = null
    var lastName:String? = null
    var email:String? = null
    var createdAt:Date? = null
    var lastModified:Date? = null
    var roles:ArrayList<String> = ArrayList<String>()
    var permissions:ArrayList<String> = ArrayList<String>()
    var isAuthenticated:Boolean? = null
    var authProvider:String? = null
    var profileUrl:String? = null
    var githubProfileUrl:String? = null
    var twitterProfileUrl:String? = null
    var accessToken:String? = null
    var avatarUrl:String? = null
    var techStacks:ArrayList<TechnologyStack> = ArrayList<TechnologyStack>()
    var favoriteTechStacks:ArrayList<TechnologyStack> = ArrayList<TechnologyStack>()
    var favoriteTechnologies:ArrayList<Technology> = ArrayList<Technology>()
    var userActivity:UserActivity? = null
    var members:ArrayList<OrganizationMember> = ArrayList<OrganizationMember>()
    var memberInvites:ArrayList<OrganizationMemberInvite> = ArrayList<OrganizationMemberInvite>()
    var subscriptions:ArrayList<OrganizationSubscription> = ArrayList<OrganizationSubscription>()
    var responseStatus:ResponseStatus? = null
}

open class GetTechnologyPreviousVersionsResponse
{
    var results:ArrayList<TechnologyHistory> = ArrayList<TechnologyHistory>()
}

open class GetAllTechnologiesResponse
{
    var results:ArrayList<Technology> = ArrayList<Technology>()
    var total:Long? = null
}

open class GetTechnologyResponse
{
    var created:Date? = null
    var technology:Technology? = null
    var technologyStacks:ArrayList<TechnologyStack> = ArrayList<TechnologyStack>()
    var responseStatus:ResponseStatus? = null
}

open class GetTechnologyFavoriteDetailsResponse
{
    var users:ArrayList<String> = ArrayList<String>()
    var favoriteCount:Int? = null
}

open class CreateTechnologyResponse
{
    var result:Technology? = null
    var responseStatus:ResponseStatus? = null
}

open class UpdateTechnologyResponse
{
    var result:Technology? = null
    var responseStatus:ResponseStatus? = null
}

open class DeleteTechnologyResponse
{
    var result:Technology? = null
    var responseStatus:ResponseStatus? = null
}

open class GetTechnologyStackPreviousVersionsResponse
{
    var results:ArrayList<TechnologyStackHistory> = ArrayList<TechnologyStackHistory>()
}

open class GetPageStatsResponse
{
    @SerializedName("type") var Type:String? = null
    var slug:String? = null
    var viewCount:Long? = null
    var favCount:Long? = null
}

open class HourlyTaskResponse
{
    var meta:HashMap<String,String> = HashMap<String,String>()
    var responseStatus:ResponseStatus? = null
}

open class OverviewResponse
{
    var created:Date? = null
    var topUsers:ArrayList<UserInfo> = ArrayList<UserInfo>()
    var topTechnologies:ArrayList<TechnologyInfo> = ArrayList<TechnologyInfo>()
    var latestTechStacks:ArrayList<TechStackDetails> = ArrayList<TechStackDetails>()
    var popularTechStacks:ArrayList<TechnologyStack> = ArrayList<TechnologyStack>()
    var allOrganizations:ArrayList<OrganizationInfo> = ArrayList<OrganizationInfo>()
    var topTechnologiesByTier:HashMap<String,ArrayList<TechnologyInfo>> = HashMap<String,ArrayList<TechnologyInfo>>()
    var responseStatus:ResponseStatus? = null
}

open class AppOverviewResponse
{
    var created:Date? = null
    var allTiers:ArrayList<Option> = ArrayList<Option>()
    var topTechnologies:ArrayList<TechnologyInfo> = ArrayList<TechnologyInfo>()
    var responseStatus:ResponseStatus? = null
}

open class GetAllTechnologyStacksResponse
{
    var results:ArrayList<TechnologyStack> = ArrayList<TechnologyStack>()
    var total:Long? = null
}

open class GetTechnologyStackResponse
{
    var created:Date? = null
    var result:TechStackDetails? = null
    var responseStatus:ResponseStatus? = null
}

open class GetTechnologyStackFavoriteDetailsResponse
{
    var users:ArrayList<String> = ArrayList<String>()
    var favoriteCount:Int? = null
}

open class GetConfigResponse
{
    var allTiers:ArrayList<Option> = ArrayList<Option>()
    var allPostTypes:ArrayList<Option> = ArrayList<Option>()
    var allFlagTypes:ArrayList<Option> = ArrayList<Option>()
}

open class CreateTechnologyStackResponse
{
    var result:TechStackDetails? = null
    var responseStatus:ResponseStatus? = null
}

open class UpdateTechnologyStackResponse
{
    var result:TechStackDetails? = null
    var responseStatus:ResponseStatus? = null
}

open class DeleteTechnologyStackResponse
{
    var result:TechStackDetails? = null
    var responseStatus:ResponseStatus? = null
}

open class GetFavoriteTechStackResponse
{
    var results:ArrayList<TechnologyStack> = ArrayList<TechnologyStack>()
}

open class FavoriteTechStackResponse
{
    var result:TechnologyStack? = null
}

open class GetFavoriteTechnologiesResponse
{
    var results:ArrayList<Technology> = ArrayList<Technology>()
}

open class FavoriteTechnologyResponse
{
    var result:Technology? = null
}

open class GetUserFeedResponse
{
    var results:ArrayList<TechStackDetails> = ArrayList<TechStackDetails>()
}

open class GetUsersKarmaResponse
{
    var results:HashMap<Int,Int> = HashMap<Int,Int>()
    var responseStatus:ResponseStatus? = null
}

open class GetUserInfoResponse
{
    var id:Int? = null
    var userName:String? = null
    var created:Date? = null
    var avatarUrl:String? = null
    var techStacks:ArrayList<TechnologyStack> = ArrayList<TechnologyStack>()
    var favoriteTechStacks:ArrayList<TechnologyStack> = ArrayList<TechnologyStack>()
    var favoriteTechnologies:ArrayList<Technology> = ArrayList<Technology>()
    var userActivity:UserActivity? = null
    var responseStatus:ResponseStatus? = null
}

open class SyncDiscourseSiteResponse
{
    var timeTaken:String? = null
    var userLogs:ArrayList<String> = ArrayList<String>()
    var postsLogs:ArrayList<String> = ArrayList<String>()
    var responseStatus:ResponseStatus? = null
}

open class LogoUrlApprovalResponse
{
    var result:Technology? = null
}

open class LockStackResponse
{
}

open class EmailTestRespoonse
{
    var responseStatus:ResponseStatus? = null
}

open class ImportUserResponse
{
    var id:Int? = null
    var responseStatus:ResponseStatus? = null
}

open class ImportUserVoiceSuggestionResponse
{
    var postId:Long? = null
    var postSlug:String? = null
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
    var bearerToken:String? = null

    @DataMember(Order=7)
    var refreshToken:String? = null

    @DataMember(Order=8)
    var responseStatus:ResponseStatus? = null

    @DataMember(Order=9)
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
    var responseStatus:ResponseStatus? = null
}

@DataContract
open class GetAccessTokenResponse
{
    @DataMember(Order=1)
    var accessToken:String? = null

    @DataMember(Order=2)
    var responseStatus:ResponseStatus? = null
}

open class Organization
{
    var id:Int? = null
    var name:String? = null
    var slug:String? = null
    var description:String? = null
    var descriptionHtml:String? = null
    var color:String? = null
    var textColor:String? = null
    var linkColor:String? = null
    var backgroundColor:String? = null
    var backgroundUrl:String? = null
    var logoUrl:String? = null
    var heroUrl:String? = null
    var lang:String? = null
    var defaultPostType:String? = null
    var defaultSubscriptionPostTypes:ArrayList<String>? = null
    var postTypes:ArrayList<String>? = null
    var moderatorPostTypes:ArrayList<String>? = null
    var deletePostsWithReportCount:Int? = null
    var disableInvites:Boolean? = null
    var upVotes:Long? = null
    var downVotes:Long? = null
    var views:Long? = null
    var favorites:Long? = null
    var subscribers:Int? = null
    var commentsCount:Int? = null
    var postsCount:Int? = null
    var score:Int? = null
    var rank:Int? = null
    var refId:Long? = null
    var refSource:String? = null
    var hidden:Date? = null
    var hiddenBy:String? = null
    var locked:Date? = null
    var lockedBy:String? = null
    var deleted:Date? = null
    var deletedBy:String? = null
    var created:Date? = null
    var createdBy:String? = null
    var modified:Date? = null
    var modifiedBy:String? = null
}

open class OrganizationLabel
{
    var slug:String? = null
    var organizationId:Int? = null
    var description:String? = null
    var color:String? = null
}

open class Category
{
    var id:Int? = null
    var organizationId:Int? = null
    var name:String? = null
    var slug:String? = null
    var description:String? = null
    var color:String? = null
    var technologyIds:ArrayList<Int>? = null
    var commentsCount:Int? = null
    var postsCount:Int? = null
    var score:Int? = null
    var rank:Int? = null
}

open class OrganizationMember
{
    var id:Int? = null
    var organizationId:Int? = null
    var userId:Int? = null
    var userName:String? = null
    var isOwner:Boolean? = null
    var isModerator:Boolean? = null
    var denyAll:Boolean? = null
    var denyPosts:Boolean? = null
    var denyComments:Boolean? = null
    var notes:String? = null
}

open class OrganizationMemberInvite
{
    var id:Int? = null
    var organizationId:Int? = null
    var userId:Int? = null
    var userName:String? = null
    var dismissed:Date? = null
}

open class PostReportInfo : PostReport()
{
    var title:String? = null
    var reportCount:Int? = null
    var createdBy:String? = null
}

open class PostCommentReportInfo : PostCommentReport()
{
    var contentHtml:String? = null
    var reportCount:Int? = null
    var createdBy:String? = null
}

open class QueryDb<T> : QueryBase()
{
}

open class Post
{
    var id:Long? = null
    var organizationId:Int? = null
    var userId:Int? = null
    @SerializedName("type") var Type:PostType? = null
    var categoryId:Int? = null
    var title:String? = null
    var slug:String? = null
    var url:String? = null
    var imageUrl:String? = null
    @StringLength(2147483647)
    var content:String? = null

    @StringLength(2147483647)
    var contentHtml:String? = null

    var pinCommentId:Long? = null
    var technologyIds:ArrayList<Int>? = null
    var fromDate:Date? = null
    var toDate:Date? = null
    var location:String? = null
    var metaType:String? = null
    var meta:String? = null
    var approved:Boolean? = null
    var upVotes:Long? = null
    var downVotes:Long? = null
    var points:Long? = null
    var views:Long? = null
    var favorites:Long? = null
    var subscribers:Int? = null
    var replyCount:Int? = null
    var commentsCount:Int? = null
    var wordCount:Int? = null
    var reportCount:Int? = null
    var linksCount:Int? = null
    var linkedToCount:Int? = null
    var score:Int? = null
    var rank:Int? = null
    var labels:ArrayList<String>? = null
    var refUserIds:ArrayList<Int>? = null
    var refLinks:ArrayList<String>? = null
    var muteUserIds:ArrayList<Int>? = null
    var lastCommentDate:Date? = null
    var lastCommentId:Long? = null
    var lastCommentUserId:Int? = null
    var deleted:Date? = null
    var deletedBy:String? = null
    var locked:Date? = null
    var lockedBy:String? = null
    var hidden:Date? = null
    var hiddenBy:String? = null
    var status:String? = null
    var statusDate:Date? = null
    var statusBy:String? = null
    var archived:Boolean? = null
    var bumped:Date? = null
    var created:Date? = null
    var createdBy:String? = null
    var modified:Date? = null
    var modifiedBy:String? = null
    var refId:Long? = null
    var refSource:String? = null
    var refUrn:String? = null
}

open class PostComment
{
    var id:Long? = null
    var postId:Long? = null
    var userId:Int? = null
    var replyId:Long? = null
    @StringLength(2147483647)
    var content:String? = null

    @StringLength(2147483647)
    var contentHtml:String? = null

    var score:Int? = null
    var rank:Int? = null
    var upVotes:Long? = null
    var downVotes:Long? = null
    var favorites:Long? = null
    var wordCount:Int? = null
    var reportCount:Int? = null
    var deleted:Date? = null
    var hidden:Date? = null
    var modified:Date? = null
    var created:Date? = null
    var createdBy:String? = null
    var refId:Long? = null
    var refSource:String? = null
    var refUrn:String? = null
}

enum class PostType
{
    Announcement,
    Post,
    Showcase,
    Question,
    Request,
}

enum class ReportAction
{
    Dismiss,
    Delete,
}

open class UserRef
{
    var id:Int? = null
    var userName:String? = null
    var email:String? = null
    var refId:Int? = null
    var refSource:String? = null
    var refUrn:String? = null
}

open class OrganizationSubscription
{
    var id:Long? = null
    var organizationId:Int? = null
    var userId:Int? = null
    var userName:String? = null
    var postTypes:ArrayList<String>? = null
    var frequencyDays:Int? = null
    var lastSyncedId:Long? = null
    var lastSynced:Date? = null
    var created:Date? = null
}

enum class FlagType
{
    Violation,
    Spam,
    Abusive,
    Confidential,
    OffTopic,
    Other,
}

open class TechnologyStack : TechnologyStackBase()
{
}

open class Technology : TechnologyBase()
{
}

open class UserActivity
{
    var id:Int? = null
    var userName:String? = null
    var karma:Int? = null
    var technologyCount:Int? = null
    var techStacksCount:Int? = null
    var postsCount:Int? = null
    var postUpVotes:Int? = null
    var postDownVotes:Int? = null
    var commentUpVotes:Int? = null
    var commentDownVotes:Int? = null
    var postCommentsCount:Int? = null
    var pinnedCommentCount:Int? = null
    var postReportCount:Int? = null
    var postCommentReportCount:Int? = null
    var created:Date? = null
    var modified:Date? = null
}

enum class Frequency(val value:Int)
{
    Daily(1),
    Weekly(7),
    Monthly(30),
    Quarterly(90),
}

open class TechnologyHistory : TechnologyBase()
{
    var technologyId:Long? = null
    var operation:String? = null
}

open interface IRegisterStats
{
}

enum class TechnologyTier
{
    ProgrammingLanguage,
    Client,
    Http,
    Server,
    Data,
    SoftwareInfrastructure,
    OperatingSystem,
    HardwareInfrastructure,
    ThirdPartyServices,
}

open class TechnologyStackHistory : TechnologyStackBase()
{
    var technologyStackId:Long? = null
    var operation:String? = null
    var technologyIds:ArrayList<Long> = ArrayList<Long>()
}

open class UserInfo
{
    var userName:String? = null
    var avatarUrl:String? = null
    var stacksCount:Int? = null
}

open class TechnologyInfo
{
    var tier:TechnologyTier? = null
    var slug:String? = null
    var name:String? = null
    var logoUrl:String? = null
    var stacksCount:Int? = null
}

open class TechStackDetails : TechnologyStackBase()
{
    var technologyChoices:ArrayList<TechnologyInStack> = ArrayList<TechnologyInStack>()
}

open class OrganizationInfo
{
    var id:Int? = null
    var name:String? = null
    var slug:String? = null
    var refId:Long? = null
    var refSource:String? = null
    var upVotes:Long? = null
    var downVotes:Long? = null
    var membersCount:Long? = null
    var rank:Int? = null
    var disableInvites:Boolean? = null
    var lang:String? = null
    var postTypes:ArrayList<String>? = null
    var moderatorPostTypes:ArrayList<String>? = null
    var locked:Date? = null
    var labels:ArrayList<LabelInfo> = ArrayList<LabelInfo>()
    var categories:ArrayList<CategoryInfo> = ArrayList<CategoryInfo>()
}

@DataContract
open class Option
{
    @DataMember(Name="name")
    @SerializedName("name")
    var name:String? = null

    @DataMember(Name="title")
    @SerializedName("title")
    var title:String? = null

    @DataMember(Name="value")
    @SerializedName("value")
    var value:TechnologyTier? = null
}

open class UserVoiceUser
{
    var id:Int? = null
    var name:String? = null
    var email:String? = null
    var avatarUrl:String? = null
    var createdAt:Date? = null
    var updatedAt:Date? = null
}

open class UserVoiceComment
{
    var text:String? = null
    var formattedText:String? = null
    var createdAt:Date? = null
    var creator:UserVoiceUser? = null
}

open class PostReport
{
    var id:Long? = null
    var organizationId:Int? = null
    var postId:Long? = null
    var userId:Int? = null
    var userName:String? = null
    var flagType:FlagType? = null
    var reportNotes:String? = null
    var created:Date? = null
    var acknowledged:Date? = null
    var acknowledgedBy:String? = null
    var dismissed:Date? = null
    var dismissedBy:String? = null
}

open class PostCommentReport
{
    var id:Long? = null
    var organizationId:Int? = null
    var postId:Long? = null
    var postCommentId:Long? = null
    var userId:Int? = null
    var userName:String? = null
    var flagType:FlagType? = null
    var reportNotes:String? = null
    var created:Date? = null
    var acknowledged:Date? = null
    var acknowledgedBy:String? = null
    var dismissed:Date? = null
    var dismissedBy:String? = null
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
    var fields:String? = null

    @DataMember(Order=7)
    var meta:HashMap<String,String> = HashMap<String,String>()
}

open class TechnologyStackBase
{
    var id:Long? = null
    var name:String? = null
    var vendorName:String? = null
    var description:String? = null
    var appUrl:String? = null
    var screenshotUrl:String? = null
    var created:Date? = null
    var createdBy:String? = null
    var lastModified:Date? = null
    var lastModifiedBy:String? = null
    var isLocked:Boolean? = null
    var ownerId:String? = null
    var slug:String? = null
    @StringLength(2147483647)
    var details:String? = null

    @StringLength(2147483647)
    var detailsHtml:String? = null

    var lastStatusUpdate:Date? = null
    var organizationId:Int? = null
    var commentsPostId:Long? = null
    var viewCount:Int? = null
    var favCount:Int? = null
}

open class TechnologyBase
{
    var id:Long? = null
    var name:String? = null
    var vendorName:String? = null
    var vendorUrl:String? = null
    var productUrl:String? = null
    var logoUrl:String? = null
    var description:String? = null
    var created:Date? = null
    var createdBy:String? = null
    var lastModified:Date? = null
    var lastModifiedBy:String? = null
    var ownerId:String? = null
    var slug:String? = null
    var logoApproved:Boolean? = null
    var isLocked:Boolean? = null
    var tier:TechnologyTier? = null
    var lastStatusUpdate:Date? = null
    var organizationId:Int? = null
    var commentsPostId:Long? = null
    var viewCount:Int? = null
    var favCount:Int? = null
}

open class TechnologyInStack : TechnologyBase()
{
    var technologyId:Long? = null
    var technologyStackId:Long? = null
    var justification:String? = null
}

open class LabelInfo
{
    var slug:String? = null
    var color:String? = null
}

open class CategoryInfo
{
    var id:Int? = null
    var name:String? = null
    var slug:String? = null
}

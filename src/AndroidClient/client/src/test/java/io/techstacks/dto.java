/* Options:
Date: 2018-04-15 04:43:13
Version: 5.03
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: https://techstacks.io

Package: io.techstacks
GlobalNamespace: dto
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

package io.techstacks;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;
import com.google.gson.annotations.*;
import com.google.gson.reflect.*;

public class dto
{

    @Route("/ping")
    public static class Ping
    {
        
    }

    @Route(Path="/orgs/{Id}", Verbs="GET")
    public static class GetOrganization implements IReturn<GetOrganizationResponse>
    {
        public Integer id = null;
        
        public Integer getId() { return id; }
        public GetOrganization setId(Integer value) { this.id = value; return this; }
        private static Object responseType = GetOrganizationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/organizations/{Slug}", Verbs="GET")
    public static class GetOrganizationBySlug implements IReturn<GetOrganizationResponse>
    {
        public String slug = null;
        
        public String getSlug() { return slug; }
        public GetOrganizationBySlug setSlug(String value) { this.slug = value; return this; }
        private static Object responseType = GetOrganizationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{Id}/members", Verbs="GET")
    public static class GetOrganizationMembers implements IReturn<GetOrganizationMembersResponse>
    {
        public Integer id = null;
        
        public Integer getId() { return id; }
        public GetOrganizationMembers setId(Integer value) { this.id = value; return this; }
        private static Object responseType = GetOrganizationMembersResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{Id}/admin", Verbs="GET")
    public static class GetOrganizationAdmin implements IReturn<GetOrganizationAdminResponse>
    {
        public Integer id = null;
        
        public Integer getId() { return id; }
        public GetOrganizationAdmin setId(Integer value) { this.id = value; return this; }
        private static Object responseType = GetOrganizationAdminResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/posts/new", Verbs="POST")
    public static class CreateOrganizationForTechnology implements IReturn<CreateOrganizationForTechnologyResponse>
    {
        public Long technologyId = null;
        public Long techStackId = null;
        
        public Long getTechnologyId() { return technologyId; }
        public CreateOrganizationForTechnology setTechnologyId(Long value) { this.technologyId = value; return this; }
        public Long getTechStackId() { return techStackId; }
        public CreateOrganizationForTechnology setTechStackId(Long value) { this.techStackId = value; return this; }
        private static Object responseType = CreateOrganizationForTechnologyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs", Verbs="POST")
    public static class CreateOrganization implements IReturn<CreateOrganizationResponse>
    {
        public String name = null;
        public String slug = null;
        public String description = null;
        public Long refId = null;
        public String refSource = null;
        public String refUrn = null;
        
        public String getName() { return name; }
        public CreateOrganization setName(String value) { this.name = value; return this; }
        public String getSlug() { return slug; }
        public CreateOrganization setSlug(String value) { this.slug = value; return this; }
        public String getDescription() { return description; }
        public CreateOrganization setDescription(String value) { this.description = value; return this; }
        public Long getRefId() { return refId; }
        public CreateOrganization setRefId(Long value) { this.refId = value; return this; }
        public String getRefSource() { return refSource; }
        public CreateOrganization setRefSource(String value) { this.refSource = value; return this; }
        public String getRefUrn() { return refUrn; }
        public CreateOrganization setRefUrn(String value) { this.refUrn = value; return this; }
        private static Object responseType = CreateOrganizationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{Id}", Verbs="PUT")
    public static class UpdateOrganization implements IReturn<UpdateOrganizationResponse>
    {
        public Integer id = null;
        public String slug = null;
        public String name = null;
        public String description = null;
        public String color = null;
        public String textColor = null;
        public String linkColor = null;
        public String backgroundColor = null;
        public String backgroundUrl = null;
        public String logoUrl = null;
        public String heroUrl = null;
        public String lang = null;
        public Integer deletePostsWithReportCount = null;
        public Boolean disableInvites = null;
        public String defaultPostType = null;
        public ArrayList<String> defaultSubscriptionPostTypes = null;
        public ArrayList<String> postTypes = null;
        public ArrayList<String> moderatorPostTypes = null;
        public ArrayList<Integer> technologyIds = null;
        
        public Integer getId() { return id; }
        public UpdateOrganization setId(Integer value) { this.id = value; return this; }
        public String getSlug() { return slug; }
        public UpdateOrganization setSlug(String value) { this.slug = value; return this; }
        public String getName() { return name; }
        public UpdateOrganization setName(String value) { this.name = value; return this; }
        public String getDescription() { return description; }
        public UpdateOrganization setDescription(String value) { this.description = value; return this; }
        public String getColor() { return color; }
        public UpdateOrganization setColor(String value) { this.color = value; return this; }
        public String getTextColor() { return textColor; }
        public UpdateOrganization setTextColor(String value) { this.textColor = value; return this; }
        public String getLinkColor() { return linkColor; }
        public UpdateOrganization setLinkColor(String value) { this.linkColor = value; return this; }
        public String getBackgroundColor() { return backgroundColor; }
        public UpdateOrganization setBackgroundColor(String value) { this.backgroundColor = value; return this; }
        public String getBackgroundUrl() { return backgroundUrl; }
        public UpdateOrganization setBackgroundUrl(String value) { this.backgroundUrl = value; return this; }
        public String getLogoUrl() { return logoUrl; }
        public UpdateOrganization setLogoUrl(String value) { this.logoUrl = value; return this; }
        public String getHeroUrl() { return heroUrl; }
        public UpdateOrganization setHeroUrl(String value) { this.heroUrl = value; return this; }
        public String getLang() { return lang; }
        public UpdateOrganization setLang(String value) { this.lang = value; return this; }
        public Integer getDeletePostsWithReportCount() { return deletePostsWithReportCount; }
        public UpdateOrganization setDeletePostsWithReportCount(Integer value) { this.deletePostsWithReportCount = value; return this; }
        public Boolean isDisableInvites() { return disableInvites; }
        public UpdateOrganization setDisableInvites(Boolean value) { this.disableInvites = value; return this; }
        public String getDefaultPostType() { return defaultPostType; }
        public UpdateOrganization setDefaultPostType(String value) { this.defaultPostType = value; return this; }
        public ArrayList<String> getDefaultSubscriptionPostTypes() { return defaultSubscriptionPostTypes; }
        public UpdateOrganization setDefaultSubscriptionPostTypes(ArrayList<String> value) { this.defaultSubscriptionPostTypes = value; return this; }
        public ArrayList<String> getPostTypes() { return postTypes; }
        public UpdateOrganization setPostTypes(ArrayList<String> value) { this.postTypes = value; return this; }
        public ArrayList<String> getModeratorPostTypes() { return moderatorPostTypes; }
        public UpdateOrganization setModeratorPostTypes(ArrayList<String> value) { this.moderatorPostTypes = value; return this; }
        public ArrayList<Integer> getTechnologyIds() { return technologyIds; }
        public UpdateOrganization setTechnologyIds(ArrayList<Integer> value) { this.technologyIds = value; return this; }
        private static Object responseType = UpdateOrganizationResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{Id}", Verbs="DELETE")
    public static class DeleteOrganization implements IReturnVoid
    {
        public Integer id = null;
        
        public Integer getId() { return id; }
        public DeleteOrganization setId(Integer value) { this.id = value; return this; }
    }

    @Route(Path="/orgs/{Id}/lock", Verbs="PUT")
    public static class LockOrganization implements IReturnVoid
    {
        public Integer id = null;
        public Boolean lock = null;
        public String reason = null;
        
        public Integer getId() { return id; }
        public LockOrganization setId(Integer value) { this.id = value; return this; }
        public Boolean isLock() { return lock; }
        public LockOrganization setLock(Boolean value) { this.lock = value; return this; }
        public String getReason() { return reason; }
        public LockOrganization setReason(String value) { this.reason = value; return this; }
    }

    @Route(Path="/orgs/{OrganizationId}/labels", Verbs="POST")
    public static class AddOrganizationLabel implements IReturn<OrganizationLabelResponse>
    {
        public Integer organizationId = null;
        public String slug = null;
        public String description = null;
        public String color = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public AddOrganizationLabel setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getSlug() { return slug; }
        public AddOrganizationLabel setSlug(String value) { this.slug = value; return this; }
        public String getDescription() { return description; }
        public AddOrganizationLabel setDescription(String value) { this.description = value; return this; }
        public String getColor() { return color; }
        public AddOrganizationLabel setColor(String value) { this.color = value; return this; }
        private static Object responseType = OrganizationLabelResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/members/{Slug}", Verbs="PUT")
    public static class UpdateOrganizationLabel implements IReturn<OrganizationLabelResponse>
    {
        public Integer organizationId = null;
        public String slug = null;
        public String description = null;
        public String color = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public UpdateOrganizationLabel setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getSlug() { return slug; }
        public UpdateOrganizationLabel setSlug(String value) { this.slug = value; return this; }
        public String getDescription() { return description; }
        public UpdateOrganizationLabel setDescription(String value) { this.description = value; return this; }
        public String getColor() { return color; }
        public UpdateOrganizationLabel setColor(String value) { this.color = value; return this; }
        private static Object responseType = OrganizationLabelResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/labels/{Slug}", Verbs="DELETE")
    public static class RemoveOrganizationLabel implements IReturnVoid
    {
        public Integer organizationId = null;
        public String slug = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public RemoveOrganizationLabel setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getSlug() { return slug; }
        public RemoveOrganizationLabel setSlug(String value) { this.slug = value; return this; }
    }

    @Route(Path="/orgs/{OrganizationId}/categories", Verbs="POST")
    public static class AddOrganizationCategory implements IReturn<AddCategoryResponse>
    {
        public Integer organizationId = null;
        public String slug = null;
        public String name = null;
        public String description = null;
        public ArrayList<Integer> technologyIds = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public AddOrganizationCategory setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getSlug() { return slug; }
        public AddOrganizationCategory setSlug(String value) { this.slug = value; return this; }
        public String getName() { return name; }
        public AddOrganizationCategory setName(String value) { this.name = value; return this; }
        public String getDescription() { return description; }
        public AddOrganizationCategory setDescription(String value) { this.description = value; return this; }
        public ArrayList<Integer> getTechnologyIds() { return technologyIds; }
        public AddOrganizationCategory setTechnologyIds(ArrayList<Integer> value) { this.technologyIds = value; return this; }
        private static Object responseType = AddCategoryResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/categories/{Id}", Verbs="PUT")
    public static class UpdateOrganizationCategory implements IReturn<UpdateCategoryResponse>
    {
        public Integer organizationId = null;
        public Integer id = null;
        public String name = null;
        public String slug = null;
        public String description = null;
        public ArrayList<Integer> technologyIds = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public UpdateOrganizationCategory setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Integer getId() { return id; }
        public UpdateOrganizationCategory setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public UpdateOrganizationCategory setName(String value) { this.name = value; return this; }
        public String getSlug() { return slug; }
        public UpdateOrganizationCategory setSlug(String value) { this.slug = value; return this; }
        public String getDescription() { return description; }
        public UpdateOrganizationCategory setDescription(String value) { this.description = value; return this; }
        public ArrayList<Integer> getTechnologyIds() { return technologyIds; }
        public UpdateOrganizationCategory setTechnologyIds(ArrayList<Integer> value) { this.technologyIds = value; return this; }
        private static Object responseType = UpdateCategoryResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/categories/{Id}", Verbs="DELETE")
    public static class DeleteOrganizationCategory implements IReturnVoid
    {
        public Integer organizationId = null;
        public Integer id = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public DeleteOrganizationCategory setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Integer getId() { return id; }
        public DeleteOrganizationCategory setId(Integer value) { this.id = value; return this; }
    }

    @Route(Path="/orgs/{OrganizationId}/members", Verbs="POST")
    public static class AddOrganizationMember implements IReturn<AddOrganizationMemberResponse>
    {
        public Integer organizationId = null;
        public String userName = null;
        public Boolean isOwner = null;
        public Boolean isModerator = null;
        public Boolean denyPosts = null;
        public Boolean denyComments = null;
        public Boolean denyAll = null;
        public String notes = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public AddOrganizationMember setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getUserName() { return userName; }
        public AddOrganizationMember setUserName(String value) { this.userName = value; return this; }
        public Boolean getIsOwner() { return isOwner; }
        public AddOrganizationMember setIsOwner(Boolean value) { this.isOwner = value; return this; }
        public Boolean getIsModerator() { return isModerator; }
        public AddOrganizationMember setIsModerator(Boolean value) { this.isModerator = value; return this; }
        public Boolean isDenyPosts() { return denyPosts; }
        public AddOrganizationMember setDenyPosts(Boolean value) { this.denyPosts = value; return this; }
        public Boolean isDenyComments() { return denyComments; }
        public AddOrganizationMember setDenyComments(Boolean value) { this.denyComments = value; return this; }
        public Boolean isDenyAll() { return denyAll; }
        public AddOrganizationMember setDenyAll(Boolean value) { this.denyAll = value; return this; }
        public String getNotes() { return notes; }
        public AddOrganizationMember setNotes(String value) { this.notes = value; return this; }
        private static Object responseType = AddOrganizationMemberResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/members/{Id}", Verbs="PUT")
    public static class UpdateOrganizationMember implements IReturn<UpdateOrganizationMemberResponse>
    {
        public Integer organizationId = null;
        public Integer userId = null;
        public Boolean isOwner = null;
        public Boolean isModerator = null;
        public Boolean denyPosts = null;
        public Boolean denyComments = null;
        public Boolean denyAll = null;
        public String notes = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public UpdateOrganizationMember setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Integer getUserId() { return userId; }
        public UpdateOrganizationMember setUserId(Integer value) { this.userId = value; return this; }
        public Boolean getIsOwner() { return isOwner; }
        public UpdateOrganizationMember setIsOwner(Boolean value) { this.isOwner = value; return this; }
        public Boolean getIsModerator() { return isModerator; }
        public UpdateOrganizationMember setIsModerator(Boolean value) { this.isModerator = value; return this; }
        public Boolean isDenyPosts() { return denyPosts; }
        public UpdateOrganizationMember setDenyPosts(Boolean value) { this.denyPosts = value; return this; }
        public Boolean isDenyComments() { return denyComments; }
        public UpdateOrganizationMember setDenyComments(Boolean value) { this.denyComments = value; return this; }
        public Boolean isDenyAll() { return denyAll; }
        public UpdateOrganizationMember setDenyAll(Boolean value) { this.denyAll = value; return this; }
        public String getNotes() { return notes; }
        public UpdateOrganizationMember setNotes(String value) { this.notes = value; return this; }
        private static Object responseType = UpdateOrganizationMemberResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/members/{UserId}", Verbs="DELETE")
    public static class RemoveOrganizationMember implements IReturnVoid
    {
        public Integer organizationId = null;
        public Integer userId = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public RemoveOrganizationMember setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Integer getUserId() { return userId; }
        public RemoveOrganizationMember setUserId(Integer value) { this.userId = value; return this; }
    }

    @Route(Path="/orgs/{OrganizationId}/members/set", Verbs="POST")
    public static class SetOrganizationMembers implements IReturn<SetOrganizationMembersResponse>
    {
        public Integer organizationId = null;
        public ArrayList<String> githubUserNames = null;
        public ArrayList<String> twitterUserNames = null;
        public ArrayList<String> emails = null;
        public Boolean removeUnspecifiedMembers = null;
        public Boolean isOwner = null;
        public Boolean isModerator = null;
        public Boolean denyPosts = null;
        public Boolean denyComments = null;
        public Boolean denyAll = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public SetOrganizationMembers setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public ArrayList<String> getGithubUserNames() { return githubUserNames; }
        public SetOrganizationMembers setGithubUserNames(ArrayList<String> value) { this.githubUserNames = value; return this; }
        public ArrayList<String> getTwitterUserNames() { return twitterUserNames; }
        public SetOrganizationMembers setTwitterUserNames(ArrayList<String> value) { this.twitterUserNames = value; return this; }
        public ArrayList<String> getEmails() { return emails; }
        public SetOrganizationMembers setEmails(ArrayList<String> value) { this.emails = value; return this; }
        public Boolean isRemoveUnspecifiedMembers() { return removeUnspecifiedMembers; }
        public SetOrganizationMembers setRemoveUnspecifiedMembers(Boolean value) { this.removeUnspecifiedMembers = value; return this; }
        public Boolean getIsOwner() { return isOwner; }
        public SetOrganizationMembers setIsOwner(Boolean value) { this.isOwner = value; return this; }
        public Boolean getIsModerator() { return isModerator; }
        public SetOrganizationMembers setIsModerator(Boolean value) { this.isModerator = value; return this; }
        public Boolean isDenyPosts() { return denyPosts; }
        public SetOrganizationMembers setDenyPosts(Boolean value) { this.denyPosts = value; return this; }
        public Boolean isDenyComments() { return denyComments; }
        public SetOrganizationMembers setDenyComments(Boolean value) { this.denyComments = value; return this; }
        public Boolean isDenyAll() { return denyAll; }
        public SetOrganizationMembers setDenyAll(Boolean value) { this.denyAll = value; return this; }
        private static Object responseType = SetOrganizationMembersResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/invites", Verbs="GET")
    public static class GetOrganizationMemberInvites implements IReturn<GetOrganizationMemberInvitesResponse>
    {
        public Integer organizationId = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public GetOrganizationMemberInvites setOrganizationId(Integer value) { this.organizationId = value; return this; }
        private static Object responseType = GetOrganizationMemberInvitesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/invites", Verbs="POST")
    public static class RequestOrganizationMemberInvite implements IReturn<RequestOrganizationMemberInviteResponse>
    {
        public Integer organizationId = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public RequestOrganizationMemberInvite setOrganizationId(Integer value) { this.organizationId = value; return this; }
        private static Object responseType = RequestOrganizationMemberInviteResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/invites/{UserId}", Verbs="PUT")
    public static class UpdateOrganizationMemberInvite implements IReturn<UpdateOrganizationMemberInviteResponse>
    {
        public Integer organizationId = null;
        public String userName = null;
        public Boolean approve = null;
        public Boolean dismiss = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public UpdateOrganizationMemberInvite setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getUserName() { return userName; }
        public UpdateOrganizationMemberInvite setUserName(String value) { this.userName = value; return this; }
        public Boolean isApprove() { return approve; }
        public UpdateOrganizationMemberInvite setApprove(Boolean value) { this.approve = value; return this; }
        public Boolean isDismiss() { return dismiss; }
        public UpdateOrganizationMemberInvite setDismiss(Boolean value) { this.dismiss = value; return this; }
        private static Object responseType = UpdateOrganizationMemberInviteResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts", Verbs="GET")
    public static class QueryPosts extends QueryDb<Post> implements IReturn<QueryResponse<Post>>
    {
        public ArrayList<Integer> ids = null;
        public Integer organizationId = null;
        public ArrayList<Integer> organizationIds = null;
        public ArrayList<String> types = null;
        public ArrayList<Integer> anyTechnologyIds = null;
        public ArrayList<String> is = null;
        
        public ArrayList<Integer> getIds() { return ids; }
        public QueryPosts setIds(ArrayList<Integer> value) { this.ids = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public QueryPosts setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public ArrayList<Integer> getOrganizationIds() { return organizationIds; }
        public QueryPosts setOrganizationIds(ArrayList<Integer> value) { this.organizationIds = value; return this; }
        public ArrayList<String> getTypes() { return types; }
        public QueryPosts setTypes(ArrayList<String> value) { this.types = value; return this; }
        public ArrayList<Integer> getAnyTechnologyIds() { return anyTechnologyIds; }
        public QueryPosts setAnyTechnologyIds(ArrayList<Integer> value) { this.anyTechnologyIds = value; return this; }
        public ArrayList<String> getIs() { return is; }
        public QueryPosts setIs(ArrayList<String> value) { this.is = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<Post>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{Id}", Verbs="GET")
    public static class GetPost implements IReturn<GetPostResponse>
    {
        public Long id = null;
        public String include = null;
        
        public Long getId() { return id; }
        public GetPost setId(Long value) { this.id = value; return this; }
        public String getInclude() { return include; }
        public GetPost setInclude(String value) { this.include = value; return this; }
        private static Object responseType = GetPostResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts", Verbs="POST")
    public static class CreatePost implements IReturn<CreatePostResponse>
    {
        public Integer organizationId = null;
        public PostType type = null;
        public Integer categoryId = null;
        public String title = null;
        public String url = null;
        public String imageUrl = null;
        public String content = null;
        public Boolean lock = null;
        public ArrayList<Integer> technologyIds = null;
        public ArrayList<String> labels = null;
        public Date fromDate = null;
        public Date toDate = null;
        public String metaType = null;
        public String meta = null;
        public Long refId = null;
        public String refSource = null;
        public String refUrn = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public CreatePost setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public PostType getType() { return type; }
        public CreatePost setType(PostType value) { this.type = value; return this; }
        public Integer getCategoryId() { return categoryId; }
        public CreatePost setCategoryId(Integer value) { this.categoryId = value; return this; }
        public String getTitle() { return title; }
        public CreatePost setTitle(String value) { this.title = value; return this; }
        public String getUrl() { return url; }
        public CreatePost setUrl(String value) { this.url = value; return this; }
        public String getImageUrl() { return imageUrl; }
        public CreatePost setImageUrl(String value) { this.imageUrl = value; return this; }
        public String getContent() { return content; }
        public CreatePost setContent(String value) { this.content = value; return this; }
        public Boolean isLock() { return lock; }
        public CreatePost setLock(Boolean value) { this.lock = value; return this; }
        public ArrayList<Integer> getTechnologyIds() { return technologyIds; }
        public CreatePost setTechnologyIds(ArrayList<Integer> value) { this.technologyIds = value; return this; }
        public ArrayList<String> getLabels() { return labels; }
        public CreatePost setLabels(ArrayList<String> value) { this.labels = value; return this; }
        public Date getFromDate() { return fromDate; }
        public CreatePost setFromDate(Date value) { this.fromDate = value; return this; }
        public Date getToDate() { return toDate; }
        public CreatePost setToDate(Date value) { this.toDate = value; return this; }
        public String getMetaType() { return metaType; }
        public CreatePost setMetaType(String value) { this.metaType = value; return this; }
        public String getMeta() { return meta; }
        public CreatePost setMeta(String value) { this.meta = value; return this; }
        public Long getRefId() { return refId; }
        public CreatePost setRefId(Long value) { this.refId = value; return this; }
        public String getRefSource() { return refSource; }
        public CreatePost setRefSource(String value) { this.refSource = value; return this; }
        public String getRefUrn() { return refUrn; }
        public CreatePost setRefUrn(String value) { this.refUrn = value; return this; }
        private static Object responseType = CreatePostResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{Id}", Verbs="PUT")
    public static class UpdatePost implements IReturn<UpdatePostResponse>
    {
        public Long id = null;
        public Integer organizationId = null;
        public PostType type = null;
        public Integer categoryId = null;
        public String title = null;
        public String url = null;
        public String imageUrl = null;
        public String content = null;
        public Boolean lock = null;
        public ArrayList<Integer> technologyIds = null;
        public ArrayList<String> labels = null;
        public Date fromDate = null;
        public Date toDate = null;
        public String metaType = null;
        public String meta = null;
        
        public Long getId() { return id; }
        public UpdatePost setId(Long value) { this.id = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public UpdatePost setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public PostType getType() { return type; }
        public UpdatePost setType(PostType value) { this.type = value; return this; }
        public Integer getCategoryId() { return categoryId; }
        public UpdatePost setCategoryId(Integer value) { this.categoryId = value; return this; }
        public String getTitle() { return title; }
        public UpdatePost setTitle(String value) { this.title = value; return this; }
        public String getUrl() { return url; }
        public UpdatePost setUrl(String value) { this.url = value; return this; }
        public String getImageUrl() { return imageUrl; }
        public UpdatePost setImageUrl(String value) { this.imageUrl = value; return this; }
        public String getContent() { return content; }
        public UpdatePost setContent(String value) { this.content = value; return this; }
        public Boolean isLock() { return lock; }
        public UpdatePost setLock(Boolean value) { this.lock = value; return this; }
        public ArrayList<Integer> getTechnologyIds() { return technologyIds; }
        public UpdatePost setTechnologyIds(ArrayList<Integer> value) { this.technologyIds = value; return this; }
        public ArrayList<String> getLabels() { return labels; }
        public UpdatePost setLabels(ArrayList<String> value) { this.labels = value; return this; }
        public Date getFromDate() { return fromDate; }
        public UpdatePost setFromDate(Date value) { this.fromDate = value; return this; }
        public Date getToDate() { return toDate; }
        public UpdatePost setToDate(Date value) { this.toDate = value; return this; }
        public String getMetaType() { return metaType; }
        public UpdatePost setMetaType(String value) { this.metaType = value; return this; }
        public String getMeta() { return meta; }
        public UpdatePost setMeta(String value) { this.meta = value; return this; }
        private static Object responseType = UpdatePostResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{Id}", Verbs="DELETE")
    public static class DeletePost implements IReturn<DeletePostResponse>
    {
        public Long id = null;
        
        public Long getId() { return id; }
        public DeletePost setId(Long value) { this.id = value; return this; }
        private static Object responseType = DeletePostResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{Id}/lock", Verbs="PUT")
    public static class LockPost implements IReturnVoid
    {
        public Long id = null;
        public Boolean lock = null;
        public String reason = null;
        
        public Long getId() { return id; }
        public LockPost setId(Long value) { this.id = value; return this; }
        public Boolean isLock() { return lock; }
        public LockPost setLock(Boolean value) { this.lock = value; return this; }
        public String getReason() { return reason; }
        public LockPost setReason(String value) { this.reason = value; return this; }
    }

    @Route(Path="/posts/{Id}/hide", Verbs="PUT")
    public static class HidePost implements IReturnVoid
    {
        public Long id = null;
        public Boolean hide = null;
        public String reason = null;
        
        public Long getId() { return id; }
        public HidePost setId(Long value) { this.id = value; return this; }
        public Boolean isHide() { return hide; }
        public HidePost setHide(Boolean value) { this.hide = value; return this; }
        public String getReason() { return reason; }
        public HidePost setReason(String value) { this.reason = value; return this; }
    }

    @Route(Path="/posts/{Id}/status/{Status}", Verbs="PUT")
    public static class ChangeStatusPost implements IReturnVoid
    {
        public Long id = null;
        public String status = null;
        public String reason = null;
        
        public Long getId() { return id; }
        public ChangeStatusPost setId(Long value) { this.id = value; return this; }
        public String getStatus() { return status; }
        public ChangeStatusPost setStatus(String value) { this.status = value; return this; }
        public String getReason() { return reason; }
        public ChangeStatusPost setReason(String value) { this.reason = value; return this; }
    }

    @Route(Path="/posts/{PostId}/report/{Id}", Verbs="POST")
    public static class ActionPostReport implements IReturnVoid
    {
        public Long postId = null;
        public Long id = null;
        public ReportAction reportAction = null;
        
        public Long getPostId() { return postId; }
        public ActionPostReport setPostId(Long value) { this.postId = value; return this; }
        public Long getId() { return id; }
        public ActionPostReport setId(Long value) { this.id = value; return this; }
        public ReportAction getReportAction() { return reportAction; }
        public ActionPostReport setReportAction(ReportAction value) { this.reportAction = value; return this; }
    }

    @Route(Path="/posts/{PostId}/comments", Verbs="POST")
    public static class CreatePostComment implements IReturn<CreatePostCommentResponse>
    {
        public Long postId = null;
        public Long replyId = null;
        public String content = null;
        
        public Long getPostId() { return postId; }
        public CreatePostComment setPostId(Long value) { this.postId = value; return this; }
        public Long getReplyId() { return replyId; }
        public CreatePostComment setReplyId(Long value) { this.replyId = value; return this; }
        public String getContent() { return content; }
        public CreatePostComment setContent(String value) { this.content = value; return this; }
        private static Object responseType = CreatePostCommentResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{PostId}/comments/{Id}", Verbs="PUT")
    public static class UpdatePostComment implements IReturn<UpdatePostCommentResponse>
    {
        public Long id = null;
        public Long postId = null;
        public String content = null;
        
        public Long getId() { return id; }
        public UpdatePostComment setId(Long value) { this.id = value; return this; }
        public Long getPostId() { return postId; }
        public UpdatePostComment setPostId(Long value) { this.postId = value; return this; }
        public String getContent() { return content; }
        public UpdatePostComment setContent(String value) { this.content = value; return this; }
        private static Object responseType = UpdatePostCommentResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{PostId}/comments/{Id}", Verbs="DELETE")
    public static class DeletePostComment implements IReturn<DeletePostCommentResponse>
    {
        public Long id = null;
        public Long postId = null;
        
        public Long getId() { return id; }
        public DeletePostComment setId(Long value) { this.id = value; return this; }
        public Long getPostId() { return postId; }
        public DeletePostComment setPostId(Long value) { this.postId = value; return this; }
        private static Object responseType = DeletePostCommentResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{PostId}/comments/{PostCommentId}/report/{Id}", Verbs="POST")
    public static class ActionPostCommentReport implements IReturnVoid
    {
        public Long id = null;
        public Long postCommentId = null;
        public Long postId = null;
        public ReportAction reportAction = null;
        
        public Long getId() { return id; }
        public ActionPostCommentReport setId(Long value) { this.id = value; return this; }
        public Long getPostCommentId() { return postCommentId; }
        public ActionPostCommentReport setPostCommentId(Long value) { this.postCommentId = value; return this; }
        public Long getPostId() { return postId; }
        public ActionPostCommentReport setPostId(Long value) { this.postId = value; return this; }
        public ReportAction getReportAction() { return reportAction; }
        public ActionPostCommentReport setReportAction(ReportAction value) { this.reportAction = value; return this; }
    }

    @Route("/user/comments/votes")
    public static class GetUserPostCommentVotes implements IReturn<GetUserPostCommentVotesResponse>
    {
        public Long postId = null;
        
        public Long getPostId() { return postId; }
        public GetUserPostCommentVotes setPostId(Long value) { this.postId = value; return this; }
        private static Object responseType = GetUserPostCommentVotesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{PostId}/comments/{Id}/pin", Verbs="UPDATE")
    public static class PinPostComment implements IReturn<PinPostCommentResponse>
    {
        public Long id = null;
        public Long postId = null;
        public Boolean pin = null;
        
        public Long getId() { return id; }
        public PinPostComment setId(Long value) { this.id = value; return this; }
        public Long getPostId() { return postId; }
        public PinPostComment setPostId(Long value) { this.postId = value; return this; }
        public Boolean isPin() { return pin; }
        public PinPostComment setPin(Boolean value) { this.pin = value; return this; }
        private static Object responseType = PinPostCommentResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/users/by-email")
    public static class GetUsersByEmails implements IReturn<GetUsersByEmailsResponse>
    {
        public ArrayList<String> emails = null;
        
        public ArrayList<String> getEmails() { return emails; }
        public GetUsersByEmails setEmails(ArrayList<String> value) { this.emails = value; return this; }
        private static Object responseType = GetUsersByEmailsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/user/posts/activity")
    public static class GetUserPostActivity implements IReturn<GetUserPostActivityResponse>
    {
        
        private static Object responseType = GetUserPostActivityResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/user/organizations")
    public static class GetUserOrganizations implements IReturn<GetUserOrganizationsResponse>
    {
        
        private static Object responseType = GetUserOrganizationsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{Id}/vote", Verbs="PUT")
    public static class UserPostVote implements IReturn<UserPostVoteResponse>
    {
        public Long id = null;
        public Integer weight = null;
        
        public Long getId() { return id; }
        public UserPostVote setId(Long value) { this.id = value; return this; }
        public Integer getWeight() { return weight; }
        public UserPostVote setWeight(Integer value) { this.weight = value; return this; }
        private static Object responseType = UserPostVoteResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{Id}/favorite", Verbs="PUT")
    public static class UserPostFavorite implements IReturn<UserPostFavoriteResponse>
    {
        public Long id = null;
        
        public Long getId() { return id; }
        public UserPostFavorite setId(Long value) { this.id = value; return this; }
        private static Object responseType = UserPostFavoriteResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{Id}/report", Verbs="PUT")
    public static class UserPostReport implements IReturn<UserPostReportResponse>
    {
        public Long id = null;
        public FlagType flagType = null;
        public String reportNotes = null;
        
        public Long getId() { return id; }
        public UserPostReport setId(Long value) { this.id = value; return this; }
        public FlagType getFlagType() { return flagType; }
        public UserPostReport setFlagType(FlagType value) { this.flagType = value; return this; }
        public String getReportNotes() { return reportNotes; }
        public UserPostReport setReportNotes(String value) { this.reportNotes = value; return this; }
        private static Object responseType = UserPostReportResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{PostId}/comments/{Id}", Verbs="GET")
    public static class UserPostCommentVote implements IReturn<UserPostCommentVoteResponse>
    {
        public Long id = null;
        public Long postId = null;
        public Integer weight = null;
        
        public Long getId() { return id; }
        public UserPostCommentVote setId(Long value) { this.id = value; return this; }
        public Long getPostId() { return postId; }
        public UserPostCommentVote setPostId(Long value) { this.postId = value; return this; }
        public Integer getWeight() { return weight; }
        public UserPostCommentVote setWeight(Integer value) { this.weight = value; return this; }
        private static Object responseType = UserPostCommentVoteResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/{PostId}/comments/{Id}/report", Verbs="PUT")
    public static class UserPostCommentReport implements IReturn<UserPostCommentReportResponse>
    {
        public Long id = null;
        public Long postId = null;
        public FlagType flagType = null;
        public String reportNotes = null;
        
        public Long getId() { return id; }
        public UserPostCommentReport setId(Long value) { this.id = value; return this; }
        public Long getPostId() { return postId; }
        public UserPostCommentReport setPostId(Long value) { this.postId = value; return this; }
        public FlagType getFlagType() { return flagType; }
        public UserPostCommentReport setFlagType(FlagType value) { this.flagType = value; return this; }
        public String getReportNotes() { return reportNotes; }
        public UserPostCommentReport setReportNotes(String value) { this.reportNotes = value; return this; }
        private static Object responseType = UserPostCommentReportResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/prerender/{Path*}", Verbs="PUT")
    public static class StorePreRender implements IReturnVoid
    {
        public String path = null;
        
        public String getPath() { return path; }
        public StorePreRender setPath(String value) { this.path = value; return this; }
    }

    @Route(Path="/prerender/{Path*}", Verbs="GET")
    public static class GetPreRender implements IReturn<String>
    {
        public String path = null;
        
        public String getPath() { return path; }
        public GetPreRender setPath(String value) { this.path = value; return this; }
        private static Object responseType = String.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/my-session")
    public static class SessionInfo implements IReturn<SessionInfoResponse>
    {
        
        private static Object responseType = SessionInfoResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/orgs/{OrganizationId}/subscribe", Verbs="PUT")
    public static class SubscribeToOrganization implements IReturnVoid
    {
        public Integer organizationId = null;
        public ArrayList<PostType> postTypes = null;
        public Frequency frequency = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public SubscribeToOrganization setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public ArrayList<PostType> getPostTypes() { return postTypes; }
        public SubscribeToOrganization setPostTypes(ArrayList<PostType> value) { this.postTypes = value; return this; }
        public Frequency getFrequency() { return frequency; }
        public SubscribeToOrganization setFrequency(Frequency value) { this.frequency = value; return this; }
    }

    @Route(Path="/posts/{PostId}/subscribe", Verbs="PUT")
    public static class SubscribeToPost implements IReturnVoid
    {
        public Long postId = null;
        
        public Long getPostId() { return postId; }
        public SubscribeToPost setPostId(Long value) { this.postId = value; return this; }
    }

    @Route(Path="/orgs/{OrganizationId}/subscribe", Verbs="DELETE")
    public static class DeleteOrganizationSubscription implements IReturnVoid
    {
        public Long organizationId = null;
        
        public Long getOrganizationId() { return organizationId; }
        public DeleteOrganizationSubscription setOrganizationId(Long value) { this.organizationId = value; return this; }
    }

    @Route(Path="/posts/{PostId}/subscribe", Verbs="DELETE")
    public static class DeletePostSubscription implements IReturnVoid
    {
        public Long postId = null;
        
        public Long getPostId() { return postId; }
        public DeletePostSubscription setPostId(Long value) { this.postId = value; return this; }
    }

    @Route(Path="/technology/{Slug}/previous-versions", Verbs="GET")
    public static class GetTechnologyPreviousVersions implements IReturn<GetTechnologyPreviousVersionsResponse>
    {
        public String slug = null;
        
        public String getSlug() { return slug; }
        public GetTechnologyPreviousVersions setSlug(String value) { this.slug = value; return this; }
        private static Object responseType = GetTechnologyPreviousVersionsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/technology", Verbs="GET")
    public static class GetAllTechnologies implements IReturn<GetAllTechnologiesResponse>
    {
        
        private static Object responseType = GetAllTechnologiesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/technology/search")
    @AutoQueryViewer(DefaultSearchField="Tier", DefaultSearchText="Data", DefaultSearchType="=", Description="Explore different Technologies", IconUrl="octicon:database", Title="Find Technologies")
    public static class FindTechnologies extends QueryDb<Technology> implements IReturn<QueryResponse<Technology>>
    {
        public String name = null;
        public String nameContains = null;
        
        public String getName() { return name; }
        public FindTechnologies setName(String value) { this.name = value; return this; }
        public String getNameContains() { return nameContains; }
        public FindTechnologies setNameContains(String value) { this.nameContains = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<Technology>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route("/technology/query")
    public static class QueryTechnology extends QueryDb<Technology> implements IReturn<QueryResponse<Technology>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<Technology>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route("/technology/{Slug}")
    public static class GetTechnology implements IReturn<GetTechnologyResponse>, IRegisterStats
    {
        public String slug = null;
        
        public String getSlug() { return slug; }
        public GetTechnology setSlug(String value) { this.slug = value; return this; }
        private static Object responseType = GetTechnologyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/technology/{Slug}/favorites")
    public static class GetTechnologyFavoriteDetails implements IReturn<GetTechnologyFavoriteDetailsResponse>
    {
        public String slug = null;
        
        public String getSlug() { return slug; }
        public GetTechnologyFavoriteDetails setSlug(String value) { this.slug = value; return this; }
        private static Object responseType = GetTechnologyFavoriteDetailsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/technology", Verbs="POST")
    public static class CreateTechnology implements IReturn<CreateTechnologyResponse>
    {
        public String name = null;
        public String slug = null;
        public String vendorName = null;
        public String vendorUrl = null;
        public String productUrl = null;
        public String logoUrl = null;
        public String description = null;
        public Boolean isLocked = null;
        public TechnologyTier tier = null;
        
        public String getName() { return name; }
        public CreateTechnology setName(String value) { this.name = value; return this; }
        public String getSlug() { return slug; }
        public CreateTechnology setSlug(String value) { this.slug = value; return this; }
        public String getVendorName() { return vendorName; }
        public CreateTechnology setVendorName(String value) { this.vendorName = value; return this; }
        public String getVendorUrl() { return vendorUrl; }
        public CreateTechnology setVendorUrl(String value) { this.vendorUrl = value; return this; }
        public String getProductUrl() { return productUrl; }
        public CreateTechnology setProductUrl(String value) { this.productUrl = value; return this; }
        public String getLogoUrl() { return logoUrl; }
        public CreateTechnology setLogoUrl(String value) { this.logoUrl = value; return this; }
        public String getDescription() { return description; }
        public CreateTechnology setDescription(String value) { this.description = value; return this; }
        public Boolean getIsLocked() { return isLocked; }
        public CreateTechnology setIsLocked(Boolean value) { this.isLocked = value; return this; }
        public TechnologyTier getTier() { return tier; }
        public CreateTechnology setTier(TechnologyTier value) { this.tier = value; return this; }
        private static Object responseType = CreateTechnologyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/technology/{Id}", Verbs="PUT")
    public static class UpdateTechnology implements IReturn<UpdateTechnologyResponse>
    {
        public Long id = null;
        public String name = null;
        public String vendorName = null;
        public String vendorUrl = null;
        public String productUrl = null;
        public String logoUrl = null;
        public String description = null;
        public Boolean isLocked = null;
        public TechnologyTier tier = null;
        
        public Long getId() { return id; }
        public UpdateTechnology setId(Long value) { this.id = value; return this; }
        public String getName() { return name; }
        public UpdateTechnology setName(String value) { this.name = value; return this; }
        public String getVendorName() { return vendorName; }
        public UpdateTechnology setVendorName(String value) { this.vendorName = value; return this; }
        public String getVendorUrl() { return vendorUrl; }
        public UpdateTechnology setVendorUrl(String value) { this.vendorUrl = value; return this; }
        public String getProductUrl() { return productUrl; }
        public UpdateTechnology setProductUrl(String value) { this.productUrl = value; return this; }
        public String getLogoUrl() { return logoUrl; }
        public UpdateTechnology setLogoUrl(String value) { this.logoUrl = value; return this; }
        public String getDescription() { return description; }
        public UpdateTechnology setDescription(String value) { this.description = value; return this; }
        public Boolean getIsLocked() { return isLocked; }
        public UpdateTechnology setIsLocked(Boolean value) { this.isLocked = value; return this; }
        public TechnologyTier getTier() { return tier; }
        public UpdateTechnology setTier(TechnologyTier value) { this.tier = value; return this; }
        private static Object responseType = UpdateTechnologyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/technology/{Id}", Verbs="DELETE")
    public static class DeleteTechnology implements IReturn<DeleteTechnologyResponse>
    {
        public Long id = null;
        
        public Long getId() { return id; }
        public DeleteTechnology setId(Long value) { this.id = value; return this; }
        private static Object responseType = DeleteTechnologyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/techstacks/{Slug}/previous-versions", Verbs="GET")
    public static class GetTechnologyStackPreviousVersions implements IReturn<GetTechnologyStackPreviousVersionsResponse>
    {
        public String slug = null;
        
        public String getSlug() { return slug; }
        public GetTechnologyStackPreviousVersions setSlug(String value) { this.slug = value; return this; }
        private static Object responseType = GetTechnologyStackPreviousVersionsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/pagestats/{Type}/{Slug}")
    public static class GetPageStats implements IReturn<GetPageStatsResponse>
    {
        public String type = null;
        public String slug = null;
        public Integer id = null;
        
        public String getType() { return type; }
        public GetPageStats setType(String value) { this.type = value; return this; }
        public String getSlug() { return slug; }
        public GetPageStats setSlug(String value) { this.slug = value; return this; }
        public Integer getId() { return id; }
        public GetPageStats setId(Integer value) { this.id = value; return this; }
        private static Object responseType = GetPageStatsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/cache/clear")
    public static class ClearCache implements IReturn<String>
    {
        
        private static Object responseType = String.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/tasks/hourly")
    public static class HourlyTask implements IReturn<HourlyTaskResponse>
    {
        public Boolean force = null;
        
        public Boolean isForce() { return force; }
        public HourlyTask setForce(Boolean value) { this.force = value; return this; }
        private static Object responseType = HourlyTaskResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/techstacks/search")
    @AutoQueryViewer(DefaultSearchField="Description", DefaultSearchText="ServiceStack", DefaultSearchType="Contains", Description="Explore different Technology Stacks", IconUrl="material-icons:cloud", Title="Find Technology Stacks")
    public static class FindTechStacks extends QueryDb<TechnologyStack> implements IReturn<QueryResponse<TechnologyStack>>
    {
        public String nameContains = null;
        
        public String getNameContains() { return nameContains; }
        public FindTechStacks setNameContains(String value) { this.nameContains = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<TechnologyStack>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route("/techstacks/query")
    public static class QueryTechStacks extends QueryDb<TechnologyStack> implements IReturn<QueryResponse<TechnologyStack>>
    {
        
        private static Object responseType = new TypeToken<QueryResponse<TechnologyStack>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route("/overview")
    public static class Overview implements IReturn<OverviewResponse>
    {
        public Boolean reload = null;
        
        public Boolean isReload() { return reload; }
        public Overview setReload(Boolean value) { this.reload = value; return this; }
        private static Object responseType = OverviewResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/app-overview")
    public static class AppOverview implements IReturn<AppOverviewResponse>
    {
        public Boolean reload = null;
        
        public Boolean isReload() { return reload; }
        public AppOverview setReload(Boolean value) { this.reload = value; return this; }
        private static Object responseType = AppOverviewResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/techstacks", Verbs="GET")
    public static class GetAllTechnologyStacks implements IReturn<GetAllTechnologyStacksResponse>
    {
        
        private static Object responseType = GetAllTechnologyStacksResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/techstacks/{Slug}", Verbs="GET")
    public static class GetTechnologyStack implements IReturn<GetTechnologyStackResponse>, IRegisterStats
    {
        public String slug = null;
        
        public String getSlug() { return slug; }
        public GetTechnologyStack setSlug(String value) { this.slug = value; return this; }
        private static Object responseType = GetTechnologyStackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/techstacks/{Slug}/favorites")
    public static class GetTechnologyStackFavoriteDetails implements IReturn<GetTechnologyStackFavoriteDetailsResponse>
    {
        public String slug = null;
        
        public String getSlug() { return slug; }
        public GetTechnologyStackFavoriteDetails setSlug(String value) { this.slug = value; return this; }
        private static Object responseType = GetTechnologyStackFavoriteDetailsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/config")
    public static class GetConfig implements IReturn<GetConfigResponse>
    {
        
        private static Object responseType = GetConfigResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/techstacks", Verbs="POST")
    public static class CreateTechnologyStack implements IReturn<CreateTechnologyStackResponse>
    {
        public String name = null;
        public String slug = null;
        public String vendorName = null;
        public String appUrl = null;
        public String screenshotUrl = null;
        public String description = null;
        public String details = null;
        public Boolean isLocked = null;
        public ArrayList<Long> technologyIds = null;
        
        public String getName() { return name; }
        public CreateTechnologyStack setName(String value) { this.name = value; return this; }
        public String getSlug() { return slug; }
        public CreateTechnologyStack setSlug(String value) { this.slug = value; return this; }
        public String getVendorName() { return vendorName; }
        public CreateTechnologyStack setVendorName(String value) { this.vendorName = value; return this; }
        public String getAppUrl() { return appUrl; }
        public CreateTechnologyStack setAppUrl(String value) { this.appUrl = value; return this; }
        public String getScreenshotUrl() { return screenshotUrl; }
        public CreateTechnologyStack setScreenshotUrl(String value) { this.screenshotUrl = value; return this; }
        public String getDescription() { return description; }
        public CreateTechnologyStack setDescription(String value) { this.description = value; return this; }
        public String getDetails() { return details; }
        public CreateTechnologyStack setDetails(String value) { this.details = value; return this; }
        public Boolean getIsLocked() { return isLocked; }
        public CreateTechnologyStack setIsLocked(Boolean value) { this.isLocked = value; return this; }
        public ArrayList<Long> getTechnologyIds() { return technologyIds; }
        public CreateTechnologyStack setTechnologyIds(ArrayList<Long> value) { this.technologyIds = value; return this; }
        private static Object responseType = CreateTechnologyStackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/techstacks/{Id}", Verbs="PUT")
    public static class UpdateTechnologyStack implements IReturn<UpdateTechnologyStackResponse>
    {
        public Long id = null;
        public String name = null;
        public String vendorName = null;
        public String appUrl = null;
        public String screenshotUrl = null;
        public String description = null;
        public String details = null;
        public Boolean isLocked = null;
        public ArrayList<Long> technologyIds = null;
        
        public Long getId() { return id; }
        public UpdateTechnologyStack setId(Long value) { this.id = value; return this; }
        public String getName() { return name; }
        public UpdateTechnologyStack setName(String value) { this.name = value; return this; }
        public String getVendorName() { return vendorName; }
        public UpdateTechnologyStack setVendorName(String value) { this.vendorName = value; return this; }
        public String getAppUrl() { return appUrl; }
        public UpdateTechnologyStack setAppUrl(String value) { this.appUrl = value; return this; }
        public String getScreenshotUrl() { return screenshotUrl; }
        public UpdateTechnologyStack setScreenshotUrl(String value) { this.screenshotUrl = value; return this; }
        public String getDescription() { return description; }
        public UpdateTechnologyStack setDescription(String value) { this.description = value; return this; }
        public String getDetails() { return details; }
        public UpdateTechnologyStack setDetails(String value) { this.details = value; return this; }
        public Boolean getIsLocked() { return isLocked; }
        public UpdateTechnologyStack setIsLocked(Boolean value) { this.isLocked = value; return this; }
        public ArrayList<Long> getTechnologyIds() { return technologyIds; }
        public UpdateTechnologyStack setTechnologyIds(ArrayList<Long> value) { this.technologyIds = value; return this; }
        private static Object responseType = UpdateTechnologyStackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/techstacks/{Id}", Verbs="DELETE")
    public static class DeleteTechnologyStack implements IReturn<DeleteTechnologyStackResponse>
    {
        public Long id = null;
        
        public Long getId() { return id; }
        public DeleteTechnologyStack setId(Long value) { this.id = value; return this; }
        private static Object responseType = DeleteTechnologyStackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/favorites/techtacks", Verbs="GET")
    public static class GetFavoriteTechStack implements IReturn<GetFavoriteTechStackResponse>
    {
        public Integer technologyStackId = null;
        
        public Integer getTechnologyStackId() { return technologyStackId; }
        public GetFavoriteTechStack setTechnologyStackId(Integer value) { this.technologyStackId = value; return this; }
        private static Object responseType = GetFavoriteTechStackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/favorites/techtacks/{TechnologyStackId}", Verbs="PUT")
    public static class AddFavoriteTechStack implements IReturn<FavoriteTechStackResponse>
    {
        public Integer technologyStackId = null;
        
        public Integer getTechnologyStackId() { return technologyStackId; }
        public AddFavoriteTechStack setTechnologyStackId(Integer value) { this.technologyStackId = value; return this; }
        private static Object responseType = FavoriteTechStackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/favorites/techtacks/{TechnologyStackId}", Verbs="DELETE")
    public static class RemoveFavoriteTechStack implements IReturn<FavoriteTechStackResponse>
    {
        public Integer technologyStackId = null;
        
        public Integer getTechnologyStackId() { return technologyStackId; }
        public RemoveFavoriteTechStack setTechnologyStackId(Integer value) { this.technologyStackId = value; return this; }
        private static Object responseType = FavoriteTechStackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/favorites/technology", Verbs="GET")
    public static class GetFavoriteTechnologies implements IReturn<GetFavoriteTechnologiesResponse>
    {
        public Integer technologyId = null;
        
        public Integer getTechnologyId() { return technologyId; }
        public GetFavoriteTechnologies setTechnologyId(Integer value) { this.technologyId = value; return this; }
        private static Object responseType = GetFavoriteTechnologiesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/favorites/technology/{TechnologyId}", Verbs="PUT")
    public static class AddFavoriteTechnology implements IReturn<FavoriteTechnologyResponse>
    {
        public Integer technologyId = null;
        
        public Integer getTechnologyId() { return technologyId; }
        public AddFavoriteTechnology setTechnologyId(Integer value) { this.technologyId = value; return this; }
        private static Object responseType = FavoriteTechnologyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/favorites/technology/{TechnologyId}", Verbs="DELETE")
    public static class RemoveFavoriteTechnology implements IReturn<FavoriteTechnologyResponse>
    {
        public Integer technologyId = null;
        
        public Integer getTechnologyId() { return technologyId; }
        public RemoveFavoriteTechnology setTechnologyId(Integer value) { this.technologyId = value; return this; }
        private static Object responseType = FavoriteTechnologyResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/my-feed")
    public static class GetUserFeed implements IReturn<GetUserFeedResponse>
    {
        
        private static Object responseType = GetUserFeedResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/users/karma", Verbs="GET")
    public static class GetUsersKarma implements IReturn<GetUsersKarmaResponse>
    {
        public ArrayList<Integer> userIds = null;
        
        public ArrayList<Integer> getUserIds() { return userIds; }
        public GetUsersKarma setUserIds(ArrayList<Integer> value) { this.userIds = value; return this; }
        private static Object responseType = GetUsersKarmaResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/userinfo/{UserName}")
    public static class GetUserInfo implements IReturn<GetUserInfoResponse>
    {
        public String userName = null;
        
        public String getUserName() { return userName; }
        public GetUserInfo setUserName(String value) { this.userName = value; return this; }
        private static Object responseType = GetUserInfoResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/users/{UserName}/avatar", Verbs="GET")
    public static class UserAvatar
    {
        public String userName = null;
        
        public String getUserName() { return userName; }
        public UserAvatar setUserName(String value) { this.userName = value; return this; }
    }

    @Route("/mq/start")
    public static class MqStart implements IReturn<String>
    {
        
        private static Object responseType = String.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/mq/stop")
    public static class MqStop implements IReturn<String>
    {
        
        private static Object responseType = String.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/mq/stats")
    public static class MqStats implements IReturn<String>
    {
        
        private static Object responseType = String.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/mq/status")
    public static class MqStatus implements IReturn<String>
    {
        
        private static Object responseType = String.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/sync/discourse/{Site}")
    public static class SyncDiscourseSite implements IReturn<SyncDiscourseSiteResponse>
    {
        public String site = null;
        
        public String getSite() { return site; }
        public SyncDiscourseSite setSite(String value) { this.site = value; return this; }
        private static Object responseType = SyncDiscourseSiteResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/admin/technology/{TechnologyId}/logo")
    public static class LogoUrlApproval implements IReturn<LogoUrlApprovalResponse>
    {
        public Long technologyId = null;
        public Boolean approved = null;
        
        public Long getTechnologyId() { return technologyId; }
        public LogoUrlApproval setTechnologyId(Long value) { this.technologyId = value; return this; }
        public Boolean isApproved() { return approved; }
        public LogoUrlApproval setApproved(Boolean value) { this.approved = value; return this; }
        private static Object responseType = LogoUrlApprovalResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/admin/techstacks/{TechnologyStackId}/lock")
    public static class LockTechStack implements IReturn<LockStackResponse>
    {
        public Long technologyStackId = null;
        public Boolean isLocked = null;
        
        public Long getTechnologyStackId() { return technologyStackId; }
        public LockTechStack setTechnologyStackId(Long value) { this.technologyStackId = value; return this; }
        public Boolean getIsLocked() { return isLocked; }
        public LockTechStack setIsLocked(Boolean value) { this.isLocked = value; return this; }
        private static Object responseType = LockStackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/admin/technology/{TechnologyId}/lock")
    public static class LockTech implements IReturn<LockStackResponse>
    {
        public Long technologyId = null;
        public Boolean isLocked = null;
        
        public Long getTechnologyId() { return technologyId; }
        public LockTech setTechnologyId(Long value) { this.technologyId = value; return this; }
        public Boolean getIsLocked() { return isLocked; }
        public LockTech setIsLocked(Boolean value) { this.isLocked = value; return this; }
        private static Object responseType = LockStackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/email/post/{PostId}")
    public static class EmailTest implements IReturn<EmailTestRespoonse>
    {
        public Integer postId = null;
        
        public Integer getPostId() { return postId; }
        public EmailTest setPostId(Integer value) { this.postId = value; return this; }
        private static Object responseType = EmailTestRespoonse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class ImportUser implements IReturn<ImportUserResponse>
    {
        public String userName = null;
        public String email = null;
        public String firstName = null;
        public String lastName = null;
        public String displayName = null;
        public String company = null;
        public String refSource = null;
        public Integer refId = null;
        public String refIdStr = null;
        public String refUrn = null;
        public String defaultProfileUrl = null;
        public HashMap<String,String> meta = null;
        
        public String getUserName() { return userName; }
        public ImportUser setUserName(String value) { this.userName = value; return this; }
        public String getEmail() { return email; }
        public ImportUser setEmail(String value) { this.email = value; return this; }
        public String getFirstName() { return firstName; }
        public ImportUser setFirstName(String value) { this.firstName = value; return this; }
        public String getLastName() { return lastName; }
        public ImportUser setLastName(String value) { this.lastName = value; return this; }
        public String getDisplayName() { return displayName; }
        public ImportUser setDisplayName(String value) { this.displayName = value; return this; }
        public String getCompany() { return company; }
        public ImportUser setCompany(String value) { this.company = value; return this; }
        public String getRefSource() { return refSource; }
        public ImportUser setRefSource(String value) { this.refSource = value; return this; }
        public Integer getRefId() { return refId; }
        public ImportUser setRefId(Integer value) { this.refId = value; return this; }
        public String getRefIdStr() { return refIdStr; }
        public ImportUser setRefIdStr(String value) { this.refIdStr = value; return this; }
        public String getRefUrn() { return refUrn; }
        public ImportUser setRefUrn(String value) { this.refUrn = value; return this; }
        public String getDefaultProfileUrl() { return defaultProfileUrl; }
        public ImportUser setDefaultProfileUrl(String value) { this.defaultProfileUrl = value; return this; }
        public HashMap<String,String> getMeta() { return meta; }
        public ImportUser setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        private static Object responseType = ImportUserResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/import/uservoice/suggestion")
    public static class ImportUserVoiceSuggestion implements IReturn<ImportUserVoiceSuggestionResponse>
    {
        public Integer organizationId = null;
        public String url = null;
        public Integer id = null;
        public Integer topicId = null;
        public String state = null;
        public String title = null;
        public String slug = null;
        public String category = null;
        public String text = null;
        public String formattedText = null;
        public Integer voteCount = null;
        public Date closedAt = null;
        public String statusKey = null;
        public String statusHexColor = null;
        public UserVoiceUser statusChangedBy = null;
        public UserVoiceUser creator = null;
        public UserVoiceComment response = null;
        public Date createdAt = null;
        public Date updatedAt = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public ImportUserVoiceSuggestion setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getUrl() { return url; }
        public ImportUserVoiceSuggestion setUrl(String value) { this.url = value; return this; }
        public Integer getId() { return id; }
        public ImportUserVoiceSuggestion setId(Integer value) { this.id = value; return this; }
        public Integer getTopicId() { return topicId; }
        public ImportUserVoiceSuggestion setTopicId(Integer value) { this.topicId = value; return this; }
        public String getState() { return state; }
        public ImportUserVoiceSuggestion setState(String value) { this.state = value; return this; }
        public String getTitle() { return title; }
        public ImportUserVoiceSuggestion setTitle(String value) { this.title = value; return this; }
        public String getSlug() { return slug; }
        public ImportUserVoiceSuggestion setSlug(String value) { this.slug = value; return this; }
        public String getCategory() { return category; }
        public ImportUserVoiceSuggestion setCategory(String value) { this.category = value; return this; }
        public String getText() { return text; }
        public ImportUserVoiceSuggestion setText(String value) { this.text = value; return this; }
        public String getFormattedText() { return formattedText; }
        public ImportUserVoiceSuggestion setFormattedText(String value) { this.formattedText = value; return this; }
        public Integer getVoteCount() { return voteCount; }
        public ImportUserVoiceSuggestion setVoteCount(Integer value) { this.voteCount = value; return this; }
        public Date getClosedAt() { return closedAt; }
        public ImportUserVoiceSuggestion setClosedAt(Date value) { this.closedAt = value; return this; }
        public String getStatusKey() { return statusKey; }
        public ImportUserVoiceSuggestion setStatusKey(String value) { this.statusKey = value; return this; }
        public String getStatusHexColor() { return statusHexColor; }
        public ImportUserVoiceSuggestion setStatusHexColor(String value) { this.statusHexColor = value; return this; }
        public UserVoiceUser getStatusChangedBy() { return statusChangedBy; }
        public ImportUserVoiceSuggestion setStatusChangedBy(UserVoiceUser value) { this.statusChangedBy = value; return this; }
        public UserVoiceUser getCreator() { return creator; }
        public ImportUserVoiceSuggestion setCreator(UserVoiceUser value) { this.creator = value; return this; }
        public UserVoiceComment getResponse() { return response; }
        public ImportUserVoiceSuggestion setResponse(UserVoiceComment value) { this.response = value; return this; }
        public Date getCreatedAt() { return createdAt; }
        public ImportUserVoiceSuggestion setCreatedAt(Date value) { this.createdAt = value; return this; }
        public Date getUpdatedAt() { return updatedAt; }
        public ImportUserVoiceSuggestion setUpdatedAt(Date value) { this.updatedAt = value; return this; }
        private static Object responseType = ImportUserVoiceSuggestionResponse.class;
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

    @Route("/session-to-token")
    @DataContract
    public static class ConvertSessionToToken implements IReturn<ConvertSessionToTokenResponse>, IPost
    {
        @DataMember(Order=1)
        public Boolean preserveSession = null;
        
        public Boolean isPreserveSession() { return preserveSession; }
        public ConvertSessionToToken setPreserveSession(Boolean value) { this.preserveSession = value; return this; }
        private static Object responseType = ConvertSessionToTokenResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/access-token")
    @DataContract
    public static class GetAccessToken implements IReturn<GetAccessTokenResponse>, IPost
    {
        @DataMember(Order=1)
        public String refreshToken = null;
        
        public String getRefreshToken() { return refreshToken; }
        public GetAccessToken setRefreshToken(String value) { this.refreshToken = value; return this; }
        private static Object responseType = GetAccessTokenResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/posts/comment", Verbs="GET")
    public static class QueryPostComments extends QueryDb<PostComment> implements IReturn<QueryResponse<PostComment>>
    {
        public Integer userId = null;
        public Integer postId = null;
        
        public Integer getUserId() { return userId; }
        public QueryPostComments setUserId(Integer value) { this.userId = value; return this; }
        public Integer getPostId() { return postId; }
        public QueryPostComments setPostId(Integer value) { this.postId = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<PostComment>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route("/admin/technology/search")
    @AutoQueryViewer(DefaultSearchField="Tier", DefaultSearchText="Data", DefaultSearchType="=", Description="Explore different Technologies", IconUrl="octicon:database", Title="Find Technologies Admin")
    public static class FindTechnologiesAdmin extends QueryDb<Technology> implements IReturn<QueryResponse<Technology>>
    {
        public String name = null;
        
        public String getName() { return name; }
        public FindTechnologiesAdmin setName(String value) { this.name = value; return this; }
        private static Object responseType = new TypeToken<QueryResponse<Technology>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    public static class GetOrganizationResponse
    {
        public Long cache = null;
        public Integer id = null;
        public String slug = null;
        public Organization organization = null;
        public ArrayList<OrganizationLabel> labels = null;
        public ArrayList<Category> categories = null;
        public ArrayList<OrganizationMember> owners = null;
        public ArrayList<OrganizationMember> moderators = null;
        public Long membersCount = null;
        public ResponseStatus responseStatus = null;
        
        public Long getCache() { return cache; }
        public GetOrganizationResponse setCache(Long value) { this.cache = value; return this; }
        public Integer getId() { return id; }
        public GetOrganizationResponse setId(Integer value) { this.id = value; return this; }
        public String getSlug() { return slug; }
        public GetOrganizationResponse setSlug(String value) { this.slug = value; return this; }
        public Organization getOrganization() { return organization; }
        public GetOrganizationResponse setOrganization(Organization value) { this.organization = value; return this; }
        public ArrayList<OrganizationLabel> getLabels() { return labels; }
        public GetOrganizationResponse setLabels(ArrayList<OrganizationLabel> value) { this.labels = value; return this; }
        public ArrayList<Category> getCategories() { return categories; }
        public GetOrganizationResponse setCategories(ArrayList<Category> value) { this.categories = value; return this; }
        public ArrayList<OrganizationMember> getOwners() { return owners; }
        public GetOrganizationResponse setOwners(ArrayList<OrganizationMember> value) { this.owners = value; return this; }
        public ArrayList<OrganizationMember> getModerators() { return moderators; }
        public GetOrganizationResponse setModerators(ArrayList<OrganizationMember> value) { this.moderators = value; return this; }
        public Long getMembersCount() { return membersCount; }
        public GetOrganizationResponse setMembersCount(Long value) { this.membersCount = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetOrganizationResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetOrganizationMembersResponse
    {
        public Integer organizationId = null;
        public ArrayList<OrganizationMember> results = null;
        public ResponseStatus responseStatus = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public GetOrganizationMembersResponse setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public ArrayList<OrganizationMember> getResults() { return results; }
        public GetOrganizationMembersResponse setResults(ArrayList<OrganizationMember> value) { this.results = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetOrganizationMembersResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetOrganizationAdminResponse
    {
        public ArrayList<OrganizationLabel> labels = null;
        public ArrayList<OrganizationMember> members = null;
        public ArrayList<OrganizationMemberInvite> memberInvites = null;
        public ArrayList<PostReportInfo> reportedPosts = null;
        public ArrayList<PostCommentReportInfo> reportedPostComments = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<OrganizationLabel> getLabels() { return labels; }
        public GetOrganizationAdminResponse setLabels(ArrayList<OrganizationLabel> value) { this.labels = value; return this; }
        public ArrayList<OrganizationMember> getMembers() { return members; }
        public GetOrganizationAdminResponse setMembers(ArrayList<OrganizationMember> value) { this.members = value; return this; }
        public ArrayList<OrganizationMemberInvite> getMemberInvites() { return memberInvites; }
        public GetOrganizationAdminResponse setMemberInvites(ArrayList<OrganizationMemberInvite> value) { this.memberInvites = value; return this; }
        public ArrayList<PostReportInfo> getReportedPosts() { return reportedPosts; }
        public GetOrganizationAdminResponse setReportedPosts(ArrayList<PostReportInfo> value) { this.reportedPosts = value; return this; }
        public ArrayList<PostCommentReportInfo> getReportedPostComments() { return reportedPostComments; }
        public GetOrganizationAdminResponse setReportedPostComments(ArrayList<PostCommentReportInfo> value) { this.reportedPostComments = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetOrganizationAdminResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CreateOrganizationForTechnologyResponse
    {
        public Integer organizationId = null;
        public String organizationSlug = null;
        public Long commentsPostId = null;
        public String commentsPostSlug = null;
        public ResponseStatus responseStatus = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public CreateOrganizationForTechnologyResponse setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getOrganizationSlug() { return organizationSlug; }
        public CreateOrganizationForTechnologyResponse setOrganizationSlug(String value) { this.organizationSlug = value; return this; }
        public Long getCommentsPostId() { return commentsPostId; }
        public CreateOrganizationForTechnologyResponse setCommentsPostId(Long value) { this.commentsPostId = value; return this; }
        public String getCommentsPostSlug() { return commentsPostSlug; }
        public CreateOrganizationForTechnologyResponse setCommentsPostSlug(String value) { this.commentsPostSlug = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public CreateOrganizationForTechnologyResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CreateOrganizationResponse
    {
        public Integer id = null;
        public String slug = null;
        public ResponseStatus responseStatus = null;
        
        public Integer getId() { return id; }
        public CreateOrganizationResponse setId(Integer value) { this.id = value; return this; }
        public String getSlug() { return slug; }
        public CreateOrganizationResponse setSlug(String value) { this.slug = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public CreateOrganizationResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UpdateOrganizationResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UpdateOrganizationResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class OrganizationLabelResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public OrganizationLabelResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class AddCategoryResponse
    {
        public Integer id = null;
        public String slug = null;
        public ResponseStatus responseStatus = null;
        
        public Integer getId() { return id; }
        public AddCategoryResponse setId(Integer value) { this.id = value; return this; }
        public String getSlug() { return slug; }
        public AddCategoryResponse setSlug(String value) { this.slug = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public AddCategoryResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UpdateCategoryResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UpdateCategoryResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class AddOrganizationMemberResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public AddOrganizationMemberResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UpdateOrganizationMemberResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UpdateOrganizationMemberResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class SetOrganizationMembersResponse
    {
        public ArrayList<Integer> userIdsAdded = null;
        public ArrayList<Integer> userIdsRemoved = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<Integer> getUserIdsAdded() { return userIdsAdded; }
        public SetOrganizationMembersResponse setUserIdsAdded(ArrayList<Integer> value) { this.userIdsAdded = value; return this; }
        public ArrayList<Integer> getUserIdsRemoved() { return userIdsRemoved; }
        public SetOrganizationMembersResponse setUserIdsRemoved(ArrayList<Integer> value) { this.userIdsRemoved = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public SetOrganizationMembersResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetOrganizationMemberInvitesResponse
    {
        public ArrayList<OrganizationMemberInvite> results = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<OrganizationMemberInvite> getResults() { return results; }
        public GetOrganizationMemberInvitesResponse setResults(ArrayList<OrganizationMemberInvite> value) { this.results = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetOrganizationMemberInvitesResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class RequestOrganizationMemberInviteResponse
    {
        public Integer organizationId = null;
        public ResponseStatus responseStatus = null;
        
        public Integer getOrganizationId() { return organizationId; }
        public RequestOrganizationMemberInviteResponse setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public RequestOrganizationMemberInviteResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UpdateOrganizationMemberInviteResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UpdateOrganizationMemberInviteResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
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

    public static class GetPostResponse
    {
        public Long cache = null;
        public Post post = null;
        public ArrayList<PostComment> comments = null;
        public ResponseStatus responseStatus = null;
        
        public Long getCache() { return cache; }
        public GetPostResponse setCache(Long value) { this.cache = value; return this; }
        public Post getPost() { return post; }
        public GetPostResponse setPost(Post value) { this.post = value; return this; }
        public ArrayList<PostComment> getComments() { return comments; }
        public GetPostResponse setComments(ArrayList<PostComment> value) { this.comments = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetPostResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CreatePostResponse
    {
        public Long id = null;
        public String slug = null;
        public ResponseStatus responseStatus = null;
        
        public Long getId() { return id; }
        public CreatePostResponse setId(Long value) { this.id = value; return this; }
        public String getSlug() { return slug; }
        public CreatePostResponse setSlug(String value) { this.slug = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public CreatePostResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UpdatePostResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UpdatePostResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class DeletePostResponse
    {
        public Long id = null;
        public ResponseStatus responseStatus = null;
        
        public Long getId() { return id; }
        public DeletePostResponse setId(Long value) { this.id = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public DeletePostResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class CreatePostCommentResponse
    {
        public Long id = null;
        public Long postId = null;
        public ResponseStatus responseStatus = null;
        
        public Long getId() { return id; }
        public CreatePostCommentResponse setId(Long value) { this.id = value; return this; }
        public Long getPostId() { return postId; }
        public CreatePostCommentResponse setPostId(Long value) { this.postId = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public CreatePostCommentResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UpdatePostCommentResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UpdatePostCommentResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class DeletePostCommentResponse
    {
        public Long id = null;
        public Long postId = null;
        public ResponseStatus responseStatus = null;
        
        public Long getId() { return id; }
        public DeletePostCommentResponse setId(Long value) { this.id = value; return this; }
        public Long getPostId() { return postId; }
        public DeletePostCommentResponse setPostId(Long value) { this.postId = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public DeletePostCommentResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetUserPostCommentVotesResponse
    {
        public Long postId = null;
        public ArrayList<Long> upVotedCommentIds = null;
        public ArrayList<Long> downVotedCommentIds = null;
        
        public Long getPostId() { return postId; }
        public GetUserPostCommentVotesResponse setPostId(Long value) { this.postId = value; return this; }
        public ArrayList<Long> getUpVotedCommentIds() { return upVotedCommentIds; }
        public GetUserPostCommentVotesResponse setUpVotedCommentIds(ArrayList<Long> value) { this.upVotedCommentIds = value; return this; }
        public ArrayList<Long> getDownVotedCommentIds() { return downVotedCommentIds; }
        public GetUserPostCommentVotesResponse setDownVotedCommentIds(ArrayList<Long> value) { this.downVotedCommentIds = value; return this; }
    }

    public static class PinPostCommentResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public PinPostCommentResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetUsersByEmailsResponse
    {
        public ArrayList<UserRef> results = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<UserRef> getResults() { return results; }
        public GetUsersByEmailsResponse setResults(ArrayList<UserRef> value) { this.results = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetUsersByEmailsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetUserPostActivityResponse
    {
        public ArrayList<Long> upVotedPostIds = null;
        public ArrayList<Long> downVotedPostIds = null;
        public ArrayList<Long> favoritePostIds = null;
        public ResponseStatus responseStatus = null;
        
        public ArrayList<Long> getUpVotedPostIds() { return upVotedPostIds; }
        public GetUserPostActivityResponse setUpVotedPostIds(ArrayList<Long> value) { this.upVotedPostIds = value; return this; }
        public ArrayList<Long> getDownVotedPostIds() { return downVotedPostIds; }
        public GetUserPostActivityResponse setDownVotedPostIds(ArrayList<Long> value) { this.downVotedPostIds = value; return this; }
        public ArrayList<Long> getFavoritePostIds() { return favoritePostIds; }
        public GetUserPostActivityResponse setFavoritePostIds(ArrayList<Long> value) { this.favoritePostIds = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetUserPostActivityResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetUserOrganizationsResponse
    {
        public ArrayList<OrganizationMember> members = null;
        public ArrayList<OrganizationMemberInvite> memberInvites = null;
        public ArrayList<OrganizationSubscription> subscriptions = null;
        
        public ArrayList<OrganizationMember> getMembers() { return members; }
        public GetUserOrganizationsResponse setMembers(ArrayList<OrganizationMember> value) { this.members = value; return this; }
        public ArrayList<OrganizationMemberInvite> getMemberInvites() { return memberInvites; }
        public GetUserOrganizationsResponse setMemberInvites(ArrayList<OrganizationMemberInvite> value) { this.memberInvites = value; return this; }
        public ArrayList<OrganizationSubscription> getSubscriptions() { return subscriptions; }
        public GetUserOrganizationsResponse setSubscriptions(ArrayList<OrganizationSubscription> value) { this.subscriptions = value; return this; }
    }

    public static class UserPostVoteResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UserPostVoteResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UserPostFavoriteResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UserPostFavoriteResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UserPostReportResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UserPostReportResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UserPostCommentVoteResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UserPostCommentVoteResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UserPostCommentReportResponse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UserPostCommentReportResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class SessionInfoResponse
    {
        public Date created = null;
        public String id = null;
        public String referrerUrl = null;
        public String userAuthId = null;
        public String userAuthName = null;
        public String userName = null;
        public String displayName = null;
        public String firstName = null;
        public String lastName = null;
        public String email = null;
        public Date createdAt = null;
        public Date lastModified = null;
        public ArrayList<String> roles = null;
        public ArrayList<String> permissions = null;
        public Boolean isAuthenticated = null;
        public String authProvider = null;
        public String profileUrl = null;
        public String githubProfileUrl = null;
        public String twitterProfileUrl = null;
        public String accessToken = null;
        public String avatarUrl = null;
        public ArrayList<TechnologyStack> techStacks = null;
        public ArrayList<TechnologyStack> favoriteTechStacks = null;
        public ArrayList<Technology> favoriteTechnologies = null;
        public UserActivity userActivity = null;
        public ArrayList<OrganizationMember> members = null;
        public ArrayList<OrganizationMemberInvite> memberInvites = null;
        public ArrayList<OrganizationSubscription> subscriptions = null;
        public ResponseStatus responseStatus = null;
        
        public Date getCreated() { return created; }
        public SessionInfoResponse setCreated(Date value) { this.created = value; return this; }
        public String getId() { return id; }
        public SessionInfoResponse setId(String value) { this.id = value; return this; }
        public String getReferrerUrl() { return referrerUrl; }
        public SessionInfoResponse setReferrerUrl(String value) { this.referrerUrl = value; return this; }
        public String getUserAuthId() { return userAuthId; }
        public SessionInfoResponse setUserAuthId(String value) { this.userAuthId = value; return this; }
        public String getUserAuthName() { return userAuthName; }
        public SessionInfoResponse setUserAuthName(String value) { this.userAuthName = value; return this; }
        public String getUserName() { return userName; }
        public SessionInfoResponse setUserName(String value) { this.userName = value; return this; }
        public String getDisplayName() { return displayName; }
        public SessionInfoResponse setDisplayName(String value) { this.displayName = value; return this; }
        public String getFirstName() { return firstName; }
        public SessionInfoResponse setFirstName(String value) { this.firstName = value; return this; }
        public String getLastName() { return lastName; }
        public SessionInfoResponse setLastName(String value) { this.lastName = value; return this; }
        public String getEmail() { return email; }
        public SessionInfoResponse setEmail(String value) { this.email = value; return this; }
        public Date getCreatedAt() { return createdAt; }
        public SessionInfoResponse setCreatedAt(Date value) { this.createdAt = value; return this; }
        public Date getLastModified() { return lastModified; }
        public SessionInfoResponse setLastModified(Date value) { this.lastModified = value; return this; }
        public ArrayList<String> getRoles() { return roles; }
        public SessionInfoResponse setRoles(ArrayList<String> value) { this.roles = value; return this; }
        public ArrayList<String> getPermissions() { return permissions; }
        public SessionInfoResponse setPermissions(ArrayList<String> value) { this.permissions = value; return this; }
        public Boolean getIsAuthenticated() { return isAuthenticated; }
        public SessionInfoResponse setIsAuthenticated(Boolean value) { this.isAuthenticated = value; return this; }
        public String getAuthProvider() { return authProvider; }
        public SessionInfoResponse setAuthProvider(String value) { this.authProvider = value; return this; }
        public String getProfileUrl() { return profileUrl; }
        public SessionInfoResponse setProfileUrl(String value) { this.profileUrl = value; return this; }
        public String getGithubProfileUrl() { return githubProfileUrl; }
        public SessionInfoResponse setGithubProfileUrl(String value) { this.githubProfileUrl = value; return this; }
        public String getTwitterProfileUrl() { return twitterProfileUrl; }
        public SessionInfoResponse setTwitterProfileUrl(String value) { this.twitterProfileUrl = value; return this; }
        public String getAccessToken() { return accessToken; }
        public SessionInfoResponse setAccessToken(String value) { this.accessToken = value; return this; }
        public String getAvatarUrl() { return avatarUrl; }
        public SessionInfoResponse setAvatarUrl(String value) { this.avatarUrl = value; return this; }
        public ArrayList<TechnologyStack> getTechStacks() { return techStacks; }
        public SessionInfoResponse setTechStacks(ArrayList<TechnologyStack> value) { this.techStacks = value; return this; }
        public ArrayList<TechnologyStack> getFavoriteTechStacks() { return favoriteTechStacks; }
        public SessionInfoResponse setFavoriteTechStacks(ArrayList<TechnologyStack> value) { this.favoriteTechStacks = value; return this; }
        public ArrayList<Technology> getFavoriteTechnologies() { return favoriteTechnologies; }
        public SessionInfoResponse setFavoriteTechnologies(ArrayList<Technology> value) { this.favoriteTechnologies = value; return this; }
        public UserActivity getUserActivity() { return userActivity; }
        public SessionInfoResponse setUserActivity(UserActivity value) { this.userActivity = value; return this; }
        public ArrayList<OrganizationMember> getMembers() { return members; }
        public SessionInfoResponse setMembers(ArrayList<OrganizationMember> value) { this.members = value; return this; }
        public ArrayList<OrganizationMemberInvite> getMemberInvites() { return memberInvites; }
        public SessionInfoResponse setMemberInvites(ArrayList<OrganizationMemberInvite> value) { this.memberInvites = value; return this; }
        public ArrayList<OrganizationSubscription> getSubscriptions() { return subscriptions; }
        public SessionInfoResponse setSubscriptions(ArrayList<OrganizationSubscription> value) { this.subscriptions = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public SessionInfoResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetTechnologyPreviousVersionsResponse
    {
        public ArrayList<TechnologyHistory> results = null;
        
        public ArrayList<TechnologyHistory> getResults() { return results; }
        public GetTechnologyPreviousVersionsResponse setResults(ArrayList<TechnologyHistory> value) { this.results = value; return this; }
    }

    public static class GetAllTechnologiesResponse
    {
        public ArrayList<Technology> results = null;
        public Long total = null;
        
        public ArrayList<Technology> getResults() { return results; }
        public GetAllTechnologiesResponse setResults(ArrayList<Technology> value) { this.results = value; return this; }
        public Long getTotal() { return total; }
        public GetAllTechnologiesResponse setTotal(Long value) { this.total = value; return this; }
    }

    public static class GetTechnologyResponse
    {
        public Date created = null;
        public Technology technology = null;
        public ArrayList<TechnologyStack> technologyStacks = null;
        public ResponseStatus responseStatus = null;
        
        public Date getCreated() { return created; }
        public GetTechnologyResponse setCreated(Date value) { this.created = value; return this; }
        public Technology getTechnology() { return technology; }
        public GetTechnologyResponse setTechnology(Technology value) { this.technology = value; return this; }
        public ArrayList<TechnologyStack> getTechnologyStacks() { return technologyStacks; }
        public GetTechnologyResponse setTechnologyStacks(ArrayList<TechnologyStack> value) { this.technologyStacks = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetTechnologyResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetTechnologyFavoriteDetailsResponse
    {
        public ArrayList<String> users = null;
        public Integer favoriteCount = null;
        
        public ArrayList<String> getUsers() { return users; }
        public GetTechnologyFavoriteDetailsResponse setUsers(ArrayList<String> value) { this.users = value; return this; }
        public Integer getFavoriteCount() { return favoriteCount; }
        public GetTechnologyFavoriteDetailsResponse setFavoriteCount(Integer value) { this.favoriteCount = value; return this; }
    }

    public static class CreateTechnologyResponse
    {
        public Technology result = null;
        public ResponseStatus responseStatus = null;
        
        public Technology getResult() { return result; }
        public CreateTechnologyResponse setResult(Technology value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public CreateTechnologyResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UpdateTechnologyResponse
    {
        public Technology result = null;
        public ResponseStatus responseStatus = null;
        
        public Technology getResult() { return result; }
        public UpdateTechnologyResponse setResult(Technology value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UpdateTechnologyResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class DeleteTechnologyResponse
    {
        public Technology result = null;
        public ResponseStatus responseStatus = null;
        
        public Technology getResult() { return result; }
        public DeleteTechnologyResponse setResult(Technology value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public DeleteTechnologyResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetTechnologyStackPreviousVersionsResponse
    {
        public ArrayList<TechnologyStackHistory> results = null;
        
        public ArrayList<TechnologyStackHistory> getResults() { return results; }
        public GetTechnologyStackPreviousVersionsResponse setResults(ArrayList<TechnologyStackHistory> value) { this.results = value; return this; }
    }

    public static class GetPageStatsResponse
    {
        public String type = null;
        public String slug = null;
        public Long viewCount = null;
        public Long favCount = null;
        
        public String getType() { return type; }
        public GetPageStatsResponse setType(String value) { this.type = value; return this; }
        public String getSlug() { return slug; }
        public GetPageStatsResponse setSlug(String value) { this.slug = value; return this; }
        public Long getViewCount() { return viewCount; }
        public GetPageStatsResponse setViewCount(Long value) { this.viewCount = value; return this; }
        public Long getFavCount() { return favCount; }
        public GetPageStatsResponse setFavCount(Long value) { this.favCount = value; return this; }
    }

    public static class HourlyTaskResponse
    {
        public HashMap<String,String> meta = null;
        public ResponseStatus responseStatus = null;
        
        public HashMap<String,String> getMeta() { return meta; }
        public HourlyTaskResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public HourlyTaskResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class OverviewResponse
    {
        public Date created = null;
        public ArrayList<UserInfo> topUsers = null;
        public ArrayList<TechnologyInfo> topTechnologies = null;
        public ArrayList<TechStackDetails> latestTechStacks = null;
        public ArrayList<TechnologyStack> popularTechStacks = null;
        public ArrayList<OrganizationInfo> allOrganizations = null;
        public HashMap<String,ArrayList<TechnologyInfo>> topTechnologiesByTier = null;
        public ResponseStatus responseStatus = null;
        
        public Date getCreated() { return created; }
        public OverviewResponse setCreated(Date value) { this.created = value; return this; }
        public ArrayList<UserInfo> getTopUsers() { return topUsers; }
        public OverviewResponse setTopUsers(ArrayList<UserInfo> value) { this.topUsers = value; return this; }
        public ArrayList<TechnologyInfo> getTopTechnologies() { return topTechnologies; }
        public OverviewResponse setTopTechnologies(ArrayList<TechnologyInfo> value) { this.topTechnologies = value; return this; }
        public ArrayList<TechStackDetails> getLatestTechStacks() { return latestTechStacks; }
        public OverviewResponse setLatestTechStacks(ArrayList<TechStackDetails> value) { this.latestTechStacks = value; return this; }
        public ArrayList<TechnologyStack> getPopularTechStacks() { return popularTechStacks; }
        public OverviewResponse setPopularTechStacks(ArrayList<TechnologyStack> value) { this.popularTechStacks = value; return this; }
        public ArrayList<OrganizationInfo> getAllOrganizations() { return allOrganizations; }
        public OverviewResponse setAllOrganizations(ArrayList<OrganizationInfo> value) { this.allOrganizations = value; return this; }
        public HashMap<String,ArrayList<TechnologyInfo>> getTopTechnologiesByTier() { return topTechnologiesByTier; }
        public OverviewResponse setTopTechnologiesByTier(HashMap<String,ArrayList<TechnologyInfo>> value) { this.topTechnologiesByTier = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public OverviewResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class AppOverviewResponse
    {
        public Date created = null;
        public ArrayList<Option> allTiers = null;
        public ArrayList<TechnologyInfo> topTechnologies = null;
        public ResponseStatus responseStatus = null;
        
        public Date getCreated() { return created; }
        public AppOverviewResponse setCreated(Date value) { this.created = value; return this; }
        public ArrayList<Option> getAllTiers() { return allTiers; }
        public AppOverviewResponse setAllTiers(ArrayList<Option> value) { this.allTiers = value; return this; }
        public ArrayList<TechnologyInfo> getTopTechnologies() { return topTechnologies; }
        public AppOverviewResponse setTopTechnologies(ArrayList<TechnologyInfo> value) { this.topTechnologies = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public AppOverviewResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetAllTechnologyStacksResponse
    {
        public ArrayList<TechnologyStack> results = null;
        public Long total = null;
        
        public ArrayList<TechnologyStack> getResults() { return results; }
        public GetAllTechnologyStacksResponse setResults(ArrayList<TechnologyStack> value) { this.results = value; return this; }
        public Long getTotal() { return total; }
        public GetAllTechnologyStacksResponse setTotal(Long value) { this.total = value; return this; }
    }

    public static class GetTechnologyStackResponse
    {
        public Date created = null;
        public TechStackDetails result = null;
        public ResponseStatus responseStatus = null;
        
        public Date getCreated() { return created; }
        public GetTechnologyStackResponse setCreated(Date value) { this.created = value; return this; }
        public TechStackDetails getResult() { return result; }
        public GetTechnologyStackResponse setResult(TechStackDetails value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetTechnologyStackResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetTechnologyStackFavoriteDetailsResponse
    {
        public ArrayList<String> users = null;
        public Integer favoriteCount = null;
        
        public ArrayList<String> getUsers() { return users; }
        public GetTechnologyStackFavoriteDetailsResponse setUsers(ArrayList<String> value) { this.users = value; return this; }
        public Integer getFavoriteCount() { return favoriteCount; }
        public GetTechnologyStackFavoriteDetailsResponse setFavoriteCount(Integer value) { this.favoriteCount = value; return this; }
    }

    public static class GetConfigResponse
    {
        public ArrayList<Option> allTiers = null;
        public ArrayList<Option> allPostTypes = null;
        public ArrayList<Option> allFlagTypes = null;
        
        public ArrayList<Option> getAllTiers() { return allTiers; }
        public GetConfigResponse setAllTiers(ArrayList<Option> value) { this.allTiers = value; return this; }
        public ArrayList<Option> getAllPostTypes() { return allPostTypes; }
        public GetConfigResponse setAllPostTypes(ArrayList<Option> value) { this.allPostTypes = value; return this; }
        public ArrayList<Option> getAllFlagTypes() { return allFlagTypes; }
        public GetConfigResponse setAllFlagTypes(ArrayList<Option> value) { this.allFlagTypes = value; return this; }
    }

    public static class CreateTechnologyStackResponse
    {
        public TechStackDetails result = null;
        public ResponseStatus responseStatus = null;
        
        public TechStackDetails getResult() { return result; }
        public CreateTechnologyStackResponse setResult(TechStackDetails value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public CreateTechnologyStackResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class UpdateTechnologyStackResponse
    {
        public TechStackDetails result = null;
        public ResponseStatus responseStatus = null;
        
        public TechStackDetails getResult() { return result; }
        public UpdateTechnologyStackResponse setResult(TechStackDetails value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public UpdateTechnologyStackResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class DeleteTechnologyStackResponse
    {
        public TechStackDetails result = null;
        public ResponseStatus responseStatus = null;
        
        public TechStackDetails getResult() { return result; }
        public DeleteTechnologyStackResponse setResult(TechStackDetails value) { this.result = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public DeleteTechnologyStackResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetFavoriteTechStackResponse
    {
        public ArrayList<TechnologyStack> results = null;
        
        public ArrayList<TechnologyStack> getResults() { return results; }
        public GetFavoriteTechStackResponse setResults(ArrayList<TechnologyStack> value) { this.results = value; return this; }
    }

    public static class FavoriteTechStackResponse
    {
        public TechnologyStack result = null;
        
        public TechnologyStack getResult() { return result; }
        public FavoriteTechStackResponse setResult(TechnologyStack value) { this.result = value; return this; }
    }

    public static class GetFavoriteTechnologiesResponse
    {
        public ArrayList<Technology> results = null;
        
        public ArrayList<Technology> getResults() { return results; }
        public GetFavoriteTechnologiesResponse setResults(ArrayList<Technology> value) { this.results = value; return this; }
    }

    public static class FavoriteTechnologyResponse
    {
        public Technology result = null;
        
        public Technology getResult() { return result; }
        public FavoriteTechnologyResponse setResult(Technology value) { this.result = value; return this; }
    }

    public static class GetUserFeedResponse
    {
        public ArrayList<TechStackDetails> results = null;
        
        public ArrayList<TechStackDetails> getResults() { return results; }
        public GetUserFeedResponse setResults(ArrayList<TechStackDetails> value) { this.results = value; return this; }
    }

    public static class GetUsersKarmaResponse
    {
        public HashMap<Integer,Integer> results = null;
        public ResponseStatus responseStatus = null;
        
        public HashMap<Integer,Integer> getResults() { return results; }
        public GetUsersKarmaResponse setResults(HashMap<Integer,Integer> value) { this.results = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetUsersKarmaResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class GetUserInfoResponse
    {
        public Integer id = null;
        public String userName = null;
        public Date created = null;
        public String avatarUrl = null;
        public ArrayList<TechnologyStack> techStacks = null;
        public ArrayList<TechnologyStack> favoriteTechStacks = null;
        public ArrayList<Technology> favoriteTechnologies = null;
        public UserActivity userActivity = null;
        public ResponseStatus responseStatus = null;
        
        public Integer getId() { return id; }
        public GetUserInfoResponse setId(Integer value) { this.id = value; return this; }
        public String getUserName() { return userName; }
        public GetUserInfoResponse setUserName(String value) { this.userName = value; return this; }
        public Date getCreated() { return created; }
        public GetUserInfoResponse setCreated(Date value) { this.created = value; return this; }
        public String getAvatarUrl() { return avatarUrl; }
        public GetUserInfoResponse setAvatarUrl(String value) { this.avatarUrl = value; return this; }
        public ArrayList<TechnologyStack> getTechStacks() { return techStacks; }
        public GetUserInfoResponse setTechStacks(ArrayList<TechnologyStack> value) { this.techStacks = value; return this; }
        public ArrayList<TechnologyStack> getFavoriteTechStacks() { return favoriteTechStacks; }
        public GetUserInfoResponse setFavoriteTechStacks(ArrayList<TechnologyStack> value) { this.favoriteTechStacks = value; return this; }
        public ArrayList<Technology> getFavoriteTechnologies() { return favoriteTechnologies; }
        public GetUserInfoResponse setFavoriteTechnologies(ArrayList<Technology> value) { this.favoriteTechnologies = value; return this; }
        public UserActivity getUserActivity() { return userActivity; }
        public GetUserInfoResponse setUserActivity(UserActivity value) { this.userActivity = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetUserInfoResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class SyncDiscourseSiteResponse
    {
        public String timeTaken = null;
        public ArrayList<String> userLogs = null;
        public ArrayList<String> postsLogs = null;
        public ResponseStatus responseStatus = null;
        
        public String getTimeTaken() { return timeTaken; }
        public SyncDiscourseSiteResponse setTimeTaken(String value) { this.timeTaken = value; return this; }
        public ArrayList<String> getUserLogs() { return userLogs; }
        public SyncDiscourseSiteResponse setUserLogs(ArrayList<String> value) { this.userLogs = value; return this; }
        public ArrayList<String> getPostsLogs() { return postsLogs; }
        public SyncDiscourseSiteResponse setPostsLogs(ArrayList<String> value) { this.postsLogs = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public SyncDiscourseSiteResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class LogoUrlApprovalResponse
    {
        public Technology result = null;
        
        public Technology getResult() { return result; }
        public LogoUrlApprovalResponse setResult(Technology value) { this.result = value; return this; }
    }

    public static class LockStackResponse
    {
        
    }

    public static class EmailTestRespoonse
    {
        public ResponseStatus responseStatus = null;
        
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public EmailTestRespoonse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class ImportUserResponse
    {
        public Integer id = null;
        public ResponseStatus responseStatus = null;
        
        public Integer getId() { return id; }
        public ImportUserResponse setId(Integer value) { this.id = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public ImportUserResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class ImportUserVoiceSuggestionResponse
    {
        public Long postId = null;
        public String postSlug = null;
        public ResponseStatus responseStatus = null;
        
        public Long getPostId() { return postId; }
        public ImportUserVoiceSuggestionResponse setPostId(Long value) { this.postId = value; return this; }
        public String getPostSlug() { return postSlug; }
        public ImportUserVoiceSuggestionResponse setPostSlug(String value) { this.postSlug = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public ImportUserVoiceSuggestionResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
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
        public String refreshToken = null;

        @DataMember(Order=8)
        public ResponseStatus responseStatus = null;

        @DataMember(Order=9)
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
        public String getRefreshToken() { return refreshToken; }
        public AuthenticateResponse setRefreshToken(String value) { this.refreshToken = value; return this; }
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
    public static class ConvertSessionToTokenResponse
    {
        @DataMember(Order=1)
        public HashMap<String,String> meta = null;

        @DataMember(Order=2)
        public String accessToken = null;

        @DataMember(Order=3)
        public ResponseStatus responseStatus = null;
        
        public HashMap<String,String> getMeta() { return meta; }
        public ConvertSessionToTokenResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
        public String getAccessToken() { return accessToken; }
        public ConvertSessionToTokenResponse setAccessToken(String value) { this.accessToken = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public ConvertSessionToTokenResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    @DataContract
    public static class GetAccessTokenResponse
    {
        @DataMember(Order=1)
        public String accessToken = null;

        @DataMember(Order=2)
        public ResponseStatus responseStatus = null;
        
        public String getAccessToken() { return accessToken; }
        public GetAccessTokenResponse setAccessToken(String value) { this.accessToken = value; return this; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public GetAccessTokenResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
    }

    public static class Organization
    {
        public Integer id = null;
        public String name = null;
        public String slug = null;
        public String description = null;
        public String descriptionHtml = null;
        public String color = null;
        public String textColor = null;
        public String linkColor = null;
        public String backgroundColor = null;
        public String backgroundUrl = null;
        public String logoUrl = null;
        public String heroUrl = null;
        public String lang = null;
        public String defaultPostType = null;
        public ArrayList<String> defaultSubscriptionPostTypes = null;
        public ArrayList<String> postTypes = null;
        public ArrayList<String> moderatorPostTypes = null;
        public Integer deletePostsWithReportCount = null;
        public Boolean disableInvites = null;
        public Long upVotes = null;
        public Long downVotes = null;
        public Long views = null;
        public Long favorites = null;
        public Integer subscribers = null;
        public Integer commentsCount = null;
        public Integer postsCount = null;
        public Integer score = null;
        public Integer rank = null;
        public Long refId = null;
        public String refSource = null;
        public Date hidden = null;
        public String hiddenBy = null;
        public Date locked = null;
        public String lockedBy = null;
        public Date deleted = null;
        public String deletedBy = null;
        public Date created = null;
        public String createdBy = null;
        public Date modified = null;
        public String modifiedBy = null;
        
        public Integer getId() { return id; }
        public Organization setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public Organization setName(String value) { this.name = value; return this; }
        public String getSlug() { return slug; }
        public Organization setSlug(String value) { this.slug = value; return this; }
        public String getDescription() { return description; }
        public Organization setDescription(String value) { this.description = value; return this; }
        public String getDescriptionHtml() { return descriptionHtml; }
        public Organization setDescriptionHtml(String value) { this.descriptionHtml = value; return this; }
        public String getColor() { return color; }
        public Organization setColor(String value) { this.color = value; return this; }
        public String getTextColor() { return textColor; }
        public Organization setTextColor(String value) { this.textColor = value; return this; }
        public String getLinkColor() { return linkColor; }
        public Organization setLinkColor(String value) { this.linkColor = value; return this; }
        public String getBackgroundColor() { return backgroundColor; }
        public Organization setBackgroundColor(String value) { this.backgroundColor = value; return this; }
        public String getBackgroundUrl() { return backgroundUrl; }
        public Organization setBackgroundUrl(String value) { this.backgroundUrl = value; return this; }
        public String getLogoUrl() { return logoUrl; }
        public Organization setLogoUrl(String value) { this.logoUrl = value; return this; }
        public String getHeroUrl() { return heroUrl; }
        public Organization setHeroUrl(String value) { this.heroUrl = value; return this; }
        public String getLang() { return lang; }
        public Organization setLang(String value) { this.lang = value; return this; }
        public String getDefaultPostType() { return defaultPostType; }
        public Organization setDefaultPostType(String value) { this.defaultPostType = value; return this; }
        public ArrayList<String> getDefaultSubscriptionPostTypes() { return defaultSubscriptionPostTypes; }
        public Organization setDefaultSubscriptionPostTypes(ArrayList<String> value) { this.defaultSubscriptionPostTypes = value; return this; }
        public ArrayList<String> getPostTypes() { return postTypes; }
        public Organization setPostTypes(ArrayList<String> value) { this.postTypes = value; return this; }
        public ArrayList<String> getModeratorPostTypes() { return moderatorPostTypes; }
        public Organization setModeratorPostTypes(ArrayList<String> value) { this.moderatorPostTypes = value; return this; }
        public Integer getDeletePostsWithReportCount() { return deletePostsWithReportCount; }
        public Organization setDeletePostsWithReportCount(Integer value) { this.deletePostsWithReportCount = value; return this; }
        public Boolean isDisableInvites() { return disableInvites; }
        public Organization setDisableInvites(Boolean value) { this.disableInvites = value; return this; }
        public Long getUpVotes() { return upVotes; }
        public Organization setUpVotes(Long value) { this.upVotes = value; return this; }
        public Long getDownVotes() { return downVotes; }
        public Organization setDownVotes(Long value) { this.downVotes = value; return this; }
        public Long getViews() { return views; }
        public Organization setViews(Long value) { this.views = value; return this; }
        public Long getFavorites() { return favorites; }
        public Organization setFavorites(Long value) { this.favorites = value; return this; }
        public Integer getSubscribers() { return subscribers; }
        public Organization setSubscribers(Integer value) { this.subscribers = value; return this; }
        public Integer getCommentsCount() { return commentsCount; }
        public Organization setCommentsCount(Integer value) { this.commentsCount = value; return this; }
        public Integer getPostsCount() { return postsCount; }
        public Organization setPostsCount(Integer value) { this.postsCount = value; return this; }
        public Integer getScore() { return score; }
        public Organization setScore(Integer value) { this.score = value; return this; }
        public Integer getRank() { return rank; }
        public Organization setRank(Integer value) { this.rank = value; return this; }
        public Long getRefId() { return refId; }
        public Organization setRefId(Long value) { this.refId = value; return this; }
        public String getRefSource() { return refSource; }
        public Organization setRefSource(String value) { this.refSource = value; return this; }
        public Date getHidden() { return hidden; }
        public Organization setHidden(Date value) { this.hidden = value; return this; }
        public String getHiddenBy() { return hiddenBy; }
        public Organization setHiddenBy(String value) { this.hiddenBy = value; return this; }
        public Date getLocked() { return locked; }
        public Organization setLocked(Date value) { this.locked = value; return this; }
        public String getLockedBy() { return lockedBy; }
        public Organization setLockedBy(String value) { this.lockedBy = value; return this; }
        public Date getDeleted() { return deleted; }
        public Organization setDeleted(Date value) { this.deleted = value; return this; }
        public String getDeletedBy() { return deletedBy; }
        public Organization setDeletedBy(String value) { this.deletedBy = value; return this; }
        public Date getCreated() { return created; }
        public Organization setCreated(Date value) { this.created = value; return this; }
        public String getCreatedBy() { return createdBy; }
        public Organization setCreatedBy(String value) { this.createdBy = value; return this; }
        public Date getModified() { return modified; }
        public Organization setModified(Date value) { this.modified = value; return this; }
        public String getModifiedBy() { return modifiedBy; }
        public Organization setModifiedBy(String value) { this.modifiedBy = value; return this; }
    }

    public static class OrganizationLabel
    {
        public String slug = null;
        public Integer organizationId = null;
        public String description = null;
        public String color = null;
        
        public String getSlug() { return slug; }
        public OrganizationLabel setSlug(String value) { this.slug = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public OrganizationLabel setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getDescription() { return description; }
        public OrganizationLabel setDescription(String value) { this.description = value; return this; }
        public String getColor() { return color; }
        public OrganizationLabel setColor(String value) { this.color = value; return this; }
    }

    public static class Category
    {
        public Integer id = null;
        public Integer organizationId = null;
        public String name = null;
        public String slug = null;
        public String description = null;
        public String color = null;
        public ArrayList<Integer> technologyIds = null;
        public Integer commentsCount = null;
        public Integer postsCount = null;
        public Integer score = null;
        public Integer rank = null;
        
        public Integer getId() { return id; }
        public Category setId(Integer value) { this.id = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public Category setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public String getName() { return name; }
        public Category setName(String value) { this.name = value; return this; }
        public String getSlug() { return slug; }
        public Category setSlug(String value) { this.slug = value; return this; }
        public String getDescription() { return description; }
        public Category setDescription(String value) { this.description = value; return this; }
        public String getColor() { return color; }
        public Category setColor(String value) { this.color = value; return this; }
        public ArrayList<Integer> getTechnologyIds() { return technologyIds; }
        public Category setTechnologyIds(ArrayList<Integer> value) { this.technologyIds = value; return this; }
        public Integer getCommentsCount() { return commentsCount; }
        public Category setCommentsCount(Integer value) { this.commentsCount = value; return this; }
        public Integer getPostsCount() { return postsCount; }
        public Category setPostsCount(Integer value) { this.postsCount = value; return this; }
        public Integer getScore() { return score; }
        public Category setScore(Integer value) { this.score = value; return this; }
        public Integer getRank() { return rank; }
        public Category setRank(Integer value) { this.rank = value; return this; }
    }

    public static class OrganizationMember
    {
        public Integer id = null;
        public Integer organizationId = null;
        public Integer userId = null;
        public String userName = null;
        public Boolean isOwner = null;
        public Boolean isModerator = null;
        public Boolean denyAll = null;
        public Boolean denyPosts = null;
        public Boolean denyComments = null;
        public String notes = null;
        
        public Integer getId() { return id; }
        public OrganizationMember setId(Integer value) { this.id = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public OrganizationMember setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Integer getUserId() { return userId; }
        public OrganizationMember setUserId(Integer value) { this.userId = value; return this; }
        public String getUserName() { return userName; }
        public OrganizationMember setUserName(String value) { this.userName = value; return this; }
        public Boolean getIsOwner() { return isOwner; }
        public OrganizationMember setIsOwner(Boolean value) { this.isOwner = value; return this; }
        public Boolean getIsModerator() { return isModerator; }
        public OrganizationMember setIsModerator(Boolean value) { this.isModerator = value; return this; }
        public Boolean isDenyAll() { return denyAll; }
        public OrganizationMember setDenyAll(Boolean value) { this.denyAll = value; return this; }
        public Boolean isDenyPosts() { return denyPosts; }
        public OrganizationMember setDenyPosts(Boolean value) { this.denyPosts = value; return this; }
        public Boolean isDenyComments() { return denyComments; }
        public OrganizationMember setDenyComments(Boolean value) { this.denyComments = value; return this; }
        public String getNotes() { return notes; }
        public OrganizationMember setNotes(String value) { this.notes = value; return this; }
    }

    public static class OrganizationMemberInvite
    {
        public Integer id = null;
        public Integer organizationId = null;
        public Integer userId = null;
        public String userName = null;
        public Date dismissed = null;
        
        public Integer getId() { return id; }
        public OrganizationMemberInvite setId(Integer value) { this.id = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public OrganizationMemberInvite setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Integer getUserId() { return userId; }
        public OrganizationMemberInvite setUserId(Integer value) { this.userId = value; return this; }
        public String getUserName() { return userName; }
        public OrganizationMemberInvite setUserName(String value) { this.userName = value; return this; }
        public Date getDismissed() { return dismissed; }
        public OrganizationMemberInvite setDismissed(Date value) { this.dismissed = value; return this; }
    }

    public static class PostReportInfo extends PostReport
    {
        public String title = null;
        public Integer reportCount = null;
        public String createdBy = null;
        
        public String getTitle() { return title; }
        public PostReportInfo setTitle(String value) { this.title = value; return this; }
        public Integer getReportCount() { return reportCount; }
        public PostReportInfo setReportCount(Integer value) { this.reportCount = value; return this; }
        public String getCreatedBy() { return createdBy; }
        public PostReportInfo setCreatedBy(String value) { this.createdBy = value; return this; }
    }

    public static class PostCommentReportInfo extends PostCommentReport
    {
        public String contentHtml = null;
        public Integer reportCount = null;
        public String createdBy = null;
        
        public String getContentHtml() { return contentHtml; }
        public PostCommentReportInfo setContentHtml(String value) { this.contentHtml = value; return this; }
        public Integer getReportCount() { return reportCount; }
        public PostCommentReportInfo setReportCount(Integer value) { this.reportCount = value; return this; }
        public String getCreatedBy() { return createdBy; }
        public PostCommentReportInfo setCreatedBy(String value) { this.createdBy = value; return this; }
    }

    public static class QueryDb<T> extends QueryBase
    {
        
    }

    public static class Post
    {
        public Long id = null;
        public Integer organizationId = null;
        public Integer userId = null;
        public PostType type = null;
        public Integer categoryId = null;
        public String title = null;
        public String slug = null;
        public String url = null;
        public String imageUrl = null;
        @StringLength(2147483647)
        public String content = null;

        @StringLength(2147483647)
        public String contentHtml = null;

        public Long pinCommentId = null;
        public ArrayList<Integer> technologyIds = null;
        public Date fromDate = null;
        public Date toDate = null;
        public String location = null;
        public String metaType = null;
        public String meta = null;
        public Boolean approved = null;
        public Long upVotes = null;
        public Long downVotes = null;
        public Long points = null;
        public Long views = null;
        public Long favorites = null;
        public Integer subscribers = null;
        public Integer replyCount = null;
        public Integer commentsCount = null;
        public Integer wordCount = null;
        public Integer reportCount = null;
        public Integer linksCount = null;
        public Integer linkedToCount = null;
        public Integer score = null;
        public Integer rank = null;
        public ArrayList<String> labels = null;
        public ArrayList<Integer> refUserIds = null;
        public ArrayList<String> refLinks = null;
        public ArrayList<Integer> muteUserIds = null;
        public Date lastCommentDate = null;
        public Long lastCommentId = null;
        public Integer lastCommentUserId = null;
        public Date deleted = null;
        public String deletedBy = null;
        public Date locked = null;
        public String lockedBy = null;
        public Date hidden = null;
        public String hiddenBy = null;
        public String status = null;
        public Date statusDate = null;
        public String statusBy = null;
        public Boolean archived = null;
        public Date bumped = null;
        public Date created = null;
        public String createdBy = null;
        public Date modified = null;
        public String modifiedBy = null;
        public Long refId = null;
        public String refSource = null;
        public String refUrn = null;
        
        public Long getId() { return id; }
        public Post setId(Long value) { this.id = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public Post setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Integer getUserId() { return userId; }
        public Post setUserId(Integer value) { this.userId = value; return this; }
        public PostType getType() { return type; }
        public Post setType(PostType value) { this.type = value; return this; }
        public Integer getCategoryId() { return categoryId; }
        public Post setCategoryId(Integer value) { this.categoryId = value; return this; }
        public String getTitle() { return title; }
        public Post setTitle(String value) { this.title = value; return this; }
        public String getSlug() { return slug; }
        public Post setSlug(String value) { this.slug = value; return this; }
        public String getUrl() { return url; }
        public Post setUrl(String value) { this.url = value; return this; }
        public String getImageUrl() { return imageUrl; }
        public Post setImageUrl(String value) { this.imageUrl = value; return this; }
        public String getContent() { return content; }
        public Post setContent(String value) { this.content = value; return this; }
        public String getContentHtml() { return contentHtml; }
        public Post setContentHtml(String value) { this.contentHtml = value; return this; }
        public Long getPinCommentId() { return pinCommentId; }
        public Post setPinCommentId(Long value) { this.pinCommentId = value; return this; }
        public ArrayList<Integer> getTechnologyIds() { return technologyIds; }
        public Post setTechnologyIds(ArrayList<Integer> value) { this.technologyIds = value; return this; }
        public Date getFromDate() { return fromDate; }
        public Post setFromDate(Date value) { this.fromDate = value; return this; }
        public Date getToDate() { return toDate; }
        public Post setToDate(Date value) { this.toDate = value; return this; }
        public String getLocation() { return location; }
        public Post setLocation(String value) { this.location = value; return this; }
        public String getMetaType() { return metaType; }
        public Post setMetaType(String value) { this.metaType = value; return this; }
        public String getMeta() { return meta; }
        public Post setMeta(String value) { this.meta = value; return this; }
        public Boolean isApproved() { return approved; }
        public Post setApproved(Boolean value) { this.approved = value; return this; }
        public Long getUpVotes() { return upVotes; }
        public Post setUpVotes(Long value) { this.upVotes = value; return this; }
        public Long getDownVotes() { return downVotes; }
        public Post setDownVotes(Long value) { this.downVotes = value; return this; }
        public Long getPoints() { return points; }
        public Post setPoints(Long value) { this.points = value; return this; }
        public Long getViews() { return views; }
        public Post setViews(Long value) { this.views = value; return this; }
        public Long getFavorites() { return favorites; }
        public Post setFavorites(Long value) { this.favorites = value; return this; }
        public Integer getSubscribers() { return subscribers; }
        public Post setSubscribers(Integer value) { this.subscribers = value; return this; }
        public Integer getReplyCount() { return replyCount; }
        public Post setReplyCount(Integer value) { this.replyCount = value; return this; }
        public Integer getCommentsCount() { return commentsCount; }
        public Post setCommentsCount(Integer value) { this.commentsCount = value; return this; }
        public Integer getWordCount() { return wordCount; }
        public Post setWordCount(Integer value) { this.wordCount = value; return this; }
        public Integer getReportCount() { return reportCount; }
        public Post setReportCount(Integer value) { this.reportCount = value; return this; }
        public Integer getLinksCount() { return linksCount; }
        public Post setLinksCount(Integer value) { this.linksCount = value; return this; }
        public Integer getLinkedToCount() { return linkedToCount; }
        public Post setLinkedToCount(Integer value) { this.linkedToCount = value; return this; }
        public Integer getScore() { return score; }
        public Post setScore(Integer value) { this.score = value; return this; }
        public Integer getRank() { return rank; }
        public Post setRank(Integer value) { this.rank = value; return this; }
        public ArrayList<String> getLabels() { return labels; }
        public Post setLabels(ArrayList<String> value) { this.labels = value; return this; }
        public ArrayList<Integer> getRefUserIds() { return refUserIds; }
        public Post setRefUserIds(ArrayList<Integer> value) { this.refUserIds = value; return this; }
        public ArrayList<String> getRefLinks() { return refLinks; }
        public Post setRefLinks(ArrayList<String> value) { this.refLinks = value; return this; }
        public ArrayList<Integer> getMuteUserIds() { return muteUserIds; }
        public Post setMuteUserIds(ArrayList<Integer> value) { this.muteUserIds = value; return this; }
        public Date getLastCommentDate() { return lastCommentDate; }
        public Post setLastCommentDate(Date value) { this.lastCommentDate = value; return this; }
        public Long getLastCommentId() { return lastCommentId; }
        public Post setLastCommentId(Long value) { this.lastCommentId = value; return this; }
        public Integer getLastCommentUserId() { return lastCommentUserId; }
        public Post setLastCommentUserId(Integer value) { this.lastCommentUserId = value; return this; }
        public Date getDeleted() { return deleted; }
        public Post setDeleted(Date value) { this.deleted = value; return this; }
        public String getDeletedBy() { return deletedBy; }
        public Post setDeletedBy(String value) { this.deletedBy = value; return this; }
        public Date getLocked() { return locked; }
        public Post setLocked(Date value) { this.locked = value; return this; }
        public String getLockedBy() { return lockedBy; }
        public Post setLockedBy(String value) { this.lockedBy = value; return this; }
        public Date getHidden() { return hidden; }
        public Post setHidden(Date value) { this.hidden = value; return this; }
        public String getHiddenBy() { return hiddenBy; }
        public Post setHiddenBy(String value) { this.hiddenBy = value; return this; }
        public String getStatus() { return status; }
        public Post setStatus(String value) { this.status = value; return this; }
        public Date getStatusDate() { return statusDate; }
        public Post setStatusDate(Date value) { this.statusDate = value; return this; }
        public String getStatusBy() { return statusBy; }
        public Post setStatusBy(String value) { this.statusBy = value; return this; }
        public Boolean isArchived() { return archived; }
        public Post setArchived(Boolean value) { this.archived = value; return this; }
        public Date getBumped() { return bumped; }
        public Post setBumped(Date value) { this.bumped = value; return this; }
        public Date getCreated() { return created; }
        public Post setCreated(Date value) { this.created = value; return this; }
        public String getCreatedBy() { return createdBy; }
        public Post setCreatedBy(String value) { this.createdBy = value; return this; }
        public Date getModified() { return modified; }
        public Post setModified(Date value) { this.modified = value; return this; }
        public String getModifiedBy() { return modifiedBy; }
        public Post setModifiedBy(String value) { this.modifiedBy = value; return this; }
        public Long getRefId() { return refId; }
        public Post setRefId(Long value) { this.refId = value; return this; }
        public String getRefSource() { return refSource; }
        public Post setRefSource(String value) { this.refSource = value; return this; }
        public String getRefUrn() { return refUrn; }
        public Post setRefUrn(String value) { this.refUrn = value; return this; }
    }

    public static class PostComment
    {
        public Long id = null;
        public Long postId = null;
        public Integer userId = null;
        public Long replyId = null;
        @StringLength(2147483647)
        public String content = null;

        @StringLength(2147483647)
        public String contentHtml = null;

        public Integer score = null;
        public Integer rank = null;
        public Long upVotes = null;
        public Long downVotes = null;
        public Long favorites = null;
        public Integer wordCount = null;
        public Integer reportCount = null;
        public Date deleted = null;
        public Date hidden = null;
        public Date modified = null;
        public Date created = null;
        public String createdBy = null;
        public Long refId = null;
        public String refSource = null;
        public String refUrn = null;
        
        public Long getId() { return id; }
        public PostComment setId(Long value) { this.id = value; return this; }
        public Long getPostId() { return postId; }
        public PostComment setPostId(Long value) { this.postId = value; return this; }
        public Integer getUserId() { return userId; }
        public PostComment setUserId(Integer value) { this.userId = value; return this; }
        public Long getReplyId() { return replyId; }
        public PostComment setReplyId(Long value) { this.replyId = value; return this; }
        public String getContent() { return content; }
        public PostComment setContent(String value) { this.content = value; return this; }
        public String getContentHtml() { return contentHtml; }
        public PostComment setContentHtml(String value) { this.contentHtml = value; return this; }
        public Integer getScore() { return score; }
        public PostComment setScore(Integer value) { this.score = value; return this; }
        public Integer getRank() { return rank; }
        public PostComment setRank(Integer value) { this.rank = value; return this; }
        public Long getUpVotes() { return upVotes; }
        public PostComment setUpVotes(Long value) { this.upVotes = value; return this; }
        public Long getDownVotes() { return downVotes; }
        public PostComment setDownVotes(Long value) { this.downVotes = value; return this; }
        public Long getFavorites() { return favorites; }
        public PostComment setFavorites(Long value) { this.favorites = value; return this; }
        public Integer getWordCount() { return wordCount; }
        public PostComment setWordCount(Integer value) { this.wordCount = value; return this; }
        public Integer getReportCount() { return reportCount; }
        public PostComment setReportCount(Integer value) { this.reportCount = value; return this; }
        public Date getDeleted() { return deleted; }
        public PostComment setDeleted(Date value) { this.deleted = value; return this; }
        public Date getHidden() { return hidden; }
        public PostComment setHidden(Date value) { this.hidden = value; return this; }
        public Date getModified() { return modified; }
        public PostComment setModified(Date value) { this.modified = value; return this; }
        public Date getCreated() { return created; }
        public PostComment setCreated(Date value) { this.created = value; return this; }
        public String getCreatedBy() { return createdBy; }
        public PostComment setCreatedBy(String value) { this.createdBy = value; return this; }
        public Long getRefId() { return refId; }
        public PostComment setRefId(Long value) { this.refId = value; return this; }
        public String getRefSource() { return refSource; }
        public PostComment setRefSource(String value) { this.refSource = value; return this; }
        public String getRefUrn() { return refUrn; }
        public PostComment setRefUrn(String value) { this.refUrn = value; return this; }
    }

    public static enum PostType
    {
        Announcement,
        Post,
        Showcase,
        Question,
        Request;
    }

    public static enum ReportAction
    {
        Dismiss,
        Delete;
    }

    public static class UserRef
    {
        public Integer id = null;
        public String userName = null;
        public String email = null;
        public Integer refId = null;
        public String refSource = null;
        public String refUrn = null;
        
        public Integer getId() { return id; }
        public UserRef setId(Integer value) { this.id = value; return this; }
        public String getUserName() { return userName; }
        public UserRef setUserName(String value) { this.userName = value; return this; }
        public String getEmail() { return email; }
        public UserRef setEmail(String value) { this.email = value; return this; }
        public Integer getRefId() { return refId; }
        public UserRef setRefId(Integer value) { this.refId = value; return this; }
        public String getRefSource() { return refSource; }
        public UserRef setRefSource(String value) { this.refSource = value; return this; }
        public String getRefUrn() { return refUrn; }
        public UserRef setRefUrn(String value) { this.refUrn = value; return this; }
    }

    public static class OrganizationSubscription
    {
        public Long id = null;
        public Integer organizationId = null;
        public Integer userId = null;
        public String userName = null;
        public ArrayList<String> postTypes = null;
        public Integer frequencyDays = null;
        public Long lastSyncedId = null;
        public Date lastSynced = null;
        public Date created = null;
        
        public Long getId() { return id; }
        public OrganizationSubscription setId(Long value) { this.id = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public OrganizationSubscription setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Integer getUserId() { return userId; }
        public OrganizationSubscription setUserId(Integer value) { this.userId = value; return this; }
        public String getUserName() { return userName; }
        public OrganizationSubscription setUserName(String value) { this.userName = value; return this; }
        public ArrayList<String> getPostTypes() { return postTypes; }
        public OrganizationSubscription setPostTypes(ArrayList<String> value) { this.postTypes = value; return this; }
        public Integer getFrequencyDays() { return frequencyDays; }
        public OrganizationSubscription setFrequencyDays(Integer value) { this.frequencyDays = value; return this; }
        public Long getLastSyncedId() { return lastSyncedId; }
        public OrganizationSubscription setLastSyncedId(Long value) { this.lastSyncedId = value; return this; }
        public Date getLastSynced() { return lastSynced; }
        public OrganizationSubscription setLastSynced(Date value) { this.lastSynced = value; return this; }
        public Date getCreated() { return created; }
        public OrganizationSubscription setCreated(Date value) { this.created = value; return this; }
    }

    public static enum FlagType
    {
        Violation,
        Spam,
        Abusive,
        Confidential,
        OffTopic,
        Other;
    }

    public static class TechnologyStack extends TechnologyStackBase
    {
        
    }

    public static class Technology extends TechnologyBase
    {
        
    }

    public static class UserActivity
    {
        public Integer id = null;
        public String userName = null;
        public Integer karma = null;
        public Integer technologyCount = null;
        public Integer techStacksCount = null;
        public Integer postsCount = null;
        public Integer postUpVotes = null;
        public Integer postDownVotes = null;
        public Integer commentUpVotes = null;
        public Integer commentDownVotes = null;
        public Integer postCommentsCount = null;
        public Integer pinnedCommentCount = null;
        public Integer postReportCount = null;
        public Integer postCommentReportCount = null;
        public Date created = null;
        public Date modified = null;
        
        public Integer getId() { return id; }
        public UserActivity setId(Integer value) { this.id = value; return this; }
        public String getUserName() { return userName; }
        public UserActivity setUserName(String value) { this.userName = value; return this; }
        public Integer getKarma() { return karma; }
        public UserActivity setKarma(Integer value) { this.karma = value; return this; }
        public Integer getTechnologyCount() { return technologyCount; }
        public UserActivity setTechnologyCount(Integer value) { this.technologyCount = value; return this; }
        public Integer getTechStacksCount() { return techStacksCount; }
        public UserActivity setTechStacksCount(Integer value) { this.techStacksCount = value; return this; }
        public Integer getPostsCount() { return postsCount; }
        public UserActivity setPostsCount(Integer value) { this.postsCount = value; return this; }
        public Integer getPostUpVotes() { return postUpVotes; }
        public UserActivity setPostUpVotes(Integer value) { this.postUpVotes = value; return this; }
        public Integer getPostDownVotes() { return postDownVotes; }
        public UserActivity setPostDownVotes(Integer value) { this.postDownVotes = value; return this; }
        public Integer getCommentUpVotes() { return commentUpVotes; }
        public UserActivity setCommentUpVotes(Integer value) { this.commentUpVotes = value; return this; }
        public Integer getCommentDownVotes() { return commentDownVotes; }
        public UserActivity setCommentDownVotes(Integer value) { this.commentDownVotes = value; return this; }
        public Integer getPostCommentsCount() { return postCommentsCount; }
        public UserActivity setPostCommentsCount(Integer value) { this.postCommentsCount = value; return this; }
        public Integer getPinnedCommentCount() { return pinnedCommentCount; }
        public UserActivity setPinnedCommentCount(Integer value) { this.pinnedCommentCount = value; return this; }
        public Integer getPostReportCount() { return postReportCount; }
        public UserActivity setPostReportCount(Integer value) { this.postReportCount = value; return this; }
        public Integer getPostCommentReportCount() { return postCommentReportCount; }
        public UserActivity setPostCommentReportCount(Integer value) { this.postCommentReportCount = value; return this; }
        public Date getCreated() { return created; }
        public UserActivity setCreated(Date value) { this.created = value; return this; }
        public Date getModified() { return modified; }
        public UserActivity setModified(Date value) { this.modified = value; return this; }
    }

    public static enum Frequency
    {
        Daily(1),
        Weekly(7),
        Monthly(30),
        Quarterly(90);

        private final int value;
        Frequency(final int intValue) { value = intValue; }
        public int getValue() { return value; }
    }

    public static class TechnologyHistory extends TechnologyBase
    {
        public Long technologyId = null;
        public String operation = null;
        
        public Long getTechnologyId() { return technologyId; }
        public TechnologyHistory setTechnologyId(Long value) { this.technologyId = value; return this; }
        public String getOperation() { return operation; }
        public TechnologyHistory setOperation(String value) { this.operation = value; return this; }
    }

    public static interface IRegisterStats
    {
    }

    public static enum TechnologyTier
    {
        ProgrammingLanguage,
        Client,
        Http,
        Server,
        Data,
        SoftwareInfrastructure,
        OperatingSystem,
        HardwareInfrastructure,
        ThirdPartyServices;
    }

    public static class TechnologyStackHistory extends TechnologyStackBase
    {
        public Long technologyStackId = null;
        public String operation = null;
        public ArrayList<Long> technologyIds = null;
        
        public Long getTechnologyStackId() { return technologyStackId; }
        public TechnologyStackHistory setTechnologyStackId(Long value) { this.technologyStackId = value; return this; }
        public String getOperation() { return operation; }
        public TechnologyStackHistory setOperation(String value) { this.operation = value; return this; }
        public ArrayList<Long> getTechnologyIds() { return technologyIds; }
        public TechnologyStackHistory setTechnologyIds(ArrayList<Long> value) { this.technologyIds = value; return this; }
    }

    public static class UserInfo
    {
        public String userName = null;
        public String avatarUrl = null;
        public Integer stacksCount = null;
        
        public String getUserName() { return userName; }
        public UserInfo setUserName(String value) { this.userName = value; return this; }
        public String getAvatarUrl() { return avatarUrl; }
        public UserInfo setAvatarUrl(String value) { this.avatarUrl = value; return this; }
        public Integer getStacksCount() { return stacksCount; }
        public UserInfo setStacksCount(Integer value) { this.stacksCount = value; return this; }
    }

    public static class TechnologyInfo
    {
        public TechnologyTier tier = null;
        public String slug = null;
        public String name = null;
        public String logoUrl = null;
        public Integer stacksCount = null;
        
        public TechnologyTier getTier() { return tier; }
        public TechnologyInfo setTier(TechnologyTier value) { this.tier = value; return this; }
        public String getSlug() { return slug; }
        public TechnologyInfo setSlug(String value) { this.slug = value; return this; }
        public String getName() { return name; }
        public TechnologyInfo setName(String value) { this.name = value; return this; }
        public String getLogoUrl() { return logoUrl; }
        public TechnologyInfo setLogoUrl(String value) { this.logoUrl = value; return this; }
        public Integer getStacksCount() { return stacksCount; }
        public TechnologyInfo setStacksCount(Integer value) { this.stacksCount = value; return this; }
    }

    public static class TechStackDetails extends TechnologyStackBase
    {
        public ArrayList<TechnologyInStack> technologyChoices = null;
        
        public ArrayList<TechnologyInStack> getTechnologyChoices() { return technologyChoices; }
        public TechStackDetails setTechnologyChoices(ArrayList<TechnologyInStack> value) { this.technologyChoices = value; return this; }
    }

    public static class OrganizationInfo
    {
        public Integer id = null;
        public String name = null;
        public String slug = null;
        public Long refId = null;
        public String refSource = null;
        public Long upVotes = null;
        public Long downVotes = null;
        public Long membersCount = null;
        public Integer rank = null;
        public Boolean disableInvites = null;
        public String lang = null;
        public ArrayList<String> postTypes = null;
        public ArrayList<String> moderatorPostTypes = null;
        public Date locked = null;
        public ArrayList<LabelInfo> labels = null;
        public ArrayList<CategoryInfo> categories = null;
        
        public Integer getId() { return id; }
        public OrganizationInfo setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public OrganizationInfo setName(String value) { this.name = value; return this; }
        public String getSlug() { return slug; }
        public OrganizationInfo setSlug(String value) { this.slug = value; return this; }
        public Long getRefId() { return refId; }
        public OrganizationInfo setRefId(Long value) { this.refId = value; return this; }
        public String getRefSource() { return refSource; }
        public OrganizationInfo setRefSource(String value) { this.refSource = value; return this; }
        public Long getUpVotes() { return upVotes; }
        public OrganizationInfo setUpVotes(Long value) { this.upVotes = value; return this; }
        public Long getDownVotes() { return downVotes; }
        public OrganizationInfo setDownVotes(Long value) { this.downVotes = value; return this; }
        public Long getMembersCount() { return membersCount; }
        public OrganizationInfo setMembersCount(Long value) { this.membersCount = value; return this; }
        public Integer getRank() { return rank; }
        public OrganizationInfo setRank(Integer value) { this.rank = value; return this; }
        public Boolean isDisableInvites() { return disableInvites; }
        public OrganizationInfo setDisableInvites(Boolean value) { this.disableInvites = value; return this; }
        public String getLang() { return lang; }
        public OrganizationInfo setLang(String value) { this.lang = value; return this; }
        public ArrayList<String> getPostTypes() { return postTypes; }
        public OrganizationInfo setPostTypes(ArrayList<String> value) { this.postTypes = value; return this; }
        public ArrayList<String> getModeratorPostTypes() { return moderatorPostTypes; }
        public OrganizationInfo setModeratorPostTypes(ArrayList<String> value) { this.moderatorPostTypes = value; return this; }
        public Date getLocked() { return locked; }
        public OrganizationInfo setLocked(Date value) { this.locked = value; return this; }
        public ArrayList<LabelInfo> getLabels() { return labels; }
        public OrganizationInfo setLabels(ArrayList<LabelInfo> value) { this.labels = value; return this; }
        public ArrayList<CategoryInfo> getCategories() { return categories; }
        public OrganizationInfo setCategories(ArrayList<CategoryInfo> value) { this.categories = value; return this; }
    }

    @DataContract
    public static class Option
    {
        @DataMember(Name="name")
        @SerializedName("name")
        public String name = null;

        @DataMember(Name="title")
        @SerializedName("title")
        public String title = null;

        @DataMember(Name="value")
        @SerializedName("value")
        public TechnologyTier value = null;
        
        public String getName() { return name; }
        public Option setName(String value) { this.name = value; return this; }
        public String getTitle() { return title; }
        public Option setTitle(String value) { this.title = value; return this; }
        public TechnologyTier getValue() { return value; }
        public Option setValue(TechnologyTier value) { this.value = value; return this; }
    }

    public static class UserVoiceUser
    {
        public Integer id = null;
        public String name = null;
        public String email = null;
        public String avatarUrl = null;
        public Date createdAt = null;
        public Date updatedAt = null;
        
        public Integer getId() { return id; }
        public UserVoiceUser setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public UserVoiceUser setName(String value) { this.name = value; return this; }
        public String getEmail() { return email; }
        public UserVoiceUser setEmail(String value) { this.email = value; return this; }
        public String getAvatarUrl() { return avatarUrl; }
        public UserVoiceUser setAvatarUrl(String value) { this.avatarUrl = value; return this; }
        public Date getCreatedAt() { return createdAt; }
        public UserVoiceUser setCreatedAt(Date value) { this.createdAt = value; return this; }
        public Date getUpdatedAt() { return updatedAt; }
        public UserVoiceUser setUpdatedAt(Date value) { this.updatedAt = value; return this; }
    }

    public static class UserVoiceComment
    {
        public String text = null;
        public String formattedText = null;
        public Date createdAt = null;
        public UserVoiceUser creator = null;
        
        public String getText() { return text; }
        public UserVoiceComment setText(String value) { this.text = value; return this; }
        public String getFormattedText() { return formattedText; }
        public UserVoiceComment setFormattedText(String value) { this.formattedText = value; return this; }
        public Date getCreatedAt() { return createdAt; }
        public UserVoiceComment setCreatedAt(Date value) { this.createdAt = value; return this; }
        public UserVoiceUser getCreator() { return creator; }
        public UserVoiceComment setCreator(UserVoiceUser value) { this.creator = value; return this; }
    }

    public static class PostReport
    {
        public Long id = null;
        public Integer organizationId = null;
        public Long postId = null;
        public Integer userId = null;
        public String userName = null;
        public FlagType flagType = null;
        public String reportNotes = null;
        public Date created = null;
        public Date acknowledged = null;
        public String acknowledgedBy = null;
        public Date dismissed = null;
        public String dismissedBy = null;
        
        public Long getId() { return id; }
        public PostReport setId(Long value) { this.id = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public PostReport setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Long getPostId() { return postId; }
        public PostReport setPostId(Long value) { this.postId = value; return this; }
        public Integer getUserId() { return userId; }
        public PostReport setUserId(Integer value) { this.userId = value; return this; }
        public String getUserName() { return userName; }
        public PostReport setUserName(String value) { this.userName = value; return this; }
        public FlagType getFlagType() { return flagType; }
        public PostReport setFlagType(FlagType value) { this.flagType = value; return this; }
        public String getReportNotes() { return reportNotes; }
        public PostReport setReportNotes(String value) { this.reportNotes = value; return this; }
        public Date getCreated() { return created; }
        public PostReport setCreated(Date value) { this.created = value; return this; }
        public Date getAcknowledged() { return acknowledged; }
        public PostReport setAcknowledged(Date value) { this.acknowledged = value; return this; }
        public String getAcknowledgedBy() { return acknowledgedBy; }
        public PostReport setAcknowledgedBy(String value) { this.acknowledgedBy = value; return this; }
        public Date getDismissed() { return dismissed; }
        public PostReport setDismissed(Date value) { this.dismissed = value; return this; }
        public String getDismissedBy() { return dismissedBy; }
        public PostReport setDismissedBy(String value) { this.dismissedBy = value; return this; }
    }

    public static class PostCommentReport
    {
        public Long id = null;
        public Integer organizationId = null;
        public Long postId = null;
        public Long postCommentId = null;
        public Integer userId = null;
        public String userName = null;
        public FlagType flagType = null;
        public String reportNotes = null;
        public Date created = null;
        public Date acknowledged = null;
        public String acknowledgedBy = null;
        public Date dismissed = null;
        public String dismissedBy = null;
        
        public Long getId() { return id; }
        public PostCommentReport setId(Long value) { this.id = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public PostCommentReport setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Long getPostId() { return postId; }
        public PostCommentReport setPostId(Long value) { this.postId = value; return this; }
        public Long getPostCommentId() { return postCommentId; }
        public PostCommentReport setPostCommentId(Long value) { this.postCommentId = value; return this; }
        public Integer getUserId() { return userId; }
        public PostCommentReport setUserId(Integer value) { this.userId = value; return this; }
        public String getUserName() { return userName; }
        public PostCommentReport setUserName(String value) { this.userName = value; return this; }
        public FlagType getFlagType() { return flagType; }
        public PostCommentReport setFlagType(FlagType value) { this.flagType = value; return this; }
        public String getReportNotes() { return reportNotes; }
        public PostCommentReport setReportNotes(String value) { this.reportNotes = value; return this; }
        public Date getCreated() { return created; }
        public PostCommentReport setCreated(Date value) { this.created = value; return this; }
        public Date getAcknowledged() { return acknowledged; }
        public PostCommentReport setAcknowledged(Date value) { this.acknowledged = value; return this; }
        public String getAcknowledgedBy() { return acknowledgedBy; }
        public PostCommentReport setAcknowledgedBy(String value) { this.acknowledgedBy = value; return this; }
        public Date getDismissed() { return dismissed; }
        public PostCommentReport setDismissed(Date value) { this.dismissed = value; return this; }
        public String getDismissedBy() { return dismissedBy; }
        public PostCommentReport setDismissedBy(String value) { this.dismissedBy = value; return this; }
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

    public static class TechnologyStackBase
    {
        public Long id = null;
        public String name = null;
        public String vendorName = null;
        public String description = null;
        public String appUrl = null;
        public String screenshotUrl = null;
        public Date created = null;
        public String createdBy = null;
        public Date lastModified = null;
        public String lastModifiedBy = null;
        public Boolean isLocked = null;
        public String ownerId = null;
        public String slug = null;
        @StringLength(2147483647)
        public String details = null;

        @StringLength(2147483647)
        public String detailsHtml = null;

        public Date lastStatusUpdate = null;
        public Integer organizationId = null;
        public Long commentsPostId = null;
        public Integer viewCount = null;
        public Integer favCount = null;
        
        public Long getId() { return id; }
        public TechnologyStackBase setId(Long value) { this.id = value; return this; }
        public String getName() { return name; }
        public TechnologyStackBase setName(String value) { this.name = value; return this; }
        public String getVendorName() { return vendorName; }
        public TechnologyStackBase setVendorName(String value) { this.vendorName = value; return this; }
        public String getDescription() { return description; }
        public TechnologyStackBase setDescription(String value) { this.description = value; return this; }
        public String getAppUrl() { return appUrl; }
        public TechnologyStackBase setAppUrl(String value) { this.appUrl = value; return this; }
        public String getScreenshotUrl() { return screenshotUrl; }
        public TechnologyStackBase setScreenshotUrl(String value) { this.screenshotUrl = value; return this; }
        public Date getCreated() { return created; }
        public TechnologyStackBase setCreated(Date value) { this.created = value; return this; }
        public String getCreatedBy() { return createdBy; }
        public TechnologyStackBase setCreatedBy(String value) { this.createdBy = value; return this; }
        public Date getLastModified() { return lastModified; }
        public TechnologyStackBase setLastModified(Date value) { this.lastModified = value; return this; }
        public String getLastModifiedBy() { return lastModifiedBy; }
        public TechnologyStackBase setLastModifiedBy(String value) { this.lastModifiedBy = value; return this; }
        public Boolean getIsLocked() { return isLocked; }
        public TechnologyStackBase setIsLocked(Boolean value) { this.isLocked = value; return this; }
        public String getOwnerId() { return ownerId; }
        public TechnologyStackBase setOwnerId(String value) { this.ownerId = value; return this; }
        public String getSlug() { return slug; }
        public TechnologyStackBase setSlug(String value) { this.slug = value; return this; }
        public String getDetails() { return details; }
        public TechnologyStackBase setDetails(String value) { this.details = value; return this; }
        public String getDetailsHtml() { return detailsHtml; }
        public TechnologyStackBase setDetailsHtml(String value) { this.detailsHtml = value; return this; }
        public Date getLastStatusUpdate() { return lastStatusUpdate; }
        public TechnologyStackBase setLastStatusUpdate(Date value) { this.lastStatusUpdate = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public TechnologyStackBase setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Long getCommentsPostId() { return commentsPostId; }
        public TechnologyStackBase setCommentsPostId(Long value) { this.commentsPostId = value; return this; }
        public Integer getViewCount() { return viewCount; }
        public TechnologyStackBase setViewCount(Integer value) { this.viewCount = value; return this; }
        public Integer getFavCount() { return favCount; }
        public TechnologyStackBase setFavCount(Integer value) { this.favCount = value; return this; }
    }

    public static class TechnologyBase
    {
        public Long id = null;
        public String name = null;
        public String vendorName = null;
        public String vendorUrl = null;
        public String productUrl = null;
        public String logoUrl = null;
        public String description = null;
        public Date created = null;
        public String createdBy = null;
        public Date lastModified = null;
        public String lastModifiedBy = null;
        public String ownerId = null;
        public String slug = null;
        public Boolean logoApproved = null;
        public Boolean isLocked = null;
        public TechnologyTier tier = null;
        public Date lastStatusUpdate = null;
        public Integer organizationId = null;
        public Long commentsPostId = null;
        public Integer viewCount = null;
        public Integer favCount = null;
        
        public Long getId() { return id; }
        public TechnologyBase setId(Long value) { this.id = value; return this; }
        public String getName() { return name; }
        public TechnologyBase setName(String value) { this.name = value; return this; }
        public String getVendorName() { return vendorName; }
        public TechnologyBase setVendorName(String value) { this.vendorName = value; return this; }
        public String getVendorUrl() { return vendorUrl; }
        public TechnologyBase setVendorUrl(String value) { this.vendorUrl = value; return this; }
        public String getProductUrl() { return productUrl; }
        public TechnologyBase setProductUrl(String value) { this.productUrl = value; return this; }
        public String getLogoUrl() { return logoUrl; }
        public TechnologyBase setLogoUrl(String value) { this.logoUrl = value; return this; }
        public String getDescription() { return description; }
        public TechnologyBase setDescription(String value) { this.description = value; return this; }
        public Date getCreated() { return created; }
        public TechnologyBase setCreated(Date value) { this.created = value; return this; }
        public String getCreatedBy() { return createdBy; }
        public TechnologyBase setCreatedBy(String value) { this.createdBy = value; return this; }
        public Date getLastModified() { return lastModified; }
        public TechnologyBase setLastModified(Date value) { this.lastModified = value; return this; }
        public String getLastModifiedBy() { return lastModifiedBy; }
        public TechnologyBase setLastModifiedBy(String value) { this.lastModifiedBy = value; return this; }
        public String getOwnerId() { return ownerId; }
        public TechnologyBase setOwnerId(String value) { this.ownerId = value; return this; }
        public String getSlug() { return slug; }
        public TechnologyBase setSlug(String value) { this.slug = value; return this; }
        public Boolean isLogoApproved() { return logoApproved; }
        public TechnologyBase setLogoApproved(Boolean value) { this.logoApproved = value; return this; }
        public Boolean getIsLocked() { return isLocked; }
        public TechnologyBase setIsLocked(Boolean value) { this.isLocked = value; return this; }
        public TechnologyTier getTier() { return tier; }
        public TechnologyBase setTier(TechnologyTier value) { this.tier = value; return this; }
        public Date getLastStatusUpdate() { return lastStatusUpdate; }
        public TechnologyBase setLastStatusUpdate(Date value) { this.lastStatusUpdate = value; return this; }
        public Integer getOrganizationId() { return organizationId; }
        public TechnologyBase setOrganizationId(Integer value) { this.organizationId = value; return this; }
        public Long getCommentsPostId() { return commentsPostId; }
        public TechnologyBase setCommentsPostId(Long value) { this.commentsPostId = value; return this; }
        public Integer getViewCount() { return viewCount; }
        public TechnologyBase setViewCount(Integer value) { this.viewCount = value; return this; }
        public Integer getFavCount() { return favCount; }
        public TechnologyBase setFavCount(Integer value) { this.favCount = value; return this; }
    }

    public static class TechnologyInStack extends TechnologyBase
    {
        public Long technologyId = null;
        public Long technologyStackId = null;
        public String justification = null;
        
        public Long getTechnologyId() { return technologyId; }
        public TechnologyInStack setTechnologyId(Long value) { this.technologyId = value; return this; }
        public Long getTechnologyStackId() { return technologyStackId; }
        public TechnologyInStack setTechnologyStackId(Long value) { this.technologyStackId = value; return this; }
        public String getJustification() { return justification; }
        public TechnologyInStack setJustification(String value) { this.justification = value; return this; }
    }

    public static class LabelInfo
    {
        public String slug = null;
        public String color = null;
        
        public String getSlug() { return slug; }
        public LabelInfo setSlug(String value) { this.slug = value; return this; }
        public String getColor() { return color; }
        public LabelInfo setColor(String value) { this.color = value; return this; }
    }

    public static class CategoryInfo
    {
        public Integer id = null;
        public String name = null;
        public String slug = null;
        
        public Integer getId() { return id; }
        public CategoryInfo setId(Integer value) { this.id = value; return this; }
        public String getName() { return name; }
        public CategoryInfo setName(String value) { this.name = value; return this; }
        public String getSlug() { return slug; }
        public CategoryInfo setSlug(String value) { this.slug = value; return this; }
    }

}

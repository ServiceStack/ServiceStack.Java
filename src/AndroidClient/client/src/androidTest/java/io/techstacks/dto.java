/* Options:
Date: 2015-03-08 07:34:03
Version: 1
BaseUrl: http://techstacks.io

Package: io.techstacks
//GlobalNamespace: dto
//AddPropertyAccessors: True
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion: 
//IncludeTypes: 
//ExcludeTypes: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.*
*/

package io.techstacks;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;

public class dto
{
    public static interface IReturnVoid
    {
    }

    public static interface IReturn<T>
    {
    }

    public static class Technology extends TechnologyBase
    {
    }

    public static enum TechnologyTier
    {
        programmingLanguage,
        client,
        http,
        server,
        data,
        softwareInfrastructure,
        operatingSystem,
        hardwareInfrastructure,
        thirdPartyServices;
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
        public void setErrorCode(String value) { this.errorCode = value; }
        public String getMessage() { return message; }
        public void setMessage(String value) { this.message = value; }
        public String getStackTrace() { return stackTrace; }
        public void setStackTrace(String value) { this.stackTrace = value; }
        public ArrayList<ResponseError> getErrors() { return errors; }
        public void setErrors(ArrayList<ResponseError> value) { this.errors = value; }
    }

    public static class TechnologyStack extends TechnologyStackBase
    {

    }

    public static class TechnologyHistory extends TechnologyBase
    {
        public Long technologyId = null;
        public String operation = null;

        public Long getTechnologyId() { return technologyId; }
        public void setTechnologyId(Long value) { this.technologyId = value; }
        public String getOperation() { return operation; }
        public void setOperation(String value) { this.operation = value; }
    }

    public static class QueryBase_1<T> extends QueryBase
    {

    }

    public static class TechStackDetails extends TechnologyStackBase
    {
        public String detailsHtml = null;
        public ArrayList<TechnologyInStack> technologyChoices = null;

        public String getDetailsHtml() { return detailsHtml; }
        public void setDetailsHtml(String value) { this.detailsHtml = value; }
        public ArrayList<TechnologyInStack> getTechnologyChoices() { return technologyChoices; }
        public void setTechnologyChoices(ArrayList<TechnologyInStack> value) { this.technologyChoices = value; }
    }

    public static class TechnologyStackHistory extends TechnologyStackBase
    {
        public Long technologyStackId = null;
        public String operation = null;
        public ArrayList<Long> technologyIds = null;

        public Long getTechnologyStackId() { return technologyStackId; }
        public void setTechnologyStackId(Long value) { this.technologyStackId = value; }
        public String getOperation() { return operation; }
        public void setOperation(String value) { this.operation = value; }
        public ArrayList<Long> getTechnologyIds() { return technologyIds; }
        public void setTechnologyIds(ArrayList<Long> value) { this.technologyIds = value; }
    }

    @DataContract
    public static class Option
    {
        @DataMember(Name="name")
        public String name = null;

        @DataMember(Name="title")
        public String title = null;

        @DataMember(Name="value")
        public TechnologyTier value = null;

        public String getName() { return name; }
        public void setName(String value) { this.name = value; }
        public String getTitle() { return title; }
        public void setTitle(String value) { this.title = value; }
        public TechnologyTier getValue() { return value; }
        public void setValue(TechnologyTier value) { this.value = value; }
    }

    public static class UserInfo
    {
        public String userName = null;
        public String avatarUrl = null;
        public Integer stacksCount = null;

        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
        public String getAvatarUrl() { return avatarUrl; }
        public void setAvatarUrl(String value) { this.avatarUrl = value; }
        public Integer getStacksCount() { return stacksCount; }
        public void setStacksCount(Integer value) { this.stacksCount = value; }
    }

    public static class TechnologyInfo
    {
        public TechnologyTier tier = null;
        public String slug = null;
        public String name = null;
        public String logoUrl = null;
        public Integer stacksCount = null;

        public TechnologyTier getTier() { return tier; }
        public void setTier(TechnologyTier value) { this.tier = value; }
        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
        public String getName() { return name; }
        public void setName(String value) { this.name = value; }
        public String getLogoUrl() { return logoUrl; }
        public void setLogoUrl(String value) { this.logoUrl = value; }
        public Integer getStacksCount() { return stacksCount; }
        public void setStacksCount(Integer value) { this.stacksCount = value; }
    }

    public static class Post
    {
        public Integer id = null;
        public String userId = null;
        public String userName = null;
        public String date = null;
        public String shortDate = null;
        public String textHtml = null;
        public ArrayList<PostComment> comments = null;

        public Integer getId() { return id; }
        public void setId(Integer value) { this.id = value; }
        public String getUserId() { return userId; }
        public void setUserId(String value) { this.userId = value; }
        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
        public String getDate() { return date; }
        public void setDate(String value) { this.date = value; }
        public String getShortDate() { return shortDate; }
        public void setShortDate(String value) { this.shortDate = value; }
        public String getTextHtml() { return textHtml; }
        public void setTextHtml(String value) { this.textHtml = value; }
        public ArrayList<PostComment> getComments() { return comments; }
        public void setComments(ArrayList<PostComment> value) { this.comments = value; }
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

        public Long getId() { return id; }
        public void setId(Long value) { this.id = value; }
        public String getName() { return name; }
        public void setName(String value) { this.name = value; }
        public String getVendorName() { return vendorName; }
        public void setVendorName(String value) { this.vendorName = value; }
        public String getVendorUrl() { return vendorUrl; }
        public void setVendorUrl(String value) { this.vendorUrl = value; }
        public String getProductUrl() { return productUrl; }
        public void setProductUrl(String value) { this.productUrl = value; }
        public String getLogoUrl() { return logoUrl; }
        public void setLogoUrl(String value) { this.logoUrl = value; }
        public String getDescription() { return description; }
        public void setDescription(String value) { this.description = value; }
        public Date getCreated() { return created; }
        public void setCreated(Date value) { this.created = value; }
        public String getCreatedBy() { return createdBy; }
        public void setCreatedBy(String value) { this.createdBy = value; }
        public Date getLastModified() { return lastModified; }
        public void setLastModified(Date value) { this.lastModified = value; }
        public String getLastModifiedBy() { return lastModifiedBy; }
        public void setLastModifiedBy(String value) { this.lastModifiedBy = value; }
        public String getOwnerId() { return ownerId; }
        public void setOwnerId(String value) { this.ownerId = value; }
        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
        public Boolean isLogoApproved() { return logoApproved; }
        public void setLogoApproved(Boolean value) { this.logoApproved = value; }
        public Boolean getIsLocked() { return isLocked; }
        public void setIsLocked(Boolean value) { this.isLocked = value; }
        public TechnologyTier getTier() { return tier; }
        public void setTier(TechnologyTier value) { this.tier = value; }
        public Date getLastStatusUpdate() { return lastStatusUpdate; }
        public void setLastStatusUpdate(Date value) { this.lastStatusUpdate = value; }
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
        public void setErrorCode(String value) { this.errorCode = value; }
        public String getFieldName() { return fieldName; }
        public void setFieldName(String value) { this.fieldName = value; }
        public String getMessage() { return message; }
        public void setMessage(String value) { this.message = value; }
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
        public String details = null;
        public Date lastStatusUpdate = null;

        public Long getId() { return id; }
        public void setId(Long value) { this.id = value; }
        public String getName() { return name; }
        public void setName(String value) { this.name = value; }
        public String getVendorName() { return vendorName; }
        public void setVendorName(String value) { this.vendorName = value; }
        public String getDescription() { return description; }
        public void setDescription(String value) { this.description = value; }
        public String getAppUrl() { return appUrl; }
        public void setAppUrl(String value) { this.appUrl = value; }
        public String getScreenshotUrl() { return screenshotUrl; }
        public void setScreenshotUrl(String value) { this.screenshotUrl = value; }
        public Date getCreated() { return created; }
        public void setCreated(Date value) { this.created = value; }
        public String getCreatedBy() { return createdBy; }
        public void setCreatedBy(String value) { this.createdBy = value; }
        public Date getLastModified() { return lastModified; }
        public void setLastModified(Date value) { this.lastModified = value; }
        public String getLastModifiedBy() { return lastModifiedBy; }
        public void setLastModifiedBy(String value) { this.lastModifiedBy = value; }
        public Boolean getIsLocked() { return isLocked; }
        public void setIsLocked(Boolean value) { this.isLocked = value; }
        public String getOwnerId() { return ownerId; }
        public void setOwnerId(String value) { this.ownerId = value; }
        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
        public String getDetails() { return details; }
        public void setDetails(String value) { this.details = value; }
        public Date getLastStatusUpdate() { return lastStatusUpdate; }
        public void setLastStatusUpdate(Date value) { this.lastStatusUpdate = value; }
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
        public void setSkip(Integer value) { this.skip = value; }
        public Integer getTake() { return take; }
        public void setTake(Integer value) { this.take = value; }
        public String getOrderBy() { return orderBy; }
        public void setOrderBy(String value) { this.orderBy = value; }
        public String getOrderByDesc() { return orderByDesc; }
        public void setOrderByDesc(String value) { this.orderByDesc = value; }
    }

    public static class TechnologyInStack extends TechnologyBase
    {
        public Long technologyId = null;
        public Long technologyStackId = null;
        public String justification = null;

        public Long getTechnologyId() { return technologyId; }
        public void setTechnologyId(Long value) { this.technologyId = value; }
        public Long getTechnologyStackId() { return technologyStackId; }
        public void setTechnologyStackId(Long value) { this.technologyStackId = value; }
        public String getJustification() { return justification; }
        public void setJustification(String value) { this.justification = value; }
    }

    public static class PostComment
    {
        public Integer id = null;
        public Integer postId = null;
        public String userId = null;
        public String userName = null;
        public String date = null;
        public String shortDate = null;
        public String textHtml = null;

        public Integer getId() { return id; }
        public void setId(Integer value) { this.id = value; }
        public Integer getPostId() { return postId; }
        public void setPostId(Integer value) { this.postId = value; }
        public String getUserId() { return userId; }
        public void setUserId(String value) { this.userId = value; }
        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
        public String getDate() { return date; }
        public void setDate(String value) { this.date = value; }
        public String getShortDate() { return shortDate; }
        public void setShortDate(String value) { this.shortDate = value; }
        public String getTextHtml() { return textHtml; }
        public void setTextHtml(String value) { this.textHtml = value; }
    }

    public static class LogoUrlApprovalResponse
    {
        public Technology result = null;

        public Technology getResult() { return result; }
        public void setResult(Technology value) { this.result = value; }
    }

    public static class LockStackResponse
    {

    }

    public static class CreateTechnologyResponse
    {
        public Technology result = null;
        public ResponseStatus responseStatus = null;

        public Technology getResult() { return result; }
        public void setResult(Technology value) { this.result = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class UpdateTechnologyResponse
    {
        public Technology result = null;
        public ResponseStatus responseStatus = null;

        public Technology getResult() { return result; }
        public void setResult(Technology value) { this.result = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class DeleteTechnologyResponse
    {
        public Technology result = null;
        public ResponseStatus responseStatus = null;

        public Technology getResult() { return result; }
        public void setResult(Technology value) { this.result = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class GetTechnologyResponse
    {
        public Date created = null;
        public Technology technology = null;
        public ArrayList<TechnologyStack> technologyStacks = null;
        public ResponseStatus responseStatus = null;

        public Date getCreated() { return created; }
        public void setCreated(Date value) { this.created = value; }
        public Technology getTechnology() { return technology; }
        public void setTechnology(Technology value) { this.technology = value; }
        public ArrayList<TechnologyStack> getTechnologyStacks() { return technologyStacks; }
        public void setTechnologyStacks(ArrayList<TechnologyStack> value) { this.technologyStacks = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class GetTechnologyPreviousVersionsResponse
    {
        public ArrayList<TechnologyHistory> results = null;

        public ArrayList<TechnologyHistory> getResults() { return results; }
        public void setResults(ArrayList<TechnologyHistory> value) { this.results = value; }
    }

    public static class GetTechnologyFavoriteDetailsResponse
    {
        public ArrayList<String> users = null;
        public Integer favoriteCount = null;

        public ArrayList<String> getUsers() { return users; }
        public void setUsers(ArrayList<String> value) { this.users = value; }
        public Integer getFavoriteCount() { return favoriteCount; }
        public void setFavoriteCount(Integer value) { this.favoriteCount = value; }
    }

    public static class GetAllTechnologiesResponse
    {
        public ArrayList<Technology> results = null;

        public ArrayList<Technology> getResults() { return results; }
        public void setResults(ArrayList<Technology> value) { this.results = value; }
    }

    @DataContract
    public static class QueryResponse<Technology>
    {
        @DataMember(Order=1)
        public Integer offset = null;

        @DataMember(Order=2)
        public Integer total = null;

        @DataMember(Order=3)
        public ArrayList<Technology> results = null;

        @DataMember(Order=4)
        public HashMap<String,String> meta = null;

        @DataMember(Order=5)
        public ResponseStatus responseStatus = null;

        public Integer getOffset() { return offset; }
        public void setOffset(Integer value) { this.offset = value; }
        public Integer getTotal() { return total; }
        public void setTotal(Integer value) { this.total = value; }
        public ArrayList<Technology> getResults() { return results; }
        public void setResults(ArrayList<Technology> value) { this.results = value; }
        public HashMap<String,String> getMeta() { return meta; }
        public void setMeta(HashMap<String,String> value) { this.meta = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class CreateTechnologyStackResponse
    {
        public TechStackDetails result = null;
        public ResponseStatus responseStatus = null;

        public TechStackDetails getResult() { return result; }
        public void setResult(TechStackDetails value) { this.result = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class UpdateTechnologyStackResponse
    {
        public TechStackDetails result = null;
        public ResponseStatus responseStatus = null;

        public TechStackDetails getResult() { return result; }
        public void setResult(TechStackDetails value) { this.result = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class DeleteTechnologyStackResponse
    {
        public TechStackDetails result = null;
        public ResponseStatus responseStatus = null;

        public TechStackDetails getResult() { return result; }
        public void setResult(TechStackDetails value) { this.result = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class GetAllTechnologyStacksResponse
    {
        public ArrayList<TechnologyStack> results = null;

        public ArrayList<TechnologyStack> getResults() { return results; }
        public void setResults(ArrayList<TechnologyStack> value) { this.results = value; }
    }

    public static class GetTechnologyStackResponse
    {
        public Date created = null;
        public TechStackDetails result = null;
        public ResponseStatus responseStatus = null;

        public Date getCreated() { return created; }
        public void setCreated(Date value) { this.created = value; }
        public TechStackDetails getResult() { return result; }
        public void setResult(TechStackDetails value) { this.result = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class GetTechnologyStackPreviousVersionsResponse
    {
        public ArrayList<TechnologyStackHistory> results = null;

        public ArrayList<TechnologyStackHistory> getResults() { return results; }
        public void setResults(ArrayList<TechnologyStackHistory> value) { this.results = value; }
    }

    public static class GetTechnologyStackFavoriteDetailsResponse
    {
        public ArrayList<String> users = null;
        public Integer favoriteCount = null;

        public ArrayList<String> getUsers() { return users; }
        public void setUsers(ArrayList<String> value) { this.users = value; }
        public Integer getFavoriteCount() { return favoriteCount; }
        public void setFavoriteCount(Integer value) { this.favoriteCount = value; }
    }

    public static class GetConfigResponse
    {
        public ArrayList<Option> allTiers = null;

        public ArrayList<Option> getAllTiers() { return allTiers; }
        public void setAllTiers(ArrayList<Option> value) { this.allTiers = value; }
    }

    public static class OverviewResponse
    {
        public Date created = null;
        public ArrayList<UserInfo> topUsers = null;
        public ArrayList<TechnologyInfo> topTechnologies = null;
        public ArrayList<TechStackDetails> latestTechStacks = null;
        public HashMap<TechnologyTier,ArrayList<TechnologyInfo>> topTechnologiesByTier = null;
        public ResponseStatus responseStatus = null;

        public Date getCreated() { return created; }
        public void setCreated(Date value) { this.created = value; }
        public ArrayList<UserInfo> getTopUsers() { return topUsers; }
        public void setTopUsers(ArrayList<UserInfo> value) { this.topUsers = value; }
        public ArrayList<TechnologyInfo> getTopTechnologies() { return topTechnologies; }
        public void setTopTechnologies(ArrayList<TechnologyInfo> value) { this.topTechnologies = value; }
        public ArrayList<TechStackDetails> getLatestTechStacks() { return latestTechStacks; }
        public void setLatestTechStacks(ArrayList<TechStackDetails> value) { this.latestTechStacks = value; }
        public HashMap<TechnologyTier,ArrayList<TechnologyInfo>> getTopTechnologiesByTier() { return topTechnologiesByTier; }
        public void setTopTechnologiesByTier(HashMap<TechnologyTier,ArrayList<TechnologyInfo>> value) { this.topTechnologiesByTier = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class AppOverviewResponse
    {
        public Date created = null;
        public ArrayList<Option> allTiers = null;
        public ArrayList<TechnologyInfo> topTechnologies = null;
        public ResponseStatus responseStatus = null;

        public Date getCreated() { return created; }
        public void setCreated(Date value) { this.created = value; }
        public ArrayList<Option> getAllTiers() { return allTiers; }
        public void setAllTiers(ArrayList<Option> value) { this.allTiers = value; }
        public ArrayList<TechnologyInfo> getTopTechnologies() { return topTechnologies; }
        public void setTopTechnologies(ArrayList<TechnologyInfo> value) { this.topTechnologies = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class GetFavoriteTechStackResponse
    {
        public ArrayList<TechnologyStack> results = null;

        public ArrayList<TechnologyStack> getResults() { return results; }
        public void setResults(ArrayList<TechnologyStack> value) { this.results = value; }
    }

    public static class FavoriteTechStackResponse
    {
        public TechnologyStack result = null;

        public TechnologyStack getResult() { return result; }
        public void setResult(TechnologyStack value) { this.result = value; }
    }

    public static class GetFavoriteTechnologiesResponse
    {
        public ArrayList<Technology> results = null;

        public ArrayList<Technology> getResults() { return results; }
        public void setResults(ArrayList<Technology> value) { this.results = value; }
    }

    public static class FavoriteTechnologyResponse
    {
        public Technology result = null;

        public Technology getResult() { return result; }
        public void setResult(Technology value) { this.result = value; }
    }

    public static class GetUserFeedResponse
    {
        public ArrayList<TechStackDetails> results = null;

        public ArrayList<TechStackDetails> getResults() { return results; }
        public void setResults(ArrayList<TechStackDetails> value) { this.results = value; }
    }

    public static class GetUserInfoResponse
    {
        public String userName = null;
        public Date created = null;
        public String avatarUrl = null;
        public ArrayList<TechnologyStack> techStacks = null;
        public ArrayList<TechnologyStack> favoriteTechStacks = null;
        public ArrayList<Technology> favoriteTechnologies = null;

        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
        public Date getCreated() { return created; }
        public void setCreated(Date value) { this.created = value; }
        public String getAvatarUrl() { return avatarUrl; }
        public void setAvatarUrl(String value) { this.avatarUrl = value; }
        public ArrayList<TechnologyStack> getTechStacks() { return techStacks; }
        public void setTechStacks(ArrayList<TechnologyStack> value) { this.techStacks = value; }
        public ArrayList<TechnologyStack> getFavoriteTechStacks() { return favoriteTechStacks; }
        public void setFavoriteTechStacks(ArrayList<TechnologyStack> value) { this.favoriteTechStacks = value; }
        public ArrayList<Technology> getFavoriteTechnologies() { return favoriteTechnologies; }
        public void setFavoriteTechnologies(ArrayList<Technology> value) { this.favoriteTechnologies = value; }
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
        public ResponseStatus responseStatus = null;

        @DataMember(Order=7)
        public HashMap<String,String> meta = null;

        public String getUserId() { return userId; }
        public void setUserId(String value) { this.userId = value; }
        public String getSessionId() { return sessionId; }
        public void setSessionId(String value) { this.sessionId = value; }
        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
        public String getDisplayName() { return displayName; }
        public void setDisplayName(String value) { this.displayName = value; }
        public String getReferrerUrl() { return referrerUrl; }
        public void setReferrerUrl(String value) { this.referrerUrl = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
        public HashMap<String,String> getMeta() { return meta; }
        public void setMeta(HashMap<String,String> value) { this.meta = value; }
    }

    public static class AssignRolesResponse
    {
        public ArrayList<String> allRoles = null;
        public ArrayList<String> allPermissions = null;
        public ResponseStatus responseStatus = null;

        public ArrayList<String> getAllRoles() { return allRoles; }
        public void setAllRoles(ArrayList<String> value) { this.allRoles = value; }
        public ArrayList<String> getAllPermissions() { return allPermissions; }
        public void setAllPermissions(ArrayList<String> value) { this.allPermissions = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    public static class UnAssignRolesResponse
    {
        public ArrayList<String> allRoles = null;
        public ArrayList<String> allPermissions = null;
        public ResponseStatus responseStatus = null;

        public ArrayList<String> getAllRoles() { return allRoles; }
        public void setAllRoles(ArrayList<String> value) { this.allRoles = value; }
        public ArrayList<String> getAllPermissions() { return allPermissions; }
        public void setAllPermissions(ArrayList<String> value) { this.allPermissions = value; }
        public ResponseStatus getResponseStatus() { return responseStatus; }
        public void setResponseStatus(ResponseStatus value) { this.responseStatus = value; }
    }

    @Route("/admin/technology/{TechnologyId}/logo")
    public static class LogoUrlApproval implements IReturn<LogoUrlApprovalResponse>
    {
        public Long technologyId = null;
        public Boolean approved = null;

        public Long getTechnologyId() { return technologyId; }
        public void setTechnologyId(Long value) { this.technologyId = value; }
        public Boolean isApproved() { return approved; }
        public void setApproved(Boolean value) { this.approved = value; }
    }

    @Route("/admin/techstacks/{TechnologyStackId}/lock")
    public static class LockTechStack implements IReturn<LockStackResponse>
    {
        public Long technologyStackId = null;
        public Boolean isLocked = null;

        public Long getTechnologyStackId() { return technologyStackId; }
        public void setTechnologyStackId(Long value) { this.technologyStackId = value; }
        public Boolean getIsLocked() { return isLocked; }
        public void setIsLocked(Boolean value) { this.isLocked = value; }
    }

    @Route("/admin/technology/{TechnologyId}/lock")
    public static class LockTech implements IReturn<LockStackResponse>
    {
        public Long technologyId = null;
        public Boolean isLocked = null;

        public Long getTechnologyId() { return technologyId; }
        public void setTechnologyId(Long value) { this.technologyId = value; }
        public Boolean getIsLocked() { return isLocked; }
        public void setIsLocked(Boolean value) { this.isLocked = value; }
    }

    @Route("/ping")
    public static class Ping
    {

    }

    @Route("/{PathInfo*}")
    public static class FallbackForClientRoutes
    {
        public String pathInfo = null;

        public String getPathInfo() { return pathInfo; }
        public void setPathInfo(String value) { this.pathInfo = value; }
    }

    @Route("/stacks")
    public static class ClientAllTechnologyStacks
    {

    }

    @Route("/tech")
    public static class ClientAllTechnologies
    {

    }

    @Route("/tech/{Slug}")
    public static class ClientTechnology
    {
        public String slug = null;

        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
    }

    @Route("/users/{UserName}")
    public static class ClientUser
    {
        public String userName = null;

        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
    }

    @Route("/my-session")
    public static class SessionInfo
    {

    }

    @Route(Path="/technology", Verbs="POST")
    public static class CreateTechnology implements IReturn<CreateTechnologyResponse>
    {
        public String name = null;
        public String vendorName = null;
        public String vendorUrl = null;
        public String productUrl = null;
        public String logoUrl = null;
        public String description = null;
        public Boolean isLocked = null;
        public TechnologyTier tier = null;

        public String getName() { return name; }
        public void setName(String value) { this.name = value; }
        public String getVendorName() { return vendorName; }
        public void setVendorName(String value) { this.vendorName = value; }
        public String getVendorUrl() { return vendorUrl; }
        public void setVendorUrl(String value) { this.vendorUrl = value; }
        public String getProductUrl() { return productUrl; }
        public void setProductUrl(String value) { this.productUrl = value; }
        public String getLogoUrl() { return logoUrl; }
        public void setLogoUrl(String value) { this.logoUrl = value; }
        public String getDescription() { return description; }
        public void setDescription(String value) { this.description = value; }
        public Boolean getIsLocked() { return isLocked; }
        public void setIsLocked(Boolean value) { this.isLocked = value; }
        public TechnologyTier getTier() { return tier; }
        public void setTier(TechnologyTier value) { this.tier = value; }
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
        public void setId(Long value) { this.id = value; }
        public String getName() { return name; }
        public void setName(String value) { this.name = value; }
        public String getVendorName() { return vendorName; }
        public void setVendorName(String value) { this.vendorName = value; }
        public String getVendorUrl() { return vendorUrl; }
        public void setVendorUrl(String value) { this.vendorUrl = value; }
        public String getProductUrl() { return productUrl; }
        public void setProductUrl(String value) { this.productUrl = value; }
        public String getLogoUrl() { return logoUrl; }
        public void setLogoUrl(String value) { this.logoUrl = value; }
        public String getDescription() { return description; }
        public void setDescription(String value) { this.description = value; }
        public Boolean getIsLocked() { return isLocked; }
        public void setIsLocked(Boolean value) { this.isLocked = value; }
        public TechnologyTier getTier() { return tier; }
        public void setTier(TechnologyTier value) { this.tier = value; }
    }

    @Route(Path="/technology/{Id}", Verbs="DELETE")
    public static class DeleteTechnology implements IReturn<DeleteTechnologyResponse>
    {
        public Long id = null;

        public Long getId() { return id; }
        public void setId(Long value) { this.id = value; }
    }

    @Route("/technology/{Slug}")
    public static class GetTechnology implements IReturn<GetTechnologyResponse>
    {
        public Boolean reload = null;
        public String slug = null;

        public Boolean isReload() { return reload; }
        public void setReload(Boolean value) { this.reload = value; }
        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
    }

    @Route(Path="/technology/{Slug}/previous-versions", Verbs="GET")
    public static class GetTechnologyPreviousVersions implements IReturn<GetTechnologyPreviousVersionsResponse>
    {
        public String slug = null;

        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
    }

    @Route("/technology/{Slug}/favorites")
    public static class GetTechnologyFavoriteDetails implements IReturn<GetTechnologyFavoriteDetailsResponse>
    {
        public String slug = null;
        public Boolean reload = null;

        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
        public Boolean isReload() { return reload; }
        public void setReload(Boolean value) { this.reload = value; }
    }

    @Route(Path="/technology", Verbs="GET")
    public static class GetAllTechnologies implements IReturn<GetAllTechnologiesResponse>
    {

    }

    @Route("/technology/search")
    @AutoQueryViewer(Title="Find Technologies", Description="Explore different Technologies", IconUrl="/img/app/tech-white-75.png", DefaultSearchField="Tier", DefaultSearchType="=", DefaultSearchText="Data")
    public static class FindTechnologies extends QueryBase_1<Technology> implements IReturn<QueryResponse<Technology>>
    {
        public Boolean reload = null;

        public Boolean isReload() { return reload; }
        public void setReload(Boolean value) { this.reload = value; }
    }

    @Route(Path="/techstacks", Verbs="POST")
    public static class CreateTechnologyStack implements IReturn<CreateTechnologyStackResponse>
    {
        public String name = null;
        public String vendorName = null;
        public String appUrl = null;
        public String screenshotUrl = null;
        public String description = null;
        public String details = null;
        public Boolean isLocked = null;
        public ArrayList<Long> technologyIds = null;

        public String getName() { return name; }
        public void setName(String value) { this.name = value; }
        public String getVendorName() { return vendorName; }
        public void setVendorName(String value) { this.vendorName = value; }
        public String getAppUrl() { return appUrl; }
        public void setAppUrl(String value) { this.appUrl = value; }
        public String getScreenshotUrl() { return screenshotUrl; }
        public void setScreenshotUrl(String value) { this.screenshotUrl = value; }
        public String getDescription() { return description; }
        public void setDescription(String value) { this.description = value; }
        public String getDetails() { return details; }
        public void setDetails(String value) { this.details = value; }
        public Boolean getIsLocked() { return isLocked; }
        public void setIsLocked(Boolean value) { this.isLocked = value; }
        public ArrayList<Long> getTechnologyIds() { return technologyIds; }
        public void setTechnologyIds(ArrayList<Long> value) { this.technologyIds = value; }
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
        public void setId(Long value) { this.id = value; }
        public String getName() { return name; }
        public void setName(String value) { this.name = value; }
        public String getVendorName() { return vendorName; }
        public void setVendorName(String value) { this.vendorName = value; }
        public String getAppUrl() { return appUrl; }
        public void setAppUrl(String value) { this.appUrl = value; }
        public String getScreenshotUrl() { return screenshotUrl; }
        public void setScreenshotUrl(String value) { this.screenshotUrl = value; }
        public String getDescription() { return description; }
        public void setDescription(String value) { this.description = value; }
        public String getDetails() { return details; }
        public void setDetails(String value) { this.details = value; }
        public Boolean getIsLocked() { return isLocked; }
        public void setIsLocked(Boolean value) { this.isLocked = value; }
        public ArrayList<Long> getTechnologyIds() { return technologyIds; }
        public void setTechnologyIds(ArrayList<Long> value) { this.technologyIds = value; }
    }

    @Route(Path="/techstacks/{Id}", Verbs="DELETE")
    public static class DeleteTechnologyStack implements IReturn<DeleteTechnologyStackResponse>
    {
        public Long id = null;

        public Long getId() { return id; }
        public void setId(Long value) { this.id = value; }
    }

    @Route(Path="/techstacks", Verbs="GET")
    public static class GetAllTechnologyStacks implements IReturn<GetAllTechnologyStacksResponse>
    {

    }

    @Route(Path="/techstacks/{Slug}", Verbs="GET")
    public static class GetTechnologyStack implements IReturn<GetTechnologyStackResponse>
    {
        public Boolean reload = null;
        public String slug = null;

        public Boolean isReload() { return reload; }
        public void setReload(Boolean value) { this.reload = value; }
        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
    }

    @Route(Path="/techstacks/{Slug}/previous-versions", Verbs="GET")
    public static class GetTechnologyStackPreviousVersions implements IReturn<GetTechnologyStackPreviousVersionsResponse>
    {
        public String slug = null;

        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
    }

    @Route("/techstacks/{Slug}/favorites")
    public static class GetTechnologyStackFavoriteDetails implements IReturn<GetTechnologyStackFavoriteDetailsResponse>
    {
        public String slug = null;
        public Boolean reload = null;

        public String getSlug() { return slug; }
        public void setSlug(String value) { this.slug = value; }
        public Boolean isReload() { return reload; }
        public void setReload(Boolean value) { this.reload = value; }
    }

    @Route("/config")
    public static class GetConfig implements IReturn<GetConfigResponse>
    {

    }

    @Route("/overview")
    public static class Overview implements IReturn<OverviewResponse>
    {
        public Boolean reload = null;

        public Boolean isReload() { return reload; }
        public void setReload(Boolean value) { this.reload = value; }
    }

    @Route("/app-overview")
    public static class AppOverview implements IReturn<AppOverviewResponse>
    {
        public Boolean reload = null;

        public Boolean isReload() { return reload; }
        public void setReload(Boolean value) { this.reload = value; }
    }

    @Route("/techstacks/search")
    @AutoQueryViewer(Title="Find Technology Stacks", Description="Explore different Technology Stacks", IconUrl="/img/app/stacks-white-75.png", DefaultSearchField="Description", DefaultSearchType="Contains", DefaultSearchText="ServiceStack")
    public static class FindTechStacks extends QueryBase_1<TechnologyStack> implements IReturn<QueryResponse<TechnologyStack>>
    {
        public Boolean reload = null;

        public Boolean isReload() { return reload; }
        public void setReload(Boolean value) { this.reload = value; }
    }

    @Route(Path="/favorites/techtacks", Verbs="GET")
    public static class GetFavoriteTechStack implements IReturn<GetFavoriteTechStackResponse>
    {
        public Integer technologyStackId = null;

        public Integer getTechnologyStackId() { return technologyStackId; }
        public void setTechnologyStackId(Integer value) { this.technologyStackId = value; }
    }

    @Route(Path="/favorites/techtacks/{TechnologyStackId}", Verbs="PUT")
    public static class AddFavoriteTechStack implements IReturn<FavoriteTechStackResponse>
    {
        public Integer technologyStackId = null;

        public Integer getTechnologyStackId() { return technologyStackId; }
        public void setTechnologyStackId(Integer value) { this.technologyStackId = value; }
    }

    @Route(Path="/favorites/techtacks/{TechnologyStackId}", Verbs="DELETE")
    public static class RemoveFavoriteTechStack implements IReturn<FavoriteTechStackResponse>
    {
        public Integer technologyStackId = null;

        public Integer getTechnologyStackId() { return technologyStackId; }
        public void setTechnologyStackId(Integer value) { this.technologyStackId = value; }
    }

    @Route(Path="/favorites/technology", Verbs="GET")
    public static class GetFavoriteTechnologies implements IReturn<GetFavoriteTechnologiesResponse>
    {
        public Integer technologyId = null;

        public Integer getTechnologyId() { return technologyId; }
        public void setTechnologyId(Integer value) { this.technologyId = value; }
    }

    @Route(Path="/favorites/technology/{TechnologyId}", Verbs="PUT")
    public static class AddFavoriteTechnology implements IReturn<FavoriteTechnologyResponse>
    {
        public Integer technologyId = null;

        public Integer getTechnologyId() { return technologyId; }
        public void setTechnologyId(Integer value) { this.technologyId = value; }
    }

    @Route(Path="/favorites/technology/{TechnologyId}", Verbs="DELETE")
    public static class RemoveFavoriteTechnology implements IReturn<FavoriteTechnologyResponse>
    {
        public Integer technologyId = null;

        public Integer getTechnologyId() { return technologyId; }
        public void setTechnologyId(Integer value) { this.technologyId = value; }
    }

    @Route("/my-feed")
    public static class GetUserFeed implements IReturn<GetUserFeedResponse>
    {

    }

    @Route("/userinfo/{UserName}")
    public static class GetUserInfo implements IReturn<GetUserInfoResponse>
    {
        public Boolean reload = null;
        public String userName = null;

        public Boolean isReload() { return reload; }
        public void setReload(Boolean value) { this.reload = value; }
        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
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
        public String Continue = null;

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
        public HashMap<String,String> meta = null;

        public String getProvider() { return provider; }
        public void setProvider(String value) { this.provider = value; }
        public String getState() { return state; }
        public void setState(String value) { this.state = value; }
        public String getOauth_token() { return oauth_token; }
        public void setOauth_token(String value) { this.oauth_token = value; }
        public String getOauth_verifier() { return oauth_verifier; }
        public void setOauth_verifier(String value) { this.oauth_verifier = value; }
        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
        public String getPassword() { return password; }
        public void setPassword(String value) { this.password = value; }
        public Boolean isRememberMe() { return rememberMe; }
        public void setRememberMe(Boolean value) { this.rememberMe = value; }
        public String getContinue() { return Continue; }
        public void setContinue(String value) { this.Continue = value; }
        public String getNonce() { return nonce; }
        public void setNonce(String value) { this.nonce = value; }
        public String getUri() { return uri; }
        public void setUri(String value) { this.uri = value; }
        public String getResponse() { return response; }
        public void setResponse(String value) { this.response = value; }
        public String getQop() { return qop; }
        public void setQop(String value) { this.qop = value; }
        public String getNc() { return nc; }
        public void setNc(String value) { this.nc = value; }
        public String getCnonce() { return cnonce; }
        public void setCnonce(String value) { this.cnonce = value; }
        public HashMap<String,String> getMeta() { return meta; }
        public void setMeta(HashMap<String,String> value) { this.meta = value; }
    }

    @Route("/assignroles")
    public static class AssignRoles implements IReturn<AssignRolesResponse>
    {
        public String userName = null;
        public ArrayList<String> permissions = null;
        public ArrayList<String> roles = null;

        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
        public ArrayList<String> getPermissions() { return permissions; }
        public void setPermissions(ArrayList<String> value) { this.permissions = value; }
        public ArrayList<String> getRoles() { return roles; }
        public void setRoles(ArrayList<String> value) { this.roles = value; }
    }

    @Route("/unassignroles")
    public static class UnAssignRoles implements IReturn<UnAssignRolesResponse>
    {
        public String userName = null;
        public ArrayList<String> permissions = null;
        public ArrayList<String> roles = null;

        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }
        public ArrayList<String> getPermissions() { return permissions; }
        public void setPermissions(ArrayList<String> value) { this.permissions = value; }
        public ArrayList<String> getRoles() { return roles; }
        public void setRoles(ArrayList<String> value) { this.roles = value; }
    }

    @Route("/posts")
    public static class QueryPosts extends QueryBase_1<Post> implements IReturn<QueryResponse<Post>>
    {

    }

}
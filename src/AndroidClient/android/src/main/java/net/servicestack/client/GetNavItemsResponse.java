package net.servicestack.client;

import java.util.ArrayList;
import java.util.HashMap;

@DataContract
public class GetNavItemsResponse
{
    @DataMember(Order=1)
    public String baseUrl = null;

    @DataMember(Order=2)
    public ArrayList<NavItem> results = null;

    @DataMember(Order=3)
    public HashMap<String,ArrayList<NavItem>> navItemsMap = null;

    @DataMember(Order=4)
    public HashMap<String,String> meta = null;

    @DataMember(Order=5)
    public ResponseStatus responseStatus = null;

    public String getBaseUrl() { return baseUrl; }
    public GetNavItemsResponse setBaseUrl(String value) { this.baseUrl = value; return this; }
    public ArrayList<NavItem> getResults() { return results; }
    public GetNavItemsResponse setResults(ArrayList<NavItem> value) { this.results = value; return this; }
    public HashMap<String,ArrayList<NavItem>> getNavItemsMap() { return navItemsMap; }
    public GetNavItemsResponse setNavItemsMap(HashMap<String,ArrayList<NavItem>> value) { this.navItemsMap = value; return this; }
    public HashMap<String,String> getMeta() { return meta; }
    public GetNavItemsResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    public ResponseStatus getResponseStatus() { return responseStatus; }
    public GetNavItemsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
}
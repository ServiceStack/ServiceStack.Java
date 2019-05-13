package net.servicestack;

import net.servicestack.client.DataContract;
import net.servicestack.client.DataMember;
import net.servicestack.client.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;

@DataContract
public class GetNavItemsResponse
{
    @DataMember(Order=1)
    public ArrayList<NavItem> results = null;

    @DataMember(Order=2)
    public HashMap<String,ArrayList<NavItem>> navItemsMap = null;

    @DataMember(Order=3)
    public HashMap<String,String> meta = null;

    @DataMember(Order=4)
    public ResponseStatus responseStatus = null;

    public ArrayList<NavItem> getResults() { return results; }
    public GetNavItemsResponse setResults(ArrayList<NavItem> value) { this.results = value; return this; }
    public HashMap<String,ArrayList<NavItem>> getNavItemsMap() { return navItemsMap; }
    public GetNavItemsResponse setNavItemsMap(HashMap<String,ArrayList<NavItem>> value) { this.navItemsMap = value; return this; }
    public HashMap<String,String> getMeta() { return meta; }
    public GetNavItemsResponse setMeta(HashMap<String,String> value) { this.meta = value; return this; }
    public ResponseStatus getResponseStatus() { return responseStatus; }
    public GetNavItemsResponse setResponseStatus(ResponseStatus value) { this.responseStatus = value; return this; }
}
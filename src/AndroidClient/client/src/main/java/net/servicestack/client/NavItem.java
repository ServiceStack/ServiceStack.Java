package net.servicestack.client;

import java.util.ArrayList;
import java.util.HashMap;

public class NavItem
{
    public String label = null;
    public String href = null;
    public Boolean exact = null;
    public String id = null;
    public String className = null;
    public String iconHtml = null;
    public String show = null;
    public String hide = null;
    public ArrayList<NavItem> children = null;
    public HashMap<String,String> meta = null;

    public String getLabel() { return label; }
    public NavItem setLabel(String value) { this.label = value; return this; }
    public String getHref() { return href; }
    public NavItem setHref(String value) { this.href = value; return this; }
    public Boolean isExact() { return exact; }
    public NavItem setExact(Boolean value) { this.exact = value; return this; }
    public String getId() { return id; }
    public NavItem setId(String value) { this.id = value; return this; }
    public String getClassName() { return className; }
    public NavItem setClassName(String value) { this.className = value; return this; }
    public String getIconHtml() { return iconHtml; }
    public NavItem setIconHtml(String value) { this.iconHtml = value; return this; }
    public String getShow() { return show; }
    public NavItem setShow(String value) { this.show = value; return this; }
    public String getHide() { return hide; }
    public NavItem setHide(String value) { this.hide = value; return this; }
    public ArrayList<NavItem> getChildren() { return children; }
    public NavItem setChildren(ArrayList<NavItem> value) { this.children = value; return this; }
    public HashMap<String,String> getMeta() { return meta; }
    public NavItem setMeta(HashMap<String,String> value) { this.meta = value; return this; }
}
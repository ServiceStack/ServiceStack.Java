/* Options:
Date: 2015-11-12 14:13:51
Version: 4.046
BaseUrl: http://localhost:65109

Package: net.servicestack.client.tests
GlobalNamespace: testDateDtos
//AddPropertyAccessors: True
//SettersReturnThis: True
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion: 
//IncludeTypes: 
//ExcludeTypes: 
//TreatTypesAsStrings: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*
*/

package net.servicestack.client;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;

public class testDateDtos
{

    @Route("/hello/{Name}")
    public static class Hello implements IReturn<HelloResponse>
    {
        public String Name = null;

        public String getName() { return Name; }
        public Hello setName(String value) { this.Name = value; return this; }
        private static Object responseType = HelloResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/testing")
    public static class MyGetRequest implements IReturn<MyGetRequestResponse>
    {
        public Date Date = null;

        public Date getDate() { return Date; }
        public MyGetRequest setDate(Date value) { this.Date = value; return this; }
        private static Object responseType = MyGetRequestResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class HelloResponse
    {
        public String Result = null;

        public String getResult() { return Result; }
        public HelloResponse setResult(String value) { this.Result = value; return this; }
    }

    public static class MyGetRequestResponse
    {
        public Date Result = null;

        public Date getResult() { return Result; }
        public MyGetRequestResponse setResult(Date value) { this.Result = value; return this; }
    }

    public static class HelloDateTime implements IReturn<HelloDateTime>
    {
        public Date dateTime = null;

        public Date getDateTime() { return dateTime; }
        public HelloDateTime setDateTime(Date value) { this.dateTime = value; return this; }
        private static Object responseType = HelloDateTime.class;
        public Object getResponseType() { return responseType; }
    }
}
//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.util.ArrayList;

public class WebServiceException extends RuntimeException {
    int StatusCode;
    String StatusDescription;
    String ResponseBody;
    ResponseStatus ResponseStatus;

    public WebServiceException(
            int statusCode, String statusDescription, String responseBody)
    {
        StatusCode = statusCode;
        StatusDescription = statusDescription;
        ResponseBody = responseBody;
    }

    public void setResponseStatus(ResponseStatus responseStatus){
        ResponseStatus = responseStatus;
    }

    public ResponseStatus getResponseStatus(){
        return ResponseStatus;
    }

    public String getErrorCode(){
        return ResponseStatus != null ? ResponseStatus.errorCode : null;
    }

    public String getErrorMessage(){
        return ResponseStatus != null ? ResponseStatus.message : null;
    }

    public String getServerStackTrace(){
        return ResponseStatus != null ? ResponseStatus.stackTrace : null;
    }

    public ArrayList<ResponseError> getFieldErrors(){
        ArrayList<ResponseError> fieldErrors = ResponseStatus != null
            ? ResponseStatus.getErrors()
            : null;
        return fieldErrors != null ? fieldErrors : new ArrayList<ResponseError>();
    }
}

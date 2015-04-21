//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.util.ArrayList;

@DataContract
public class ResponseStatus
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
    public ResponseStatus setErrorCode(String value) { this.errorCode = value; return this; }
    public String getMessage() { return message; }
    public ResponseStatus setMessage(String value) { this.message = value; return this; }
    public String getStackTrace() { return stackTrace; }
    public ResponseStatus setStackTrace(String value) { this.stackTrace = value; return this; }
    public ArrayList<ResponseError> getErrors() { return errors; }
    public ResponseStatus setErrors(ArrayList<ResponseError> value) { this.errors = value; return this; }
}

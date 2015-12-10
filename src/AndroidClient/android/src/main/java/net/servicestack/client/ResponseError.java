//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

@DataContract
public class ResponseError
{
    @DataMember(Order=1, EmitDefaultValue=false)
    public String errorCode = null;

    @DataMember(Order=2, EmitDefaultValue=false)
    public String fieldName = null;

    @DataMember(Order=3, EmitDefaultValue=false)
    public String message = null;

    public String getErrorCode() { return errorCode; }
    public ResponseError setErrorCode(String value) { this.errorCode = value; return this; }
    public String getFieldName() { return fieldName; }
    public ResponseError setFieldName(String value) { this.fieldName = value; return this; }
    public String getMessage() { return message; }
    public ResponseError setMessage(String value) { this.message = value; return this; }
}

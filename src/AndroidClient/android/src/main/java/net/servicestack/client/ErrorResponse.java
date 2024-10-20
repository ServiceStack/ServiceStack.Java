//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

@DataContract
public class ErrorResponse {

    @DataMember(Order=1)
    public ResponseStatus responseStatus = null;

    public ResponseStatus getResponseStatus() { return responseStatus; }
    public ErrorResponse setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus; return this;
    }
}
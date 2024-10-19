//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

@DataContract
public class IdResponse {

    @DataMember(Order=1)
    public String id = null;

    @DataMember(Order=2)
    public ResponseStatus responseStatus = null;

    public String getId() { return id; }
    public IdResponse setId(String id) {
        this.id = id; return this;
    }
    public ResponseStatus getResponseStatus() { return responseStatus; }
    public IdResponse setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus; return this;
    }
}
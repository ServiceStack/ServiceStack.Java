package net.servicestack.client.sse;

import net.servicestack.client.DataContract;
import net.servicestack.client.DataMember;
import net.servicestack.client.ResponseStatus;

/**
 * Created by mythz on 2/12/2017.
 */

@DataContract
public class UpdateEventSubscriberResponse
{
    @DataMember(Order=1)
    public ResponseStatus ResponseStatus = null;
    public ResponseStatus responseStatus = null;

    public ResponseStatus getResponseStatus() { return ResponseStatus != null ? ResponseStatus : responseStatus; }
    public UpdateEventSubscriberResponse setResponseStatus(ResponseStatus value) { this.ResponseStatus = this.responseStatus = value; return this; }
}


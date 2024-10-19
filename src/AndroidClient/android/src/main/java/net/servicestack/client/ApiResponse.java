//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ApiResponse {

    /// <summary>
    /// HTTP status code of response
    /// </summary>
    public int StatusCode() default 0;

    /// <summary>
    /// HTTP status code of response
    /// </summary>
    public String Description() default "";

    /// <summary>
    /// If set to true, the response is default for all non-explicitly defined status codes
    /// </summary>
    public boolean IsDefaultResponse() default false;

    /// <summary>
    /// Open API schema definition type for response
    /// </summary>
    public Class ResponseType() default Object.class;
}

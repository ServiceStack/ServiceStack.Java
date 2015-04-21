//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

public @interface ApiResponse {

    public int StatusCode() default 0;

    public String Description() default "";
}

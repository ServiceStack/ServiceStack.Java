//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client;

public @interface Meta {
    public String Name() default "";
    public String Value() default "";
}

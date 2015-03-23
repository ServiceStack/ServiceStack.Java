//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client;

public @interface References {

    public Class value() default Object.class;

    public Class Type() default Object.class;
}


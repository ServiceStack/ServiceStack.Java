//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Default {
    public String value() default "";

    public int IntValue() default 0;
    public double DoubleValue() default 0.0;

    public Class DefaultType() default Object.class;
    public String DefaultValue() default "";
}

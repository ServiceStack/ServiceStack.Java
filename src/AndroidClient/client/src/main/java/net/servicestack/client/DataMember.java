//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DataMember {
    public String value() default "";

    public String Name() default "";
    public int Order() default 0;
    public boolean IsRequired() default false;
    public boolean EmitDefaultValue() default false;
}

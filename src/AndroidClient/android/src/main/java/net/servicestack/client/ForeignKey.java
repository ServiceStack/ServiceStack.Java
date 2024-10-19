//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKey {

    public Class Type() default Object.class;
    public String OnDelete() default "";
    public String OnUpdate() default "";
    public String ForeignKeyName() default "";
}

//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Index {
    public String Name() default "";
    public boolean Unique() default false;
    public boolean Clustered() default false;
    public boolean NonClustered() default false;
}

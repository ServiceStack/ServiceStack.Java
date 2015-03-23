//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.

package net.servicestack.client;

public @interface DataContract {
    public String value() default "";

    public String Name() default "";
    public String Namespace() default "";
}

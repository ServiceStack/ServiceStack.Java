//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

public @interface DataMember {
    public String value() default "";

    public String Name() default "";
    public int Order() default 0;
    public boolean IsRequired() default false;
    public boolean EmitDefaultValue() default false;
}

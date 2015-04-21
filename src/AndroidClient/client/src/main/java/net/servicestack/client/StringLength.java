//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

public @interface StringLength {
    public int value() default 0;

    public int MinimumLength() default 0;
    public int MaximumLength() default 0;
}

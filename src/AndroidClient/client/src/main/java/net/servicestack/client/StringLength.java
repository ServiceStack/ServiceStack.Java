//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface StringLength {
    public int value() default 0;

    public int MinimumLength() default 0;
    public int MaximumLength() default 0;
}

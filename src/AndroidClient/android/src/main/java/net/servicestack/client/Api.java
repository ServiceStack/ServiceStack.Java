//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Api {
    String value() default "";

    /// <summary>
    /// The overall description of an API. Used by Swagger.
    /// </summary>
    public String Description() default "";

    /// <summary>
    /// Create or not body param for request type when verb is POST or PUT.
    /// Value can be one of the constants of `GenerateBodyParam` class:
    /// `GenerateBodyParam.IfNotDisabled` (default value), `GenerateBodyParam.Always`, `GenerateBodyParam.Never`
    /// </summary>
    public int BodyParameter() default 0;

    /// <summary>
    /// Tells if body param is required
    /// </summary>
    public boolean IsRequired() default false;
}


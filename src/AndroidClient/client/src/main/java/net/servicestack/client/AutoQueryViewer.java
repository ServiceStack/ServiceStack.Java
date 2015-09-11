//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AutoQueryViewer {
    public String Title() default "";
    public String Description() default "";
    public String IconUrl() default "";
    public String BrandUrl() default "";
    public String BrandImageUrl() default "";
    public String TextColor() default "";
    public String LinkColor() default "";
    public String BackgroundColor() default "";
    public String BackgroundImageUrl() default "";
    public String DefaultSearchField() default "";
    public String DefaultSearchType() default "";
    public String DefaultSearchText() default "";
}

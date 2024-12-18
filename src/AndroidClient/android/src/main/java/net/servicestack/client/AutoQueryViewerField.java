//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AutoQueryViewerField {
    public String Title() default "";
    public String Description() default "";
    public boolean HideInSummary() default false;
    public String ValueFormat () default "";
    public String LayoutHint() default "";
}

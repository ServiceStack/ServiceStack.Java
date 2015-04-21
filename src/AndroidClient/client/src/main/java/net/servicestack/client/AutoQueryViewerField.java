//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

public @interface AutoQueryViewerField {
    public String Title() default "";
    public String Description() default "";
    public boolean HideInSummary() default false;
    public String ValueFormat () default "";
    public String LayoutHint() default "";
}

package net.servicestack;

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

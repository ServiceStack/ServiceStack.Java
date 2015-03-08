package net.servicestack;

public @interface AutoQueryViewerField {
    public String Title() default "";
    public String Description() default "";
    public boolean HideInSummary() default false;
    public String ValueFormat () default "";
    public String LayoutHint() default "";
}

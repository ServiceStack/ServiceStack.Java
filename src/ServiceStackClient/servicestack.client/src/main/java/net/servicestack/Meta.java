package net.servicestack;

public @interface Meta {
    public String Name() default "";
    public String Value() default "";
}

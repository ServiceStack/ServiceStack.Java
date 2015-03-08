package net.servicestack;

public @interface DataContract {
    public String value() default "";

    public String Name() default "";
    public String Namespace() default "";
}

package net.servicestack;

public @interface Api {
    String value() default "";

    public String Description() default "";
}


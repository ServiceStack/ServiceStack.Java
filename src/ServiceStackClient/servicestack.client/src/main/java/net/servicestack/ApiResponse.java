package net.servicestack;

public @interface ApiResponse {

    public int StatusCode() default 0;

    public String Description() default "";
}

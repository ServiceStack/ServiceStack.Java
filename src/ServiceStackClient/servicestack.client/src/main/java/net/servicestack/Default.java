package net.servicestack;

public @interface Default {
    public String value() default "";

    public int IntValue() default 0;
    public double DoubleValue() default 0.0;

    public Class DefaultType() default Object.class;
    public String DefaultValue() default "";
}

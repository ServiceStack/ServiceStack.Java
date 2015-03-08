package net.servicestack;

public @interface StringLength {
    public int value() default 0;

    public int MinimumLength() default 0;
    public int MaximumLength() default 0;
}

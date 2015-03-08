package net.servicestack;

public @interface DataMember {
    public String value() default "";

    public String Name() default "";
    public int Order() default 0;
    public boolean IsRequired() default false;
    public boolean EmitDefaultValue() default false;
}

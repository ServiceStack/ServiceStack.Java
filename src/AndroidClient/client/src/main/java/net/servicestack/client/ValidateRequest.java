package net.servicestack.client;

public @interface ValidateRequest {
    /// <summary>
    /// Boolean #Script Code Expression to Test
    /// </summary>
    public String Condition() default "";

    /// <summary>
    /// Custom ErrorCode to return
    /// </summary>
    public String ErrorCode() default "";

    /// <summary>
    /// FluentValidation Error Message
    /// </summary>
    public String Message() default "";
}

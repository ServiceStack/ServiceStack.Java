package net.servicestack.client;

public @interface ValidateRequest {
    /// <summary>
    /// Expression to create a validator registered in Validators.Types
    /// </summary>
    public String Validator() default "";

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

    /// <summary>
    /// Error StatusCode
    /// </summary>
    public int StatusCode() default 400;
}

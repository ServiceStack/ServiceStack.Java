//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

public @interface ApiMember {

    /// <summary>
    /// Gets or sets verb to which applies attribute. By default applies to all verbs.
    /// </summary>
    public String Verb() default "";

    /// <summary>
    /// Gets or sets parameter type: It can be only one of the following: path, query, body, form, or header.
    /// </summary>
    public String ParameterType() default "";

    /// <summary>
    /// Gets or sets unique name for the parameter. Each name must be unique, even if they are associated with different paramType values. 
    /// </summary>
    /// <remarks>
    /// <para>
    /// Other notes on the name field:
    /// If paramType is body, the name is used only for UI and codegeneration.
    /// If paramType is path, the name field must correspond to the associated path segment from the path field in the api object.
    /// If paramType is query, the name field corresponds to the query param name.
    /// </para>
    /// </remarks>
    public String Name() default "";

    /// <summary>
    /// Gets or sets the human-readable description for the parameter.
    /// </summary>
    public String Description() default "";

    /// <summary>
    /// For path, query, and header paramTypes, this field must be a primitive. For body, this can be a complex or container datatype.
    /// </summary>
    public String DataType() default "";

    /// <summary>
    /// For path, this is always true. Otherwise, this field tells the client whether or not the field must be supplied.
    /// </summary>
    public boolean IsRequired() default false;

    /// <summary>
    /// For query params, this specifies that a comma-separated list of values can be passed to the API. For path and body types, this field cannot be true.
    /// </summary>
    public boolean AllowMultiple() default false;

    /// <summary>
    /// Gets or sets route to which applies attribute, matches using StartsWith. By default applies to all routes. 
    /// </summary>
    public String Route() default "";
}

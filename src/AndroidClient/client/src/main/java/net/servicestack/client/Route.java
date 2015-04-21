//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

public @interface Route {

    String value() default "";

    public String Path() default "";

    /// <summary>
    ///    Gets or sets short summary of what the route does.
    /// </summary>
    public String Summary() default "";

    /// <summary>
    ///    Gets or sets longer text to explain the behaviour of the route. 
    /// </summary>
    public String Notes() default "";

    /// <summary>
    ///		Gets or sets a comma-delimited list of HTTP verbs supported by the service, such as
    ///		"GET,PUT,POST,DELETE".
    /// </summary>
    /// <value>
    ///		A <see cref="String"/> providing a comma-delimited list of HTTP verbs supported
    ///		by the service, <see langword="null"/> or empty if all verbs are supported.
    /// </value>
    public String Verbs() default "";

    /// <summary>
    /// Used to rank the precedences of route definitions in reverse routing. 
    /// i.e. Priorities below 0 are auto-generated have less precedence.
    /// </summary>
    public int Priority() default 0;
}

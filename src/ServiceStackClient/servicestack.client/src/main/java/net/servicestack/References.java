package net.servicestack;

/**
 * Created by mythz on 3/7/2015.
 */
public @interface References {

    public Class value() default Object.class;

    public Class Type() default Object.class;
}


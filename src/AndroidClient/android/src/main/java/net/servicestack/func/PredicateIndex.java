//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.func;

public interface PredicateIndex<T> {

    boolean apply(T t, int index);
}


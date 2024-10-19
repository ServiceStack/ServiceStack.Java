//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.func;

public interface FunctionIndex<T,R> {
    public R apply(T t, int i);
}

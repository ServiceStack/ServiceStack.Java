//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.func;

public interface Predicate2<T1,T2> {
    public boolean apply(T1 a, T2 b);
}

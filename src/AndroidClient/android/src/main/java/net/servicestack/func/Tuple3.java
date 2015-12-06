//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.func;

public class Tuple3<A,B,C> {
    public A A;
    public B B;
    public C C;

    public Tuple3(A a, B b, C c) {
        A = a;
        B = b;
        C = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple3)) return false;
        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
        if (A != null ? !A.equals(tuple3.A) : tuple3.A != null) return false;
        if (B != null ? !B.equals(tuple3.B) : tuple3.B != null) return false;
        return !(C != null ? !C.equals(tuple3.C) : tuple3.C != null);
    }

    @Override
    public int hashCode() {
        int result = A != null ? A.hashCode() : 0;
        result = 31 * result + (B != null ? B.hashCode() : 0);
        result = 31 * result + (C != null ? C.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + A + ", " + B + ", " + C + ")";
    }
}

//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.func;

public class Tuple<A,B> {
    public A A;
    public B B;
    public Tuple(A a, B b) {
        A = a;
        B = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        if (A != null ? !A.equals(tuple.A) : tuple.A != null) return false;
        return !(B != null ? !B.equals(tuple.B) : tuple.B != null);
    }

    @Override
    public int hashCode() {
        int result = A != null ? A.hashCode() : 0;
        result = 31 * result + (B != null ? B.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + A + ", " + B + ")";
    }
}

package net.servicestack.client;

import java.util.ArrayList;
import java.util.List;

public class Func {
    public static interface Function<T,R> {
        public R apply(T t);
    }

    public static <T,R> ArrayList<R> map(List<T> xs, Function<T,R> f) {
        ArrayList<R> to = new ArrayList<>();
        for (T x : xs) {
            R ret = f.apply(x);
            to.add(ret);
        }
        return to;
    }
}

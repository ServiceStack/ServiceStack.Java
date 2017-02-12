package net.servicestack.client.sse;

import net.servicestack.client.IResolver;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * Created by mythz on 2/12/2017.
 */

public class SingletonInstanceResolver implements IResolver {

    ConcurrentMap<Class, Object> cache = new ConcurrentHashMap<>();

    @Override
    public Object TryResolve(Class cls) {
        return cache.computeIfAbsent(cls, new Function<Class, Object>() {
            @Override
            public Object apply(Class aClass) {
                try {
                    return aClass.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

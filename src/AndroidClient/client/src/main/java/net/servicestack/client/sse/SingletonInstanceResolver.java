package net.servicestack.client.sse;

import net.servicestack.client.IResolver;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by mythz on 2/12/2017.
 */

public class SingletonInstanceResolver implements IResolver {

    ConcurrentMap<Class, Object> cache = new ConcurrentHashMap<>();

    @Override
    public Object TryResolve(Class cls) {
        Object instance = cache.get(cls);

        if (instance == null){
            try {
                Object newInstance = cls.newInstance();
                instance = (instance = cache.putIfAbsent(cls, newInstance)) == null
                        ? newInstance
                        : instance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
}

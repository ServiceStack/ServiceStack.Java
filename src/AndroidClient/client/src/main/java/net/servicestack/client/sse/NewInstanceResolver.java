package net.servicestack.client.sse;

import net.servicestack.client.IResolver;

/**
 * Created by mythz on 2/11/2017.
 */

public class NewInstanceResolver implements IResolver {
    @Override
    public Object TryResolve(Class cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

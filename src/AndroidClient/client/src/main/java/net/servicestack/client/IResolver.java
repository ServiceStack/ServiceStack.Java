package net.servicestack.client;

/**
 * Created by mythz on 2/11/2017.
 */

public interface IResolver {
    Object TryResolve(Class cls);
}

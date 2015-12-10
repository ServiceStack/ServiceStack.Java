package net.servicestack.android.checkweb;

/**
 * Created by mythz on 6/30/2015.
 */
public class ExtendedRockstar extends dtos.Rockstar {
    public Integer ExtendedId = null;

    public ExtendedRockstar(Integer extendedId) {
        ExtendedId = extendedId;
        super.Id = extendedId;
    }
}

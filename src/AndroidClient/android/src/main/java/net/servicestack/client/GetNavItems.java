package net.servicestack.client;

import net.servicestack.GetNavItemsResponse;

@Route("/metadata/nav")
@DataContract
public class GetNavItems implements IReturn<GetNavItemsResponse>
{

    private static Object responseType = GetNavItemsResponse.class;
    public Object getResponseType() { return responseType; }
}

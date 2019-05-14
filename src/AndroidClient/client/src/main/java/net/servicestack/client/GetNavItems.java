package net.servicestack.client;

@Route("/metadata/nav")
@DataContract
public class GetNavItems implements IReturn<GetNavItemsResponse>
{

    private static Object responseType = GetNavItemsResponse.class;
    public Object getResponseType() { return responseType; }
}

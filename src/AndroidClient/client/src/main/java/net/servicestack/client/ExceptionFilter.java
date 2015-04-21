//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.net.HttpURLConnection;

public interface ExceptionFilter {
    public void exec(HttpURLConnection conn, Exception ex);
}

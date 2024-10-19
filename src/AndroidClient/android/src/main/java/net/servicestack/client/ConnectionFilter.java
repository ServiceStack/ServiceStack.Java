//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.client;

import java.net.HttpURLConnection;

public interface ConnectionFilter {
    public void exec(HttpURLConnection conn);
}

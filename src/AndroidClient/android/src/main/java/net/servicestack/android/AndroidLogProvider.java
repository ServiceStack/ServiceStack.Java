//  Copyright (c) 2015 ServiceStack LLC. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.android;

import net.servicestack.client.LogProvider;
import net.servicestack.client.LogType;

import static net.servicestack.client.LogType.*;

public class AndroidLogProvider extends LogProvider {

    public AndroidLogProvider(){
        super(null, true);
    }

    public AndroidLogProvider(String prefix) {
        super(prefix, true);
    }

    public AndroidLogProvider(String prefix, boolean debugEnabled) {
        super(prefix, debugEnabled);
    }

    @Override
    public void println(LogType type, Object message) {
        switch (type){
            case DEBUG:
                android.util.Log.d(getPrefix(), message.toString());
                break;
            case INFO:
                android.util.Log.i(getPrefix(), message.toString());
                break;
            case WARN:
                android.util.Log.w(getPrefix(), message.toString());
                break;
            case ERROR:
                android.util.Log.e(getPrefix(), message.toString());
                break;
        }
    }
}

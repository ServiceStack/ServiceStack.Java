//  Copyright (c) 2013-present ServiceStack, Inc. All rights reserved.
//  License: https://servicestack.net/bsd-license.txt

package net.servicestack.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import net.servicestack.client.AsyncResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AndroidUtils {

    public static Bitmap readBitmap(String url){
        try {
            URL heartbeatUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)heartbeatUrl.openConnection();
            return readBitmap(conn.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Bitmap readBitmap(HttpURLConnection response){
        try {
            return readBitmap(response.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Bitmap readBitmap(InputStream stream) {
        return BitmapFactory.decodeStream(stream);
    }

    public static Bitmap readBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}

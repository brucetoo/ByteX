package com.ss.android.ugc.bytex.example.catch_exception;

import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;

public class CatchTest {
    public void markCatch() {
        try {
            File file = new File("11");
            file.createNewFile();
        } catch (Exception e) {

        }

        try {
            URL uri = new URL("www.baidu.com");
            HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
        } catch (Exception e) {

        }
    }
}

package com.pacific.common.http;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class HttpConnection {

    private static char[] is = new char[] {'0', '1', '2', '4', '5', '6', '7', '8', '9'};
    private static int total;
    private static int m = 6;

    // 5秒超时
    public static final int CONNECTION_TIME_OUT = 5000;
    public static final int READ_TIME_OUT = 5000;

    public static String sendPost(String link, String query, String data)
            throws IOException {
        URL url = new URL(link + "?" + query);

        URLConnection conn = url.openConnection();
      //  conn.setConnectTimeout(CONNECTION_TIME_OUT);
       // conn.setReadTimeout(READ_TIME_OUT);
        conn.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(
                conn.getOutputStream());
        out.write(data);
        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn
                        .getInputStream()));
        String line = "";
        String back = "";
        while ((line = reader.readLine()) != null) {
            back = back + line;
        }
        try {
            reader.close();
        } catch (Exception e) {
        }
        return back;
    }

    public static String sendGet(String link)
            throws IOException {
        return sendGet(link,null);
    }

    public static String sendGet(String link, String query)
            throws IOException {
        if (StringUtils.isNotEmpty(query)) {
            link += query;
        }

        URL url = new URL(link);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String line = "";
        String back = "";
        while ((line = reader.readLine()) != null) {
            back = back + line;
        }
        try {
            reader.close();
        } catch (Exception e) {
        }
        return back;
    }

    public static InputStream sendBigGet(String link, String query)
            throws IOException {
        URL url = new URL(link + "?" + query);
        InputStream stream = url.openStream();
        return stream;
    }

}

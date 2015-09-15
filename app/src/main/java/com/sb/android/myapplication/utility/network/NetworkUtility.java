package com.sb.android.myapplication.utility.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by student on 2015-09-15.
 */
public class NetworkUtility {
    /**
     * getUrlConnection
     * @Note : url 커넥션
     * @return
     * @throws Exception
     *
     *
     */
    public static URLConnection getUrlConnection(String urlString)
            throws Exception {

        URL url = new URL( urlString ); // 넘어오는 URL밎정보
        URLConnection connection = url.openConnection(); // 커넥션
        connection.setDoOutput( true );
        return connection;
    }

    /**
     * getReturnString
     * @Note : 커넥션된 결과값
     * @param connection
     * @return
     * @throws IOException
     *
     *
     */
    public static String getReturnString(URLConnection connection)
            throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(
                connection.getInputStream(), "UTF-8" ) ); // 반환되는 값이 UTF-8 경우
        StringBuffer buffer = new StringBuffer();// @Thread stringbuffer is safe
        String decodedString;

        while( ( decodedString = in.readLine() ) != null ) {
            buffer.append( decodedString );

        }

        in.close();

        return buffer.toString();
    }

    /**
     * Retrieve source from url
     * @param url
     * @return
     * @throws Exception
     */
    public static String getReturnString(String url) throws Exception {
        return getReturnString(getUrlConnection(url));
    }
}

package org.company.project.model.repository;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ClientWebSocket {
    private ClientWebSocket () {}
    public static Long sendRequestByGet(String requestAddress) throws Exception{
        URL url = new URL(requestAddress);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        String context = "";
        int ascii = inputStream.read();
        while (ascii != -1){
            context += (char) ascii;
            ascii = inputStream.read();
        }
        inputStream.close();
        return Long.parseLong(context);
    }
}

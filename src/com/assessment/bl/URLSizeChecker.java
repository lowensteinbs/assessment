package com.assessment.bl;

import com.assessment.dc.URLResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLSizeChecker {

    public static URLResponse execute(String targetUrl) throws Exception {
        URLResponse response = new URLResponse(targetUrl);
        HttpURLConnection connection;
        URL url;
        try {
            StringBuilder result = new StringBuilder();
            url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            response.setSize(result.length());

        } catch (Exception e) {
            throw new Exception("Cannot connect to URL: " + targetUrl);
        }

        return response;
    }
}

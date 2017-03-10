package com.assessment.bl;

import com.google.gson.Gson;
import com.assessment.dc.Constants;
import com.assessment.dc.URLResponse;

public class Assessment {

    private boolean jsonArray = false;

    public Assessment() {

    }

    public boolean isJsonArray() {
        return jsonArray;
    }

    public URLResponse[] getURLResponse(URLResponse responses[]) throws Exception {
        URLResponse urlResponses[] = new URLResponse[responses.length];
        try {
            for (int i = 0; i <= responses.length - 1; i++) {
                URLResponse urlResponse = responses[i];
                urlResponse = URLSizeChecker.execute(urlResponse.getUrl());
                urlResponses[i] = urlResponse;
            }
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return urlResponses;
    }
    
    public URLResponse[] execute(String args[]) throws Exception {
        URLResponse urlResponse[];

        if (args == null || args.length == 0) {
            ReadConfiguration readConfiguration = new ReadConfiguration();
            String url = readConfiguration.getProperty("URL.DEV");

            URLResponse response = new URLResponse(url);
            urlResponse = new URLResponse[1];
            urlResponse[0] = response;
        } else {
            String fileName = args[0];
            String json = null;
            try {
                json = ReadFile.readFile(fileName);
            }
            catch (Exception e) {
                throw new Exception("Cannot read file: " + fileName);
            }
            Gson gson = new Gson();
            urlResponse = gson.fromJson(json, URLResponse[].class);
            if (urlResponse != null || urlResponse.length > 0) {
                if (urlResponse[0].getUrl() == null) {
                    String error = "Invalid file format. ";
                    error += Constants.VALID_JSON_FORMAT;
                    throw new Exception(error);
                }
            }
            this.jsonArray = true;
        }

        return urlResponse;
    }
}

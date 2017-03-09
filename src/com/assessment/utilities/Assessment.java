package com.assessment.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.assessment.bl.ReadConfiguration;
import com.assessment.bl.ReadFile;
import com.assessment.bl.URLSizeChecker;
import com.assessment.bl.WriteFile;
import com.assessment.dc.Constants;
import com.assessment.dc.URLResponse;

public class Assessment {

    private boolean jsonArray = false;

    public Assessment() {

    }

    public boolean isJsonArray() {
        return jsonArray;
    }

    private URLResponse[] getURLResponse(URLResponse responses[]) throws Exception {
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
    
    private URLResponse[] execute(String args[]) throws Exception {
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

    public static void main(String args[]) throws Exception {

        try {
            Assessment assessment = new Assessment();
            URLResponse urlResponse[] = assessment.execute(args);
            urlResponse = assessment.getURLResponse(urlResponse);

            try {
                WriteFile.writeFile(urlResponse, assessment.isJsonArray());
            } catch (Exception e) {
                throw new Exception("Error writing file");
            }

            System.out.println("Please see " + Constants.OUTPUT_FILENAME + " for results.");
        }
        catch (JsonSyntaxException jsonSyntaxException) {
            throw new Exception("Invalid json syntax. " + Constants.VALID_JSON_FORMAT);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
}

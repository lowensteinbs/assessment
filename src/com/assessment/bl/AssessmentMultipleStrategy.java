package com.assessment.bl;

import com.google.gson.Gson;
import com.assessment.dc.Constants;
import com.assessment.dc.URLResponse;

public class AssessmentMultipleStrategy extends Assessment implements IAssessmentStrategy {

    public AssessmentMultipleStrategy() {

    }

    public boolean isJsonArray() {
        return true;
    }

    public URLResponse[] execute(String args[]) throws Exception {
        URLResponse urlResponse[];

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
        
        return urlResponse;
    }
}

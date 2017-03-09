package com.assessment.bl;

import com.google.gson.Gson;
import com.assessment.dc.Constants;
import com.assessment.dc.JSONResponse;
import com.assessment.dc.URLResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteFile {

    public static void writeFile(URLResponse[] response, boolean jsonOutputRequested) throws Exception {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            if (response.length > 0) {
                File file = new File(Constants.OUTPUT_FILENAME);
                fw = new FileWriter(file.getAbsoluteFile());
                bw = new BufferedWriter(fw);
                if (jsonOutputRequested) {
                    JSONResponse jsonResponse = new JSONResponse();
                    jsonResponse.setUrlResponses(response);
                    String jsonString = new Gson().toJson(jsonResponse);
                    bw.write(jsonString);
                } else {
                    bw.write("Size of " + response[0].getUrl() + ": " + response[0].getSize());
                }
            }
        }
        catch (Exception e) {
            throw new Exception("Could not write to file", e);
        }
        finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            }
            catch (Exception e) {
                throw new Exception("Could not close file");
            }
        }
    }
}

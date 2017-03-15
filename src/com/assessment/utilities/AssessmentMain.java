package com.assessment.utilities;

import com.assessment.bl.Assessment;
import com.assessment.bl.IAssessmentStrategy;
import com.assessment.bl.WriteFile;
import com.assessment.dc.Constants;
import com.assessment.dc.URLResponse;
import com.google.gson.JsonSyntaxException;

public class AssessmentMain {
	public static void main(String args[]) throws Exception {

        try {
        	IAssessmentStrategy assessment = Assessment.getAssessmentStrategy(args);
        	
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

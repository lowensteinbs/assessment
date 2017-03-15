package com.assessment.bl;

import com.assessment.dc.URLResponse;

public class Assessment {
	
	public static IAssessmentStrategy getAssessmentStrategy(String[] args) {
		IAssessmentStrategy assessment = null;
		
		if (args.length > 0) {
    		assessment = new AssessmentMultipleStrategy();
    	}
    	else {
    		assessment = new AssessmentSingleStrategy();
    	}
		
		return assessment;
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
}

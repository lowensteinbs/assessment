package com.assessment.bl;

import com.assessment.dc.URLResponse;

public class AssessmentSingleStrategy extends Assessment implements IAssessmentStrategy {

	public URLResponse[] execute(String[] args) throws Exception {
		URLResponse urlResponse[];
		
		ReadConfiguration readConfiguration = new ReadConfiguration();
        String url = readConfiguration.getProperty("URL.DEV");

        URLResponse response = new URLResponse(url);
        urlResponse = new URLResponse[1];
        urlResponse[0] = response;
        
        return urlResponse;
	}

	public boolean isJsonArray() {
		return false;
	}

}

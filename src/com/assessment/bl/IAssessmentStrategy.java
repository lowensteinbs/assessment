package com.assessment.bl;

import com.assessment.dc.URLResponse;

public interface IAssessmentStrategy {
	public URLResponse[] getURLResponse(URLResponse responses[]) throws Exception;
	public URLResponse[] execute(String args[]) throws Exception;
	public boolean isJsonArray();
}

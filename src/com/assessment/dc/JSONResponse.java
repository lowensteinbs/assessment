package com.assessment.dc;

public class JSONResponse {
    private int requests;
    private URLResponse urlResponses[];

    public void setUrlResponses(URLResponse[] urlResponses) {
        this.urlResponses = urlResponses;
        this.requests = urlResponses.length;
    }
}

package com.spring.spring_rest.entity;

/**
 * Error details returned to the client as JSON when a request fails.
 * Used by {@code StudentExceptionHandler} to build error responses.
 */
public class StudentErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

    /**
     * Creates an empty error response. Values are set with the setters.
     */
    public StudentErrorResponse(){ 
    }

    /**
     * Creates an error response with all of its values.
     *
     * @param status the HTTP status code, such as 404
     * @param message the error message shown to the client
     * @param timeStamp the time of the error in milliseconds
     */
    public StudentErrorResponse(int status, String message, long timeStamp){
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}

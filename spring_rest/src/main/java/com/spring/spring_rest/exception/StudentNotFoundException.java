package com.spring.spring_rest.exception;

/**
 * Thrown when a student with the requested id does not exist.
 * It is a {@link RuntimeException}, so it does not need to be declared
 * with {@code throws}. {@code StudentExceptionHandler} catches it and
 * returns an HTTP 404 (Not Found) response.
 */
public class StudentNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -269010004608797626L;

    /**
     * Creates the exception with a message and the original cause.
     *
     * @param message the error message describing what was not found
     * @param cause the underlying exception that triggered this one
     */
    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates the exception with a message.
     *
     * @param message the error message describing what was not found
     */
    public StudentNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates the exception from an underlying cause.
     *
     * @param cause the underlying exception that triggered this one
     */
    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }

}

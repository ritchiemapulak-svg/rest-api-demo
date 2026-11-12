package com.spring.spring_rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.spring_rest.entity.StudentErrorResponse;
import com.spring.spring_rest.exception.StudentNotFoundException;

/**
 * Global exception handler for the REST controllers. Converts
 * exceptions into JSON error responses so clients do not receive
 * raw stack traces.
 */
@ControllerAdvice 
public class StudentExceptionHandler {

     /**
     * Handles a missing student.
     *
     * @param ex the exception thrown when a student id does not exist
     * @return an error response with HTTP status 404 (Not Found)
     */
    @ExceptionHandler 
    public ResponseEntity<StudentErrorResponse> handleException(
        StudentNotFoundException ex) {
            StudentErrorResponse err = new StudentErrorResponse();

            err.setStatus(HttpStatus.NOT_FOUND.value());
            err.setMessage(ex.getMessage());
            err.setTimeStamp(System.currentTimeMillis());

            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }

     /**
     * Catch-all handler for any other exception, such as a
     * non-numeric id in the URL.
     *
     * @param ex the exception that was thrown
     * @return an error response describing the invalid data
     */
    @ExceptionHandler 
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {
            StudentErrorResponse err = new StudentErrorResponse();

            err.setStatus(HttpStatus.BAD_REQUEST.value());
            err.setMessage("Invalid data. Only integers are accepted");
            err.setTimeStamp(System.currentTimeMillis());

            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }

     /**
     * Handles validation failures from {@code @Valid} request bodies.
     *
     * @param ex the exception holding the failed validation rules
     * @return a map of field name to error message with HTTP
     *         status 400 (Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> 
        handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> err = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            err.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    
}

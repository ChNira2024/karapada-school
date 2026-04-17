package com.karapada.school.exception;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	
    @ExceptionHandler(StudentNotFoundException.class)
	public ProblemDetail handleStudentNotFound(StudentNotFoundException snfe){
    	
    	logger.error("StudentNotFoundException occurred", snfe);

    	ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    	problemDetail.setTitle("STUDENT NOT FOUND");
    	problemDetail.setDetail(snfe.getMessage());
    	problemDetail.setProperty("timestamp", java.time.Instant.now());
    	
    	return problemDetail;
		
	}
    
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<?> handleUsernameExists(UsernameAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", ex.getMessage(),
                        "status", 400
                ));
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", ex.getMessage(),
                        "status", 400
                ));
    }

}

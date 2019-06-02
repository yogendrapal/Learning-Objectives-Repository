package com.LearningObjectiveRepo.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LORestExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<LearningObjectiveErrorResponse> handleException(ResourceNotFoundException exc) {
		
		LearningObjectiveErrorResponse error = new LearningObjectiveErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	 }

	@ExceptionHandler
	public ResponseEntity<LearningObjectiveErrorResponse> handleException(Exception exc) {
		
		LearningObjectiveErrorResponse error = new LearningObjectiveErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}	

}

package com.springmvc.webService;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	  ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
			  ex.getMessage(), ex.getBindingResult().toString());
	  return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}

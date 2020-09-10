package com.learning.rest.webservice.restfulwebservices.Exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.rest.webservice.restfulwebservices.helloworld.user.UserNotFoundException;

@ControllerAdvice //i want this customised handler to be applicable to all the controllers across application. AOP
@RestController
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class) //handle all exceptions
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class) //handle specific exceptions
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
		
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	

}

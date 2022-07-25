package com.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.challenge.dto.ErrorResponse;

import lombok.Generated;

@Generated
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		String detail = new String();
		detail = (ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, detail);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex,
			WebRequest request) {
		String detail = new String();
		detail = (ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, detail);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmailNotValidException.class)
	public final ResponseEntity<Object> handleEmailNotValidException(EmailNotValidException ex, WebRequest request) {
		String detail = new String();
		detail = (ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, detail);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

		
	}
	@ExceptionHandler(PassNotValidException.class)
	public final ResponseEntity<Object> handlePassNotValidException(PassNotValidException ex, WebRequest request) {
		String detail = new String();
		detail = (ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, detail);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotExistsException.class)
	public final ResponseEntity<Object> handleUserNotExistsException(UserNotExistsException ex,
			WebRequest request) {
		String detail = new String();
		detail = (ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, detail);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}

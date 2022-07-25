package com.challenge.exceptions;

public class EmailNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailNotValidException(String exception) {
		super(exception);
	}
}

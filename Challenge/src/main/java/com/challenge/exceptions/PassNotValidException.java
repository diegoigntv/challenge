package com.challenge.exceptions;

public class PassNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PassNotValidException(String exception) {
		super(exception);
	}
}

package com.challenge.exceptions;

public class UserNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotExistsException(String exception) {
		super(exception);
	}
}

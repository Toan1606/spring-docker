package com.codedecode.demo.exception;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
    }
	
	public UserNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

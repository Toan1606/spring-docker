package com.codedecode.demo.exception;


public class CustomIllegalArgumentException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomIllegalArgumentException(String errorMessage) {
		super(errorMessage);
	}
}
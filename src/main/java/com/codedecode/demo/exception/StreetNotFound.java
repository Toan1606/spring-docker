package com.codedecode.demo.exception;

public class StreetNotFound extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StreetNotFound(String errorMessage) {
		super(errorMessage);
	}
}
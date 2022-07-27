package com.codedecode.demo.exception;

public class CityNotFound extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CityNotFound(String errorMessage) {
		super(errorMessage);
	}
}

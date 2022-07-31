package com.codedecode.demo.exception;

public class AddressNotFound extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddressNotFound(String errorMessage) {
		super(errorMessage);
	}
}
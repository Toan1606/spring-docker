package com.codedecode.demo.exception;


public class ProvinceNotFound extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProvinceNotFound(String errorMessage) {
		super(errorMessage);
	}
}
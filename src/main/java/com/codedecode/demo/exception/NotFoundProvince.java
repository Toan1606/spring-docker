package com.codedecode.demo.exception;

public class NotFoundProvince extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundProvince(String errorMessage) {
		super(errorMessage);
	}
}
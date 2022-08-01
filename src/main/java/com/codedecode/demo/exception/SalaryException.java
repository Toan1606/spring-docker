package com.codedecode.demo.exception;

public class SalaryException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SalaryException(String errorMessage) {
		super(errorMessage);
	}
}
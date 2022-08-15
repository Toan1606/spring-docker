package com.codedecode.demo.exception;

public class DesiredJobUpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DesiredJobUpdateException(String errorMessage) {
		super(errorMessage);
	}
}
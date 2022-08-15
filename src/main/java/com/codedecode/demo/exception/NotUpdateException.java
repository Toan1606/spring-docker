package com.codedecode.demo.exception;

public class NotUpdateException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotUpdateException(String errorMessage) {
		super(errorMessage);
	}
}
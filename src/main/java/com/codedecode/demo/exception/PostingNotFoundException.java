package com.codedecode.demo.exception;

public class PostingNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PostingNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
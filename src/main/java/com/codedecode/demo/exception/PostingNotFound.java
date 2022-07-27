package com.codedecode.demo.exception;

public class PostingNotFound extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PostingNotFound(String errorMessage) {
		super(errorMessage);
	}
}
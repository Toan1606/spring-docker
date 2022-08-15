package com.codedecode.demo.exception;

public class PostingCategoryNotFound extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PostingCategoryNotFound(String errorMessage) {
		super(errorMessage);
	}
}
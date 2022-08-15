package com.codedecode.demo.exception;

public class RankNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RankNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
package com.codedecode.demo.exception;

public class DesiredJobNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DesiredJobNotFound(String errorMessage) {
		super(errorMessage);
	}
}
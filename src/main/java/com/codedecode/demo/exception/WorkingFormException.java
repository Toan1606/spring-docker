package com.codedecode.demo.exception;

public class WorkingFormException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public WorkingFormException(String errorMessage) {
        super(errorMessage);
    }
}

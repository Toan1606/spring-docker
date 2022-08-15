package com.codedecode.demo.exception;

public class WorkingFormNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public WorkingFormNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

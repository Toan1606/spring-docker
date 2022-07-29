package com.codedecode.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthenticatedException  extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthenticatedException(HttpStatus status) {
		super(HttpStatus.UNAUTHORIZED, "unauthenticated");
	}

}

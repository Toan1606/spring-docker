package com.codedecode.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class IndexException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndexException() {
		super();
	}

	public IndexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IndexException(String message, Throwable cause) {
		super(message, cause);
	}

	public IndexException(String message) {
		super(message);
	}

	public IndexException(Throwable cause) {
		super(cause);
	}
}

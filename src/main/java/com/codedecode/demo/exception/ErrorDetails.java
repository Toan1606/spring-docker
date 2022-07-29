package com.codedecode.demo.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;
	private String details;
}

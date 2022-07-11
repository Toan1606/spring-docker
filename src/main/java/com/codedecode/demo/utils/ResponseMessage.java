package com.codedecode.demo.utils;

import lombok.Getter;

@Getter
public enum ResponseMessage {
	LOGOUT_SUCCESS("Logout Successfuly");
	
	private final String message;
	
	private ResponseMessage(String message) {
		this.message = message;
	}
}

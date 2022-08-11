package com.codedecode.demo.utils;

import lombok.Getter;

@Getter
public enum ResponseMessage {
	LOGOUT_SUCCESS("Logout Successfuly"), DELETE_SUCCESS("Delete Applied Job Successfully");
	
	private final String message;
	
	private ResponseMessage(String message) {
		this.message = message;
	}
}

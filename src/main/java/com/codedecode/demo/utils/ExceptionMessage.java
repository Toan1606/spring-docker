package com.codedecode.demo.utils;

import lombok.Getter;

public enum ExceptionMessage {
	CITY_NOT_FOUND("City Does Not Exist"), PROVINCE_NOT_FOUND("Province Does Not Exist"), ADDRESS_NOT_FOUND("City Does Not Exist"), PASSWORD_DON_NOT_MATCH("Password do not match");
	
	@Getter
	private String errorMessage;
	
	private ExceptionMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

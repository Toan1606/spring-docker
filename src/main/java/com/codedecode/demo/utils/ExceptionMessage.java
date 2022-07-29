package com.codedecode.demo.utils;


import lombok.Getter;

public enum ExceptionMessage {
	STREET_NOT_FOUND("Street Does Not Exist"), CITY_NOT_FOUND("City Does Not Exist"), PROVINCE_NOT_FOUND("Province Does Not Exist"), ADDRESS_NOT_FOUND("City Does Not Exist")
	, PASSWORD_DON_NOT_MATCH("Password do not match"), USER_NOT_FOUND("User Does Not Exist"), POSTING_NOT_FOUND("Posting Does Not Exist")
	, ILLEGAL_ARGUMENT_EXCEPTION("Illegal argument exception"), INDEX_EXCEPTION("Index error"), NO_BEARER_TOKEN_ERROR("No bearer in token")
	, UNAUTHENTICATED_EXCEPTION("unauthenticated"), EMPTY_RESULT_DATA_ACCESS_EXCEPTION("No Data Found From Database");
	
	@Getter
	private String errorMessage;
	
	private ExceptionMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

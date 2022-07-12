package com.codedecode.demo.utils;

import lombok.Getter;

public enum CookieUtils {

	COOKIE_REFRESH("refresh_token");
	
	@Getter
	private String cookieName;
	
	private CookieUtils(String cookieName) {
		this.cookieName = cookieName;
	}
}

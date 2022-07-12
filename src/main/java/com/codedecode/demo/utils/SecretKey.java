package com.codedecode.demo.utils;

import lombok.Getter;

public enum SecretKey {
	
	ACCESS_SECRET_KEY("very_long_and_secure_and_safe_access_key"), REFRESH_SECRET_KEY("very_long_and_secure_and_safe_refresh_key");
	
	@Getter private String secretKey;
	
	private SecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}

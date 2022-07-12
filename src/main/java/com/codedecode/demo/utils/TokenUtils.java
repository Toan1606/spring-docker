package com.codedecode.demo.utils;

import lombok.Getter;

public enum TokenUtils {

	SECRET_TOKEN_WORD("Bearer ");
	
	@Getter
	private String secretToken;
	
	private TokenUtils(String secretToken) {
		this.secretToken = secretToken;
	}
}

package com.codedecode.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class LoginResponse {

	private final Token accessToken;

	private final Token refreshToken;

	public static final Long ACCESS_TOKEN_VADILITY = 1L;

	public static final Long REFRESH_TOKEN_VADILITY = 1L;

	private LoginResponse(Token accessToken, Token refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public static LoginResponse of(Long userId, String accessSecret, String refreshSecret) {
		return new LoginResponse(Token.of(userId, ACCESS_TOKEN_VADILITY, accessSecret),
				Token.of(userId, REFRESH_TOKEN_VADILITY, refreshSecret));
	}

	public static LoginResponse of(Long userId, String accessSecret, Token refreshToken) {
		return new LoginResponse(Token.of(userId, ACCESS_TOKEN_VADILITY, accessSecret), refreshToken);
	}
}

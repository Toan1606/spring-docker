package com.codedecode.demo.dto;

import com.codedecode.demo.service.JwtUtil;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class LoginResponseDTO {

	private final JwtUtil accessToken;

	private final JwtUtil refreshToken;

	private LoginResponseDTO(JwtUtil accessToken, JwtUtil refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public static LoginResponseDTO of(String email, String accessSecret, String refreshSecret) {
		return new LoginResponseDTO(JwtUtil.of(email, accessSecret),
				JwtUtil.of(email, refreshSecret));
	}

	public static LoginResponseDTO of(String email, String accessSecret, JwtUtil refreshToken) {
		return new LoginResponseDTO(JwtUtil.of(email, accessSecret), refreshToken);
	}
}

package com.codedecode.demo.dto;

import com.codedecode.demo.entity.User;
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

	private final User user;
	
	private LoginResponseDTO(JwtUtil accessToken, JwtUtil refreshToken, User user) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.user = user;
	}

	public static LoginResponseDTO of(String email, String accessSecret, String refreshSecret, User user) {
		return new LoginResponseDTO(JwtUtil.of(email, accessSecret),
				JwtUtil.of(email, refreshSecret), user);
	}

	public static LoginResponseDTO of(String email, String accessSecret, JwtUtil refreshToken, User user) {
		return new LoginResponseDTO(JwtUtil.of(email, accessSecret), refreshToken, user);
	}
}

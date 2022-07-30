package com.codedecode.demo.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.google.common.net.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "application.jwt")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {
	
	private String accessSecretKey;
	
	private String refreshSecretKey;
	
	private Integer tokenExpirationAfterDays;
	
	private String tokenPrefix;
	
	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}
}

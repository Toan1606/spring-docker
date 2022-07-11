package com.codedecode.demo.dto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
public class Token {
	private static final Logger LOGGER = LoggerFactory.getLogger(Token.class);

	@Getter
	private final String token;

	private Token(String token) {
		this.token = token;
	}

	public static Token of(Long userId, Long validityMinutes, String secretKey) {

		Instant issueDate = Instant.now();
		String token = Jwts.builder().setSubject(userId.toString()).setIssuer("CodeJava").setIssuedAt(new Date())
				.setExpiration(Date.from(issueDate.plus(validityMinutes, ChronoUnit.MINUTES)))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
		return new Token(token);
	}

	public static Token of(String token) {
		return new Token(token);
	}

	public static boolean validateAccessToken(String token, String secretKey) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		} catch (ExpiredJwtException e) {
			LOGGER.error("Jwts expired", e);
			return false;
		} catch (IllegalArgumentException e) {
			LOGGER.error("Token is null, empty or has only whitespace", e);
		} catch (MalformedJwtException e) {
			LOGGER.error("Jwt is invalid", e);
			return false;
		} catch (UnsupportedJwtException e) {
			LOGGER.error("Jwt is not supported", e);
			return false;
		} catch (SignatureException e) {
			LOGGER.error("Signature validation failed", e);
			return false;
		}
		return true;
	}

	public static Long getUserId(String token, String secretKey) {
		return Long.parseLong(getSubject(token, secretKey).trim());
	}

	public static String getSubject(String token, String secretKey) {
		return parseClaims(token, secretKey).getSubject();
	}

	private static Claims parseClaims(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

//	@SuppressWarnings("deprecation")
//	public static Long from(String token, String secretKey) {
//		System.out.println("secretKey: " + secretKey);
//		System.out.println(Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)));
//		Claims body = (Claims) Jwts.parserBuilder()
//				.setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)))
//				.build()
//				.parse(token);
//				
//		 return body.get("user_id", Long.class);
//	}
}

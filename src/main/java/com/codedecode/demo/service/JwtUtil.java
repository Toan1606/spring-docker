package com.codedecode.demo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codedecode.demo.entity.User;

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
@Getter
public class JwtUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

	private static final int EXPIRE_IN_MINUTE = 60 * 1000 * 1000;
	
	private String token;
	
	private JwtUtil(String token) {
		this.token = token;
	}
	
	public static JwtUtil of(User user, String secretKey) {

		Instant issueDate = Instant.now();
		String token = Jwts.builder()
				.setSubject(user.getEmail())
				.setIssuer("JobEz")
				.claim("roles", user.getRoles().toString())
				.setIssuedAt(new Date())
				.setExpiration(Date.from(issueDate.plus(EXPIRE_IN_MINUTE, ChronoUnit.MINUTES)))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		return new JwtUtil(token);
	}
	
	public static JwtUtil of(String email, String secretKey) {

		Instant issueDate = Instant.now();
		String token = Jwts.builder()
				.setSubject(email)
				.setIssuer("JobEz")
				.setIssuedAt(new Date())
				.setExpiration(Date.from(issueDate.plus(EXPIRE_IN_MINUTE, ChronoUnit.MINUTES)))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		return new JwtUtil(token);
	}
	
	public static JwtUtil of(String token) {
		return new JwtUtil(token);
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

	public Long getUserId(String token, String secretKey) {
		return Long.parseLong(getSubject(token, secretKey).trim());
	}

	public static String getSubject(String token, String secretKey) {
		return parseClaims(token, secretKey).getSubject();
	}
	
	public static String[] getRoles(String token, String secretKey) {
		Claims claims = parseClaims(token, secretKey);
		String rawRole = String.valueOf(claims.get("roles"));
		String []roles = rawRole.replace("[", "").replace("]", "").split(",");
		return Arrays.stream(roles).map(role -> role.trim()).toArray(String[]::new);
	}

	private static Claims parseClaims(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	public String getEmail(String token) {
		return null;
	}
}

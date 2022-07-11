package com.codedecode.demo.service;

import java.util.Base64;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.codedecode.demo.dto.LoginRequest;
import com.codedecode.demo.dto.LoginResponse;
import com.codedecode.demo.dto.RegisterRequestDTO;
import com.codedecode.demo.dto.Token;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.exception.UnauthenticatedException;
import com.codedecode.demo.exception.UserNotFoundException;
import com.codedecode.demo.repository.UserRepository;

@Service
@Transactional
public class AuthService {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final String accessTokenSecret;

	private final String refreshTokenSecret;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, 
			@Value("${application.security.access-token-secret}") String accessTokenSecret,
			@Value("${application.security.refresh-token-secret}") String refreshTokenSecret) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.accessTokenSecret = accessTokenSecret;
		this.refreshTokenSecret = refreshTokenSecret;
	}
	
	public User register(RegisterRequestDTO registerRequestDTO) {
		String email = registerRequestDTO.getEmail();
		String password = registerRequestDTO.getPassword();
		String confirmPassword = registerRequestDTO.getConfirmPassword();
		if (!Objects.equals(password, confirmPassword)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password does not match");
		}
		String encodePassword = passwordEncoder.encode(password);
//		(firstName, lastName, email, encodePassword)
		return userRepository.save(User.builder()
				.email(email)
				.password(encodePassword)
				.build());
	}
	
	public Token login(LoginRequest loginResponse) {
		String email = loginResponse.getEmail();
		String password = loginResponse.getPassword(); 
		// find user by email
		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials"));
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials");
		}
		
		return Token.of(user.getId(), 10L, accessTokenSecret);
	}
	
	public User getUserFromToken(String authorizationHeader) {
		String[] chunks = authorizationHeader.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String payload = new String(decoder.decode(chunks[1]));
		String userIdCharacter = payload.split(",")[0];
		Long userId = Long.parseLong(Character.toString(userIdCharacter.charAt(userIdCharacter.length() - 1)));
		
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		
		return user;
	}
	
	public LoginResponse checkLogin(LoginRequest loginResponse) {
		String email = loginResponse.getEmail();
		String password = loginResponse.getPassword(); 
		// find user by email
		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials"));
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials");
		}
		
		return LoginResponse.of(user.getId(), accessTokenSecret, refreshTokenSecret);
	}
	
	public LoginResponse refreshAccess(String refreshToken) {
		Long userId = Token.getUserId(refreshToken, refreshTokenSecret);
		LoginResponse loginResponse = LoginResponse.of(userId, accessTokenSecret, Token.of(refreshToken));
		return loginResponse;
	}

	public Boolean logout(String refreshToken) {
		Long userId = Token.getUserId(refreshToken, refreshTokenSecret);
		User user = userRepository.findById(userId).orElseThrow(() -> new UnauthenticatedException(HttpStatus.UNAUTHORIZED));
//		Boolean tokenIsRemoved = user.removeTokenIf(token -> Objects.equals(token.getToken(), refreshToken));
		Boolean tokenIsRemoved = true;
		if(tokenIsRemoved) {
			userRepository.save(user);
		}
		return tokenIsRemoved;
	}
}

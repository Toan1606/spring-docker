package com.codedecode.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.AddressRequestDTO;
import com.codedecode.demo.dto.LoginRequestDTO;
import com.codedecode.demo.dto.LoginResponseDTO;
import com.codedecode.demo.dto.LogoutResponseDTO;
import com.codedecode.demo.dto.RegisterRequestDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.AddressService;
import com.codedecode.demo.service.AuthService;
import com.codedecode.demo.service.JwtUtil;
import com.codedecode.demo.service.UserService;
import com.codedecode.demo.utils.CookieUtils;
import com.codedecode.demo.utils.ResponseMessage;
import com.codedecode.demo.utils.SecretKey;
import com.codedecode.demo.utils.TokenUtils;

@RestController
@RequestMapping("/candidate")
@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
public class UserController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<Address> getAddressByProvinceAndCity(@RequestBody AddressRequestDTO addressRequestDTO) {
		Long provinceId = addressRequestDTO.getProvinceId();
		Long cityId = addressRequestDTO.getCityId();
		
		Address address = addressService.findAddressByProvinceAndCity(provinceId, cityId);
		return ResponseEntity.ok().body(address);
	}
	
	/*
	 * 
	 *	@author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<User> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDTO));
	}
	
	/*
	 * 
	 *	@author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
		String email = loginRequestDTO.getEmail();
		String password = loginRequestDTO.getPassword();
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		authenticationManager.authenticate(token);
		
		User loginUser = userService.getUserByEmail(email);
		
		String jwtAccessToken = JwtUtil.of(email, SecretKey.ACCESS_SECRET_KEY.getSecretKey()).getToken();
		String jwtRefreshToken = JwtUtil.of(email, SecretKey.REFRESH_SECRET_KEY.getSecretKey()).getToken();
		
		Cookie cookie = new Cookie(CookieUtils.COOKIE_REFRESH.getCookieName(), jwtRefreshToken);
		cookie.setMaxAge(3600);
		cookie.setHttpOnly(true);
//		cookie.setPath("/api");
		response.addCookie(cookie);
		
		response.setHeader("Authorization", TokenUtils.SECRET_TOKEN_WORD.getSecretToken().concat(jwtRefreshToken));
		return ResponseEntity.status(HttpStatus.OK).body(LoginResponseDTO.builder()
				.accessToken(JwtUtil.of(jwtAccessToken))
				.refreshToken(JwtUtil.of(jwtRefreshToken))
				.user(loginUser)
				.build()
				);
	}
	
	/*
	 * 
	 *	@author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<JwtUtil> refresh(@CookieValue("refresh_token") String refreshToken) {
		String newRefreshToken = authService.refreshAccess(refreshToken).getAccessToken().getToken();
		JwtUtil token = JwtUtil.builder().token(newRefreshToken).build();
		return ResponseEntity.status(HttpStatus.OK).body(token);
	}
	
	/*
	 * 
	 *	@author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/logout")
	public ResponseEntity<LogoutResponseDTO> logout(@CookieValue("refresh_token") String refreshToken, HttpServletResponse response) {
		
		Cookie cookie = new Cookie(TokenUtils.SECRET_TOKEN_WORD.getSecretToken(), null);
		cookie.setMaxAge(0);
		cookie.setHttpOnly(true);
		
		response.addCookie(cookie);
		
		return ResponseEntity.status(HttpStatus.OK).body(LogoutResponseDTO.builder().message(ResponseMessage.LOGOUT_SUCCESS.getMessage()).build());
	}
}

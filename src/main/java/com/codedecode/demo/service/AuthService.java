package com.codedecode.demo.service;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.codedecode.demo.dto.LoginRequestDTO;
import com.codedecode.demo.dto.LoginResponseDTO;
import com.codedecode.demo.dto.RecruiterRegisterDTO;
import com.codedecode.demo.dto.RegisterRequestDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.ApplicationUserRole;
import com.codedecode.demo.entity.CV;
import com.codedecode.demo.entity.CandidateProfileSaved;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.Role;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.exception.UserNotFoundException;
import com.codedecode.demo.repository.UserRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class AuthService {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final String accessTokenSecret;

	private final String refreshTokenSecret;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CandidateProfileSavedService candidateProfileSavedService;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, 
			@Value("${application.security.access-token-secret}") String accessTokenSecret,
			@Value("${application.security.refresh-token-secret}") String refreshTokenSecret) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.accessTokenSecret = accessTokenSecret;
		this.refreshTokenSecret = refreshTokenSecret;
	}
	
	public User register(RegisterRequestDTO registerRequestDTO) {
		String fullName = registerRequestDTO.getFullName();
		String email = registerRequestDTO.getEmail();
		String password = registerRequestDTO.getPassword();
		Long provinceId = registerRequestDTO.getProvinceId();
		Long cityId = registerRequestDTO.getCityId();
		String phoneNumber = registerRequestDTO.getPhoneNumber();
		
//		if (!Objects.equals(password, confirmPassword)) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionMessage.PASSWORD_DON_NOT_MATCH.getErrorMessage());
//		}
		
		Address address = addressService.findAddressByProvinceAndCity(provinceId, cityId);
		
		String encodePassword = passwordEncoder.encode(password);
		Role role = roleService.findRoleByName(ApplicationUserRole.ROLE_CANDIDATE.name());
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		CV cv = new CV();
		CandidateProfileSaved candidateProfileSaved = new CandidateProfileSaved();
		
		User user = new User();
		user.setRoles(roles);
		user.setAddress(address);
		user.setName(fullName);
		user.setEmail(email);
		user.setPassword(encodePassword);
		user.setPhone(phoneNumber);
		user.setCv(cv);
		user.setDesiredJob(new DesiredJob());
		
		User rs = userRepository.save(user);
		candidateProfileSaved.setUser(rs);
		candidateProfileSavedService.saveCandidateProfileSaved(candidateProfileSaved);
	
		return rs;
	}
	
	public User recruiterRegister(RecruiterRegisterDTO registerRequestDTO) {
		String fullName = registerRequestDTO.getFullName();
		String email = registerRequestDTO.getEmail();
		String password = registerRequestDTO.getPassword();		
		
		CandidateProfileSaved candidateProfileSaved = new CandidateProfileSaved();
		
		String encodePassword = passwordEncoder.encode(password);
		Role role = roleService.findRoleByName(ApplicationUserRole.ROLE_RECRUITER.name());
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		
		User user = new User();
		user.setRoles(roles);
		user.setName(fullName);
		user.setEmail(email);
		user.setPassword(encodePassword);
		user.setDesiredJob(new DesiredJob());
		
		User rs = userRepository.save(user);
		candidateProfileSaved.setUser(rs);
		candidateProfileSavedService.saveCandidateProfileSaved(candidateProfileSaved);
		
		return rs;
	}
	
	public JwtUtil login(LoginRequestDTO loginResponse) {
		String email = loginResponse.getEmail();
		String password = loginResponse.getPassword(); 
		// find user by email
		User user = userRepository.findByEmail(email);
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials");
		}
		
		return JwtUtil.of(email, accessTokenSecret);
	}
	
	public User getUserFromToken(String authorizationHeader) {
		String[] chunks = authorizationHeader.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String payload = new String(decoder.decode(chunks[1]));
		String userIdCharacter = payload.split(",")[0];
		Long userId = Long.parseLong(Character.toString(userIdCharacter.charAt(userIdCharacter.length() - 1)));
		
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getErrorMessage()));
		
		return user;
	}
	
	public LoginResponseDTO checkLogin(LoginRequestDTO loginResponse) {
		String email = loginResponse.getEmail();
		String password = loginResponse.getPassword(); 
		// find user by email
		User user = userRepository.findByEmail(email);
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials");
		}
		
		return LoginResponseDTO.of(email, accessTokenSecret, refreshTokenSecret, user);
	}
	
	public LoginResponseDTO refreshAccess(String refreshToken) {
		String email = JwtUtil.getSubject(refreshToken, refreshTokenSecret);
		User user = userRepository.findByEmail(email);
		LoginResponseDTO loginResponse = LoginResponseDTO.of(email, accessTokenSecret, JwtUtil.of(refreshToken), user);
		return loginResponse;
	}
}

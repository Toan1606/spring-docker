package com.codedecode.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.FindAllUserResponseDTO;
import com.codedecode.demo.dto.PostingRecruiterResponseDTO;
import com.codedecode.demo.dto.RegisterRequestDTO;
import com.codedecode.demo.dto.UserRequestIdDTO;
import com.codedecode.demo.dto.UserResponseIdDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Street;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.AuthService;
import com.codedecode.demo.service.UserService;


@RestController
@RequestMapping("/recruiter")
@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
public class RecruiterLoginController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseIdDTO> findUserById(@RequestBody UserRequestIdDTO request) {
		Long userId = request.getUserId();
		User user = userService.findUserById(userId);
		List<Posting> postings = new ArrayList<Posting>(user.getPostings());
		
		List<PostingRecruiterResponseDTO> postingsDto = userService.convert(postings);
		System.out.println("Length : " + postingsDto.size());
		Address address = user.getAddress();
		Province province = address.getProvince();
		City city = address.getCity();
		Street street = address.getStreet();

		UserResponseIdDTO response = UserResponseIdDTO
				.builder()
				.recruiterId(user.getId())
				.recruiterName(user.getName())
				.description(user.getDescription())
				.city(city.getName())
				.province(province.getName())
				.street(street.getName())
				.recruiterDescription(user.getRecruiterDescription().substring(0,50))
				.taxNumber(user.getTaxtNumber())
				.postings(postingsDto)
				.build();
		
		return new ResponseEntity<UserResponseIdDTO>(response, HttpStatus.OK); 
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
	@PostMapping(value = "/find-all")
	public ResponseEntity<List<FindAllUserResponseDTO>> findAllRecruiter() {
		List<User> users = userService.findAllRecruiter();
		List<FindAllUserResponseDTO> usersDto = userService.convertFindAllUser(users);
		return ResponseEntity.status(HttpStatus.CREATED).body(usersDto);
	}
	
}

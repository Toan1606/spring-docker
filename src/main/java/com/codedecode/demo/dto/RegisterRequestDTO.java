package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
	private Long id;

	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;

	@JsonProperty("confirm_password")
	private String confirmPassword;
	
	@JsonProperty("phone_number")
	private String phoneNumber;
	
	@JsonProperty("province_id")
	private Long provinceId;
	
	@JsonProperty("city_id")
	private Long cityId;
	
}

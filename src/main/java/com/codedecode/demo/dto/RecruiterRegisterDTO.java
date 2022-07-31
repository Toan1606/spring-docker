package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterRegisterDTO {
	private Long id;

	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;
}

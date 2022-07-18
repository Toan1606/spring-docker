package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

	private Long id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;
}

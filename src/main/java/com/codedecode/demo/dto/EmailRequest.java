package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class EmailRequest {

	@JsonProperty("email")
	private String email;
}

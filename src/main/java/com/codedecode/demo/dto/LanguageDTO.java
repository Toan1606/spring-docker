package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
@Builder
public class LanguageDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("certificate_name")
	private String certificate_name;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("mark")
	private float mark;
	
	@JsonProperty("userId")
	private Long userId;
}

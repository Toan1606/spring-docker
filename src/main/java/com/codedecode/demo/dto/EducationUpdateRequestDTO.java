package com.codedecode.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EducationUpdateRequestDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("classification")
	private String classification;
	
	@JsonProperty("major")
	private String major;
	
	@JsonProperty("universityName")
	private String universityName;
}

package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPostingRequestDTO {
	private Long id;
	
	@JsonProperty("email")
	private String email;

	@JsonProperty("position")
	private String position;

	@JsonProperty("workingForm")
	private long workingForm;

	@JsonProperty("salary")
	private long salary;

	@JsonProperty("quantity")
	private int quantity;
	
	@JsonProperty("degreeRequired")
	private String degreeRequired;
	
	@JsonProperty("genderRequirement")
	private String genderRequirement;
	
	@JsonProperty("benefits")
	private String benefits;
	
	@JsonProperty("files")
	private String files;
	
	@JsonProperty("deadlineForSubmission")
	private String deadlineForSubmission;
}

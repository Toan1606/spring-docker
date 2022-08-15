package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateDesireJobRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("desiredId")
	private Long desiredId;
	
	@JsonProperty("desiredJobName")
	private String desiredJobName;
	
	@JsonProperty("rankId")
	private Long rankId;
	
	@JsonProperty("WorkingFormId")
	private Long WorkingFormId;
	
	@JsonProperty("yearOfExperienceId")
	private Long yearOfExperienceId;
	
	@JsonProperty("salaryId")
	private Long salaryId;
	
	@JsonProperty("addresssId")
	private List<Long> addresssId;
	
	@JsonProperty("postingCategoryId")
	private Long postingCategoryId;
}

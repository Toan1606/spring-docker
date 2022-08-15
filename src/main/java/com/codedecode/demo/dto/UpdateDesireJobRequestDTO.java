package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

@Getter
public class UpdateDesireJobRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long desiredId;
	
	private String desiredJobName;
	
	private Long rankId;
	
	private Long WorkingFormId;
	
	private Long yearOfExperienceId;
	
	private Long salaryId;
	
	private List<Long> addresssId;
	
	private Long postingCategoryId;
}

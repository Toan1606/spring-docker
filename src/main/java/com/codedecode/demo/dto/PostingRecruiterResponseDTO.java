package com.codedecode.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostingRecruiterResponseDTO {
	
	private Long id;
	
	private String jobName;
	
	private String deadlineForSubmission;
	
	private String position;
	
	private String degreeRequired;
	
	private String description;
	
	private String salary;
	
	private String jobRequirement;
	
	private String genderRequirement;
	
	private String province;
	
	private String workingForm;
	
	private String postingType;
	
	private String image;
}

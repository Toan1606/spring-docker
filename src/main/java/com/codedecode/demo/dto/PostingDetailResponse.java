package com.codedecode.demo.dto;


import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostingDetailResponse implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String benefits;
	
	private String commission;
	
	private String deadlineForSubmission;
	
	private String degreeRequired;
	
	private String description;
	
	private String emailContact;
	
	private String file;
	
	private String genderRequirement;
	
	private String images;
	
	private String jobName;
	
	private String jobRequirement;
	
	private String phoneNumber;
	
	private String position;
	
	private String profileIncluded;
	
	private int quantity;
	
	private String quantityNeeded;
	
	private Long view;
	
	private Long rankId;
	
	private Long salaryId;
	
	private Long workingFormId;
	
	private String workingForm;
	
	private Long yearOfExperienceId;
	
	private Long companyId;
	
	private String companyName;
	
	private Long postingCategoryId;
	
	private String postingCategoryName;
	
	private String salary;
	
	private String yearOfExperience;
	
	private List<String> province;
	
	private List<String> cities;
	
	private List<String> street;
}

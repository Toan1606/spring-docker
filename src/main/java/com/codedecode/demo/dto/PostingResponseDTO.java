package com.codedecode.demo.dto;




import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostingResponseDTO implements Serializable {

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
	
	private Integer quantity;
	
	private String quantityNeeded;
	
	private Long view;
	
	private Long addressId;
	
	private Long rankId;
	
	private Long salaryId;
	
	private Long workingFormId;
	
	private Long yearOfExperienceId;
	
	private String companyName;
}

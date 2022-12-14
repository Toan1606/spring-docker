package com.codedecode.demo.dto;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.codedecode.demo.entity.Notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PostingDetailResponse implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String recruiterEmail;

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
	
	private Long companyId;
	
	private String companyName;
	
	private Long postingCategoryId;
	
	private String postingCategoryName;
	
	private String salary;
	
	private List<Map<String, String>> province;
	
	private List<Map<String, String>> cities;
	
	private List<String> street;
	
	private List<CityResponseDTO> citiesDto;
	
	private List<PostingRelatedDTO> relatedPosting;
	
	private List<Notification> notifications;
}

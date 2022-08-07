package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.entity.WorkExperiences;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateByIdResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String gender;
	
	private String dateOfBirth;
	
	private String profileCode;
	
	private String desiredJob;
	
	private String mariaStatus;
	
	private String phone;
	
	private String email;
	
	private String province;
	
	private String city;
	
	private String workplaceDesired;
	
	private String yearOfExperience;
	
	private String salary;
	
	private String workingForm;
	
	private String rank;
	
	private String careerGoal;
	
	private String skill;
	
	private List<WorkExperiences> workExperiences;
	
	private List<Degree> degrees;
}

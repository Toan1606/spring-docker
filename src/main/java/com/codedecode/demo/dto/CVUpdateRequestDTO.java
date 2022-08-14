package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CVUpdateRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("career_goal")
	private String careerGoal;
	
	@JsonProperty("skills")
	private List<SkillRequestDTO> skills;
	
	@JsonProperty("awards")
	private String awards;
	
	@JsonProperty("degrees")
	private List<DegreeUpdateRequestDTO> degrees;
	
	@JsonProperty("hobbies")
	private String hobbies;
	
	@JsonProperty("images")
	private String images;
	
	@JsonProperty("educations")
	private List<EducationUpdateRequestDTO> educations;
	
	@JsonProperty("work_experiences")
	private List<WorkExperienceUpdateRequestDTO> workExperiences;
	
	@JsonProperty("activities")
	private List<ActivityUpdateRequestDTO> activities;
	
	@JsonProperty("involved_projects")
	private List<InvolvedUpdateRequestDTO> involvedProjects;
	
}

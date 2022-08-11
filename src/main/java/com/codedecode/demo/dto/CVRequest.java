package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.codedecode.demo.entity.Activity;
import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.entity.Education;
import com.codedecode.demo.entity.InvolvedProject;
import com.codedecode.demo.entity.Skill;
import com.codedecode.demo.entity.WorkExperiences;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CVRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("name")
	private String candidateName;
	
	@JsonProperty("desired_jobName")
	private String desiredJobName;
	
	@JsonProperty("images")
	private String images;
	
	@JsonProperty("position")
	private String position;
	
	@JsonProperty("date_of_birth")
	private String dateOfBirth;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("province")
	private String province;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("street")
	private String street;
	
	@JsonProperty("career_goal")
	private String careerGoal;
	
	@JsonProperty("skills")
	private List<Skill> skills;
	
	@JsonProperty("awards")
	private String awards;
	
	@JsonProperty("degrees")
	private List<Degree> degrees;
	
	@JsonProperty("hobbies")
	private String hobbies;
	
	@JsonProperty("educations")
	private List<Education> educations;
	
	@JsonProperty("work_experiences")
	private List<WorkExperiences> workExperiences;
	
	@JsonProperty("activities")
	private List<Activity> activities;
	
	@JsonProperty("involved_projects")
	private List<InvolvedProject> involvedProjects;
}

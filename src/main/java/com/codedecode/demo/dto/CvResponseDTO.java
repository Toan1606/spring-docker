package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.codedecode.demo.entity.Activity;
import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.entity.Education;
import com.codedecode.demo.entity.InvolvedProject;
import com.codedecode.demo.entity.Skill;
import com.codedecode.demo.entity.WorkExperiences;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CvResponseDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String images;
	
	private String position;
	
	private String name;
	
	private String dateOfBirth;
	
	private String gender;
	
	private String phone;
	
	private String email;
	
	private String province;
	
	private String city;
	
	private String street;
	
	private String facebook;
	
	private String careerGoal;
	
	private List<Skill> skills;
	
	private String awards;
	
	private List<Degree> degrees; 
	
	private String hobbies;
	
	private List<Education> educations;
	
	private List<WorkExperiences> workExperiences;
	
	private Collection<Activity> activities;
	
	private List<InvolvedProject> involvedProjects;
}

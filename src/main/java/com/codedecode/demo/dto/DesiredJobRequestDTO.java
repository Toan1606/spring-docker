package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DesiredJobRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Rank> ranks;
	
	private List<WorkingForm> workingForms;
	
	private List<WorkExperiences> workExperiences;
	
	private List<YearOfExperience> yearOfExperiences;
	
	private List<Salary> salaries;
	
	private List<Province> provinces;
	
	private List<PostingCategory> postingCategories;
}

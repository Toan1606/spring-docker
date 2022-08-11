package com.codedecode.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkExperienceDTO {

	@JsonProperty("companyName")
	private String companyName;
	
	@JsonProperty("position")
	private String position;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("startDate")
	private Date startDate;
	
	@JsonProperty("endDate")
	private Date endDate;
	
	@JsonProperty("userId")
	private Long userId;
}

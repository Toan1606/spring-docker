package com.codedecode.demo.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WorkExperienceDTO {

	private String companyName;
	private String position;
	private String description;
	private Date startDate;
	private Date endDate;
	private Long userId;
}

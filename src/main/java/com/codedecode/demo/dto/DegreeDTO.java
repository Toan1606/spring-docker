package com.codedecode.demo.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DegreeDTO {
	
	private Long id;
	private String name;
	private String certificateName;
	private String teachingUnit;
	private Date startTime;
	private Date endTime;
	private String major;
	private Long userId;
}

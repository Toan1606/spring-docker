package com.codedecode.demo.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostingSearchCategory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer rowNumber;
	
	private String images;
	
	private String position;
	
	private Long postingId;
	
	private String jobName;
	
	private String deadlineForSubmission;
	
	private String companyId;
	
	private String companyName;
	
	private String salary;
	
	private String province;
	
	private Integer postingCategoryId;
	
}

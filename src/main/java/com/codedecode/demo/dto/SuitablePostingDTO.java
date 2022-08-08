package com.codedecode.demo.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuitablePostingDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String images;
	
	private String position;
	
	private String companyName;
	
	private String province;
	
	private String salary;
	
	private String deadLineForSubmission;
}

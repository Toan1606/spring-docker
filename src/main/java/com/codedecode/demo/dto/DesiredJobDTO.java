package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.Collection;

import com.codedecode.demo.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesiredJobDTO implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String jobName;
	private String workingForm;
	private Long workingFormId;
	private String yearOfExp;
	private Long yearOfExpId;
	private String rank;
	private Long rankId;
	private String salary;
	private Long salaryId;
	private Long userId;
	private Collection<Address> address;
	
}

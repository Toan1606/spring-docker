package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.codedecode.demo.entity.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
	private List<AddressDesiredJobDTO> address;
	
}

package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.Collection;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;

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
	private WorkingForm workingForm;
	private YearOfExperience yearOfExp;
	private Rank rank;
	private Salary salary;
	private Long userId;
	private Collection<Address> address;
	
}

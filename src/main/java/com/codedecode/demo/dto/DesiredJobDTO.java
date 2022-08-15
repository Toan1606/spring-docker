package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.Collection;

import com.codedecode.demo.entity.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DesiredJobDTO implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String major;
	private String workingForm;
	private String yearOfExp;
	private String rank;
	private String salary;
	private Long userId;
	private Collection<Address> address;
	
}

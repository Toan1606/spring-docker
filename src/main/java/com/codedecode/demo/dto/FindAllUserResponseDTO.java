package com.codedecode.demo.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindAllUserResponseDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String recruiterDescription;
	
	private String image;
}

package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.codedecode.demo.entity.Posting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseIdDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long recruiterId;
	
	private String recruiterName;
	
	private String description;
	
	private String city;
	
	private String province;
	
	private String taxNumber;
	
	private List<Posting> postings;
	
	
}

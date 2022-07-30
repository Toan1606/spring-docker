package com.codedecode.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostingDetailResponse extends PostingResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> provinces;
	
	private List<String> cities;
	
	private String street;
}

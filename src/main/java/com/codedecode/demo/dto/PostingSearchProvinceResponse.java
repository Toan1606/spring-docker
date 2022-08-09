package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.List;


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
public class PostingSearchProvinceResponse implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PostingSearchProvince> postings;
	
	private int numberOfRecords;

}

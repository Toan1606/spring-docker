package com.codedecode.demo.dto;


import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AppliedJobDTOResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
    private String deadlineForSubmission;
    
    private String dateSubmission;
    
    private String commentFromEmployer;
}

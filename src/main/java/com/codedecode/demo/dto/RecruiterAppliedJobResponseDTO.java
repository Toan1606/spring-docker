package com.codedecode.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterAppliedJobResponseDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long candidateId;
	
	private String candidateName;
	
	private Long postingId;
	
	private String postingPosition;
	
	private String appliedDate;
	
	private String commentFromEmployer;
}

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterMangementResponseDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numberOfApplieds;
	
	private int numberOfPostings;
	
	private List<AppliedCandidateDTO> appliedCandidate;
	
	private List<Posting> lastestPostings;
}

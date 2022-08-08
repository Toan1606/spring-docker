package com.codedecode.demo.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralManagementDTO {

	private Set<SuitablePostingDTO> suitableJob;
	
	private int numberOfAppliedJob;
	
	private int numberOfSuitableJob;
	
	private int view;
}

package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class CandidateFindByIdDTO {
	
	@JsonProperty("candidate_id")
	private Long canddiateId;
}

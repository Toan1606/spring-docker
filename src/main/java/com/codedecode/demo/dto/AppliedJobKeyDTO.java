package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AppliedJobKeyDTO {
	
	@JsonProperty("candidate_id")
	private Long candidateId;
	
	@JsonProperty("recruiter_id")
	private Long recruiterId;
	
	@JsonProperty("posting_id")
	private Long postingId;
}

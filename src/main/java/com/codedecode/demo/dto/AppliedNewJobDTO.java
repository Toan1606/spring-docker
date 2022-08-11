package com.codedecode.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppliedNewJobDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("candidte_email")
	private String candidateEmail;
	
	@JsonProperty("recruiter_email")
	private String recruiterEmail;
	
	@JsonProperty("posting_id")
	private Long postingId;
}

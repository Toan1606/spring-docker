package com.codedecode.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavedNewJobDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("posting_id")
	private Long postingId;
}
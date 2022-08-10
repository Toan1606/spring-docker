package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SavedJobKeyDTO {

	@JsonProperty("user_id")
	private Long userId;
	
	@JsonProperty("posting_id")
	private Long postingId;
}

package com.codedecode.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class PostingRequestDTO {
	@JsonProperty("user_id")
	private Long userId;
	
	@JsonProperty("posting_id")
	private Long postingId;
}

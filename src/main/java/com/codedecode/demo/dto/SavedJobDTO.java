package com.codedecode.demo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SavedJobDTO {

	private String postingJobname;
	private String postingPosition;
	private String deadlineForSubmission;
	private Long postingId;
	private Long userId;
}

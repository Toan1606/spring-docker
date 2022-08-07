package com.codedecode.demo.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SavedJobDTO {

	private Long id;
	private String postingJobname;
	private String postingPosition;
	private String deadlineForSubmission;
	
}

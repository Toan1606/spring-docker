package com.codedecode.demo.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AppliedJobDTO {

	private Long id;
	private String positionJobname;
	private String postingPosition;
	private Date dateSubmission;
	private String deadlineForSubmission;
	private String commentFromEmployer;
}

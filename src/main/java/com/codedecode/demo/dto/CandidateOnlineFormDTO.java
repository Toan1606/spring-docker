package com.codedecode.demo.dto;

import java.util.List;

import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.entity.Salary;

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
public class CandidateOnlineFormDTO {
	private List<YearOfExperience> yearOfExperience;
	private List<WorkingForm> workingForm;
	private List<Salary> salary;
}

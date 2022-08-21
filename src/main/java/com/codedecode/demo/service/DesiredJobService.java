package com.codedecode.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.exception.DesiredJobNotFound;
import com.codedecode.demo.exception.DesiredJobUpdateException;
import com.codedecode.demo.repository.DesiredJobRepository;

@Service
@Transactional
public class DesiredJobService {

	
	@Autowired
	private DesiredJobRepository desiredJobRepository ;
	
	public DesiredJob saveDesiredJob(DesiredJob desiredJob) {
		return desiredJobRepository.save(desiredJob);
	}

	public DesiredJob update(DesiredJob desiredJob, String jobName, Rank rank, WorkingForm workingForm, YearOfExperience yearOfExperience, Salary salary, Set<Address> addresss,
			PostingCategory postingCategory) {
		desiredJob.setRank(rank);
		desiredJob.setName(jobName);
		desiredJob.setWorkingForm(workingForm);
		desiredJob.setYearOfExperience(yearOfExperience);
		desiredJob.setSalary(salary);
		desiredJob.setWorkPlaceDesired(addresss);
		desiredJob.setPostingCategory(postingCategory);
		DesiredJob savedDesiredJob = desiredJobRepository.save(desiredJob);
		
		if (!savedDesiredJob.equals(desiredJob)) {
			throw new DesiredJobUpdateException("Desired Job Update Exception");
		}
		
		return desiredJob;
	}
	
	public DesiredJob updateFormOnline(Long userId, WorkingForm workingForm, YearOfExperience yearOfExperience, Salary salary) {
		DesiredJob desiredJob = desiredJobRepository.findDesiredJobByUserId(userId);
		desiredJob.setWorkingForm(workingForm);
		desiredJob.setYearOfExperience(yearOfExperience);
		desiredJob.setSalary(salary);
		desiredJobRepository.save(desiredJob);
		
		return desiredJob;
	}

	public DesiredJob findById(Long desiredJobId) {
		return desiredJobRepository.findById(desiredJobId).orElseThrow(() -> new DesiredJobNotFound("Desired Job Not Found"));
	}
	
}

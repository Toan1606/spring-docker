package com.codedecode.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.dto.WorkExperienceDTO;
import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.repository.WorkExperienceRepository;

@Service
public class WorkExperienceService {
	@Autowired
	private WorkExperienceRepository workExperienceRepository;

	public List<WorkExperiences> getAllWorkExp(Long userId) {
		return workExperienceRepository.getAllWorkExpByUserId(userId);
	}

	public WorkExperiences getWorkExpById(Long id) {
		return workExperienceRepository.getWorkExpById(id);
	}

	public WorkExperiences addWorkExp(WorkExperiences workexp) {
		return workExperienceRepository.save(workexp);
	}

	public void deleteWorkExp(Long id) {
		workExperienceRepository.deleteWorkExpById(id);
	}

//	public boolean isDuplicate(WorkExperiences workexp) {
//		List<WorkExperiences> list = workExperienceRepository.getAllWorkExpByUserId(workexp.getCv().getUser().getId());
//		
//		for (WorkExperiences w : list) {
//			if(w.getCompanyName().equalsIgnoreCase(workexp.getCompanyName()) 
//					&& w.getPosition().equalsIgnoreCase(workexp.getPosition()) 
//					&& w.getDescription().equalsIgnoreCase(workexp.getDescription()) 
//					&& w.getCv().equals(workexp.getCv())
//					&& (formatDate(w.getStartDate()).compareTo(formatDate(workexp.getStartDate())) == 0)
//					&& (formatDate(w.getEndDate()).compareTo(formatDate(workexp.getEndDate())) == 0)) {
//				return true;
//			}
//		}
//		return false;
//	}

	public String formatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
}

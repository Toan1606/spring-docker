package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.dto.EducationUpdateRequestDTO;
import com.codedecode.demo.exception.NotUpdateException;
import com.codedecode.demo.repository.EducationRepository;

@Service
@Transactional
public class EducationService {

	private final EducationRepository educationRepository;

	@Autowired
	public EducationService(EducationRepository educationRepository) {
		this.educationRepository = educationRepository;
	}

	public Integer updateEducation(List<EducationUpdateRequestDTO> educations) {
		if (educations == null)
			return -1;
		
		int effectColumns = 0;
		
		for(EducationUpdateRequestDTO education : educations) {
			Long id = education.getId();
			String classification = education.getClassification();
			String major = education.getMajor();
			String universityName = education.getUniversityName();
			
			int effect = educationRepository.updateEducation(id, classification, major, universityName);
			effectColumns += effect;
		}
		
		if (effectColumns != educations.size()) {
			throw new NotUpdateException("Skill Is Not Update");
		}
		
		return effectColumns;
	}
}

package com.codedecode.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.CV;
import com.codedecode.demo.exception.NotUpdateException;
import com.codedecode.demo.repository.CVRepository;

@Service
@Transactional
public class CVService {
	@Autowired
	private CVRepository cvRepository;

	/*
	 * 
	 * @author: TuanNV
	 * 
	 */
	public CV getCVById(Long id) {
		return cvRepository.getCVById(id);
	}

	/*
	 * 
	 * @author: TuanNV
	 * 
	 */
	public CV getCVsByUserId(Long id) {
		return cvRepository.getCVsByUserId(id);
	}

	/*
	 * 
	 * @author: TuanNV
	 * 
	 */
	public void update(CV cv) {
		cvRepository.save(cv);
	}

	public CV findCvByCandidateId(Long candidateId) {
		return cvRepository.findByUser_Id(candidateId);
	}

	public Integer updateCv(CV cv, String newCareerGoal, String newAwards, String newHobbies, String newImages) {
		String careerGoal = cv.getCareerJobObjective();
		String awards = cv.getAward();
		String hobbies = cv.getHobbies();
		String images = cv.getImages();

		// set default value
		careerGoal = careerGoal == null ? "" : careerGoal;
		awards = awards == null ? "" : awards;
		hobbies = hobbies == null ? "" : hobbies;
		images = images == null ? "" : images;
		
		newCareerGoal = newCareerGoal == null ? careerGoal : newCareerGoal;
		newAwards = newAwards == null ? awards : newAwards;
		newHobbies = newHobbies == null ? hobbies : newHobbies;
		newImages = newImages == null ? images : newImages;
		
		

		if (careerGoal.equals(newCareerGoal) && awards.equals(newAwards) && hobbies.equals(newHobbies)
				&& images.equals(newImages)) {
			return -1;
		}

		int effectColumns = cvRepository.updateCv(cv.getId(), newAwards, newCareerGoal, newHobbies, newImages);

		if (effectColumns != 1) {
			throw new NotUpdateException("Cv Is Not Update");
		}

		return effectColumns;
	}
}

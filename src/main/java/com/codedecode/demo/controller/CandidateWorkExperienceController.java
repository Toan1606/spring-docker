package com.codedecode.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.WorkExperienceDTO;
import com.codedecode.demo.entity.CV;
import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.service.CVService;
import com.codedecode.demo.service.WorkExperienceService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/workexp")
public class CandidateWorkExperienceController {

	@Autowired
	private WorkExperienceService workExperienceService;

	@Autowired
	private CVService cvService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> showWorkExpPage(@PathVariable Long userId) {
		List<WorkExperiences> list = workExperienceService.getAllWorkExp(userId);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<WorkExperiences>>(list, HttpStatus.OK);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteWorkExp(@PathVariable Long id) {
		WorkExperiences w = workExperienceService.getWorkExpById(id);
		if (w == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		} else {
			workExperienceService.deleteWorkExp(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> addWorkExp(@RequestBody WorkExperienceDTO workexpDTO) {
		CV cv = cvService.getCVsByUserId(workexpDTO.getUserId());
		WorkExperiences workexp = new WorkExperiences();
		workexp.setCompanyName(workexpDTO.getCompanyName());
		workexp.setPosition(workexpDTO.getPosition());
		workexp.setDescription(workexpDTO.getDescription());
		workexp.setStartDate(workexpDTO.getStartDate());
		workexp.setEndDate(workexpDTO.getEndDate());
		workexp.setCv(cv);
		workExperienceService.addWorkExp(workexp);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}
//	@PostMapping("/isDuplicate")
//	public ResponseEntity<?> isDuplicate(@RequestBody WorkExperienceDTO workExperienceDTO){
//		CV cv = cvService.getCVsByUserId(workExperienceDTO.getUserId());
//		WorkExperiences workExperiences = new WorkExperiences();
//		workExperiences.setCompanyName(workExperienceDTO.getCompanyName());
//		workExperiences.setPosition(workExperienceDTO.getPosition());
//		workExperiences.setStartDate(workExperienceDTO.getStartDate());
//		workExperiences.setEndDate(workExperienceDTO.getEndDate());
//		workExperiences.setDescription(workExperienceDTO.getDescription());
//		workExperiences.setCv(cv);
//		boolean isDuplicate = workExperienceService.isDuplicate(workExperiences);
//		return new ResponseEntity<Boolean>(isDuplicate, HttpStatus.OK);
//	}
}

package com.codedecode.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.EducationDegreeDTO;
import com.codedecode.demo.entity.CV;
import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.service.CVService;
import com.codedecode.demo.service.DegreeService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/degree")
@Transactional
public class CandidateDegreeController {

	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private CVService cvService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> showDegreePage(@PathVariable Long userId){
		List<Degree> list = degreeService.getAllDegreeByUserId(userId);
		if(list == null || list.size() == 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Degree>>(list, HttpStatus.OK);
	}
	@GetMapping("/edit/{id}")
	public ResponseEntity<?> getDegreeById(@PathVariable Long id){
		Degree degree = degreeService.getDegreeById(id);
		EducationDegreeDTO degreeDTO = new EducationDegreeDTO();
		degreeDTO.setId(id);
		degreeDTO.setCertificateName(degree.getCertificateName());
		degreeDTO.setStartTime(degree.getStartTime());
		degreeDTO.setEndTime(degree.getEndTime());
		degreeDTO.setMajor(degree.getMajor());
		degreeDTO.setRank(degree.getRank());
		degreeDTO.setSupplementaryInformation(degree.getSupplementaryInformation());
		degreeDTO.setTeachingUnit(degree.getTeachingUnit());
		return new ResponseEntity<EducationDegreeDTO>(degreeDTO, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addDegree(@RequestBody EducationDegreeDTO educationDegreeDTO){
		Degree degree = new Degree();
		degree.setCertificateName(educationDegreeDTO.getCertificateName());
		degree.setStartTime(educationDegreeDTO.getStartTime());
		degree.setEndTime(educationDegreeDTO.getEndTime());
		degree.setMajor(educationDegreeDTO.getMajor());
		degree.setRank(educationDegreeDTO.getRank());
		degree.setTeachingUnit(educationDegreeDTO.getTeachingUnit());
		degree.setSupplementaryInformation(educationDegreeDTO.getSupplementaryInformation());
		List<Degree> list = degreeService.getAllDegreeByUserId(educationDegreeDTO.getUserId());
		list.add(degree);
		CV cv = cvService.getCVsByUserId(educationDegreeDTO.getUserId());
		cv.setDegrees(list);
		cvService.update(cv);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateDegree(@RequestBody EducationDegreeDTO educationDegreeDTO){
		List<Degree> list = degreeService.getAllDegreeByUserId(educationDegreeDTO.getUserId());
		for (Degree degree : list) {
			if(degree.getId() == educationDegreeDTO.getId()) {
				degree.setCertificateName(educationDegreeDTO.getCertificateName());
				degree.setStartTime(educationDegreeDTO.getStartTime());
				degree.setEndTime(educationDegreeDTO.getEndTime());
				degree.setMajor(educationDegreeDTO.getMajor());
				degree.setRank(educationDegreeDTO.getRank());
				degree.setTeachingUnit(educationDegreeDTO.getTeachingUnit());
				degree.setSupplementaryInformation(educationDegreeDTO.getSupplementaryInformation());
				break;
			}
		}
		CV cv = cvService.getCVsByUserId(educationDegreeDTO.getUserId());
		cv.setDegrees(list);
		cvService.update(cv);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}/{userId}")
	public ResponseEntity<?> deleteDegree(@PathVariable Long id, @PathVariable Long userId){
		List<Degree> list = degreeService.getAllDegreeByUserId(userId);
		for (Degree degree : list) {
			if(degree.getId() == id) {
				list.remove(degree);
				CV cv = cvService.getCVsByUserId(userId);
				cv.setDegrees(list);
				cvService.update(cv);
				degreeService.deleteDegree(id);
				break;
			}
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	@PostMapping("/isDuplicate")
	public ResponseEntity<?> isDuplicate(@RequestBody EducationDegreeDTO degreeDTO){
//		Degree degree = new Degree();
		CV cv = cvService.getCVsByUserId(degreeDTO.getUserId());
//		degree.setCertificateName(degreeDTO.getCertificateName());
//		degree.setTeachingUnit(degreeDTO.getTeachingUnit());
//		degree.setStartTime(degreeDTO.getStartTime());
//		degree.setEndTime(degreeDTO.getEndTime());
//		degree.setMajor(degreeDTO.getMajor());
//		degree.setRank(degreeDTO.getRank());
//		degree.setSupplementaryInformation(degreeDTO.getSupplementaryInformation());
		boolean isDuplicate = degreeService.isDuplicate(degreeDTO, cv);
		return new ResponseEntity<Boolean>(isDuplicate, HttpStatus.OK);
	}
}

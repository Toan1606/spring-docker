package com.codedecode.demo.controller;

import java.util.ArrayList;
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
	public ResponseEntity<?> showDegreePage(@PathVariable Long userId) {
		List<Degree> list = degreeService.getAllDegreeByUserId(userId);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		List<EducationDegreeDTO> dDTO = new ArrayList<EducationDegreeDTO>();
		for (Degree degree : list) {
			EducationDegreeDTO degreeDTO = new EducationDegreeDTO();
			degreeDTO.setId(degree.getId());
			degreeDTO.setCertificateName(degree.getCertificateName());
			degreeDTO.setStartTime(degree.getStartTime());
			degreeDTO.setEndTime(degree.getEndTime());
			degreeDTO.setMajor(degree.getMajor());
			degreeDTO.setRank(degree.getRank());
			degreeDTO.setSupplementaryInformation(degree.getSupplementaryInformation());
			degreeDTO.setTeachingUnit(degree.getTeachingUnit());
			degreeDTO.setUserId(userId);
			dDTO.add(degreeDTO);
		}
		return new ResponseEntity<List<EducationDegreeDTO>>(dDTO, HttpStatus.OK);
	}

	@GetMapping("/edit/{id}")
	public ResponseEntity<?> getDegreeById(@PathVariable Long id) {
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
	public ResponseEntity<?> addDegree(@RequestBody EducationDegreeDTO educationDegreeDTO) {
		if (!degreeService.isDuplicate(educationDegreeDTO)) {
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
		} else {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateDegree(@RequestBody EducationDegreeDTO educationDegreeDTO) {
		List<Degree> list = degreeService.getAllDegreeByUserId(educationDegreeDTO.getUserId());
		CV cv = cvService.getCVsByUserId(educationDegreeDTO.getUserId());
//		boolean isDuplicate = degreeService.isDuplicateUpdate(educationDegreeDTO);
//		if (!isDuplicate) {
		for (Degree degree : list) {
			if (degree.getId() == educationDegreeDTO.getId()) {
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

		cv.setDegrees(list);
		cvService.update(cv);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//		} else {
//			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
//		}

	}

	@DeleteMapping("/delete/{id}/{userId}")
	public ResponseEntity<?> deleteDegree(@PathVariable Long id, @PathVariable Long userId) {
		List<Degree> list = degreeService.getAllDegreeByUserId(userId);
		for (Degree degree : list) {
			if (degree.getId() == id) {
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
}

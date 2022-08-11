package com.codedecode.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.CVRequestDTO;
import com.codedecode.demo.dto.CvResponseDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.CV;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.Education;
import com.codedecode.demo.entity.InvolvedProject;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Skill;
import com.codedecode.demo.entity.Street;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.service.CVService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/cv")
public class CvController {
	
	@Autowired
	private CVService cvService;
	
	@PostMapping(value = "/id")
	public ResponseEntity<CvResponseDTO> findCvByCandidateId(@RequestBody CVRequestDTO request) {
		Long candidateId = request.getCandidateId();
		CV cv = cvService.findCvByCandidateId(candidateId);
		System.out.println("Cv : " + cv);
		User candidate = cv.getUser();
		Address address = candidate.getAddress();
		Province province = address.getProvince();
		City city = address.getCity();
		Street street = address.getStreet();
		String facebook = new StringBuilder("https://www.facebook.com/").append(candidate.getEmail().split("@")[0]).toString();
		DesiredJob desiredJob = candidate.getDesiredJob();
		cv.getDegrees();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  

		
		CvResponseDTO response = CvResponseDTO.builder()
				.images(cv.getImages())
				.name(candidate.getName())
				.position(desiredJob.getName())
				.dateOfBirth(dateFormat.format(candidate.getBirthDate()))
				.gender(candidate.getGender())
				.phone(candidate.getPhone())
				.email(candidate.getEmail())
				.province(province.getName())
				.city(city.getName())
				.street(street.getName())
				.facebook(facebook)
				.careerGoal(cv.getCareerJobObjective())
				.skills(new ArrayList<Skill>(cv.getSkills()))
				.awards(cv.getAward())
				.degrees(new ArrayList<Degree>(cv.getDegrees()))
				.hobbies(cv.getHobbies())
				.educations(new ArrayList<Education>(cv.getEducations()))
				.workExperiences(new ArrayList<WorkExperiences>(cv.getWorkExperiences()))
				.activities(cv.getActivities())
				.involvedProjects(new ArrayList<InvolvedProject>(cv.getInvolvedProject()))
				.build();
		return new ResponseEntity<CvResponseDTO>(response, HttpStatus.OK);
	}
}

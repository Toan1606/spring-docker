package com.codedecode.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.ActivityUpdateRequestDTO;
import com.codedecode.demo.dto.CVRequestDTO;
import com.codedecode.demo.dto.CVUpdateRequestDTO;
import com.codedecode.demo.dto.CvResponseDTO;
import com.codedecode.demo.dto.DegreeUpdateRequestDTO;
import com.codedecode.demo.dto.EducationUpdateRequestDTO;
import com.codedecode.demo.dto.InvolvedUpdateRequestDTO;
import com.codedecode.demo.dto.SkillRequestDTO;
import com.codedecode.demo.dto.WorkExperienceUpdateRequestDTO;
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
import com.codedecode.demo.service.ActivityService;
import com.codedecode.demo.service.CVService;
import com.codedecode.demo.service.DegreeService;
import com.codedecode.demo.service.EducationService;
import com.codedecode.demo.service.InvolvedProjectService;
import com.codedecode.demo.service.ProvinceService;
import com.codedecode.demo.service.SkillService;
import com.codedecode.demo.service.UserService;
import com.codedecode.demo.service.WorkExperienceService;

@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/cv")
public class CvController {

	@Autowired
	private UserService userService;

	@Autowired
	private CVService cvService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private SkillService skillService;
	
	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private EducationService educationService;
	
	@Autowired
	private WorkExperienceService workExperienceService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private InvolvedProjectService involvedProjectService;

	@GetMapping
	public ResponseEntity<List<Province>> findAllProvince() {
		return new ResponseEntity<List<Province>>(provinceService.findAllProvince(), HttpStatus.OK);
	}

	@PostMapping(value = "/id")
	public ResponseEntity<CvResponseDTO> findCvByCandidateId(@RequestBody CVRequestDTO request) {
		Long candidateId = request.getCandidateId();
		CV cv = cvService.findCvByCandidateId(candidateId);

		User candidate = cv.getUser();
		Address address = candidate.getAddress();
		Province province = address.getProvince();
		City city = address.getCity();
		Street street = address.getStreet();
		String facebook = new StringBuilder("https://www.facebook.com/").append(candidate.getEmail().split("@")[0])
				.toString();
		DesiredJob desiredJob = candidate.getDesiredJob();
		cv.getDegrees();

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		CvResponseDTO response = CvResponseDTO.builder().images(cv.getImages()).name(candidate.getName())
				.position(desiredJob.getName()).dateOfBirth(dateFormat.format(candidate.getBirthDate()))
				.gender(candidate.getGender()).phone(candidate.getPhone()).email(candidate.getEmail())
				.province(province.getName()).city(city.getName()).street(street.getName()).facebook(facebook)
				.careerGoal(cv.getCareerJobObjective()).skills(new ArrayList<Skill>(cv.getSkills()))
				.awards(cv.getAward()).degrees(new ArrayList<Degree>(cv.getDegrees())).hobbies(cv.getHobbies())
				.educations(new ArrayList<Education>(cv.getEducations()))
				.workExperiences(new ArrayList<WorkExperiences>(cv.getWorkExperiences())).activities(cv.getActivities())
				.involvedProjects(new ArrayList<InvolvedProject>(cv.getInvolvedProject())).build();
		return new ResponseEntity<CvResponseDTO>(response, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<String> updateCv(@RequestBody CVUpdateRequestDTO request) {
		System.out.println(request);
		
		// 1. get new cv
		
		String email = request.getEmail();
		String newCareerGoal = request.getCareerGoal();
		String newAwards = request.getAwards();
		String newHobbies = request.getHobbies();
		String newImages = request.getImages();

		User user = userService.findUserByEmail(email);

		CV cv = user.getCv();
		// 2. get new skills 
		List<SkillRequestDTO> skills = request.getSkills();
		// 3. get in degree
		List<DegreeUpdateRequestDTO> degrees = request.getDegrees();
		// 4. get new education
		List<EducationUpdateRequestDTO> educations = request.getEducations();
		// 5. get new work experience
		List<WorkExperienceUpdateRequestDTO> workExperiences = request.getWorkExperiences();
		// 6. get new acitivities
		List<ActivityUpdateRequestDTO> activities = request.getActivities();
		// 7. get new involved project
		List<InvolvedUpdateRequestDTO> involvedProjects = request.getInvolvedProjects();
		
		// 8. update
		skillService.updateSkills(skills);
		cvService.updateCv(cv, newCareerGoal, newAwards, newHobbies, newImages);
		degreeService.updateDegree(degrees);
		educationService.updateEducation(educations);
		workExperienceService.updateWorkExperiences(workExperiences);
		activityService.updateActivity(activities);
		involvedProjectService.updateInvolvedProject(involvedProjects);
		
		return new ResponseEntity<String>("Update CV Sucessfully", HttpStatus.OK);
	}
}

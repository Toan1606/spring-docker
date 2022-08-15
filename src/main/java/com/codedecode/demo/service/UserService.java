package com.codedecode.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.dto.CandidateByIdResponseDTO;
import com.codedecode.demo.dto.FindAllUserResponseDTO;
import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Role;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.Skill;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.repository.SearchCandidateRepository;
import com.codedecode.demo.dto.PostingRecruiterResponseDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.ApplicationUserRole;
import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.CV;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.exception.UserNotFoundException;
import com.codedecode.demo.repository.UserRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class UserService {

	@Autowired(required = true)
	private UserRepository userRepository;

	@Autowired(required = true)
	private SearchCandidateRepository searchCandidateRepository;

	public User addNewUser(User user) {
		return userRepository.save(user);
	}

	public User findUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getErrorMessage()));
		return user;
	}

	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	public void updateCandidateOnlineCVForm() {
		userRepository.flush();
	}

	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	public PageDTO<User> searchCandidatePage(String text, List<String> fields, int limit, int pageOffset) {
		return searchCandidateRepository.searchPageBy(text, limit, pageOffset, fields.toArray(new String[0]));
	}

	public List<PostingRecruiterResponseDTO> convert(List<Posting> postings) {
		List<PostingRecruiterResponseDTO> postingsDto = new ArrayList<PostingRecruiterResponseDTO>();
		for (Posting posting : postings) {
			PostingRecruiterResponseDTO postingDto = new PostingRecruiterResponseDTO();
			postingDto.setId(posting.getId());
			postingDto.setJobName(posting.getJobName());
			postingDto.setDeadlineForSubmission(posting.getDeadlineForSubmission());
			postingDto.setPosition(posting.getPosition());
			postingDto.setDegreeRequired(posting.getDegreeRequired());
			postingDto.setDescription(posting.getDescription());
			postingDto.setSalary(posting.getSalary().getName());
			postingDto.setJobRequirement(posting.getJobRequirement());
			postingDto.setGenderRequirement(posting.getGenderRequirement());
			postingDto.setWorkingForm(posting.getWorkingForm().getName());
			postingDto.setPostingType(posting.getPostingType().getPostingType());
			postingDto.setImage(posting.getImages());
			List<Address> addresss = new ArrayList<Address>(posting.getAddresss());
			StringBuilder provinces = new StringBuilder();
			for (Address address : addresss) {
				String province = address.getProvince().getName();
				provinces.append(province);
			}
			postingDto.setProvince(provinces.toString());
			postingsDto.add(postingDto);
		}
		return postingsDto;

	}

	public List<User> findAllRecruiter() {
		List<User> users = userRepository.findByRoles_RoleName(ApplicationUserRole.ROLE_RECRUITER.name());
		return users;
	}

	public List<FindAllUserResponseDTO> convertFindAllUser(List<User> users) {
		List<FindAllUserResponseDTO> usersDto = new ArrayList<FindAllUserResponseDTO>();
		for (User user : users) {
			FindAllUserResponseDTO userDto = new FindAllUserResponseDTO();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			String description = user.getRecruiterDescription();
			if (description != null)
				userDto.setRecruiterDescription(description.substring(0, 50));
			userDto.setImage(user.getImages());
			usersDto.add(userDto);
		}
		return usersDto;
	}

	public CandidateByIdResponseDTO findCandidateById(Long candidateId) {
		User user = userRepository.findById(candidateId)
				.orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getErrorMessage()));

		Set<Role> roles = user.getRoles();
		Iterator<Role> value = roles.iterator();

		while (value.hasNext()) {
			Role role = value.next();
			if (!role.getRoleName().equals(ApplicationUserRole.ROLE_CANDIDATE.name()))
				throw new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getErrorMessage());
		}

		CandidateByIdResponseDTO userDto = convertToCandidateResposeDTO(user);
		return userDto;
	}

	public String getProvinceOfWorkplaceDesired(List<Address> address) {
		StringBuilder addrStr = new StringBuilder();
		for (Address addr : address) {
			addrStr.append(addr.getProvince().getName()).append(", ");
		}
		return addrStr.toString();
	}

	public String getSkill(CV cv) {
		if (cv == null)
			return "";
		StringBuilder skillStr = new StringBuilder();
		List<Skill> skills = new ArrayList<Skill>(cv.getSkills());
		for (Skill skill : skills) {
			skillStr.append(skill.getSkillName());
		}
		return skillStr.toString();
	}

	public String getYearOfExperience(CV cv) {
		if (cv == null)
			return "";
		YearOfExperience yearOfExperience = cv.getYearOfExperience();
		return yearOfExperience != null ? yearOfExperience.getName() : null;
	}

	public CandidateByIdResponseDTO setCandidateByIdResponseDTO(Long userId, String userName, String gender,
			String birthDate, Random rnd, String desiredJobName, String mariaStatus, String phone, String email,
			String provinceName, String cityName, String workplaceDesired, String yearOfExperience, String salaryName,
			String workingFormName, String rankName, String careerGoal, String skillName,
			List<WorkExperiences> workExperiences, List<Degree> degrees) {
		CandidateByIdResponseDTO candidateDto = new CandidateByIdResponseDTO();
		candidateDto.setId(userId);
		candidateDto.setName(userName);
		candidateDto.setGender(gender);
		candidateDto.setDateOfBirth(birthDate);
		candidateDto.setProfileCode(String.valueOf(rnd.nextInt(999999)));
		candidateDto.setDesiredJob(desiredJobName);
		candidateDto.setMariaStatus(mariaStatus);
		candidateDto.setPhone(phone);
		candidateDto.setEmail(email);

		candidateDto.setProvince(provinceName);
		candidateDto.setCity(cityName);

		candidateDto.setWorkplaceDesired(workplaceDesired);
		candidateDto.setYearOfExperience(yearOfExperience);
		candidateDto.setSalary(salaryName);
		candidateDto.setWorkingForm(workingFormName);
		candidateDto.setRank(rankName);
		candidateDto.setCareerGoal(careerGoal);
		candidateDto.setSkill(skillName);
		candidateDto.setWorkExperiences(workExperiences);
		candidateDto.setDegrees(degrees);
		return candidateDto;
	}

	public List<Address> getAddressFromDesiredJob(List<DesiredJob> desiredJobs) {
		List<Address> addresss = new ArrayList<Address>();
		for (DesiredJob desiredJob : desiredJobs) {
			Collection<Address> collectionAddresss = desiredJob.getAddresss();
			Iterator<Address> iteratorAddress = collectionAddresss.iterator();
			while (iteratorAddress.hasNext()) {
				addresss.add(iteratorAddress.next());
			}
		}
		return addresss;
	}

	public Salary getSalariesFromDesiredJob(List<DesiredJob> desiredJobs) {
		// default value
		Salary salary = new Salary();
		salary.setName("Lương Thỏa Thuận");
		for (DesiredJob desiredJob : desiredJobs) {
			salary = desiredJob.getSalary();
			if (salary != null)	return salary;
		}
		return salary;
	}
	
	public WorkingForm getWorkingFormFromDesiredJob(List<DesiredJob> desiredJobs) {
		// default value
		WorkingForm workingForm = new WorkingForm();
		workingForm.setName("Toàn thời gian cố định");
		
		for (DesiredJob desiredJob : desiredJobs) {
			workingForm = desiredJob.getWorkingForm();
			if (workingForm != null)	return workingForm;
		}
		return workingForm;
	}
	
	public Rank getRankFromDesiredJob(List<DesiredJob> desiredJobs) {
		Rank rank = new Rank();
		rank.setName("Nhân viên");
		
		for (DesiredJob desiredJob : desiredJobs) {
			rank = desiredJob.getRank();
			if (rank != null)	return rank;
		}
		return rank;
	}
	
	public String getDesiredJobName(List<DesiredJob> desiredJobs) {
		StringBuilder desiredJobNames = new StringBuilder();
		for (DesiredJob desiredJob : desiredJobs) {
			desiredJobNames.append(desiredJob.getName()).append(", ");
		}
		return desiredJobNames.toString();
	}

	public CandidateByIdResponseDTO convertToCandidateResposeDTO(User user) {
		// 1. get object related
		// 1.1 birth date
		Date birthDate = user.getBirthDate();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(birthDate);
		// 1.2 random Mã hồ sơ
		Random rnd = new Random();
		// 1.3
		List<DesiredJob> desiredJobs = user.getDesiredJobs();
		// province
		Address address = user.getAddress();
		Province province = address.getProvince();
		City city = address.getCity();
		CV cv = user.getCv();

		// 2. generate object
		String workplaceDesired = null;
		Salary salary = null;
		WorkingForm workingForm = null;
		Rank rank = null;
		String desiredJobName = null;

		if (desiredJobs != null) {
			List<Address> addressWorkplaceDesireds = getAddressFromDesiredJob(desiredJobs);

			workplaceDesired = getProvinceOfWorkplaceDesired(addressWorkplaceDesireds);

			salary = getSalariesFromDesiredJob(desiredJobs);
			workingForm = getWorkingFormFromDesiredJob(desiredJobs);
			rank = getRankFromDesiredJob(desiredJobs);
			desiredJobName = getDesiredJobName(desiredJobs);
		}

		String yearOfExperience = getYearOfExperience(cv);
		String careerGoal = cv.getCareerJobObjective();
		String skillName = getSkill(cv);
		List<WorkExperiences> workExperiences = new ArrayList<WorkExperiences>(cv.getWorkExperiences());

		List<Degree> degrees = new ArrayList<Degree>(cv.getDegrees());

		salary = salary == null ? new Salary() : salary;
		workingForm = workingForm == null ? new WorkingForm() : workingForm;
		rank = rank == null ? new Rank() : rank;

		// 3. set
		CandidateByIdResponseDTO candidateDto = setCandidateByIdResponseDTO(user.getId(), user.getName(),
				user.getGender(), strDate, rnd, desiredJobName, user.getMariaStatus(), user.getPhone(), user.getEmail(),
				province.getName(), city.getName(), workplaceDesired, yearOfExperience, salary.getName(),
				workingForm.getName(), rank.getName(), careerGoal, skillName, workExperiences, degrees);

		// 4. return
		return candidateDto;
	}

	public String convertWorkPlaceDesired(List<Address> addresss) {
		StringBuilder addressStr = new StringBuilder();
		for (Address address : addresss) {
			addressStr.append(address.getProvince().getName());
		}
		return addressStr.toString();
	}

	public Set<User> getCandidateFromAppliedJob(List<AppliedJob> appliedJobs) {
		Set<User> candidates = new HashSet<User>();
		for (AppliedJob appliedJob : appliedJobs) {
			User candidate = appliedJob.getCandidate();
			candidates.add(candidate);
		}

		return candidates;
	}

	public void updateUser(User user) {
		userRepository.save(user);
	}
}

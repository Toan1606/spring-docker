package com.codedecode.demo.service;

import java.util.ArrayList;
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
import com.codedecode.demo.entity.Role;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.SearchCandidateRepository;
import com.codedecode.demo.dto.PostingRecruiterResponseDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.ApplicationUserRole;
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
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getErrorMessage()));
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
				userDto.setRecruiterDescription(description.substring(0, 100));
			userDto.setImage(user.getImages());
			usersDto.add(userDto);
		}
		return usersDto;
	}
	public CandidateByIdResponseDTO findCandidateById(Long candidateId) {
		User user = userRepository.findById(candidateId).orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getErrorMessage()));
		
		Set<Role> roles = user.getRoles();
		Iterator<Role> value = roles.iterator();
		
		while (value.hasNext()) {
			Role role = value.next();
			if (role.getRoleName().equals(ApplicationUserRole.ROLE_RECRUITER.name()))
			throw new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getErrorMessage());
		}
		
		CandidateByIdResponseDTO userDto = convertToCandidateResposeDTO(user);
		return userDto;
	}
	
	public CandidateByIdResponseDTO convertToCandidateResposeDTO(User user) {
		Random rnd = new Random();
		CandidateByIdResponseDTO candidateDto = new CandidateByIdResponseDTO();
		candidateDto.setId(user.getId());
		candidateDto.setName(user.getName());
		candidateDto.setGender(user.getGender());
		candidateDto.setDateOfBirth(user.getBirthDate().toString());
		candidateDto.setProfileCode(String.valueOf(rnd.nextInt(999999)));
		candidateDto.setDesiredJob(user.getDesiredJob().getName());
		candidateDto.setMariaStatus(user.getMariaStatus());
		candidateDto.setPhone(user.getPhone());
		candidateDto.setEmail(user.getEmail());
		
		candidateDto.setProfileCode(user.getAddress().getProvince().getName());
		candidateDto.setCity(user.getAddress().getCity().getName());
		
//		String workplaceDesired = convertWorkPlaceDesired(addresss);
//		candidateDto.setWorkplaceDesired(workplaceDesired);
//		candidateDto.setYearOfExperience(user.getYearOfExperience().getName());
//		candidateDto.setSalary(user.);
		
		return candidateDto;
	}
	
	public String convertWorkPlaceDesired(List<Address> addresss) {
		StringBuilder addressStr = new StringBuilder();
		for (Address address : addresss) {
			addressStr.append(address.getProvince().getName());
		}
		return addressStr.toString();
	}
}

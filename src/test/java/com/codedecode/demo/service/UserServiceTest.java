package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.dto.CandidateByIdResponseDTO;
import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.entity.ApplicationUserRole;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.SearchCandidateRepository;
import com.codedecode.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class UserServiceTest {
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private SearchCandidateRepository searchCandidateRepository;

	@InjectMocks
	private UserService userService;

	@Test
	public void testAddNewUser() {
		User user = new User();

		when(userRepository.save(user)).thenReturn(user);

		User reality = userService.addNewUser(user);

		assertThat(reality).isNotNull();
	}

	@Test
	public void testFindUserById1() {
		Long id = 1L;
		User user = new User();
		Optional<User> expect = Optional.of(user);
		when(userRepository.findById(id)).thenReturn(expect);
		User reality = userService.findUserById(id);
		assertThat(reality).isNotNull();
	}

	@Test
	public void testFindUserById2() {
		Long id = 100L;
		User user = new User();
		Optional<User> expect = Optional.of(user);
		when(userRepository.findById(id)).thenReturn(expect);
		User reality = userService.findUserById(id);
		assertThat(reality).isNotNull();
	}

	@Test
	public void testFindUserByEmail1() {
		String email = "tasda@gmail.com";
		User user = new User();
		when(userRepository.findByEmail(email)).thenReturn(user);
		User reality = userService.findUserByEmail(email);
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testFindUserByEmail2() {
		String email = "tung@gmail.com";
		User user = new User();
		when(userRepository.findByEmail(email)).thenReturn(user);
		User reality = userService.findUserByEmail(email);
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testSearchCandidatePage() {
		String text = "fpt";
		List<String> fields = new ArrayList<>();
		
		int limit = 4;
		
		int pageOffset = 5;
		
		PageDTO<User> expect = new PageDTO<>();
		
		when(searchCandidateRepository.searchPageBy(text, limit, pageOffset, fields.toArray(new String[0]))).thenReturn(expect);
	
		PageDTO<User> reality = userService.searchCandidatePage(text, fields, limit, pageOffset);
		
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testFindAllRecruiter1() {
		List<User> users = new ArrayList<User>();
		
		when(userRepository.findByRoles_RoleName(ApplicationUserRole.ROLE_RECRUITER.name())).thenReturn(users);
	
		List<User> reality = userService.findAllRecruiter();
		
		assertThat(reality.size()).isGreaterThan(0);
	}
	
	@Test
	public void testFindAllRecruiter2() {
		List<User> users = new ArrayList<User>();
		
		when(userRepository.findByRoles_RoleName(ApplicationUserRole.ROLE_RECRUITER.name())).thenReturn(users);
	
		List<User> reality = userService.findAllRecruiter();
		
		assertThat(reality.size()).isLessThan(0);
	}
	@Test
	public void testFindAllRecruiter3() {
		List<User> users = new ArrayList<User>();
		
		when(userRepository.findByRoles_RoleName(ApplicationUserRole.ROLE_RECRUITER.name())).thenReturn(users);
	
		List<User> reality = userService.findAllRecruiter();
		
		assertThat(reality.size()).isEqualTo(0);
	}
	
	@Test
	public void testFindCandidateById() {
		User user = new User();
		
		Long candidate_id = 2L;
		
		Optional<User> list = Optional.empty();
		
		when(userRepository.findById(candidate_id)).thenReturn(list);
		
		Optional<CandidateByIdResponseDTO> reality = Optional.of(userService.findCandidateById(candidate_id));
		
		assertThat(reality).isNotNull();
	}
}

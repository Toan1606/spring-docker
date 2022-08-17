package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.codedecode.demo.entity.User;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testGetUserById1() {
		int userId = 1;
		
		User reality = userRepository.getUserById(userId);
		
		assertThat(reality).isNotNull();
	}
	@Test
	public void testGetUserById2() {
		int userId = 100;
		
		User reality = userRepository.getUserById(userId);
		
		assertThat(reality).isNull();
	}
	@Test
	public void testFindByEmail1() {
		String email = "abc@gmail.com";
		
		User reality = userRepository.findByEmail(email);
		
		assertThat(reality).isNull();
	}
	@Test
	public void testFindByRoles_RoleName1() {
		String role = "ROLE_CANDIDATE";
		List<User> reality = userRepository.findByRoles_RoleName(role);
		assertThat(reality).isNotEmpty();
	}
	@Test
	public void testFindByRoles_RoleName2() {
		String role = "ROLE_CANDIDATE";
		List<User> reality = userRepository.findByRoles_RoleName(role);
		assertThat(reality).isEmpty();
	}
	@Test
	public void testFindByRoles_RoleName3() {
		String role = "ROLE_CANDIDATE";
		List<User> reality = userRepository.findByRoles_RoleName(role);
		assertThat(reality).isNotNull();
	}
	@Test
	public void testFindByRoles_RoleName5() {
		String role = "";
		List<User> reality = userRepository.findByRoles_RoleName(role);
		assertThat(reality).isNotNull();
	}
	@Test
	public void testFindByRoles_RoleName6() {
		String role = "";
		List<User> reality = userRepository.findByRoles_RoleName(role);
		assertThat(reality).isNotEmpty();
	}
}

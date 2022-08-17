package com.codedecode.demo.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Role;
import com.codedecode.demo.entity.User;

@SpringBootTest
public class RoleRepositoryTest {
	
	@Autowired
	private RoleRepository repository;
	
	@Test
	public void findByRoleName() {
		Role reality = repository.findByRoleName("ROLE_CANDIDATE");
		assertTrue(reality != null);
	}
	
	@Test
	public void findByRoleName1() {
		Role reality = repository.findByRoleName("ROLE_GUEST");
		assertTrue(reality != null);
	}
	
	@Test
	public void findByRoleName2() {
		Role reality = repository.findByRoleName("ROLE_RECRUITER");
		assertTrue(reality != null);
	}
	
	@Test
	public void findByRoleName3() {
		Role reality = repository.findByRoleName("");
		assertTrue(reality == null);
	}
}

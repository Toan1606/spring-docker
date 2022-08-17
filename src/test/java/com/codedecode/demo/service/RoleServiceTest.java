package com.codedecode.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.entity.Role;
import com.codedecode.demo.repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RoleServiceTest {

	@MockBean
	private RoleRepository roleRepository;
	
	@InjectMocks
	private RoleService roleService;
	
	@Test
	public void testFindRoleByName() {
		String name = "ROLE_USER";
		Role expect = new Role();
		
		when(roleRepository.findByRoleName(name)).thenReturn(expect);
		
		Role reality = roleService.findRoleByName(name);
		assertNotNull(reality);
	}
}

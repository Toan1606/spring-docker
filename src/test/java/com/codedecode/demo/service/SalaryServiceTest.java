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

import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.repository.SalaryRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class SalaryServiceTest {
	@MockBean
	private SalaryRepository salaryRepository;
	
	@InjectMocks
	private SalaryService salaryService;
	
	@Test
	public void testFindSalaryById1() {
		Long salaryId = 1L;
		Salary salary = new Salary();
		Optional<Salary> expect = Optional.of(salary);
		when(salaryRepository.findById(salaryId)).thenReturn(expect);
		
		Salary reality = salaryService.findSalaryById(salaryId);
		
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testFindSalaryById2() {
		Long salaryId = 200L;
		Salary salary = new Salary();
		Optional<Salary> expect = Optional.of(salary);
		when(salaryRepository.findById(salaryId)).thenReturn(expect);
		
		Salary reality = salaryService.findSalaryById(salaryId);
		
		assertThat(reality).isNotNull();
	}
	@Test
	public void testFindAll1() {
		List<Salary> list = new ArrayList<>();
		when(salaryRepository.findAll()).thenReturn(list);
		
		List<Salary> reality = salaryService.findAll();
		
		assertThat(reality.size()).isGreaterThan(0);
	}
	@Test
	public void testFindAll2() {
		List<Salary> list = new ArrayList<>();
		when(salaryRepository.findAll()).thenReturn(list);
		
		List<Salary> reality = salaryService.findAll();
		
		assertThat(reality.size()).isLessThan(0);
	}
	@Test
	public void testFindAll3() {
		List<Salary> list = new ArrayList<>();
		when(salaryRepository.findAll()).thenReturn(list);
		
		List<Salary> reality = salaryService.findAll();
		
		assertThat(reality.size()).isEqualTo(0);
	}
	@Test
	public void testFindAll4() {
		List<Salary> list = new ArrayList<>();
		when(salaryRepository.findAll()).thenReturn(list);
		
		List<Salary> reality = salaryService.findAll();
		
		assertThat(reality.size()).isGreaterThan(1);
	}
	
	@Test
	public void testFindAll5() {
		List<Salary> list = new ArrayList<>();
		when(salaryRepository.findAll()).thenReturn(list);
		
		List<Salary> reality = salaryService.findAll();
		
		assertThat(reality).isEmpty();
	}
}

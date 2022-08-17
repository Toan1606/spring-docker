package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Salary;

@SpringBootTest
public class SalaryRepositoryTest {

	@Autowired
	private SalaryRepository salaryRepository;
	
	@Test
	public void testGetAllSalary1() {
		List<Salary> reality = salaryRepository.getAllSalary();
		assertThat(reality.size()).isGreaterThan(11);
	}
	@Test
	public void testGetAllSalary2() {
		List<Salary> reality = salaryRepository.getAllSalary();
		assertThat(reality.size()).isLessThan(11);
	}
	
	@Test
	public void testGetAllSalary3() {
		List<Salary> reality = salaryRepository.getAllSalary();
		assertThat(reality.size()).isEqualTo(11);
	}
	@Test
	public void testGetAllSalary4() {
		List<Salary> reality = salaryRepository.getAllSalary();
		Salary salary = reality.get(0);
		String salaryName = "Thỏa thuận";
		assertThat(salary.getName()).isEqualTo(salaryName);
	}
}

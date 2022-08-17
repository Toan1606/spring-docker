package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.WorkExperiences;

@SpringBootTest
public class WorkExperienceRepositoryTest {

	@Autowired
	private WorkExperienceRepository workExperienceRepository;
	
	@Test
	public void testGetAllWorkExpByUserId1() {
		Long userId = 1L;
		List<WorkExperiences> reality = workExperienceRepository.getAllWorkExpByUserId(userId);
		assertThat(reality.size()).isGreaterThan(0);
	}
	@Test
	public void testGetAllWorkExpByUserId2() {
		Long userId = 1L;
		List<WorkExperiences> reality = workExperienceRepository.getAllWorkExpByUserId(userId);
		assertThat(reality.size()).isLessThan(0);
	}
	@Test
	public void testGetAllWorkExpByUserId3() {
		Long userId = 1L;
		List<WorkExperiences> reality = workExperienceRepository.getAllWorkExpByUserId(userId);
		assertThat(reality.size()).isEqualTo(0);
	}
	@Test
	public void testGetAllWorkExpByUserId4() {
		Long userId = 10L;
		List<WorkExperiences> reality = workExperienceRepository.getAllWorkExpByUserId(userId);
		assertThat(reality.size()).isGreaterThan(0);
	}
	@Test
	public void testGetAllWorkExpByUserId5() {
		Long userId = 10L;
		List<WorkExperiences> reality = workExperienceRepository.getAllWorkExpByUserId(userId);
		assertThat(reality.size()).isLessThan(0);
	}
	@Test
	public void testGetAllWorkExpByUserId6() {
		Long userId = 10L;
		List<WorkExperiences> reality = workExperienceRepository.getAllWorkExpByUserId(userId);
		assertThat(reality.size()).isEqualTo(0);
	}
	@Test
	public void testGetWorkExpById1() {
		Long id = 1L;
		WorkExperiences reality = workExperienceRepository.getWorkExpById(id);
		assertThat(reality).isNotNull();
	}
	@Test
	public void testGetWorkExpById2() {
		Long id = 10L;
		WorkExperiences reality = workExperienceRepository.getWorkExpById(id);
		assertThat(reality).isNotNull();
	}
	@Test
	public void tesUpdateWorkExp1() {
		Date startDate = new Date("2022-08-09");
		Date endDate = new Date("2022-08-10");
		Long id = 1L;
		String companyName = "GPTAS";
		String position= "Nhân viên";
		Integer effectCol = workExperienceRepository.updateWorkExp(id, companyName, startDate, endDate, position);
		assertThat(effectCol).isGreaterThan(0);
	}
	@Test
	public void tesUpdateWorkExp2() {
		Date startDate = new Date("2022-08-09");
		Date endDate = new Date("2022-08-10");
		Long id = 1L;
		String companyName = "GPTAS";
		String position= "Nhân viên";
		Integer effectCol = workExperienceRepository.updateWorkExp(id, companyName, startDate, endDate, position);
		assertThat(effectCol).isLessThan(0);
	}
	@Test
	public void tesUpdateWorkExp3() {
		Date startDate = new Date("2022-08-09");
		Date endDate = new Date("2022-08-10");
		Long id = 1L;
		String companyName = "GPTAS";
		String position= "Nhân viên";
		Integer effectCol = workExperienceRepository.updateWorkExp(id, companyName, startDate, endDate, position);
		assertThat(effectCol).isEqualTo(1);
	}
}

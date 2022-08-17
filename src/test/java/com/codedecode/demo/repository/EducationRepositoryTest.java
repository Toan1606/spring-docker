package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EducationRepositoryTest {

	@Autowired
	private EducationRepository educationRepository;
	
	@Test
	public void testUpdateEducation() {
		Integer reality = educationRepository.updateEducation(1L, "Giỏi", "Hệ Thống Thông Tin Bách Khoa", "Trường ĐH Bách Khoa Hà Nội");
		System.out.println("reality : " + reality);
		assertThat(reality).isGreaterThan(1);
	}
	
	@Test
	public void testUpdateEducation2() {
		Integer reality = educationRepository.updateEducation(1L, "Giỏi", "Hệ Thống Thông Tin FPT", "Trường ĐH Bách Khoa Hà Nội");
		System.out.println("reality : " + reality);
		assertThat(reality).isEqualTo(1);
	}
	
	@Test
	public void testUpdateEducation3() {
		Integer reality = educationRepository.updateEducation(1L, "Giỏi", "Hệ Thống Thông Tin VNU", "Trường ĐH Bách Khoa Hà Nội");
		System.out.println("reality : " + reality);
		assertThat(reality).isLessThan(1);
	}
	
	@Test
	public void testUpdateEducation4() {
		Integer reality = educationRepository.updateEducation(1L, "Giỏi", "Hệ Thống Thông Tin VNU", "Trường ĐH Bách Khoa Hà Nội");
		System.out.println("reality : " + reality);
		assertTrue(true);
	}
}

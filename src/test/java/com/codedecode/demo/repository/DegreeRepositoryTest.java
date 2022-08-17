package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Degree;

@SpringBootTest
public class DegreeRepositoryTest {

	@Autowired
	private DegreeRepository degreeRepository;
	
	@Test
	public void testGetAllDegreeByUserId2() {
		Long userId = 1L;
		List<Degree> reality = degreeRepository.getAllDegreeByUserId(userId);
		System.out.println("Reality : " + reality.size());
		assertThat(reality.size()).isNotEqualTo(1);
	}
	
	@Test
	public void testGetAllDegreeByUserId3() {
		Long userId = 1L;
		List<Degree> reality = degreeRepository.getAllDegreeByUserId(userId);
		assertThat(reality.size()).isLessThan(1);
	}
	
	@Test
	public void testGetAllDegreeByUserId4() {
		Long userId = 1L;
		List<Degree> reality = degreeRepository.getAllDegreeByUserId(userId);
		assertThat(reality.size()).isEqualTo(0);
	}

	@Test
	public void testUpdateDegree() {
		Integer reality = degreeRepository.updateDegree(1L, "Chứng Chỉ Ngoại Ngữ", "Chứng Chỉ Ngoại Ngữ");
		assertThat(reality).isGreaterThan(1);
	}
	
	@Test
	public void testUpdateDegree2() {
		Integer reality = degreeRepository.updateDegree(1L, "Chứng Chỉ Ngoại Ngữ", "Chứng Chỉ Ngoại Ngữ");
		assertThat(reality).isEqualTo(0);
	}
	
	@Test
	public void testUpdateDegree3() {
		Integer reality = degreeRepository.updateDegree(1L, "Chứng Chỉ Ngoại Ngữ", "Chứng Chỉ Ngoại Ngữ");
		assertThat(reality).isEqualTo(1);
	}
	
	@Test
	public void testUpdateDegree4() {
		Integer reality = degreeRepository.updateDegree(1L, "Chứng Chỉ Ngoại Ngữ", "Chứng Chỉ Ngoại Ngữ");
		assertThat(reality).isLessThan(0);
	}
}

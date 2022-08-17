package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SkillRepositoryTest {

	@Autowired
	private SkillRepository skillRepository;

	@Test
	public void testUpdateSkills1() {
		Long id = 1L;
		String skillName = "Communication";
		Integer effectColumns = skillRepository.updateSkills(id, skillName);
		assertThat(effectColumns).isGreaterThan(0);
	}
	@Test
	public void testUpdateSkills2() {
		Long id = 1L;
		String skillName = "Communication";
		Integer effectColumns = skillRepository.updateSkills(id, skillName);
		assertThat(effectColumns).isGreaterThan(1);
	}
	@Test
	public void testUpdateSkills3() {
		Long id = 1L;
		String skillName = "Communication";
		Integer effectColumns = skillRepository.updateSkills(id, skillName);
		assertThat(effectColumns).isGreaterThan(-1);
	}
	@Test
	public void testUpdateSkills4() {
		Long id = 100L;
		String skillName = "Communication";
		Integer effectColumns = skillRepository.updateSkills(id, skillName);
		assertThat(effectColumns).isGreaterThan(0);
	}
	@Test
	public void testUpdateSkills5() {
		Long id = 100L;
		String skillName = "Communication";
		Integer effectColumns = skillRepository.updateSkills(id, skillName);
		assertThat(effectColumns).isGreaterThan(1);
	}
	@Test
	public void testUpdateSkills6() {
		Long id = 100L;
		String skillName = "Communication";
		Integer effectColumns = skillRepository.updateSkills(id, skillName);
		assertThat(effectColumns).isGreaterThan(-1);
	}
}

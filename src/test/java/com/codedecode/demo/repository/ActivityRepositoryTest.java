package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ActivityRepositoryTest {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Test
	public void testUpdateActivity() {
		Long id = 1l;
		String name = "Mùa Hè Summer 2022";
		String position = "Lập Trình Viên";
		String description = "Làm Đồ Án Ra Trường";
		
		Integer effectColumns = activityRepository.updateActivity(id, name, position, description);
		assertThat(effectColumns).isGreaterThan(0);
	}
	
	@Test
	public void testUpdateActivity2() {
		Long id = 1l;
		String name = "Mùa Hè Summer 2022";
		String position = "Lập Trình Viên";
		String description = "Làm Đồ Án Ra Trường";
		
		Integer effectColumns = activityRepository.updateActivity(id, name, position, description);
		assertThat(effectColumns).isGreaterThan(1);
	}
	
	@Test
	public void testUpdateActivity3() {
		Long id = 1l;
		String name = "Mùa Hè Summer 2022";
		String position = "Lập Trình Viên";
		String description = "Làm Đồ Án Ra Trường";
		
		Integer effectColumns = activityRepository.updateActivity(id, name, position, description);
		assertThat(effectColumns).isGreaterThan(-1);
	}
	@Test
	public void testUpdateActivity4() {
		Long id = 100l;
		String name = "Mùa Hè Summer 2022";
		String position = "Lập Trình Viên";
		String description = "Làm Đồ Án Ra Trường";
		
		Integer effectColumns = activityRepository.updateActivity(id, name, position, description);
		assertThat(effectColumns).isGreaterThan(1);
	}
	@Test
	public void testUpdateActivity5() {
		Long id = 100l;
		String name = "Mùa Hè Summer 2022";
		String position = "Lập Trình Viên";
		String description = "Làm Đồ Án Ra Trường";
		
		Integer effectColumns = activityRepository.updateActivity(id, name, position, description);
		assertThat(effectColumns).isGreaterThan(0);
	}
	@Test
	public void testUpdateActivity6() {
		Long id = 100l;
		String name = "Mùa Hè Summer 2022";
		String position = "Lập Trình Viên";
		String description = "Làm Đồ Án Ra Trường";
		
		Integer effectColumns = activityRepository.updateActivity(id, name, position, description);
		assertThat(effectColumns).isGreaterThan(-1);
	}
}

package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvolvedProjectRepositoryTest {
	
	@Autowired
	private InvolvedProjectRepository involvedProjectRepository;
	
	@Test
	public void updateInvolvedProject() {
		Integer reality = involvedProjectRepository.updateInvolvedProject(1l, "Đây là dự án để kết nối nhà tuyển dụng và ứng viên có nhu cầu ", 
				"Ngôn ngữ sử dụng: Java Android Link app: https://play.google.com/store/apps/details?id=vn.timviec365.myapplication", 
				"+/ Code app timviec365.vn");
		assertTrue(reality > 1);
	}
	
	@Test
	public void updateInvolvedProject1() {
		Integer reality = involvedProjectRepository.updateInvolvedProject(1l, "Đây là dự án để kết nối nhà tuyển dụng và ứng viên có nhu cầu ", 
				"Ngôn ngữ sử dụng: Java Android Link app: https://play.google.com/store/apps/details?id=vn.timviec365.myapplication", 
				"+/ Code app timviec365.vn");
		assertTrue(reality == 1);
	}
	
	@Test
	public void updateInvolvedProject2() {
		Integer reality = involvedProjectRepository.updateInvolvedProject(1l, "Đây là dự án để kết nối nhà tuyển dụng và ứng viên có nhu cầu ", 
				"Ngôn ngữ sử dụng: Java Android Link app: https://play.google.com/store/apps/details?id=vn.timviec365.myapplication", 
				"+/ Code app timviec365.vn");
		assertTrue(reality < 1);
	}
	
}

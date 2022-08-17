package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.dto.InvolvedUpdateRequestDTO;
import com.codedecode.demo.repository.AppliedJobRepository;
import com.codedecode.demo.repository.InvolvedProjectRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class InvolvedProjectServiceTest {
	
	@MockBean
	private InvolvedProjectRepository involvedProjectRepository;
	
	@InjectMocks
	private InvolvedProjectService  involvedProjectService;
	
	@Test
	public void updateInvolvedProject() {
		Integer expect = 0;
		
		when(involvedProjectRepository.updateInvolvedProject(1L, "Đây là dự án để kết nối nhà tuyển dụng và ứng viên có nhu cầu tìm việc"
				, "Ngôn ngữ sử dụng: Java Android Link app: https://play.google.com/store/apps/details?id=vn.timviec365.myapplication", 
				"+/ Code app timviec365.vn")).thenReturn(expect);
		
		List<InvolvedUpdateRequestDTO> dtos = new ArrayList<>();
		InvolvedUpdateRequestDTO dto = new InvolvedUpdateRequestDTO();
		dto.setId(1L);
		dto.setName("+/ Code app timviec365.vn");
		dto.setDescription("Đây là dự án để kết nối nhà tuyển dụng và ứng viên có nhu cầu tìm việc");
		dto.setProjectDescription("Ngôn ngữ sử dụng: Java Android Link app: https://play.google.com/store/apps/details?id=vn.timviec365.myapplication");
		dtos.add(dto);	
		
		assertThat(expect == 0);
	}
	
	@Test
	public void updateInvolvedProject1() {
		Integer expect = 0;
		
		when(involvedProjectRepository.updateInvolvedProject(1L, "Đây là dự án để kết nối nhà tuyển dụng và ứng viên có nhu cầu tìm việc"
				, "Ngôn ngữ sử dụng: Java Android Link app: https://play.google.com/store/apps/details?id=vn.timviec365.myapplication", 
				"+/ Code app timviec365.vn")).thenReturn(expect);
		
		List<InvolvedUpdateRequestDTO> dtos = new ArrayList<>();
		InvolvedUpdateRequestDTO dto = new InvolvedUpdateRequestDTO();
		dto.setId(1L);
		dto.setName("+/ Code app timviec365.vn");
		dto.setDescription("Đây là dự án để kết nối nhà tuyển dụng và ứng viên có nhu cầu tìm việc");
		dto.setProjectDescription("Ngôn ngữ sử dụng: Java Android Link app: https://play.google.com/store/apps/details?id=vn.timviec365.myapplication");
		dtos.add(dto);	
		
		assertThat(expect != 0);
	}
	
}

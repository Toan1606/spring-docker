package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.AppliedJobRepository;
import com.codedecode.demo.repository.LanguageCertificateRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class LanguageCertificateServiceTest {
	
	@MockBean
	private LanguageCertificateRepository languageCertificateRepository;
	
	@InjectMocks
	private LanguageCertificateService  certificateService;
	
	
	@Test
	public void findAllLanguageCertificatesByUserId() {	
		List<Language> languages = new ArrayList<Language>();
		when(languageCertificateRepository.findAllLanguageCertificatesByUserId(2L)).thenReturn(languages);
		List<Language> reality = certificateService.findAllLanguageCertificatesByUserId(2L);
		assertNotNull(reality);
	}
	
	@Test
	public void findAllLanguageCertificatesByUserId1() {	
		List<Language> languages = new ArrayList<Language>();
		when(languageCertificateRepository.findAllLanguageCertificatesByUserId(2L)).thenReturn(languages);
		List<Language> reality = certificateService.findAllLanguageCertificatesByUserId(1L);
		assertThat(reality == null);
	}
	
	@Test
	public void findAllLanguageCertificatesByUserId3() {	
		List<Language> languages = new ArrayList<Language>();
		when(languageCertificateRepository.findAllLanguageCertificatesByUserId(2L)).thenReturn(languages);
		List<Language> reality = certificateService.findAllLanguageCertificatesByUserId(2L);
		assertThat(reality.size() > 0);
	}
	
	@Test
	public void findLanguageCertificateById() {	
		Language languages = new Language();
		when(languageCertificateRepository.findLanguageCertificateById(2L)).thenReturn(languages);
		Language reality = certificateService.findLanguageCertificateById(2L);
		assertThat(reality != null);
	}
	
	@Test
	public void findLanguageCertificateById1() {	
		Language languages = new Language();
		when(languageCertificateRepository.findLanguageCertificateById(2L)).thenReturn(languages);
		Language reality = certificateService.findLanguageCertificateById(2L);
		assertNotNull(reality);
	}
	
	@Test
	public void findLanguage() {
		User user = new User(); 
		Language languages = new Language(11L, "Toeic", "Tiếng Anh", 990.f, user);
		when(languageCertificateRepository.findLanguageCertificateById(2L)).thenReturn(languages);
		Language reality = certificateService.findLanguageCertificateById(2L);
		assertNotNull(reality);
	}
	
	@Test
	public void findLanguage1() {
		User user = new User(); 
		Language languages = new Language(11L, "Toeic", "Tiếng Anh", 990.f, user);
		when(languageCertificateRepository.findLanguageCertificateById(2L)).thenReturn(languages);
		Language reality = certificateService.findLanguageCertificateById(2L);
		assertThat(reality != null);
	}
	
	@Test
	public void findLanguage2() {
		User user = new User(); 
		Language languages = new Language(100L, "Toeic", "Tiếng Anh", 990.f, user);
		when(languageCertificateRepository.findLanguageCertificateById(2L)).thenReturn(languages);
		Language reality = certificateService.findLanguageCertificateById(2L);
		assertThat(reality == null);
	}
}

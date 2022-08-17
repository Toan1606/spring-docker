package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Language;

@SpringBootTest
public class LanguageCertificateRepositoryTest {
	
	@Autowired
	private LanguageCertificateRepository certificateRepository;
	
	@Test
	public void findAllLanguageCertificatesByUserId() {
		List<Language> reality = certificateRepository.findAllLanguageCertificatesByUserId(1l);
		assertThat(reality.size()).isGreaterThan(1);	
	}
	
	@Test
	public void findAllLanguageCertificatesByUserId1() {
		List<Language> reality = certificateRepository.findAllLanguageCertificatesByUserId(1l);
		assertThat(reality.size()).isBetween(9, 11);	
	}
	
	@Test
	public void findAllLanguageCertificatesByUserId2() {
		List<Language> reality = certificateRepository.findAllLanguageCertificatesByUserId(1l);
		assertThat(!reality.isEmpty());	
	}
	
	@Test
	public void findAllLanguageCertificatesByUserId3() {
		List<Language> reality = certificateRepository.findAllLanguageCertificatesByUserId(1l);
		assertThat(reality.size()).isEqualTo(10);	
	}
	
	@Test
	public void findAllLanguageCertificatesByUserId4() {
		List<Language> reality = certificateRepository.findAllLanguageCertificatesByUserId(1l);
		assertThat(reality.size()).isLessThan(20);	
	}
	
	@Test
	public void findLanguageCertificateById() {
		Language reality = certificateRepository.findLanguageCertificateById(11l);
		assertThat(reality.getId() == 11);	
	}
	
	@Test
	public void findLanguageCertificateById1() {
		Language reality = certificateRepository.findLanguageCertificateById(11l);
		assertThat(reality != null);	
	}
	
	@Test
	public void findLanguageCertificateById2() {
		Language reality = certificateRepository.findLanguageCertificateById(11l);
		assertFalse(reality.getId() != 11);	
	}
	
	@Test
	public void findLanguageCertificateById3() {
		Language reality = certificateRepository.findLanguageCertificateById(11l);
		assertFalse(reality.getGrade() > 1000);	
	}
	
	@Test
	public void findLanguageCertificateById4() {
		Language reality = certificateRepository.findLanguageCertificateById(11l);
		assertFalse(reality.getGrade() < 0);	
	}
	
}

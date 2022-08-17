package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Province;

@SpringBootTest
public class ProvinceRepositoryTest {
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Test
	public void findByName() {
		Optional<Province> reality = provinceRepository.findByName("Thành phố Hà Nội");
		assertThat(reality).isPresent();
	}
	
	@Test
	public void findByName1() {
		Optional<Province> reality = provinceRepository.findByName("Thành phố Hà Nội");
		assertThat(reality).isNotEmpty();
	}
	
	@Test
	public void findByAddressId() {
		Province reality = provinceRepository.findByAddressId(1L);
		assertThat(reality != null);
	}
	
	@Test
	public void findByAddressId1() {
		Province reality = provinceRepository.findByAddressId(1L);
		assertFalse(reality == null);
	}
	
	@Test
	public void findByAddressId2() {
		Province reality = provinceRepository.findByAddressId(1L);
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void findProvinceByName() {
		Province reality = provinceRepository.findProvinceByName("Thành phố Hà Nội");
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void findProvinceByName1() {
		Province reality = provinceRepository.findProvinceByName("Thành phố Hà Nội");
		assertFalse(reality == null);
	}
	
	@Test
	public void findProvinceByName2() {
		Province reality = provinceRepository.findProvinceByName("Thành phố Hà Nội");
		assertThat(reality != null);
	}
	
}

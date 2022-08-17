package com.codedecode.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.City;

@SpringBootTest
public class DistrictRepositoryTest {

	@Autowired
	private DistrictRepository districtRepository;
	
	@Test
	public void testGetAllDistrict() {
		Long cityId = 1L;
		List<City> reality = districtRepository.getAllDistrict(cityId);
		assertNotNull(reality);
	}
	
	@Test
	public void testGetAllDistrict2() {
		Long cityId = 1L;
		List<City> reality = districtRepository.getAllDistrict(cityId);
		assertNull(reality);
	}
	
	@Test
	public void testFindDistrictByName() {
		String name = "Ba Đình";
		City reality = districtRepository.findDistrictByName(name);
		assertNull(reality);
	}
	
	@Test
	public void testFindDistrictByName2() {
		String name = "Bốn Đình";
		City reality = districtRepository.findDistrictByName(name);
		assertNull(reality);
	}
}

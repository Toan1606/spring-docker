package com.codedecode.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.dto.CityResponseDTO;
import com.codedecode.demo.entity.City;

@SpringBootTest
public class CityRepositoryTest {

	@Autowired
	private CityRepository cityRepository;
	
	@Test
	public void testFindByName() {
		String cityName = "Bắc Từ Liêm";
		Optional<City> city = cityRepository.findByName(cityName);
		assertTrue(city.isPresent());
	}
	
	@Test
	public void testFindByName2() {
		String cityName = "Ha Noi";
		Optional<City> city = cityRepository.findByName(cityName);
		assertFalse(city.isPresent());
	}
	
	@Test
	public void testFindByAddressId() {
		Long addressId = 1l;
		City city = cityRepository.findByAddressId(addressId);
		assertNotNull(city);
	}
	
	@Test
	public void testFindByAddressId3() {
		Long addressId = 1l;
		City city = cityRepository.findByAddressId(addressId);
		assertEquals(city.getName(), "Bắc Từ Liêm");
	}
	
	@Test
	public void testFindByAddressId4() {
		Long addressId = 1l;
		City city = cityRepository.findByAddressId(addressId);
		boolean reality = city.getName().equals("Nam Từ Liêm");
		assertTrue(reality);
	}
	
	@Test
	public void testFindCityByProvinceId() {
		Long provinceId = 1L;
		List<City> cities = cityRepository.findCityByProvinceId(provinceId);
		assertNotNull(cities);
	}
	
	@Test
	public void testFindCityByProvinceId2() {
		Long provinceId = 1L;
		List<City> cities = cityRepository.findCityByProvinceId(provinceId);
		assertNull(cities);
	}
	
	@Test
	public void testFindAllCityDto() {
		List<CityResponseDTO> cityDto = cityRepository.findAllCityDto();
		assertNotNull(cityDto);
	}
	
	@Test
	public void testFindAllCityDto2() {
		List<CityResponseDTO> cityDto = cityRepository.findAllCityDto();
		assertNull(cityDto);
	}
	
	@Test
	public void testFindAllCityByProvinceId() {
		Long provinceId = 1L;
		List<City> cities = cityRepository.findAllCityByProvinceId(provinceId);
		assertNotNull(cities);
	}
	
	@Test
	public void testFindAllCityByProvinceId2() {
		Long provinceId = 1L;
		List<City> cities = cityRepository.findAllCityByProvinceId(provinceId);
		assertNull(cities);
	}
}

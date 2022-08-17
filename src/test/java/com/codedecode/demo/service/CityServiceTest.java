package com.codedecode.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.dto.CityResponseDTO;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.repository.CityRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CityServiceTest {

	@MockBean
	private CityRepository cityRepository;

	@InjectMocks
	private CityService cityService;
	
	@Test
	public void testFindCityById() {
		Long id = 1L;
		Optional<City> expect = Optional.of(new City());
		
		when(cityRepository.findById(id)).thenReturn(expect);
		
		City reality = cityService.findCityById(id);
		assertNotNull(reality);
	}
	
	@Test
	public void testFindCityByCityName() {
		String cityName = "Hạ Long";
		Optional<City> expect = Optional.of(new City());
		
		when(cityRepository.findByName(cityName)).thenReturn(expect);
		
		City reality = cityService.findCityByCityName(cityName);
		assertNotNull(reality);
	}
	
	@Test
	public void testFindAllCity() {
		List<City> expect = new ArrayList<City>();
		
		when(cityRepository.findAll()).thenReturn(expect);
		
		List<City> reality = cityService.findAllCity();
		assertNotNull(reality);
	}
	
	@Test
	public void testFindAllCityByProvinceId() {
		Long provinceId = 1L;
		List<City> expect = new ArrayList<City>();
		
		when(cityRepository.findAllCityByProvinceId(provinceId)).thenReturn(expect);
		
		List<City> reality = cityService.findAllCityByProvinceId(provinceId);
		assertNotNull(reality);
	}
	
	@Test
	public void testFindAllCityDto() {
		List<CityResponseDTO> expect = new ArrayList<CityResponseDTO>();
		
		when(cityRepository.findAllCityDto()).thenReturn(expect);
		
		List<CityResponseDTO> reality = cityService.findAllCityDto();
		assertNotNull(reality);
	}
	
	@Test
	public void testFindByAddress() {		
		List<CityResponseDTO> reality = cityService.findAllCityDto();
		assertNotNull(reality);
	}
	
	@Test
	public void testFindByAddressId() {
		Long addressId = 1L;
		City expect = new City();
		
		when(cityRepository.findByAddressId(addressId)).thenReturn(expect);
		
		City reality = cityService.findByAddressId(addressId);
		assertNotNull(reality);
	}
	
	@Test
	public void testConvertToDTO() {
		List<City> cities = new ArrayList<City>();
		List<CityResponseDTO> reality = cityService.convertToDTO(cities);
		assertNotNull(reality);
	}
	
	@Test
	public void testFindCityByProvinceId() {
		Long provinceId = 1L;
		List<City> expect = new ArrayList<City>();
		
		when(cityRepository.findCityByProvinceId(provinceId)).thenReturn(expect);
		
		List<City> reality = cityService.findCityByProvinceId(provinceId);
		assertNotNull(reality);
	}
}

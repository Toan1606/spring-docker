package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Street;
import com.codedecode.demo.repository.StreetRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class StreetServiceTest {
	@MockBean
	private StreetRepository streetRepository;
	
	@InjectMocks
	private StreetService streetService;
	
	@Test
	public void testFindStreetById1() {
		Long steetId = 1L;
		
		Street street = new Street();
		
		Optional<Street> expect = Optional.of(street);
		
		when(streetRepository.findById(steetId)).thenReturn(expect);
		
		Optional<Street> reality = Optional.of(streetService.findStreetById(steetId));
		
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testFindStreetById2() {
		Long steetId = 1L;
		
		Street street = new Street();
		
		Optional<Street> expect = Optional.of(street);
		
		when(streetRepository.findById(steetId)).thenReturn(expect);
		
		Optional<Street> reality = Optional.of(streetService.findStreetById(steetId));
		
		assertThat(reality).isEmpty();
	}
	
	@Test
	public void testFindStreetByCityId1() {
		Long cityId = 1L;
		
		City city = new City();
		city.setId(cityId);
		city.setName("Bắc Từ Liêm");
		
		Optional<Street> list = Optional.empty();
		
		when(streetRepository.findById(cityId)).thenReturn(list);
		
		List<Street> reality = streetService.findStreetByCityId(city);
		
		assertThat(reality).isEmpty();
	}
	
	@Test
	public void testFindStreetByCityId2() {
		Long cityId = 1L;
		
		City city = new City();
		city.setId(cityId);
		city.setName("Bắc Từ Liêm");
		
		Optional<Street> list = Optional.empty();
		
		when(streetRepository.findById(cityId)).thenReturn(list);
		
		List<Street> reality = streetService.findStreetByCityId(city);
		
		assertThat(reality.size()).isGreaterThan(0);
	}
	@Test
	public void testFindStreetByCityId3() {
		Long cityId = 1L;
		
		City city = new City();
		city.setId(cityId);
		city.setName("Bắc Từ Liêm");
		
		Optional<Street> list = Optional.empty();
		
		when(streetRepository.findById(cityId)).thenReturn(list);
		
		List<Street> reality = streetService.findStreetByCityId(city);
		
		assertThat(reality.size()).isLessThan(0);
	}
	@Test
	public void testFindStreetByCityId4() {
		Long cityId = 1L;
		
		City city = new City();
		city.setId(cityId);
		city.setName("Bắc Từ Liêm");
		
		Optional<Street> list = Optional.empty();
		
		when(streetRepository.findById(cityId)).thenReturn(list);
		
		List<Street> reality = streetService.findStreetByCityId(city);
		
		assertThat(reality.size()).isEqualTo(0);
	}
}

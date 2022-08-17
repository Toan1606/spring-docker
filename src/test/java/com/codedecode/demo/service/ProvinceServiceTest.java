package com.codedecode.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.repository.ProvinceRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ProvinceServiceTest {

	@MockBean
	private ProvinceRepository provinceRepository;
	
	@InjectMocks
	private ProvinceService provinceService;
	
	@Test
	public void testFindProvinceById() {
		Long id = 1L;
		Optional<Province> expect = Optional.of(new Province());
		
		when(provinceRepository.findById(id)).thenReturn(expect);
		
		Province reality = provinceService.findProvinceById(id);
		assertNotNull(reality);
	}
	
	@Test
	public void testFindProvinceByName() {
		String provinceName = "Đà Nẵng";
		Optional<Province> expect = Optional.of(new Province());
		
		when(provinceRepository.findByName(provinceName)).thenReturn(expect);
		
		Province reality = provinceService.findProvinceByName(provinceName);
		assertNotNull(reality);
	}
	
	@Test
	public void testFindAll() {
		List<Province> expect = new ArrayList<Province>();
		
		when(provinceRepository.findAll()).thenReturn(expect);
		
		List<Province> reality = provinceService.findAll();
		assertNotNull(reality);
	}
	
	@Test
	public void testFindByAddress() {
		Set<Address> addresss = new HashSet<Address>();
		Province expect = new Province();
		
		when(provinceRepository.findByAddressId(1L)).thenReturn(expect);
		
		List<Map<String, String>> reality = provinceService.findByAddress(addresss);
		assertNotNull(reality);
	}
	
	@Test
	public void testFindByAddressId() {
		Long addressId = 1L;
		Province expect = new Province();
		
		when(provinceRepository.findByAddressId(addressId)).thenReturn(expect);
		
		Province reality = provinceService.findByAddressId(addressId);
		assertNotNull(reality);
	}
}

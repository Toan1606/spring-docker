package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.repository.AddressRepository;
import com.codedecode.demo.service.AddressService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class AddressServiceTest {
	@MockBean
	private AddressRepository addressRepository;
	
	@InjectMocks
	private AddressService addressService;
	
	@Test
	public void testFindAddressByProvinceAndCity1() {
		Long provinceId = 1L;
		Long cityId = 1L;
		Address address = new Address();
		Optional<Address> expect = Optional.of(address);
		when(addressRepository.findAddressByProvinceAndCity(provinceId, cityId)).thenReturn(expect);
		
		Address reality = addressService.findAddressByProvinceAndCity(provinceId, cityId);
		
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testFindAddressByProvinceAndCity2() {
		Long provinceId = 1000L;
		Long cityId = 2L;
		
		Address address = new Address();
		
		Optional<Address> expect = Optional.of(address);
		
		when(addressRepository.findAddressByProvinceAndCity(provinceId, cityId)).thenReturn(expect);
		
		Address reality = addressService.findAddressByProvinceAndCity(provinceId, cityId);
		
		assertThat(reality).isNotNull();
	}
	@Test
	public void testFindAddressByProvinceAndCity3() {
		Long provinceId = 1000L;
		Long cityId = 200L;
		
		Address address = new Address();
		
		Optional<Address> expect = Optional.of(address);
		
		when(addressRepository.findAddressByProvinceAndCity(provinceId, cityId)).thenReturn(expect);
		
		Address reality = addressService.findAddressByProvinceAndCity(provinceId, cityId);
		
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testFindAddressById1() {
		Long addressId = 1L;
		
		Address address = new Address();
		
		Optional<Address> expect = Optional.of(address);
		
		when(addressRepository.findById(addressId)).thenReturn(expect);
		
		Optional<Address> reality = Optional.of(addressService.findAddressById(addressId));
		
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testFindAddressById2() {
		Long addressId = 200L;
		
		Address address = new Address();
		
		Optional<Address> expect = Optional.of(address);
		
		when(addressRepository.findById(addressId)).thenReturn(expect);
		
		Optional<Address> reality = Optional.of(addressService.findAddressById(addressId));
		
		assertThat(reality).isNotNull();
	}
	@Test
	public void testFindAddressByPostingId1() {
		Long postingId = 1L;
		
		Set<Address> list = null;
		
		when(addressRepository.findByPostingId(postingId)).thenReturn(list);
		
		Set<Address> reality = addressService.findAddressByPostingId(postingId);
		
		assertThat(reality).isNull();
	}
	@Test
	public void testFindAddressByPostingId2() {
		Long postingId = 1L;
		
		Set<Address> list = null;
		
		when(addressRepository.findByPostingId(postingId)).thenReturn(list);
		
		Set<Address> reality = addressService.findAddressByPostingId(postingId);
		
		assertThat(reality).isNotNull();
	}
	@Test
	public void testFindAddressByPostingId3() {
		Long postingId = 200L;
		
		Set<Address> list = null;
		
		when(addressRepository.findByPostingId(postingId)).thenReturn(list);
		
		Set<Address> reality = addressService.findAddressByPostingId(postingId);
		
		assertThat(reality).isNotNull();
	}
}

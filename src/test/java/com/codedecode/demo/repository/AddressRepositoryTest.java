package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Address;

@SpringBootTest
public class AddressRepositoryTest {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Test
	public void testFindAddressByProvinceAndCity1() {
		Long provinceId = 1L;
		Long cityId = 1L;
		String addressName = "Hà Nội-Thành phố Trung ương";
		Optional<Address> reality = addressRepository.findAddressByProvinceAndCity(provinceId, cityId);
		assertTrue(reality.get().getName().equals(addressName));
	}
	
	@Test
	public void testFindAddressByProvinceAndCity2() {
		Long provinceId = 1L;
		Long cityId = 1L;
		Optional<Address> reality = addressRepository.findAddressByProvinceAndCity(provinceId, cityId);
		assertTrue(reality.isPresent());
	}
	
	@Test
	public void testFindAddressByProvinceAndCity3() {
		Long provinceId = 100L;
		Long cityId = 1L;
		Optional<Address> reality = addressRepository.findAddressByProvinceAndCity(provinceId, cityId);
		assertTrue(reality.isEmpty());
	}
	
	@Test
	public void testFindAddressByProvinceAndCity4() {
		Long provinceId = 1L;
		Long cityId = 100L;
		Optional<Address> reality = addressRepository.findAddressByProvinceAndCity(provinceId, cityId);
		assertTrue(reality.isEmpty());
	}
	@Test
	public void testFindByPostingId1() {
		Long postingId = 1l;
		Set<Address> reality = addressRepository.findByPostingId(postingId);
		assertThat(reality.size()).isGreaterThan(0);
	}
	
	@Test
	public void testFindByPostingId2() {
		Long postingId = 100l;
		Set<Address> reality = addressRepository.findByPostingId(postingId);
		assertThat(reality.size()).isLessThan(0);
	}
	@Test
	public void testFindByPostingId3() {
		Long postingId = 10l;
		Set<Address> reality = addressRepository.findByPostingId(postingId);
		assertThat(reality.size()).isEqualTo(0);
	}
}

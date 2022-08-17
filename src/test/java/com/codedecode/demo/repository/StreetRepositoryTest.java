package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Street;

@SpringBootTest
public class StreetRepositoryTest {

	@Autowired
	private StreetRepository streetRepository;

	@Test
	public void testFindByAddressId1() {
		Long addressId = 1L;
		Street reality = streetRepository.findByAddressId(addressId);
		assertThat(reality).isNotNull();
	}

	@Test
	public void testFindByAddressId2() {
		Long addressId = 100L;
		Street reality = streetRepository.findByAddressId(addressId);
		assertThat(reality).isNull();
	}

	@Test
	public void testFindByAddressId3() {
		Long addressId = 100L;
		Street reality = streetRepository.findByAddressId(addressId);
		assertThat(reality).isNotNull();
	}
}

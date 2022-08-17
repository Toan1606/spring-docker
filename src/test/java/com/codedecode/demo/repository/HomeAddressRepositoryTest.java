package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Address;

@SpringBootTest
public class HomeAddressRepositoryTest {

	@Autowired HomeAddressRepository addressRepository;
	
	@Test
	public void testFindAddress() {
		List<Address> addresss = addressRepository.findAddress();
		assertNotNull(addresss);
	}
	
	@Test
	public void testFindAddress2() {
		List<Address> addresss = addressRepository.findAddress();
		
		assertThat(addresss.size()).isGreaterThan(1);
	}
	
	@Test
	public void testFindAddress3() {
		List<Address> addresss = addressRepository.findAddress();
		assertThat(addresss.size()).isEqualTo(0);
	}
	
	@Test
	public void testFindAddress4() {
		List<Address> addresss = addressRepository.findAddress();
		assertThat(addresss.size()).isLessThan(1);
	}
	
	@Test
	public void testFindAddress5() {
		List<Address> addresss = addressRepository.findAddress();
		assertThat(addresss.size()).isGreaterThan(1);
	}
}

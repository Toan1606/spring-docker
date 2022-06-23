package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.Address;

public interface HomeAddressRepository extends JpaRepository<Address, Long>{
	
	@Query(value = "SELECT * FROM jobez.address" , nativeQuery = true)
	List<Address> getAllJobByProvince();
	
}

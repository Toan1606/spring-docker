package com.codedecode.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query(value = "SELECT * FROM Province p INNER JOIN City c WHERE p.id = c.province_id AND p.name = :provinceName AND c.name = :cityName" , nativeQuery = true)
	Optional<Address> findByProvinceAndCity(@Param("provinceName") String provinceName, @Param("cityName") String cityName);
	
}

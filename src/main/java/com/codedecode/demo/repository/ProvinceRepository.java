package com.codedecode.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long>{

	Optional<Province> findByName(String provinceName);

	@Query(value = "select * from province p join address a on p.id =a.province_id where a.id = :addressId", nativeQuery = true)
	Province findByAddressId(@Param("addressId") Long addressId);
	
	@Query(value= "select * from province where name = ?1", nativeQuery = true)
	Province findProvinceByName(String name);
	
}

package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.City;

@Repository
public interface DistrictRepository extends JpaRepository<City, Long>{

	@Query(value="select * from city where province_id = ?1", nativeQuery=true)
	List<City> getAllDistrict(Long id);
	
	@Query(value="select * from city where name = ?1", nativeQuery = true)
	City findDistrictByName(String name);
}

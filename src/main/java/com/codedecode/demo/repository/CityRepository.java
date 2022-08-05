package com.codedecode.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Province;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
	
	Optional<City> findByName(String name);
	
	List<City> findByProvince(Province province);

	@Query(value = "select * from city c join address a on c.id =a.province_id where a.id = :addressId", nativeQuery = true)
	City findByAddressId(@Param("addressId") Long addressId);

	@Query(value = "select * from city c join province p on c.province_id =  p.id where p.id = :provinceId", nativeQuery = true)
	List<City> findCityByProvinceId(Long provinceId);
}

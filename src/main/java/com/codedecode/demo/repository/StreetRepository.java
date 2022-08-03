package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long>{
	
	List<Street> findByCity(City city);

	@Query(value = "select * from street s join address a on s.id =a.street_id where a.id = :addressId", nativeQuery = true)
	Street findByAddressId(@Param("addressId") Long addressId);
}

package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long>{
	
	List<Street> findByCity(City city);
}

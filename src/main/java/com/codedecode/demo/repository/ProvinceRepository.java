package com.codedecode.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long>{

	Optional<Province> findByName(String provinceName);

}

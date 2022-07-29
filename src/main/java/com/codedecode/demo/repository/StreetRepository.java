package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long>{

}

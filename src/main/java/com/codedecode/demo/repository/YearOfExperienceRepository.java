package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.YearOfExperience;

@Repository
public interface YearOfExperienceRepository extends JpaRepository<YearOfExperience, Long>{

}

package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.DesiredJob;

@Repository
public interface DesiredJobRepository  extends JpaRepository<DesiredJob, Long>{

}

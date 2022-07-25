package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long>{
	
	@Query(value="select * from salary", nativeQuery=true)
	List<Salary> getAllSalary();
	
}

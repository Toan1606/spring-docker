package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long>{
	
}

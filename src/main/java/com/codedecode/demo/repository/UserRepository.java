package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codedecode.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

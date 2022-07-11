package com.codedecode.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value="select * from Users where id = ?1", nativeQuery=true)
	User getUserById(int id);
	
	Optional<User> findByEmail(String email);
}

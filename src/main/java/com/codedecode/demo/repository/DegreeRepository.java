package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Degree;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long>{
	
	@Query(value="select * from degree as d join cv_degree as cd on d.id = cd.degree_id join cvs as c on cd.cv_id = c.id join users as u on u.cv_id = c.id where u.id = ?1", nativeQuery= true)
	List<Degree>getAllDegreeByUserId(Long userId);
}

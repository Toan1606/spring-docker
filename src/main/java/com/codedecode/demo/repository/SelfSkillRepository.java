package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.SelfSkill;

@Repository
public interface SelfSkillRepository extends JpaRepository<SelfSkill, Long>{
	@Query(value="select * from self_skill where user_id = ?1", nativeQuery=true)
	SelfSkill getSelfSkillByUserId(Long userID);
	
	@Query(value="select * from self_skill where id = ?1", nativeQuery=true)
	SelfSkill getSelfSkillById(Long id);
}

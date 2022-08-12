package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.CandidateProfileSaved;

@Repository
public interface CandidateProfileSavedRepository extends JpaRepository<CandidateProfileSaved, Long>{

}

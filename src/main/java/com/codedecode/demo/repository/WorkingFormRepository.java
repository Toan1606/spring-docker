package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.WorkingForm;

@Repository
public interface WorkingFormRepository extends JpaRepository<WorkingForm, Long> {

}

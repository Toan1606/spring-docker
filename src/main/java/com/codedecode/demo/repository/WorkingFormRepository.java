package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codedecode.demo.entity.WorkingForm;

public interface WorkingFormRepository extends JpaRepository<WorkingForm, Long> {

}

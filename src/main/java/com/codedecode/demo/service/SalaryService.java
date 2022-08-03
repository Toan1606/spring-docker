package com.codedecode.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.exception.SalaryException;
import com.codedecode.demo.repository.SalaryRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class SalaryService {
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	public Salary findSalaryById(Long salaryId) {
		return salaryRepository.findById(salaryId).orElseThrow(() -> new SalaryException(ExceptionMessage.SALARY_EXCEPTION.getErrorMessage()));
	}
}

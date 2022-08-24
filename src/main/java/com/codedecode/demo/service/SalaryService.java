package com.codedecode.demo.service;

import java.util.List;

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

	private final SalaryRepository salaryRepository;

	@Autowired
	public SalaryService(SalaryRepository salaryRepository) {
		this.salaryRepository = salaryRepository;
	}

	public Salary findSalaryById(Long salaryId) {
		return salaryRepository.findById(salaryId).orElseThrow(() -> new SalaryException(ExceptionMessage.SALARY_EXCEPTION.getErrorMessage()));
	}

	public List<Salary> findAll() {
		return salaryRepository.findAll();
	}
}

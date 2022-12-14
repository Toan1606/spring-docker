package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.exception.WorkingFormException;
import com.codedecode.demo.exception.WorkingFormNotFoundException;
import com.codedecode.demo.repository.WorkingFormRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class WorkingFormService {

	private final WorkingFormRepository workingFormRepository;

	@Autowired
	public WorkingFormService(WorkingFormRepository workingFormRepository) {
		this.workingFormRepository = workingFormRepository;
	}

	public WorkingForm findWorkingFormById(Long workingFormId) {
		return workingFormRepository.findById(workingFormId).orElseThrow(() -> new WorkingFormException(ExceptionMessage.WORKING_FORM_EXCEPTION.getErrorMessage()));
	}
	
	public List<WorkingForm> findAll() {
		return workingFormRepository.findAll();
	}

	public WorkingForm findById(Long workingFormId) {
		return workingFormRepository.findById(workingFormId).orElseThrow(() -> new WorkingFormNotFoundException("Working Form Not Found"));
	}
}

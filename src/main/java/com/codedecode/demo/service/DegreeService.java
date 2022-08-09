package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.repository.DegreeRepository;

@Service
@Transactional
public class DegreeService {
	@Autowired
	private DegreeRepository degreeRepository;

	public List<Degree> getAllDegreeByUserId(Long userId) {
		return degreeRepository.findAll();
	}
}

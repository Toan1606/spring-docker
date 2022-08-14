package com.codedecode.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.dto.DegreeUpdateRequestDTO;
import com.codedecode.demo.dto.EducationDegreeDTO;
import com.codedecode.demo.entity.CV;
import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.exception.NotUpdateException;
import com.codedecode.demo.repository.DegreeRepository;

@Service
@Transactional
public class DegreeService {
	@Autowired
	private DegreeRepository degreeRepository;

	public List<Degree> getAllDegreeByUserId(Long userId) {
		return degreeRepository.findAll();
	}

	public void deleteDegree(Long id) {
		degreeRepository.deleteDegree(id);
	}

	public Degree getDegreeById(Long id) {
		return degreeRepository.getOne(id);
	}

	public boolean isDuplicate(EducationDegreeDTO degree, CV cv) {
//		List<Degree> list = degreeRepository.getAllDegreeByUserId(degree.getUserId());
//		for (Degree d : list) {
//			if(d.getCertificateName().equals(degree.getCertificateName())
//					&& d.getTeachingUnit().equals(degree.getTeachingUnit())
//					&& formatDate(d.getStartTime()).compareTo(formatDate(degree.getStartTime())) == 0
//					&& formatDate(d.getEndTime()).compareTo(formatDate(degree.getEndTime())) == 0
//					&& d.getMajor().equals(degree.getMajor())
//					&& d.getRank().equals(degree.getRank())
//					&& d.getSupplementaryInformation().equals(degree.getSupplementaryInformation())
//					&& )
//		}
		return false;
	}

	public String formatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public Integer updateDegree(List<DegreeUpdateRequestDTO> degrees) {
		if (degrees == null)
			return -1;
		
		int effectColumns = 0;

		for (DegreeUpdateRequestDTO degree : degrees) {
			int effect = degreeRepository.updateDegree(degree.getId(), degree.getName(), degree.getName());
			effectColumns += effect;
		}
		if (effectColumns != degrees.size()) {
			throw new NotUpdateException("Degree Is Not Update");
		}
		return effectColumns;
	}
}

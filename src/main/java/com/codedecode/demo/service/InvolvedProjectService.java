package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.dto.InvolvedUpdateRequestDTO;
import com.codedecode.demo.exception.NotUpdateException;
import com.codedecode.demo.repository.InvolvedProjectRepository;

@Service
@Transactional
public class InvolvedProjectService {
	
	@Autowired
	private InvolvedProjectRepository involvedProjectRepository;
	
	public Integer updateInvolvedProject(List<InvolvedUpdateRequestDTO> involvedProjects) {
		if (involvedProjects == null)
			return -1;
		
		int effectColumns = 0;
		
		for (InvolvedUpdateRequestDTO involvedProject : involvedProjects) {
			Long id = involvedProject.getId();
			String name = involvedProject.getName();
			String description = involvedProject.getDescription();
			String projectDescription = involvedProject.getProjectDescription();
			
			int effect = involvedProjectRepository.updateInvolvedProject(id, name, description, projectDescription);
			effectColumns += effect;
		}
		
		if (effectColumns != involvedProjects.size()) {
			throw new NotUpdateException("Involved Project Is Not Update");
		}
		
		return effectColumns;
	}
}

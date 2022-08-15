package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.dto.ActivityUpdateRequestDTO;
import com.codedecode.demo.exception.NotUpdateException;
import com.codedecode.demo.repository.ActivityRepository;

@Service
@Transactional
public class ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;

	public Integer updateActivity(List<ActivityUpdateRequestDTO> activities) {
		if (activities == null)
			return -1;
		
		int effectColumns = 0;
		
		for (ActivityUpdateRequestDTO activity : activities) {
			Long id = activity.getId();
			String name = activity.getName();
			String position = activity.getPosition();
			String description = activity.getDescription();
			
			int effect = activityRepository.updateActivity(id, name, position, description);
			effectColumns += effect;
		}
		
		if (effectColumns != activities.size()) {
			throw new NotUpdateException("Activity Is Not Update");
		}
		
		return effectColumns;
	}
}

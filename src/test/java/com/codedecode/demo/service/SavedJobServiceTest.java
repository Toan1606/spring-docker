package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.SavedJob;
import com.codedecode.demo.entity.key.SavedJobKey;
import com.codedecode.demo.repository.SavedJobRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class SavedJobServiceTest {
	@MockBean
	private SavedJobRepository savedJobRepository;
	
	@InjectMocks
	private SavedJobService savedJobService;
	
	@Test
	public void testGetAllSavedJobs1() {
		Long userId = 1L;
		List<SavedJob> list = new ArrayList<SavedJob>();
		when(savedJobRepository.getAllSavedJobs(userId)).thenReturn(list);
		
		List<SavedJob> reality = savedJobService.getAllSavedJobs(userId);
		
		assertThat(reality).isEmpty();
	}
	@Test
	public void testGetAllSavedJobs2() {
		Long userId = 1L;
		List<SavedJob> list = new ArrayList<SavedJob>();
		when(savedJobRepository.getAllSavedJobs(userId)).thenReturn(list);
		
		List<SavedJob> reality = savedJobService.getAllSavedJobs(userId);
		
		assertThat(reality.size()).isGreaterThan(0);
	}
	@Test
	public void testGetAllSavedJobs3() {
		Long userId = 100L;
		List<SavedJob> list = new ArrayList<SavedJob>();
		when(savedJobRepository.getAllSavedJobs(userId)).thenReturn(list);
		
		List<SavedJob> reality = savedJobService.getAllSavedJobs(userId);
		
		assertThat(reality.size()).isLessThan(0);
	}
	@Test
	public void testGetAllSavedJobs4() {
		Long userId = 2L;
		List<SavedJob> list = new ArrayList<SavedJob>();
		when(savedJobRepository.getAllSavedJobs(userId)).thenReturn(list);
		
		List<SavedJob> reality = savedJobService.getAllSavedJobs(userId);
		
		assertThat(reality.size()).isEqualTo(0);
	}
	
	@Test
	public void testGetSavedJobById1() {
		SavedJobKey key = new SavedJobKey();
		key.setPostingId(1L);
		key.setUserId(2L);
		
		SavedJob savedjob = new SavedJob();
		
		when(savedJobRepository.findBySavedJobKey(key)).thenReturn(savedjob);
		
		SavedJob reality = savedJobService.getSavedJobById(key);
		
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testGetSavedJobById2() {
		SavedJobKey key = new SavedJobKey();
		key.setPostingId(1L);
		key.setUserId(2L);
		
		SavedJob savedjob = new SavedJob();
		
		when(savedJobRepository.findBySavedJobKey(key)).thenReturn(savedjob);
		
		SavedJob reality = savedJobService.getSavedJobById(key);
		
		assertThat(reality).isNotNull();
	}
	@Test
	public void testAddNewSavedJob1() {
		SavedJobKey key = new SavedJobKey();
		key.setPostingId(10L);
		key.setUserId(2L);
		
		SavedJob savedjob = new SavedJob();
		savedjob.setSavedJobKey(key);
		savedjob.setSavedDate(new Date());
		
		SavedJob saveJob = new SavedJob();
		
		when(savedJobRepository.save(savedjob)).thenReturn(saveJob);
		
		SavedJob reality = savedJobService.addNewSavedJob(savedjob);
		
		assertThat(reality).isEqualTo(saveJob);
	}
	
	@Test
	public void testAddNewSavedJob2() {
		SavedJobKey key = new SavedJobKey();
		key.setPostingId(10L);
		key.setUserId(2L);
		
		SavedJob savedjob = new SavedJob();
		savedjob.setSavedJobKey(key);
		savedjob.setSavedDate(new Date());
		
		SavedJob saveJob = new SavedJob();
		
		when(savedJobRepository.save(savedjob)).thenReturn(saveJob);
		
		SavedJob reality = savedJobService.addNewSavedJob(savedjob);
		
		assertThat(reality).isNotNull();
	}
}

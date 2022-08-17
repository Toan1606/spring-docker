package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.SavedJob;
import com.codedecode.demo.entity.key.SavedJobKey;

@SpringBootTest
public class SavedJobRepositoryTest {
	@Autowired
	private SavedJobRepository savedJobRepository;

	@Test
	public void testGetAllSavedJobs1() {
		Long userId = 100L;
		List<SavedJob> reality = savedJobRepository.getAllSavedJobs(userId);
		assertTrue(reality.isEmpty());
	}

	@Test
	public void testGetAllSavedJobs2() {
		Long userId = 1L;
		List<SavedJob> reality = savedJobRepository.getAllSavedJobs(userId);
		assertThat(reality.size()).isGreaterThan(0);
	}

	@Test
	public void testGetAllSavedJobs3() {
		Long userId = 1L;
		List<SavedJob> reality = savedJobRepository.getAllSavedJobs(userId);
		assertThat(reality.size()).isLessThan(0);
	}

	@Test
	public void testGetAllSavedJobs4() {
		Long userId = 1L;
		List<SavedJob> reality = savedJobRepository.getAllSavedJobs(userId);
		assertThat(reality.size()).isEqualTo(0);
	}
	@Test
	public void testFindBySavedJobKey1() {
		SavedJobKey key = new SavedJobKey();
		Long userId = 1L;
		Long postingId = 1L;
		key.setPostingId(postingId);
		key.setUserId(userId);
		SavedJob reality = savedJobRepository.findBySavedJobKey(key);
		assertThat(reality).isNotNull();
	}
	@Test
	public void testFindBySavedJobKey2() {
		SavedJobKey key = new SavedJobKey();
		Long userId = 20L;
		Long postingId = 1L;
		key.setPostingId(postingId);
		key.setUserId(userId);
		SavedJob reality = savedJobRepository.findBySavedJobKey(key);
		assertThat(reality).isNull();
	}
	@Test
	public void testFindBySavedJobKey3() {
		SavedJobKey key = new SavedJobKey();
		Long userId = 1L;
		Long postingId = 20L;
		key.setPostingId(postingId);
		key.setUserId(userId);
		SavedJob reality = savedJobRepository.findBySavedJobKey(key);
		assertThat(reality).isNull();
	}
	@Test
	public void testFindBySavedJobKey4() {
		SavedJobKey key = new SavedJobKey();
		Long userId = 20L;
		Long postingId = 20L;
		key.setPostingId(postingId);
		key.setUserId(userId);
		SavedJob reality = savedJobRepository.findBySavedJobKey(key);
		assertThat(reality).isNotNull();
	}
}

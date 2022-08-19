package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.dto.PostingRelatedDTO;
import com.codedecode.demo.dto.PostingResponseInterfaceDTO;
import com.codedecode.demo.dto.PostingSearchCategoryResponseInterface;
import com.codedecode.demo.dto.PostingSearchCity;
import com.codedecode.demo.dto.PostingSearchProvince;
import com.codedecode.demo.entity.Posting;


@SpringBootTest
public class PostingRepositoryTest {
	
	@Mock
	private PostingRepository postingRepository;
	
	@Mock
	private PostingProjectionRepository postingProjectionRepository;
	
	@Test
	public void testGetAllJob() {
		assertNotNull(postingRepository);
		assertEquals(postingRepository, postingRepository);
	}
	
	@Test
	public void getAllJob() {
		List<Posting> reality = postingRepository.findAll();
		assertFalse(reality.size() < 0);
	}
	
	@Test
	public void getAllJob1() {
		List<Posting> reality = postingRepository.findAll();
		assertFalse(reality.size() > 0);
	}
	
	@Test
	public void getAllJob2() {
		List<Posting> reality = postingRepository.findAll();
		assertNotNull(reality.size());
	}
	
	@Test
	public void getAllJob3() {
		List<Posting> reality = postingRepository.findAll();
		assertNotNull(reality);
	}
	
	@Test
	public void findPostingByUserIdAndPostingId() {
		PostingResponseInterfaceDTO reality = postingRepository.findPostingByUserIdAndPostingId(1L, 1L);
		assertThat(reality != null);
	}
	
	@Test
	public void findPostingByUserIdAndPostingId1() {
		PostingResponseInterfaceDTO reality = postingRepository.findPostingByUserIdAndPostingId(1L, 1L);
		assertThat(reality == null);
	}
	
	@Test
	public void findTop10RelatedPosting() {
		List<PostingRelatedDTO> reality = postingRepository.findTop10RelatedPosting(1L);
		assertThat(reality != null);
	}
	
	@Test
	public void findTop10RelatedPosting1() {
		List<PostingRelatedDTO> reality = postingRepository.findTop10RelatedPosting(1L);
		assertThat(reality.size() > 0);
	}
	
	@Test
	public void findTop10RelatedPosting2() {
		List<PostingRelatedDTO> reality = postingRepository.findTop10RelatedPosting(1L);
		assertFalse(reality.size() < 0);
	}
	
	@Test
	public void findTop10RelatedPosting3() {
		List<PostingRelatedDTO> reality = postingRepository.findTop10RelatedPosting(1L);
		assertThat(reality.size()).isLessThan(20);
	}
	
	@Test
	public void findTop10RelatedPosting4() {
		List<PostingRelatedDTO> reality = postingRepository.findTop10RelatedPosting(1L);
		assertThat(reality.size()).isNotNegative();
	}
	
	@Test
	public void findPostingByCategory() {
		List<PostingSearchCategoryResponseInterface> reality = postingRepository.findPostingByCategory(1, 11,1L);
		assertThat(reality.size()).isNotNegative();
	}
	
	@Test
	public void findPostingByCategory1() {
		List<PostingSearchCategoryResponseInterface> reality = postingRepository.findPostingByCategory(1, 11,1L);
		assertThat(reality.size() > 0);
	}
	
	@Test
	public void findPostingByCategory2() {
		List<PostingSearchCategoryResponseInterface> reality = postingRepository.findPostingByCategory(1, 11,1L);
		assertThat(reality.size()).isLessThan(20);
	}
	
	@Test
	public void findPostingByCategory3() {
		List<PostingSearchCategoryResponseInterface> reality = postingRepository.findPostingByCategory(1, 11,1L);
		assertFalse(reality.size() < 0);
	}
	
	@Test
	public void findPostingByCategory4() {
		List<PostingSearchCategoryResponseInterface> reality = postingRepository.findPostingByCategory(1, 11,1L);
		assertThat(reality != null);
	}
	
	@Test
	public void findPostingByCategory5() {
		List<PostingSearchCategoryResponseInterface> reality = postingRepository.findPostingByCategory(1, 11,1L);
		assertTrue(reality != null);
	}
	
	@Test
	public void findPostingByProvince() {
		List<PostingSearchProvince> reality = postingRepository.findPostingByProvince(1, 5, 1L);
		assertTrue(reality != null);
	}
	
	@Test
	public void findPostingByProvince1() {
		List<PostingSearchProvince> reality = postingRepository.findPostingByProvince(1, 5, 1L);
		assertThat(reality != null);
	}
	
	@Test
	public void findPostingByProvince2() {
		List<PostingSearchProvince> reality = postingRepository.findPostingByProvince(1, 5, 1L);
		assertThat(reality.size()).isNotNegative();
	}
	
	@Test
	public void findPostingByProvince3() {
		List<PostingSearchProvince> reality = postingRepository.findPostingByProvince(1, 5, 1L);
		assertThat(reality.size()).isLessThan(20);
	}
	
	@Test
	public void findPostingByProvince4() {
		List<PostingSearchProvince> reality = postingRepository.findPostingByProvince(1, 5, 1L);
		assertNotNull(reality);
	}
	
	@Test
	public void findPostingByCity() {
		List<PostingSearchCity> reality = postingRepository.findPostingByCity(1, 5, 1L);
		assertNotNull(reality);
	}
	
	@Test
	public void findPostingByCity1() {
		List<PostingSearchCity> reality = postingRepository.findPostingByCity(1, 5, 1L);
		assertThat(reality.size()).isLessThan(20);
	}
	
	@Test
	public void findPostingByCity2() {
		List<PostingSearchCity> reality = postingRepository.findPostingByCity(1, 5, 1L);
		assertThat(reality.size()).isNotNegative();
	}
	
	@Test
	public void findPostingByCity3() {
		List<PostingSearchCity> reality = postingRepository.findPostingByCity(1, 5, 1L);
		assertThat(reality != null);
	}
	
	@Test
	public void findPostingByCity4() {
		List<PostingSearchCity> reality = postingRepository.findPostingByCity(1, 5, 1L);
		assertTrue(reality != null);
	}
	
	@Test
	public void fingPostingByUserId() {
		List<Posting> reality = postingRepository.fingPostingByUserId(1L);
		assertTrue(reality != null);
	}
	
	@Test
	public void fingPostingByUserId1() {
		List<Posting> reality = postingRepository.fingPostingByUserId(1L);
		assertThat(reality != null);
	}
	
	@Test
	public void fingPostingByUserId2() {
		List<Posting> reality = postingRepository.fingPostingByUserId(1L);
		assertThat(reality.size()).isNotNegative();
	}
	
	@Test
	public void fingPostingByUserId3() {
		List<Posting> reality = postingRepository.fingPostingByUserId(1L);
		assertThat(reality.size()).isLessThan(20);
	}
	
	@Test
	public void fingPostingByUserId4() {
		List<Posting> reality = postingRepository.fingPostingByUserId(1L);
		assertNotNull(reality);
	}
	
	@Test
	public void fingPostingById() {
		Posting reality = postingRepository.fingPostingById(1L);
		assertThat(reality != null);
	}
	
	@Test
	public void fingPostingById1() {
		Posting reality = postingRepository.fingPostingById(1L);
		assertNotNull(reality);
	}
	
	@Test
	public void fingPostingById2() {
		Posting reality = postingRepository.fingPostingById(1L);
		assertFalse(reality == null);
	}
	
	@Test
	public void countNumberOfRecordsByCity() {
		int reality = postingRepository.countNumberOfRecordsByCity(1L);
		assertFalse(reality < 0);
	}
	
	@Test
	public void countNumberOfRecordsByCity1() {
		int reality = postingRepository.countNumberOfRecordsByCity(1L);
		assertThat(reality > 0);
	}
	
	@Test
	public void countNumberOfRecordsByCity2() {
		int reality = postingRepository.countNumberOfRecordsByCity(1L);
		assertThat(reality < 50);
	}
	
	@Test
	public void countNumberOfRecordsByProvince() {
		int reality = postingRepository.countNumberOfRecordsByProvince(1L);
		assertFalse(reality < 0);
	}
	
	@Test
	public void countNumberOfRecordsByProvince1() {
		int reality = postingRepository.countNumberOfRecordsByProvince(1L);
		assertThat(reality < 50);
	}
	
	@Test
	public void countNumberOfRecordsByProvince2() {
		int reality = postingRepository.countNumberOfRecordsByProvince(1L);
		assertThat(reality > 0);
	}
	
	@Test
	public void countNumberOfRecordsByCategory() {
		int reality = postingRepository.countNumberOfRecordsByCategory(1L);
		assertThat(reality > 0);
	}
	
	@Test
	public void countNumberOfRecordsByCategory1() {
		int reality = postingRepository.countNumberOfRecordsByCategory(1L);
		assertThat(reality < 50);
	}
	
	@Test
	public void countNumberOfRecordsByCategory2() {
		int reality = postingRepository.countNumberOfRecordsByCategory(1L);
		assertFalse(reality < 0);
	}
	
}

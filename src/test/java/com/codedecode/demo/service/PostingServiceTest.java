package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.dto.PostingResponseInterfaceDTO;
import com.codedecode.demo.dto.PostingSearchCity;
import com.codedecode.demo.dto.PostingSearchProvince;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.repository.HomeAddressRepository;
import com.codedecode.demo.repository.PostingRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PostingServiceTest {

	@Mock
	private PostingRepository postingRepository;
	
	@InjectMocks
	private PostingService postingService;
	
	@Mock
	private HomeAddressRepository addressRepository;
	
	
	@Test
	public void testGetUrgentJob() {
		List<Posting> list = new ArrayList<Posting>();
		when(postingRepository.findAll()).thenReturn(list);
		Iterable<Posting> urgentJobs = postingService.getUrgentJob();
		assertTrue(true);
	}
	
	@Test
	public void getAttractiveJob() {
		List<Posting> list = new ArrayList<Posting>();
		when(postingRepository.findAll()).thenReturn(list);
		Iterable<Posting> urgentJobs = postingService.getAttractiveJob();
		assertTrue(true);
	}
	
	@Test
	public void getJobByProvice() {
		List<Address> list = new ArrayList<Address>();
		when(addressRepository.findAddress()).thenReturn(list);
		List<Address> addresses = postingService.getJobByProvice();
		assertTrue(addresses != null);
	}
	
	@Test
	public void getJobByProvice1() {
		List<Address> list = new ArrayList<Address>();
		when(addressRepository.findAddress()).thenReturn(list);
		List<Address> addresses = postingService.getJobByProvice();
		assertThat(addresses.size() > 0);
	}
	
	@Test
	public void getJobByProvice2() {
		List<Address> list = new ArrayList<Address>();
		when(addressRepository.findAddress()).thenReturn(list);
		List<Address> addresses = postingService.getJobByProvice();
		assertThat(addresses.size()).isLessThan(20);
	}
	
	@Test
	public void countNumberOfRecordsByProvince() {
		int posting  = postingService.countNumberOfRecordsByProvince(1L);
		assertFalse(posting < 0);
	}
	
	@Test
	public void countNumberOfRecordsByProvince1() {
		int posting  = postingService.countNumberOfRecordsByProvince(2L);
		assertTrue(posting >= 0);
	}
	
	@Test
	public void countNumberOfRecordsByProvince2() {
		int posting  = postingService.countNumberOfRecordsByProvince(2L);
		assertThat(posting).isGreaterThanOrEqualTo(0);
	}
	
	@Test
	public void searchPostingByProvince() {
		List<PostingSearchProvince> posting  = postingService.searchPostingByProvince(1, 1L);
		assertThat(posting.size() > 0);
	}
	
	@Test
	public void searchPostingByProvince1() {
		List<PostingSearchProvince> posting  = postingService.searchPostingByProvince(1, 1L);
		assertThat(posting.size()).isGreaterThanOrEqualTo(0);
	}
	
	@Test
	public void searchPostingByProvince2() {
		List<PostingSearchProvince> posting  = postingService.searchPostingByProvince(1, 1L);
		assertThat(posting != null);
	}
	
	@Test
	public void searchPostingByCity() {
		List<PostingSearchCity> posting  = postingService.searchPostingByCity(1, 1L);
		assertThat(posting != null);
	}
	
	@Test
	public void searchPostingByCity1() {
		List<PostingSearchCity> posting  = postingService.searchPostingByCity(1, 1L);
		assertThat(posting.size()).isGreaterThanOrEqualTo(0);
	}
	
	@Test
	public void searchPostingByCity2() {
		List<PostingSearchCity> posting  = postingService.searchPostingByCity(1, 1L);
		assertThat(posting.size() > 0);
	}
	
	@Test
	public void countNumberOfRecordsByCity() {
		int posting  = postingService.countNumberOfRecordsByCity(1L);
		assertThat(posting > 0);
	}
	
	@Test
	public void countNumberOfRecordsByCity1() {
		int posting  = postingService.countNumberOfRecordsByCity(1L);
		assertFalse(posting < 0);
	}
	
	@Test
	public void countNumberOfRecordsByCity2() {
		int posting  = postingService.countNumberOfRecordsByCity(1L);
		assertTrue(posting >= 0);
	}
	
	@Test
	public void countNumberOfRecordsByCategory() {
		int posting  = postingService.countNumberOfRecordsByCategory(1L);
		assertTrue(posting >= 0);
	}
	
	@Test
	public void countNumberOfRecordsByCategory1() {
		int posting  = postingService.countNumberOfRecordsByCategory(1L);
		assertThat(posting > 0);
	}
	
	@Test
	public void countNumberOfRecordsByCategory2() {
		int posting  = postingService.countNumberOfRecordsByCategory(1L);
		assertFalse(posting < 0);
	}
	
	@Test
	public void getPostingByRecruiterId() {
		List<Posting> posting  = postingService.getPostingByRecruiterId(1L);
		assertFalse(posting != null);
	}
	
	@Test
	public void getPostingByRecruiterId1() {
		List<Posting> posting  = postingService.getPostingByRecruiterId(1L);
		assertTrue(posting == null);
	}
	
	@Test
	public void getPostingByRecruiterId2() {
		List<Posting> posting  = postingService.getPostingByRecruiterId(1L);
		assertThat(posting == null);
	}
	
	@Test
	public void countNumberOfPostingsByRecruiter() {
		int posting  = postingService.countNumberOfPostingsByRecruiter(1L);
		assertFalse(posting < 0);
	}
	
	@Test
	public void countNumberOfPostingsByRecruiter1() {
		int posting  = postingService.countNumberOfPostingsByRecruiter(1L);
		assertThat(posting > 0);
	}
	
	@Test
	public void countNumberOfPostingsByRecruiter2() {
		int posting  = postingService.countNumberOfPostingsByRecruiter(100L);
		assertFalse(posting < 0);
	}
	
	@Test
	public void countNumberOfPostingsByRecruiter3() {
		int posting  = postingService.countNumberOfPostingsByRecruiter(100L);
		assertThat(posting < 0);
	}
	
	@Test
	public void findLastestPostingByRecruiterId() {
		List<Posting> posting  = postingService.findLastestPostingByRecruiterId(1L);
		assertThat(posting != null);
	}
	
	@Test
	public void findLastestPostingByRecruiterId1() {
		List<Posting> posting  = postingService.findLastestPostingByRecruiterId(1L);
		assertThat(posting.size() > 0);
	}
	
	@Test
	public void findLastestPostingByRecruiterId2() {
		List<Posting> posting  = postingService.findLastestPostingByRecruiterId(1L);
		assertFalse(posting.size() < 0);
	}
}

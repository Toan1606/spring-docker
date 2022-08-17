package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Posting;

@SpringBootTest
public class HomePostingRepositoryTest {

	@Autowired
	private HomePostingRepository homePostingRepository;
	
	@Test
	public void testFindPosting() {
		List<Posting> reality = homePostingRepository.findAll();
		System.out.println("hello: " +reality);
		assertThat(reality.size()).isGreaterThan(1);	
	}
	
	@Test
	public void testFindPosting1() {
		List<Posting> reality = homePostingRepository.findAll();
		System.out.println("hello: " +reality);
		assertThat(reality.size()).isBetween(15, 18);
	}
	
	@Test
	public void testFindPosting2() {
		List<Posting> reality = homePostingRepository.findAll();
		System.out.println("hello: " +reality);
		assertThat(reality.size()).isLessThan(1);	
	}
	
}

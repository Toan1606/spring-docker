package com.codedecode.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.codedecode.demo.entity.Posting;

public interface PostingRepository extends PagingAndSortingRepository<Posting, Long>{

}

package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codedecode.demo.entity.Rank;

public interface RankRepository extends JpaRepository<Rank, Long> {

}

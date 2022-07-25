package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Rank;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

}

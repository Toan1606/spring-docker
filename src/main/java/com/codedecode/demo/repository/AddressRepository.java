package com.codedecode.demo.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

//	@Query(value = "SELECT a.id, a.name FROM address a where a.province_id = :provinceId and a.city_id = :cityId", nativeQuery = true)
//	Optional<Address> findByProvinceAndCity(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
	
	@Query(value = "select * from address a where a.province_id = :provinceId and a.city_id = :cityId", nativeQuery = true)
	Optional<Address> findAddressByProvinceAndCity(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

	@Query(value = "select * from posting p join posting_address pa on p.id = pa.posting_id join address a on a.id = pa.address_id where p.id = :postingId", nativeQuery = true)
	Set<Address> findByPostingId(@Param("postingId") Long postingId);
}

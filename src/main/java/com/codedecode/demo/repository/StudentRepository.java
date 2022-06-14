package com.codedecode.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.one_to_many.Student;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long>{

	/*
	 * List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String
	 * lastname);
	 * 
	 * 
	 * // Enables the distinct flag for the query List<Person>
	 * findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
	 * List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String
	 * firstname);
	 * 
	 * // Enabling ignoring case for an individual property List<Person>
	 * findByLastnameIgnoreCase(String lastname); // Enabling ignoring case for all
	 * suitable properties List<Person>
	 * findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);
	 * 
	 * // Enabling static ORDER BY for a query List<Person>
	 * findByLastnameOrderByFirstnameAsc(String lastname); List<Person>
	 * findByLastnameOrderByFirstnameDesc(String lastname);
	 * 
	 * // a Person has an Address with a ZipCode
	 * List<Person> findByAddressZipCode(ZipCode zipCode);
	 * List<Person> findByAddress_ZipCode(ZipCode zipCode);
	 * 
	 * 
	 * // limit number of results
	 * Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);
	 * Slice<User> findTop3ByLastname(String lastname, Pageable pageable);
	 * List<User> findFirst10ByLastname(String lastname, Sort sort);
	 * List<User> findTop10ByLastname(String lastname, Pageable pageable);
	 * 
	 * Streamable<Person> findByFirstnameContaining(String firstname);
	 * Streamable<Person> findByLastnameContaining(String lastname);
	 * 
	 * @Nullable
	 * User findByEmailAddress(@Nullable EmailAddress emailAdress);
	 * Optional<User> findOptionalByEmailAddress(EmailAddress emailAddress);
	 */
}

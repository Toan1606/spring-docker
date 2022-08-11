package com.codedecode.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.dto.PostingRelatedDTO;
import com.codedecode.demo.dto.PostingResponseInterfaceDTO;
import com.codedecode.demo.dto.PostingSearchCategoryResponseInterface;
import com.codedecode.demo.dto.PostingSearchCity;
import com.codedecode.demo.dto.PostingSearchProvince;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Posting;

@Repository
public interface PostingRepository extends SearchRepository<Posting, Long> {

	@Query(value = "select * from posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id \r\n"
			+ "join year_of_experience on posting.year_of_experience_id = year_of_experience.id", nativeQuery = true)
	List<Posting> getAllJob();

	@Query(value = "select posting.id, commission, deadline_for_submission, images, job_name, address.name as address, salary.name as salary "
			+ "from posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id", nativeQuery = true)
	List<Posting> findPosting();
	
	@Query(value = "select p.benefits as benefits, p.commission as commission, p.deadline_for_submission as deadlineForSubmission\r\n"
			+ "	, p.degree_required as degreeRequired, p.descriptions as description, p.email_contact as emailContact, p.files as files\r\n"
			+ "	, p.gender_requirement as genderRequirement, p.job_requirement as jobRequirement, p.images as image, p.job_name as jobName\r\n"
			+ "	, p.phone_number as phoneNumber, p.position as position, p.profile_included as profileIncluded, p.quantity as quantity\r\n"
			+ "	, p.quantity_needed as quantityNeeded, p.views as views, p.rank_id as rankId, p.salary_id as salaryId\r\n"
			+ "	, p.working_form_id as workingFormId, u.id as CompanyId, u.name as companyName\r\n"
			+ "	, p.posting_category_id as postingCategoryId, pc.category_name as postingCategoryName\r\n"
			+ "	from posting p join users u on p.user_id = u.id and p.id = :postingId and u.id = :userId\r\n"
			+ "	join posting_category pc on pc.id = p.posting_category_id;", nativeQuery = true)
	PostingResponseInterfaceDTO findPostingByUserIdAndPostingId(@Param("userId") Long userId, @Param("postingId") Long postingId);

	@Query(value = "select TOP 10 p.benefits as benefits, p.images as images, p.position as position, p.id as postingId\r\n"
			+ "	, p.job_name as jobName, p.deadline_for_submission as deadlineForSubmission, u.id as CompanyId, u.name as companyName \r\n"
			+ "	, s.name as salary, pr.name as province\r\n"
			+ "	from posting p join users u on p.user_id = u.id join salary s on s.id = p.salary_id\r\n"
			+ "		join posting_address pa on pa.posting_id = p.id\r\n"
			+ "		join address a on a.id = pa.address_id\r\n"
			+ "		join province pr on pr.id = a.province_id\r\n"
			+ "	where p.posting_category_id = :posting_category_id", nativeQuery = true)
	List<PostingRelatedDTO> findTop10RelatedPosting(@Param("posting_category_id") Long posting_category_id);

	@Query(value = "SELECT * FROM (\r\n"
			+ "    select ROW_NUMBER() OVER (ORDER BY p.id) as rowNumber, p.images as images, p.position as position, p.id as postingId\r\n"
			+ ", p.job_name as jobName, p.deadline_for_submission as deadlineForSubmission, u.id as CompanyId, u.name as companyName \r\n"
			+ ", s.name as salary, pr.name as province, p.posting_category_id as postingCategoryId\r\n"
			+ "from posting p join users u on p.user_id = u.id join salary s on s.id = p.salary_id\r\n"
			+ "	join posting_address pa on pa.posting_id = p.id\r\n"
			+ "	join address a on a.id = pa.address_id\r\n"
			+ "	join province pr on pr.id = a.province_id) t "
			+ "WHERE t.rowNumber BETWEEN :start and :end and t.postingCategoryId = :posting_category_id", nativeQuery = true)
	List<PostingSearchCategoryResponseInterface> findPostingByCategory(@Param("start") int start, @Param("end") int end, @Param("posting_category_id") Long postingCategoryId);

	@Query(value = "SELECT * FROM (\r\n"
			+ "    select ROW_NUMBER() OVER (ORDER BY p.id) as rowNumber, p.images as images, p.position as position, p.id as postingId\r\n"
			+ ", p.job_name as jobName, p.deadline_for_submission as deadlineForSubmission, u.id as CompanyId, u.name as companyName \r\n"
			+ ", s.name as salary, pr.name as provinceName, pr.id as provinceId\r\n"
			+ "from posting p join users u on p.user_id = u.id join salary s on s.id = p.salary_id\r\n"
			+ "	join posting_address pa on pa.posting_id = p.id\r\n"
			+ "	join address a on a.id = pa.address_id\r\n"
			+ "	join province pr on pr.id = a.province_id) t WHERE t.rowNumber BETWEEN :start and :end and t.provinceId = :province_id", nativeQuery = true)
	List<PostingSearchProvince> findPostingByProvince(@Param("start") int start, @Param("end") int end, @Param("province_id") Long provinceId);

	
	@Query(value = "SELECT * FROM (\r\n"
			+ "    select ROW_NUMBER() OVER (ORDER BY p.id) as rowNumber, p.images as images, p.position as position, p.id as postingId\r\n"
			+ ", p.job_name as jobName, p.deadline_for_submission as deadlineForSubmission, u.id as CompanyId, u.name as companyName \r\n"
			+ ", s.name as salary, c.name as cityName, c.id as cityId\r\n"
			+ "from posting p join users u on p.user_id = u.id join salary s on s.id = p.salary_id\r\n"
			+ "	join posting_address pa on pa.posting_id = p.id\r\n"
			+ "	join address a on a.id = pa.address_id\r\n"
			+ "	join city c on c.id = a.city_id ) t "
			+ "WHERE t.rowNumber BETWEEN :start and :end and t.cityId = :city_id", nativeQuery = true)
	List<PostingSearchCity> findPostingByCity(@Param("start") int start, @Param("end") int end, @Param("city_id") Long cityId);

	@Query(value = "select * from posting where posting.user_id = ?1", nativeQuery=true)
	List<Posting> fingPostingByUserId(long userId);
	
	@Query(value = "select * from posting where posting.id = ?1", nativeQuery=true)
	Posting fingPostingById(long id);
	
	@Query(value = "delete from posting where id = ?1", nativeQuery=true)
	@Modifying
	void deletePostingById(long id);
	
	Set<Posting> findByAddresss(Address addresss);

	@Query(value = "select count(*) as numberOfRecords from posting p join posting_address pa on pa.posting_id = p.id\r\n"
			+ "join address a on a.id = pa.address_id\r\n"
			+ "where a.province_id = :province_id", nativeQuery = true)
	int countNumberOfRecordsByCity(@Param("province_id") Long provinceId);
	
	@Query(value = "select count(*) as numberOfRecords from posting p join posting_address pa on pa.posting_id = p.id\r\n"
			+ "join address a on a.id = pa.address_id\r\n"
			+ "where a.province_id = :province_id", nativeQuery = true)
	int countNumberOfRecordsByProvince(@Param("province_id") Long provinceId);
	
	@Query(value = "select count(*) from posting p where p.posting_category_id = :category_id", nativeQuery = true)
	int countNumberOfRecordsByCategory(@Param("category_id") Long categoryId);
	
	int countByUser_Id(Long id);
	
	List<Posting> findByUser_IdOrderByIdDesc(Long id);
	
	
}

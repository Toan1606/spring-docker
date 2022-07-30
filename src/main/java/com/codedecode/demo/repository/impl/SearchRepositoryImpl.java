package com.codedecode.demo.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.repository.SearchRepository;

@Transactional
public class SearchRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements SearchRepository<T, ID> {

	@PersistenceContext
	private EntityManager entityManager;

	public SearchRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	public SearchRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public List<T> searchBy(String text, int limit, String... fields) {

		SearchResult<T> result = getSearchResult(text, limit, 0, fields);

		return result.hits();
	}

	@Override
	public PageDTO<T> searchPageBy(String text, int limit, int offset, String... fields) {
		SearchResult<T> result = getSearchResult(text, limit, offset, fields);

		return new PageDTO<T>(result.hits(), result.total().hitCount());
	}

	private SearchResult<T> getSearchResult(String text, int limit, int offset, String[] fields) {
		SearchSession searchSession = Search.session(entityManager);

		SearchResult<T> result = searchSession.search(getDomainClass())
				.where(f -> f.match().fields(fields).matching(text).fuzzy(2)).fetch(offset, limit);
		return result;
	}
}
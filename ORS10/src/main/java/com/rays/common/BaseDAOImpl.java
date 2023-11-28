package com.rays.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {
	
	@PersistenceContext
	public EntityManager entityManager;

}

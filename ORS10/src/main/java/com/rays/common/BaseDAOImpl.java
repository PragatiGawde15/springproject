package com.rays.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	@PersistenceContext
	public EntityManager entityManager;

	public abstract Class<T> getDTOClass();

	public abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder builder, Root<T> qRoot);

	public void populate(T dto) {

	}

	public long add(T dto) {
		populate(dto);
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(T dto) {
		entityManager.merge(dto);

	}

	public void delete(long id) {
		T dto = findByPk(id);
		entityManager.remove(dto);
	}

	public T findByPk(long pk) {
		T dto = entityManager.find(getDTOClass(), pk);
		return dto;
	}

	@Override
	public T findByUniqueKey(String property, String value) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery cq = builder.createQuery(getDTOClass());

		Root qRoot = cq.from(getDTOClass());

		Predicate condition = builder.equal(qRoot.get(property), value);

		cq.where(condition);

		TypedQuery tq = entityManager.createQuery(cq);

		List list = tq.getResultList();

		T dto = null;

		if (list.size() > 0) {

			dto = (T) list.get(0);

		}
		return dto;
	}

	public TypedQuery<T> createCriteria(T dto) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());
		Root<T> qRoot = cq.from(getDTOClass());
		List<Predicate> whereCondition = getWhereClause(dto, builder, qRoot);
		cq.where(whereCondition.toArray(new Predicate[whereCondition.size()]));
		TypedQuery<T> tq = entityManager.createQuery(cq);
		return tq;
	}

	public List search(T dto, int pageNo, int pageSize) {

		TypedQuery<T> tq = createCriteria(dto);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		List<T> list = tq.getResultList();
		list.forEach(e -> System.out.println("***" + e));
		return list;
	}

	protected boolean isEmptyString(String val) {
		return val == null || val.trim().length() == 0;
	}

	protected boolean isZeroNumber(Long val) {
		return val == null || val == 0;
	}
}

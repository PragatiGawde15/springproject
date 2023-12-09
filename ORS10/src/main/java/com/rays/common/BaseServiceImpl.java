package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	@Autowired
	public D baseDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(T dto) {
		long pk = baseDao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto) {
		baseDao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(T dto) {
		Long pk = dto.getId();
		if (pk != null && pk > 0) {
			update(dto);
		} else {
			pk = add(dto);
		}
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		baseDao.delete(id);
	}

	@Transactional(readOnly = true)
	public T findByPk(long id) {
		T dto = baseDao.findByPk(id);
		return dto;
	}

	@Transactional(readOnly = true)
	public T findByLogin(String loginId) {
		return baseDao.findByUniqueKey("loginId", loginId);
	}

	@Transactional(readOnly = true)
	public List search(T dto, int pageNo, int pageSize) {
		return baseDao.search(dto, pageNo, pageSize);
	}

}

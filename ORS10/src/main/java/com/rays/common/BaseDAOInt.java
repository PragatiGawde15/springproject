package com.rays.common;

import java.util.List;

public interface BaseDAOInt<T extends BaseDTO> {

	public long add(T dto);

	public void update(T dto);

	public void delete(long id);

	public T findByPk(long id);

	public T findByUniqueKey(String property, String value);
	
	public List search(T dto, int pageNo, int pageSize);

}

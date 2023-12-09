package com.rays.common;

import java.util.List;

public interface BaseServiceInt<T extends BaseDTO> {

	public long add(T dto);

	public void update(T dto);

	public void delete(long id);

	public long save(T dto);

	public T findByPk(long id);

	public T findByLogin(String loginId);

	public List search(T dto, int pageNo, int pageSize);

}

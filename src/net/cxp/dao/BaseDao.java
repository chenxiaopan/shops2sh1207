package net.cxp.dao;

import java.util.List;

public interface BaseDao<T> {

	public void save(T t);
	
	public void update(T t);
	
	public void delete(Integer id);
	
	public T get(Integer t);
	
	public List<T> list();
	
}

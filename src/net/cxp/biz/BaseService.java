package net.cxp.biz;

import java.util.List;

public interface BaseService<T> {
	
	public void save(T t);
	
	public void update(T t);
	
	public void delete(Integer id);
	
	public T get(Integer t);
	
	public List<T> list();
	
	

}

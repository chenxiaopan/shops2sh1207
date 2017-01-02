package net.cxp.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cxp.biz.BaseService;
import net.cxp.dao.BaseDao;

public class BaseServiceImpl<T> implements BaseService<T> {
    
	@Autowired//泛型式依赖注入，子类只需继承它并提供泛型参数就可以注入相应的dao
	protected BaseDao<T> dao;

	@Override
	@Transactional
	public void save(T t) {
		dao.save(t);
	}

	@Override
	@Transactional
	public void update(T t) {
		dao.update(t);

	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T get(Integer t) {
		return dao.get(t);
	}

	@Override
	@Transactional
	public List<T> list() {
		return dao.list();
	}

}

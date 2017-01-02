package net.cxp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.cxp.dao.CategoryDao;
import net.cxp.entity.Category;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements
		CategoryDao {

	@Override
	@Transactional
	public List<Category> queryByHot(Short hot) {
		String hql = "from Category c where c.hot=:hot";
		return getSession().createQuery(hql).setShort("hot", hot).list();
	}

	@Override
	@Transactional
	public List<Category> queryJoinAccount(String type, int page, int rows) {
		String hql = "from Category c left join fetch  c.account where  c.type like :type";
		return getSession().createQuery(hql)
				.setString("type", "%" + type + "%")
				.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	@Transactional
	public Long getCount(String type) {
		String hql = "select count(1) from Category c where c.type like :type";
		//返回一条记录:总记录数
		return (Long) getSession().createQuery(hql)
				.setString("type", "%" + type + "%").uniqueResult();
	}
}

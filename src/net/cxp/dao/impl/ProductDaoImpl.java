package net.cxp.dao.impl;

import java.util.List;

import net.cxp.dao.ProductDao;
import net.cxp.entity.Product;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

	@Override
	public List<Product> querByCategoryId(Integer cid) {

		String hql = "from Product p where p.commend=1 and p.open=1 and p.category.id=:cid order by p.pdate desc";

		return getSession().createQuery(hql).setInteger("cid", cid)
				.setFirstResult(0).setMaxResults(4).setCacheable(true).list();
	}

	@Override
	public List<Product> queryJoinCategory(String name, Integer page,
			Integer rows) {

		String hql = "from Product p where name like ?";

		return getSession().createQuery(hql).setParameter(0, "%" + name + "%")
				.setFirstResult((page - 1) * rows).setMaxResults(rows).setCacheable(true).list();
	}

	@Override
	public Long getCount(String name) {
		String hql = "select count(1) from Product p where p.name like ? ";
		return (Long) getSession().createQuery(hql)
				.setParameter(0, "%" + name + "%").uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Product p where p.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();

	}

}

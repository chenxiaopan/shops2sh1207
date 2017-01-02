package net.cxp.biz.impl;

import java.util.List;

import net.cxp.biz.ProductService;
import net.cxp.dao.ProductDao;
import net.cxp.dao.impl.BaseDaoImpl;
import net.cxp.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements
		ProductService {

	/*
	 * private ProductDao dao;
	 * 
	 * // 构造注入
	 * 
	 * @Autowired public ProductServiceImpl(ProductDao dao) { this.dao = dao;
	 * System.out.println("ProductDao------>" + dao); }
	 */

	@Override
	@Transactional
	public List<Product> queryByCategoryId(Integer id) {
		return ((ProductDao) dao).querByCategoryId(id);
	}

	@Override
	@Transactional
	public List<Product> queryJoinCategory(String name, Integer page,
			Integer rows) {
		return ((ProductDao) dao).queryJoinCategory(name, page, rows);
	}

	@Override
	public Long getCount(String name) {
		return ((ProductDao) dao).getCount(name);
	}

	@Override
	@Transactional
	public void deleteByIds(String ids) {
		((ProductDao) dao).deleteByIds(ids);

	}

}

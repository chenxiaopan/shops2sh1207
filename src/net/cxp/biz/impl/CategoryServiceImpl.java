package net.cxp.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cxp.biz.CategoryService;
import net.cxp.dao.CategoryDao;
import net.cxp.entity.Category;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category>  implements CategoryService {

/*	private CategoryDao dao;

	// 构造注入
	@Autowired
	public CategoryServiceImpl(CategoryDao dao) {
		this.dao = dao;
		System.out.println("CategoryDao------>" + dao);
	}*/


	@Override
	public List<Category> queryByHot(Short hot) {
		return ((CategoryDao) dao).queryByHot(hot);
	}

	@Override
	public List<Category> queryJoinAccount(String type, int page, int rows) {
		return ((CategoryDao) dao).queryJoinAccount(type, page, rows);
	}

	@Override
	public Long getCount(String type) {
		return ((CategoryDao) dao).getCount(type);
	}



}

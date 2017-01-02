package net.cxp.dao;

import java.util.List;

import net.cxp.entity.Product;

public interface ProductDao extends BaseDao<Product> {

	//根据热点类别查询推荐商品（仅仅查询前4个）
	public List<Product> querByCategoryId(Integer cid);

	public List<Product> queryJoinCategory(String name, Integer page,
			Integer rows);

	public Long getCount(String name);
   //根据ids删除多条记录
	public void deleteByIds(String ids);
	
}

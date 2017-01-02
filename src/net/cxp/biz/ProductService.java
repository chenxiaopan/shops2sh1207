package net.cxp.biz;

import java.util.List;

import net.cxp.entity.Product;
import net.cxp.entity.Users;

public interface ProductService extends BaseService<Product> {

	
	public List<Product> queryByCategoryId(Integer id);

	public List<Product> queryJoinCategory(String name, Integer page,
			Integer rows);

	public Long getCount(String name);
   //根据ids删除多行记录
	public void deleteByIds(String ids);

}

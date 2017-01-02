package net.cxp.biz;

import java.util.List;

import net.cxp.entity.Category;

public interface CategoryService extends BaseService<Category> {

	//根据hot值查询热点或非热点类别
	public List<Category> queryByHot(Short hot);
	//查询类别信息，级联管理员
	public List<Category> queryJoinAccount(String type,int page,int rows);
	//根据关键字查询总记录数
	public Long getCount(String type);
	

}

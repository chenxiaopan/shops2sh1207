package net.cxp.dao;

import java.util.List;

import net.cxp.entity.Forder;
import net.cxp.entity.Product;
import net.cxp.entity.Sorder;

public interface SorderDao extends BaseDao<Sorder> {

	
	Forder addSorder(Forder forder, Product product,Integer numbers);
	
	//pid是购物项所包含商品的id，因为订单没有结算购物项和购物车都没有持久化，所以没有id
	Forder deleteSorder(Forder forder,int pid);
	//查询热点商品的销售量
	List<Object[]> querySale(Integer numbers);
	
}

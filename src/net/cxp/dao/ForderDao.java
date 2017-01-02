package net.cxp.dao;

import net.cxp.entity.Forder;

public interface ForderDao extends BaseDao<Forder> {

	
	Double payTotal(Forder forder);
	
	
	
}

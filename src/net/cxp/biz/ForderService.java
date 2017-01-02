package net.cxp.biz;

import net.cxp.entity.Forder;

public interface ForderService extends BaseService<Forder> {

	
	Double payTotal(Forder forder);

}

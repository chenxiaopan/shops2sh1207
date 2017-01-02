package net.cxp.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;

import net.cxp.dao.ForderDao;
import net.cxp.entity.Forder;
import net.cxp.entity.Sorder;

@Repository
public class ForderDaoImpl extends BaseDaoImpl<Forder> implements ForderDao {
	
	
	

	@Override
	public Double payTotal(Forder forder) {
		// 用户需要支付的总价
		Double payTotal = new Double(0.00);
		Set<Sorder> sorders = forder.getSorders();
		// 累加每一项的价钱
		for (Sorder sorder : sorders) {
			payTotal += sorder.getPrice() * sorder.getNumbers();
		}

		return payTotal;
	}


}

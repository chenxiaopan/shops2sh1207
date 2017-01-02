package net.cxp.biz.impl;

import net.cxp.biz.ForderService;
import net.cxp.dao.ForderDao;
import net.cxp.entity.Forder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("forderService")
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements ForderService {

/*	private ForderDao dao;

	@Autowired
	public ForderServiceImpl(ForderDao dao) {
		super();
		this.dao = dao;
	}*/


	@Override
	public Double payTotal(Forder forder) {
		return ((ForderDao) dao).payTotal(forder);
	}


}

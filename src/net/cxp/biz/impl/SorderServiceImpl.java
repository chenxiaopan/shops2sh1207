package net.cxp.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.cxp.biz.SorderService;
import net.cxp.dao.SorderDao;
import net.cxp.entity.Forder;
import net.cxp.entity.Product;
import net.cxp.entity.Sorder;

@Service("sorderService")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements SorderService {

/*	private SorderDao dao;
	
	@Autowired
	public SorderServiceImpl(SorderDao dao) {
		super();
		this.dao = dao;
	}*/


	@Override
	public Forder addSorder(Forder forder, Product product,Integer numbers) {
	return ((SorderDao) dao).addSorder(forder, product,numbers);
	}

	@Override
	public Forder deleteSorder(Forder forder,int pid) {
		return ((SorderDao) dao).deleteSorder(forder,pid);
	}

	@Override
	@Transactional//spring事务要放在service层，放在dao层无法在提交失败时进行正确的回滚（因为可能在一个service中调用多个dao层的方法）
	public List<Object[]> querySale(Integer numbers) {
		return ((SorderDao) dao).querySale(numbers);
	}

}

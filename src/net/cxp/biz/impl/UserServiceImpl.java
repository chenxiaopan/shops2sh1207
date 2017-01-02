package net.cxp.biz.impl;

import net.cxp.biz.UserService;
import net.cxp.dao.UserDao;
import net.cxp.entity.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<Users>  implements UserService {

/*	private UserDao dao;

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	public UserDao getDao() {
		return dao;
	}

	// 构造时注入UserDao
	@Autowired
	public UserServiceImpl(UserDao dao) {
		super();
		this.dao = dao;
		System.out.println("UserDao------>" + dao);
	}*/

	@Override
	public Users authenticateUser(String username, String password) {
		return ((UserDao) dao).authenticateUser(username, password);
	}



}

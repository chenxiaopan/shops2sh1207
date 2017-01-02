package net.cxp.dao.impl;

import net.cxp.dao.UserDao;
import net.cxp.entity.Users;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl extends BaseDaoImpl<Users> implements UserDao {

	public UserDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 用户登录验证
	 * 如果用户名和密码是有效的返回相应的用户对象，否则返回null
	 */
	@Transactional
	public Users authenticateUser(String username, String password) {

		System.out.println("Authenticating: username = " + username);

		Users validUser = null;

		// 1.获得会话
		Session session = this.getSession();

		// 2.hql语句
		String hql = "from Users where  uname=:username";

		Users user = (Users) session.createQuery(hql)
				.setParameter("username", username).uniqueResult();

		System.out.println(user);

		if (user != null) {

			/* If the username mapped to a real user, check password */
			if (user != null && user.getUpwd().equals(password)) {
				validUser = user;

			}
		}

		return validUser;
	}


}

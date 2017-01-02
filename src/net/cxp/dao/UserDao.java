package net.cxp.dao;

import net.cxp.entity.Users;


public interface UserDao extends BaseDao<Users> {

	Users authenticateUser(String username, String password);


}

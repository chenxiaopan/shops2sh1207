package net.cxp.biz;

import java.util.List;

import net.cxp.entity.Users;

public interface UserService extends BaseService<Users> {


	Users authenticateUser(String username, String password);

}

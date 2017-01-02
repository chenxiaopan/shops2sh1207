package net.cxp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.cxp.biz.UserService;
import net.cxp.entity.Users;
import net.cxp.utils.Struts2Constants;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 登录动作
 * 
 * @author cxp
 * @date 2016-11-14
 * 
 */

@Controller("userAction")
// 配置为原型，即一个请求一个action
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserAction extends ActionSupport implements ModelDriven,
		SessionAware {

	private static final long serialVersionUID = 1L;

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String login() {

		System.out.println("----------->login " + userService);
		// 进行用户验证
		user = userService.authenticateUser(user.getUname(), user.getUpwd());
		if (user == null) {
			return INPUT;
		} else {
			// 登录成功后，将用户存入session中，方便用户身份验证拦截器获取登录信息进行用户验证
			session.put(Struts2Constants.USER, user);
		}
		// 根据session中的goUrl是否有值来进行跳转
		if (session.get("goURL") == null) {
			return SUCCESS;
		} else {
          return "goURL";
		}
	}

	public String logout() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();

		return "input";
	}

	// 通过实现ModelDriven这个接口获得User对象
	private Users user = new Users();

	@Override
	public Users getModel() {
		return user;
	}

	/**
	 * 在action中注入服务对象需要插件struts2-spring-plugin-2.2.1.jar 使用spring注入服务
	 */
	@Autowired
	private UserService userService;

	private Map session;

	// 重写SessionAware接口的方法
	@Override
	public void setSession(Map session) {
		this.session = session;
	}

}

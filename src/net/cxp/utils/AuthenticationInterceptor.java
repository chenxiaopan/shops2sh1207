package net.cxp.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.cxp.entity.Users;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 自定义拦截器，用于进行用户身份验证，只有通过身份验证的用户才能进行敏感操作
 * 继承MethodFilterInterceptor可以过滤掉一些不需要拦截的方法
 */

public class AuthenticationInterceptor extends MethodFilterInterceptor {

	/**
	 * 执行拦截操作
	 */

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();

		purgeStaleTokens(session);

		// 从session中获得用户
		Users user = (Users) session.get(Struts2Constants.USER);

		if (user == null) {
			// 获得原生的request
			HttpServletRequest request = ServletActionContext.getRequest();
			// 保存当前用户想要去的url地址
			String goURL = request.getServletPath();// 获得用户想要去的地址
			String param = request.getQueryString();// 获得地址中携带的参数
			if (param != null) {
				goURL = goURL + "?" + param;// 重新拼好请求地址+参数
			}
			// 把当前用户想要访问的url存在session域
			request.getSession().setAttribute("goURL", goURL);
			// 非法请求，跳转到登录页面
			request.getSession().setAttribute("error", "请登录!");
			return Action.LOGIN;
		}

		else {

			Action action = (Action) invocation.getAction();

			if (action instanceof UserAware) {
				((UserAware) action).setUser(user);
			}

			System.out.println("Logged in: interceptor");
			return invocation.invoke();
		}
	}

	private void purgeStaleTokens(Map session) {

		Object userToken = session.get(Struts2Constants.USER);
		if (!(userToken instanceof Users))
			session.remove(Struts2Constants.USER);

	}

}

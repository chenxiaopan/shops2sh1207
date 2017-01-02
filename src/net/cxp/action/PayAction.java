package net.cxp.action;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Struts处理流程：
 * 1. 获取请求后，先创建Action代理，在创建代理的时候顺便创建了Action，
 * 2. 执行18个拦截器，拦截器执行成功后再调用Action的方法
 * 3. Action的方法执行完毕后，再调用18个拦截器
 * 所以先创建Action-->servletConfig(拿到parameters)-->modelDriven
 * @author cxp
 *
 */

@Controller("payAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PayAction  extends BaseAction<Object> implements ParameterAware {

	private Map<String , String[]> parameters;
	@Override
	public void setParameters(Map<String, String[]> parameters) {
               this.parameters=parameters;		
	}

	 public String goBank(){
		 
		 
		 return "pay";
	 }
	
	
}

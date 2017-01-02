package net.cxp.action;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @Description: TODO(此Action用来完成WEB-INF中JSP与JSP请求转发功能，此Action不处理任何的逻辑) 
 * @author eson_15 
 * 
 */  

@Controller("sendAction")
//配置为原型，即一个请求一个action
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SendAction extends ActionSupport {  
      
    public String execute() {  
        return "send";  
    }  
}  
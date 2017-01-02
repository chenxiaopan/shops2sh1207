package net.cxp.action;

import net.cxp.entity.Forder;
import net.cxp.entity.Status;
import net.cxp.entity.Users;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("forderAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ForderAction extends BaseAction<Forder> {
	
	//重写getModel方法的原因是要从会话中获得购物车，而不能根据反射获得一个空的购物车
	@Override
	public Forder getModel() {
		model = (Forder) session.get("forder");
		return model;
	}

	public String emptyForder() {

		// 会话移除购物车
		session.remove("forder");

		return "emptyForder";
	}
	
	public String save(){
		
		//会话获得购物车
//		Forder forder=(Forder) session.get("forder");
		
		model.setUsers((Users)session.get("user"));
		model.setStatus(new Status(1));
		System.out.println("model.getSorders().size()--->"+model.getSorders().size());
		//保存进数据库
		forderService.save(model);
		
		//此时购物车已经入库，那么原来session中的购物车就应该清空
		session.put("oldForder", session.get("forder"));//先将原来的购物车信息保存下来，因为后面付款的时候还需要相关信息
		session.put("forder", new Forder());//new一个新的空购物车（相当于清空了购物车），还可以方便用户再买~
		return "bank";
	}
	
	

	public String search() {
		
		
		
		

		return "jsonMap";
	}

}

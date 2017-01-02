package net.cxp.action;

import java.util.HashMap;
import java.util.List;

import net.cxp.entity.Category;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("categoryAction")
// 配置为原型，即一个请求一个action
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CategoryAction extends BaseAction<Category> {

	public String queryJoinAccount() {
		System.out.println("type:" + model.getType());
		System.out.println("page:" + page);
		System.out.println("rows:" + rows);

		// 用来存储分页的数据
		pageMap = new HashMap<String, Object>();

		// 根据关键字和分页的参数查询相应的数据(模糊查询：参数为空则查询所有)
		List<Category> categorylist = categoryService.queryJoinAccount(
				model.getType(), page, rows);
		pageMap.put("rows", categorylist);// 存储为json格式
		
		
		// 根据关键字查询总记录数(模糊查询)
		Long total = categoryService.getCount(model.getType());
		pageMap.put("total", total);// 存储为json格式

		return "jsonMap";
	}
	
	
	public String query(){
		
		jsonList=categoryService.list();
		System.out.println("jsonList.size---->"+jsonList.size());
		return "jsonList";
	}
	
	

}

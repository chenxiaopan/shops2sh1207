package net.cxp.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.cxp.biz.CategoryService;
import net.cxp.biz.ProductService;
import net.cxp.entity.Product;
import net.cxp.entity.Category;

/**
 * @Description: TODO(设置任务：run方法中用来加载首页商品信息)
 * @author cxp
 *
 */
@Component
public class ProductTimerTask extends TimerTask {

	//应用程序上下文
	private ServletContext application=null;
	
	public void setApplication(ServletContext application){
		
		this .application=application;
	}
	
	@Resource
	private ProductService productService=null;
	
	@Resource
	private CategoryService categoryService=null; 
	
	
	
	@Override
	//和监听器在项目启动时数据初始化的逻辑一样
	public void run() {
		
		System.out.println("initApplication================>run");
		
		//bigList中存放一个装有Category类的list
		List<List<Product>> bigList=new ArrayList<List<Product>>();
		//1.查询出热点类别
		for (Category category : categoryService.queryByHot(new Short("1"))) {
			//根据热点类别id获取推荐商品信息
			List<Product> list=productService.queryByCategoryId(category.getId());
			//将装有category的list放到bigList中
			bigList.add(list);
			
		}
		
		System.out.println("bigList"+bigList);
		//2.把查询的bigList传给application内置对象
		application.setAttribute("bigList", bigList);
	}

}

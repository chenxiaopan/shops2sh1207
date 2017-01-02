package net.cxp.action;

import java.util.ArrayList;
import java.util.List;

import net.cxp.entity.Forder;
import net.cxp.entity.Product;
import net.cxp.entity.Sorder;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("sorderAction")
@Scope("prototype")
public class SorderAction extends BaseAction<Sorder> {

	public String addSorder() {

		// 1.根据product.id获取相应的商品数据
		// sorder_addSorder.action?product.id=${product.id}，
		// product.id会自动绑定到sorder的product属性下的id属性
		Product product = productService.get(model.getProduct().getId());
		// 获得用户购买的数量，从首页直接点加入购物车时没有numbers，所以要判断一下
		int numbers = model.getNumbers() == null ? 1 : model.getNumbers();

		// 2.判断当前的session是否有购物车，没有则创建一个
		if (session.get("forder") == null) {
			session.put("forder", new Forder());
		}

		// 3. 把商品信息转化为sorder,并且添加到购物车中（判断购物项是否重复）
		Forder forder = (Forder) session.get("forder");

		forder = sorderService.addSorder(forder, product, numbers);

		System.out.println("forder---->" + forder);

		// 4.计算购物的总价格
		forder.setTotal(forderService.payTotal(forder));

		// 5.把新的购物车存储到会话中
		session.put("forder", forder);

		return "showCart";
	}

	public String deleteSorder() {

		// 获得要删除购物项所包含的商品的id号
		int pid = model.getProduct().getId();

		// 获得购物车
		Forder forder = (Forder) session.get("forder");

		Forder forder1 = sorderService.deleteSorder(forder, pid);

		// 把新的购物车存储到会话中
		session.put("forder", forder1);

		return "del";
	}

	public String querySale() {

		System.out.println("querySale------------->" + model.getNumbers());

		List<Object[]> json = sorderService.querySale(model.getNumbers());
             jsonList=new ArrayList();
		for (Object[] object : json) {
			System.out.println("object---------->" + object[0]+object[1]);
			Sorder sorder = new Sorder();
			sorder.setName((String) object[0]);
			sorder.setNumbers(Integer.parseInt(object[1].toString()));
			jsonList.add(sorder);
		}

		// 这里的jsonList是个List<Object>对象，不是BaseAction<T>中的T对象，所以不能使用BaseAction
		// 中的List<T>对象来接收，所以要在Action中新建一个List<Object>并实现set方法，但是有点麻烦
		// 这里我们介绍一个简便的方法，之前都是先把返回的jsonList经过struts.xml文件配置给root,然后才能将
		// jsonList转成json格式
		// 其实我们不用在struts.xml文件中配置root，struts2如果找不到root，就会从栈顶拿出来数据来转json
		// 所以我们只要将现在拿到的jsonList扔到值栈的栈顶，然后在配置文件中写好result就可以了。
		// ActionContext.getContext().getValueStack().push(json );

		return "jsonList";
	}


}

package net.cxp.action;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.cxp.entity.Product;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {

	public String queryJoinCategory() {

		System.out.println("name:" + model.getName());
		System.out.println("page:" + page);
		System.out.println("rows" + rows);

		// 用来存储分页的数据
		pageMap = new HashMap<String, Object>();

		// 根据关键字和分页的参数查询相应的数据
		List<Product> productList = productService.queryJoinCategory(
				model.getName(), page, rows);
		// 存储为json数据
		pageMap.put("rows", productList);
		// 根据关键字查询总记录数
		Long total = productService.getCount(model.getName());
		// 存储为json数据
		pageMap.put("total", total);

		return "jsonMap";
	}

	public void save() {
		// fileUpload工具类被抽取为通用类，uploadFile方法接受一个fileImage对象，返回新的图片名
		String pic = fileUpload.uploadFile(fileImage);
		model.setPic(pic);
		model.setPdate(new Date());
		System.out.println(model);
		productService.save(model);

	}

	public String deleteByIds() {

		System.out.println(ids);
		// 根据ids删除多条记录
		productService.deleteByIds(ids);
		// 如果删除成功就往下执行，将true以流的形式返回前台
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

	public void update() {
		// fileUpload工具类被抽取了，uploadFile方法直接接受一个fileImage对象，返回新的图片名
		String pic = fileUpload.uploadFile(fileImage);
		model.setPic(pic);
		model.setPdate(new Date());
		System.out.println(model);
		// 更新商品
		productService.update(model);
	}

	public String get() {

		// 后台获得数据
		Product product = productService.get(model.getId());
		// 将商品信息存放在请求域，前台用el表达式获取
		request.put("product", product);

		return "detail";
	}

}

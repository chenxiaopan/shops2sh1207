package net.cxp.dao.impl;

import java.util.List;
import java.util.Set;

import net.cxp.dao.SorderDao;
import net.cxp.entity.Forder;
import net.cxp.entity.Product;
import net.cxp.entity.Sorder;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SorderDaoImpl extends BaseDaoImpl<Sorder> implements SorderDao {

	@Override
	public Forder addSorder(Forder forder, Product product, Integer numbers) {
		// 标记是否有重复项
		boolean isHave = false;

		// 得到当前购物项
		Sorder sorder = new Sorder(product, product.getName(),
				product.getPrice(), numbers);

		// 取出购物车里的购物项集合
		Set<Sorder> sorders = forder.getSorders();
		// 遍历，查找是否有重复的购物项，没有设置好表建关系有可能会报空指针异常
		for (Sorder oldSorder : sorders) {
			// 测试是否得到值
			/*
			 * System.out .println("for--->sorder" + sorder + "///" +
			 * oldSorder.getProduct().getId() + "--//" + product.getId());
			 */

			// 有重复项，将数量相加即可
			if ((oldSorder.getProduct().getId()).equals(product.getId())) {
				oldSorder.setNumbers(oldSorder.getNumbers()
						+ sorder.getNumbers());
				isHave = true;
			}
		}

		// 没有重复项，将新的购物项添加到购物车里
		if (!isHave) {
			// 在向购物中添加购物项之前，先建立购物项与购物车的关联，但是此时forder.id为null，
			// 但是在入库的时候是先入库购物车，再入库购物项，那时候就有主键了
			sorder.setForder(forder);
			forder.getSorders().add(sorder);
		}

		System.out.println("add---->forder" + forder);
		return forder;
	}

	@Override
	public Forder deleteSorder(Forder forder, int pid) {
		// 获得购物项集合
		Set<Sorder> sorders = forder.getSorders();
		System.out.println("del--------->before" + sorders.size());
		// 遍历，找出要删除的购物项
		Sorder del = null;
		for (Sorder sorder : sorders) {
			if (sorder.getProduct().getId() == pid) {
				del = sorder;
			}
		}
		sorders.remove(del);
		System.out.println("del--------->late" + sorders.size());
		return forder;
	}

	@Override
	public List<Object[]> querySale(Integer numbers) {
		String hql = "select s.name,sum(s.numbers)  from Sorder s group by s.product.id,s.name";
		return getSession().createQuery(hql).setFirstResult(0)
				.setMaxResults(numbers).list();
	}

}

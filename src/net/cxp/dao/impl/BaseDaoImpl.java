package net.cxp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import net.cxp.dao.BaseDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO（公共模块的抽取）
 * @author cxp
 * 
 */
@Repository
//spring注解，必须要延迟初始化，因为无法识别类型参数，
//只有当实例化ProductDaoImpl这些子类时才能实例化BaseDaoImpl
//也可以不实例化BaseDaoImpl或者定义为抽象类来解决这个问题
@Lazy(true)
public class BaseDaoImpl<T>  implements BaseDao<T> {

	private Class clazz;// clazz存储了当前操作的类型，即泛型T

	// 使用反射注入进来
	@Resource
	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		System.out.println("this代表的是当前调用构造方法的对象" + this);
		System.out.println("获取当前this对象的父类信息" + this.getClass().getSuperclass());
		System.out.println("获取当前this对象的父类信息，(包括泛型信息)"
				+ this.getClass().getGenericSuperclass());

		// 拿到泛型的参数类型
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		// 从当前线程获取session,如果没有则创建一个新的session
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(Integer id) {
		System.out.println(clazz.getSimpleName());
		getSession().delete(clazz.getSimpleName(), id);

	}

	@Override
	public T get(Integer id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> list() {
		String hql = "from " + clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}

}

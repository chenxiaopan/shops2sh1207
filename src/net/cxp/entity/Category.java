package net.cxp.entity;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private Integer id;
	private Account account;
	private String type;
	private Short hot;
	private Set<Product> products = new HashSet<Product>();


	@Override
	public String toString() {
		return "Category [id=" + id + ", account=" + account + ", type=" + type
				+ ", hot=" + hot + "]";
	}
	
	// Constructors


	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(Account account, String type, Short hot, Set<Product> products) {
		this.account = account;
		this.type = type;
		this.hot = hot;
		this.products = products;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Short getHot() {
		return this.hot;
	}

	public void setHot(Short hot) {
		this.hot = hot;
	}
	
	public Set<Product> getProducts() {
		return this.products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
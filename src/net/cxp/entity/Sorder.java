package net.cxp.entity;


/**
 * Sorder entity. @author MyEclipse Persistence Tools
 */

public class Sorder implements java.io.Serializable {

	// Fields

	private Integer id;
	private Forder forder;
	private Product product;
	private String name;
	private Double price;
	private Integer numbers;

	// Constructors

	/** default constructor */
	public Sorder() {
	}

	/** minimal constructor */
	public Sorder(Integer numbers) {
		this.numbers = numbers;
	}

	
	public Sorder(Product product, String name, Double price,
			Integer numbers) {
		super();
		this.product = product;
		this.name = name;
		this.price = price;
		this.numbers = numbers;
	}

	/** full constructor */
	public Sorder(Forder forder, Product product, String name, Double price,
			Integer numbers) {
		this.forder = forder;
		this.product = product;
		this.name = name;
		this.price = price;
		this.numbers = numbers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Forder getForder() {
		return this.forder;
	}

	public void setForder(Forder forder) {
		this.forder = forder;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumbers() {
		return this.numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

}
package net.cxp.entity;

import java.util.Date;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Long userid;
	private String uname;
	private String upwd;
	private String usex;
	private Date ubirth;
	private String utel;
	private String uadd;
	private Short utype;

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(String uname, String upwd, String usex, Date ubirth,
			String utel, String uadd, Short utype) {
		this.uname = uname;
		this.upwd = upwd;
		this.usex = usex;
		this.ubirth = ubirth;
		this.utel = utel;
		this.uadd = uadd;
		this.utype = utype;
	}

	// Property accessors

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return this.upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUsex() {
		return this.usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	public Date getUbirth() {
		return this.ubirth;
	}

	public void setUbirth(Date ubirth) {
		this.ubirth = ubirth;
	}

	public String getUtel() {
		return this.utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public String getUadd() {
		return this.uadd;
	}

	public void setUadd(String uadd) {
		this.uadd = uadd;
	}

	public Short getUtype() {
		return this.utype;
	}

	public void setUtype(Short utype) {
		this.utype = utype;
	}

}
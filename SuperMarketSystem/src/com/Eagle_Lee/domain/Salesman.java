package com.Eagle_Lee.domain;

/**
 * 售货员对象
 * @author Eagle_Lee
 * source--Generate...Fields & source--Generate getters
 */
public class Salesman {
	/*成员变量*/
	private int id;
	private String name;
	private String passwd;
	
	
	@Override
	public String toString() {
		return "Salesman [id=" + id + ", name=" + name + ", passwd=" + passwd
				+ ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getPasswd()=" + getPasswd() + "]";
	}

	public Salesman(){
		
	}

	public Salesman(int id, String name, String passwd) {
		super();
		this.id = id;
		this.name = name;
		this.passwd = passwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
	
}

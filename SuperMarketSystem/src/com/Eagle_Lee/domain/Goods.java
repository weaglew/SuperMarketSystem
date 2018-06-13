package com.Eagle_Lee.domain;

public class Goods {

	/**
	 * 成员变量
	 */
	private int id;
	private String name;
	private double price;
	private int num;   //商品的数量
	
		
	
	
	
	
	@Override
	/**
	 * 打印goods成员变量字段值
	 * 不加打印此对象的内存地址
	 */
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price
				+ ", num=" + num + "]";
	}

	public Goods(){
		
	}
	
	public Goods(String name,double price,int num,String tips){
			
			this.name=name;
			this.price=price;
			this.num=num;
			
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
	
}

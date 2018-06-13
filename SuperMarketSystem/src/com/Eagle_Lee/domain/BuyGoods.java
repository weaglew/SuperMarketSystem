package com.Eagle_Lee.domain;
/**
 * 把要买的商品重新封装 防止修改数据库中的源数据
 * BuyGoods类包含Goods类和将要购买商品的数量
 * @author Eagle_Lee
 */
public class BuyGoods {
	@Override
	public String toString() {
		return "BuyGoods [goods=" + goods + ", number=" + number + "]";
	}

	private Goods goods;//买的商品信息
	private int number;//购买的数量
	public BuyGoods() {
		// TODO Auto-generated constructor stub
	}
	
	public BuyGoods(Goods goods, int number) {
		super();
		this.goods = goods;
		this.number = number;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	


}

package com.Eagle_Lee.domain;
/**
 * ��Ҫ�����Ʒ���·�װ ��ֹ�޸����ݿ��е�Դ����
 * BuyGoods�����Goods��ͽ�Ҫ������Ʒ������
 * @author Eagle_Lee
 */
public class BuyGoods {
	@Override
	public String toString() {
		return "BuyGoods [goods=" + goods + ", number=" + number + "]";
	}

	private Goods goods;//�����Ʒ��Ϣ
	private int number;//���������
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

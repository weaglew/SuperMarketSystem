package com.Eagle_Lee.test;

import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.Test;

import com.Eagle_Lee.dao.GoodsDao;
import com.Eagle_Lee.domain.Goods;
import com.Eagle_Lee.view.MainPage;

public class TestDao {
	
	
	
	@Test
	public void test(){
		GoodsDao goodsDao=new GoodsDao();
		System.out.println(goodsDao.getMaxID());
	}
	
	@Test
	public void test2(){
		GoodsDao goodsDao=new GoodsDao();
		Goods goods=new Goods("����",1200,20,null);
		
		boolean flag=goodsDao.addGoods(goods);
		System.out.println(flag);
	}
	@Test
	public void test3(){
		MainPage mainPage=new MainPage();
		mainPage.showMainPage();
		
	}
	@Test
	public void test4(){
		GoodsDao goodsDao =new GoodsDao();
		Goods goods=goodsDao.selectGoods("⨺���");//����goods
		System.out.println(goods);
	}
	@Test
	public void test5() {
		GoodsDao goodsDao =new GoodsDao();
		Goods goods =new Goods();
		goods.setName("myeclipse");
		goods.setNum(1970);
		goods.setPrice(10000);
		
		boolean boo=goodsDao.updateGoods("eclipse", goods);
		System.out.println(boo);
	}
	@Test
	public void test6(){
		GoodsDao goodsDao =new GoodsDao();
		Goods goods =new Goods();
		goods.setName("ip");
		goods.setNum(1970);
		goods.setPrice(10000);
		boolean boo1=goodsDao.addGoods(goods);
		boolean boo=goodsDao.deleteGood("ip", goods);
		System.out.println(boo1);
		System.out.println(boo);
	}
	
	@Test
	public void test7(){
		GoodsDao goodsDao =new GoodsDao();
		ArrayList<Goods> list=goodsDao.showGoods();
		for (Goods goods:list) {
//			System.out.println(goods.toString());   //����Goods.java��toString�������
			System.out.println("��Ʒ���ƣ�"+goods.getName());
			System.out.println("��Ʒid��"+goods.getId());
		}
	}
   
	/*��Ʒά��ģ����Խ���*/
	
	

}

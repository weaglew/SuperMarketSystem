package com.Eagle_Lee.test;
/**
 * Ä£¿é¶þ²âÊÔ
 * @author Eagle_Lee
 */
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.Eagle_Lee.dao.SalesmanDao;
import com.Eagle_Lee.dao.SalesmanManageDao;
import com.Eagle_Lee.dao.TodaySoldListDao;
import com.Eagle_Lee.domain.BuyGoods;
import com.Eagle_Lee.domain.Salesman;
import com.Eagle_Lee.view.MainPage;
import com.Eagle_Lee.view.TodaySoldPage;

public class Test3Dao {
  @Test
	public void test() {
	SalesmanManageDao salesmanManageDao =new SalesmanManageDao();
	int a =salesmanManageDao.getMaxPerId();
	System.out.println(a);
	
}
  @Test
  public void test2() {
	MainPage mainPage =new MainPage();
	mainPage.itemManagement();
}
  @Test
  public void test3(){
	  SalesmanDao salesmanDao =new SalesmanDao();
	  int a=salesmanDao.getMaxIdSold();
     System.err.println(a);
  }
  @Test
  public void test4() {
	//²âÊÔ ±ß¼õ¿â´æ ±ßÍùSOLD_DETAIL_LISTºÍSOLD_OUT_LIST
	  TodaySoldListDao todaySoldListDao =new TodaySoldListDao();
	  ArrayList<BuyGoods> list =todaySoldListDao.details();
	  for (BuyGoods buyGoods : list) {
		System.out.println(buyGoods.toString());
	}

	  
}
  @Test
  public void test5() {
	TodaySoldPage td=new TodaySoldPage();
	td.showTodaySold();
}
  
}

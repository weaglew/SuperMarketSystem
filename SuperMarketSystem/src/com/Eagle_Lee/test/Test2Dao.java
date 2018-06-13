package com.Eagle_Lee.test;

import org.junit.Test;

import com.Eagle_Lee.dao.SalesmanDao;
import com.Eagle_Lee.domain.Salesman;
import com.Eagle_Lee.view.PayPage;
import com.Eagle_Lee.view.UserLoginPage;

/**
 *用于测试模块二 
 * @author Eagle_Lee
 *
 */
public class Test2Dao {
@Test
	public void test1() {
	    
	    UserLoginPage userLoginPage=new UserLoginPage();
	   userLoginPage.showLoginPage();
	   
	}
@Test
	public void test2(){
	   PayPage page=new PayPage();
	   page.showPayPage();
   }
	
	
}

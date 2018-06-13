package com.Eagle_Lee.view;

import java.util.Scanner;

import com.Eagle_Lee.dao.SalesmanDao;
import com.Eagle_Lee.domain.Salesman;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserLoginPage {
	/**与类名相同时为构造函数 只在new的时候运行一次；
	 * 设置为其他名字的函数时 可以调用方法**/
	Scanner sc;       //全局变量sc
	Salesman salesman;//全局变量salesman
	
	    /*构造函数*/
	public  UserLoginPage() {
		sc=new Scanner(System.in);
		salesman= new Salesman();
	}
	/*显示登录界面函数*/
	public void showLoginPage() {
		SalesmanDao salesmanDao=new SalesmanDao();
		int count=0;
		while (true) {
			
			System.out.println("请输入用户名：");
			String uname=sc.next();
			System.out.println("请输入密码：");
			String upasswd= sc.next();
			salesman.setName(uname);    //传入salesman对象参数
			salesman.setPasswd(upasswd);//传入salesman对象参数
			//将传好参数的salesman对象封装给login函数
			//判断登录结果
			boolean boo=salesmanDao.login(salesman);
			
			if(boo){
				PayPage page =new PayPage();
				page.showPayPage();
				break;
			}else {
				count++;
				System.err.println("还剩"+(3-count)+"次");
				System.err.println("用户名和密码不匹配");
				if (count==3) {
					return;
				} 
			}
			
		}
	}			
}
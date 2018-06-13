package com.Eagle_Lee.view;

/**
 * 商品维护>>商品管理>>售货员管理 界面
 * @author Eagle_Lee
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

import com.Eagle_Lee.dao.JdbcTool;
import com.Eagle_Lee.dao.SalesmanDao;
import com.Eagle_Lee.dao.SalesmanManageDao;
import com.Eagle_Lee.domain.Goods;
import com.Eagle_Lee.domain.Salesman;

public class SalesmanManagePage {

	/*全局变量*/
	Scanner scanner;
	Salesman salesman;
	
	/*构造函数*/
	public  SalesmanManagePage() {
	      scanner=new Scanner(System.in);
	      salesman=new Salesman();
	}
	
	/*显示登录界面函数**/
	public void showSalesmanManage() {
		while (true) {
			System.out.println("执行显示商品维护菜单");
			System.out.println("商超购物管理系统>>商品管理>>售货员管理");
			System.out.println("****************************************");
			System.out.println("\n\t\t1.添加售货员\n");
			System.out.println("\t\t2.更改售货员\n");
			System.out.println("\t\t3.删除售货员\n");
			System.out.println("\t\t4.显示所有售货员\n");
			System.out.println("\t\t5.查询售货员\n");
			System.out.println("****************************************");
			System.out.println("请选择,输入数字或者按0返回上一级菜单：");
			String flag=scanner.next();
			
			switch (Integer.parseInt(flag)) {
			case 1:
			    addSalesman();    //添加售货员
				break;
			case 2: 
				changeSalesman();  //更改售货员
				break;
			case 3:
				deleteSalesman();  //删除售货员
				break;
			case 4:
				showAllSalesman();  //显示所有售货员
				break;
			case 5:
				selectSalesman();   //查询售货员
				break; 
			default:
				return;           //能返回到();
				
			}
	   }
	}
	
	/**
	 * 商品管理>>售货员管理 >>添加售货员 界面
	 */
	public void addSalesman() {
		
		System.out.println("执行添加售货员操作：");
		SalesmanManageDao salesmanManageDao = new SalesmanManageDao();
		do {
			Salesman salesman =new Salesman();
			System.out.println("添加售货员姓名：");
			String name =scanner.next();
			System.out.println("添加售货员密码");
			String passwd=scanner.next();
			/*传参到实例*/
			salesman.setName(name);
			salesman.setPasswd(passwd);
			/*调用Dao方法*/
			//salesmanManageDao.addSalesmanPer(salesman);
			/*判断是否添加成功*/
			boolean boo=salesmanManageDao.addSalesmanPer(salesman);
			if (boo) {
				System.out.println("售货员"+name+"添加成功");
				System.out.println("是否继续(y/n):");
				String sure =scanner.next();
				if (sure.equals("y")) {
					
				}else if (sure.equals("n")) {
					break;
				}else {
					System.out.println("您的输入有误，请重新输入");
					return;
				}
			}else {
				System.out.println("添加失败，请重新添加");
				return;
			}
	   } while (true);
				
	}

	/**
	 * 商品管理>>售货员管理 >>更改售货员 界面
	 */
	public void changeSalesman() {
		SalesmanManageDao salesmanManageDao =new SalesmanManageDao();
		System.out.println("执行更改售货员操作：");
	do {
		
		System.out.println("输入更改的售货员姓名：");
		System.out.println("售货员姓名");
		String name =scanner.next();
		//旧传
		 
		Salesman salesman2=salesmanManageDao.selectSalesman(name);
      
		/**如果输入的售货员名称不存在**/
		if (salesman2==null) {
			System.out.println("查询的售货员"+name+"不存在，请重新输入");
			break;
		}else {
			System.out.println("售货员姓名\t\t售货员密码");
			System.out.print(salesman2.getName()+"\t"+"\t");
			System.out.print(salesman2.getPasswd()+"\t"+"\t");
			System.out.println();
			
			
			System.out.println("选择您要更改的内容：");
			System.out.println("1.更改售货员姓名：");
			System.out.println("2.更改售货员密码：");
			String sure=scanner.next();
			switch (Integer.parseInt(sure)) {
			case 1:
				System.out.println("将售货员名称更改为：");  //只改商品名称
				String updateNString=scanner.next();
				salesman.setName(updateNString);
				salesman.setPasswd(salesman2.getPasswd());
				boolean boo =salesmanManageDao.updateSalesman(salesman2.getName(),salesman);
				if (boo) {
					System.out.println("售货员名称修改成功");
				} else {
                   System.out.println("售货员名称修改失败");
				}
				break;
			case 2:
				System.out.println("将售货员密码更改为：");
                String upasswd=scanner.next();
                salesman.setName(salesman2.getName());
                salesman.setPasswd(upasswd);
                boolean boo2 = salesmanManageDao.updateSalesman(salesman2.getName(), salesman);
                if (boo2) {
					System.out.println("售货员密码修改成功");
				} else {
					System.out.println("售货员密码修改失败");
				}
                break;
                
            default:
				break;
			}
			
			System.out.println("是否继续(y/n):");
			String sc=scanner.next();
			 if (sc.equals("y")) {
					
				}else {
					return;  //返回上一级调用此函数的函数
				    
				}
			
			
		  }
		 
	   } while (true);
	}

	/**
	 * 商品管理>>售货员管理 >>删除售货员 界面
	 * */
	public void deleteSalesman() {
		SalesmanManageDao salesmanManageDao= new SalesmanManageDao();
		System.out.println("执行删除售货员操作：");
		do {
			System.out.println("输入将要删除售货员：");
			String dname=scanner.next();
			Salesman salesman2=salesmanManageDao.selectSalesman(dname);
			      
			     /**如果输入的售货员名称不存在**/
			if (salesman2==null) {
				System.out.println("查询的售货员"+dname+"不存在，请重新输入");
				break;
			}else {
				System.out.println("售货员姓名\t\t售货员密码");
				System.out.print(salesman2.getName()+"\t"+"\t");
				System.out.print(salesman2.getPasswd()+"\t"+"\t");
				System.out.println();
				System.out.println("是否确定要删除(y/n)？");
				String su=scanner.next();
				if (su.equals("y")) {
					
					boolean boo=salesmanManageDao.deleteMan(salesman2.getName(), salesman);
					if (boo) {
						System.out.println("售货员"+dname+"已经成功删除");
					}
				} else {
					return;
				}
				System.out.println("是否继续(y/n)?:");
				String sure3=scanner.next();
				if (sure3.equals("y")) {
					
				} else {
					return;
				}
			}
		} while (true);
	}
 
	/**
	 * 商品管理>>售货员管理 >>显示所有售货员 界面
	 */

	public void showAllSalesman() {
		SalesmanManageDao salesmanManageDao= new SalesmanManageDao();
		ArrayList<Salesman> list =salesmanManageDao.allMembers();
		//不用new一个新的地址 因为只要定义一个集合list来接收就好了
		System.out.println("执行显示所有售货员操作：");
		do {
			System.out.println("显示所有售货员");
			System.out.println("售货员姓名\t\t售货员密码\t\t");
			/*用foreach循环读出list内的数据结果*/
			for (Salesman salesman: list) {
				System.out.println(salesman.getName()+"\t\t\t"+salesman.getPasswd()+"\t\t");
			}
			break;
		} while (true);
		
		
	}

    /**
     * 商品管理>>售货员管理 >>查询售货员 界面
     */
	 
	public void selectSalesman() {
		do {
			SalesmanManageDao salesmanManageDao =new SalesmanManageDao();
			System.out.println("执行查询售货员操作");
			System.out.println("输入要查询的售货员姓名关键字");
			String sc=scanner.next();
			ArrayList<Salesman> list=salesmanManageDao.keyWdSelect(sc);
			System.out.println("售货员姓名\t\t售货员密码\t\t");
			for (Salesman salesman : list) {
				System.out.print(salesman.getName()+"\t\t\t"+salesman.getPasswd());
				System.out.println();
			}
			System.out.println("是否继续(y/n):？");
			String sur=scanner.next();
			if (sur.equals("y")) {
				
			}else {
				break;
			}
		} while (true);
	}
  
   
	
	

}

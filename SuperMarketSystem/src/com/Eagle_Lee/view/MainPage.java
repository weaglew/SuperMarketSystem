
/**
 * com.Eagle_Lee.dao 包放置的是运行的程序内容
 * com.Eagle_Lee.domain 包放置的是JavaBean 用来产生接口
 * com.Eagle_Lee.service 包放置的是对外的服务界面等
 * @author Eagle
 */
package com.Eagle_Lee.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.Eagle_Lee.dao.GoodsDao;
import com.Eagle_Lee.dao.SalesmanDao;
import com.Eagle_Lee.domain.Goods;
import com.Eagle_Lee.domain.Salesman;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.swing.internal.plaf.basic.resources.basic;

//系统开始运行
public class MainPage {
	
	private  Scanner scanner;
	
	public MainPage(){
		scanner=new Scanner(System.in);                //构造函数 scanner ---接收用户输入
	}
	
	         /**系统主界面**/
	public  void showMainPage() {
	              
	    while(true){                                   //while循使得一个输入一个账号
		System.out.println("****************************************");
		System.out.println("\n\t\t1.商品维护\n");
		System.out.println("\t\t2.前台收银\n");
		System.out.println("\t\t3.商品管理\n");
		System.out.println("****************************************");
		System.out.println("请选择,输入数字或者按0退出：");
		
														//选择界面接入函数
		String next =scanner.next();
		//判断输入的是什么 根据输入的数字接入相应的模块
		if (next.equals("1")) {
			
		    maintainItem();                             //商品维护模块
		} else if (next.equals("2")) {
			System.out.println("执行前台收银\n\n");
	        moneyCollection();                          //前台收银模块
		}else if(next.equals("3")){
			System.out.println("执行商品管理\n\n");
			itemManagement();                           //商品管理模块	
		}else if (next.equals("0")) {
			System.out.println("谢谢您的使用！");
			System.exit(0);
		} else {
			System.err.println("输入不合格，请重新输入");
			
		}
		
	    }
		
		
    }
	        /**商品维护模块界面**/
	 public void maintainItem(){
		 while (true) {
			 System.out.println("执行显示商品维护菜单");
	    	 System.out.println("商超购物管理系统>>商品维护");
	    	 System.out.println("****************************************");
	    	 System.out.println("\n\t\t1.添加商品\n");
			 System.out.println("\t\t2.更改商品\n");
			 System.out.println("\t\t3.删除商品\n");
			 System.out.println("\t\t4.显示所有商品\n");
			 System.out.println("\t\t5.查询商品\n");
			 System.out.println("****************************************");
			 System.out.println("请选择,输入数字或者按0返回上一级菜单：");
			 String flag=scanner.next();
			 
			 switch (Integer.parseInt(flag)) {
     			case 1:
     				 	addGoods();    					//添加商品
				 break;
     			case 2: 
     					changeGoods(); 				   //修改商品
				 break;
     			case 3:
     					deleteGoods();                 //删除商品
   				 break;
     			case 4:
        			    showAllItem();                //显示所有商品
   				 break;
     			case 5:
        			    selectItem();  				  //查询商品
   				 break; 
			default:
				showMainPage();   					 //能返回到showMainPage();
				                   
				
			}
	//实现		 			
		}
	     }
	
	        /**商品维护>>添加商品界面**/
	public void addGoods() {
		System.out.println("请选择,输入数字1确认进行添加商品操作或者0返回上一级菜单：");
		String sure=scanner.next();
		 GoodsDao goodsDao=new GoodsDao();   //连库 
		 do {
			 
			 if (sure.equals("1")) {
					System.out.println("执行添加商品操作：");
					System.out.println("添加商品名称：");
					String name=scanner.next();
					System.out.println("添加商品价格：");
					String price=scanner.next();
					System.out.println("添加商品数量：");
					String num=scanner.next();
					Goods goods=new Goods(name,Double.parseDouble(price),Integer.parseInt(num),null);     //实例化对象
					boolean b=goodsDao.addGoods(goods);
					
					System.out.println("是否继续(y/n):");			
				    String sc=scanner.next();
					 if (sc.equals("y")) {
						
					}else {
						maintainItem();
					    break;	
					}
				}else {
					maintainItem();
					break;
				}
		} while (true);
		 
	}

	
	       /**商品维护>>更改商品界面**/
	public void changeGoods() {
		
		System.out.println("请选择,输入数字2确认进行更改商品操作或者0返回上一级菜单：");
		String sure=scanner.next();
		 GoodsDao goodsDao=new GoodsDao();   					//实例化
		 do {
			 Goods goods=new Goods();
			 
			 if (sure.equals("2")) {
					System.out.println("执行更改商品操作：");
					System.out.println("输入更改商品名称：");
					String name=scanner.next();
					Goods goods2=goodsDao.selectGoods(name);  //查询商品结果
				        
					        /**如果输入的商品名称不存在**/
					if (goods2!=null) {    					 //goods2是上一语句查询商品的结果
						System.out.println("商品名称\t\t商品价格\t\t商品数量");
						System.out.print(goods2.getName()+"\t"+"\t");
						System.out.print(goods2.getPrice()+"\t"+"\t");
						System.out.print(goods2.getNum()+"\t"+"\t");
						System.out.println();
						System.out.println("选择您要更改的内容：");
						System.out.println("1.更改商品名称：");
						System.out.println("2.更改商品价格：");
						System.out.println("3.更改商品数量：");
						/**
						 * 连库 显示商品名称、商品价格、商品数量  
						 */
						String price=scanner.next();
						
						//根据数字123进入相应的函数
						switch (Integer.parseInt(price)) {
						case 1:
							System.out.println("请输入要更改商品名称");  //只改商品名称
							String updateName=scanner.next();
							goods.setName(updateName);                 //除了名字 其他不变的传入函数
							goods.setNum(goods2.getNum());
							goods.setPrice(goods2.getPrice());
							boolean boo=goodsDao.updateGoods(goods2.getName(), goods);
							if (boo) {
								System.out.println("商品名称更新成功");
							}else {
								System.out.println("商品名称更新失败");
							}
							break;
						case 2:
							System.out.println("请输入要更改商品价格");
							String updatePrice=scanner.next();
							goods.setName(goods2.getName());         //除了价格 其他不变的传入函数
							goods.setNum(goods2.getNum());
							goods.setPrice(Double.parseDouble(updatePrice));  //将输入内容转为double类型更改价格
							boolean boo2=goodsDao.updateGoods(goods2.getName(), goods);
							if (boo2) {
								System.out.println("商品价格更新成功");
							}else {
								System.out.println("商品价格更新失败");
							}
							break;
						case 3:
							System.out.println("请输入要更改商品数量");
							String updateNum=scanner.next();
							goods.setName(goods2.getName());               //除了数量 其他不变的传入函数
							goods.setNum(Integer.parseInt(updateNum));
							goods.setPrice(goods2.getPrice());
							boolean boo3=goodsDao.updateGoods(goods2.getName(), goods);
							if (boo3) {
								System.out.println("商品数量更新成功");
							}else {
								System.out.println("商品数量更新失败");
							}
							break;

						default:
							break;
						}
						
						
						System.out.println("是否继续(y/n):");			
					    String sc=scanner.next();
						 if (sc.equals("y")) {
							
						}else {
							return; 									 //返回上一级调用此函数的函数
						    
						}
					}else {
						System.out.println("查询商品不存在");
						break;
					}
			}
			 else if (sure.equals("0")) {
				maintainItem();
				break;
			}else {
				System.out.println("您输入有误，请重新输入");
				
				break;
			}
		} while (true);
	}
	
	
	      /**商品维护>>删除商品界面**/
	public void deleteGoods() {
		System.out.println("请选择,输入数字3确认进行删除商品操作或者0返回上一级菜单：");
		String sure=scanner.next();
		 GoodsDao goodsDao=new GoodsDao();   							//实例化
		 do {
			Goods goods=new Goods();
			if (sure.equals("3")) {
				System.out.println("执行删除商品操作");
				System.out.println("输入将要删除的商品名称");
				String name=scanner.next();
				Goods goods3=goodsDao.selectGoods(name);
				     /*如果输入的商品不存在*/
				if (goods3!=null) {
					System.out.println("商品名称\t\t商品价格\t\t商品数量");
					System.out.print(goods3.getName()+"\t"+"\t");
					System.out.print(goods3.getPrice()+"\t"+"\t");
					System.out.print(goods3.getNum()+"\t"+"\t");
					System.out.println();
					System.out.println("是否确定要删除该商品(y/n)？:");
					String sure2 =scanner.next();
					if (sure2.equals("y")) {
						boolean boo=goodsDao.deleteGood(name, goods3);
						if (boo) {
							System.out.println("该商品已删除");
						} else {
							System.out.println("删除操作失败，请重新操作");
							return;
						}
				
					} else{
						return;
					}
					

				}else {
				System.out.println("您输入的商品不存在");
					break;
				}
				System.out.println("是否继续(y/n)：");
				String sure3=scanner.next();
				if (sure3.equals("y")) {
					
				} else {
					return;
				}
			}else if(sure.equals("0")){
				maintainItem();
				break;
			}else {
				System.out.println("您输入有误，请重新输入");
				break;
			}
		} while(true);
	}

		 /**商品维护>>显示所有商品界面**/
    public void showAllItem() {
    	System.out.println("请选择,输入数字4确认进行显示所有商品操作或者0返回上一级菜单：");
		String sure=scanner.next();
		GoodsDao goodsDao=new GoodsDao(); 
		/*进入显示所有商品函数。由于是集合结果 需用集合声明变量来接收结果*/
		ArrayList<Goods> list=goodsDao.showGoods();
		do {
			if (sure.equals("4")) {
				System.out.println("显示所有商品");
				System.out.println("商品名称\t\t商品价格\t\t商品数量\t\t备注");
				/*用foreach循环读出list内的数据结果*/
				for (Goods goods : list) {
					System.out.println(goods.getName()+"\t\t"+goods.getPrice()+"\t\t"+goods.getNum()+"\t\t"+goods.getTips());
				}
				
				break;
			} else if(sure.equals("0")){
				return;
			}else {
				System.out.println("您输入有误，请重新输入");
				break;
			}
		} while (true);
	 
	}

    	/**商品维护>>查询商品界面**/	
    public void selectItem() {
    	System.out.println("请选择,输入数字5确认进行显示所有商品操作或者0返回上一级菜单：");
		String sure=scanner.next();
		GoodsDao goodsDao=new GoodsDao(); 
		
		do {
			if (sure.equals("5")) {
				System.out.println("执行查询商品操作");
				System.out.println();
				System.out.println("1.按商品数量升序查询");
				System.out.println("2.按商品价格升序查询");
				System.out.println("3.输入关键字查询商品");
				System.out.println("请选择，输入数字或按0返回上一级菜单：");
				/*进入按条件查询函数*/
				
				String way=scanner.next();
				do {
					if (way.equals("1")) {
						//执行按商品数量升序查询
						ArrayList<Goods> list = goodsDao.numUpSort();
						for (Goods goods : list) {
							System.out.println(goods.getName());
							System.out.println(goods.getPrice());
							System.out.println(goods.getNum());
							
						}
						break;
					} else if(way.equals("2")){
						//执行按商品价格升序查询
						ArrayList<Goods> list=goodsDao.priceUpSort();
						for (Goods goods : list) {
							System.out.println(goods.getName());
							System.out.println(goods.getPrice());
							System.out.println(goods.getNum());
						}
						break;
					}else if(way.equals("3")){
						//执行关键字查询商品
						System.out.println("请输入关键字：");
						String name=scanner.next();
						ArrayList<Goods> list = goodsDao.keywdSort(name);
						for (Goods goods : list) {
							System.out.println(goods.getName());
							System.out.println(goods.getPrice());
							System.out.println(goods.getNum());
						}
						break;
					}else if (way.equals("0")) {
						return;
					}
					else {
						return;
					}
				} while (true);
			} else if(sure.equals("0")){
                return;
                
			}else {
				System.out.println("您输入有误，请重新输入：");
				return;
				
			}
		} while (true);
	}
    
        /**前台收银模块界面**/
    public void moneyCollection() {
    	Salesman salesman=new Salesman();
    	SalesmanDao salesmanDao=new SalesmanDao();
    	UserLoginPage userLoginPage=new UserLoginPage();
		System.out.print("欢迎使用商超购物管理系统");
		System.out.println();
    	do {
    		System.out.println("1.登录系统");
    		System.out.println("2.退出");
    		System.out.println("****************************************");
    		System.out.println("请选择，输入数字：");
    		String sure=scanner.next();
    		if (sure.equals("1")) {
    			userLoginPage.showLoginPage();
    			//接入登录系统函数login()的界面函数;
    			break;
    		} else if(sure.equals("2")){
    			//退出系统
    			return;
    		}else {
    			System.err.println("您的输入有误，请重新输入：");
    			return;
    		}
		} while (true);
	}
    
    	/**商品管理模块界面**/
    public void itemManagement() {
    	SalesmanManagePage salesmanManagePage =new SalesmanManagePage();
    	TodaySoldPage tdSoldPage=new TodaySoldPage();
    	
    	System.out.println("商超购物管理系统>>商品管理");
    	do {
			
    		System.out.println("****************************************");
    		System.out.println();
    		System.out.println("\t\t1.列出当日卖出商品列表");
    		System.out.println("\t\t2.售货员管理");
    		System.out.println();
    		System.out.println("****************************************");
    		System.out.println("请选择，输入数字或者按0返回上一级菜单：");
    		String sure=scanner.next();
    		if (sure.equals("1")) {
    			//接入列出当日卖出商品列表函数界面 showListTodayGoods();
    		    tdSoldPage.showTodaySold();
    			break;
    		}else if (sure.equals("2")) {
    			//售货员管理  调用//showSalesmanManage();//售货员管理界面
				salesmanManagePage.showSalesmanManage();
    			break;
			}else if (sure.equals("0")) {
				showMainPage();
			}
    		else {
				System.out.println("您的输入有误，请重新输入");
				return;
			}
		} while (true);
	}
    
   
    
    public static void main(String[] args) {
		new MainPage().showMainPage();
	}
}




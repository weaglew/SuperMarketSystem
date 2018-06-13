package com.Eagle_Lee.view;
/**
 * 显示在前端的view层
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import jdk.nashorn.internal.ir.BreakableNode;

import com.Eagle_Lee.dao.GoodsDao;
import com.Eagle_Lee.dao.SalesmanDao;
import com.Eagle_Lee.domain.BuyGoods;
import com.Eagle_Lee.domain.Goods;
import com.Eagle_Lee.domain.Salesman;
import com.sun.org.apache.bcel.internal.generic.NEW;


public class PayPage {
	
	Scanner scanner;
	Salesman salesman;
	SalesmanDao salesmanDao;
	GoodsDao goodsDao;
	public PayPage() {
		scanner=new Scanner(System.in);
		salesman=new Salesman();
		salesmanDao=new SalesmanDao();   //记得new哟
		goodsDao=new GoodsDao();
	} 
	
	public void showPayPage() {
		ArrayList<BuyGoods> goodslist =new ArrayList<BuyGoods>();//建立要买的产品的集合
		ByNewing byNewing= new ByNewing();
		do {
			Goods goods =new Goods();  //用户将要购买的商品信息

			System.out.println("1.购物结算");
			System.out.println();
			System.out.println("请输入商品关键字：");
			//接入查询函数
			String name ;
			ArrayList<Goods>  list; //所有list均为此list 即模糊查询后的结果集
			do {
				name =scanner.next();
				list=salesmanDao.selectGoods(name);
				//System.err.println("..."+list);
				if (list.size()==0) {
					System.out.println("暂无该商品，请重新输入：");
				}else {
					break;
				}
			} while (true);
			System.out.println("商品名称\t\t商品价格\t\t商品数量\t\t");
			//遍历输出模糊查询结果
			for (Goods goods2 : list) {
				System.out.print(goods2.getName());
				System.out.print("\t\t"+goods2.getPrice());
				System.out.println("\t\t"+goods2.getNum());
			}
			
			System.out.println("请选择商品：");
			String name1=scanner.next();
            goods.setName(name1);
			System.out.println("请输入购买数量：");
			String num;
			
			//给刚才新建的goods对象填充价格和库存
			for (Goods goods3:list) {
				if (name1.equals(goods3.getName())) {
					goods.setId(goods3.getId());
					goods.setPrice(goods3.getPrice());
					goods.setNum(goods3.getNum());
					break;
				}
			}
			
			do {
				num=scanner.next();
				if (Integer.parseInt(num)>goods.getNum()) {  //goods默认是最后一条数据
					System.out.println("\t\t\t库存不足，请重新输入");
					System.out.println();
					return;
					
				}else {
					break;
				}
			} while (true);
			
			BuyGoods buyGoods=new BuyGoods(goods,Integer.parseInt(num));
			goodslist.add(buyGoods);//把每一个goods对象都存在goodslist里  ---//接收第一次选好的商品？
			
			System.out.println("是否继续(y/n)？");
			String sure=scanner.next();
			 
			if (sure.equals("y")) {
				
			} else {
				//如果是n 计算总价totalPrice
				//
				System.out.println("总计：");
				double sumGoods=0;
				for (BuyGoods goods4 : goodslist) {  //goods4代表每个goodslist集合里的元素；循环一次代表一个元素
					sumGoods=sumGoods+goods4.getGoods().getPrice()*goods4.getNumber();
				}
				boolean boo=byNewing.checkBuy(goodslist);//减少库存
				if(boo){
					System.out.println("库存减少成功");
					System.out.println(sumGoods);
				}else {
					System.out.println("库存不足，请调整购买数量。");
				}
				/***************************************************/
				
				System.out.println("请输入实际交费金额：");
				String money;
				
				//接入找钱金额
				do {
					money=scanner.next();
					if (Double.parseDouble(money)>=sumGoods) {
						break;
					}else {
						System.out.println("钱不够啦~ 再拿点？");
						
					}
				} while (true);
				System.out.println("找钱：");
				double extra=Double.parseDouble(money)-sumGoods;
				System.out.println(extra);
				System.out.println("谢谢光临");
				
				return;
			}
			
		} while (true);
	}
	
	
	
}

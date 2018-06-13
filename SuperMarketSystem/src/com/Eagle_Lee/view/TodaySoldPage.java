package com.Eagle_Lee.view;

import java.util.ArrayList;

import com.Eagle_Lee.dao.SalesmanManageDao;
import com.Eagle_Lee.dao.TodaySoldListDao;
import com.Eagle_Lee.domain.BuyGoods;
import com.Eagle_Lee.domain.Salesman;

/**
 *  * 商品维护>>商品管理>>查询当日商品卖出 界面
 * @author Eagle_Lee
 *
 */

public class TodaySoldPage{
	
	public void showTodaySold() { 
		System.out.println("执行列出当日卖出商品列表操作：");
		
			SalesmanManageDao salesmanManageDao= new SalesmanManageDao();
			ArrayList<BuyGoods> list=new ArrayList<BuyGoods>();
			System.out.println("今日售出商品：");
			System.out.println("商品名称\t\t商品价格\t\t商品数量\t\t销量\t\t备注");
			//接入查询当日卖出商品列表函数   
			TodaySoldListDao tdListDao =new TodaySoldListDao();
			list=tdListDao.details();
			//输出查询结果
			for (BuyGoods buyGoods : list) {
				if (buyGoods.getGoods().getNum()<10) {
					System.out.print(buyGoods.getGoods().getName());
					System.out.print("\t\t"+buyGoods.getGoods().getPrice());
					System.out.print("\t\t"+buyGoods.getGoods().getNum());
					System.out.print("\t\t"+buyGoods.getNumber());
					System.out.println("\t\t"+"*该商品不足10件");
				}
			}
			if (list.isEmpty()) {
				System.out.println("今日没有卖出商品哟~");
			}
		
	}
	
	
}

package com.Eagle_Lee.view;
/**
 * 前端PayPage.java中showPayPage();函数和SalesmanDao里decreaseItem函数的中间层 
 * 对前端返回一个是否执行成功的boolean类型 同时接受前端传入的list集合 将其中的数据分割传入Dao
 * 将decreaseItem中参数传入本函数
 * service层
 */
import java.util.ArrayList;

import com.Eagle_Lee.dao.SalesmanDao;
import com.Eagle_Lee.domain.BuyGoods;
import com.Eagle_Lee.domain.Salesman;

public class ByNewing {
    public boolean checkBuy(ArrayList<BuyGoods> list) {
    	SalesmanDao salesmanDao =new SalesmanDao();
    	Salesman salesman =new Salesman();
    	salesman.setId(23);
    	for (BuyGoods buyGoods2 : list) {
			boolean boo=salesmanDao.decreaseItem(buyGoods2,salesman);
			if (boo) {
				
			}else {
				return false;
						
			}
		}
    	return true;
	}
}

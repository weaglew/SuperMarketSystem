package com.Eagle_Lee.view;
/**
 * ǰ��PayPage.java��showPayPage();������SalesmanDao��decreaseItem�������м�� 
 * ��ǰ�˷���һ���Ƿ�ִ�гɹ���boolean���� ͬʱ����ǰ�˴����list���� �����е����ݷָ��Dao
 * ��decreaseItem�в������뱾����
 * service��
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

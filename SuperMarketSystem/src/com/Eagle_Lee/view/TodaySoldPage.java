package com.Eagle_Lee.view;

import java.util.ArrayList;

import com.Eagle_Lee.dao.SalesmanManageDao;
import com.Eagle_Lee.dao.TodaySoldListDao;
import com.Eagle_Lee.domain.BuyGoods;
import com.Eagle_Lee.domain.Salesman;

/**
 *  * ��Ʒά��>>��Ʒ����>>��ѯ������Ʒ���� ����
 * @author Eagle_Lee
 *
 */

public class TodaySoldPage{
	
	public void showTodaySold() { 
		System.out.println("ִ���г�����������Ʒ�б������");
		
			SalesmanManageDao salesmanManageDao= new SalesmanManageDao();
			ArrayList<BuyGoods> list=new ArrayList<BuyGoods>();
			System.out.println("�����۳���Ʒ��");
			System.out.println("��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t����\t\t��ע");
			//�����ѯ����������Ʒ�б���   
			TodaySoldListDao tdListDao =new TodaySoldListDao();
			list=tdListDao.details();
			//�����ѯ���
			for (BuyGoods buyGoods : list) {
				if (buyGoods.getGoods().getNum()<10) {
					System.out.print(buyGoods.getGoods().getName());
					System.out.print("\t\t"+buyGoods.getGoods().getPrice());
					System.out.print("\t\t"+buyGoods.getGoods().getNum());
					System.out.print("\t\t"+buyGoods.getNumber());
					System.out.println("\t\t"+"*����Ʒ����10��");
				}
			}
			if (list.isEmpty()) {
				System.out.println("����û��������ƷӴ~");
			}
		
	}
	
	
}

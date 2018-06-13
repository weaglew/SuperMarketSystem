package com.Eagle_Lee.view;
/**
 * ��ʾ��ǰ�˵�view��
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
		salesmanDao=new SalesmanDao();   //�ǵ�newӴ
		goodsDao=new GoodsDao();
	} 
	
	public void showPayPage() {
		ArrayList<BuyGoods> goodslist =new ArrayList<BuyGoods>();//����Ҫ��Ĳ�Ʒ�ļ���
		ByNewing byNewing= new ByNewing();
		do {
			Goods goods =new Goods();  //�û���Ҫ�������Ʒ��Ϣ

			System.out.println("1.�������");
			System.out.println();
			System.out.println("��������Ʒ�ؼ��֣�");
			//�����ѯ����
			String name ;
			ArrayList<Goods>  list; //����list��Ϊ��list ��ģ����ѯ��Ľ����
			do {
				name =scanner.next();
				list=salesmanDao.selectGoods(name);
				//System.err.println("..."+list);
				if (list.size()==0) {
					System.out.println("���޸���Ʒ�����������룺");
				}else {
					break;
				}
			} while (true);
			System.out.println("��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t");
			//�������ģ����ѯ���
			for (Goods goods2 : list) {
				System.out.print(goods2.getName());
				System.out.print("\t\t"+goods2.getPrice());
				System.out.println("\t\t"+goods2.getNum());
			}
			
			System.out.println("��ѡ����Ʒ��");
			String name1=scanner.next();
            goods.setName(name1);
			System.out.println("�����빺��������");
			String num;
			
			//���ղ��½���goods�������۸�Ϳ��
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
				if (Integer.parseInt(num)>goods.getNum()) {  //goodsĬ�������һ������
					System.out.println("\t\t\t��治�㣬����������");
					System.out.println();
					return;
					
				}else {
					break;
				}
			} while (true);
			
			BuyGoods buyGoods=new BuyGoods(goods,Integer.parseInt(num));
			goodslist.add(buyGoods);//��ÿһ��goods���󶼴���goodslist��  ---//���յ�һ��ѡ�õ���Ʒ��
			
			System.out.println("�Ƿ����(y/n)��");
			String sure=scanner.next();
			 
			if (sure.equals("y")) {
				
			} else {
				//�����n �����ܼ�totalPrice
				//
				System.out.println("�ܼƣ�");
				double sumGoods=0;
				for (BuyGoods goods4 : goodslist) {  //goods4����ÿ��goodslist�������Ԫ�أ�ѭ��һ�δ���һ��Ԫ��
					sumGoods=sumGoods+goods4.getGoods().getPrice()*goods4.getNumber();
				}
				boolean boo=byNewing.checkBuy(goodslist);//���ٿ��
				if(boo){
					System.out.println("�����ٳɹ�");
					System.out.println(sumGoods);
				}else {
					System.out.println("��治�㣬���������������");
				}
				/***************************************************/
				
				System.out.println("������ʵ�ʽ��ѽ�");
				String money;
				
				//������Ǯ���
				do {
					money=scanner.next();
					if (Double.parseDouble(money)>=sumGoods) {
						break;
					}else {
						System.out.println("Ǯ������~ ���õ㣿");
						
					}
				} while (true);
				System.out.println("��Ǯ��");
				double extra=Double.parseDouble(money)-sumGoods;
				System.out.println(extra);
				System.out.println("лл����");
				
				return;
			}
			
		} while (true);
	}
	
	
	
}

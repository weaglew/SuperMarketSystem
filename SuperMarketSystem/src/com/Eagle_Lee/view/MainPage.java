
/**
 * com.Eagle_Lee.dao �����õ������еĳ�������
 * com.Eagle_Lee.domain �����õ���JavaBean ���������ӿ�
 * com.Eagle_Lee.service �����õ��Ƕ���ķ�������
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

//ϵͳ��ʼ����
public class MainPage {
	
	private  Scanner scanner;
	
	public MainPage(){
		scanner=new Scanner(System.in);                //���캯�� scanner ---�����û�����
	}
	
	         /**ϵͳ������**/
	public  void showMainPage() {
	              
	    while(true){                                   //whileѭʹ��һ������һ���˺�
		System.out.println("****************************************");
		System.out.println("\n\t\t1.��Ʒά��\n");
		System.out.println("\t\t2.ǰ̨����\n");
		System.out.println("\t\t3.��Ʒ����\n");
		System.out.println("****************************************");
		System.out.println("��ѡ��,�������ֻ��߰�0�˳���");
		
														//ѡ�������뺯��
		String next =scanner.next();
		//�ж��������ʲô ������������ֽ�����Ӧ��ģ��
		if (next.equals("1")) {
			
		    maintainItem();                             //��Ʒά��ģ��
		} else if (next.equals("2")) {
			System.out.println("ִ��ǰ̨����\n\n");
	        moneyCollection();                          //ǰ̨����ģ��
		}else if(next.equals("3")){
			System.out.println("ִ����Ʒ����\n\n");
			itemManagement();                           //��Ʒ����ģ��	
		}else if (next.equals("0")) {
			System.out.println("лл����ʹ�ã�");
			System.exit(0);
		} else {
			System.err.println("���벻�ϸ�����������");
			
		}
		
	    }
		
		
    }
	        /**��Ʒά��ģ�����**/
	 public void maintainItem(){
		 while (true) {
			 System.out.println("ִ����ʾ��Ʒά���˵�");
	    	 System.out.println("�̳��������ϵͳ>>��Ʒά��");
	    	 System.out.println("****************************************");
	    	 System.out.println("\n\t\t1.�����Ʒ\n");
			 System.out.println("\t\t2.������Ʒ\n");
			 System.out.println("\t\t3.ɾ����Ʒ\n");
			 System.out.println("\t\t4.��ʾ������Ʒ\n");
			 System.out.println("\t\t5.��ѯ��Ʒ\n");
			 System.out.println("****************************************");
			 System.out.println("��ѡ��,�������ֻ��߰�0������һ���˵���");
			 String flag=scanner.next();
			 
			 switch (Integer.parseInt(flag)) {
     			case 1:
     				 	addGoods();    					//�����Ʒ
				 break;
     			case 2: 
     					changeGoods(); 				   //�޸���Ʒ
				 break;
     			case 3:
     					deleteGoods();                 //ɾ����Ʒ
   				 break;
     			case 4:
        			    showAllItem();                //��ʾ������Ʒ
   				 break;
     			case 5:
        			    selectItem();  				  //��ѯ��Ʒ
   				 break; 
			default:
				showMainPage();   					 //�ܷ��ص�showMainPage();
				                   
				
			}
	//ʵ��		 			
		}
	     }
	
	        /**��Ʒά��>>�����Ʒ����**/
	public void addGoods() {
		System.out.println("��ѡ��,��������1ȷ�Ͻ��������Ʒ��������0������һ���˵���");
		String sure=scanner.next();
		 GoodsDao goodsDao=new GoodsDao();   //���� 
		 do {
			 
			 if (sure.equals("1")) {
					System.out.println("ִ�������Ʒ������");
					System.out.println("�����Ʒ���ƣ�");
					String name=scanner.next();
					System.out.println("�����Ʒ�۸�");
					String price=scanner.next();
					System.out.println("�����Ʒ������");
					String num=scanner.next();
					Goods goods=new Goods(name,Double.parseDouble(price),Integer.parseInt(num),null);     //ʵ��������
					boolean b=goodsDao.addGoods(goods);
					
					System.out.println("�Ƿ����(y/n):");			
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

	
	       /**��Ʒά��>>������Ʒ����**/
	public void changeGoods() {
		
		System.out.println("��ѡ��,��������2ȷ�Ͻ��и�����Ʒ��������0������һ���˵���");
		String sure=scanner.next();
		 GoodsDao goodsDao=new GoodsDao();   					//ʵ����
		 do {
			 Goods goods=new Goods();
			 
			 if (sure.equals("2")) {
					System.out.println("ִ�и�����Ʒ������");
					System.out.println("���������Ʒ���ƣ�");
					String name=scanner.next();
					Goods goods2=goodsDao.selectGoods(name);  //��ѯ��Ʒ���
				        
					        /**����������Ʒ���Ʋ�����**/
					if (goods2!=null) {    					 //goods2����һ����ѯ��Ʒ�Ľ��
						System.out.println("��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����");
						System.out.print(goods2.getName()+"\t"+"\t");
						System.out.print(goods2.getPrice()+"\t"+"\t");
						System.out.print(goods2.getNum()+"\t"+"\t");
						System.out.println();
						System.out.println("ѡ����Ҫ���ĵ����ݣ�");
						System.out.println("1.������Ʒ���ƣ�");
						System.out.println("2.������Ʒ�۸�");
						System.out.println("3.������Ʒ������");
						/**
						 * ���� ��ʾ��Ʒ���ơ���Ʒ�۸���Ʒ����  
						 */
						String price=scanner.next();
						
						//��������123������Ӧ�ĺ���
						switch (Integer.parseInt(price)) {
						case 1:
							System.out.println("������Ҫ������Ʒ����");  //ֻ����Ʒ����
							String updateName=scanner.next();
							goods.setName(updateName);                 //�������� ��������Ĵ��뺯��
							goods.setNum(goods2.getNum());
							goods.setPrice(goods2.getPrice());
							boolean boo=goodsDao.updateGoods(goods2.getName(), goods);
							if (boo) {
								System.out.println("��Ʒ���Ƹ��³ɹ�");
							}else {
								System.out.println("��Ʒ���Ƹ���ʧ��");
							}
							break;
						case 2:
							System.out.println("������Ҫ������Ʒ�۸�");
							String updatePrice=scanner.next();
							goods.setName(goods2.getName());         //���˼۸� ��������Ĵ��뺯��
							goods.setNum(goods2.getNum());
							goods.setPrice(Double.parseDouble(updatePrice));  //����������תΪdouble���͸��ļ۸�
							boolean boo2=goodsDao.updateGoods(goods2.getName(), goods);
							if (boo2) {
								System.out.println("��Ʒ�۸���³ɹ�");
							}else {
								System.out.println("��Ʒ�۸����ʧ��");
							}
							break;
						case 3:
							System.out.println("������Ҫ������Ʒ����");
							String updateNum=scanner.next();
							goods.setName(goods2.getName());               //�������� ��������Ĵ��뺯��
							goods.setNum(Integer.parseInt(updateNum));
							goods.setPrice(goods2.getPrice());
							boolean boo3=goodsDao.updateGoods(goods2.getName(), goods);
							if (boo3) {
								System.out.println("��Ʒ�������³ɹ�");
							}else {
								System.out.println("��Ʒ��������ʧ��");
							}
							break;

						default:
							break;
						}
						
						
						System.out.println("�Ƿ����(y/n):");			
					    String sc=scanner.next();
						 if (sc.equals("y")) {
							
						}else {
							return; 									 //������һ�����ô˺����ĺ���
						    
						}
					}else {
						System.out.println("��ѯ��Ʒ������");
						break;
					}
			}
			 else if (sure.equals("0")) {
				maintainItem();
				break;
			}else {
				System.out.println("��������������������");
				
				break;
			}
		} while (true);
	}
	
	
	      /**��Ʒά��>>ɾ����Ʒ����**/
	public void deleteGoods() {
		System.out.println("��ѡ��,��������3ȷ�Ͻ���ɾ����Ʒ��������0������һ���˵���");
		String sure=scanner.next();
		 GoodsDao goodsDao=new GoodsDao();   							//ʵ����
		 do {
			Goods goods=new Goods();
			if (sure.equals("3")) {
				System.out.println("ִ��ɾ����Ʒ����");
				System.out.println("���뽫Ҫɾ������Ʒ����");
				String name=scanner.next();
				Goods goods3=goodsDao.selectGoods(name);
				     /*����������Ʒ������*/
				if (goods3!=null) {
					System.out.println("��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����");
					System.out.print(goods3.getName()+"\t"+"\t");
					System.out.print(goods3.getPrice()+"\t"+"\t");
					System.out.print(goods3.getNum()+"\t"+"\t");
					System.out.println();
					System.out.println("�Ƿ�ȷ��Ҫɾ������Ʒ(y/n)��:");
					String sure2 =scanner.next();
					if (sure2.equals("y")) {
						boolean boo=goodsDao.deleteGood(name, goods3);
						if (boo) {
							System.out.println("����Ʒ��ɾ��");
						} else {
							System.out.println("ɾ������ʧ�ܣ������²���");
							return;
						}
				
					} else{
						return;
					}
					

				}else {
				System.out.println("���������Ʒ������");
					break;
				}
				System.out.println("�Ƿ����(y/n)��");
				String sure3=scanner.next();
				if (sure3.equals("y")) {
					
				} else {
					return;
				}
			}else if(sure.equals("0")){
				maintainItem();
				break;
			}else {
				System.out.println("��������������������");
				break;
			}
		} while(true);
	}

		 /**��Ʒά��>>��ʾ������Ʒ����**/
    public void showAllItem() {
    	System.out.println("��ѡ��,��������4ȷ�Ͻ�����ʾ������Ʒ��������0������һ���˵���");
		String sure=scanner.next();
		GoodsDao goodsDao=new GoodsDao(); 
		/*������ʾ������Ʒ�����������Ǽ��Ͻ�� ���ü����������������ս��*/
		ArrayList<Goods> list=goodsDao.showGoods();
		do {
			if (sure.equals("4")) {
				System.out.println("��ʾ������Ʒ");
				System.out.println("��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע");
				/*��foreachѭ������list�ڵ����ݽ��*/
				for (Goods goods : list) {
					System.out.println(goods.getName()+"\t\t"+goods.getPrice()+"\t\t"+goods.getNum()+"\t\t"+goods.getTips());
				}
				
				break;
			} else if(sure.equals("0")){
				return;
			}else {
				System.out.println("��������������������");
				break;
			}
		} while (true);
	 
	}

    	/**��Ʒά��>>��ѯ��Ʒ����**/	
    public void selectItem() {
    	System.out.println("��ѡ��,��������5ȷ�Ͻ�����ʾ������Ʒ��������0������һ���˵���");
		String sure=scanner.next();
		GoodsDao goodsDao=new GoodsDao(); 
		
		do {
			if (sure.equals("5")) {
				System.out.println("ִ�в�ѯ��Ʒ����");
				System.out.println();
				System.out.println("1.����Ʒ���������ѯ");
				System.out.println("2.����Ʒ�۸������ѯ");
				System.out.println("3.����ؼ��ֲ�ѯ��Ʒ");
				System.out.println("��ѡ���������ֻ�0������һ���˵���");
				/*���밴������ѯ����*/
				
				String way=scanner.next();
				do {
					if (way.equals("1")) {
						//ִ�а���Ʒ���������ѯ
						ArrayList<Goods> list = goodsDao.numUpSort();
						for (Goods goods : list) {
							System.out.println(goods.getName());
							System.out.println(goods.getPrice());
							System.out.println(goods.getNum());
							
						}
						break;
					} else if(way.equals("2")){
						//ִ�а���Ʒ�۸������ѯ
						ArrayList<Goods> list=goodsDao.priceUpSort();
						for (Goods goods : list) {
							System.out.println(goods.getName());
							System.out.println(goods.getPrice());
							System.out.println(goods.getNum());
						}
						break;
					}else if(way.equals("3")){
						//ִ�йؼ��ֲ�ѯ��Ʒ
						System.out.println("������ؼ��֣�");
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
				System.out.println("�������������������룺");
				return;
				
			}
		} while (true);
	}
    
        /**ǰ̨����ģ�����**/
    public void moneyCollection() {
    	Salesman salesman=new Salesman();
    	SalesmanDao salesmanDao=new SalesmanDao();
    	UserLoginPage userLoginPage=new UserLoginPage();
		System.out.print("��ӭʹ���̳��������ϵͳ");
		System.out.println();
    	do {
    		System.out.println("1.��¼ϵͳ");
    		System.out.println("2.�˳�");
    		System.out.println("****************************************");
    		System.out.println("��ѡ���������֣�");
    		String sure=scanner.next();
    		if (sure.equals("1")) {
    			userLoginPage.showLoginPage();
    			//�����¼ϵͳ����login()�Ľ��溯��;
    			break;
    		} else if(sure.equals("2")){
    			//�˳�ϵͳ
    			return;
    		}else {
    			System.err.println("���������������������룺");
    			return;
    		}
		} while (true);
	}
    
    	/**��Ʒ����ģ�����**/
    public void itemManagement() {
    	SalesmanManagePage salesmanManagePage =new SalesmanManagePage();
    	TodaySoldPage tdSoldPage=new TodaySoldPage();
    	
    	System.out.println("�̳��������ϵͳ>>��Ʒ����");
    	do {
			
    		System.out.println("****************************************");
    		System.out.println();
    		System.out.println("\t\t1.�г�����������Ʒ�б�");
    		System.out.println("\t\t2.�ۻ�Ա����");
    		System.out.println();
    		System.out.println("****************************************");
    		System.out.println("��ѡ���������ֻ��߰�0������һ���˵���");
    		String sure=scanner.next();
    		if (sure.equals("1")) {
    			//�����г�����������Ʒ�б������� showListTodayGoods();
    		    tdSoldPage.showTodaySold();
    			break;
    		}else if (sure.equals("2")) {
    			//�ۻ�Ա����  ����//showSalesmanManage();//�ۻ�Ա�������
				salesmanManagePage.showSalesmanManage();
    			break;
			}else if (sure.equals("0")) {
				showMainPage();
			}
    		else {
				System.out.println("����������������������");
				return;
			}
		} while (true);
	}
    
   
    
    public static void main(String[] args) {
		new MainPage().showMainPage();
	}
}




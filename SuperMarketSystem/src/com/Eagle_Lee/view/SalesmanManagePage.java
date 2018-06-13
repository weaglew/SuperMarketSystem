package com.Eagle_Lee.view;

/**
 * ��Ʒά��>>��Ʒ����>>�ۻ�Ա���� ����
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

	/*ȫ�ֱ���*/
	Scanner scanner;
	Salesman salesman;
	
	/*���캯��*/
	public  SalesmanManagePage() {
	      scanner=new Scanner(System.in);
	      salesman=new Salesman();
	}
	
	/*��ʾ��¼���溯��**/
	public void showSalesmanManage() {
		while (true) {
			System.out.println("ִ����ʾ��Ʒά���˵�");
			System.out.println("�̳��������ϵͳ>>��Ʒ����>>�ۻ�Ա����");
			System.out.println("****************************************");
			System.out.println("\n\t\t1.����ۻ�Ա\n");
			System.out.println("\t\t2.�����ۻ�Ա\n");
			System.out.println("\t\t3.ɾ���ۻ�Ա\n");
			System.out.println("\t\t4.��ʾ�����ۻ�Ա\n");
			System.out.println("\t\t5.��ѯ�ۻ�Ա\n");
			System.out.println("****************************************");
			System.out.println("��ѡ��,�������ֻ��߰�0������һ���˵���");
			String flag=scanner.next();
			
			switch (Integer.parseInt(flag)) {
			case 1:
			    addSalesman();    //����ۻ�Ա
				break;
			case 2: 
				changeSalesman();  //�����ۻ�Ա
				break;
			case 3:
				deleteSalesman();  //ɾ���ۻ�Ա
				break;
			case 4:
				showAllSalesman();  //��ʾ�����ۻ�Ա
				break;
			case 5:
				selectSalesman();   //��ѯ�ۻ�Ա
				break; 
			default:
				return;           //�ܷ��ص�();
				
			}
	   }
	}
	
	/**
	 * ��Ʒ����>>�ۻ�Ա���� >>����ۻ�Ա ����
	 */
	public void addSalesman() {
		
		System.out.println("ִ������ۻ�Ա������");
		SalesmanManageDao salesmanManageDao = new SalesmanManageDao();
		do {
			Salesman salesman =new Salesman();
			System.out.println("����ۻ�Ա������");
			String name =scanner.next();
			System.out.println("����ۻ�Ա����");
			String passwd=scanner.next();
			/*���ε�ʵ��*/
			salesman.setName(name);
			salesman.setPasswd(passwd);
			/*����Dao����*/
			//salesmanManageDao.addSalesmanPer(salesman);
			/*�ж��Ƿ���ӳɹ�*/
			boolean boo=salesmanManageDao.addSalesmanPer(salesman);
			if (boo) {
				System.out.println("�ۻ�Ա"+name+"��ӳɹ�");
				System.out.println("�Ƿ����(y/n):");
				String sure =scanner.next();
				if (sure.equals("y")) {
					
				}else if (sure.equals("n")) {
					break;
				}else {
					System.out.println("����������������������");
					return;
				}
			}else {
				System.out.println("���ʧ�ܣ����������");
				return;
			}
	   } while (true);
				
	}

	/**
	 * ��Ʒ����>>�ۻ�Ա���� >>�����ۻ�Ա ����
	 */
	public void changeSalesman() {
		SalesmanManageDao salesmanManageDao =new SalesmanManageDao();
		System.out.println("ִ�и����ۻ�Ա������");
	do {
		
		System.out.println("������ĵ��ۻ�Ա������");
		System.out.println("�ۻ�Ա����");
		String name =scanner.next();
		//�ɴ�
		 
		Salesman salesman2=salesmanManageDao.selectSalesman(name);
      
		/**���������ۻ�Ա���Ʋ�����**/
		if (salesman2==null) {
			System.out.println("��ѯ���ۻ�Ա"+name+"�����ڣ�����������");
			break;
		}else {
			System.out.println("�ۻ�Ա����\t\t�ۻ�Ա����");
			System.out.print(salesman2.getName()+"\t"+"\t");
			System.out.print(salesman2.getPasswd()+"\t"+"\t");
			System.out.println();
			
			
			System.out.println("ѡ����Ҫ���ĵ����ݣ�");
			System.out.println("1.�����ۻ�Ա������");
			System.out.println("2.�����ۻ�Ա���룺");
			String sure=scanner.next();
			switch (Integer.parseInt(sure)) {
			case 1:
				System.out.println("���ۻ�Ա���Ƹ���Ϊ��");  //ֻ����Ʒ����
				String updateNString=scanner.next();
				salesman.setName(updateNString);
				salesman.setPasswd(salesman2.getPasswd());
				boolean boo =salesmanManageDao.updateSalesman(salesman2.getName(),salesman);
				if (boo) {
					System.out.println("�ۻ�Ա�����޸ĳɹ�");
				} else {
                   System.out.println("�ۻ�Ա�����޸�ʧ��");
				}
				break;
			case 2:
				System.out.println("���ۻ�Ա�������Ϊ��");
                String upasswd=scanner.next();
                salesman.setName(salesman2.getName());
                salesman.setPasswd(upasswd);
                boolean boo2 = salesmanManageDao.updateSalesman(salesman2.getName(), salesman);
                if (boo2) {
					System.out.println("�ۻ�Ա�����޸ĳɹ�");
				} else {
					System.out.println("�ۻ�Ա�����޸�ʧ��");
				}
                break;
                
            default:
				break;
			}
			
			System.out.println("�Ƿ����(y/n):");
			String sc=scanner.next();
			 if (sc.equals("y")) {
					
				}else {
					return;  //������һ�����ô˺����ĺ���
				    
				}
			
			
		  }
		 
	   } while (true);
	}

	/**
	 * ��Ʒ����>>�ۻ�Ա���� >>ɾ���ۻ�Ա ����
	 * */
	public void deleteSalesman() {
		SalesmanManageDao salesmanManageDao= new SalesmanManageDao();
		System.out.println("ִ��ɾ���ۻ�Ա������");
		do {
			System.out.println("���뽫Ҫɾ���ۻ�Ա��");
			String dname=scanner.next();
			Salesman salesman2=salesmanManageDao.selectSalesman(dname);
			      
			     /**���������ۻ�Ա���Ʋ�����**/
			if (salesman2==null) {
				System.out.println("��ѯ���ۻ�Ա"+dname+"�����ڣ�����������");
				break;
			}else {
				System.out.println("�ۻ�Ա����\t\t�ۻ�Ա����");
				System.out.print(salesman2.getName()+"\t"+"\t");
				System.out.print(salesman2.getPasswd()+"\t"+"\t");
				System.out.println();
				System.out.println("�Ƿ�ȷ��Ҫɾ��(y/n)��");
				String su=scanner.next();
				if (su.equals("y")) {
					
					boolean boo=salesmanManageDao.deleteMan(salesman2.getName(), salesman);
					if (boo) {
						System.out.println("�ۻ�Ա"+dname+"�Ѿ��ɹ�ɾ��");
					}
				} else {
					return;
				}
				System.out.println("�Ƿ����(y/n)?:");
				String sure3=scanner.next();
				if (sure3.equals("y")) {
					
				} else {
					return;
				}
			}
		} while (true);
	}
 
	/**
	 * ��Ʒ����>>�ۻ�Ա���� >>��ʾ�����ۻ�Ա ����
	 */

	public void showAllSalesman() {
		SalesmanManageDao salesmanManageDao= new SalesmanManageDao();
		ArrayList<Salesman> list =salesmanManageDao.allMembers();
		//����newһ���µĵ�ַ ��ΪֻҪ����һ������list�����վͺ���
		System.out.println("ִ����ʾ�����ۻ�Ա������");
		do {
			System.out.println("��ʾ�����ۻ�Ա");
			System.out.println("�ۻ�Ա����\t\t�ۻ�Ա����\t\t");
			/*��foreachѭ������list�ڵ����ݽ��*/
			for (Salesman salesman: list) {
				System.out.println(salesman.getName()+"\t\t\t"+salesman.getPasswd()+"\t\t");
			}
			break;
		} while (true);
		
		
	}

    /**
     * ��Ʒ����>>�ۻ�Ա���� >>��ѯ�ۻ�Ա ����
     */
	 
	public void selectSalesman() {
		do {
			SalesmanManageDao salesmanManageDao =new SalesmanManageDao();
			System.out.println("ִ�в�ѯ�ۻ�Ա����");
			System.out.println("����Ҫ��ѯ���ۻ�Ա�����ؼ���");
			String sc=scanner.next();
			ArrayList<Salesman> list=salesmanManageDao.keyWdSelect(sc);
			System.out.println("�ۻ�Ա����\t\t�ۻ�Ա����\t\t");
			for (Salesman salesman : list) {
				System.out.print(salesman.getName()+"\t\t\t"+salesman.getPasswd());
				System.out.println();
			}
			System.out.println("�Ƿ����(y/n):��");
			String sur=scanner.next();
			if (sur.equals("y")) {
				
			}else {
				break;
			}
		} while (true);
	}
  
   
	
	

}

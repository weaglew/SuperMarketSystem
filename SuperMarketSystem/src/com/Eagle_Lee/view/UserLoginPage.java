package com.Eagle_Lee.view;

import java.util.Scanner;

import com.Eagle_Lee.dao.SalesmanDao;
import com.Eagle_Lee.domain.Salesman;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserLoginPage {
	/**��������ͬʱΪ���캯�� ֻ��new��ʱ������һ�Σ�
	 * ����Ϊ�������ֵĺ���ʱ ���Ե��÷���**/
	Scanner sc;       //ȫ�ֱ���sc
	Salesman salesman;//ȫ�ֱ���salesman
	
	    /*���캯��*/
	public  UserLoginPage() {
		sc=new Scanner(System.in);
		salesman= new Salesman();
	}
	/*��ʾ��¼���溯��*/
	public void showLoginPage() {
		SalesmanDao salesmanDao=new SalesmanDao();
		int count=0;
		while (true) {
			
			System.out.println("�������û�����");
			String uname=sc.next();
			System.out.println("���������룺");
			String upasswd= sc.next();
			salesman.setName(uname);    //����salesman�������
			salesman.setPasswd(upasswd);//����salesman�������
			//�����ò�����salesman�����װ��login����
			//�жϵ�¼���
			boolean boo=salesmanDao.login(salesman);
			
			if(boo){
				PayPage page =new PayPage();
				page.showPayPage();
				break;
			}else {
				count++;
				System.err.println("��ʣ"+(3-count)+"��");
				System.err.println("�û��������벻ƥ��");
				if (count==3) {
					return;
				} 
			}
			
		}
	}			
}
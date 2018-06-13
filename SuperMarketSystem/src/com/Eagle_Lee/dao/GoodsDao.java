package com.Eagle_Lee.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.Eagle_Lee.domain.Goods;
import com.Eagle_Lee.view.*;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * ���ݿ���ɾ��ʹ��exeupdate();����exe��query();����resultset��������
 * @author Eagle_Lee
 *
 */


public class GoodsDao {
	/*Oracle:
	 * public void test() throws Exception{
		//�������ݿ�
		//1.��������
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2.����URL
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		//3.���ӵ��û���������
		String loginName="scott";
		String loginPwd="tiger";
		//4.�������ݿ�
		Connection con=DriverManager.getConnection(url,loginName,loginPwd);
	    System.out.println("con"+con);
	    
	}*/
	
	/**
	 * ��ȡ���ݿ���Ʒ���id
	 * @return
	 */
	public int getMaxID() {
		JdbcTool jbdcTool=new JdbcTool();
		Connection con=jbdcTool.getConnection();
		String sqlString="select  MAX(G_ID) from GOODS";
		int maxID = 0;
		ResultSet resultSet=null;
		PreparedStatement statement=null;
		try {
		    statement=con.prepareStatement(sqlString);
			resultSet=statement.executeQuery();
			while (resultSet.next()) {
				maxID=resultSet.getInt(1);
				
			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jbdcTool.close(resultSet,statement,con);  //���Ϸ�ʵ������һ�²���
		}
		return maxID;
		
	}
	
	/* ʵ�������Ʒ���۸������ĺ���maintainGoods*/
//	public void maintainGoods() throws Exception{
//		
//		Scanner numSure;
//		System.out.println("��ѡ����������1�����Ʒ���߰�0������һ���˵���");
//	
//		
//		//��ʾ�ڿ���̨�Ľ�������
//		while(true){
//			numSure= new Scanner(System.in);     //numSure--��ȡ�û�����
//			String numSure1 = numSure.nextLine();         //numSure1--ȷ���û�����
//			
//			if(numSure1.equals("1")){
////				addGoods()();              //�����Ʒ����
//				System.out.println("1111");
//			}else if(numSure1.equals("0")){
//				man.maintainItem();   //�����ϼ�����
//			}else {
//				System.out.println("���벻�ϸ�����������");		
//			}
//			
//		}	
//	}
	/**
	 * changeGoods()---ʵ����Ʒ����
	 * @throws Exception 
	 */
	
	                   /*������Ʒ����*/
	//String name Ҫ�޸�������; Goods goods�Ѿ���װ�õ��޸�����
	public boolean  updateGoods(String name,Goods goods) {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement ps=null;
		String sql="update GOODS set G_NAME = ?,G_PRICE=?, G_NUM=? where G_NAME=?";//where �����н����޸�
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, goods.getName());
			ps.setDouble(2,goods.getPrice());
			ps.setInt(3, goods.getNum());
			ps.setString(4, name);
			int rows=ps.executeUpdate();
			if (rows!=0) {
				return true;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			jdbcTool.close(null,ps,con);
		}
		return false;
	}
	
	              /*������Ʒ��ѯ*/
	public Goods selectGoods(String name) {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement sta=null;
		ResultSet resultSet=null;
		Goods goods =null;
	    String sql="select * from GOODS where G_NAME=? ";
		try {
		    
			sta=con.prepareStatement(sql);
			sta.setString(1, name);
			resultSet=sta.executeQuery();
			while (resultSet.next()) {
                  goods=new Goods();
                  goods.setId(resultSet.getInt(1));
                  goods.setName(resultSet.getString(2));
                  goods.setPrice(resultSet.getDouble(3));
                  goods.setNum(resultSet.getInt(4));
                  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(resultSet,sta,con);
		}
		return goods;
	
	}
	/*��Ʒ������Ʒ���������ѯ*/
	public ArrayList<Goods> numUpSort() {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement sta=null;
		ResultSet resultSet=null;
		String sql="select * from GOODS order by G_NUM ";  //order by ���� Ĭ������
		ArrayList<Goods> list=new ArrayList<Goods>();//���ղ�ѯ���������
		try {
			sta=con.prepareStatement(sql);//ִ��sql���
			resultSet=sta.executeQuery(); //���õ������ݴ��뼯��
			while (resultSet.next()) {    //�������е����ݴ������
				Goods goods=new Goods();         
				goods.setName(resultSet.getString(2));//DB�еڶ��ж�Ӧ������Ʒ����
				goods.setPrice(resultSet.getDouble(3));
				goods.setNum(resultSet.getInt(4));
				list.add(goods);       //��һ������(goods)����list�� �����ж���������ѭ��
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(resultSet,sta,con);
		}
		return list;
	}

	/*��Ʒ������Ʒ�۸������ѯ*/
	public ArrayList<Goods> priceUpSort() {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement sta=null;
		ResultSet resultSet=null;
		String sql="select * from GOODS order by G_PRICE ";  //order by ���� Ĭ������   ;ORDER BY _column1, _column2 DESC; /* _column1����_column2���� */
		ArrayList<Goods> list=new ArrayList<Goods>();//���ղ�ѯ���������
		try {
			sta=con.prepareStatement(sql);//ִ��sql���
			resultSet=sta.executeQuery(); //���õ������ݴ��뼯��
			while (resultSet.next()) {    //�������е����ݴ������
				Goods goods=new Goods();         
				goods.setName(resultSet.getString(2));//DB�еڶ��ж�Ӧ������Ʒ����
				goods.setPrice(resultSet.getDouble(3));
				goods.setNum(resultSet.getInt(4));
				list.add(goods);       //��һ������(goods)����list�� �����ж���������ѭ��
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(resultSet,sta,con);
		}
		return list;
	}
	
					/**ʵ��ģ����ѯ**/
	public ArrayList<Goods> keywdSort(String name) {
		ArrayList<Goods> list= new ArrayList<Goods>();
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement sta=null;
		ResultSet resultSet=null;
		String sql="select * from GOODS where G_NAME like ? ";  //like ��ʾģ����ѯ���ڱ�����ӵ��ַ����Ǳ�ı�����%��ʾ�����ַ� ��_�������ַ���
		//System.err.println(name);
		try {
			sta=con.prepareStatement(sql);
			sta.setString(1, "%"+name+"%");
			resultSet=sta.executeQuery();
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getInt(1));               //�Ȳ�ѯ����Ʒid��Ȼ��ȥ������Ȼ���ڴ������ݿ�
				goods.setName(resultSet.getString(2));
				goods.setPrice(resultSet.getDouble(3));
				goods.setNum(resultSet.getInt(4));
				//System.out.println(resultSet.getInt(4));
				list.add(goods);
				//System.err.println(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(resultSet,sta,con);
		}
		return list;
	}
	
	
	/**
	 * selectItem()---ʵ�ֲ�ѯ��Ʒ
	 * @return
	 */
//	public ArrayList<Goods> select() {
//		JdbcTool jdbcTool = new JdbcTool();
//		Connection con=jdbcTool.getConnection();
//		PreparedStatement sta=null;
//		ResultSet resultSet=null;
//		return 
//		
//	    String sql="select * from GOODS ";
//	}
	
	/**
	 * showAllGoods()---ʵ����ʾ������Ʒ
	 *@return list
	 */
	
		/*�����ݿ�õ�GOODS���е�ȫ������*/
	public ArrayList<Goods> showGoods() {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement sta=null;
		ResultSet resultSet=null;
		
	    String sql="select * from GOODS ";
	    ArrayList<Goods> list=new ArrayList<Goods>();
		try{    
			sta=con.prepareStatement(sql);
			
			resultSet=sta.executeQuery();
			while (resultSet.next()) {
                  Goods goods=new Goods();
                  goods.setId(resultSet.getInt(1));
                  goods.setName(resultSet.getString(2));
                  goods.setPrice(resultSet.getDouble(3));
                  goods.setNum(resultSet.getInt(4));
                  list.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(resultSet,sta,con);
		}
		return list;
	
	}
	
	/**
	 * deleteGoods()---ʵ����Ʒɾ��
	 */
					/*������Ʒɾ��*/
	public boolean deleteGood(String name,Goods goods) {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement pre=null;
		String sql="delete from GOODS where G_NAME=?";//where ������ɾ�� ��drop��ɾ�����ű�
		try {
			pre=con.prepareStatement(sql);
			pre.setString(1, name);
			int rows=pre.executeUpdate();
			if (rows!=0) {
				return true;
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(null,pre,con);
		}
		
		return false;
	}
	
	/**
	 * addGoods()---ʵ����Ʒ���
	 * @throws Exception 
	 */
	public boolean addGoods(Goods goods) {
		JdbcTool jdbcTool=new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement sta=null;
		String sql="insert into GOODS values(?,?,?,?)";
		try {
			sta=con.prepareStatement(sql);
			sta.setInt(1, getMaxID()+1);
			sta.setString(2, goods.getName());
			sta.setDouble(3, goods.getPrice());
			sta.setInt(4, goods.getNum());
			int rows=sta.executeUpdate();
			if (rows>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(null,sta,con );
		}
		return false;
		
//		int itemPrice=Integer.parseInt(item.nextLine());
//		System.out.println("�����Ʒ������");
//		//�����û��������Ʒ����
//		int itemNum=Integer.parseInt(item.next());
//		System.out.println("�Ƿ����(y/n)��");
//		String sure = item.nextLine();
//		if(sure.equals("y")){
//			//��itemName/itemPrice/itemNum�������ݿ�
//			System.out.println("22222");
//		}else {// �������n������Ʒά������
//			try {
//				man.maintainItem();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	
		
	
	
}

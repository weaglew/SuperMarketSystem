package com.Eagle_Lee.dao;
/**
 * ֻ�����ݿ��в�����Dao�� �������κ�ǰ�˵Ķ���
 */
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sun.misc.Signal;

import com.Eagle_Lee.domain.BuyGoods;
import com.Eagle_Lee.domain.Goods;
import com.Eagle_Lee.domain.Salesman;
import com.Eagle_Lee.view.UserLoginPage;

public class SalesmanDao {

	/* ģ���>>ʵ�ֵ�¼�ж�*/
	public boolean login(Salesman salesman) {
		JdbcTool jbdcTool=new JdbcTool();
		Connection con=jbdcTool.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String sql="select * from SALESMAN where S_NAME = ?";
		//�õ����ݿ��ѯ���
		//�õ��Ѿ���װ�õ�salesman�������
		String uname=salesman.getName();
		String passwd=salesman.getPasswd();
	   try {
			preparedStatement=con.prepareStatement(sql);
		    preparedStatement.setString(1, uname);
		    resultSet=preparedStatement.executeQuery();
		    while (resultSet.next()) {
		    	    if (passwd.equals(resultSet.getString(3))) {
						return true;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jbdcTool.close(resultSet, preparedStatement, con);
		}
		
	     return false;
		
	}
	
	/*ʵ����Ʒģ����ѯ*/
	public ArrayList<Goods> selectGoods(String name) {
		GoodsDao goodsDao=new GoodsDao();
		ArrayList<Goods> list=goodsDao.keywdSort(name);
		return list;
	}
	
	
	/*ʵ�ּ��ٿ��*/
	public boolean decreaseItem(BuyGoods buyGoods,Salesman salesman) {
	    JdbcTool jdbcTool=new JdbcTool();
	    Connection con=jdbcTool.getConnection();
	    try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//�ر��Զ��ύ
	    finally{
	    	jdbcTool.close(null, con);
	    }
	    
	    PreparedStatement preparedStatement=null;
	    
	    String sql="update GOODS set G_NUM=? where G_NAME=?";
	    
	    PreparedStatement pre2 = null;
	    
	    String sql2="insert into SOLD_OUT_LIST values(?,?,?)";
	    
	    PreparedStatement pre3=null;
	    
	    
	    String sql3="insert into SOLD_DETAIL_LIST(SOLD_ID,G_ID,SALE_QUNTITY_TODAY) values(?,?,?) ";
	    
		try {
			preparedStatement=con.prepareStatement(sql);
			
		    //���������
			preparedStatement.setInt(1, buyGoods.getGoods().getNum()-buyGoods.getNumber());
		    preparedStatement.setString(2, buyGoods.getGoods().getName());
		    /*****��ע��������ִ�м��ٿ������sql***********/
		    pre2=con.prepareStatement(sql2);
		    int temp=getMaxIdSold()+1;//������ʱ���������ڴ�����
		   
		    pre2.setInt(1, temp);//pre�ǰ�����ũд��values���ʺ�˳��  res�ǰ������ݿ������
		    pre2.setInt(2, salesman.getId());
		    /*��ע���������趨ֵ���sql2*/
		    Date date = new Date();
		    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yy-MM-dd HH:mm:ss");//hh��12Сʱ
		    /*��ע�������ǽ�ʱ��涨��ʽ**/
		    pre2.setString(3, simpleDateFormat.format(date));
		   //�趨sql2��ʱ��
		    
		    
		    pre3=con.prepareStatement(sql3);
		    pre3.setInt(1, temp);
		    pre3.setInt(2, buyGoods.getGoods().getId());
		    pre3.setInt(3, buyGoods.getGoods().getNum());
		    /*��ע���������趨ֵ���sql3*/
			int rows=preparedStatement.executeUpdate();//�ı������
			
			int rowsSOL =pre2.executeUpdate();//Sold_out_list ��ִ�����
			
			int rowsSDT=pre3.executeUpdate();//SOLD_DETAIL_LIST ��ִ�����
			con.commit();//�����ύ
			if (rows>0&&rowsSDT>0&&rowsSOL>0) {
				return true;
			}else {
				con.rollback();//�����һ�����Ǵ���0��ҲҪ�ع�
			}
			/*��¼��Ʒ*/
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//��������Զ��ع�
		}
	    		
	    		
	    		
	    		
		return false;
		
	}
	
	/*��ȡ�������ID soldId  Ϊ��ȡ���������б�����¼*/
	public int getMaxIdSold() {
		JdbcTool jdbcTool=new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement pS=null;
		ResultSet rSet=null;
		String sql="select MAX(SOLD_ID) from SOLD_OUT_LIST ";
		int soldId=0;
		try {
			pS=con.prepareStatement(sql);
			rSet=pS.executeQuery();
			while (rSet.next()) {
				soldId = rSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(rSet,pS,con);
		}
		return soldId;
		
	}
	
}

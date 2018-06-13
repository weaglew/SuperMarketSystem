package com.Eagle_Lee.dao;
/**
 * 只和数据库有操作的Dao层 不能有任何前端的东西
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

	/* 模块二>>实现登录判断*/
	public boolean login(Salesman salesman) {
		JdbcTool jbdcTool=new JdbcTool();
		Connection con=jbdcTool.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String sql="select * from SALESMAN where S_NAME = ?";
		//得到数据库查询结果
		//得到已经封装好的salesman对象参数
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
	
	/*实现商品模糊查询*/
	public ArrayList<Goods> selectGoods(String name) {
		GoodsDao goodsDao=new GoodsDao();
		ArrayList<Goods> list=goodsDao.keywdSort(name);
		return list;
	}
	
	
	/*实现减少库存*/
	public boolean decreaseItem(BuyGoods buyGoods,Salesman salesman) {
	    JdbcTool jdbcTool=new JdbcTool();
	    Connection con=jdbcTool.getConnection();
	    try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//关闭自动提交
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
			
		    //减掉新买的
			preparedStatement.setInt(1, buyGoods.getGoods().getNum()-buyGoods.getNumber());
		    preparedStatement.setString(2, buyGoods.getGoods().getName());
		    /*****此注释往上是执行减少库存的语句sql***********/
		    pre2=con.prepareStatement(sql2);
		    int temp=getMaxIdSold()+1;//设置临时变量减少内存运行
		   
		    pre2.setInt(1, temp);//pre是按照码农写的values的问号顺序  res是按照数据库的列数
		    pre2.setInt(2, salesman.getId());
		    /*此注释往上是设定值语句sql2*/
		    Date date = new Date();
		    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yy-MM-dd HH:mm:ss");//hh是12小时
		    /*此注释往上是将时间规定格式**/
		    pre2.setString(3, simpleDateFormat.format(date));
		   //设定sql2的时间
		    
		    
		    pre3=con.prepareStatement(sql3);
		    pre3.setInt(1, temp);
		    pre3.setInt(2, buyGoods.getGoods().getId());
		    pre3.setInt(3, buyGoods.getGoods().getNum());
		    /*此注释往上是设定值语句sql3*/
			int rows=preparedStatement.executeUpdate();//改变的行数
			
			int rowsSOL =pre2.executeUpdate();//Sold_out_list 表执行语句
			
			int rowsSDT=pre3.executeUpdate();//SOLD_DETAIL_LIST 表执行语句
			con.commit();//事务提交
			if (rows>0&&rowsSDT>0&&rowsSOL>0) {
				return true;
			}else {
				con.rollback();//如果有一个不是大于0的也要回滚
			}
			/*记录商品*/
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//事务出错自动回滚
		}
	    		
	    		
	    		
	    		
		return false;
		
	}
	
	/*获取最大已售ID soldId  为获取今日所售列表做记录*/
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

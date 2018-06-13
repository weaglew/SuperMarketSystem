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
 * 数据库增删改使用exeupdate();查用exe…query();返回resultset集合数据
 * @author Eagle_Lee
 *
 */


public class GoodsDao {
	/*Oracle:
	 * public void test() throws Exception{
		//连接数据库
		//1.加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2.创建URL
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		//3.连接的用户名和密码
		String loginName="scott";
		String loginPwd="tiger";
		//4.连接数据库
		Connection con=DriverManager.getConnection(url,loginName,loginPwd);
	    System.out.println("con"+con);
	    
	}*/
	
	/**
	 * 获取数据库商品最大id
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
			jbdcTool.close(resultSet,statement,con);  //和上方实例化的一致才行
		}
		return maxID;
		
	}
	
	/* 实现添加商品、价格、数量的函数maintainGoods*/
//	public void maintainGoods() throws Exception{
//		
//		Scanner numSure;
//		System.out.println("请选择，输入数字1添加商品或者按0返回上一级菜单：");
//	
//		
//		//显示在控制台的交互内容
//		while(true){
//			numSure= new Scanner(System.in);     //numSure--读取用户输入
//			String numSure1 = numSure.nextLine();         //numSure1--确认用户输入
//			
//			if(numSure1.equals("1")){
////				addGoods()();              //添加商品函数
//				System.out.println("1111");
//			}else if(numSure1.equals("0")){
//				man.maintainItem();   //返回上级函数
//			}else {
//				System.out.println("输入不合格，请重新输入");		
//			}
//			
//		}	
//	}
	/**
	 * changeGoods()---实现商品更改
	 * @throws Exception 
	 */
	
	                   /*单个商品更改*/
	//String name 要修改是哪行; Goods goods已经封装好的修改内容
	public boolean  updateGoods(String name,Goods goods) {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement ps=null;
		String sql="update GOODS set G_NAME = ?,G_PRICE=?, G_NUM=? where G_NAME=?";//where 对哪行进行修改
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
	
	              /*单个商品查询*/
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
	/*商品集按商品数量升序查询*/
	public ArrayList<Goods> numUpSort() {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement sta=null;
		ResultSet resultSet=null;
		String sql="select * from GOODS order by G_NUM ";  //order by 列名 默认升序
		ArrayList<Goods> list=new ArrayList<Goods>();//接收查询结果的数组
		try {
			sta=con.prepareStatement(sql);//执行sql语句
			resultSet=sta.executeQuery(); //将得到的数据传入集合
			while (resultSet.next()) {    //将集合中的数据传入对象
				Goods goods=new Goods();         
				goods.setName(resultSet.getString(2));//DB中第二列对应的是商品名称
				goods.setPrice(resultSet.getDouble(3));
				goods.setNum(resultSet.getInt(4));
				list.add(goods);       //将一行数据(goods)传入list内 ，再判断条件继续循环
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(resultSet,sta,con);
		}
		return list;
	}

	/*商品集按商品价格升序查询*/
	public ArrayList<Goods> priceUpSort() {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement sta=null;
		ResultSet resultSet=null;
		String sql="select * from GOODS order by G_PRICE ";  //order by 列名 默认升序   ;ORDER BY _column1, _column2 DESC; /* _column1升序，_column2降序 */
		ArrayList<Goods> list=new ArrayList<Goods>();//接收查询结果的数组
		try {
			sta=con.prepareStatement(sql);//执行sql语句
			resultSet=sta.executeQuery(); //将得到的数据传入集合
			while (resultSet.next()) {    //将集合中的数据传入对象
				Goods goods=new Goods();         
				goods.setName(resultSet.getString(2));//DB中第二列对应的是商品名称
				goods.setPrice(resultSet.getDouble(3));
				goods.setNum(resultSet.getInt(4));
				list.add(goods);       //将一行数据(goods)传入list内 ，再判断条件继续循环
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(resultSet,sta,con);
		}
		return list;
	}
	
					/**实现模糊查询**/
	public ArrayList<Goods> keywdSort(String name) {
		ArrayList<Goods> list= new ArrayList<Goods>();
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement sta=null;
		ResultSet resultSet=null;
		String sql="select * from GOODS where G_NAME like ? ";  //like 表示模糊查询；在表名后加的字符串是表的别名；%表示任意字符 ；_代表单个字符；
		//System.err.println(name);
		try {
			sta=con.prepareStatement(sql);
			sta.setString(1, "%"+name+"%");
			resultSet=sta.executeQuery();
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getInt(1));               //先查询出商品id，然后去遍历，然后在传给数据库
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
	 * selectItem()---实现查询商品
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
	 * showAllGoods()---实现显示所有商品
	 *@return list
	 */
	
		/*从数据库得到GOODS表中的全部数据*/
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
	 * deleteGoods()---实现商品删除
	 */
					/*单个商品删除*/
	public boolean deleteGood(String name,Goods goods) {
		JdbcTool jdbcTool = new JdbcTool();
		Connection con=jdbcTool.getConnection();
		PreparedStatement pre=null;
		String sql="delete from GOODS where G_NAME=?";//where 对哪行删除 ；drop是删除整张表
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
	 * addGoods()---实现商品添加
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
//		System.out.println("添加商品数量：");
//		//接受用户输入的商品数量
//		int itemNum=Integer.parseInt(item.next());
//		System.out.println("是否继续(y/n)：");
//		String sure = item.nextLine();
//		if(sure.equals("y")){
//			//将itemName/itemPrice/itemNum加入数据库
//			System.out.println("22222");
//		}else {// 如果输入n返回商品维护界面
//			try {
//				man.maintainItem();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	
		
	
	
}

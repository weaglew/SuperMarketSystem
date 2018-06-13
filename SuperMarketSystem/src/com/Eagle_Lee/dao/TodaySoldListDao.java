package com.Eagle_Lee.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.Eagle_Lee.domain.BuyGoods;
import com.Eagle_Lee.domain.Goods;

/**
 * 实现显示今日购买商品的函数
 * @author Eagle_Lee
 *
 */
	public class TodaySoldListDao {
		public ArrayList<BuyGoods> details() {
			ArrayList<BuyGoods> list=new ArrayList<BuyGoods>();
		    JdbcTool jdbcTool=new JdbcTool();
		    Connection con=jdbcTool.getConnection();
		    ResultSet resultSet=null;
		    PreparedStatement ps = null;
//		    String sqlString="select G_NAME,G_PRICE,G_NUM,SUM(SALE_QUNTITY_TODAY)" +
//		    				"from GOODS g,SOLD_DETAIL_LIST s ,SOLD_OUT_LIST sl"+
//		    		        "where SOLD_DETAIL_LIST.G_ID=GOODS.G_ID and"+
//		    				"SOLD_OUT_LIST.G_ID=GOODS.G_ID and SOLD_OUT_LIST.SALE_DATE like ?"+
//		    		        "group by SOLD_DETAILS_LIST.G_ID";
		    // 少了链接 起别名  ;数据库里面会自己匹配相同的id连接 
		  
		    String sqlString="SELECT G_NAME,G_PRICE,G_NUM,SUM(SALE_QUNTITY_TODAY) FROM SOLD_DETAIL_LIST s,GOODS g,SOLD_OUT_LIST sl "
				+ "where s.G_ID=g.G_ID AND s.SOLD_ID=sl.SOLD_ID and sl.SALE_DATE like ? GROUP BY s.G_ID";
			try {
				 ps=con.prepareStatement(sqlString);

				Date date =new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
			    ps.setString(1, "%"+simpleDateFormat.format(date)+"%");
			    resultSet=ps.executeQuery();
			    
			    con.commit();
			    while (resultSet.next()) {
			    	
					BuyGoods buyGoods =new BuyGoods();
					Goods goods =new Goods();
					
					goods.setName(resultSet.getString(1));
					goods.setPrice(resultSet.getDouble(2));
					goods.setNum(resultSet.getInt(3));
					buyGoods.setGoods(goods);
				//	buyGoods.setNumber(resultSet.getInt(4));
				//不用加id在sql里多表链接
					list.add(buyGoods);
					/********注意最后把buygoods加进list去！！！！！*******/
				}
			    
			    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}finally{
				jdbcTool.close(resultSet,ps,con);
			}
			return list;
			
		}
}

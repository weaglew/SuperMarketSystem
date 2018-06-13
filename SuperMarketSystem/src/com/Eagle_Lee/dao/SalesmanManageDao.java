/**
 * ��Ʒ���� * ��Ʒά��>>��Ʒ����>>�ۻ�Ա����  ����ʵ�ֵײ�
 */
package com.Eagle_Lee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Eagle_Lee.domain.Goods;
import com.Eagle_Lee.domain.Salesman;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class SalesmanManageDao {

	//Salesman salesman=new Salesman();
	/**
	 *��ȡ�ۻ�Ա���ID 
	 * @return
	 */
	public int getMaxPerId() {
		JdbcTool jdbcTool =new JdbcTool();
		Connection connection = jdbcTool.getConnection();
		String sqlString="select max(S_ID) from SALESMAN";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		int SalesmanPerID=0;
		try {
			preparedStatement=connection.prepareStatement(sqlString);
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				SalesmanPerID = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcTool.close(resultSet, preparedStatement, connection);
		}
		
		return SalesmanPerID;
	}

	
	/**
	* ʵ������ۻ�Ա����
	 */
		 public boolean addSalesmanPer(Salesman salesman) {
			 JdbcTool jdbcTool =new JdbcTool();
			 Connection connection =jdbcTool.getConnection();
			 PreparedStatement ps=null;
			 ResultSet res=null;
			 String sql="insert into SALESMAN values(?,?,?)";
			 try {
				 ps=connection.prepareStatement(sql);
				 ps.setInt(1, getMaxPerId()+1);
				 ps.setString(2, salesman.getName() );
				 ps.setString(3, salesman.getPasswd());
				 int rows =ps.executeUpdate();
				 if (rows>0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jdbcTool.close(res, ps, connection);
			}
			 
			 return false;
		}
		 
		 
		 /**
		  *������ѯ�ۻ�Ա 
		  */
		 public Salesman selectSalesman(String name) {
			 Salesman salesman=null;
			 JdbcTool jdbcTool =new JdbcTool();
			 Connection connection =jdbcTool.getConnection();
			 PreparedStatement preparedStatement=null;
			 ResultSet resultSet=null;
			 //ʵ����һ��salesman����Ž��  ������������Ѿ�ʵ��������
			 
			 String sqlString="select * from SALESMAN where S_NAME=?";
			 try {
				preparedStatement=connection.prepareStatement(sqlString);
				preparedStatement.setString(1,name);
				resultSet=preparedStatement.executeQuery();
				while (resultSet.next()) {
					salesman=new Salesman();//���ٴ洢�ռ�
					salesman.setId(resultSet.getInt(1));
					salesman.setName(resultSet.getString(2));
					salesman.setPasswd(resultSet.getString(3));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jdbcTool.close(resultSet,preparedStatement, connection);
			}
			 
			 return salesman;
		}
		 
		 /**
		  * �޸��ۻ�Ա����
		  */
		 public boolean updateSalesman(String name,Salesman salesman) {
			JdbcTool jdbcTool =new JdbcTool();
			Connection cn=jdbcTool.getConnection();
			PreparedStatement preparedStatemen=null;
			String sql="update SALESMAN set S_NAME=?, S_PASSWORD=? where S_NAME=?";//Ҫ���ĸ������Ķ�λ����whereӴ
			try {
				preparedStatemen=cn.prepareStatement(sql);
				preparedStatemen.setString(1, salesman.getName());
				preparedStatemen.setString(2, salesman.getPasswd());
				preparedStatemen.setString(3, name);
				int rows=preparedStatemen.executeUpdate();
				if (rows>0) {
					return true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jdbcTool.close(null, preparedStatemen, cn);
			}
			 
			 return false;
		}
	
		 /**
		  * ɾ���ۻ�Ա��¼�˺�
		  */
		 public boolean deleteMan(String name,Salesman salesman) {
			 JdbcTool jdbcTool =new JdbcTool();
			 Connection connection = jdbcTool.getConnection();
			 PreparedStatement preparedStatement =null;
			 String sql="delete from SALESMAN where S_NAME=?";
			 try {
				 preparedStatement=connection.prepareStatement(sql);
				 preparedStatement.setString(1, name);
				 int rows =preparedStatement.executeUpdate();
				 if (rows>0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jdbcTool.close(preparedStatement, connection);
			}
			 
			 
			 
			 
			 return false;
		}

		 /**
		  * ��ʾ���ݿ��������ۻ�Ա�û�
		  */
		 public ArrayList<Salesman> allMembers() {
			ArrayList<Salesman> list =new ArrayList<Salesman>();
			JdbcTool jdbcTool =new JdbcTool();
			Connection connection =jdbcTool.getConnection();
			PreparedStatement preparedStatement =null;
			ResultSet resultSet=null;
			
			String sql="select * from SALESMAN"; 
			try {
				preparedStatement=connection.prepareStatement(sql);
				resultSet=preparedStatement.executeQuery();
				while (resultSet.next()) {
					Salesman salesman =new Salesman();
					salesman.setName(resultSet.getString(1));
					salesman.setPasswd(resultSet.getString(2));
					list.add(salesman);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jdbcTool.close(resultSet, preparedStatement, connection);
			}
			
			
			return list;
		}

		 /**
		  *ģ����ѯ����Ա����
		  */
		 public ArrayList<Salesman> keyWdSelect(String name){
			ArrayList<Salesman> list=new ArrayList<Salesman>();
			JdbcTool jdbcTool =new JdbcTool();
			Connection connection=jdbcTool.getConnection();
			PreparedStatement preparedStatement=null;
			ResultSet resultSet=null;
			String sql="select * from SALESMAN where S_NAME like ? ";  //like ��ʾģ����ѯ���ڱ�����ӵ��ַ����Ǳ�ı�����%��ʾ�����ַ� ��_�������ַ���
			
			try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, "%"+name+"%");
				resultSet=preparedStatement.executeQuery();
				while (resultSet.next()) {
					Salesman  salesman = new Salesman();
					salesman.setName(resultSet.getString(2)); // �����ű��еĵڶ���
					salesman.setPasswd(resultSet.getString(3));  //�����ű��еĵ�����
					list.add(salesman);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jdbcTool.close(resultSet,preparedStatement,connection);
			}
			 return list;
		}
}		 



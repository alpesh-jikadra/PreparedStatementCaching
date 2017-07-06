package com.example;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;

public class ExampleDAO<T, PK extends Serializable> extends GenericDaoHibernateImpl<T, Integer>{

	public ExampleDAO(Class<T> type) {
		super(type);
	}

	
	@SuppressWarnings("deprecation")
	public void validate(boolean insertNew) throws Exception {
		
		for(int i =0; i<50 ;i++){
			if(insertNew){
				insert("Ins"+i); // Comment this line to check the performance
			}
			if(i%2 ==0){
				executeDummyQuery(i);
			}
		}
		System.out.println("Complete Validate");
	}
	
	@SuppressWarnings("deprecation")
	private void executeDummyQuery(int i ) throws SQLException {
		String s=  "SELECT a.product_id, COUNT(a.product_id) FROM product a LEFT JOIN trn b  ON a.product_id = b.product_id GROUP BY a.product_id";
		Session session = getSession();
		PreparedStatement prepareStatement = session.connection().prepareStatement(s);
		ResultSet resultSet = prepareStatement.executeQuery();
		System.out.println("Execute Select "+i);
		prepareStatement.close();
		
	}
	

	@SuppressWarnings("deprecation")
	public String insert(String name) throws Exception{
		PreparedStatement ps = getSession().connection().prepareStatement("insert into product (PRODUCT_ID)" + " values(?)");
		ps.setString(1, name);
		long startTime = System.currentTimeMillis();
		ps.executeUpdate();
		return "";
	}
	public String get(String name) throws SQLException{
		
		PreparedStatement ps = getSession().connection().prepareStatement("select product_id from product  where product_id = ?");
		ps.setString(1, name);
		long startTime = System.currentTimeMillis();
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			return rs.getString(1);
		}
		return "";
	}
	
}

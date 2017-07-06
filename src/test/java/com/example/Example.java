package com.example;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Example {

	
	static final ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "testApplication.xml" });;
	static JdbcTemplate jdbcTemplate;
	@BeforeClass
	public static void setupAll(){
		jdbcTemplate = new JdbcTemplate((DataSource) context.getBean("dataSource"));
		//addDummyEntry();
		
	}
	 
	private static void addDummyEntry(){
		jdbcTemplate.execute("delete from trn"); 
		for(int i =0;i<100000;i++){
			jdbcTemplate.execute("insert into trn values('p"+i+"')");
		}
	}
	@Test
	public void simpleTest() throws Exception {
		ExampleDAO dao= (ExampleDAO) context.getBean("exampleDAO");
		boolean insertNew = true;
		if(insertNew){
			jdbcTemplate.execute("delete from product");
		}
		dao.validate(insertNew);
		System.out.println("Done");
	}
	
}

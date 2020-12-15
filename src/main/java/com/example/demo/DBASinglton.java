package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public final class DBASinglton {

	private static DBASinglton instance;
	private JdbcTemplate jdbc;
	private DriverManagerDataSource dataSource;
	
	private DBASinglton() {
		
		setDataSource();
		setJdbcTemplate();
	}
	
	public static DBASinglton getDBASingltonInstance() {
		if (instance == null) {
			instance = new DBASinglton();
		}
		
		return instance;
	}
	
	public void setDataSource() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/tptproject");
		dataSource.setUsername("root");
		dataSource.setPassword("port");
	}
	
	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}
	
	public void setJdbcTemplate() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbc;
	}
}

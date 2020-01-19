package com.dealership.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionFactory {
	
	private static String url;
	
	private static String user;
	
	private static String password;
	
	private static ConnectionFactory cf;
	
	private static final String PROPERTIES_FILE = "resources/database.properties";
	
	public static Connection getConnection() {
		
		if (cf == null) {
			cf = new ConnectionFactory();
		}
		
		return cf.createConnection();
		
	}
	
	private ConnectionFactory() {
		
		Properties prop = new Properties();
		
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)){
			
			prop.load(fis);
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private Connection createConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	public static ResultSet sendCommand(String sql) {
				
		Connection conn = getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void insertCommand(String sql) {
		
		Connection conn = getConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
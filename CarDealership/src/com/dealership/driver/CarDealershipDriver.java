package com.dealership.driver;
/*
 * In Java parlance, a DAO is an organizing class that contains methods to 
 * access a database table. A POJO holds database records. A DAO will return 
 * POJOs from some of its methods.
 */


import java.io.IOException;
import java.sql.SQLException;


public class CarDealershipDriver {
	
		public static void main(String[] args) throws IOException, SQLException {
		
		CarDealership.mainMenu();				
	
	}
}
/*
 * String sql2 = "select * from project0.customer"; Connection conn =
 * ConnectionFactory.getConnection(); try { Statement stmt =
 * conn.createStatement(); ResultSet rs = stmt.executeQuery(sql2); if(rs.next())
 * { int id = rs.getInt("customerid"); System.out.println(id); } } catch
 * (SQLException e) { e.printStackTrace(); } finally { try { conn.close(); }
 * catch (SQLException e) { e.printStackTrace(); } }
 */
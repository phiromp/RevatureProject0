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


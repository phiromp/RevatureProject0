package com.dealership.driver;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dealership.util.ConnectionFactory;

public class Car {

	
	
	
	public static String toString(int carId) throws SQLException {
		
		String sql = "select * from project0.car where car_id = " + carId;
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		while(rs.next()) {
			return (rs.getString(4) + " " + rs.getString(2) + " " + rs.getString(3));
			}
	
		return null;
	}
	
}

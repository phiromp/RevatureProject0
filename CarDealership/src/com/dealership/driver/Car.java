package com.dealership.driver;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dealership.util.ConnectionFactory;

public class Car {

	static void viewMyCars(Customer me) throws SQLException {
		
		String sql = "select * from project0.customer where username = '" + me.getUsername() + "'";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		int id = 0;
		while(rs.next()) {
			id = rs.getInt(1);
		}
		sql = "select * from project0.car";
		rs = ConnectionFactory.sendCommand(sql);
		int i=1;
		while(rs.next()) {
			if(id!=0 && rs.getInt(5) == id) { // null(0) means not owned
				System.out.println("[" +i+"] " + rs.getString(4) + " " + rs.getString(2) + " " + rs.getString(3));
				i++;
			}
		}
		Customer.customerHome(me);
	} 
	
	static void displayAllCars() throws SQLException {
		String sql = "select * from project0.car";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		int i=1;
		while(rs.next()) {
			if(rs.getInt(5) == 0) { // null(0) means not owned
				System.out.println("[" +i+"] " + rs.getString(4) + " " + rs.getString(2) + " " + rs.getString(3));
				i++;
			}
		}
	}
	
	public static String toString(int carId) throws SQLException {
		
		String sql = "select * from project0.car where car_id = " + carId;
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		while(rs.next()) {
			return (rs.getString(4) + " " + rs.getString(2) + " " + rs.getString(3));
			}
	
		return null;
	}
	
	static void changeCars() throws SQLException {
		System.out.println("\n[1] Add car to the lot");
		System.out.println("[2] Remove car from the lot");
		int input = CarDealership.sc.nextInt();
		
		switch (input) {
		case 1:
			String in = CarDealership.sc.nextLine();
			System.out.println("\n[1] Enter car to add to lot (YEAR MAKE MODEL)");
			in = CarDealership.sc.nextLine();
			String inArr[] = in.split(" ");
			
			String sql = "insert into project0.car (make, model, car_year) values ( '" + inArr[1] + "', '" + inArr[2] + "', " + inArr[0] + " )";
			ConnectionFactory.insertCommand(sql);
			
			CarDealership.logger.info("Employee added " + in + " the lot");
			Customer.carCount++;
			Employee.employeeMainMenu();
			break;
		case 2:
			System.out.println("\nWhich car would you like to remove");
			Car.displayAllCars();
			input = CarDealership.sc.nextInt();
			
			sql = "select * from project0.car";
			ResultSet rs = ConnectionFactory.sendCommand(sql);
			int i=0;
			int removeID = 0;
			while(rs.next()) {
				if(rs.getInt(5) == 0) { // only accessed un-owned cars
					i++;
					if(i == input) {
						removeID = rs.getInt(1);
					}
				}
			}
			CarDealership.logger.info("Employee removed " + Car.toString(removeID) );
			sql = "delete from project0.car where car_id = " + removeID;
			ConnectionFactory.insertCommand(sql);

			Customer.carCount--;
			Employee.employeeMainMenu();
			break;
		default:
			System.out.println("not valid option");
			changeCars();
		}
	}
	
}

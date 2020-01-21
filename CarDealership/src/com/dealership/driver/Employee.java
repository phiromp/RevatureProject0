package com.dealership.driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.dealership.util.ConnectionFactory;

// 1. As an employee, I can add a car to the lot.
// 2. As an employee, I can accept or reject an offer for a car.
// 3. As an employee, I can remove a car from the lot.
// 4. As an employee, I can view all payments.

public class Employee {

	public static void employeeMainMenu() throws SQLException {
		System.out.println("\nWelcome to the Employee Main Menu!");
		System.out.println("[1] Add/Remove car from the lot");
		System.out.println("[2] View car offers");
		System.out.println("[3] View all payments");
		System.out.println("[4] Logout");
		
		int input = CarDealership.sc.nextInt();
		switch (input) {
		case 1:
			changeCars();
			break;
		case 2:
			viewCarOffers();
			break;
		case 3:
			viewPayments();
			employeeMainMenu();
			break;
		case 4:
			CarDealership.mainMenu();
			break;
		default:
			System.out.println("not valid option");
			employeeMainMenu();
		
		}
	}


	private static void viewPayments() throws SQLException {
		
		String sql = "select c2.username, amount, make, model, car_year " + 
				     "from project0.payment p " + 
				     "left join project0.car c on p.car_id = c.car_id " + 
				     "left join project0.customer c2 on c2.customerid = p.customerid";
		
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		int i=0;
		while(rs.next()) {
			i++;
			System.out.println( "[" +i+"] " + rs.getString(1) + " paid $" + rs.getInt(2) + 
								" on : " + rs.getInt(5) + " " + rs.getString(3) + " " + rs.getString(4) );
		}
	}


	private static void viewCarOffers() throws SQLException {
		String sql = "select c2.username, amount, make, model, car_year " + 
			     "from project0.car_offer co " + 
			     "left join project0.car c on co.car_id = c.car_id " + 
			     "left join project0.customer c2 on c2.customerid = co.customerid";
	
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		int i=0;
		while(rs.next()) {
		i++;
		System.out.println( "[" +i+"] " + rs.getString(1) + " offer $" + rs.getInt(2) + 
							" on : " + rs.getInt(5) + " " + rs.getString(3) + " " + rs.getString(4) );
		}
		
		if(i > 0) {
			System.out.println("\n[1] Home");
			System.out.println("[2] Accept offer");
			System.out.println("[3] Reject offer");
			int input = CarDealership.sc.nextInt();
	
			switch (input) {
			case 1:
				employeeMainMenu();
				break;
			case 2:
				changeOffers(true);
				break;
			case 3:
				changeOffers(false);
				break;
			default:
				System.out.println("not valid option");
				employeeMainMenu();
			}
		}
		else {
			System.out.println("No offers currently");
			employeeMainMenu();
		}
	}

	private static void changeCars() throws SQLException {
		System.out.println("\n[1] Add car to the lot");
		System.out.println("[2] Remove car from the lot");
		int input = CarDealership.sc.nextInt();
		
		switch (input) {
		case 1:
			String in = CarDealership.sc.nextLine();
			System.out.println("\n[1] Enter car to add to lot (YEAR MAKE MODEL)");
			in = CarDealership.sc.nextLine();
			String inArr[] = in.split(" ");
			
			// TODO add car to data base
			String sql = "insert into project0.car (make, model, car_year) values ( '" + inArr[1] + "', '" + inArr[2] + "', " + inArr[0] + " )";
			ConnectionFactory.insertCommand(sql);
			
			CarDealership.logger.info("Employee added " + in + " the lot");
			Customer.carCount++;
			employeeMainMenu();
			break;
		case 2:
			System.out.println("\nWhich car would you like to remove");
			Customer.displayCars();
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
			employeeMainMenu();
			break;
		default:
			System.out.println("not valid option");
			changeCars();
		}
	}
	
	public static void empSignIn() throws SQLException {
		System.out.println("Enter username: ");
		String user = CarDealership.sc.next();
		System.out.println("Enter password: ");
		String pass = CarDealership.sc.next();
		
		String sql = "select * from project0.employee";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		
		while(rs.next()) {
			if(user.equals(rs.getString(2)) && pass.equals(rs.getString(3))) {
				System.out.println("Employee signed in");
				employeeMainMenu();
			}
		}
			System.out.println("Failed sign in");
			CarDealership.mainMenu();
	}
	

	private static void changeOffers(boolean accept) throws SQLException {
		String sql = "select * from project0.car_offer";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		
		System.out.println("Which offer?");
		int i = CarDealership.sc.nextInt();
		System.out.println("i = " + i);
		int offerID = 0;
		int carID = 0;
		int index=0;
		int amount=0;
		int user=0;
		while(rs.next()) {
			index++;
			System.out.println("index: " + index);
			if(index == i) {
				offerID = rs.getInt(1);
				carID = rs.getInt(4);
				amount = rs.getInt(2);
				user = rs.getInt(3);
			}
		}
		
		if(accept) {
			// TODO
			// log transactions
			// insert into payment plan
			// add customer id into car table
			CarDealership.logger.info("Employee accepted offer on: " +  Car.toString(carID));
			
			sql = "update project0.car set customerid = " + user + " where car_id = " + carID ;
			ConnectionFactory.insertCommand(sql);
			
			sql = "insert into project0.payment_plan ( amount_owed, monthly_payment, car_id) values "
					+ "( " + amount + ", " + (amount/60) + ", " + carID + ")";
			ConnectionFactory.insertCommand(sql);
			
			sql = "delete from project0.car_offer where car_id = " + carID;
			ConnectionFactory.insertCommand(sql);
		}

		else {
			CarDealership.logger.info("Employee declined offer: " + Car.toString(carID));
			System.out.println(offerID);
			sql = "delete from project0.car_offer where offer_id = " + offerID;
			ConnectionFactory.insertCommand(sql);
		}
		employeeMainMenu();
	}
	
	
}
	

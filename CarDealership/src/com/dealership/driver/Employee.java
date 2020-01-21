package com.dealership.driver;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dealership.util.ConnectionFactory;

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
			Car.changeCars();
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
				Offers.changeOffers(true);
				break;
			case 3:
				Offers.changeOffers(false);
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
}
	

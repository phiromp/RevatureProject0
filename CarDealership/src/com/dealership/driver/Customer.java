package com.dealership.driver;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dealership.util.ConnectionFactory;

public class Customer {
	private String username, password;
	static int carCount = 3;
	private int myId;

	public Customer(String user, String pass, int id) {
		super();
		this.username = user;
		this.password = pass;
		this.myId = id;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	public int getId() {
		return myId;
	}

	public static void CustomerSignIn() throws SQLException {
		System.out.println("Enter username: ");
		String user = CarDealership.sc.next();
		System.out.println("Enter password: ");
		String pass = CarDealership.sc.next();
		
		String sql = "select * from project0.customer";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		
		while(rs.next()) {
			String name = rs.getString(2);
			if(user.equals(name) && pass.equals(rs.getString(3))) {
				Customer me = new Customer(user,pass, rs.getInt(1));
				customerHome(me);
			}
		}
		
		System.out.println("unsuccessful");
		CarDealership.mainMenu();
		
	}

	static void customerHome(Customer me) throws SQLException {
		System.out.println("\nWelcome to the Customer home!");
		System.out.println("[1] View the cars on the lot");
		System.out.println("[2] Make an offer for a car");
		System.out.println("[3] View the cars that I own");
		System.out.println("[4] View my remaining payments for a car");
		System.out.println("[5] Signout");
		
		int input = CarDealership.sc.nextInt();
  
		switch (input) { 
			case 1: 
				Car.displayAllCars();
				customerHome(me);
				break;
			case 2:
				Offers.makeOffer(me);
				break; 
			case 3: 
				Car.viewMyCars(me);
				break;
			case 4: 
				PaymentPlan.remainPay(me);
				break; 
			case 5:
				CarDealership.mainMenu();
			default:
				System.out.println("not valid option"); 
				customerHome(me); 
		} 
	}


}

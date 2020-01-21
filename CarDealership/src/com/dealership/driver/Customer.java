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

	private static void customerHome(Customer me) throws SQLException {
		System.out.println("\nWelcome to the Customer home!");
		System.out.println("[1] View the cars on the lot");
		System.out.println("[2] Make an offer for a car");
		System.out.println("[3] View the cars that I own");
		System.out.println("[4] View my remaining payments for a car");
		System.out.println("[5] Signout");
		
		int input = CarDealership.sc.nextInt();
  
		switch (input) { 
			case 1: 
				displayCars();
				customerHome(me);
				break;
			case 2:
				makeOffer(me);
				break; 
			case 3: 
				viewMyCars(me);
				break;
			case 4: 
				remainPay(me);
				break; 
			case 5:
				CarDealership.mainMenu();
			default:
				System.out.println("not valid option"); 
				customerHome(me); 
		} 
	}

	private static void viewMyCars(Customer me) throws SQLException {
		
		String sql = "select * from project0.customer where username = '" + me.username + "'";
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
		customerHome(me);
	} 

	private static void makeOffer(Customer me) throws SQLException {
		displayCars();
		System.out.println("Enter name of car you'd like to make an offer on? (make model)");
		CarDealership.sc.nextLine();
		String car = CarDealership.sc.nextLine();
		String[] carArr = car.split(" ");
		
		System.out.println("How much would you like to offer? ($)");
		int amount = CarDealership.sc.nextInt();
		
		String sql = "select * from project0.car";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		while(rs.next()) {
			if(rs.getString(2).equals(carArr[0]) && rs.getString(3).equals(carArr[1])) {
				sql = "insert into project0.car_offer (amount, customerid, car_id) " + 
						"values (" + amount + ", " + me.getId() + ", " + rs.getInt(1) + "); ";
				ConnectionFactory.insertCommand(sql);
			}
		}
		customerHome(me);
	}

	static void displayCars() throws SQLException {
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
	
	private static void remainPay(Customer me) throws SQLException {
		
		String sql = "select * from project0.payment_plan";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		int i=0;
		while(rs.next()) {
			i++;
			System.out.println("[" +i+"] " + Car.toString(rs.getInt(4)));
			System.out.println("   Remaining balance: $" + rs.getInt(2));
			System.out.println("   Monthly Payment: $" + rs.getInt(3));
		}
		
		if(i!=0) {
	        System.out.println("Make a payment?");
	        System.out.println("[1] yes");
	        System.out.println("[2] no");
	        
	        int input = CarDealership.sc.nextInt();
	        
	        switch (input) { 
			case 1: 
		        
		        System.out.println("Which car");
		        int index = CarDealership.sc.nextInt();
		        
				System.out.println("Enter how much ($): ");
				int pay = CarDealership.sc.nextInt();
				makePayment(pay, index);
				customerHome(me);
				break;
			case 2:
				customerHome(me);
				break; 
			default:
				System.out.println("not valid option"); 
				customerHome(me); 
	        }
		}
        else {
        	System.out.println("All cars are paid off");
        	customerHome(me);
        }
		
	}
	
	public static void makePayment(int pay, int index) throws SQLException {
		String sql = "select * from project0.payment_plan";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		
		int i = 0;
		while(rs.next()) {
			i++;
			if (index == i) {
				sql = "update project0.payment_plan set amount_owed = " + (rs.getInt(2)-pay) + " where payment_plan_id = " + rs.getInt(1);
				ConnectionFactory.insertCommand(sql);
			}
		}
		
	}

}

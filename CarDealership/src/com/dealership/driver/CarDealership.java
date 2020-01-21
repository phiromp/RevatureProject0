package com.dealership.driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.*;

import com.dealership.util.ConnectionFactory;

/*# Car Dealership
## Description
   The Car Dealership app is a console-based application that facilitates the purchasing of cars. 
   An employee can add cars to the lot and manage offers for those cars, while a customer can view the cars on the lot and make offers.

## Purpose
   We want to see that you can meet deadlines and that you can code. You are expected to complete the following requirements and give a 
   5 minute presentation demonstrating your application.

## Requirements
1. Functionality should reflect the below user stories.
2. Data persisted through serialization.
3. All input is received using the java.util.Scanner class.
4. Log4j is implemented to log events to a file.
5. 100% test coverage using JUnit Java testing framework (excluding Pojos).

## User Stories

*/

public class CarDealership {
	static final Logger logger = Logger.getLogger(CarDealership.class);
	public static Scanner sc = new Scanner(System.in); 
	
	public static void mainMenu() throws SQLException {
		int input;
		logger.setLevel(Level.INFO);

		System.out.println("\nWelcome to the Main Menu!");
		System.out.println("[1] New User Sign Up");
		System.out.println("[2] Login as Customer");
		System.out.println("[3] Login as Employee");
		
		input = sc.nextInt();
		switch (input) {
		case 1:
			NewUser();
			break;
		case 2:
			Customer.CustomerSignIn();
			break;
		case 3:
			new Employee();
			Employee.empSignIn();
			break;
		default:
			System.out.println("not valid option");
			mainMenu();
		
		}
	}

	private static void NewUser() throws SQLException {
		System.out.println("Sign up for a new account.");
		System.out.println("Enter desired username: ");
		String user = sc.next();
		System.out.println("Enter desired password: ");
		String pass = sc.next();
		
		String sql = "insert into project0.customer (username, user_password) values('" + user + "', '" + pass + "')";
		ConnectionFactory.insertCommand(sql);
		
		System.out.println("Account Created!");
		mainMenu();
	}

}

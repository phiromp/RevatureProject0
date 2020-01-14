import java.awt.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.*;

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
	static ArrayList<Object> customerList = new ArrayList<Object>();
	static final Logger logger = Logger.getLogger(CarDealership.class);
	static Scanner sc = new Scanner(System.in); 

	public static void main(String[] args) throws IOException {
		logger.setLevel(Level.INFO);
		customerList.add(new Customer("joe", "pass"));
		customerList.add(new Customer("abc", "123"));

		mainMenu(sc);

	}

	public static void mainMenu(Scanner sc) {
		int input;

		System.out.println("\nWelcome to the Main Menu!");
		System.out.println("[1] New User Sign Up");
		System.out.println("[2] Login as Customer");
		System.out.println("[3] Login as Employee");
		System.out.println("[4] System Admin");
		
		input = sc.nextInt();
		switch (input) {
		case 1:
			NewUser(sc);
			break;
		case 2:
			Customer.CustomerSignIn(sc);
			break;
		case 3:
			new Employee();
			Employee.employeeMainMenu(sc);
			break;
		default:
			System.out.println("not valid option");
			mainMenu(sc);
		
		}
	}

	private static void NewUser(Scanner sc) {
		System.out.println("Sign up for a new account.");
		System.out.println("Enter desired username: ");
		String user = sc.next();
		System.out.println("Enter desired password: ");
		String pass = sc.next();
		Customer guy = new Customer(user, pass);
		customerList.add(guy);

		System.out.println("Account Created!");
		mainMenu(sc);
	}

}

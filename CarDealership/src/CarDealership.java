import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

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

	public static void main(String[] args) {
		int input;
		customerList.add(new Customer("joe", "pass"));
		customerList.add(new Customer("jim", "pass2"));
		customerList.add(new Customer("jack", "pass3"));

		System.out.println("Welcome to the Car Dealership! Are you a:");
		System.out.println("[1] New User");
		System.out.println("[2] Customer");
		System.out.println("[3] Employee");
		System.out.println("[4] System");

		Scanner s = new Scanner(System.in);
		input = s.nextInt();
		switch (input) {
		case 1:
			System.out.println("hi User");
			NewUser();
			break;
		case 2:
			System.out.println("hi Customer");
			Customer.CustomerSignIn();
			break;
		case 3:
			System.out.println("hi employee");
			new Employee();
			break;
		case 4:
			System.out.println("hi system");
			new SystemApp();
			break;
		default:
			System.out.println("not valid option");
		}
		s.close();

	}

	private static void NewUser() {
		Scanner s = new Scanner(System.in);
		System.out.println("Sign up for a new account.");
		System.out.println("Enter desired username: ");
		String user = s.next();
		System.out.println("Enter desired password: ");
		String pass = s.next();
		Customer guy = new Customer(user, pass);
		customerList.add(guy);

		System.out.println("username: " + guy.getUsername());
		System.out.println("password: " + guy.getPassword());
		s.close();
	}

}

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
* As a user, I can login.
* As an employee, I can add a car to the lot.
* As a customer, I can view the cars on the lot.
* As a customer, I can make an offer for a car.
* As an employee, I can accept or reject an offer for a car.
* As the system, I reject all other pending offers for a car when an offer is accepted.
* As a user, I can register for a customer account.
* As an employee, I can remove a car from the lot.
* As a customer, I can view the cars that I own.
* As a customer, I can view my remaining payments for a car.
* As an employee, I can view all payments.
* As the system, I can calculate the monthly payment.
*/

public class CarDealership {


	public static void main(String[] args) {
		int input;
		
		System.out.println("Welcome to the Car Dealership! Are you a:");
		System.out.println("[1] User");
		System.out.println("[2] Customer");
		System.out.println("[3] Employee");
		System.out.println("[4] System");


		Scanner s = new Scanner(System.in);
		input = s.nextInt();
		
		switch(input){
		case 1:
			System.out.println("hi user");
			break;
		case 2:
			System.out.println("hi customer");
			break;
		case 3:
			System.out.println("hi employee");
			break;
		case 4:
			System.out.println("hi system");
			break;
		default:
			System.out.println("not valid option");
		}
		
		
	}
	

}

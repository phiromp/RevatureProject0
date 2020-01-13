import java.util.ArrayList;
import java.util.Scanner;

// 1. As an employee, I can add a car to the lot.
// 2. As an employee, I can accept or reject an offer for a car.
// 3. As an employee, I can remove a car from the lot.
// 4. As an employee, I can view all payments.

public class Employee {
	static ArrayList<Object> employeeList = new ArrayList<Object>();
	
	static ArrayList<String> offerList = new ArrayList<String>();
	
	public Employee() {
		super();
	}

	public static void employeeMainMenu(Scanner sc) {
		System.out.println("\nWelcome to the Employee Main Menu!");
		System.out.println("[1] Add/Remove car from the lot");
		System.out.println("[2] View car offers");
		System.out.println("[3] View all payments");
		
		int input = sc.nextInt();
		switch (input) {
		case 1:
			changeCars(sc);
			break;
		case 2:
			viewCarOffers(sc);
			break;
		case 3:
			System.out.println("Payments!");
			break;
		default:
			System.out.println("not valid option");
			employeeMainMenu(sc);
		
		}
	}


	private static void viewCarOffers(Scanner sc) {
		for(int i=1; i<=offerList.size(); i++) {
			System.out.println("["+i+"] "+offerList.get(i-1));
		}
		System.out.println("\n[1] Home");
		System.out.println("[2] Accept any offers?");
		System.out.println("[3] Reject any offers?");
		int input = sc.nextInt();

		switch (input) {
		case 1:
			employeeMainMenu(sc);
			break;
		case 2:
			changeOffers(sc, true);
			break;
		case 3:
			changeOffers(sc, false);
			break;
		default:
			System.out.println("not valid option");
			employeeMainMenu(sc);
		}
		
	}

	private static void changeCars(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
	
	private static void changeOffers(Scanner sc, boolean accept) {
		System.out.println("Which offer?");
		int i = sc.nextInt();
		if(accept) 
			offerList.clear();
		else
			offerList.remove(i-1);
		employeeMainMenu(sc);
	}
	
	
}

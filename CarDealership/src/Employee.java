import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

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
		System.out.println("[4] Logout");
		
		int input = sc.nextInt();
		switch (input) {
		case 1:
			changeCars(sc);
			break;
		case 2:
			viewCarOffers(sc);
			break;
		case 3:
			for(String entry : Customer.payments) {
				System.out.println(entry);
			}
			employeeMainMenu(sc);
			break;
		case 4:
			CarDealership.mainMenu(sc);
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
		System.out.println("\n[1] Add car to the lot");
		System.out.println("[2] Remove car from the lot");
		int input = sc.nextInt();
		
		switch (input) {
		case 1:
			String in = sc.nextLine();
			System.out.println("\n[1] Enter car to add to lot (YEAR MAKE MODEL)");
			in = sc.nextLine();
			Customer.carList.add(in);
			CarDealership.logger.info("Employee added " + in + " the lot");
			Customer.carCount++;
			employeeMainMenu(sc);
			break;
		case 2:
			System.out.println("\n[1] Which car would you like to remove");
			Customer.displayCars();
			input = sc.nextInt();
			CarDealership.logger.info("Employee removed " + Customer.carList.get(input-1) );
			Customer.carList.remove(input-1);
			Customer.carCount--;
			employeeMainMenu(sc);
			break;
		default:
			System.out.println("not valid option");
			changeCars(sc);
		}
	}
	
	private static void changeOffers(Scanner sc, boolean accept) {
		System.out.println("Which offer?");
		int i = sc.nextInt();
		// make temp string so system can remove the pending offers on the car that has been sold
		String temp = offerList.get(i-1).split("on the ")[1];
		String user = offerList.get(i-1).split(" ")[0];
		int offerAmount = Integer.parseInt(offerList.get(i-1).split(" ")[2].substring(1));
		Customer.myCars.put(temp, user);
		if(accept) {
			CarDealership.logger.info("Employee accepted offer: " + offerList.get(i-1));
			
			Iterator<String> iter = offerList.iterator();
			while(iter.hasNext()) {
				if(iter.hasNext()) {
					String offer = iter.next();
					if(offer.contains(temp)) {
						CarDealership.logger.info("System removed pending offer: " + offer);
						iter.remove();
						CarDealership.logger.info("Employee removed " + temp);
						Customer.carList.remove(temp);
						Customer.carCount--;
						PaymentPlan plan = new PaymentPlan(offerAmount, temp, user);
						PaymentPlan.serialize(plan);
						PaymentPlan.planCount++;
					}
				}
			}
		}
			/*
			 * for(String s: offerList) { if(s.contains(temp)) {
			 * CarDealership.logger.info("System removed pending offer: " + s);
			 * offerList.remove(s); } }
			 */
		else {
			CarDealership.logger.info("Employee declined offer: " + offerList.get(i-1));
			offerList.remove(i-1);
		}
		employeeMainMenu(sc);
	}
	
	
}
	

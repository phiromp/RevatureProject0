import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// 1. As an employee, I can add a car to the lot.
// 2. As an employee, I can accept or reject an offer for a car.
// 3. As an employee, I can remove a car from the lot.
// 4. As an employee, I can view all payments.

public class Employee {
	static ArrayList<String> offerList = new ArrayList<String>();
	public static Map<String, String> employeeList;
	static {
	    employeeList = new HashMap<>();
	    employeeList.put("admin", "admin");
	}
	
	public Employee() {
		super();
	}

	public static void employeeMainMenu() {
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
			for(String entry : Customer.payments) {
				System.out.println(entry);
			}
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


	private static void viewCarOffers() {
		for(int i=1; i<=offerList.size(); i++) {
			System.out.println("["+i+"] "+offerList.get(i-1));
		} 
		if(offerList.size()>0) {
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

	private static void changeCars() {
		System.out.println("\n[1] Add car to the lot");
		System.out.println("[2] Remove car from the lot");
		int input = CarDealership.sc.nextInt();
		
		switch (input) {
		case 1:
			String in = CarDealership.sc.nextLine();
			System.out.println("\n[1] Enter car to add to lot (YEAR MAKE MODEL)");
			in = CarDealership.sc.nextLine();
			Customer.carList.add(in);
			CarDealership.logger.info("Employee added " + in + " the lot");
			Customer.carCount++;
			employeeMainMenu();
			break;
		case 2:
			System.out.println("\nWhich car would you like to remove");
			Customer.displayCars();
			input = CarDealership.sc.nextInt();
			CarDealership.logger.info("Employee removed " + Customer.carList.get(input-1) );
			Customer.carList.remove(input-1);
			Customer.carCount--;
			employeeMainMenu();
			break;
		default:
			System.out.println("not valid option");
			changeCars();
		}
	}
	
	public static void empSignIn() {
		System.out.println("Enter username: ");
		String user = CarDealership.sc.next();
		System.out.println("Enter password: ");
		String pass = CarDealership.sc.next();
		
		boolean validEmp = checkValidEmp(user, pass);
		if(validEmp) {
			System.out.println("Employee signed in");
			employeeMainMenu();
		}
		else {
			System.out.println("Failed sign in");
			CarDealership.mainMenu();
		}
	}
	
	
	public static boolean checkValidEmp(String user, String pass) {
		if(employeeList.containsKey(pass)) {
			if(employeeList.containsValue(user))
				return true;
		}
		return false;
	}

	private static void changeOffers(boolean accept) {
		System.out.println("Which offer?");
		int i = CarDealership.sc.nextInt();
		// make temp string so system can remove the pending offers on the car that has been sold
		String temp = offerList.get(i-1).split("on the ")[1];
		String user = offerList.get(i-1).split(" ")[0];
		int offerAmount = Integer.parseInt(offerList.get(i-1).split(" ")[2].substring(1));
		if(accept) {
			Customer.myCars.put(temp, user);
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
		employeeMainMenu();
	}
	
	
}
	

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// As a customer, I can view the cars on the lot.
// As a customer, I can make an offer for a car.
// As a customer, I can view the cars that I own.
// As a customer, I can view my remaining payments for a car.

public class Customer {
	private String username, password;
	static int carCount = 3;
	static ArrayList<Object> carList = new ArrayList<Object>(Arrays.asList("2006 Toyota Camry", "2012 Ford Fusion", "2010 Fiat 500"));
	
	public Customer(String user, String pass) {
		super();
		this.username = user;
		this.password = pass;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static void CustomerSignIn(Scanner sc) {
		System.out.println("Enter username: ");
		String user = sc.next();
		System.out.println("Enter password: ");
		String pass = sc.next();
		//boolean success = true;
		// Check if customer exists

		for (int i = 0; i < CarDealership.customerList.size(); i++) {
			Customer temp = (Customer) CarDealership.customerList.get(i);

			//System.out.println(temp.getUsername());
			//System.out.println(temp.getPassword());
			if (user.equals(temp.getUsername()) && pass.equals(temp.getPassword())) {
				System.out.println("Sign in Successful");
				Customer me = new Customer(user,pass);
				CustomerHome(sc, me);
			}
		}
		//if(!success)
		System.out.println("unsuccessful");
		CarDealership.mainMenu(sc);
		
	}

	private static void CustomerHome(Scanner sc, Customer me) {
		System.out.println("\nWelcome to the Customer home!");
		System.out.println("[1] View the cars on the lot");
		System.out.println("[2] Make an offer for a car");
		System.out.println("[3] View the cars that I own");
		System.out.println("[4] View my remaining payments for a car");
		System.out.println("[5] Signout");
		
		int input = sc.nextInt();
  
		switch (input) { 
			case 1: 
				displayCars();
				CustomerHome(sc, me);
				break;
			case 2:
				makeOffer(sc, me);
				break; 
			case 3: 
				viewMyCars();
				break;
			case 4: 
				remainPay(sc);
				break; 
			case 5:
				CarDealership.mainMenu(sc);
			default:
				System.out.println("not valid option"); 
				CustomerHome(sc, me); 
		} 
	}

	private static void remainPay(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private static void viewMyCars() {
		// TODO Auto-generated method stub
		
	}

	private static void makeOffer(Scanner sc, Customer me) {
		displayCars();
		System.out.println("Which car would you like to make an offer on?");
		int carChoice = sc.nextInt();
		System.out.println("How much would you like to offer ($)?");
		int carOffer = sc.nextInt();
		Employee.offerList.add(me.getUsername() + " offered $"+ Integer.toString(carOffer) +" on the " + carList.get(carChoice-1));
		CustomerHome(sc, me);
	}

	private static void displayCars() {
		for(int i=1; i<=carCount; i++) {
			System.out.println("["+i+"] "+carList.get(i-1));
		}
	}
	
	

}

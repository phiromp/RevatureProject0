import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// As a customer, I can view the cars on the lot.
// As a customer, I can make an offer for a car.
// As a customer, I can view the cars that I own.
// As a customer, I can view my remaining payments for a car.

public class Customer {
	private String username, password;
	static int carCount = 3;
	static ArrayList<Object> carList = new ArrayList<Object>(Arrays.asList("2006 Toyota Camry", "2012 Ford Fusion", "2010 Fiat 500"));
	static ArrayList<String> payments = new ArrayList<String>();
	public static Map<String, String> myCars;
	static {
	    myCars = new HashMap<>();
	    myCars.put("2015 Jeep Wrangler", "joe");
	    myCars.put("2011 Ford Mustang", "abc");
	}
	
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

	public static void CustomerSignIn() {
		System.out.println("Enter username: ");
		String user = CarDealership.sc.next();
		System.out.println("Enter password: ");
		String pass = CarDealership.sc.next();
		//boolean success = true;
		// Check if customer exists

		boolean validUser = checkValidUser(user, pass);
		
		if(validUser) {
			Customer me = new Customer(user,pass);
			customerHome(me);
		}
		else {
			//if(!success)
			System.out.println("unsuccessful");
			CarDealership.mainMenu();
		}
	}

	public static boolean checkValidUser(String user, String pass) {
		for (int i = 0; i < CarDealership.customerList.size(); i++) {
			Customer temp = (Customer) CarDealership.customerList.get(i);

			if (user.equals(temp.getUsername()) && pass.equals(temp.getPassword())) {
				System.out.println("Sign in Successful");
				return true;
			}
		}
		return false;
	}

	private static void customerHome(Customer me) {
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

	private static void viewMyCars(Customer me) {
		for (Map.Entry<String,String> entry : myCars.entrySet()) {  
			if(entry.getValue().equals(me.username))
				System.out.println(entry.getKey());
    	} 
		customerHome(me);
	} 

	private static void makeOffer(Customer me) {
		displayCars();
		System.out.println("Which car would you like to make an offer on?");
		int carChoice = CarDealership.sc.nextInt();
		System.out.println("How much would you like to offer ($)?");
		int carOffer = CarDealership.sc.nextInt();
		
		String offerMessage = me.getUsername() + " offered $"+ Integer.toString(carOffer) +" on the " + carList.get(carChoice-1);
		Employee.offerList.add(offerMessage);
		CarDealership.logger.info(offerMessage);
		
		customerHome(me);
	}

	static void displayCars() {
		for(int i=1; i<=carCount; i++) {
			System.out.println("["+i+"] "+carList.get(i-1));
		}
	}
	
	private static void remainPay(Customer me) {
		
		PaymentPlan plan = deserialize();		
            
        //System.out.println("Object has been de-serialized "); 
        String format = "You owe $" + plan.getMoneyOwed() + " on " + plan.getCar() +
        				"\nMonthly payment: $" + plan.getMonthlyPayment();
        System.out.println(format);
        //customerHome(sc, me);
        
        System.out.println("Make a payment?");
        System.out.println("[1] yes");
        System.out.println("[2] no");
        
        int input = CarDealership.sc.nextInt();
        
        switch (input) { 
		case 1: 
			System.out.println("Enter how much ($): ");
			int pay = CarDealership.sc.nextInt();
			makePayment(pay, plan, me);
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

	
	public static void makePayment(int pay, PaymentPlan plan, Customer me) {
		payments.add(me.username + " paid " + String.valueOf(pay) + " for " + plan.getCar());
		plan.setMoneyOwed(plan.getMoneyOwed()-pay);
		PaymentPlan.serialize(plan);		
	}

	public static PaymentPlan deserialize() {
		String filename = "file.ser";
		try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for de-serialization of object 
            PaymentPlan plan = (PaymentPlan)in.readObject(); 
              
            in.close(); 
            file.close(); 
            
            return plan;
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
            
        } 
        
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
		
		return null;
	}
	

}

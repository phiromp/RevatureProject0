import java.util.Scanner;

// As a customer, I can view the cars on the lot.
// As a customer, I can make an offer for a car.
// As a customer, I can view the cars that I own.
// As a customer, I can view my remaining payments for a car.

public class Customer {
	private String username, password;

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
		Scanner s = new Scanner(System.in);
		System.out.println("Enter username: ");
		String user = s.next();
		System.out.println("Enter password: ");
		String pass = s.next();
		s.close();
		// Check if customer exists

		for (int i = 0; i < CarDealership.customerList.size(); i++) {
			Customer temp = (Customer) CarDealership.customerList.get(i);

			System.out.println(temp.getUsername());
			System.out.println(temp.getPassword());
			if (user.equals(temp.getUsername()) && pass.equals(temp.getPassword())) {
				System.out.println("Sign in Successful");
				CustomerHome();
			}
		}
		System.out.println("Unsuccessful");
	}

	private static void CustomerHome() {
		System.out.println("Welcome to the Customer home!");
		System.out.println("[1] view the cars on the lot");
		System.out.println("[2] make an offer for a car");
		System.out.println("[3] view the cars that I own");
		System.out.println("[4] view my remaining payments for a car");
	}

}

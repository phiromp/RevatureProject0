import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@SuppressWarnings("serial")
public class PaymentPlan implements java.io.Serializable {
	private int moneyOwed; 
	private int monthlyPayment;
	static int planCount = 0;
	private String car;
	private String user;
	
	public PaymentPlan(int price, String car, String user) {
		this.moneyOwed = price;
		this.monthlyPayment = (price/60);
		this.car = car;
		this.user = user;
		
	}

	static void serialize(PaymentPlan plan) {
		String filename = "file.ser"; 
        
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(plan); 
              
            out.close(); 
            file.close(); 
              
            System.out.println("Object has been serialized"); 
  
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
	}

	public int getMoneyOwed() {
		return moneyOwed;
	}

	public void setMoneyOwed(int moneyOwed) {
		this.moneyOwed = moneyOwed;
	}

	public int getMonthlyPayment() {
		return monthlyPayment;
	}


	public String getCar() {
		return car;
	}


	public String getUser() {
		return user;
	}

	
}

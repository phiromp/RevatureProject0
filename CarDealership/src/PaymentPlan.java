import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;

@SuppressWarnings("serial")
public class PaymentPlan implements java.io.Serializable {
	private int moneyOwed; 
	private int monthlyPayment;
	static int planCount = 0;
	private String car;
	private String user;
	static ArrayList<PaymentPlan> plansList = new ArrayList<PaymentPlan>();
	
	public PaymentPlan(int price, String car, String user) {
		this.moneyOwed = price;
		this.monthlyPayment = (price/60);
		this.car = car;
		this.user = user;
	}

	static void serialize(PaymentPlan plan) {
		
		// add plan to list, make sure not duplicate
		// if car is already in plan update it or it effects monthly pay
		boolean alreadyExists = false;
		ListIterator<PaymentPlan> itr = plansList.listIterator();
		while(itr.hasNext()){
			PaymentPlan curr = itr.next();
		    if(curr.getCar().equals(plan.getCar())){
		    	if(plan.getMoneyOwed() <= 0) {
					itr.remove();
				}
		    	else {
		    		curr.setMoneyOwed(plan.getMoneyOwed());
		    	}
		        alreadyExists = true;
		    }
		}
		if(!alreadyExists)
			plansList.add(plan);
		
		String filename = "file.ser"; 
        
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(plansList); 
              
            out.close(); 
            file.close(); 
              
            //System.out.println("Object has been serialized"); 
  
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

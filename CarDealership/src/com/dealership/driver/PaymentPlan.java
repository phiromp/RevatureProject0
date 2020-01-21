package com.dealership.driver;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.translation.messages_bg;

import com.dealership.util.ConnectionFactory;

public class PaymentPlan {

	
	static void remainPay(Customer me) throws SQLException {
		
		String sql = "select * from project0.payment_plan";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		int i=0;
		while(rs.next()) {
			if(rs.getInt(5) == me.getId()) {
				i++;
				System.out.println("[" +i+"] " + Car.toString(rs.getInt(4)));
				System.out.println("   Remaining balance: $" + rs.getInt(2));
				System.out.println("   Monthly Payment: $" + rs.getInt(3));
			}
		}
		
		if(i!=0) {
	        System.out.println("Make a payment?");
	        System.out.println("[1] yes");
	        System.out.println("[2] no");
	        
	        int input = CarDealership.sc.nextInt();
	        
	        switch (input) { 
			case 1: 
		        
		        System.out.println("Which car");
		        int index = CarDealership.sc.nextInt();
		        
				System.out.println("Enter how much ($): ");
				int pay = CarDealership.sc.nextInt();
				PaymentPlan.makePayment(pay, index, me);
				Customer.customerHome(me);
				break;
			case 2:
				Customer.customerHome(me);
				break; 
			default:
				System.out.println("not valid option"); 
				Customer.customerHome(me); 
	        }
		}
        else {
        	System.out.println("All cars are paid off");
        	Customer.customerHome(me);
        }
	}
	
	private static void makePayment(int pay, int index, Customer me) throws SQLException {
		
		String sql = "select payment_plan_id, amount_owed, c.car_id, c2.customerid " + 
					 "from project0.payment_plan pp " + 
					 "left join project0.car c on pp.car_id = c.car_id " + 
					 "left join project0.customer c2 on pp.customerid = " + me.getId();
		
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		
		int i = 0;
		while(rs.next()) {
			i++;
			if (index == i) {
				sql = "update project0.payment_plan set amount_owed = " + (rs.getInt(2)-pay) + " where payment_plan_id = " + rs.getInt(1);
				ConnectionFactory.insertCommand(sql);
				
				sql = "insert into project0.payment (amount, customerid, car_id) values ( " + pay + ", " + rs.getInt(4) + ", " + rs.getInt(3) + ")";
				ConnectionFactory.insertCommand(sql);
			}
		}
	}
	
}

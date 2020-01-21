package com.dealership.driver;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dealership.util.ConnectionFactory;

public class Offers {

	static void changeOffers(boolean accept) throws SQLException {
		String sql = "select * from project0.car_offer";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		
		System.out.println("Which offer?");
		int i = CarDealership.sc.nextInt();
		System.out.println("i = " + i);
		int offerID = 0;
		int carID = 0;
		int index=0;
		int amount=0;
		int user=0;
		while(rs.next()) {
			index++;
			System.out.println("index: " + index);
			if(index == i) {
				offerID = rs.getInt(1);
				carID = rs.getInt(4);
				amount = rs.getInt(2);
				user = rs.getInt(3);
			}
		}
		
		if(accept) {
			
			accceptOffer(carID, user, amount);
		}

		else {
			
			declineOffer(carID, offerID);
			
		}
		Employee.employeeMainMenu();
	}
	
	private static void accceptOffer(int carID, int user, int amount) throws SQLException {
		
		CarDealership.logger.info("Employee accepted offer on: " +  Car.toString(carID));
		
		String sql = "update project0.car set customerid = " + user + " where car_id = " + carID ;
		ConnectionFactory.insertCommand(sql);
		
		sql = "insert into project0.payment_plan ( amount_owed, monthly_payment, car_id) values "
				+ "( " + amount + ", " + (amount/60) + ", " + carID + ")";
		ConnectionFactory.insertCommand(sql);
		
		sql = "delete from project0.car_offer where car_id = " + carID;
		ConnectionFactory.insertCommand(sql);
		
	}
	
	
	private static void declineOffer(int CarID, int offerID) throws SQLException {
		
		CarDealership.logger.info("Employee declined offer: " + Car.toString(CarID));
		
		String sql = "delete from project0.car_offer where offer_id = " + offerID;
		
		ConnectionFactory.insertCommand(sql);
		
	}
	
	static void makeOffer(Customer me) throws SQLException {
		Car.displayAllCars();
		System.out.println("Enter name of car you'd like to make an offer on? (make model)");
		CarDealership.sc.nextLine();
		String car = CarDealership.sc.nextLine();
		String[] carArr = car.split(" ");
		
		System.out.println("How much would you like to offer? ($)");
		int amount = CarDealership.sc.nextInt();
		
		String sql = "select * from project0.car";
		ResultSet rs = ConnectionFactory.sendCommand(sql);
		while(rs.next()) {
			if(rs.getString(2).equals(carArr[0]) && rs.getString(3).equals(carArr[1])) {
				sql = "insert into project0.car_offer (amount, customerid, car_id) " + 
						"values (" + amount + ", " + me.getId() + ", " + rs.getInt(1) + "); ";
				ConnectionFactory.insertCommand(sql);
			}
		}
		Customer.customerHome(me);
	}
	
	
}

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.dealership.driver.CarDealership;
import com.dealership.driver.Customer;
import com.dealership.driver.Employee;
import com.dealership.driver.PaymentPlan;

public class CarDealershipTest {

	@Test
	public void testRandom() {
		assertEquals(5,5);
	}
	
	@Test
	public void testInvalidUser() {
		assertFalse(Customer.checkValidUser("abcdef", "wrong"));
	}
	
	@Test
	public void testValidUser() {
		CarDealership.customerList.add(new Customer("joe", "pass"));
		assertTrue(Customer.checkValidUser("joe", "pass"));
	}
	
	@Test
	public void testInvalidUser2() {
		CarDealership.customerList.add(new Customer("joe", "pass"));
		assertFalse(Customer.checkValidUser("joey", "pass"));
	}
	
	@Test
	public void testValidUser2() {
		CarDealership.customerList.add(new Customer("12345", "54321"));
		assertTrue(Customer.checkValidUser("12345", "54321"));
	}
	
	@Test
	public void testSerialize() {
		PaymentPlan planExample = new PaymentPlan(10000, "2012 Ford Mustang", "Username");
		PaymentPlan.serialize(planExample);
		ArrayList<PaymentPlan> testPlan = Customer.deserialize();
		PaymentPlan test = testPlan.get(testPlan.size()-1);
		
		assertEquals(10000, (test.getMoneyOwed()));
		assertTrue(planExample.getCar().equals(test.getCar()));
		assertEquals(166, (test.getMonthlyPayment()));
		assertTrue(planExample.getUser().equals(test.getUser()));
	}
	
	@Test
	public void testSomeWrongSerialize() {
		PaymentPlan planExample = new PaymentPlan(10000, "cool car", "Username");
		PaymentPlan.serialize(planExample);
		ArrayList<PaymentPlan> testPlan = Customer.deserialize();
		PaymentPlan test = testPlan.get(testPlan.size()-1);
		
		assertEquals(10000, (test.getMoneyOwed()));
		assertFalse("lame car".equals(test.getCar()));
		assertNotEquals(100, (test.getMonthlyPayment()));
		assertTrue(planExample.getUser().equals(test.getUser()));
	}
	
	@Test
	public void testAllWrongSerialize() {
		PaymentPlan planExample = new PaymentPlan(10000, "2012 Ford Mustang", "Username");
		PaymentPlan.serialize(planExample);
		PaymentPlan test = Customer.deserialize().get(0);
		
		assertNotEquals(1234, (test.getMoneyOwed()));
		assertFalse("2011 Ford Mustand".equals(test.getCar()));
		assertNotEquals(321, (test.getMonthlyPayment()));
		assertFalse("bob".equals(test.getUser()));
	}
	
	@Test
	public void testMakePayment() {
		PaymentPlan plan = new PaymentPlan(1000, "car", "user");
		PaymentPlan.serialize(plan);
		Customer me = new Customer("user", "pass");
		ArrayList<PaymentPlan> test = Customer.deserialize();
		Customer.makePayment(100, test, me, 1);
		
		assertEquals(900, test.get(0).getMoneyOwed());
	}
	
	@Test
	public void testMakePaymentWrong() {
		PaymentPlan plan = new PaymentPlan(1000, "car", "user");
		PaymentPlan.serialize(plan);
		Customer me = new Customer("user", "pass");
		ArrayList<PaymentPlan> test = Customer.deserialize();
		Customer.makePayment(100, test, me, 1);
		
		assertNotEquals(100, test.get(0).getMoneyOwed());
	}
	
	@Test
	public void testInvalidEmp() {
		Employee.employeeList.put("admin", "admin");
		assertFalse(Employee.checkValidEmp("incorrect", "wrong"));
	}
	
	@Test
	public void testValidEmp() {
		Employee.employeeList.put("admin", "admin");
		assertTrue(Employee.checkValidEmp("admin", "admin"));
	}
}

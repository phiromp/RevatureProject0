import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
		PaymentPlan planExample = new PaymentPlan(6000, "2012 Ford Mustang", "Username");
		PaymentPlan.serialize(planExample);
		PaymentPlan test = Customer.deserialize();
		
		assertEquals(6000, (test.getMoneyOwed()));
		assertTrue(planExample.getCar().equals(test.getCar()));
		assertEquals(100, (test.getMonthlyPayment()));
		assertTrue(planExample.getUser().equals(test.getUser()));
	}
	
	@Test
	public void testSomeWrongSerialize() {
		PaymentPlan planExample = new PaymentPlan(10000, "cool car", "Username");
		PaymentPlan.serialize(planExample);
		PaymentPlan test = Customer.deserialize();
		
		assertEquals(10000, (test.getMoneyOwed()));
		assertFalse("lame car".equals(test.getCar()));
		assertNotEquals(100, (test.getMonthlyPayment()));
		assertTrue(planExample.getUser().equals(test.getUser()));
	}
	
	@Test
	public void testAllWrongSerialize() {
		PaymentPlan planExample = new PaymentPlan(10000, "2012 Ford Mustang", "Username");
		PaymentPlan.serialize(planExample);
		PaymentPlan test = Customer.deserialize();
		
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
		Customer.makePayment(100, plan, me);
		PaymentPlan test = Customer.deserialize();
		
		assertEquals(900, test.getMoneyOwed());
	}
	
	@Test
	public void testMakePaymentWrong() {
		PaymentPlan plan = new PaymentPlan(1000, "car", "user");
		PaymentPlan.serialize(plan);
		Customer me = new Customer("user", "pass");
		Customer.makePayment(100, plan, me);
		PaymentPlan test = Customer.deserialize();
		
		assertNotEquals(100, test.getMoneyOwed());
	}
	
}

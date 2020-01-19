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

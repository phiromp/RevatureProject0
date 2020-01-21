import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.dealership.driver.Car;
import com.dealership.driver.CarDealership;
import com.dealership.driver.Customer;
import com.dealership.driver.Employee;
import com.dealership.driver.PaymentPlan;
import com.dealership.util.ConnectionFactory;

public class CarDealershipTest {

	// remove, this is just to setup the testing file
	@Test
	public void testRandom() {
		assertEquals(5,5);
	}
	
	// method to take car ID, 
	// find in database and return as a nice string
	@Test
	public void testCarToString() throws SQLException {
		// assuming there's a 2012 ford fusion in database with the id of 2
		assertEquals("2012 Ford Fusion",Car.toString(2));
	}
	
	@Test
	public void testConnection() throws SQLException {
		ConnectionFactory c = new ConnectionFactory();
		// assuming there's a 2012 ford fusion in database with the id of 2
		assertNotEquals(null, c.createConnection());
	}

}

package tests;

import static org.junit.Assert.*;
import models.ParkManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test class for ParkManager class
 * @author Jingzhu Guo
 *
 */
public class ParkManagerTest {

	@Before
	public void setUp() throws Exception {
		
		ParkManager manager = new ParkManager("abc@gmail.com", "John", "Wang", "Park Manager");
		manager.addPark("ALkI");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testParkManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPark(ParkManager manager) {
		assertEquals ("same park name","ALKI", manager.getParks() );
	}

	@Test
	public void testCreateJob() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindMyJob() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindVolunteer() {
		fail("Not yet implemented");
	}

}

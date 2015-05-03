package tests;

import static org.junit.Assert.*;
import models.ParkManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkManagerTest {

	@Before
	public void setUp() throws Exception {
		
		ParkManager manager = new ParkManager("abc@gmail.com", "John", "Wang");
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
	public void testAddPark() {
		fail("Not yet implemented");
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

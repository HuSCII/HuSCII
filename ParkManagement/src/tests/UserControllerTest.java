package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import models.UserController;

import org.junit.Before;
import org.junit.Test;

public class UserControllerTest {

	UserController testController;

	/** Create UserController for test use. */
	@Before
	public void setUp() {

		testController = new UserController();
	}

	@Test
	public void testReadUserFile() {

		// Populate the User List from CSV file:
		testController.readUserFile("/testFile.csv");

		// Test when User List has been populated
		assertFalse("The list is supposed to be populated!", testController
				.getUserList().isEmpty());

		// Test the first entry (Albert Einstein)
		assertEquals("Error: Emails are different!", "emc2@relativity.com",
				testController.getUserList().get(0).getEmail());

		assertEquals("Error: First names don't match!", "Albert",
				testController.getUserList().get(0).getFirstName());

		assertEquals("Error: Last names don't match!", "Einstein",
				testController.getUserList().get(0).getLastName());

		assertEquals("Error: roles don't match!", "administrator",
				testController.getUserList().get(0).getRole());

		// Test the last entry (Sailor Moon)
		assertEquals("Error: Emails are different!",
				"sailormoon@moonkingdom.com",
				testController.getUserList().get(19).getEmail());

		assertEquals("Error: First names don't match!", "Serena",
				testController.getUserList().get(19).getFirstName());

		assertEquals("Error: Last names don't match!", "Tsukino",
				testController.getUserList().get(19).getLastName());

		assertEquals("Error: roles don't match!", "volunteer", testController
				.getUserList().get(19).getRole());

	}

	@Test
	public void testWriteUserFile() {

		// Read in text first:
		testController.readUserFile("/testFile.csv");

		// Now write it out as a different name:
		testController.writeUserFile("src/testOutputFile.csv");

		// Now check the new file created:
		testController.readUserFile("/testOutputFile.csv");

		// Check if array is empty, it shouldn't be:
		assertFalse("Error: List shouldn't be empty!", testController
				.getUserList().isEmpty());

		// ! Find a way to compare two CSV Files efficiently. Perhaps Set/diffs

	}

	@Test
	public void testGetUserList() {

		// Read in text first:
		testController.readUserFile("/testFile.csv");

		// User List should not be empty after reading in a file:
		assertFalse("The list is supposed to be empty!", testController
				.getUserList().isEmpty());

	}

	@Test
	public void testGetVolunteers() {

		// Read in text first:
		testController.readUserFile("/testFile.csv");

		// There should be 2 volunteers with "Stark" as last name:
		// (Tony Stark and Nedd Stark are not volunteers and shouldn't appear)
		assertEquals("There should've been two Stark volunteers!", 2,
				testController.getVolunteers("Stark").size());

		// First volunteer should be Arya Stark
		assertEquals("Error: First names don't match!", "Arya", testController
				.getVolunteers("Stark").get(0).getFirstName());

		// Second volunteer should be Robb Stark
		assertEquals("Error: First names don't match!", "Robb", testController
				.getVolunteers("Stark").get(1).getFirstName());
	}

	@Test
	public void testToString() {

		// Read in text first:
		testController.readUserFile("/testFileOneUser.csv");

		// Compare toString output (don't forget the endofline):
		assertEquals("Output not same!",
				"emc2@relativity.com,Einstein,Albert,administrator\r\n",
				testController.toString());
	}
}

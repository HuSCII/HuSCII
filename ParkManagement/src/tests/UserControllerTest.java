/*
 * HuSCII (Group 2)
 * TCSS 360 - Spring '15
 * UserControllerTest.java
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import models.User;
import models.UserController;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing of UserController.
 * 
 * @author Duy Huynh
 * @version 3 May 2015
 *
 */
public class UserControllerTest {

    /** UserController used for testing. */
    private UserController testController;

    /** Create UserController for test use. */
    @Before
    public void setUp() {

        testController = new UserController("/userTestFile.csv");
    }

    /** Test to see if first user was read correctly from file. */
    @Test
    public void testReadUserFileFirstUser() {

        // Test the first entry
        assertEquals("Error: Emails are different!", "emc2@relativity.com",
                     testController.getUserList().get(0).getEmail());

        assertEquals("Error: First names don't match!", "Albert",
                     testController.getUserList().get(0).getFirstName());

        assertEquals("Error: Last names don't match!", "Einstein",
                     testController.getUserList().get(0).getLastName());

        assertEquals("Error: roles don't match!", "administrator",
                     testController.getUserList().get(0).getRole());

    }

    /** Test to see if last user was read correctly from file. */
    @Test
    public void testReadUserFileLastUser() {

        final List<User> userList = testController.getUserList();

        // Test the last entry
        assertEquals("Error: Emails are different!",
                     "damonsalvatore@tvd.com",
                     userList.get(userList.size() - 1).getEmail());

        assertEquals("Error: First names don't match!", "Damon",
                     userList.get(userList.size() - 1).getFirstName());

        assertEquals("Error: Last names don't match!", "Salvatore",
                     userList.get(userList.size() - 1).getLastName());

        assertEquals("Error: roles don't match!", "volunteer",
                     userList.get(userList.size() - 1).getRole());
    }

    /** Write User file then read it in again and test if it's not empty. */
    @Test
    public void testWriteUserFileIfEmpty() {

        // Now write it out as a different name:
        testController.writeUserFile("/userFileTestOutput.csv");

        // Now check the new file created:
        testController.readUserFile("src/userFileTestOutput.csv");

        // Check if array is empty, it shouldn't be:
        assertFalse("Error: List shouldn't be empty!", testController
                        .getUserList().isEmpty());

    }

    /** Write User file then read it in again and if first user is correct. */
    @Test
    public void testWriteUserFileFirstUser() {

        // Save the first user of the current file
        final User firstUser = testController.getUserList().get(0);

        // Now write it out as a different name:
        testController.writeUserFile("/userFileTestOutput.csv");

        // Now read in the file that was just created:
        testController.readUserFile("src/userFileTestOutput.csv");

        // Save the first user of the written file
        final User writtenFileFirstUser = testController.getUserList().get(0);

        // Check if array is empty, it shouldn't be:
        assertEquals("Users not same!", firstUser, writtenFileFirstUser);
    }

    /** Write User file then read it in again and if last user is correct. */
    @Test
    public void testWriteUserFileLastUser() {

        // Save the last user of the current file
        int lastSpot = testController.getUserList().size() - 1;
        final User lastUser = testController.getUserList().get(lastSpot);

        // Now write it out as a different name:
        testController.writeUserFile("/userFileTestOutput.csv");

        // Now read in the file that was just created:
        testController.readUserFile("src/userFileTestOutput.csv");

        // Save the last user of the written file
        lastSpot = testController.getUserList().size() - 1;
        final User writtenFileLastUser = testController.getUserList().get(lastSpot);

        // Check if array is empty, it shouldn't be:
        assertEquals("Users not same!", lastUser, writtenFileLastUser);
    }

    /** Test the getter of the User List. */
    @Test
    public void testGetUserList() {

        // User List should not be empty after reading in a file:
        assertFalse("The list is supposed to be empty!", testController
                        .getUserList().isEmpty());

    }

    /** Test the getter of the Volunteers by last name List. */
    @Test
    public void testGetVolunteers() {

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

    /** Test the getter of the parks for a park manager. */
    @Test
    public void testGetManagedParks() {

        // Grab Ron Swanson's parks
        final List<String> parks = testController.getManagedParks("anonymous@nowhere.com");

        // Test to see if there are indeed three parks in his list
        assertEquals("There should've been three parks!", 3, parks.size());

        // Check if first park matches"
        assertEquals("Should've been The Pit", "The Pit", parks.get(0));

        // Check if second park matches"
        assertEquals("Should've been Pawnee National Park", "Pawnee National Park",
                     parks.get(1));

        // Check if third park matches"
        assertEquals("Should've been World's Smallest Park", "World's Smallest Park",
                     parks.get(2));

    }

    /** Test the getter of the User List. */
    // @Test
    public void testToString() {

        // Compare toString output (don't forget the endofline):
        assertEquals("Output not same!",
                     "emc2@relativity.com,Einstein,Albert,administrator\r\n",
                     testController.toString());
    }
}

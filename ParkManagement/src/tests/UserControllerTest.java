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
        testController.readUserFile("/userTestFile.csv");
    }

    /** Tests to see if a List object was created that should contain Users. */
    @Test
    public void testReadUserFile() {

        // Test when User List has been populated
        assertFalse("The list is supposed to be populated!", testController
                        .getUserList().isEmpty());
    }

    /** Test reading in user file and creating the Users. */
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

    /** Tests to see if the last User created is the last user in file. */
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

    @Test
    public void testWriteUserFile() {

        // Now write it out as a different name:
        testController.writeUserFile("src/testOutputFile.csv");

        // Now check the new file created:
        // testController.readUserFile("/testOutputFile.csv");

        // Check if array is empty, it shouldn't be:
        assertFalse("Error: List shouldn't be empty!", testController
                        .getUserList().isEmpty());

        // ! Find a way to compare two CSV Files efficiently. Perhaps Set/diffs

    }

    @Test
    public void testGetUserList() {

        // User List should not be empty after reading in a file:
        assertFalse("The list is supposed to be empty!", testController
                        .getUserList().isEmpty());

    }

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

    @Test
    public void testToString() {

        // Compare toString output (don't forget the endofline):
        assertEquals("Output not same!",
                     "emc2@relativity.com,Einstein,Albert,administrator\r\n",
                     testController.toString());
    }
}

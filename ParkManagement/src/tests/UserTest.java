/*
 * HuSCII (Group 2)
 * TCSS 360 - Spring '15
 * UserControllerTest.java
 */

package tests;

import static org.junit.Assert.assertEquals;

import models.User;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing of User.
 * 
 * @author Duy Huynh
 * @version 27 May 2015
 *
 */
public class UserTest {

    /** User to test against. */
    private User testDummy;

    /** Before each test, create this dummy User. */
    @Before
    public void setUp() {
        testDummy = new User("abc@123.com", "Doe", "John", "volunteer");
    }

    /** Test to see if the overridden toString() works. */
    @Test
    public void testToString() {
        assertEquals("Strings should've been the same!", "abc@123.com,Doe,John,volunteer",
                     testDummy.toString());
    }

    /** Test to see if the overridden equals() works. */
    @Test
    public void testEquals() {

        // Create temp user to test against
        final User tempUser = new User("abc@123.com", "Doe", "John", "volunteer");

        assertEquals("These users should've been the same!", tempUser, testDummy);
    }

    /** Test to see if the overridden hashCode() works. */
    @Test
    public void testHashCode() {

        // Create temp user to test against
        final User tempUser = new User("abc@123.com", "Doe", "John", "volunteer");

        assertEquals("These users should've had same hashcode!", tempUser.hashCode(),
                     testDummy.hashCode());

    }

}

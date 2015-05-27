
package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is a JUnit Test Suite for all JUnit test cases.
 */
@RunWith(Suite.class)
@SuiteClasses({BusinessRulesTest.class, JobControllerTester.class, JobTest.class,
                ParkManagerTest.class, UserControllerTest.class, UserTest.class})
public class AllTests {
    // Everything can be done by using annotation above.
}

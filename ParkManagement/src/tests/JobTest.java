/*
 * This class represents JUnit Test Case for Job class.
 * Group 2 - HuSCII
 * TCSS 360 Spring '15
 * 
 * JobTest.java 
 */

package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import models.Job;
import models.User;
import models.Job.WorkCategories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class represents JUnit Test Case for Job class.
 * 
 * @author Putthida Samrith
 * @version May/31/2015
 *
 */
public class JobTest {

    /** This represents different jobs for testing. */
    private Job testJob1, testJob2, testJob3, testJob4, testJob5, testJob6, testJob7;

    /** This represents volunteers sign up for light work category. */
    private Map<String, WorkCategories> volunteerLight;

    /** This represents volunteers sign up for medium work category. */
    private Map<String, WorkCategories> volunteerMed;

    /** This represents volunteers sign up for heavy work category. */
    private Map<String, WorkCategories> volunteerHeavy;

    /** This represents volunteers sign up for all work categories. */
    private Map<String, WorkCategories> volunteer;

    /** This represents map without initialize. */
    private Map<String, WorkCategories> volunteerNull;
    
    /** This represents a user. */
    private User user;

    /**
     * Initialize the objects.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        //represent a user (volunteer)
        user = new User("volLight@gmail.com", "Mickey", "Mouse", "volunteer");
        
        //represent volunteers sign up for light work category
        volunteerLight = new HashMap<String, WorkCategories>();
        volunteerLight.put("volLight@gmail.com", WorkCategories.LIGHT);
        volunteerLight.put("volLight1@gmail.com", WorkCategories.LIGHT);

        //represent volunteers sign up for medium work category
        volunteerMed = new HashMap<String, WorkCategories>();
        volunteerMed.put("volMed@gmail.com", WorkCategories.MEDIUM);

        //represent volunteers sign up for heavy work category
        volunteerHeavy = new HashMap<String, WorkCategories>();
        volunteerHeavy.put("volHeavy@gmail.com", WorkCategories.HEAVY);

        //represent volunteers sign up for all work category
        volunteer = new HashMap<String, WorkCategories>();
        volunteer.put("volLight2@gmail.com", WorkCategories.LIGHT);
        volunteer.put("volMed1@gmail.com", WorkCategories.MEDIUM);
        volunteer.put("volHeavy1@gmail.com", WorkCategories.HEAVY);

        //represent past date
        testJob1 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming",
                           "05/08/2014 09:30 AM", "05/08/2014 10:30 AM", 1, 5, 2, 5, 3,
                           5, volunteerNull);

        //represent future date
        testJob2 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup",
                           "05/08/2016 09:30 AM", "05/10/2014 09:30 AM", 1, 5, 2, 5, 3,
                           5, volunteerLight);

        //represent date within 3 months from today
        testJob3 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup",
                           "06/08/2015 09:30 AM", "06/11/2015 09:30 AM", 1, 5, 2, 5, 3,
                           5, volunteerMed);

        //represent current volunteers meet the maximum number of requirement in light category
        testJob4 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup",
                           "06/08/2015 09:30 AM", "06/08/2015 11:30 AM", 2, 2, 0, 1, 0,
                           1, volunteerHeavy);

        //represent current volunteers meet the maximum number of requirement in medium category
        testJob5 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup",
                           "06/08/2015 09:30 AM", "06/08/2015 11:30 AM", 0, 1, 1, 1, 0,
                           1, volunteerMed);

        //represent current volunteers meet the maximum number of requirement in heavy category
        testJob6 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup",
                           "06/08/2015 09:30 AM", "06/08/2015 11:30 AM", 0, 1, 0, 1, 1,
                           1, volunteerHeavy);

        //represent all full work categories
        testJob7 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup",
                           "06/08/2015 09:30 AM", "06/08/2015 11:30 AM", 1, 1, 1, 1, 1,
                           1, volunteer);
    }

    @After
    public void tearDown() throws Exception {
        volunteerLight = null;
        volunteerMed = null;
        volunteerHeavy = null;
        volunteer = null;

        testJob1 = null;
        testJob2 = null;
        testJob3 = null;
        testJob4 = null;
        testJob5 = null;
        testJob6 = null;
        testJob7 = null;
    }

    @Test
    public void testOnParkName() {
        assertEquals("Park name is different; not the same park.", "Wright Park",
                     testJob1.getParkName());
    }

    @Test
    public void testOnJobName() {
        assertEquals("Job name should be the same.", "Tree Trimming", testJob1.getJobName());
    }

    @Test
    public void testOnMaxVolunteerNeeded() {
        assertEquals("Max volunteer needed", 15, testJob1.getVolunteerMax());
    }

    @Test
    public void testOnStartDate() {
        assertEquals("Date is not the same.", "05/08/2014 09:30 AM",
                     new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(testJob1.getStartDate()
                                                                       .getTime()));
    }

    @Test
    public void testOnEndDate() {
        assertEquals("Date is not the same.", "05/08/2014 10:30 AM",
                     new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(testJob1.getEndDate()
                                                                       .getTime()));
    }

    @Test
    public void testOnNumberOfCurrentVolunteerInEachWorkCat() {
        assertEquals("Number of volunteer in light work category is not equal", 1,
                     testJob1.getCurrentLight());
        assertEquals("Number of volunteer in medium work category is not equal", 2,
                     testJob1.getCurrentMedium());
        assertEquals("Number of volunteer in heavy work category is not equal", 3,
                     testJob1.getCurrentHard());
    }

    @Test
    public void testOnNumberOfMaxVolunteerInEachWorkCat() {
        assertEquals("Number of volunteer in light work category is not equal", 5,
                     testJob1.getMaxLight());
        assertEquals("Number of volunteer in medium work category is not equal", 5,
                     testJob1.getMaxMedium());
        assertEquals("Number of volunteer in heavy work category is not equal", 5,
                     testJob1.getMaxHard());
    }

    @Test
    public void testOnVolunteerSignedUpForLight() {
        assertEquals("Volunteer who signed up for Light",
                     volunteerLight.get("volLight@gmail.com"), WorkCategories.LIGHT);
    }

    @Test
    public void testOnVolunteerSignedUpForMedium() {
        assertEquals("Volunteer who signed up for Medium",
                     volunteerMed.get("volMed@gmail.com"), WorkCategories.MEDIUM);
    }

    @Test
    public void testOnVolunteerSignedUpForHeavy() {
        assertEquals("Volunteer who signed up for Heavy",
                     volunteerHeavy.get("volHeavy@gmail.com"), WorkCategories.HEAVY);
    }

    @Test
    public void testAddVolunteerOnLightWhenNotFull() {
        assertTrue("Volunteer cannot be added.",
                   testJob3.addVolunteer("volLight@gmail.com",
                                         volunteerLight.get("volLight@gmail.com")));
    }

    @Test
    public void testAddVolunteerOnLightWhenFull() {
        assertFalse("LIGHT work category is full!",
                    testJob4.addVolunteer("volLight@gmail.com",
                                          volunteerLight.get("volLight@gmail.com")));
    }

    @Test
    public void testAddVolunteerOnMediumWhenNotFull() {
        assertTrue("Volunteer cannot be added.",
                   testJob3.addVolunteer("volMed@gmail.com",
                                         volunteerMed.get("volMed@gmail.com")));
    }

    @Test
    public void testAddVolunteerOnMediumWhenFull() {
        assertFalse("MEDIUM work category is full!",
                    testJob5.addVolunteer("volMed@gmail.com",
                                          volunteerMed.get("volMed@gmail.com")));
    }

    @Test
    public void testAddVolunteerOnHeavyWhenNotFull() {
        assertTrue("Volunteer cannot be added.",
                   testJob3.addVolunteer("volHeavy11@gmail.com",
                                         volunteerHeavy.get("volHeavy@gmail.com")));
    }

    @Test
    public void testAddVolunteerOnHeavyWhenFull() {
        assertFalse("HEAVY work category is full!",
                    testJob6.addVolunteer("volHeavy@gmail.com",
                                          volunteerHeavy.get("volHeavy@gmail.com")));
    }

    @Test
    public void testIsJobFullOnAllWorkCatFull() {
        assertTrue("All work categories are full.", testJob7.isJobFull());
    }

    @Test
    public void testIsJobFullOnWorkCatNotFull() {
        assertFalse("At least one of the work category is not full.", testJob6.isJobFull());
    }

    @Test
    public void testToStringOnHaveVolunteerSignUp() {
        assertEquals("Output is not the same.",
                     "walderfrey@gmail.com,Wright Park Tacoma,Trash Pickup,"
                                     + "05/08/2016 09:30 AM,05/10/2014 09:30 AM,"
                                     + "1,5,2,5,3,5,volLight@gmail.com,volLight1@gmail.com",
                                     testJob2.toString());
    }

    @Test
    public void testToStringOnNoVolunteerSignUp() {
        assertEquals("Output is not the same.",
                     "walderfrey@gmail.com,Wright Park,Tree Trimming,05/08/2014 "
                                     + "09:30 AM,05/08/2014 10:30 AM,1,5,2,5,3,5",
                                     testJob1.toString());
    }
    
    @Test
    public void testVolunteerSignUpOnLight() {
        assertEquals("Output is not the same.", "volLight@gmail.com,volLight1@gmail.com",
                     testJob2.getVolunteerString());
    }
    
    @Test
    public void testVolunteerSignUpOnMedium() {
        assertEquals("Output is not the same.", "volMed@gmail.com",
                     testJob3.getVolunteerString());
    }
    
    @Test
    public void testVolunteerSignUpOnHeavy() {
         assertEquals("Output is not the same.", "volHeavy@gmail.com",
                     testJob4.getVolunteerString());
    }

    @Test
    public void testContainOnHaveVolunteer() {
        assertTrue("This job doesn't contain a volunteer with that email.", testJob2.contains(user.getEmail()));
    }
    
    @Test
    public void testContainOnNoVolunteer() {
        assertFalse("This job contain a volunteer with that email.", testJob3.contains(user.getEmail()));
    }
    
}

/*
 * Group 2 - HuSCII
 * 
 * JobTest.java
 * This class represents JUnit Test Case for Job class.
 */

package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import models.Job;
import models.Job.WorkCategories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class represents JUnit Test Case for Job class.
 * 
 * @author Putthida Samrith
 * @version May/26/2015
 *
 */
public class JobTest {

    /** This represents different jobs for testing.*/
    Job testJob1, testJob2, testJob3, testJob4, testJob5, testJob6, testJob7;

    /** This represents volunteers sign up for light work category. */
    Map<String, WorkCategories> volunteerLight;

    /** This represents volunteers sign up for medium work category. */
    Map<String, WorkCategories> volunteerMed;

    /** This represents volunteers sign up for heavy work category. */
    Map<String, WorkCategories> volunteerHeavy;

    /** This represents volunteers sign up for all work categories. */
    Map<String, WorkCategories> volunteer;

    /** This represents map without initialize. */
    Map<String, WorkCategories> volunteerNull;


    /**
     * Initialize the objects.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

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
        testJob1 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "05/08/2014 09:30 AM", 
                           "05/08/2014 10:30 AM", 1, 5, 2, 5, 3, 5, volunteerNull);

        //represent future date
        testJob2 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "05/08/2016 09:30 AM", 
                           "05/10/2014 09:30 AM", 1, 5, 2, 5, 3, 5, volunteerLight);

        //represent date within 3 months from today
        testJob3 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           "06/11/2015 09:30 AM", 1, 5, 2, 5, 3, 5, volunteerMed);

        //represent current volunteers meet the maximum number of requirement in light category
        testJob4 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           "06/08/2015 11:30 AM", 2, 2, 0, 1, 0, 1, volunteerHeavy);

        //represent current volunteers meet the maximum number of requirement in medium category
        testJob5 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           "06/08/2015 11:30 AM", 0, 1, 1, 1, 0, 1, volunteerMed);

        //represent current volunteers meet the maximum number of requirement in heavy category
        testJob6 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           "06/08/2015 11:30 AM", 0, 1, 0, 1, 1, 1, volunteerHeavy);

        //represent all full work categories
        testJob7 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           "06/08/2015 11:30 AM", 1, 1, 1, 1, 1, 1, volunteer);
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
    public void test() {

        //test park name
        assertEquals("Park name is different; not the same park.", "Wright Park", 
                     testJob1.getParkName());

        //test the maximum volunteers needed for that job in all work categories
        assertEquals("Max volunteer needed", 15, testJob1.getVolunteerMax());

        //test job name
        assertEquals("Job name should be the same.", "Tree Trimming", testJob1.getJobName());

        //test start date
        assertEquals("Date is not the same.", "05/08/2014 09:30 AM", 
                     new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(testJob1.getStartDate().getTime()));

        //test end date
        assertEquals("Date is not the same.", "05/08/2014 10:30 AM", 
                     new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(testJob1.getEndDate().getTime()));

        //test the number of current and max volunteer in each work category
        assertEquals("Number of volunteer in light work category is not equal", 1, testJob1.getCurrentLight());
        assertEquals("Number of volunteer in light work category is not equal", 5, testJob1.getMaxLight());
        assertEquals("Number of volunteer in medium work category is not equal", 2, testJob1.getCurrentMedium());
        assertEquals("Number of volunteer in medium work category is not equal", 5, testJob1.getMaxMedium());
        assertEquals("Number of volunteer in heavy work category is not equal", 3, testJob1.getCurrentHard());
        assertEquals("Number of volunteer in heavy work category is not equal", 5, testJob1.getMaxHard());

        //test volunteer signs up for each work category
        assertEquals("Volunteer who signed up for Light", volunteerLight.get("volLight@gmail.com"), WorkCategories.LIGHT);
        assertEquals("Volunteer who signed up for Medium", volunteerMed.get("volMed@gmail.com"), WorkCategories.MEDIUM);
        assertEquals("Volunteer who signed up for Heavy", volunteerHeavy.get("volHeavy@gmail.com"), WorkCategories.HEAVY);
    }

    @Test
    public void testAddVolunteerOnLight() {
        assertTrue("Volunteer cannot be added.", testJob3.addVolunteer("volLight@gmail.com", volunteerLight.get("volLight@gmail.com")));

        //add volunteer when work category is full
        assertFalse("LIGHT work category is full!", testJob4.addVolunteer("volLight@gmail.com", volunteerLight.get("volLight@gmail.com")));
    }

    @Test
    public void testAddVolunteerOnMedium() {
        assertTrue("Volunteer cannot be added.", testJob3.addVolunteer("volMed@gmail.com", volunteerMed.get("volMed@gmail.com")));

        //add volunteer when work category is full
        assertFalse("MEDIUM work category is full!", testJob5.addVolunteer("volMed@gmail.com", volunteerMed.get("volMed@gmail.com")));
    }

    @Test
    public void testAddVolunteerOnHeavy() {
        assertTrue("Volunteer cannot be added.", testJob3.addVolunteer("volHeavy11@gmail.com", volunteerHeavy.get("volHeavy@gmail.com")));

        //add volunteer when work category is full
        assertFalse("HEAVY work category is full!", testJob6.addVolunteer("volHeavy@gmail.com", volunteerHeavy.get("volHeavy@gmail.com")));
    }

    @Test
    public void testIsJobFull() {
        assertTrue("All work categories are full.", testJob7.isJobFull());
        assertFalse("At least one of the work category is not full.", testJob6.isJobFull());
    }

    @Test
    public void testToString() {

        //when there is no volunteer sign up for that job
        assertEquals("Output is not the same.",
                     "walderfrey@gmail.com,Wright Park,Tree Trimming,05/08/2014 "
                                     + "09:30 AM,05/08/2014 10:30 AM,1,5,2,5,3,5",
                                     testJob1.toString());

        //when there are volunteers sign up for that job
        assertEquals("Output is not the same.", "walderfrey@gmail.com,Wright Park Tacoma,Trash Pickup,"
                        + "05/08/2016 09:30 AM,05/10/2014 09:30 AM,"
                        + "1,5,2,5,3,5,volLight@gmail.com,volLight1@gmail.com", testJob2.toString());
    }

    @Test
    public void testVolunteerSignUp() {
        assertEquals("Output is not the same.", "volLight@gmail.com,volLight1@gmail.com", testJob2.volunteerSignUp());
        assertEquals("Output is not the same.", "volMed@gmail.com", testJob3.volunteerSignUp());
        assertEquals("Output is not the same.", "volHeavy@gmail.com", testJob4.volunteerSignUp());
    }
}

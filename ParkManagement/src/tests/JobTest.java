/*
 * Group 2 - HuSCII
 * This class represents JUnit Test Case for Job class.
 */

package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import models.Job;
import models.Job.WorkCatagories;

import org.junit.Before;
import org.junit.Test;

/**
 * This class represents JUnit Test Case for Job class.
 * 
 * @author Putthida Samrith
 * @version May/4/2015
 *
 */
public class JobTest {

    Job testJob1, testJob2, testJob3;
    Job testDate1, testDate2;
    GregorianCalendar jobDate;
    Map<String, WorkCatagories> signedVolunteers = new HashMap<String, WorkCatagories>();

    /**
     * Initialize the objects.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        signedVolunteers.put("janedoe@gmail.com", WorkCatagories.LIGHT);

        //represent past date
        testJob1 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "05/08/2014 09:30 AM", 
                           7, 1, 5, 2, 5, 3, 5, signedVolunteers);

        //represent future date
        testJob2 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "05/08/2016 09:30 AM", 
                           48, 1, 5, 2, 5, 3, 5, signedVolunteers);

        //represent date within 3 months from today
        testJob3 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           50, 1, 5, 2, 5, 3, 5, signedVolunteers);
    }

    @Test
    public void test() {
        assertEquals("Park name is different; not the same park.", "Wright Park", 
                     testJob1.getParkName());
        assertEquals("Max volunteer needed", 15, testJob1.getVolunteerMax());
        assertEquals("Job name should be the same.", "Tree Trimming", testJob1.getJobName());
        assertEquals("Date ", "05/08/2014 09:30 AM", 
                     new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(testJob1.getDate().getTime()));
        assertEquals("Job duration should be the same.", 7, testJob1.getJobDuration());
        assertEquals("Number of volunteer in light work category is not equal", 1, testJob1.getCurrentLight());
        assertEquals("Number of volunteer in light work category is not equal", 5, testJob1.getMaxLight());
        assertEquals("Number of volunteer in medium work category is not equal", 2, testJob1.getCurrentMedium());
        assertEquals("Number of volunteer in medium work category is not equal", 5, testJob1.getMaxMedium());
        assertEquals("Number of volunteer in heavy work category is not equal", 3, testJob1.getCurrentHard());
        assertEquals("Number of volunteer in heavy work category is not equal", 5, testJob1.getMaxHard());
        assertEquals("Volunteer who signed up for Light", signedVolunteers.get("janedoe@gmail.com"), WorkCatagories.LIGHT);
    }

    @Test
    public void testAddVolunteer() {
        assertEquals("Current volunteers in light category!", 1 , testJob1.getCurrentLight());
        //assertTrue("Add volunteers", testJob1.addVolunteer("thida@gmail.com", WorkCatagories.HEAVY) );
    }

    @Test
    public void testIsCompleted() {
        assertTrue("A job is not completed.", testJob1.isCompleted(testJob1.getDate())); //past job
        assertFalse("A job is completed.", testJob2.isCompleted(testJob2.getDate())); //future job
        assertFalse("A job is completed.", testJob3.isCompleted(testJob3.getDate())); //future job
    }

    @Test
    public void testCheckJobDuration() {
        assertTrue("A job may not be scheduled that lasts more than two days.", testJob1.checkJobDuration());
        assertFalse("A job may be scheduled that lasts more than two days.", 
                    testJob2.checkJobDuration()); //last more than 2 days
        assertFalse("A job may be scheduled that lasts more than two days.", 
                    testJob3.checkJobDuration()); //last more than 2 days
    }

    @Test
    public void testFutureMonth() {
        assertFalse("A job is less than 3 months in the future.", testJob1.futureDate(testJob1.getDate()));

        assertTrue("A job is more than 3 months in the future.", 
                   testJob1.futureDate(testJob2.getDate())); //more than 3 months in the future

        assertFalse("A job is more than 3 months in the future.", testJob1.futureDate(testJob3.getDate()));
    }

    @Test
    public void testValiDate() {
        assertFalse("A job may be added that is in the past or "
                        + "more than three months in the future.", testJob1.valiDate(testJob1.getDate()));
        assertFalse("A job may be added that is in the past or "
                        + "more than three months in the future.", testJob2.valiDate(testJob2.getDate()));
        assertTrue("A job may not be added that is in the past or "
                        + "more than three months in the future.", testJob3.valiDate(testJob3.getDate()));
    }
}

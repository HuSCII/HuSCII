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
import models.Job.WorkCategories;

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

    Job testJob1, testJob2, testJob3, testJob4, testJob5, testJob6, testJob7;
    Job testDate1, testDate2;
    GregorianCalendar jobDate;
    Map<String, WorkCategories> volunteerLight = new HashMap<String, WorkCategories>();
    Map<String, WorkCategories> volunteerMed = new HashMap<String, WorkCategories>();
    Map<String, WorkCategories> volunteerHeavy = new HashMap<String, WorkCategories>();
    Map<String, WorkCategories> volunteer = new HashMap<String, WorkCategories>();

    Map<String, WorkCategories> volunteerNull;
    
    /**
     * Initialize the objects.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        volunteerLight.put("volLight@gmail.com", WorkCategories.LIGHT);
        volunteerLight.put("volLight1@gmail.com", WorkCategories.LIGHT);
        
        volunteerMed.put("volMed@gmail.com", WorkCategories.MEDIUM);
        volunteerHeavy.put("volHeavy@gmail.com", WorkCategories.HEAVY);

        volunteer.put("volHeavy1@gmail.com", WorkCategories.HEAVY);
        volunteer.put("volHeavy2@gmail.com", WorkCategories.HEAVY);
        volunteer.put("volHeavy3@gmail.com", WorkCategories.HEAVY);
        
        //represent past date
        testJob1 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "05/08/2014 09:30 AM", 
                           7, 1, 5, 2, 5, 3, 5, volunteerNull);

        //represent future date
        testJob2 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "05/08/2016 09:30 AM", 
                           48, 1, 5, 2, 5, 3, 5, volunteerLight);

        //represent date within 3 months from today
        testJob3 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           50, 1, 5, 2, 5, 3, 5, volunteerLight);

        //represent current volunteers meet the maximum number of requirement in light category
        testJob4 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           50, 2, 2, 0, 1, 0, 1, volunteerLight);

        //represent current volunteers meet the maximum number of requirement in medium category
        testJob5 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           50, 0, 1, 1, 1, 0, 1, volunteerMed);

        //represent current volunteers meet the maximum number of requirement in heavy category
        testJob6 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           8, 0, 1, 0, 1, 1, 1, volunteerHeavy);
        
        //represent all full work categories
        testJob7 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/08/2015 09:30 AM", 
                           8, 1, 1, 1, 1, 1, 1, volunteer);

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

}

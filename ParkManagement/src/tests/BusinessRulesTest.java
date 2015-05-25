/*
 * This class represents JUnit Test Case for Business Rules.
 * 
 * Group 2 - HuSCII
 * TCSS 360, Spring 2015
 */

package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.BusinessRules;
import models.Job;
import models.Job.WorkCategories;
import models.JobController;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class represents JUnit Test Case for Business Rules.
 * 
 * @author Putthida Samrith
 * @version 5/25/2015
 */
public class BusinessRulesTest {

    /** This represents first job. */
    private Job testJob1;

    /** This represents second job. */
    private Job testJob2;

    /** This represents third job. */
    private Job testJob3;

    /**This represents test job. */
    private Job testJob4, testJob5, testJob6, testJob7, testJob8;

    /** Map of volunteer's email and work categories */
    private Map<String, WorkCategories> signedVolunteers = new HashMap<String, WorkCategories>();

    /** Stores a copy of all jobs in the system. */
    private ArrayList<Job> allJobs;

    /** when there is only one job in the system. */
    private ArrayList<Job> oneJob, jobList;

    /** Instant field. */
    private JobController jobController;

    /**
     * Initialize the objects.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        jobController = new JobController("/jobFile.csv");

        //when there is only one job in the list
        oneJob = new ArrayList<Job>();
        oneJob.add(testJob1);

        //when there are more than 30 jobs in the list
        allJobs = new ArrayList<Job>();
        for (int i = 0; i < 31; i++) {
            allJobs.add(testJob1);
        }

        //List of jobs
        jobList = new ArrayList<Job>();
        jobList.add(testJob1);
        jobList.add(testJob2);

        //represent past date
        testJob1 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "05/08/2014 09:30 AM", 
                           7, 1, 5, 2, 5, 3, 5, signedVolunteers);

        //represent future date more than 3 months from today
        testJob2 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "05/06/2016 09:30 AM", 
                           48, 1, 5, 2, 5, 3, 5, signedVolunteers);

        //represent future date within 3 months from today
        testJob3 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/10/2015 09:30 AM", 
                           50, 1, 5, 2, 5, 3, 5, signedVolunteers); 
        
        //represent today date
        testJob4 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "05/25/2015 09:30 AM", 
                           7, 1, 5, 2, 5, 3, 5, signedVolunteers);
        
        //represent date exactly 3 months from now
        testJob5 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "08/25/2015 09:30 AM", 
                           7, 1, 5, 2, 5, 3, 5, signedVolunteers);
        
        //represent yesterday date
        testJob6 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "05/24/2015 09:30 AM", 
                           7, 1, 5, 2, 5, 3, 5, signedVolunteers);
        
        //represent tomorrow date
        testJob7 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "05/26/2015 09:30 AM", 
                           7, 1, 5, 2, 5, 3, 5, signedVolunteers);
        
        //represent date one day before 3 months from now
        testJob8 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "08/24/2015 09:30 AM", 
                           7, 1, 5, 2, 5, 3, 5, signedVolunteers);
    }

    @After
    public void tearDown() throws Exception {
        testJob1 = null;
        testJob2 = null;
        testJob3 = null;
        testJob4 = null;
        testJob5 = null;
        testJob6 = null;
        testJob7 = null;
        testJob8 = null;
    }

    @Test
    public void testIsCompleted() {
        assertFalse("A job is not completed.", BusinessRules.isCompleted(testJob1.getDate())); //past job
        assertTrue("A job is completed.", BusinessRules.isCompleted(testJob2.getDate())); //future job
        assertTrue("A job is completed.", BusinessRules.isCompleted(testJob3.getDate())); //future job
    }

    @Test
    public void testFutureDate() {
        assertFalse("A job is less than 3 months in the future.", BusinessRules.futureDate(testJob1.getDate()));

        assertTrue("A job is more than 3 months in the future.", 
                   BusinessRules.futureDate(testJob2.getDate())); //more than 3 months in the future

        assertFalse("A job is more than 3 months in the future.", BusinessRules.futureDate(testJob3.getDate()));

    }
    
    @Test
    public void testValiDateOnPastDate() {
        assertFalse("A job may not be added that is in the past.", BusinessRules.valiDate(testJob1.getDate()));
    }
    
    @Test
    public void testValiDateOnYesterday() {
        assertFalse("A job may not be added that is in the past.", BusinessRules.valiDate(testJob6.getDate()));
    }
    
    @Test
    public void testValiDateOnToday() {
        assertTrue("A job may not be added that is in the past or "
                        + "more than three months in the future.", BusinessRules.valiDate(testJob4.getDate()));
    }
    
    @Test
    public void testValiDateOnTomorrow() {
        assertTrue("A job may not be added that is in the past.", BusinessRules.valiDate(testJob7.getDate()));
    }
    
    @Test
    public void testValiDateOnBeforeFuture() {
        assertTrue("A job may not be added that is in the past or "
                        + "more than three months in the future.", BusinessRules.valiDate(testJob3.getDate()));
    }
    
    @Test
    public void testValiDateOnOneDayBeforeFuture() {
        assertTrue("A job may not be added that is in the past.", BusinessRules.valiDate(testJob8.getDate()));
    }
    
    @Test
    public void testValiDateOnFuture() {
        assertFalse("A job is more than three months in the future.", BusinessRules.valiDate(testJob5.getDate()));
    }
    
    @Test
    public void testValiDateOnAfterFuture() {
        assertFalse("A job may not be added if it is more "
                        + "than three months in the future.", BusinessRules.valiDate(testJob2.getDate()));
    } 

    @Test
    public void testCheckMaxJobs() {
        assertEquals("Number of jobs in the list doesn't match.", 31, allJobs.size());
        assertTrue("There're less than 30 jobs in the system.", BusinessRules.checkMaxJobs(allJobs));
        assertFalse("There're more than 30 jobs in the system.", BusinessRules.checkMaxJobs(oneJob));
    }

    @Test
    public void testCheckJobWeek() {
        assertTrue("Break Business Rule!", BusinessRules.checkJobWeek(jobController.getAllJobs(), testJob2.getDate()));

        assertTrue("Break Business Rule!", BusinessRules.checkJobWeek(jobController.getAllJobs(), testJob1.getDate()));
    }

    @Test
    public void testCheckJobDuration() {
        
        //A job lasts less than two days.
        assertTrue("A job may not be scheduled that lasts more than two days.", BusinessRules.checkJobDuration(testJob1));
        
        //A job lasts exactly two days.
        assertTrue("A job may not be scheduled that lasts more than two days.", BusinessRules.checkJobDuration(testJob2));

        //A job lasts more than two days.
        assertFalse("A job may be scheduled that lasts more than two days.", 
                    BusinessRules.checkJobDuration(testJob3));
    }
}

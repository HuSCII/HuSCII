package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import models.BusinessRules;
import models.Job;
import models.Job.WorkCatagories;
import models.JobController;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author 
 *
 */
public class BusinessRulesTest {

    /** This represents first job. */
    private Job testJob1;

    /** This represents second job. */
    private Job testJob2;

    /** This represents third job. */
    private Job testJob3;

    /** This represents business rule. */
    private BusinessRules br;

    /** Map of volunteer's email and work categories */
    private Map<String, WorkCatagories> signedVolunteers = new HashMap<String, WorkCatagories>();

    /** Stores a copy of all jobs in the system. */
    private ArrayList<Job> allJobs;

    /** when there is only one job in the system. */
    private ArrayList<Job> oneJob, jobList;

    GregorianCalendar todayDate;

    private JobController jobController, jobController1;

    /**
     * Initialize the objects.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        //        GregorianCalendar todayDate = 
        //                        (GregorianCalendar) GregorianCalendar.getInstance();

        todayDate = new GregorianCalendar();

        br = new BusinessRules();

        jobController = new JobController("/jobFile.csv");

        //when there is only one job in the list
        oneJob = new ArrayList<Job>();
        oneJob.add(testJob1);

        //when there are more than 30 jobs in the list
        allJobs = new ArrayList<Job>();
        for (int i = 0; i < 31; i++) {
            allJobs.add(testJob1);
        }

        //
        jobList = new ArrayList<Job>();
        jobList.add(testJob1);
        jobList.add(testJob2);
        //jobList.add(testJob3);

        //represent past date
        testJob1 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming", "05/08/2014 09:30 AM", 
                           7, 1, 5, 2, 5, 3, 5, signedVolunteers);

        //represent future date within 3 months from today
        testJob2 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "05/06/2016 09:30 AM", 
                           48, 1, 5, 2, 5, 3, 5, signedVolunteers);

        //represent future date more than 3 months from today
        testJob3 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup", "06/10/2015 09:30 AM", 
                           50, 1, 5, 2, 5, 3, 5, signedVolunteers);
    }

    @After
    public void tearDown() throws Exception {
        testJob1 = null;
        testJob2 = null;
        testJob3 = null;
        br = null;

    }

    @Test
    public void testIsCompleted() {
        assertFalse("A job is not completed.", br.isCompleted(testJob1.getDate())); //past job
        assertTrue("A job is completed.", br.isCompleted(testJob2.getDate())); //future job
        assertTrue("A job is completed.", br.isCompleted(testJob3.getDate())); //future job
    }

    @Test
    public void testValiDate() {
        assertFalse("A job may be added that is in the past or "
                        + "more than three months in the future.", br.valiDate(testJob1.getDate()));
        assertFalse("A job may be added that is in the past or "
                        + "more than three months in the future.", br.valiDate(testJob2.getDate()));
        assertTrue("A job may not be added that is in the past or "
                        + "more than three months in the future.", br.valiDate(testJob3.getDate()));
    }

    @Test
    public void testFutureDate() {
        assertFalse("A job is less than 3 months in the future.", br.futureDate(testJob1.getDate()));

        assertTrue("A job is more than 3 months in the future.", 
                   br.futureDate(testJob2.getDate())); //more than 3 months in the future

        assertFalse("A job is more than 3 months in the future.", br.futureDate(testJob3.getDate()));

    }

    @Test
    public void testCheckMaxJobs() {
        assertEquals("Number of jobs in the list doesn't match.", 31, allJobs.size());
        assertTrue("There're less than 30 jobs in the system.", br.checkMaxJobs(allJobs));
        assertFalse("There're more than 30 jobs in the system.", br.checkMaxJobs(oneJob));
    }

    @Test
    public void testCheckJobWeek() {
        assertTrue("Break Business Rule!", br.checkJobWeek(jobController.getAllJobs(), testJob2.getDate()));

        assertTrue("Break Business Rule!", br.checkJobWeek(jobController.getAllJobs(), testJob1.getDate()));
    }

}

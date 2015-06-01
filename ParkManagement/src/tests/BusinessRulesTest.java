/*
 * This class represents JUnit Test Case for Business Rules.
 * 
 * Group 2 - HuSCII TCSS 360, Spring 2015
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
import models.User;

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

    /** This represents test job. */
    private Job testJob1, testJob2, testJob3, testJob4, testJob5, testJob6, testJob7,
    testJob8;

    /** Map of volunteer's email and work categories */
    private Map<String, WorkCategories> signedVolunteers;

    /** Stores a copy of all jobs in the system. */
    private ArrayList<Job> allJobs;

    /** when there is only one job in the system. */
    private ArrayList<Job> oneJob, jobList;

    /** when there are 30 jobs in the system. */
    private ArrayList<Job> thirtyJob;

    /** Instance field. */
    private JobController jobController;

    private User user, user1;

    /**
     * Initialize the objects.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        user = new User("mickeymouse@disney.com", "Mickey", "Mouse", "volunteer");
        user1 = new User("mickeymouse1@disney.com", "Mickey1", "Mouse1", "volunteer");

        jobController = new JobController("/jobFile.csv");
        
        signedVolunteers = new HashMap<String, WorkCategories>();
        signedVolunteers.put("mickeymouse@disney.com", WorkCategories.LIGHT);

        // when there is only one job in the list
        oneJob = new ArrayList<Job>();
        oneJob.add(testJob1);

        // when there are more than 30 jobs in the list
        allJobs = new ArrayList<Job>();
        for (int i = 0; i < 31; i++) {
            allJobs.add(testJob1);
        }

        // when there are 30 jobs in the system
        thirtyJob = new ArrayList<Job>();
        for (int i = 0; i <= 30; i++) {
            thirtyJob.add(testJob3);
        }

        // List of jobs
        jobList = new ArrayList<Job>();
        jobList.add(testJob1);
        jobList.add(testJob2);

        // represent past date
        testJob1 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming",
                           "05/08/2014 09:30 AM", "05/08/2014 02:30 PM", 1, 5, 2, 5, 3,
                           5, signedVolunteers);

        // represent future date more than 3 months from today
        testJob2 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup",
                           "05/06/2016 09:30 AM", "05/08/2016 09:30 AM", 1, 5, 2, 5, 3,
                           5, signedVolunteers);

        // represent future date within 3 months from today
        testJob3 = new Job("walderfrey@gmail.com", "Wright Park Tacoma", "Trash Pickup",
                           "07/08/2015 09:30 AM", "07/13/2015 11:30 AM", 1, 5, 2, 5, 3,
                           5, signedVolunteers);

        // represent today date
        testJob4 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming",
                           "06/01/2015 09:30 AM", "06/01/2015 11:30 AM", 1, 5, 2, 5, 3,
                           5, signedVolunteers);

        // represent date exactly 3 months from now
        testJob5 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming",
                           "09/01/2015 09:30 AM", "09/01/2015 11:30 AM", 1, 5, 2, 5, 3,
                           5, signedVolunteers);

        // represent yesterday date
        testJob6 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming",
                           "05/31/2015 09:30 AM", "05/31/2015 11:30 AM", 1, 5, 2, 5, 3,
                           5, signedVolunteers);

        // represent tomorrow date
        testJob7 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming",
                           "06/02/2015 09:30 AM", "06/02/2015 11:30 AM", 1, 5, 2, 5, 3,
                           5, signedVolunteers);

        // represent date one day before 3 months from now
        testJob8 = new Job("walderfrey@gmail.com", "Wright Park", "Tree Trimming",
                           "08/31/2015 09:30 AM", "08/31/2015 09:30 AM", 1, 5, 2, 5, 3,
                           5, signedVolunteers);
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

        user = null;
        user1 = null;
        jobController = null;

    }

    @Test
    public void testIsCompletedOnPastDate() {
        assertTrue("A job is not completed.",
                   BusinessRules.isCompleted(testJob1.getStartDate()));
    }

    @Test
    public void testIsCompletedOnFutureDate() {
        assertFalse("A job is completed.", BusinessRules.isCompleted(testJob2.getStartDate()));
    }

    @Test
    public void testIsCompletedOnTodayDate() {
        assertFalse("A job is completed.", BusinessRules.isCompleted(testJob4.getStartDate()));
    }

    @Test
    public void testFutureDateOnPastDate() {
        assertFalse("A job is less than 3 months in the future.",
                    BusinessRules.futureDate(testJob1.getStartDate()));
    }

    @Test
    public void testFutureDateOnDateMoreThanThreeMonthsFromNow() {
        assertTrue("A job is more than 3 months in the future.",
                   BusinessRules.futureDate(testJob2.getStartDate()));
    }

    @Test
    public void testFutureDateOnDateExactlyThreeMonthsFromNow() {
        assertTrue("A job is more than 3 months in the future.",
                   BusinessRules.futureDate(testJob5.getStartDate()));
    }

    @Test
    public void testValiDateOnPastDate() {
        assertFalse("A job may not be added that is in the past.",
                    BusinessRules.valiDate(testJob1.getStartDate()));
    }

    @Test
    public void testValiDateOnYesterday() {
        assertFalse("A job may not be added that is in the past.",
                    BusinessRules.valiDate(testJob6.getStartDate()));
    }

    @Test
    public void testValiDateOnToday() {
        assertTrue("A job may not be added that is in the past or "
                        + "more than three months in the future.",
                        BusinessRules.valiDate(testJob4.getStartDate()));
    }

    @Test
    public void testValiDateOnTomorrow() {
        assertTrue("A job may not be added that is in the past.",
                   BusinessRules.valiDate(testJob7.getStartDate()));
    }

    @Test
    public void testValiDateOnBeforeFuture() {
        assertTrue("A job may not be added that is in the past or "
                        + "more than three months in the future.",
                        BusinessRules.valiDate(testJob3.getStartDate()));
    }

    @Test
    public void testValiDateOnOneDayBeforeFuture() {
        assertTrue("A job may not be added that is in the past.",
                   BusinessRules.valiDate(testJob8.getStartDate()));
    }

    @Test
    public void testValiDateOnFuture() {
        assertFalse("A job is more than three months in the future.",
                    BusinessRules.valiDate(testJob5.getStartDate()));
    }

    @Test
    public void testValiDateOnAfterFuture() {
        assertFalse("A job may not be added if it is more "
                        + "than three months in the future.",
                        BusinessRules.valiDate(testJob2.getStartDate()));
    }

    @Test
    public void testCheckMaxJobsOnJobLessThan30() {
        assertFalse("There're more than 30 jobs in the system.",
                    BusinessRules.checkMaxJobs(oneJob));
    }

    @Test
    public void testCheckMaxJobsOnJobMoreThan30() {
        assertEquals("Number of jobs in the list doesn't match.", 31, allJobs.size());

        assertTrue("There're less than 30 jobs in the system.",
                   BusinessRules.checkMaxJobs(allJobs));
    }

    @Test
    public void testCheckMaxJobsOnJobExactlyAt30() {
        assertTrue("There're less than 30 jobs in the system.",
                   BusinessRules.checkMaxJobs(thirtyJob));
    }

    @Test
    public void testCheckJobWeek() {
        assertTrue("Break Business Rule!", BusinessRules.checkJobWeek(jobController.getAllJobs(), testJob1.getStartDate(), null));

    }

    @Test
    public void testcheckTwoJobsSameDay() {
        assertFalse("You already signed up for another job on the same day.", 
                    BusinessRules.checkTwoJobsSameDay(user1, jobController.getAllJobs(), testJob1.getStartDate()));
    }
    
    @Test
    public void testCheckJobDurationOnJobLastLessThan2Days() {
        // A job lasts less than two days.
        assertTrue("A job may not be scheduled that lasts more than two days.",
                   BusinessRules.checkJobDuration(testJob1.getStartDate(),
                                                  testJob1.getEndDate()));
    }

    @Test
    public void testCheckJobDurationOnJobLastMoreThan2Days() {
        // A job lasts more than two days.
        assertFalse("A job may be scheduled that lasts more than two days.", 
                    BusinessRules.checkJobDuration(testJob3.getStartDate(),
                                                   testJob3.getEndDate()));
    }

    @Test
    public void testCheckJobDurationOnJobLastExactly2Days() {
        // A job lasts exactly two days.
        assertTrue("A job may not be scheduled that lasts more than two days.", 
                   BusinessRules.checkJobDuration(testJob2.getStartDate(), testJob2.getEndDate()));
    }

}

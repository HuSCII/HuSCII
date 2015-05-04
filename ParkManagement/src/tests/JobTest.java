/*
 * Group 2 - HuSCII
 * This class represents JUnit Test Case for Job class.
 */

package tests;

import static org.junit.Assert.*;
import java.util.GregorianCalendar;
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

	/**
	 * Initialize the objects.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		testJob1 = new Job("Cherry Park", "Trash Pickup", "5/8/2015 9:30AM", 2);
		testJob2 = new Job("Urban Park", "Tree Trimming", "5/18/2016 10:30AM", 7);
		testJob3 = new Job("Earth Park", "Tree Trimming", "6/18/2015 10:30AM", 30);
	}

	@Test
	public void test() {
		assertEquals("Park name is different; not the same park.", "Cherry Park", testJob1.getParkName());
		assertEquals("Empty volunteer list", testJob1.getVolunteerMax(), 0);
		assertEquals("Job name should be the same.", "Trash Pickup", testJob1.getJobName());
		assertEquals("Job duration should be the same.", 2, testJob1.getJobDuration());
	}

	@Test
	public void testAddVolunteer() {
		assertEquals("Current volunteers in light category!", 0 , testJob1.getCurrentLight());
		
		//assertTrue("Add volunteers", testJob1.addVolunteer("thida@gmail.com", WorkCatagories.HEAVY) );
	}

	@Test
	public void testIsCompleted() {
		jobDate = new GregorianCalendar();
		jobDate.set(2015, 5, 8, 9, 30);
		
		assertFalse("A job is completed (past job).", testJob1.isCompleted(jobDate));
	}
	
	@Test
	public void testCheckJobDuration() {
		assertTrue("A job may not be scheduled that lasts more than two days.", testJob1.checkJobDuration());
		assertFalse("A job may not be scheduled that lasts more than two days.", testJob3.checkJobDuration());
	}
	
	@Test
	public void testValiDate() {   
		jobDate = new GregorianCalendar();
		jobDate.set(2015, 5, 8, 9, 30);
		
		assertTrue("A job may not be added that is in the past or "
				+ "more than three months in the future.", testJob1.valiDate(jobDate));
	}
}

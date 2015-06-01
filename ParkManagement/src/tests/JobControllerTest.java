package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import models.Job;
import models.JobController;

import org.junit.Before;
import org.junit.Test;


public class JobControllerTest {
	//variables
	JobController jc;
	
	@Before
	public void init(){
		jc = new JobController(null);
		jc.loadJobData("tests/jobTestFile.csv");
	}

	@Test
	public void testJobController() {
		//tests load
		JobController jobController = new JobController(null);
		assertTrue(jobController.getAllJobs().isEmpty());
		jobController.loadJobData("tests/jobTestFile.csv");
        assertFalse(jobController.getAllJobs().isEmpty());
	}

	@Test
	public void testAddJob() {
		int s = jc.getAllJobs().size();
		Job j = new Job("e", "p", "ja", "06/06/2015 09:30 AM", "06/06/2015 10:30 AM", 
                        0, 0, 0, 0, 0, 0, null);
		jc.addJob(j);
		assertTrue(s+1==jc.getAllJobs().size());
	}

	@Test
	public void testGetUpcomingJobs() {
		int s = jc.getUpcomingJobs().size();
		Job pastJob = new Job("e", "p", "a", "05/30/2015 09:30 AM", "05/30/2015 11:30 AM", 
		                      0, 0, 0, 0, 0, 0, null);
		Job futureJob = new Job("e", "p", "b", "06/06/2015 09:30 AM", "06/06/2015 11:30 AM", 
		                        0, 0, 0, 0, 0, 0, null);
		jc.addJob(pastJob);
		jc.addJob(futureJob);
		assertTrue(s+1==jc.getUpcomingJobs().size());
	}
	
	@Test
	public void testWriteJobData() {
	    Job j = new Job("e", "p", "jw", "06/06/2015 09:30 AM", "06/06/2015 10:30 AM", 
	                    0, 0, 0, 0, 0, 0, null);
	    jc.addJob(j);
		jc.writeJobData("tests/jobFileTestOutput.csv");
		jc.loadJobData("tests/jobFileTestOutput.csv");
		assertFalse(jc.getAllJobs().isEmpty());		
	}

}

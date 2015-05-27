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
	Job job1, job2, job3, job4;
	JobController jc;
	
	@Before
	public void init(){
		jc = new JobController(null);
		job1 = new Job("walderfrey@gmail.com","King's Landing", "Beheading Cleanup", "07/08/2015 09:30 AM", 
		               "07/08/2015 09:30 AM", 0, 5, 0, 4, 0, 9, null);
		job2 = new Job("walderfrey@gmail.com","King's Landing", "Beheading Cleanup", "07/08/2015 09:30 AM", 
                       "07/08/2015 09:30 AM", 0, 5, 0, 4, 0, 9, null);
		job3 = new Job("walderfrey@gmail.com","King's Landing", "Beheading Cleanup", "07/08/2015 09:30 AM", 
                       "07/08/2015 09:30 AM", 0, 5, 0, 4, 0, 9, null);
	}

	@Test
	public void testJobController() {
		//tests load
		JobController jobController = new JobController(null);
		assertFalse(jobController.getAllJobs().isEmpty());
	}

	@Test
	public void testAddJob() {
		int s = jc.getAllJobs().size();
		jc.addJob(job1);
		assertTrue(s+1==jc.getAllJobs().size());
	}

	@Test
	public void testGetUpcomingJobs() {
		int s = jc.getUpcomingJobs().size();
		assertTrue(s==4);
	}
	
	@Test
	public void testWriteJobData() {
		jc.writeJobData("src/jobFileTest.txt");
		JobController jobController = new JobController("src/jobFileTest.txt");
		assertFalse(jobController.getAllJobs().isEmpty());		
	}

}

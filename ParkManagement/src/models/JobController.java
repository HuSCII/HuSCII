package models;
import java.util.List;


public class JobController {

	private List<Job> allJobs;
	
	JobController() {
		
	}
	//*****PUBLIC METHODS*****//
	public boolean addJob(Job job) {
		//should I take in a job or the info of a 
		//job to create one?
		return false;
	}
	
	public List<Job> getUpcomingJobs() {
		return null;
	}
	
	public List<Job> getAllJobs() {
		//needs to check 
		return allJobs; //make sure to send a clone -Ian
	}
	
	//*****PRIVATE METHODS*****//
	private boolean loadJobData() {
		return true;
	}
	
}

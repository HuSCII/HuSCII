package models;


import java.util.List;
import java.util.ArrayList;

/**
 * park manager
 * @author Jingzhu Guo
 *
 */
public class ParkManager extends User {
	
	private ArrayList<String> parkNames;
	private List<Job> myJob;
	private JobController jobController = new JobController();
	private Job parkJob;
	
	/**
	 * Create a User of the parks manager.
	 * 
	 * @param email
	 *            User's email address.
	 * @param lastName
	 *            User's last name.
	 * @param firstName
	 *            User's first name.
	 * @param role
	 *            User's role.
	 */
	public ParkManager (final String email, final String firstName,
			final String lastName, final String role) {
		
		super(email, firstName, lastName, role);
		this.parkNames = new ArrayList<String>();
		this.myJob = new ArrayList<Job>();
	}
	
	/**
	 * add park in Park management system
	 * @param park
	 */
	public void addPark(String park) {
		if(park.length() > 0 ) {
		
			parkNames.add(park); 
		}
		
	}
	
	/**
	 * return the list of parks 
	 * @return
	 */
	public List<String> getParks() {
		
		ArrayList<String> parkList = (ArrayList<String>) parkNames.clone();
		return parkList;
		
	}
	
	/**
	 * create park jobs
	 * @param parkName
	 * @param jobName
	 * @param date
	 * @param jobDuration
	 */
	public void createJob (int jobID, String parkName, String jobName,
						String date, int jobDuration) {
		
		parkJob = new Job(jobID, parkName, jobName, date, jobDuration);
		
		jobController.addJob(parkJob);
			
		
	}
	
	/**
	 * find the job that park manager submitted
	 * @return List of Upcoming job that I manage.
	 */
	public List<Job> getMyJobs (int jobID) {
		List<Job> upcomingJob = jobController.getUpcomingJobs();
		
		if(upcomingJob.contains(jobID)) {
			int i = upcomingJob.indexOf(jobID);
			myJob.add(upcomingJob.get(i));	
			return myJob;
		}
			return null;
		
	}
	
	/**
	 * need to work on this method
	 * @param volunteer
	 * @param job
	 * @return
	 */
	public List<String> findVolunteer (Volunteer volunteer, Job job) {
		
		return null;
	}
	
	/**
	 * print out park manager's info.
	 */
	public String toString() {
		
		return super.toString() + "  Park Lists: " + parkNames.toString();
	}
	
	
	/**
	 * main method to test the ParkManager class
	 * @param agrs
	 */
	public static void main(String[] agrs) {
		
		ParkManager manager = new ParkManager("judeguo83@gmail.com","Jude","Guo", "Park Manager");
		
		manager.addPark("alki");
		manager.addPark("let's go");
		
		manager.createJob(123, "alki", "trash picking up", "5/8/2015 9:30 am", 2);
		manager.createJob(121, "alki", "trash picking up", "5/8/2015 9:30 am", 2);
		manager.createJob(120, "alki", "trash picking up", "5/8/2015 9:30 am", 2);
		
		List<Job> myJobs = manager.getMyJobs(123);
		
		
		System.out.println(manager.toString());
		System.out.println(myJobs.toString());
		
	}
}

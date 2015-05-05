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
	private ArrayList<Job> myJob;
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
	public void createJob (String parkName, String jobName,
						String date, int jobDuration) {
		
		parkJob = new Job(parkName, jobName, date, jobDuration);
		
		jobController.addJob(parkJob);
		myJob.add(parkJob);	
		
	}
	
	/**
	 * find the job that park manager submitted
	 * @return List of sth
	 */
	public void getMyJobs() {
		
		System.out.println(myJob.toString());
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
	 * need to work on this method
	 * @return The number of volunteers currently in the job.
	 */
//	private void checkJobCapacity(String parkName) {
//		
////		ArrayList<Job> allJob = jobController.getAllJobs();  //I think we need a job ID
////		if (allJob.contains(parkName)) {					// to make each job unique.	
////			int jobIndex = allJob.indexOf(parkName);
////				System.out.println(allJob.get(jobIndex).getVolunteerCount());
////			
////		}
//		System.out.println( 0 );
//	}
//	
//	/**
//	 * need to work on this method
//	 * @param job
//	 */
//	private void checkWeekAvailibility (Job job) {
//		
//		System.out.println(0);
//	}
	
	/**
	 * main method to test the ParkManager class
	 * @param agrs
	 */
	public static void main(String[] agrs) {
		
		ParkManager manager = new ParkManager("judeguo83@gmail.com","Jude","Guo", "Park Manager");
		
		manager.addPark("alki");
		manager.addPark("let's go");
		
		manager.createJob("alki", "trash picking up", "5/8/2015 9:30 am", 2);
		manager.createJob("alki", "trash picking up", "5/8/2015 9:30 am", 2);
		manager.createJob("alki", "trash picking up", "5/8/2015 9:30 am", 2);
		
		manager.findMyJob();
		
		
		System.out.println(manager.toString());
		
	}
}

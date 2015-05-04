package models;


import java.util.ArrayList;

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
		
		parkNames.add(park);
		
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
	 * @return
	 */
	public void findMyJob() {
		
		System.out.println(myJob.toString());
	}
	
	public String findVolunteer (Volunteer volunteer, Job job) {
		
		return null;
	}
	
	/**
	 * print out park manager's info.
	 */
	public String toString() {
		
		return super.toString() + " " + parkNames.toString();
	}
	
	
	/**
	 * 
	 * @return The number of volunteers currently in the job.
	 */
	private void checkJobCapacity(String parkName) {
		
		ArrayList<Job> allJob = jobController.getAllJobs();  //I think we need a job ID
		if (allJob.contains(parkName)) {					// to make each job unique.	
			int jobIndex = allJob.indexOf(parkName);
				System.out.println(allJob.get(jobIndex).getVolunteerCount());
			
		}
		System.out.println( 0 );
	}
	
	private void checkWeekAvailibility (Job job) {
		
		
	}
	
	
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

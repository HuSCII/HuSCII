package models;


import java.util.ArrayList;

public class ParkManager extends User {
	
	private ArrayList<String> parkNames;
	private ArrayList<Job> myJob;
	
	public ParkManager (final String email, final String firstName,
			final String lastName) {
		
		super(email, firstName, lastName);
		this.parkNames = new ArrayList<String>();
		this.myJob = new ArrayList<Job>();
	}
	
	public void addPark(String park) {
		
		parkNames.add(park);
		
	}
	
	
	public void createJob (String parkName, String jobName,
						String date, int jobDuration) {
		
		Job parkJob = new Job(parkName, jobName, date, jobDuration);
		myJob.add(parkJob);
		
		System.out.println(parkJob.toString());
		
		
	}
	
	public String findMyJob() {
		
		return myJob.toString();
	}
	
	public String findVolunteer (Volunteer volunteer, Job job) {
		
		return null;
	}
	
	public String toString() {
		
		return super.toString() + " " + parkNames.toString();
	}
	
	//I do not understand what this method does?
	private int checkJobCapacity() {
		
		return 0;
	}
	
	private void checkWeekAvailibility (Job job) {
		
		
	}
	
	public static void main(String[] agrs) {
		
		ParkManager manager = new ParkManager("judeguo83@gmail.com","Jude","Guo");
		
		manager.addPark("alki");
		manager.addPark("let's go");
		
		manager.createJob("alki", "trash picking up", "1/12/2015", 1);
		manager.findMyJob();
		
		
		System.out.println(manager.toString());
		
	}
}

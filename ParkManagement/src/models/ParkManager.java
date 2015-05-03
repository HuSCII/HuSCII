package models;


import java.util.ArrayList;

public class ParkManager extends User {
	
	private ArrayList<String> parkNames;
	
	public ParkManager (final String email, final String firstName,
			final String lastName) {
		
		super(email, firstName, lastName);
		this.parkNames = new ArrayList<String>();
	}
	
	public void addPark(String park) {
		
		parkNames.add(park);
		
	}
	
	
	public void createJob (Job parkJob) {
		
		
	}
	
	public String findMyJob() {
		
		return null;
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
		
		System.out.println(manager.toString());
		
		
	}
}

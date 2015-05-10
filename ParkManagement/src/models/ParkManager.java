/*
 * HuSCII (Group 2)
 * TCSS 360 - Spring '15
 * UserController.java
 */

package models;

import java.util.List;
import java.util.ArrayList;

/**
 * park manager
 * 
 * @author Jingzhu Guo
 * @version 3 May 2015
 */
public class ParkManager extends User {

	/** Collection of the jobs this pm manages. */
	private List<Job> myJobs;

	/** Job controller. */
	private JobController jobController = new JobController();

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
	public ParkManager(final String email, final String firstName,
			final String lastName, final String role) {

		super(email, firstName, lastName, role);
		this.myJobs = new ArrayList<Job>();
	}

	/**
	 * Add job to park management system.
	 * 
	 * @param parkName Name of the park.
	 * @param jobName Name of the job.
	 * @param date Start date of job.
	 * @param jobDuration Length of job in hours.
	 */
	public void addJob(int jobID, String parkName, String jobName,
			String date, int jobDuration) {

		Job parkJob = new Job(jobID, parkName, jobName, date, jobDuration);

		jobController.addJob(parkJob);

	}

	/**
	 * find the job that park manager submitted
	 * 
	 * @return List of Upcoming job that I manage.
	 */
	public List<Job> getMyJobs(int jobID) {
		List<Job> upcomingJob = jobController.getUpcomingJobs();

		if (upcomingJob.contains(jobID)) {
			int i = upcomingJob.indexOf(jobID);
			myJobs.add(upcomingJob.get(i));
			return myJobs;
		}
		return null;

	}

	/**
	 * need to work on this method
	 * 
	 * @param volunteer
	 * @param job
	 * @return
	 */
	public List<String> findVolunteer(Volunteer volunteer, Job job) {

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
	 * 
	 * @param agrs
	 */
	public static void main(String[] agrs) {

		ParkManager manager = new ParkManager("judeguo83@gmail.com", "Jude",
				"Guo", "park manager");

		manager.addPark("alki");
		manager.addPark("let's go");

		manager.addJob(123, "alki", "trash picking up", "5/8/2015 9:30 am",
				2);
		manager.addJob(121, "alki", "trash picking up", "5/8/2015 9:30 am",
				2);
		manager.addJob(120, "alki", "trash picking up", "5/8/2015 9:30 am",
				2);

		System.out.println(manager.toString());

	}
}

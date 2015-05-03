package models;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;


public class Job {

	private String parkName;
	private String jobName;
	private GregorianCalendar date;
	private int jobDuration; //in hours
	private int currentLight;
	private int maxLight;
	private int currentMedium;
	private int maxMedium;
	private int currentHard;
	private int maxHard;
	private int volunteerMax;
	private Map signedVolunteers;

	/** A set of email values for each volunteer in this job. */
	private Set<String> volunteers;

	//init still incomplete - Ian
	Job(final String parkName, final String jobName, 
			final String date, final int jobDuration) {
		//should a job check if its allowed to be created,
		// i.e. ask jobController if the max jobs is reached
		//or should job controller takes in the data and create
		//a job only if max isn't met? -Ian
		this.parkName = parkName;
		this.jobName = jobName;
		//parse formatted string 'date' format:"m/d/yyyy hh:mmAM" see jobFile
		this.date = new GregorianCalendar(year, month, 
				dayOfMonth, hourOfDay, minute);
	}

	public enum WorkCatagories
	{
		LIGHT, MEDIUM, HEAVY;
	}

	public void addVolunteer(String email, WorkCatagories workCat) throws JobFullException {

		if (isJobFull()) {
			throw new JobFullException(getJobName() + "is already full.");
		
		} else if (contains(email)) {
			throw new VolunteerStateException("Volunteer is already in " + getJobName() + "list.");

		} else {

			switch(workCat) {
			case LIGHT:
				if ((getCurrentLight() < getMaxLight())) {
					volunteers.add(email);
				} 
				break;
			
			case MEDIUM:
				if ((getCurrentMedium() < getMaxMedium())) {
					volunteers.add(email);
				}
				break;
			case HEAVY:
				if (getCurrentHard() < getMaxHard()) {
					volunteers.add(email);
				}
				break;
			}
		}
	}

	/**
	 * @return True if this job can no longer accept volunteers.
	 */
	public boolean isJobFull() {
		return getVolunteerMax() <= volunteers.size();
	}
	
	/**
	 * Used to check if the passed volunteer is in this job
	 * @param email The volunteer to check if they are in 
	 * 					 this job as a volunteer.
	 * @return True if they are, false if they are not.
	 */
	public boolean containsVolunteer(String email) {
		return volunteers.contains(email);
	}
	/**
	 * A condensed version of containsVolunteer that checks to see if the volunteer
	 * is in the job as a volunteer.
	 * @param email Volunteer's email
	 * @return True if the passed volunteer is in the job period, false
	 * 			otherwise. 
	 */
	public boolean contains(String email) {
		return containsVolunteer(email);
	}

	private boolean isCompleted() {
		return true;
	}

	private boolean checkJobDuration() {
		return true;
	}

	private boolean valiDate() {
		return true;
	}

	public String toString() {

		return parkName + " " + jobName + " " + date + " " + jobDuration; 
	}



	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getCurrentLight() {
		return currentLight;
	}

	public void setCurrentLight(int currentLight) {
		this.currentLight = currentLight;
	}

	public int getMaxLight() {
		return maxLight;
	}

	public void setMaxLight(int maxLight) {
		this.maxLight = maxLight;
	}

	public int getCurrentMedium() {
		return currentMedium;
	}

	public void setCurrentMedium(int currentMedium) {
		this.currentMedium = currentMedium;
	}

	public int getMaxMedium() {
		return maxMedium;
	}

	public void setMaxMedium(int maxMedium) {
		this.maxMedium = maxMedium;
	}

	public int getCurrentHard() {
		return currentHard;
	}

	public void setCurrentHard(int currentHard) {
		this.currentHard = currentHard;
	}

	public int getMaxHard() {
		return maxHard;
	}

	public void setMaxHard(int maxHard) {
		this.maxHard = maxHard;
	}

	/**
	 * @return The max number of volunteers for this job.
	 */
	public int getVolunteerMax() {
		return volunteerMax;
	}


	/**
	 * An exception thrown when an operation is attempted on a full 
	 * job. 
	 */
	@SuppressWarnings("serial")
	public class JobFullException extends RuntimeException {
		public JobFullException(String message) {
			super(message);
		}
	}
	
	/**
	 * An exception thrown when an operation is attempted between
	 * a volunteer and a job, but something goes wrong.
	 *
	 */
	@SuppressWarnings("serial")
	public class VolunteerStateException extends RuntimeException {
		public VolunteerStateException(String message) {
			super(message);
		}
	}

}
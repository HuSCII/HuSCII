package models;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	public Job(final String parkName, final String jobName, 
			final String date, final int jobDuration) {

		this.parkName = parkName;
		this.jobName = jobName;
		this.jobDuration = jobDuration;

		setDate(date);
		//parse formatted string 'date' format:"m/d/yyyy hh:mmAM" see jobFile
	}

	/**
	 * 
	 * @author PutthidaSR
	 */
	public enum WorkCatagories {
		LIGHT, MEDIUM, HEAVY;
	}

	/**
	 * 
	 * @param email
	 * @param workCat
	 * @throws JobFullException
	 */
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
					currentLight++;
				} 
				break;
			case MEDIUM:
				if ((getCurrentMedium() < getMaxMedium())) {
					volunteers.add(email);
					currentMedium++;
				}
				break;
			case HEAVY:
				if (getCurrentHard() < getMaxHard()) {
					volunteers.add(email);
					currentHard++;
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

	/**
	 * @return The max number of volunteers for this job.
	 */
	public int getVolunteerMax() {
		return volunteerMax;
	}

	/**
	 * @return The number of volunteers currently in the job.
	 */
	public int getVolunteerCount() {
		return volunteers.size();
	}

	/**
	 * Comparing the job date, check if the job is completed or still a pending job.
	 * 
	 * @return true if the job is already completed (past job); otherwise, false.
	 */
	private boolean isCompleted(GregorianCalendar today) {

		if(today.compareTo(date) < 0) {
			return false;
		} else if (today.compareTo(date) == 0) {
			return false;
		} else {
			return true;
		}
	}

	//	/**
	//	 * Compares this job object to another job object based on calendar time.
	//	 * @other The other job to compare.
	//	 * 
	//	 */
	//	public int compareTo(Job other) {
	//		int result = date.compareTo(other.date);
	//		//Same date, compare time.
	//		if(result == 0){
	//			result = date.getTime().compareTo(other.date.getTime());
	//		}
	//		return result;
	//	}
	//
	//	/**
	//	 * Compares the time/date of this job to the passed date/time.
	//	 * @param date The date to compare with.
	//	 */
	//	public int compareToDate(GregorianCalendar date) {
	//		return date.compareTo(date);
	//	}

	//in progress
	private boolean checkJobDuration() {
		return true;
	}

	//in progress
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

	public void setDate(String date) {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		try {
			Date aDate = formatter.parse(date);
			this.date = new GregorianCalendar();
			this.date.setTime(aDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 */
	@SuppressWarnings("serial")
	public class VolunteerStateException extends RuntimeException {
		public VolunteerStateException(String message) {
			super(message);
		}
	}

}
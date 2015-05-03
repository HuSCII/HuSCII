package models;
import java.util.GregorianCalendar;
import java.util.Map;


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
	private Map signedVolunteers;
	
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
	
	public String toString() {
		
		return parkName + " " + jobName + " " + date + " " + jobDuration; 
	}
	
}
import java.util.GregorianCalendar;
import java.util.Map;


public class Job {
	
	private String parkName;
	private String jobName;
	private GregorianCalendar date;
	private int jobDuration;
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
		this.parkName = parkName;
		this.jobName = jobName;
		//parse formatted string 'date'
		this.date = new GregorianCalendar(year, month, 
				dayOfMonth, hourOfDay, minute);
	}

}

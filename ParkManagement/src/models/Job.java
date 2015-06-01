/*
 * This class represents park job that volunteers sign up for. Group 2 - HuSCII
 * TCSS 360, Spring 2015
 */

package models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents park job that volunteers sign up for.
 * 
 * @author Putthida Samrith
 * @version 5/31/2015
 */
public class Job implements Serializable {

    /** Delimiter used in string methods to separate values. */
    private static final String DELIM_STD = ",";

    /** Email for Park Manager who created job. */
    private String parkManagerEmail;

    /** Name of the park. */
    private String parkName;

    /** Name of the job. */
    private String jobName;

    /** Date of a job using GregorianCalendar class. */
    private GregorianCalendar startDate;

    /** Date of a job using GregorianCalendar class. */
    private GregorianCalendar endDate;

    /** The current number of volunteer for light work category. */
    private int currentLight;

    /** The maximum number of volunteer for light work category. */
    private int maxLight;

    /** The current number of volunteer for medium work category. */
    private int currentMedium;

    /** The maximum number of volunteer for medium work category. */
    private int maxMedium;

    /** The current number of volunteer for heavy work category. */
    private int currentHeavy;

    /** The maximum number of volunteer for heavy work category. */
    private int maxHeavy;

    /** The maximum number of volunteer. */
    private int volunteerMax;

    /** Maps of volunteer's email, and work categories. */
    private Map<String, WorkCategories> volunteers;

    /**
     * This represents a constructor method.
     * 
     * @param jobID unique ID number of each job.
     * @param parkName Name of the park.
     * @param jobName Name of the job.
     * @param date The start date of the job. * @param date The end date of the
     *            job.
     */
    public Job(final String parkManagerEmail, final String parkName, final String jobName,
               final String startDate, final String endDate, final int currentLight,
               final int maxLight, final int currentMedium, final int maxMedium,
               final int currentHeavy, final int maxHeavy,
               Map<String, WorkCategories> volunteers) {

        this.parkManagerEmail = parkManagerEmail;
        this.parkName = parkName;
        this.jobName = jobName;

        setStartDate(startDate);
        setEndDate(endDate);

        this.currentLight = currentLight;
        this.maxLight = maxLight;
        this.currentMedium = currentMedium;
        this.maxMedium = maxMedium;
        this.currentHeavy = currentHeavy;
        this.maxHeavy = maxHeavy;

        if (volunteers == null) {
            this.volunteers = new HashMap<String, Job.WorkCategories>();
        }
        else {
            this.volunteers = volunteers;
        }

        assert parkManagerEmail != null;
        assert parkName != null;
        assert jobName != null;
        assert startDate != null;
        assert endDate != null;
        assert currentLight >= 0;
        assert currentMedium >= 0;
        assert currentHeavy >= 0;
        assert maxLight >= 0;
        assert maxMedium >= 0;
        assert maxHeavy >= 0;
        assert volunteers != null;
    }

    /**
     * A copy constructor that creates a copy of the existing job.
     * 
     * @param job job to be cloned.
     */
    public Job(Job job) {
        this(job.getParkManagerEmail(), job.getParkName(), job.getJobName(),
             new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(job.getStartDate().getTime()),
             new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(job.getEndDate().getTime()),
             job.getCurrentLight(), job.getMaxLight(), job.getCurrentMedium(), job
             .getMaxMedium(), job.getCurrentHard(), job.getMaxHard(),
             job.volunteers);// needs getVolunteers()

        assert parkManagerEmail != null;
        assert parkName != null;
        assert jobName != null;
        assert startDate != null;
        assert endDate != null;
        assert currentLight >= 0;
        assert currentMedium >= 0;
        assert currentHeavy >= 0;
        assert maxLight >= 0;
        assert maxMedium >= 0;
        assert maxHeavy >= 0;
        assert volunteers != null;
    }

    /**
     * This represents different work categories that can be used.
     */
    public enum WorkCategories {
        LIGHT, MEDIUM, HEAVY;
    }

    /**
     * This method is for adding volunteer to a job.
     * 
     * @param email volunteer's email address
     * @param workCat different choice of work categories including light,
     *            medium, and heavy
     */
    public boolean addVolunteer(String email, WorkCategories workCat) {

        switch (workCat) {
            case LIGHT:
                if ((getCurrentLight() < getMaxLight())) {
                    volunteers.put(email, WorkCategories.LIGHT);
                    currentLight++;
                    return true;
                }
                break;
            case MEDIUM:
                if ((getCurrentMedium() < getMaxMedium())) {
                    volunteers.put(email, WorkCategories.MEDIUM);
                    currentMedium++;
                    return true;
                }
                break;
            case HEAVY:
                if (getCurrentHard() < getMaxHard()) {
                    volunteers.put(email, WorkCategories.HEAVY);
                    currentHeavy++;
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * List of volunteers sign up for a job.
     * 
     * @return A string containing the emails of all the volunteers in this job
     */
    public String getVolunteerString() {
        if (volunteers.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String light = "";
        String medium = "";
        String heavy = "";

        for (String email : volunteers.keySet()) {
            if (volunteers.get(email) == WorkCategories.LIGHT)
                light += email + DELIM_STD;
            else if (volunteers.get(email) == WorkCategories.MEDIUM)
                medium += email + DELIM_STD;
            else
                heavy += email + DELIM_STD;
        }
        sb.append(light);
        sb.append(medium);
        sb.append(heavy);
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * This method is to check whether the job is full or volunteer can still
     * sign up.
     * 
     * @return True if this job can no longer accept volunteers.
     */
    public boolean isJobFull() {
        return getVolunteerMax() <= volunteers.size();
    }

    /**
     * Get the maximum number of volunteers.
     * 
     * @pre volunteerMax >= 0;
     * @return The max number of volunteers for this job.
     */
    public int getVolunteerMax() {
        volunteerMax = getMaxLight() + getMaxMedium() + getMaxHard();

        assert volunteerMax >= 0;
        return volunteerMax;
    }

    /**
     * This method used to check if the volunteer is in the job as a volunteer.
     * 
     * @param email Volunteer's email
     * @return True if the passed volunteer is in the job; otherwise,
     *         false.
     */
    public boolean contains(String email) {
        return volunteers.containsKey(email);
    }

    /**
     * This method represents the date format from the jobFile file.
     * @param dateString The date when a job starts.
     */
    public void setStartDate(String dateString) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
        try {
            Date aDate = formatter.parse(dateString);
            this.startDate = new GregorianCalendar();
            this.startDate.setTime(aDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        
        assert dateString != null;
        assert startDate != null;
    }

    /**
     * This method represents the date format from the jobFile file.
     * @param dateString The date when a job ends.
     */
    public void setEndDate(String dateString) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
        try {
            Date aDate = formatter.parse(dateString);
            this.endDate = new GregorianCalendar();
            this.endDate.setTime(aDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        
        assert dateString != null;
        assert endDate != null;
    }

    /**
     * This represents toString() method.
     * 
     * @return String content
     */
    public String toString() {
        // toString needs to include fields for file printing
        StringBuilder sb = new StringBuilder();
        sb.append(parkManagerEmail + DELIM_STD);
        sb.append(parkName + DELIM_STD);
        sb.append(jobName + DELIM_STD);
        sb.append(new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(startDate.getTime())
                  + DELIM_STD);
        sb.append(new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(endDate.getTime())
                  + DELIM_STD);
        sb.append(currentLight + DELIM_STD);
        sb.append(maxLight + DELIM_STD);
        sb.append(currentMedium + DELIM_STD);
        sb.append(maxMedium + DELIM_STD);
        sb.append(currentHeavy + DELIM_STD);
        sb.append(maxHeavy);
        if (!volunteers.isEmpty()) {
            sb.append(DELIM_STD + getVolunteerString());
        }

        return sb.toString();
    }

    /**
     * This is a getter method that return the email of the park manager.
     * 
     * @return parkManagerEmail park manager's email
     */
    public String getParkManagerEmail() {
        return parkManagerEmail;
    }

    /**
     * This is a getter method that returns a park name.
     * 
     * @return parkName name of a park
     */
    public String getParkName() {
        return parkName;
    }

    /**
     * This is a getter method that return the start date of a job.
     * 
     * @return startDate the date when a job start
     */
    public GregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * This is a getter method that return the end date of a job.
     * @return endDate the date when a job ends
     */
    public GregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * This is a getter method that returns a job name.
     * 
     * @return jobName name of a job
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * This is a getter method that returns a number of current volunteers sign
     * up for light work category.
     * 
     * @return currentLight the number of current volunteers sign up for light
     *         work category.
     */
    public int getCurrentLight() {
        return currentLight;
    }

    /**
     * This is a getter method that returns a number of maximum volunteers in
     * which that light work category requires.
     * 
     * @return maxLight the number of maximum volunteers for light work
     *         category.
     */
    public int getMaxLight() {
        return maxLight;
    }

    /**
     * This is a getter method that returns a number of current volunteers sign
     * up for medium work category.
     * 
     * @return currentMedium the number of current volunteers sign up for medium
     *         work category.
     */
    public int getCurrentMedium() {
        return currentMedium;
    }

    /**
     * This is a getter method that returns a number of maximum volunteers in
     * which that medium work category requires.
     * 
     * @return maxMedium the number of maximum volunteers for medium work
     *         category.
     */
    public int getMaxMedium() {
        return maxMedium;
    }

    /**
     * This is a getter method that returns a number of current volunteers sign
     * up for heavy work category.
     * 
     * @return currentHard the number of current volunteers sign up for heavy
     *         work category.
     */
    public int getCurrentHard() {
        return currentHeavy;
    }

    /**
     * This is a getter method that returns a number of maximum volunteers in
     * which that heavy work category requires.
     * 
     * @return maxHard the number of maximum volunteers for heavy work category.
     */
    public int getMaxHard() {
        return maxHeavy;
    }

    /**
     * This is a getter method that return a set of volunteer email.
     * 
     * @return volunteers the set of volunteers ' emails
     */
    public Set<String> getVolunteerEmails() {
        return volunteers.keySet();
    }

}

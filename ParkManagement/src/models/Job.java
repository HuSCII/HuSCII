/*
 * This class represents park job that volunteers sign up for.
 * Group 2 - HuSCII
 * TCSS 360, Spring 2015
 */

package models;

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
 * @version 4/4/2015
 */
public class Job {

    /** Delimiter used in string methods to separate values. */
    private static final String DELIM_STD = ","; 

    /** The maximum hours of job length (2 days). */
    public static final int MAX_JOB_TIME = 48;

    /** Email for Park Manager who created job. */
    private String parkManagerEmail;

    /** Name of the park. */
    private String parkName;

    /** Name of the job.*/
    private String jobName;

    /** Date of a job using GregorianCalendar class. */
    private GregorianCalendar date;

    /** The length of a job in hours. */
    private int jobDuration;

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
    private Map<String, WorkCatagories> volunteers;

    /**
     * This represents a constructor method.
     * 
     * @param jobID unique ID number of each job.
     * @param parkName Name of the park.
     * @param jobName Name of the job.
     * @param date The date of the job.
     * @param jobDuration The length of the job in hours.
     */
    public Job(final String parkManagerEmail, 
               final String parkName, final String jobName, 
               final String date, final int jobDuration, 
               final int currentLight, final int maxLight,
               final int currentMedium, final int maxMedium,
               final int currentHeavy, final int maxHeavy,
               Map<String, WorkCatagories> volunteers) {

        //this.jobID = jobID;
        this.parkManagerEmail = parkManagerEmail;
        this.parkName = parkName;
        this.jobName = jobName;
        this.jobDuration = jobDuration;
        setDate(date);

        this.currentLight = currentLight;
        this.maxLight = maxLight;
        this.currentMedium = currentMedium;
        this.maxMedium = maxMedium;
        this.currentHeavy = currentHeavy;
        this.maxHeavy = maxHeavy;

        if(volunteers==null) {
            this.volunteers = new HashMap<String, Job.WorkCatagories>();
        } else {
            this.volunteers = volunteers;
        }

    }

    /**
     * A copy constructor that creates a copy of the existing job.
     * @param job job to be cloned.
     */
    public Job(Job job) {
        this(job.getParkManagerEmail(), job.getParkName(), job.getJobName(),
             new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(job.getDate().getTime()), 
             job.getJobDuration(), job.getCurrentLight(), job.getMaxLight(), 
             job.getCurrentMedium(), job.getMaxMedium(), 
             job.getCurrentHard(), job.getMaxHard(), job.volunteers);//needs getVolunteers()
    }

    /**
     * This represents different work categories that can be used.
     */
    public enum WorkCatagories {
        LIGHT, MEDIUM, HEAVY;
    }

    /**
     * This method is for adding volunteer to a job.
     * 
     * @param email volunteer's email address
     * @param workCat different choice of work categories including light, medium, and heavy
     * @throws JobFullException exception is thrown when the maximum number of volunteer for that job is reached.
     */
    //might need to split into separate functions so it's easier to do JUnit test
    //print out sth when current vol == max needed in each categories
    public boolean addVolunteer(String email, WorkCatagories workCat) {

//        if (isJobFull()) {
//            throw new JobFullException(getJobName() + "is already full.");
//        } else if (contains(email)) {
//            throw new VolunteerStateException("Volunteer is already in " + getJobName() + "list.");
//        } else {
            switch(workCat) {
                case LIGHT:
                    if ((getCurrentLight() < getMaxLight())) {
                        volunteers.put(email, WorkCatagories.LIGHT);
                        currentLight++;
                        return true;
                    } 
                    break;
                case MEDIUM:
                    if ((getCurrentMedium() < getMaxMedium())) {
                        volunteers.put(email, WorkCatagories.MEDIUM);
                        currentMedium++;
                        return true;
                    }
                    break;
                case HEAVY:
                    if (getCurrentHard() < getMaxHard()) {
                        volunteers.put(email, WorkCatagories.HEAVY);
                        currentHeavy++;
                        return true;
                    }
                    break;
            }
            return false;
//        }
    }

    /**
     * List of volunteers sign up for a job.
     * 
     * @return A string containing the emails of all the
     * 			volunteers in this job 
     */
    public String volunteerSignUp() {
        if(volunteers.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String light="";
        String medium="";
        String heavy="";

        for (String email : volunteers.keySet()) {
            if (volunteers.get(email) == WorkCatagories.LIGHT) {
                light += email + ",";
            } else if (volunteers.get(email) == WorkCatagories.HEAVY) {
                medium += email + ",";
            } else {
                heavy += email + ",";
            }
        }

        sb.append(light);
        sb.append(medium);
        sb.append(heavy);
        if(sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }


    /**
     * This method is to check whether the job is full or volunteer can still sign up.
     * @return True if this job can no longer accept volunteers.
     * 
     * add all getMax for each work categories to get if job is full or not
     */
    public boolean isJobFull() {
        return getVolunteerMax() <= volunteers.size();
    }	

    /**
     * Get the maximum number of volunteers.
     * @return The max number of volunteers for this job.
     */
    public int getVolunteerMax() {
        volunteerMax = getMaxLight() + getMaxMedium() + getMaxHard();
        return volunteerMax;
    }

    /**
     * Used to check if the volunteer is in the job as a volunteer.
     * @param email Volunteer's email
     * @return True if the passed volunteer is in the job period, false
     * 			otherwise. 
     */
    public boolean contains(String email) {
        return volunteers.containsKey(email);
    }

    /**
     * A job may not be scheduled that lasts more than two days. 
     * @return true if the job length is less than 2 days; otherwise, false. 
     */
    public boolean checkJobDuration() {
        return jobDuration < MAX_JOB_TIME;
    }

    /**
     * This method represents the date format from the jobFile file.
     * @param date the date of the job
     */
    public void setDate(String date) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
        try {
            Date aDate = formatter.parse(date);
            this.date = new GregorianCalendar();
            this.date.setTime(aDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * This represents toString() method.
     * @return String content
     */
    public String toString() {
        //toString needs to include fields for file printing
        StringBuilder sb = new StringBuilder();
        sb.append(parkManagerEmail + ",");
        sb.append(parkName + ",");
        sb.append(jobName + ",");
        sb.append(new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(date.getTime()) + ",");
        sb.append(jobDuration + ",");
        sb.append(currentLight + ",");
        sb.append(maxLight + ",");
        sb.append(currentMedium + ",");
        sb.append(maxMedium + ",");
        sb.append(currentHeavy + ",");
        sb.append(maxHeavy);
        if(!volunteers.isEmpty()) {
            sb.append("," + volunteerSignUp()); 
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
     * This is a getter method that return the number of job length in hour.
     * 
     * @return jobDuration the length of a job in hour
     */
    public int getJobDuration() {
        return jobDuration;
    }

    /**
     * This is a getter method that returns a park name.
     * @return parkName name of a park
     */
    public String getParkName() {
        return parkName;
    }

    /**
     * This is a getter method that return the date of a job.
     * @return date the date of a job.
     */
    public GregorianCalendar getDate() {
        return date;
    }
    /**
     * This is a getter method that returns a job name.
     * @return jobName name of a job
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * This is a getter method that returns a number of current 
     * volunteers sign up for light work category.
     * 
     * @return currentLight the number of current 
     * volunteers sign up for light work category.
     */
    public int getCurrentLight() {
        return currentLight;
    }

    /**
     * This is a getter method that returns a number of maximum 
     * volunteers in which that light work category requires.
     * 
     * @return maxLight the number of maximum 
     * volunteers for light work category.
     */
    public int getMaxLight() {
        return maxLight;
    }

    /**
     * This is a getter method that returns a number of current 
     * volunteers sign up for medium work category.
     * 
     * @return currentMedium the number of current 
     * volunteers sign up for medium work category.
     */
    public int getCurrentMedium() {
        return currentMedium;
    }

    /**
     * This is a getter method that returns a number of maximum 
     * volunteers in which that medium work category requires.
     * 
     * @return maxMedium the number of maximum 
     * volunteers for medium work category.
     */
    public int getMaxMedium() {
        return maxMedium;
    }

    /**
     * This is a getter method that returns a number of current 
     * volunteers sign up for heavy work category.
     * 
     * @return currentHard the number of current 
     * volunteers sign up for heavy work category.
     */
    public int getCurrentHard() {
        return currentHeavy;
    }

    /**
     * This is a getter method that returns a number of maximum 
     * volunteers in which that heavy work category requires.
     * 
     * @return maxHard the number of maximum 
     * volunteers for heavy work category.
     */
    public int getMaxHard() {
        return maxHeavy;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void setJobDuration(int jobDuration) {
        this.jobDuration = jobDuration;
    }

    public void setCurrentLight(int currentLight) {
        this.currentLight = currentLight;
    }

    public void setMaxLight(int maxLight) {
        this.maxLight = maxLight;
    }

    public void setCurrentMedium(int currentMedium) {
        this.currentMedium = currentMedium;
    }

    public void setMaxMedium(int maxMedium) {
        this.maxMedium = maxMedium;
    }

    public void setCurrentHeavy(int currentHeavy) {
        this.currentHeavy = currentHeavy;
    }

    public void setMaxHeavy(int maxHeavy) {
        this.maxHeavy = maxHeavy;
    }

    public void setVolunteerMax(int volunteerMax) {
        this.volunteerMax = volunteerMax;
    }

    public void setVolunteers(Map<String, WorkCatagories> signedVolunteers) {
        this.volunteers = signedVolunteers;
    }

    public Set<String> getVolunteerEmails() {
        return volunteers.keySet();
    }

//    /**
//     * An exception thrown when an operation is attempted on a full 
//     * job. 
//     */
//    @SuppressWarnings("serial")
//    public class JobFullException extends RuntimeException {
//        public JobFullException(String message) {
//            super(message);
//        }
//    }
//
//    /**
//     * An exception thrown when an operation is attempted between
//     * a volunteer and a job, but something goes wrong.
//     */
//    @SuppressWarnings("serial")
//    public class VolunteerStateException extends RuntimeException {
//        public VolunteerStateException(String message) {
//            super(message);
//        }
//    }

}

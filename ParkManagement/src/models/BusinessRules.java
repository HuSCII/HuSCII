
package models;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author Ian McPeek
 *
 */
public class BusinessRules {

    /** The maximum days from the current date that the job can be added. */
    public static final int MAX_DAYS = 90;

    /** holds current maximum number of allowed jobs at a given time. */
    private static final int MAX_JOBS = 30;
    
    private static final int MAX_JOB_LENGTH = 2;

    /**
     * Comparing the job date, check if the job is completed or still a pending
     * job.
     * 
     * @return true if the job is already completed (past job); otherwise,
     *         false.
     */
    public static boolean isCompleted(GregorianCalendar jobDate) {

        GregorianCalendar todayDate = (GregorianCalendar) GregorianCalendar.getInstance();
        if (todayDate.compareTo(jobDate) > 0) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * A job may not be added that is in the past or more than three months in
     * the future.
     * 
     * @return true if a job can be added; otherwise, false.
     */
    public static boolean valiDate(GregorianCalendar jobDate) {
        if (isCompleted(jobDate) && !futureDate(jobDate)) {
            return true;
        }
        return false;
    }

    /**
     * This method is to check if the job is more than 3 months from today date.
     * 
     * @param jobDate date of the job
     * @return true if it's more than 3 months in the future; otherwise, false
     */
    public static boolean futureDate(GregorianCalendar jobDate) {

        // create a new calendar
        GregorianCalendar todayDate = (GregorianCalendar) GregorianCalendar.getInstance();

        todayDate.add(Calendar.DAY_OF_MONTH, MAX_DAYS);

        if (todayDate.before(jobDate)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the maximum job limit has been reached.
     * 
     * @return whether maximum is met.
     */
    public static boolean checkMaxJobs(List<Job> allJobs) {
        if (allJobs.size() < MAX_JOBS) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the week quota(5) has been met for a given week.
     * 
     * @return whether week quota is met.
     */
    public static boolean checkJobWeek(List<Job> allJobs, GregorianCalendar date) {
        GregorianCalendar pastDate = new GregorianCalendar(date.getTimeZone());
        GregorianCalendar futureDate = new GregorianCalendar(date.getTimeZone());
        int count = 0;
        pastDate.set(Calendar.DAY_OF_MONTH, pastDate.get(Calendar.DAY_OF_MONTH) - 3);
        futureDate.set(Calendar.DAY_OF_MONTH, pastDate.get(Calendar.DAY_OF_MONTH) + 3);

        for (Job aJob : allJobs) {
            if (aJob.getDate().compareTo(pastDate) >= 0 && date.compareTo(futureDate) <= 0) {
                count++;
            }
            if (count >= 5) {
                return false;
            }
        }
        return true;
    }

    /**
     * A job may not be scheduled that lasts more than two days.
     * 
     * @return true if the job length is less than 2 days; otherwise, false.
     */
    public boolean checkJobDuration(Job job) {
        return job.getJobDuration() < MAX_JOB_LENGTH;
    }

}

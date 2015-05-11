/*
 * HuSCII (Group 2) TCSS 360 - Spring '15 UserController.java
 */

package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Job.WorkCatagories;

/**
 * park manager.
 * 
 * @author Jingzhu Guo
 * @version 3 May 2015
 */
public class ParkManager extends User {

    /** Collection of the jobs this pm manages. */
    private List<Job> myJobs;

    /** Job controller. */
    private JobController jobController;

    /**
     * Create a User of the parks manager.
     * 
     * @param email User's email address.
     * @param lastName User's last name.
     * @param firstName User's first name.
     * @param role User's role.
     * @param jobController Park Manager's JobController.
     */
    public ParkManager(final String email, final String firstName, final String lastName,
                       final String role, final JobController jobController) {

        super(email, firstName, lastName, role);
        this.myJobs = new ArrayList<Job>();
        this.jobController = jobController;
    }

    /**
     * Add job to park management system.
     * 
     * @param parkName Name of the park.
     * @param jobName Name of the job.
     * @param date Start date of job.
     * @param jobDuration Length of job in hours.
     */
    public boolean addJob(String parkName, String jobName, String date, int jobDuration,
                          int maxLight, int maxMed, int maxHvy,
                          Map<String, WorkCatagories> volunteers) {

        return jobController.addJob(new Job(getEmail(), parkName, jobName, date, jobDuration,
                                            0, maxLight, 0, maxMed, 0, maxHvy, volunteers));

    }

    /**
     * Retrieve this park manager's jobs.
     * 
     * @param parkManager The park manager retrieving its job.
     * @return List of Upcoming job that I manage.
     */
    public List<Job> getMyJobs(final User parkManager) {

        // Retrieve list of Upcoming jobs
        final List<Job> upcomingJobs = jobController.getUpcomingJobs();
        final List<Job> parkManagerJobs = new ArrayList<Job>();

        for (Job j : upcomingJobs) {
            if (getEmail().equals(j.getParkManagerEmail())) {
                parkManagerJobs.add(j);
            }
        }

        return parkManagerJobs;

    }

    /**
     * print out park manager's info.
     */
    public String toString() {

        return super.toString() + "  Park Lists: ";
    }

    /**
     * main method to test the ParkManager class
     * 
     * @param args
     */
    public static void main(String[] args) {

    }
}

/*
 * HuSCII (Group 2)
 * TCSS 360 - Spring '15
 * ParkManager.java
 */

package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Job.WorkCategories;

/**
 * Park manager of the park management system.
 * 
 * @author Jingzhu Guo
 * @version 3 May 2015
 */
public class ParkManager extends User {

    /**
     * Create a "Park Manager" User.
     * 
     * @param email User's email address.
     * @param lastName User's last name.
     * @param firstName User's first name.
     * @param role User's role.
     */
    public ParkManager(final String email, final String firstName, final String lastName,
                       final String role) {

        super(email, firstName, lastName, role);

    }

    /**
     * Add job to park management system.
     * 
     * @param jobController The JobController to store job in.
     * @param parkName Name of park.
     * @param jobName Name of job.
     * @param startDate Start date for job.
     * @param endDate End date for job.
     * @param maxLight Maximum light-duty volunteers.
     * @param maxMed Maximum medium-duty volunteers.
     * @param maxHvy Maximum heavy-duty volunteers.
     */
    public void addJob(JobController jobController, String parkName, String jobName,
                       String startDate, String endDate, int maxLight, int maxMed, int maxHvy) {

        jobController.addJob(new Job(getEmail(), parkName, jobName, startDate, endDate,
                                     0, maxLight, 0, maxMed, 0, maxHvy,
                                     new HashMap<String, WorkCategories>()));

    }

    /**
     * Retrieve this park manager's jobs.
     * 
     * @param jobController The JobController that stores the jobs to be retrieved from.
     * @return List of Upcoming job that I manage.
     */
    public List<Job> getMyJobs(final JobController jobController) {

        // Retrieve list of Upcoming jobs
        final List<Job> upcomingJobs = jobController.getUpcomingJobs();

        // Create list of park manager's job to return.
        final List<Job> parkManagerJobs = new ArrayList<Job>();

        // For each job, check to see if park manager's email is there.
        for (Job j : upcomingJobs) {
            if (getEmail().equals(j.getParkManagerEmail())) {
                parkManagerJobs.add(j);
            }
        }

        return parkManagerJobs;

    }

    // public static void main(String[] args) {
    // System.out.println("Testing out parkmanager class");
    //
    // JobController testJobController = new JobController("src/jobFile.csv");
    //
    // ParkManager testManager =
    // new ParkManager("walderfrey@gmail.com", "Walder", "Frey",
    // "park manager");
    //
    // System.out.println(testManager.retrieveManagedParks("/userFile.csv"));
    // System.out.println(testManager.getMyJobs(testJobController));
    // }
}

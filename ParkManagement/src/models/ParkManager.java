/*
 * HuSCII (Group 2) TCSS 360 - Spring '15 UserController.java
 */

package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import models.Job.WorkCatagories;

/**
 * park manager.
 * 
 * @author Jingzhu Guo
 * @version 3 May 2015
 */
public class ParkManager extends User {

    List<Job> managedJobs;
    List<String> managedParks;

    /**
     * Create a User of the parks manager.
     * 
     * @param email User's email address.
     * @param lastName User's last name.
     * @param firstName User's first name.
     * @param role User's role.
     */
    public ParkManager(final String email, final String firstName, final String lastName,
                       final String role) {

        super(email, firstName, lastName, role);
        managedJobs = new ArrayList<Job>();
        managedParks = new ArrayList<String>();

    }

    public List<String> retrieveManagedParks(final String inputFile) {

        Scanner fileInput = null;
        try {
            final URL url = ParkManager.class.getResource(inputFile);
            final File userFile = new File(url.toURI());
            fileInput = new Scanner(userFile);

            // For each line of text, split it up using "," as delimiter
            while (fileInput.hasNext()) {
                final List<String> userData = Arrays.asList(fileInput.nextLine().split(","));
                if (userData.get(0).equals(getEmail())) {
                    for (int i = 4; i < userData.size(); i++) {
                        managedParks.add(userData.get(i));
                    }
                    return managedParks;
                }
            }

        }
        catch (final FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        finally {
            fileInput.close();

        }
        return null;

    }

    /**
     * Add job to park management system.
     * 
     * @param parkName Name of the park.
     * @param jobName Name of the job.
     * @param date Start date of job.
     * @param jobDuration Length of job in hours.
     */
    public void addJob(JobController jobController, String parkName, String jobName,
                          String date, int jobDuration, int maxLight, int maxMed, int maxHvy) {

        jobController.addJob(new Job(getEmail(), parkName, jobName, date, jobDuration,
                                            0, maxLight, 0, maxMed, 0, maxHvy,
                                            new HashMap<String, WorkCatagories>()));

    }

    /**
     * Retrieve this park manager's jobs.
     * 
     * @param parkManager The park manager retrieving its job.
     * @return List of Upcoming job that I manage.
     */
    public List<Job> getMyJobs(JobController jobController) {

        // Retrieve list of Upcoming jobs
        final List<Job> upcomingJobs = jobController.getUpcomingJobs();
        //System.out.println("Upcoming jobs" + upcomingJobs);
        final List<Job> parkManagerJobs = new ArrayList<Job>();

        for (Job j : upcomingJobs) {
            if (getEmail().equals(j.getParkManagerEmail())) {
                parkManagerJobs.add(j);
            }
        }

        return parkManagerJobs;

    }

//    public static void main(String[] args) {
//        System.out.println("Testing out parkmanager class");
//
//        JobController testJobController = new JobController("src/jobFile.csv");
//
//        ParkManager testManager =
//                        new ParkManager("walderfrey@gmail.com", "Walder", "Frey",
//                                        "park manager");
//
//        System.out.println(testManager.retrieveManagedParks("/userFile.csv"));
//        System.out.println(testManager.getMyJobs(testJobController));
//    }
}

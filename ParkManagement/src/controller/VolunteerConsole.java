/*
 * This class represents the console for volunteer.
 * Group 2 - HuSCII
 * TCSS 360, Spring 2015
 */

package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.BusinessRules;
import models.Job;
import models.Job.WorkCategories;
import models.JobController;
import models.User;

/**
 * This class represents the console for volunteer.
 * @author HuSCII
 * @version 06/01/2015
 *
 */
public class VolunteerConsole {

    public static JobController jobController;
    private static Scanner keyboard;
    public static User volunteer;
    public static Job job;

    public VolunteerConsole(User user, JobController jobController) {

        volunteer =
                        new User(user.getEmail(), user.getFirstName(), user.getLastName(),
                                 user.getRole());
        VolunteerConsole.jobController = jobController;

    }

    public static void displayMenu() {
        keyboard = new Scanner(System.in);
        
        System.out.println("+======================================+");
        System.out.println("|       Welcome To Volunteer Page      |");
        System.out.println("+======================================+");
        System.out.println("| Options:                             |");
        System.out.println("|  1. View all upcoming jobs.          |");
        System.out.println("|  2. View jobs I have signed up for.  |");
        System.out.println("|  3. Sign up for a job.               |");
        System.out.println("|  4. Logout                           |");
        System.out.println("|  5. Exit                             |");
        System.out.println("+======================================+");
        
        System.out.println();
        System.out.print("Please select menu choice 1-5: ");

        final int menuSelect = keyboard.nextInt();

        switch (menuSelect) {
            case 1:
                viewUpcomingJobs();
                displayMenu();
                break;
            case 2:
                viewSignedUpJobs();
                displayMenu();
                break;
            case 3:
                signMeUp();
                displayMenu();
                break;
            case 4: // Logout
                MainConsole.signIn();
                break;
            case 5: // Exit program
                break;
            default:
                System.out.println("Not in a menu choice");
                System.out.println("Please Try Again!\n");
                displayMenu();
                break;
        }

    }

    /**
     * 
     * @return
     */
    public static List<Job> viewUpcomingJobs() {

        System.out.println("View available upcoming jobs:");
        System.out.println();

        final List<Job> upcomingJobs = jobController.getUpcomingJobs();

        int i = 1;
        for (Job j : upcomingJobs) {
            // upcomingJobs.add(j);
            System.out.print(i++ + ") ");
            System.out.println(j.getParkName());
            System.out.println("\t"+j.getJobName());
            System.out.println("\tStart date & time: "
                               + new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(j
                                               .getStartDate().getTime()));
            System.out.println("\tEnd date & time: "
                               + new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(j
                                               .getEndDate().getTime()));
            System.out.println("\t"+j.getCurrentLight() + " out of " + j.getMaxLight()
                               + " light-duty volunteers.");
            System.out.println("\t"+j.getCurrentMedium() + " out of " + j.getMaxMedium()
                               + " medium-duty volunteers.");
            System.out.println("\t"+j.getCurrentHard() + " out of " + j.getMaxHard()
                               + " heavy-duty volunteers.");
            System.out.println();
        }

        if (upcomingJobs.isEmpty()) {
            System.out.println("There are no upcoming jobs to display!");
        }

        System.out.println();

        return upcomingJobs;

    }

    // public static List<Job> getMyJobs() {
    //
    // final List<Job> volunteersJob = new ArrayList<Job>();
    //
    // for (Job j : jobController.getUpcomingJobs()) {
    // for (String vol : j.getVolunteerEmails()) {
    // if (volunteer.getEmail().equals(vol)) {
    // volunteersJob.add(j);
    // }
    // }
    // }
    //
    // return volunteersJob;
    //
    // }

    /**
     * 
     */
    public static void viewSignedUpJobs() {

        System.out.println("Viewing the jobs you have signed up for:");
        System.out.println();

        boolean emailFound = false;

        for (Job j : jobController.getUpcomingJobs()) {

            for (String vol : j.getVolunteerEmails()) {
                if (volunteer.getEmail().equals(vol)) {
                    emailFound = true;
                    System.out.print(j.getJobName() + " at ");
                    System.out.print(j.getParkName() + " on ");
                    System.out.println(new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(j
                                    .getStartDate().getTime()));
                    System.out.println();
                }
            }
        }

        // The case when volunteer has no jobs:
        if (!emailFound) {
            Scanner console = new Scanner(System.in);
            System.out.println("You have not signed up for any jobs yet!");
            System.out.println("Do you want to sign up a job? Yes/No ");
            String answer = console.next();
            if (answer.equalsIgnoreCase("yes")) {
                signMeUp();
            }
            else {
                System.out.println();
                displayMenu();
            }

            System.out.println();
        }
    }

    /**
     * 
     */
    public static void signMeUp() {

        System.out.println("Sign up for a job:");
        List<Job> upcomingJobs = viewUpcomingJobs(); // remember, this lists
        // jobs in console.

        if (!upcomingJobs.isEmpty()) {
            // User selects number:
            System.out.print("Select a number corresponding to the job you are interested in: ");
            keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();

            // Make sure user selects job in valid range (prevent index oob)
            while (choice < 0 || choice > upcomingJobs.size()) {
                System.out.print("Please make a selection from the list: ");
                choice = keyboard.nextInt();
            }

            // Check to see if volunteer already signed up for that job
            if (upcomingJobs.get(choice - 1).contains(volunteer.getEmail())) {
                System.out.println();
                System.out.println("You already signed up for this job.");
                return;
            }
            // Business Rule#7 - can't sign up for two jobs on the same day
            else if (BusinessRules.checkTwoJobsSameDay(volunteer, upcomingJobs, upcomingJobs
                            .get(choice - 1).getStartDate())) {
                System.out.println();
                System.out.println("You already signed up for another job on the same date.");
                return;
            }
            // Check to see if the job is TOTALLY full in all workcats
            else if (upcomingJobs.get(choice - 1).isJobFull()) {
                System.out.println();
                System.out.println("This job is already full.");
                System.out.println();
                return;
            }

            else {

                System.out.println("Enter a work category (ie 1, 2, 3) ");
                System.out.println("1. Light");
                System.out.println("2. Medium");
                System.out.println("3. Heavy");
                System.out.print("Please select a work category choice 1-3: ");
                System.out.println();
                int workCat = keyboard.nextInt();
                System.out.println();

                String parkName = upcomingJobs.get(choice - 1).getParkName();
                String jobName = upcomingJobs.get(choice - 1).getJobName();

                boolean addedVolunteer = false;

                switch (workCat) {
                    case 1:
                        addedVolunteer =
                                        upcomingJobs.get(choice - 1)
                                                        .addVolunteer(volunteer.getEmail(),
                                                                      WorkCategories.LIGHT);
                        break;
                    case 2:
                        addedVolunteer =
                                        upcomingJobs.get(choice - 1)
                                                        .addVolunteer(volunteer.getEmail(),
                                                                      WorkCategories.MEDIUM);
                        break;
                    case 3:
                        addedVolunteer =
                                        upcomingJobs.get(choice - 1)
                                                        .addVolunteer(volunteer.getEmail(),
                                                                      WorkCategories.HEAVY);

                        break;
                    default:
                        System.out.println("Please enter a valid work category");
                        System.out.println("Light, Medium, Heavy");
                        workCat = keyboard.nextInt();
                }

                if (addedVolunteer) {
                    System.out.println("You have successfully signed up for " + jobName
                                       + " at " + parkName + ".");
                }
                else {
                    System.out.println("Sorry, category is at its max volunteers.");
                }

                System.out.println();
            }

        }
    }
}


package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Job;
import models.Job.WorkCatagories;
import models.JobController;
import models.User;
import models.UserController;
import models.Volunteer;

public class VolunteerConsole {

    public static JobController jobController;
    private static Scanner keyboard;
    public static Volunteer volunteer;
    public static Job job;

    public VolunteerConsole(User user, JobController jobController) {

        volunteer =
                        new Volunteer(user.getEmail(), user.getFirstName(),
                                      user.getLastName(), user.getRole());
        VolunteerConsole.jobController = jobController;

    }

    public void displayMenu() {
        keyboard = new Scanner(System.in);

        System.out.println("Welcome To Volunteer Page");
        System.out.println("------------------------------");
        System.out.println("1. View all upcoming jobs.");
        System.out.println("2. View jobs I have signed up for.");
        System.out.println("3. Sign up for a job.");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        System.out.print("Please select menu choice 1-5: ");

        final int menuSelect = keyboard.nextInt();

        switch (menuSelect) {
            case 1:
                viewUpcomingJobs();
                break;
            case 2:
                viewSignedUpJobs();
                break;
            case 3:
                signMeUp();
                break;
            case 4: // Logout
                final String[] args = {};
                MainConsole.main(args);
                break;
            case 5: // Exit
                break;
            default:
                System.out.println("Not in a menu choice");
                System.out.println("Please Try Again!\n");
                displayMenu();
                break;
        }

        displayMenu(); // Display menu when done:

    }

    // private static void listMyJobs() {
    // for (Job job: jobController.getUpcomingJobs()) {
    // System.out.println(job.toString());
    // }
    // }

    public static List<Job> viewUpcomingJobs() {

        System.out.println("View available upcoming jobs:");
        System.out.println();

        final List<Job> upcomingJobs = new ArrayList<Job>();

        int i = 1;
        for (Job j : jobController.getUpcomingJobs()) {
            upcomingJobs.add(j);
            System.out.print(i++ + ") ");
            System.out.println(j.getParkName());
            System.out.println(j.getJobName());
            System.out.println("Start date & time: "
                               + new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(j.getDate()
                                               .getTime()));
            System.out.println("Duration: " + j.getJobDuration() + " hours");
            System.out.println(j.getCurrentLight() + " out of " + j.getMaxLight()
                               + " light-duty volunteers.");
            System.out.println(j.getCurrentMedium() + " out of " + j.getMaxMedium()
                               + " medium-duty volunteers.");
            System.out.println(j.getCurrentHard() + " out of " + j.getMaxHard()
                               + " heavy-duty volunteers.");
            System.out.println();
        }
        System.out.println();

        return upcomingJobs;

        // System.out.println(volunteer.getMyJobs(jobController));
    }

    public static List<Job> getMyJobs() {

        final List<Job> volunteersJob = new ArrayList<Job>();

        for (Job j : jobController.getUpcomingJobs()) {
            for (String vol : j.getVolunteerEmails()) {
                if (volunteer.getEmail().equals(vol)) {
                    volunteersJob.add(j);
                }
            }
        }

        return volunteersJob;

    }

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
                                    .getDate().getTime()));
                    System.out.println();
                }
            }
        }

        // The case when volunteer has no jobs:
        if (!emailFound) {
            System.out.println("You have not signed up for any jobs yet!");
            System.out.println();
        }

    }

    public static void signMeUp() {

        System.out.println("Sign up for a job:");
        List<Job> upcomingJobs = viewUpcomingJobs();

        // User selects number:
        System.out.print("Select a number corresponding to the job you are interested in: ");
        keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        while (choice < 0 || choice > upcomingJobs.size()) {
            System.out.print("Please make a selection from the list: ");
            choice = keyboard.nextInt();
        }
        // add validation for Business Rule#7 (can't sign up for two jobs on the same day)

        System.out.println("Enter a work category (ie 1, 2, 3) ");
        System.out.println("1. Light");
        System.out.println("2. Medium");
        System.out.println("3. Heavy");
        System.out.print("Please select a work category choice 1-3: ");
        System.out.println();
        int workCat = keyboard.nextInt();

        switch (workCat) {
            case 1:
                upcomingJobs.get(choice - 1).addVolunteer(volunteer.getEmail(),
                                                          WorkCatagories.LIGHT);
                break;
            case 2:
                upcomingJobs.get(choice - 1).addVolunteer(volunteer.getEmail(),
                                                          WorkCatagories.MEDIUM);
                break;
            case 3:
                upcomingJobs.get(choice - 1).addVolunteer(volunteer.getEmail(),
                                                          WorkCatagories.HEAVY);
                break;
            default:
                System.out.println("Please enter a valid work category");
                System.out.println("Light, Medium, Heavy");
                workCat = keyboard.nextInt();
        }

        // job.addVolunteer(volunteer.getEmail(), workCat);
        // volunteer.signUp(jobName, workCat);

    }
}

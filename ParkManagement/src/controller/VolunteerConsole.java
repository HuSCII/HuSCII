
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
        System.out.println("3. Logout");
        System.out.println("4. Exit");
        System.out.print("Please select menu choice 1-5: ");

        final int menuSelect = keyboard.nextInt();

        switch (menuSelect) {
            case 1:
                viewUpcomingJobs();
                break;
            case 2:
                viewSignedUpJobs();
                break;
            case 3: // Logout
                final String[] args = {};
                MainConsole.main(args);
                break;
            case 4: // Exit
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

    public static void viewUpcomingJobs() {

        System.out.println("View available upcoming jobs:");
        System.out.println();

        final List<Job> myJobs = new ArrayList<Job>();

        int i = 1;
        for (Job j : jobController.getUpcomingJobs()) {
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

        for (Job j : jobController.getUpcomingJobs()) {
            for (String vol : j.getVolunteerEmails()) {
                if (volunteer.getEmail().equals(vol)) {
                    System.out.print(j.getJobName() + " at ");
                    System.out.print(j.getParkName() + " on ");
                    System.out.println(new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(j
                                    .getDate().getTime()));
                    System.out.println();
                }
            }
        }

    }

    public static void signMeUp() {

        System.out.println("Sign up for this job:");
        keyboard = new Scanner(System.in);

        System.out.print("Enter a Job Name (ie trash pickup): ");
        String jobName = keyboard.nextLine();

        System.out.print("Enter a work category (ie Light, Medium, Heavy) ");
        String workCat = keyboard.nextLine();

        // job.addVolunteer(volunteer.getEmail(), workCat);
        // volunteer.signUp(jobName, workCat);

    }
}

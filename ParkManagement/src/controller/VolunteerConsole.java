
package controller;

import java.util.Scanner;

import models.JobController;
import models.User;
import models.Volunteer;

public class VolunteerConsole {

    public static JobController jobController;
    private static Scanner keyboard;
    public static Volunteer volunteer;

    public VolunteerConsole(User user, JobController jobController) {

        volunteer = (Volunteer) user;

    }

    public void displayMenu() {
        keyboard = new Scanner(System.in);

        System.out.println("Welcome To Volunteer Page");
        System.out.println("------------------------------");
        System.out.println("1. View all upcoming jobs.");
        System.out.println("2. View jobs I have signed up for.");
        System.out.println("3. Logout");
        System.out.println("4. Exit");
        System.out.println("Please select menu choice 1-4: ");

        final int menuSelect = keyboard.nextInt();

        switch (menuSelect) {
            case 1: // View upcoming jobs
                break;
            case 2: // View signedup jobs
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

    }

    public static void viewUpcomingJobs() {

        System.out.println("View available upcoming jobs:");

    }

    public static void viewSignedUpJobs() {

        System.out.println("Viewing the jobs you have signed up for:");

    }

    public static void signMeUp() {

        System.out.println("Sign up for this job:");

    }

}

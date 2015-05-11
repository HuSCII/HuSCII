
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Job;
import models.JobController;
import models.ParkManager;
import models.User;

public class ParkManagerConsole {

    public static JobController jobController;
    private static Scanner keyboard;
    public static ParkManager parkManager;

    public ParkManagerConsole(User user, JobController jobController) {
        parkManager =
                        new ParkManager(user.getEmail(), user.getFirstName(),
                                        user.getLastName(), user.getRole());
        keyboard = new Scanner(System.in);
        ParkManagerConsole.jobController = jobController;
    }

    public void displayMenu() {

        System.out.println("Welcome To Park Manager Page");
        System.out.println("------------------------------");
        System.out.println("1. Submit a New Job");
        System.out.println("2. View Upcoming Jobs");
        System.out.println("3. View Volunteers");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        System.out.println("Please select menu choice 1-5: ");

        final int menuSelect = keyboard.nextInt();

        switch (menuSelect) {
            case 1:
                submitJob();
                break;
            case 2:
                viewMyJobs();
                break;
            case 3:
                viewVolunteers();
                break;
            case 4:
                final String[] args = {};
                MainConsole.main(args);
            case 5:
                break;
            default:
                System.out.println("Not in a menu choice");
                System.out.println("Please Try Again!\n");
                displayMenu();
                break;
        }
    }

    public static void submitJob() {

        keyboard = new Scanner(System.in);

        System.out.println("Submit a new park job:");

        System.out.print("Enter Park Name: ");
        String parkName = keyboard.nextLine();

        System.out.print("Enter a Job name (ie trash pickup): ");
        String jobName = keyboard.nextLine();

        System.out.print("Enter a start date & time (MM/DD/YYYY HH:mm AM/PM): ");
        String date = keyboard.nextLine();

        System.out.print("Enter job duration (in hours): ");
        int duration = keyboard.nextInt();

        System.out.print("Enter max number of light-duty volunteers needed: ");
        int lightMax = keyboard.nextInt();

        System.out.print("Enter max number of medium-duty volunteers needed: ");
        int medMax = keyboard.nextInt();

        System.out.print("Enter max number of heavy-duty volunteers needed: ");
        int hvyMax = keyboard.nextInt();

        parkManager.addJob(jobController, parkName, jobName, date, duration, lightMax, medMax,
                           hvyMax);
    }

    public static void viewMyJobs() {
        System.out.println("Viewing upcoming jobs:");
        System.out.println();

        for (Job j : parkManager.getMyJobs(jobController)) {
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
            System.out.println("Volunteers: ");
            for(User u:j.get)
        }
        System.out.println();

    }

    public static void viewVolunteers() {
        System.out.println("Viewing voluteers:");

    }

}


package controller;

import java.text.SimpleDateFormat;
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
        parkManager = new ParkManager(user, jobController);
        keyboard = new Scanner(System.in);
    }

    public void displayMenu() {

        System.out.println("Welcome To Park Manager Page");
        System.out.println("------------------------------");
        System.out.println("1. Submit a New Job");
        System.out.println("2. View Upcoming Jobs");
        System.out.println("3. View Volunteers");
        System.out.println("4. Quit to program");
        System.out.println("5. Quit");
        System.out.println("Please select menu choice 1-5: ");

        final int menu = keyboard.nextInt();

        switch (menu) {
            case 1:
                submitJob();
                break;
            case 2:
                viewUpcomingJob();
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
                System.out.println("Please Try Again!");
                System.out.println("");
                displayMenu();
                break;
        }
    }

    public static void submitJob() {

        keyboard = new Scanner(System.in);

        System.out.print("Enter Park Name: ");
        String parkName = keyboard.nextLine();
        System.out.println();

        System.out.print("Enter a Job name (ie trash pickup): ");
        String jobName = keyboard.nextLine();
        System.out.println();

        System.out.print("Enter a start date & time (MM/DD/YYYY HH:mm AM/PM): ");
        String date = keyboard.nextLine();
        System.out.println();

        System.out.print("Enter job duration (in hours): ");
        int duration = keyboard.nextInt();
        System.out.println();

        System.out.print("Enter max number of light-duty volunteers needed: ");
        int lightMax = keyboard.nextInt();
        System.out.println();

        System.out.print("Enter max number of medium-duty volunteers needed: ");
        int medMax = keyboard.nextInt();
        System.out.println();

        System.out.print("Enter max number of heavy-duty volunteers needed: ");
        int hvyMax = keyboard.nextInt();
        System.out.println();

        parkManager.addJob(parkName, jobName, date, duration, lightMax, medMax, hvyMax);
    }

    public static void viewUpcomingJob() {
        System.out.println("Viewing upcoming jobs:");

    }

    public static void viewVolunteers() {
        System.out.println("Viewing voluteers:");

    }

}


package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import models.BusinessRules;
import models.Job;
import models.JobController;
import models.ParkManager;
import models.User;
import models.UserController;

public class ParkManagerConsole {

    public static JobController jobController;
    public static UserController userController;
    private static Scanner keyboard;
    public static ParkManager parkManager;

    public ParkManagerConsole(User user, JobController jobController,
                              UserController userController) {
        parkManager =
                        new ParkManager(user.getEmail(), user.getFirstName(),
                                        user.getLastName(), user.getRole());
        keyboard = new Scanner(System.in);
        ParkManagerConsole.jobController = jobController;
        ParkManagerConsole.userController = userController;
    }

    public void displayMenu() {

        System.out.println("+======================================+");
        System.out.println("|     Welcome To Park Manager Page     |");
        System.out.println("+======================================+");
        System.out.println("| Options:                             |");
        System.out.println("|     1. Submit a New Job              |");
        System.out.println("|     2. View Upcoming Jobs            |");
        System.out.println("|     3. View Volunteers               |");
        System.out.println("|     4. Logout                        |");
        System.out.println("|     5. Exit                          |");
        System.out.println("+======================================+");
        
        
//        System.out.println("Welcome To Park Manager Page");
//        System.out.println("------------------------------");
//        System.out.println("1. Submit a New Job");
//        System.out.println("2. View Upcoming Jobs");
//        System.out.println("3. View Volunteers");
//        System.out.println("4. Logout");
//        System.out.println("5. Exit");
        System.out.println();
        System.out.print("Please select menu choice 1-5: ");
        System.out.println();

        final int menuSelect = keyboard.nextInt();

        switch (menuSelect) {
            case 1:
                submitJob();
                displayMenu();
                break;
            case 2:
                viewMyJobs();
                displayMenu();
                break;
            case 3:
                viewVolunteers();
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

    public static void submitJob() {
        if (BusinessRules.checkMaxJobs(jobController.getAllJobs())) {
            System.out.println("Can't create job, job limit has been reached.");
            return;
        }

        //keyboard = new Scanner(System.in);

        System.out.println();
        System.out.println("Submit a new park job:");
        System.out.println("=====================");
        System.out.println();

        // SELECT A PARK YOU MANAGE:
        System.out.println("Which of your parks do you want to add a new job to?");
        int selection = 1;
        for (String parkName : userController.getManagedParks(parkManager.getEmail())) {
            System.out.println(selection++ + ") " + parkName);
        }
        int parkSelection = keyboard.nextInt() - 1;
        keyboard.nextLine(); // Skip a line
        String parkName =
                        userController.getManagedParks(parkManager.getEmail())
                                        .get(parkSelection);

        System.out.print("Enter a Job name (ie trash pickup): ");
        String jobName = keyboard.nextLine();

        GregorianCalendar gregStart = collectStartDate();
        GregorianCalendar gregEnd = collectEndDate(gregStart);
        while(!BusinessRules.checkJobDuration(gregStart, gregEnd)){
            System.out.println("Job duration exceeds 2 days!");
            gregStart = collectStartDate();
            gregEnd = collectEndDate(gregStart);
        }

        System.out.print("Enter max number of light-duty volunteers needed: ");
        int lightMax = keyboard.nextInt();

        System.out.print("Enter max number of medium-duty volunteers needed: ");
        int medMax = keyboard.nextInt();

        System.out.print("Enter max number of heavy-duty volunteers needed: ");
        int hvyMax = keyboard.nextInt();
        
        SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy HH:mm a");

        parkManager.addJob(jobController, parkName, jobName, f.format(gregStart.getTime()), 
                           f.format(gregEnd.getTime()), lightMax, medMax, hvyMax);
        
        System.out.println();
        System.out.println("You have successfully add " + jobName + " to " + parkName + ".");
        System.out.println("***********************************************************");
        System.out.println();
    }
    
    //create date collector
    private static GregorianCalendar collectStartDate() {
        String startdate;
        GregorianCalendar gregStart = new GregorianCalendar();
        boolean check = false;
        do {
            System.out.print("Enter a start date & time (MM/DD/YYYY HH:mm AM/PM): ");
            startdate = keyboard.nextLine();
            
            try {
                gregStart.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm a").parse(startdate));
                check = BusinessRules.valiDate(gregStart);
                if (!check) {
                    System.out.println("Date has already occurred "
                                       + "or past 3 months into the future.");
                }
                else {
                    check = BusinessRules.checkJobWeek(jobController.getAllJobs(), gregStart, null);
                    if (!check) {
                        System.out.println("The capacity for this week has already been reached.");
                    }
                }
            }
            catch (ParseException e) {
                System.out.println("Date not in right format");
                // e.printStackTrace();
            }
        }
        while (!check);
        return gregStart;
    }
    
    private static GregorianCalendar collectEndDate(GregorianCalendar gregStart) {
        String enddate;
        GregorianCalendar gregEnd = new GregorianCalendar();
        boolean check = false;
        do {
            System.out.print("Enter an end date & time (MM/DD/YYYY HH:mm AM/PM): ");
            enddate = keyboard.nextLine();
            try {
                gregEnd.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm a").parse(enddate));
                check = BusinessRules.valiDate(gregEnd);
                if (!check) {
                    System.out.println("Date has already occurred "
                                       + "or past 3 months into the future.");
                    //only check job week if 2 day job
                } else if(gregEnd.get(Calendar.DAY_OF_YEAR)!=gregStart.get(Calendar.DAY_OF_YEAR)) {
                    check = BusinessRules.checkJobWeek(jobController.getAllJobs(), gregStart, gregEnd);
                    if (!check) {
                        System.out.println("The capacity for this week has already been reached.");
                    }
                }
            }
            catch (ParseException e) {
                System.out.println("Date not in right format");
                // e.printStackTrace();
            }
        }while(!check);
        return gregEnd;
    }

    public static void viewMyJobs() {
        
        System.out.println();
        System.out.println("Viewing upcoming jobs:");
        System.out.println("=====================");
        System.out.println();

        List<Job> parkManagersJobs = parkManager.getMyJobs(jobController);

        if (parkManagersJobs.isEmpty()) {
            System.out.println("You currently do not have any jobs!");
        }
        else {
            for (Job j : parkManagersJobs) {
                System.out.println(j.getParkName());
                System.out.println(j.getJobName());
                System.out.println("Start date & time: "
                                   + new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(j
                                                   .getStartDate().getTime()));
                System.out.println("End date & time: "
                                + new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(j
                                                .getEndDate().getTime()));
                System.out.println(j.getCurrentLight() + " out of " + j.getMaxLight()
                                   + " light-duty volunteers.");
                System.out.println(j.getCurrentMedium() + " out of " + j.getMaxMedium()
                                   + " medium-duty volunteers.");
                System.out.println(j.getCurrentHard() + " out of " + j.getMaxHard()
                                   + " heavy-duty volunteers.");
                System.out.println();
            }
        }

        System.out.println();

    }

    public static void viewVolunteers() {

        // Display the list pm's jobs:
        System.out.println("Select a job to view its volunteers:");
        List<Job> tempJobs = parkManager.getMyJobs(jobController);

        if (tempJobs.isEmpty()) {
            System.out.println("You currently do not have any jobs, so no volunteers!");
        }
        else {
            int i = 1;
            for (Job j : tempJobs) {
                System.out.print(i++ + ") ");
                System.out.print(j.getJobName() + " at ");
                System.out.print(j.getParkName() + " on ");
                System.out.println(new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(j
                                .getStartDate().getTime()));
                System.out.println();
            }

            // Ask user to choose from the list
            //keyboard = new Scanner(System.in);
            System.out.print("Enter a number from the list: ");
            int choice = keyboard.nextInt();
            while (choice < 0 || choice > tempJobs.size()) {
                System.out.print("Please make a selection from the list: ");
                choice = keyboard.nextInt();
            }

            // Display volunteers of the selected jobs:
            System.out.println();
            System.out.println("Volunteers:");

            boolean volunteerFound = false;
            for (String volunteer : tempJobs.get(choice - 1).getVolunteerEmails()) {
                for (User u : userController.getUserList()) {
                    if (u.getEmail().equals(volunteer)) {
                        volunteerFound = true;
                        System.out.print(u.getFirstName() + " ");
                        System.out.print(u.getLastName() + ", ");
                        System.out.println(u.getEmail());
                    }
                }
            }
            if (!volunteerFound) {
                System.out.println("No volunteers have signed up yet!");
            }
        }
        System.out.println(); // Spacer
    }

}

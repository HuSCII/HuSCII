
package controller;

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

        volunteer = new Volunteer(user.getEmail(), user.getFirstName(),
                                  user.getLastName(), user.getRole());

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
        System.out.println("Please select menu choice 1-5: ");

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

    }
    
    //    private static void listMyJobs() {
    //        for (Job job: jobController.getUpcomingJobs()) {
    //            System.out.println(job.toString());
    //        }
    //    }
    
    public static void viewUpcomingJobs() {

        System.out.println("View available upcoming jobs:");
        System.out.println("");

//        System.out.println(volunteer.getFirstName() + " " + 
//                        volunteer.getLastName() +" :" + volunteer.getEmail());
        
        //jobController.loadJobData("src/jobFile.txt");

        final List<Job> myJobs = new ArrayList<Job>();

                for (Job job : getMyJobs(jobController)) {
                    myJobs.add(job);
                    System.out.println(job.getParkName());
                    System.out.println(job.getJobName());
                }

        // System.out.println(volunteer.getMyJobs(jobController));
    }
    
    public static List<Job> getMyJobs(JobController jobController) {
        
        final List<Job> volunteerJob = new ArrayList<Job>();

        for (Job j : jobController.getUpcomingJobs()) {
            if (volunteer.getEmail().equals(j.getVolunteerEmails())) {
                volunteerJob.add(j);
            }
        }

        return volunteerJob;

    }

    public static void viewSignedUpJobs() {

        System.out.println("Viewing the jobs you have signed up for:");
        System.out.println("");

        volunteer.findSignUpJobs();

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

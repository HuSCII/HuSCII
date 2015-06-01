
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import models.JobController;
import models.User;
import models.UserController;

public class MainConsole {

    /** User file name. */
    private static final String USER_FILE = "src/user.huscii";

    /** Job file name. */
    private static final String JOB_FILE = "src/jobs.huscii";
    
    /** HuSCII lgo. */
    private static final String HUSCII_FILE = "src/HuSCII.txt";

    public static UserController userController;
    public static JobController jobController;
    public static User currentUser;

    public static void main(String[] args) throws IOException {

        // Pre-load the User persistent data:
        userController = new UserController(USER_FILE);

        // Pre-load the Jobs persistent data:
        jobController = new JobController(JOB_FILE);

        // User signs in and display appropriate menu
        signIn();

        // Write new data on system exit:
        exitSystem();

    }

    private static void displayDoc() {
        System.out.println(loadHuSCIILogo());
        System.out.println("*******************HuSCII Parks Management System****************");
        System.out.println("* Software version 1.0                                          *");
        System.out.print("* Authors: Jingzhu Guo, Duy Huynh, ");
        System.out.println("Ian McPeek, Putthida Samrith *");
        System.out.println("* Copyright (c) 2015 HuSCII, TCSS 360 Spring '15 Project Group. *");
        System.out.println("*                                                               *");
        System.out.println("*****************************************************************");
    }
    
    private static String loadHuSCIILogo() {
        String huscii = "";
        FileInputStream in;
        try {
            in = new FileInputStream(HUSCII_FILE);
        }
        catch (FileNotFoundException e) {
            return null;
        }
        Scanner scanner = new Scanner(in);
        while(scanner.hasNext()) {
            huscii += "\t\t" + scanner.nextLine() + "\n";
        }
        try {
            in.close();
        }
        catch (IOException e) {}
        return huscii;
    }

    public static void signIn() {

        // Display copyright info
        displayDoc();

        // Display login prompt
        displayLoginPrompt();

        // Retrieve and verify email:
        boolean emailIsVerified = false;
        while (!emailIsVerified) {
            emailIsVerified = verifyEmail(retrieveEmail());

        }
        // Display appropriate user options:
        displayRoleOptions(currentUser);

    }

    private static void displayLoginPrompt() {
        System.out.println("Welcome to the HuSCII Parks Management v1.0");
        System.out.println("Please login with your email address:");
        System.out.print("-->");
    }

    private static String retrieveEmail() {

        final Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private static boolean verifyEmail(final String email) {

        for (User u : userController.getUserList()) {
            if (email.equals(u.getEmail())) {
                System.out.println("Logging in ... SUCCESS!");
                System.out.println("Welcome " + u.getFirstName() + " " + u.getLastName());
                System.out.println("You are registered as: " + u.getRole());
                System.out.println("");
                currentUser = u;
                return true;
            }
        }
        System.out.println("Logging in ... FAIL!");
        System.out.println("ERROR: Email not found, please try again:");
        System.out.print("-->");
        return false;
    }

    private static void displayRoleOptions(final User user) {

        switch (user.getRole().toLowerCase()) {
            case "volunteer":
                // Call volunteer console controller class:
                final VolunteerConsole volunteerConsole =
                                new VolunteerConsole(currentUser, jobController);
                volunteerConsole.displayMenu();
                break;
            case "park manager":
                // Call park manager console controller class:
                final ParkManagerConsole parkManagerConsole =
                                new ParkManagerConsole(currentUser, jobController,
                                                       userController);
                parkManagerConsole.displayMenu();
                break;
            case "administrator":
                // Call administrator console controller class:
                final AdministratorConsole admin = new AdministratorConsole(userController);
                admin.displayMenu();
                break;
            default:
                break;
        }
    }

    private static void exitSystem() {
        System.out.println("");
        System.out.println("Thank you for using the HuSCII Park Management System!");
        System.out.println("Logging out...");
        System.out.println("Program exiting...");

        // userController.writeUserFile("userFile.csv");
        // jobController.writeJobData("jobsFile.csv");

    }

}

/*
 * This class represents the console for administrator.
 * Group 2 - HuSCII
 * TCSS 360, Spring 2015
 */

package controller;

import java.util.Scanner;

import models.UserController;
import models.User;

/**
 * This class represents the console for administrator.
 * @author HuSCII
 * @version 06/01/2015
 *
 */
public class AdministratorConsole {

    private static Scanner keyboard;
    private static UserController userController;

    public AdministratorConsole(UserController userController) {
        AdministratorConsole.userController = userController;
    }

    public static void displayMenu() {

        keyboard = new Scanner(System.in);

        System.out.println("+======================================+");
        System.out.println("|    Welcome To Administrator Page     |");
        System.out.println("+======================================+");
        System.out.println("| Options:                             |");
        System.out.println("|  1. Search Volunteer by Last Name    |");
        System.out.println("|  2. Logout                           |");
        System.out.println("|  3. Exit                             |");
        System.out.println("+======================================+");
        System.out.println();

        System.out.println("Please select menu choice 1-3: ");
        
        int menu = keyboard.nextInt();

        switch (menu) {
            case 1:
                searchVolunteer();
                break;
            case 2: // Logout
                MainConsole.signIn();
                break;
            case 3: // Exit program
                break;
            default:
                System.out.println("Not in a menu choice");
                System.out.println("Please Try Aagain!");
                System.out.println("");
                displayMenu();
                break;
        }

    }

    /**
     * This method is to verify if the input last name exists in the system.
     * 
     * @param lastName Volunteer's last name
     * @return true if volunteer's last name exists; otherwise, false.
     */
    private static boolean hasLastName(final String lastName) {
        for (User user : userController.getVolunteers(lastName)) {
            if (lastName.equalsIgnoreCase(user.getLastName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is to search for volunteer by last name.
     */
    public static void searchVolunteer() {

        System.out.println("Please Enter Volunteer's Last Name: ");
        String lastName = keyboard.next();

        if (hasLastName(lastName)) {
            System.out.println();
            System.out.println("Volunteer Information ");
            System.out.println("=====================");
            System.out.println();

            for (User u : userController.getUserList()) {
                if (u.getLastName().equals(lastName) && u.getRole().equals("volunteer")) {
                    System.out.println("Name: " + u.getFirstName() + " " + u.getLastName());
                    System.out.println("Email: " + u.getEmail());
                    System.out.println();
                }
            }
        }
        else {
            System.out.println("No Volunteer Found!");
            System.out.println("Please Try Again!");
            System.out.println();

            searchVolunteer();
        }
        System.out.println();
        // Go to menuScreen() again when done:
        displayMenu();
    }
}

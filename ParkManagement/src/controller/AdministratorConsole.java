/*
 * 
 */

package controller;

import java.util.Scanner;

import models.UserController;
import models.User;

public class AdministratorConsole {

    private static Scanner keyboard;
    private static UserController users;

    public void menuScreen() {

        keyboard = new Scanner(System.in);

        System.out.println("Welcome To Administrator Page");
        System.out.println("------------------------------");
        System.out.println("1. Search Volunteer by Last Name");
        System.out.println("2. Logout");
        System.out.println("3. Exit");
        System.out.println("Please select menu choice 1-3: ");

        int menu = keyboard.nextInt();

        switch(menu) {
            case 1:
                searchVolunteer();
                break;
            case 2:
                MainConsole mainTest = new MainConsole();
                String[] args = {};
                mainTest.main(args);
            case 3:
                break;
            default:
                System.out.println("Not in a menu choice");
                System.out.println("Please Try Aagain!");
                System.out.println("");
                AdministratorConsole driver = new AdministratorConsole();
                driver.menuScreen();
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
        for (User user : users.getVolunteers(lastName)) {
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

        users = new UserController();
        users.readUserFile("/testFile.csv");
        
        if (hasLastName(lastName)) {
            
            System.out.println("");
            System.out.println("Volunteer Information ");
            System.out.println("---------------------");

            for (User u : users.getUserList()) {
                if (u.getLastName().equals(lastName)
                        && u.getRole().equals("volunteer")) {
                    System.out.println("Name: " + u.getFirstName() + " " + u.getLastName());
                    System.out.println("Email: " + u.getEmail());
                }
            }
        } else {
            System.out.println("No Volunter Found!");
            System.out.println("Please Try Again!");
            
            searchVolunteer();
        }
    }
//
//    public static void main(String[] args) {
//        menuScreen();
//    }

}

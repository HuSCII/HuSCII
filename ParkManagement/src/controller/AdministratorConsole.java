package controller;


import java.util.Scanner;

import models.UserController;
import models.User;

public class AdministratorConsole {

    private static Scanner keyboard;
    private static UserController users;
    private static User vol;

    public static void menuScreen() {

        keyboard = new Scanner(System.in);

        System.out.println("Welcome To Administrator Page");
        System.out.println("------------------------------");
        System.out.println("1. View Volunteer by Last Name");
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
    private static boolean hasLastName(String lastName) {

        for (User user : users.getUserList()) {
            if(lastName.equals(user.getLastName())) {
                user.toString();
                System.out.println("sdfds");
                return true;
            }
        }
        System.out.println("No Volunter Found!");
        System.out.println("Please Try Again!");

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

        if(hasLastName(lastName)) {
            users.getVolunteers(lastName);
            
            //print out data 
        }
    }

    public static void main(String[] args) {
        menuScreen();
    }

}

/*
 * HuSCII (Group 2)
 * TCSS 360 - Spring '15
 * UserController.java
 */

package models;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Parses text file of Users and creates collections of users.
 * 
 * @author HuSCII
 * @version 3 May 2015
 */
public class UserController {

    /** A collection of Users. */
    private final List<User> userList;

    /** A map of user and parks (for a park manager. */
    private final Map<String, List<String>> managedParks = new HashMap<String, List<String>>();

    /**
     * Reads from a file containing user data.
     * 
     * @param filename File name of user data.
     */
    public UserController(final String filename) {
        userList = new ArrayList<User>();
        readUserFile(filename);
    }

    /**
     * Parses a text file, creating a User from each line.
     * 
     * @param inputFile Text file of user data.
     */
    private void readUserFile(final String inputFile) {

        final InputStream in = this.getClass().getResourceAsStream(inputFile);
        final Scanner fileInput = new Scanner(in);

        // For each line of text, split it up using "," as delimiter
        while (fileInput.hasNext()) {
            final List<String> userData = Arrays.asList(fileInput.nextLine().split(","));

            // Add each User of the file to the List
            int i = 0;
            userList.add(new User(userData.get(i++), userData.get(i++), userData.get(i++),
                                  userData.get(i)));

            // If User was park manager, create separate List for its parks.
            if ("park manager".equalsIgnoreCase(userData.get(i++))) {

                // 1. Put park manager's parks into a List
                final List<String> parks = new ArrayList<String>();
                while (i < userData.size()) {
                    parks.add(userData.get(i++));
                }

                // 2. Add User's email and List of parks to a map collection.
                final int currentUser = userList.size() - 1;
                managedParks.put(userList.get(currentUser).getEmail(), parks);
            }
        }
        fileInput.close();

    }

    /**
     * Write List of Users into textfile.
     * 
     * @param outputFile Name of text file.
     */
    public void writeUserFile(final String outputFile) {

        try {
            final FileWriter writer = new FileWriter(outputFile);
            writer.append(toString());
            writer.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Users in a List.
     * 
     * @return List of all Users in system.
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Returns only Volunteers with the specified last name as a new List.
     * 
     * @return List of Volunteers of a specified last name.
     * @param lastName Volunteer's last name.
     */
    public List<User> getVolunteers(final String lastName) {

        // Check each user for last name and "volunteer" role.
        final List<User> tempList = new ArrayList<User>();
        for (User u : userList) {
            if (u.getLastName().equalsIgnoreCase(lastName)
                && u.getRole().equalsIgnoreCase("volunteer")) {
                tempList.add(u); // Add this user to the list to be returned
            }
        }
        return tempList;
    }

    /**
     * Retrieve this Park Manager's managed parks in a list.
     * 
     * @param parkManagerEmail The user (park manager) whose parks we want to retrieve.
     * @return List of parks this park manager manages.
     */
    public List<String> getManagedParks(final String parkManagerEmail) {
        return managedParks.get(parkManagerEmail);
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        // Append each user line
        for (User u : userList) {
            sb.append(u.toString() + "\r\n");
        }
        return sb.toString();
    }

}

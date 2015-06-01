/*
 * HuSCII (Group 2) TCSS 360 - Spring '15 UserController.java
 */

package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class UserController implements Serializable {

    /** A collection of Users. */
    private List<User> userList;

    /** A map of user and parks (for a park manager. */
    private Map<String, List<String>> managedParks = new HashMap<String, List<String>>();

    /**
     * Reads from a file containing user data.
     * 
     * @param filename File name of user data.
     * @throws IOException
     */
    public UserController(final String filename) throws IOException {
        userList = new ArrayList<User>();
        readInSerializable(filename);
        // Uncomment the following two lines, and comment the above to reload
         //readUserFile("/userFileFinal.csv"); // First time before serializable
         //writeUserSerialized("src/user.huscii");

    }

    /**
     * Read in serialized data.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private void readInSerializable(String fileName) throws IOException {
        FileInputStream users = new FileInputStream(fileName);
        FileInputStream parkmanagers = new FileInputStream("src/parkmanager.huscii");
        ObjectInputStream in = new ObjectInputStream(users);
        ObjectInputStream in2 = new ObjectInputStream(parkmanagers);

        try {
            userList = (List<User>) in.readObject();
            managedParks = (Map<String, List<String>>) in2.readObject();
        }
        catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        in2.close();
        users.close();
        parkmanagers.close();

    }

    /**
     * 
     * Write out Serializable data
     */
    public boolean writeUserSerialized(String filename) {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            FileOutputStream pmOut = new FileOutputStream("src/parkmanager.huscii");
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            ObjectOutputStream oout2 = new ObjectOutputStream(pmOut);
            oout.writeObject(userList);
            oout2.writeObject(managedParks);
            // }
            oout.close();
            oout2.close();
            pmOut.close();
            fout.close();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    /**
     * Parses a text file, creating a User from each line.
     * 
     * @param inputFile Text file of user data.
     */
    public void readUserFile(final String inputFile) {

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
        }
        catch (final IOException e) {
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
     * @param parkManagerEmail The user (park manager) whose parks we want to
     *            retrieve.
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

    public static void main(String[] args) {

        try {
            UserController userController = new UserController("src/user.huscii");
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

/*
 * HuSCII (Group 2)
 * TCSS 360 - Spring '15
 * UserController.java
 */
package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class retrieves and handles user data.
 * 
 * @author HuSCII
 * @version 3 May 2015
 */
public class UserController {

	/** A collection of Users. */
	final List<User> userList = new ArrayList<User>();

	/**
	 * Parses a text file, creating a User for each line
	 * 
	 * @param inputFile
	 *            Text file of user data.
	 */
	public void readUserFile(final String inputFile) {

		try {
			URL url = UserController.class.getResource(inputFile);
			File userFile = new File(url.toURI());
			final Scanner fileInput = new Scanner(userFile);

			// For each line of text, split it up using "," as delimeter
			while (fileInput.hasNext()) {
				final List<String> userData = Arrays.asList(fileInput
						.nextLine().split(","));

				// Add each User to the List
				userList.add(new User(userData.get(0), userData.get(1),
						userData.get(2), userData.get(3)));
			}

		} catch (FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}

	}

	/** Write List of Users into textfile. */
	public void writeUserFile(final String outputFile) {

		try {
			FileWriter writer = new FileWriter(outputFile);
			writer.append(toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the Users in a List.
	 * 
	 * @return List of Users.
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * Returns only Volunteers with the specified last name as a new List.
	 * 
	 * @return List of Volunteers of a specified last name.
	 */
	public List<User> getVolunteers(final String lastName) {

		List<User> tempList = new ArrayList<User>();

		for (User u : userList) {
			if (u.getLastName().equals(lastName)
					&& u.getRole().equals("volunteer")) {
				tempList.add(u);
			}
		}
		return tempList;
	}

	/** String representation of users and their data. */
	public String toString() {

		final StringBuilder sb = new StringBuilder();

		// Append each user line
		for (User u : userList) {
			sb.append(u.toString() + "\r\n");
		}

		return sb.toString();
	}

}

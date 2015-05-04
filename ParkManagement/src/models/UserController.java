/*
 * HuSCII (Group 2)
 * TCSS 360 - Spring '15
 * UserController.java
 */
package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class retrieves and handles user data.
 * 
 * @author Duy Huynh
 * @version 3 May 2015
 */
public class UserController {

	final List<User> userList = new ArrayList<User>();

	public UserController() {

		/*
		 * Upon creation of this class, it should read in a text file and
		 * populate a list of users
		 */
	}

	public void readUserFile() {

		try {
			URL url = UserController.class.getResource("/userFile.csv");
			File userFile = new File(url.toURI());
			final Scanner fileInput = new Scanner(userFile);

			while (fileInput.hasNext()) {
				List<String> userData = Arrays.asList(fileInput.nextLine()
						.split(","));

				userList.add(new User(userData.get(0), userData.get(1),
						userData.get(2), userData.get(3)));

			}

		} catch (FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}

	}

	public void writeUserFile() {

	}

	public List getUserList() {
		return userList;
	}

	/** String representation of users and their data. */
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		// Append each user line
		for (User u : userList) {
			sb.append(u.toString() + "\n");
		}

		return sb.toString();
	}

}

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
			URL url = UserController.class.getResource("/userFile.txt");
			File userFile = new File(url.toURI());
			final Scanner fileInput = new Scanner(userFile);
			fileInput.useDelimiter(",");

			while (fileInput.hasNextLine()) {
				String email = fileInput.next();
				String lastName = fileInput.next();
				String firstName = fileInput.next();
				String role = fileInput.next();
				System.out.println(email + ", " + lastName + ", " + firstName
						+ ", " + role);
			}

		} catch (FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void writeUserFile() {

	}

}

/*
 * HuSCII (Group 2)
 * TCSS 360 - Spring '15
 * User.java
 */

package models;

/**
 * Represents a user in the parks management system.
 * 
 * @author Duy Huynh
 * @version 3 May 2015
 */
public class User {

	// Instance fields:

	/** User's email address. */
	private String email;

	/** User's first name. */
	private String firstName;

	/** User's last name. */
	private String lastName;

	/** User's role. */
	private String role;

	// Constructor:

	protected User(final String email, final String lastName,
			final String firstName, final String role) {

		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;

	}

	/** Returns string representation of this user. */
	public String toString() {

		final StringBuilder sb = new StringBuilder();

		sb.append(email + "," + lastName + "," + firstName + "," + role);

		return sb.toString();
	}

}

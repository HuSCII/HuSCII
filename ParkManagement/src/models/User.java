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

	/**
	 * Create a User of the parks management system.
	 * 
	 * @param email
	 *            User's email address.
	 * @param lastName
	 *            User's last name.
	 * @param firstName
	 *            User's first name.
	 * @param role
	 *            User's role.
	 */
	protected User(final String email, final String lastName,
			final String firstName, final String role) {

		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;

	}

	/**
	 * Return's User email as a String.
	 * 
	 * @return Return User's email as a String.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Return's User last name as a String.
	 * 
	 * @return Return User's last name as a String.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Return's User first name as a String.
	 * 
	 * @return Return User's first name as a String.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Return's User role as a String.
	 * 
	 * @return Return User's role as a String.
	 */
	public String getRole() {
		return role;
	}

	/** Returns string representation of this user. */
	public String toString() {

		final StringBuilder sb = new StringBuilder();

		sb.append(email + "," + lastName + "," + firstName + "," + role);

		return sb.toString();
	}

}

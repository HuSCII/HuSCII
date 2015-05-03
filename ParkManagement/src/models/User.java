package models;

public abstract class User {

	// Instance fields:

	private String email;

	private String firstName;

	private String lastName;

	// Constructor:

	protected User(final String email, final String firstName,
			final String lastName) {

		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;

	}
	
	// print out user's info.
	
	public String toString() {
		
		return email + " " + firstName + " " + lastName;
	}

	/**
	 * Returns string representation of this user.
	 */
	public String toString() {

		final StringBuilder sb = new StringBuilder();
		sb.append(firstName + " " + lastName + ", " + email);

		return sb.toString();
	}

}

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

}

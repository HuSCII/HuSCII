package models;

public class Volunteer extends User {

	protected Volunteer(String email, String firstName, String lastName, String role) {
		super(email, firstName, lastName,role );
	}

	public void signUp(Job parkJob) {
		parkJob.addVolunteer(getEmail(), null);
	}

	public void findSignUpJobs() {
		JobController allJobs = new JobController();
		allJobs.getAllJobs();
	}

}

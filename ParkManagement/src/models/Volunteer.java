package models;

import models.Job.WorkCategories;

public class Volunteer extends User {

    public Volunteer(String email, String firstName, String lastName,
			String role) {
		super(email, firstName, lastName, role);
	}

	public void signUp(Job parkJob, WorkCategories category) {
		parkJob.addVolunteer(getEmail(), category);
	}

	public void findSignUpJobs() {
//		JobController allJobs = new JobController();
//		allJobs.getAllJobs();
	}

}

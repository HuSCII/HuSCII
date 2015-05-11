package models;

import models.Job.WorkCatagories;

public class Volunteer extends User {

    public Volunteer(String email, String lastName, String firstName,
			String role) {
		super(email, lastName, firstName, role);
	}

	public void signUp(Job parkJob, WorkCatagories category) {
		parkJob.addVolunteer(getEmail(), category);
	}

	public void findSignUpJobs() {
		JobController allJobs = new JobController();
		allJobs.getAllJobs();
	}

}

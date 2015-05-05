package models;

import models.Job.WorkCatagories;

public class Volunteer extends User {

	protected Volunteer(String email, String firstName, String lastName, String role) {
		super(email, firstName, lastName,role );
	}

	public void signUp(Job parkJob, WorkCatagories workCat) {
		parkJob.addVolunteer(getEmail(), workCat);
	}

	public void findSignUpJobs() {
		JobController allJobs = new JobController();
		allJobs.getAllJobs();
	}

}

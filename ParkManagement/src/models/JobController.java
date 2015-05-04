package models;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import models.Job.WorkCatagories;


public class JobController {

	/*
	 * holds current maximum number of allowed jobs at a given time.
	 */
	private static int MAX_JOBS = 30;
	
	/*
	 * stores current location of input and output file for jobs.
	 */
	private static String FILELOC = "jobFile.txt";
	
	/*
	 * stores a copy of all jobs in the system. 
	 */
	private ArrayList<Job> allJobs;
	
	/**
	 * Creates a new instance of a JobController.
	 */
	JobController() {
		allJobs = new ArrayList<Job>();
		loadJobData(FILELOC);
	}
	//*****PUBLIC METHODS*****//
	
	/**
	 * Adds a job to the list if maximum hasn't been reached.
	 * @param job job to be added to allJobs.
	 */
	public void addJob(Job job) {
		if(checkMaxJobs()) {
			//check week
			allJobs.add(job);
		}
		allJobs.add(job);
	}
	
	/**
	 * Returns a list of all upcoming jobs.
	 * @return upcoming jobs.
	 */
	public List<Job> getUpcomingJobs() {
		List<Job> upcoming = new ArrayList<Job>();
		for(int i=0; i<allJobs.size(); i++) {
			if(!allJobs.get(i).isCompleted(allJobs.get(i).getDate())) {
				//add copy of Job
				upcoming.add(new Job(allJobs.get(i)));
			} else {
				break;
			}
		}
		return upcoming;
	}
	
	
	/**
	 * Returns a list of all jobs.
	 * @return all jobs.
	 */
	public List<Job> getAllJobs() {
		List<Job> jobs = new ArrayList<Job>();
		for(Job job:allJobs) {
			jobs.add(new Job(job));
		}
		return jobs;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "404";
	}
	
	//*****PRIVATE METHODS*****//
	
	/**
	 * Loads in a list of jobs from file.
	 * @param filename location of file to read from.
	 * @return whether data was successfully loaded or not.
	 */
	private boolean loadJobData(String filename) {
		File file = new File(filename);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()) {
				StringTokenizer token = new StringTokenizer(scanner.nextLine(), ",");
				
				String parkName = token.nextToken();
				String jobName = token.nextToken();
				String date = token.nextToken();
				int duration = Integer.parseInt(token.nextToken());
				int currentLight = Integer.parseInt(token.nextToken());
				int maxLight = Integer.parseInt(token.nextToken());
				int currentMedium = Integer.parseInt(token.nextToken());
				int maxMedium = Integer.parseInt(token.nextToken());
				int currentHard = Integer.parseInt(token.nextToken());
				int maxHard = Integer.parseInt(token.nextToken());
				Map signedVolunteers = new HashMap<String, WorkCatagories>();
				//collect information
				int i = 0;
				while(token.hasMoreTokens()) {
					//loop for emails
					if(i>=currentLight) {
						if(i>=currentLight+currentMedium) {
							signedVolunteers.put(token.nextToken(), WorkCatagories.HEAVY);
						} else {
							signedVolunteers.put(token.nextToken(), WorkCatagories.MEDIUM);
						}
					} else {
						signedVolunteers.put(token.nextToken(), WorkCatagories.LIGHT);
					}
					i++;
				}
				//create job
				Job job = new Job(parkName, jobName, date, duration);
				//add job
				addJob(job);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Writes current job data for all jobs to file.
	 * @param filename file location of file to write to.
	 * @return whether writing file was a success or not.
	 */
	private boolean writeJobData(String filename) {
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//*****VALIDATION METHODS*****//
	
	/**
	 * Checks if the maximum job limit has been reached.
	 * @return whether maximum is met.
	 */
	private boolean checkMaxJobs() {
		if(allJobs.size()<MAX_JOBS) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the week quota(5) has been met for a given week.
	 * @return whether week quota is met.
	 */
	private boolean checkJobWeek(Job job) {
		GregorianCalendar pastDate = new GregorianCalendar(job.getDate().getTimeZone());
		GregorianCalendar futureDate = new GregorianCalendar(job.getDate().getTimeZone());
		int count = 0;
		pastDate.set(Calendar.DAY_OF_MONTH, pastDate.get(Calendar.DAY_OF_MONTH)-3);
		futureDate.set(Calendar.DAY_OF_MONTH, pastDate.get(Calendar.DAY_OF_MONTH)+3);
		
		for(Job aJob:allJobs) {
			if(job.getDate().compareTo(pastDate)>=0 &&
					job.getDate().compareTo(futureDate)<=0) {
				count++;
			}
			if(count>=5) {
				return false;
			}
		}
		return true;
	}
	
}

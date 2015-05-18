package models;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import models.Job.WorkCatagories;

/**
 * 
 * @author Ian McPeek
 *
 */
public class JobController {

	/* holds current maximum number of allowed jobs at a given time. */
	private static int MAX_JOBS = 30;
	
	/* stores a copy of all jobs in the system. */
	private ArrayList<Job> allJobs;
	
	/**
	 * Creates a new instance of a JobController by loading from 
	 * given filename.
	 */
	public JobController(String filename) {
		allJobs = new ArrayList<Job>();
		loadJobData(filename);
	}
	
	//*****PUBLIC METHODS*****//
	
	/**
	 * Adds a job to the list.
	 * @param job job to be added to allJobs.
	 */
	public void addJob(Job job) {
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
	 * Returns a String representation of all jobs.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Job job:allJobs) {
			sb.append(job.toString()+"\n");
		}
		return sb.toString();
	}
	
	//*****FILE I/O METHODS*****//
	
	/**
	 * Loads in a list of jobs from file.
	 * @param filename location of file to read from.
	 * @return whether data was successfully loaded or not.
	 */
	public boolean loadJobData(String filename) {
	    InputStream in = this.getClass().getResourceAsStream(filename);
	    //check if filename is valid
	    if(in==null) {
	        try {
                in.close();
            }
            catch (IOException e) {
                //e.printStackTrace();
            }
	        return false;
	    }
		Scanner scanner = new Scanner(in);
        while(scanner.hasNext()) {
        	StringTokenizer token = new StringTokenizer(scanner.nextLine(), ",");
        	
        	String parkManagerEmail = token.nextToken();
        	String parkName = token.nextToken();
        	String jobName = token.nextToken();
        	String date = token.nextToken();
        	int duration = Integer.parseInt(token.nextToken());
        	int currentLight = Integer.parseInt(token.nextToken());
        	int maxLight = Integer.parseInt(token.nextToken());
        	int currentMedium = Integer.parseInt(token.nextToken());
        	int maxMedium = Integer.parseInt(token.nextToken());
        	int currentHeavy = Integer.parseInt(token.nextToken());
        	int maxHeavy = Integer.parseInt(token.nextToken());
        	Map<String, WorkCatagories> signedVolunteers = new HashMap<String, WorkCatagories>();
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
        	Job job = new Job(parkManagerEmail,parkName, jobName, date, duration, 
        			currentLight, maxLight, currentMedium, maxMedium, 
        			currentHeavy, maxHeavy, signedVolunteers);
        	//add job
        	addJob(job);
        }
        scanner.close();
		return true;
	}
	
	/**
	 * Writes current job data for all jobs to file.
	 * @param filename file location of file to write to.
	 * @return whether writing file was a success or not.
	 */
	public boolean writeJobData(String filename) {
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(toString());
			writer.close();
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
}

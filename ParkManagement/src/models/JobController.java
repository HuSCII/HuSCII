package models;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import models.Job.WorkCatagories;


public class JobController {

	private List<Job> allJobs;
	
	JobController() {
		allJobs = new ArrayList<Job>();
	}
	//*****PUBLIC METHODS*****//
	public void addJob(Job job) {
		allJobs.add(job);
	}
	
	public List<Job> getUpcomingJobs() {
		return null;
		//job needs an isComplete.
	}
	
	public List<Job> getAllJobs() {
		//needs to check 
		return allJobs; //make sure to send a clone -Ian
	}
	
	//*****PRIVATE METHODS*****//
	private boolean loadJobData() {
		String fileloc = "jobFile.txt";
		File file = new File(fileloc);
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}

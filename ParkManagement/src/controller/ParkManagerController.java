package controller;

import java.util.Scanner;

import models.Job;
import models.JobController;
import models.ParkManager;

public class ParkManagerController {

	public static JobController jobController;
	public static ParkManager parkManager;
	public static Job jobs;
	private static Scanner keyboard;

	public static void menuScreen() {
		
		keyboard = new Scanner(System.in);

		System.out.println("Welcome To Park Manager Page");
		System.out.println("------------------------------");
		System.out.println("1. Submit a New Job");
		System.out.println("2. View Upcoming Jobs");
		System.out.println("3. View Volunteers");
		System.out.println("4. Back To Homepage");
		System.out.println("5. Exit");
		System.out.println("Please select menu choice 1-5: ");

		int menu = keyboard.nextInt();

		switch(menu) {
		case 1:
			submitJob();
			break;
		case 2:
			viewUpcomingJob();
			break;
		case 3:
			viewVolunteers();
			break;
		case 4:
			MainConsole mainTest = new MainConsole();
			String[] args = {};
			mainTest.main(args);
		case 5:
			break;
		default:
			System.out.println("Not in a menu choice");
			System.out.println("Please Try Aagain!");
			System.out.println("");
			ParkManagerController driver = new ParkManagerController();
			driver.menuScreen();
			break;
		}
	}
	
	
	public static void submitJob() {
		
//		System.out.println("Enter Park Name: ");
//		String parkName = keyboard.next();
		
	}
	
	public static void viewUpcomingJob() {
	
	}
	
	public static void viewVolunteers() {
		
	}
	
//	public static void main(String[] args) {
//		menuScreen();
//	}
	
	
	
}

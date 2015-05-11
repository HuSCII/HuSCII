package controller;

import java.util.Scanner;

import models.User;
import models.Volunteer;

public class VolunteerConsole {
	
	
	public static void volunteerStart(Volunteer volunteer) {
		int choice = 1;
		Scanner in = new Scanner(System.in);
		
		while(choice!=0) {
			System.out.println("Welcome back Volunteer!");
			System.out.println("Select an option from the list below.");
			
			System.out.println("1. View all upcoming jobs.");
			System.out.println("2. View jobs volunteer has signed up for.");
			System.out.println("0. Exit.");
			System.out.print("Make a selection: ");
			choice = in.nextInt();
			
			switch(choice) {
				case 1:
					break;
				case 2:
					break;
				case 0:
					break;
				default:
					System.out.println("Bad Input");
					break;
			}
		}
		
	}

	public static void viewUpcomingJobs() {
		
	}
	
	public static void viewSignedUpJobs() {
		
	}
	
	public static void signMeUp() {
		
	}
	
	public static void main(String[] args) {
		User user = new User("mickeymouse@gmail.com", 
			"Mouse", "Mickey", "Volunteer");

		volunteerStart(volunteer);
	}

}

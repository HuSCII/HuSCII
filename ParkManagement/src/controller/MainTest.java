package controller;

import java.util.Scanner;

import models.JobController;
import models.User;
import models.UserController;

public class MainTest {

	static UserController userController;
	static JobController jobController;

	public static void main(String[] args) {

		// Pre-load the User persistent data:
		userController = new UserController();
		userController.readUserFile("/testFile.csv");

		// Pre-load the Jobs persistent data:
		// instantiate a jobcontroller
		jobController = new JobController();
		// read in the jobs text file

		// Display doc
		displayDoc();

		// Display login prompt:
		String email = retrieveEmail();
		verifyEmail(email);

		// assign

	}

	private static void displayDoc() {
		System.out
				.println("*****************************************************************");
		System.out
				.println("* =================HuSCII Parks Management System============== *");
		System.out
				.println("* Software version 1.0                                          *");
		System.out.print("* Authors: Jingzhu Guo, Duy Huynh, ");
		System.out.println("Ian McPeek, Putthida Samrith *");
		System.out
				.println("* Copyright (c) 2015 HuSCII TCSS 360 Spring '15 Project Group.  *");
		System.out
				.println("*                                                               *");
		System.out
				.println("*****************************************************************");
		System.out.println();
	}

	private static String retrieveEmail() {
		System.out.println("Welcome to the HuSCII Parks Management v1.0");
		System.out.println("Please login with your email address:");
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}

	private static boolean verifyEmail(String email) {

		for (User u : userController.getUserList()) {
			if (email.equals(u.getEmail())) {
				System.out.println("You are a registered user!");
				System.out.println(u.getRole());
				return true;
			}
		}
		System.out.println("Email not found!");
		return false;
	}

	private static void assignRole(String email) {
		// Assign the role associated with the email

	}

	private static void switchToUserRole() {
		// Switch statement:

		// 1. Volunteer
		// volunteerConsole(userController,
		// 2. Parks Manager
		// 3. Administrator
	}

}

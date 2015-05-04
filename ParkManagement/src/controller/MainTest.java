package controller;

import models.UserController;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("This is a test of the parks management system:");

		UserController userController = new UserController();
		userController.readUserFile("/userFile.csv");
		userController.writeUserFile("abc.csv");
		System.out.println(userController);

	}

}

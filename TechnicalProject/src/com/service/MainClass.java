package com.service;

import java.util.Scanner;

import com.exception.InvalidUserException;
import com.pojo.User;

public class MainClass {

	public static void main(String[] args) {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		while (choice != 3) {
			System.out.println("==========================================================");
			System.out.println("1. Registration");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("==========================================================");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				UserService.register();
				break;
			case 2:
				try {
					User user = UserService.login();
					System.out.println("Login successful");
					System.out.println(user);
					UserService.showUserMenu();
				} catch (InvalidUserException e) {
					System.out.println(e.getMessage());
				}
				break;

			default:
				break;
			}
		}
	}
}

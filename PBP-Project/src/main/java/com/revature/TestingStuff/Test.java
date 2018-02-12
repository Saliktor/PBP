package com.revature.TestingStuff;

import java.util.Scanner;

import com.revature.services.UserServiceImp;

public class Test {

	private static UserServiceImp userService = new UserServiceImp();
	private static Scanner reader = new Scanner(System.in);
	
	public static void main(String[] args) {
		
	}
	
	public void testCreateUser() {
		System.out.println("Enter username: ");
		String username = reader.nextLine();
		System.out.println("Enter password: ");
		String password = reader.nextLine();
		System.out.println("Enter email: ");
		String email = reader.nextLine();
		
		userService.createUser(username, password, email);

	}

}

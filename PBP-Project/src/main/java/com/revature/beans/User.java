package com.revature.beans;

import java.util.HashSet;

public class User {
	String firstname;
	String lastname;
	String username;
	String password;
	String email;
	int userID;
	boolean isAdmin;
	
	HashSet<Integer> createdThreads;
}

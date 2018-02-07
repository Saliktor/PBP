package com.revature.beans;

import java.util.HashSet;

public class User {
	String firstname;
	String lastname;
	String username;
	String password;
	String email;
	int id;
	boolean isAdmin;
	boolean isBanned;
	boolean isMuted;
	
	HashSet<Integer> createdThreads;
	HashSet<Post> createdPost;
	HashSet<Post> repliedPost;
}

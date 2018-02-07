package com.revature.beans;

import java.util.HashSet;

public class Thread {
	String title;
	String body;
	int id;
	
	//Set of users who have voted on this post
	HashSet<Post> votedUsers;
	
	//User who created the thread
	User threadCreator;
	
	//String represented the type of game being played. Redudant with game object below?
	String gameType;
	
	//Game object that thread is playing
	//Game typeGame;
}

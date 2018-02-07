package com.revature.beans;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

public class Post {
	int id;
	String body;
	
	//The post this object replied to
	Post repliedTo;
	
	//This is the post who replied to this post object
	Post repliedFrom;
	
	//User who created the post
	User postCreater;
	
	//Timestamp for when post was created
	Timestamp postCreation;
	
	//List of edits to the post. 
	List<Edit> editList;
	
	//Set of users who have voted on this post
	HashSet<Post> votedUsers;
	
	//Post are associated to a team
	String team;
	
	//Move done by the post
	//Move moveType;
	

}

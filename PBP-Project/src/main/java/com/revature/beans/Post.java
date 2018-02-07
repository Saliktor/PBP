package com.revature.beans;

import java.sql.Timestamp;
import java.util.HashSet;

public class Post {
	//The post this object replied to
	Post repliedTo;
	
	//Text of the post
	String body;
	
	//Timestamp for when post was created
	Timestamp postCreation;
	
	//Set of edits to the post
	HashSet<Edit> editSet;
	
	//Set of users who have voted on this post
	HashSet<Post> votedUsers;
}

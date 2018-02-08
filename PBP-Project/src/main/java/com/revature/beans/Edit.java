package com.revature.beans;

import java.sql.Timestamp;

public class Edit {
	int id;
	//Testing commit
	//Timestamp designating when edit occurred
	Timestamp timeEdited;
	
	//User who did the edit
	User whoEdited;
	
	//Text associated with the edit. New body for post
	String content;
	
	//ID of post that was editted
	int postID;

}

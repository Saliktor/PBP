package com.revature.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="bear")
public class User {
	@Id
	@SequenceGenerator(name="user_pk",sequenceName="userid_seq", allocationSize=1)
	@GeneratedValue(generator="user_pk", strategy=GenerationType.SEQUENCE)
	int id;
	String firstname;
	String lastname;
	String username;
	String password;
	String email;
	int isAdmin; 		// 0 for no, 1 for yes
	int isBanned;		// 0 for no, 1 for yes
	int isMuted;		// 0 for no, 1 for yes
	
	Set<Integer> createdThreads;
	Set<Post> createdPost;
	Set<Post> repliedPost;
}

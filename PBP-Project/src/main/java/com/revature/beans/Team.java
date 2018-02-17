package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {

	@Id
	int id;
	String teamName;
	
	public Team() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}
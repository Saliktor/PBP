package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@Id
	int id;
	String teamName;
//	@OneToMany(fetch=FetchType.EAGER, mappedBy="team")
//	Set<Player> players = new HashSet<Player>();
	
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

//	public Set<Player> getPlayers() {
//		return players;
//	}
//
//	public void setPlayers(Set<Player> players) {
//		this.players = players;
//	}
//	
//	public void addPlayer(Player player) {
//		this.players.add(player);
//	}
	
	
	
	
}

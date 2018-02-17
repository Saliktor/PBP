package com.revature.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

//To be treated as an instance of user for a game

@Entity
@Table(name="player")
@PrimaryKeyJoinColumn(name="ID")
public class Player extends UserAccount{
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "GAMEID")
	Game game;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "TEAM")
	Team team;
	
	public Player() {
		super();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [game=");
		builder.append(game);
		builder.append(", team=");
		builder.append(team);
		builder.append("]");
		return builder.toString();
	}
	
	
}

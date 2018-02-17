package com.revature.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//To be treated as an instance of user for a game

@Entity
@Table(name="Player")
public class Player{
	@Id
	@SequenceGenerator(name="player_pk",sequenceName="player_seq", allocationSize=1)
	@GeneratedValue(generator="player_pk", strategy=GenerationType.SEQUENCE)
	int id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn (name = "USERID")
	UserAccount user;
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

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
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
		builder.append("Player [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", game=");
		builder.append(game);
		builder.append(", team=");
		builder.append(team);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}

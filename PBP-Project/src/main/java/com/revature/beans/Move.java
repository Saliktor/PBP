package com.revature.beans;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "move")
public class Move {

	@Id
	@SequenceGenerator(name="move_pk",sequenceName="move_seq", allocationSize=1)
	@GeneratedValue(generator="move_pk", strategy=GenerationType.SEQUENCE)
	int id;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "GAMEID")
	Game game;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "PLAYERID")
	Player player;
	Timestamp timeMade;
	String play;
	
	public Move() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Timestamp getTimeMade() {
		return timeMade;
	}
	public void setTimeMade(Timestamp timeMade) {
		this.timeMade = timeMade;
	}
	public String getPlay() {
		return play;
	}
	public void setPlay(String play) {
		this.play = play;
	}
	
	
	
}

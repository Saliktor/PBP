package com.revature.beans;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Move {

	@Id
	int id;
	Game game;
	Player player;
	Timestamp timeMade;
	String play;
}

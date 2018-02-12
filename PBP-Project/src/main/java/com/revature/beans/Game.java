package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Game {
	
	@Id
	@SequenceGenerator(name="game_pk",sequenceName="game_seq", allocationSize=1)
	@GeneratedValue(generator="game_pk", strategy=GenerationType.SEQUENCE)
	int id;
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
	Set<Move> moves = new HashSet<Move>();
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
	Set<Player> players = new HashSet<Player>();
	
	public Game() {
		
	}
	
	//Square[][] boardstate = new Square[8][8]; 
	
/*	Game() {
		super();
		for (int i=0 ; i<8 ; i++) {
			for (int j=0 ; j<8 ;j++) {
				this.boardstate[i][j] = new Square(i,j,0);
			}
		}
		this.boardstate[3][3].value = 1;
		this.boardstate[4][4].value = 1;
		this.boardstate[3][4].value = 2;
		this.boardstate[4][3].value = 2;
	}
	
	public void printBoard() {
		for (int i=0 ; i<8 ; i++){
			for (int j=0 ; j<8 ; j++) {
				String val = boardstate[i][j].value == 0 ? " " : ""+boardstate[i][j].value;
				System.out.print("["+val+"]");
			}
			System.out.print("\n");
		}
		return;
	}*/
	
	
}

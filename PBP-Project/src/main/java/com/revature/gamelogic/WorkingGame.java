package com.revature.gamelogic;

import java.util.Arrays;

import com.revature.beans.Game;
import com.revature.beans.Team;

public class WorkingGame implements Cloneable {
	private int id;
	private Square[][] boardstate = new Square[8][8]; 
	private Team whoseTurn;
	
	public WorkingGame() {
		super();
		for (int i=0 ; i<8 ; i++) {
			for (int j=0 ; j<8 ;j++) {
				this.boardstate[i][j] = new Square(i,j,0);
			}
		}
		this.boardstate[3][3].setValue(1);
		this.boardstate[4][4].setValue(1);
		this.boardstate[3][4].setValue(2);
		this.boardstate[4][3].setValue(2);
	}
	
	public WorkingGame(Game g) {
		this.id = g.getId();
		
		this.boardstate[0][0] = new Square(0,0,g.getAa());
		this.boardstate[0][1] = new Square(0,1,g.getAb());
		this.boardstate[0][2] = new Square(0,2,g.getAc());
		this.boardstate[0][3] = new Square(0,3,g.getAd());
		this.boardstate[0][4] = new Square(0,4,g.getAe());
		this.boardstate[0][5] = new Square(0,5,g.getAf());
		this.boardstate[0][6] = new Square(0,6,g.getAg());
		this.boardstate[0][7] = new Square(0,7,g.getAh());

		this.boardstate[1][0] = new Square(1,0,g.getBa());
		this.boardstate[1][1] = new Square(1,1,g.getBb());
		this.boardstate[1][2] = new Square(1,2,g.getBc());
		this.boardstate[1][3] = new Square(1,3,g.getBd());
		this.boardstate[1][4] = new Square(1,4,g.getBe());
		this.boardstate[1][5] = new Square(1,5,g.getBf());
		this.boardstate[1][6] = new Square(1,6,g.getBg());
		this.boardstate[1][7] = new Square(1,7,g.getBh());

		this.boardstate[2][0] = new Square(2,0,g.getCa());
		this.boardstate[2][1] = new Square(2,1,g.getCb());
		this.boardstate[2][2] = new Square(2,2,g.getCc());
		this.boardstate[2][3] = new Square(2,3,g.getCd());
		this.boardstate[2][4] = new Square(2,4,g.getCe());
		this.boardstate[2][5] = new Square(2,5,g.getCf());
		this.boardstate[2][6] = new Square(2,6,g.getCg());
		this.boardstate[2][7] = new Square(2,7,g.getCh());

		this.boardstate[3][0] = new Square(3,0,g.getDa());
		this.boardstate[3][1] = new Square(3,1,g.getDb());
		this.boardstate[3][2] = new Square(3,2,g.getDc());
		this.boardstate[3][3] = new Square(3,3,g.getDd());
		this.boardstate[3][4] = new Square(3,4,g.getDe());
		this.boardstate[3][5] = new Square(3,5,g.getDf());
		this.boardstate[3][6] = new Square(3,6,g.getDg());
		this.boardstate[3][7] = new Square(3,7,g.getDh());

		this.boardstate[4][0] = new Square(4,0,g.getEa());
		this.boardstate[4][1] = new Square(4,1,g.getEb());
		this.boardstate[4][2] = new Square(4,2,g.getEc());
		this.boardstate[4][3] = new Square(4,3,g.getEd());
		this.boardstate[4][4] = new Square(4,4,g.getEe());
		this.boardstate[4][5] = new Square(4,5,g.getEf());
		this.boardstate[4][6] = new Square(4,6,g.getEg());
		this.boardstate[4][7] = new Square(4,7,g.getEh());

		this.boardstate[5][0] = new Square(5,0,g.getFa());
		this.boardstate[5][1] = new Square(5,1,g.getFb());
		this.boardstate[5][2] = new Square(5,2,g.getFc());
		this.boardstate[5][3] = new Square(5,3,g.getFd());
		this.boardstate[5][4] = new Square(5,4,g.getFe());
		this.boardstate[5][5] = new Square(5,5,g.getFf());
		this.boardstate[5][6] = new Square(5,6,g.getFg());
		this.boardstate[5][7] = new Square(5,7,g.getFh());

		this.boardstate[6][0] = new Square(6,0,g.getGa());
		this.boardstate[6][1] = new Square(6,1,g.getGb());
		this.boardstate[6][2] = new Square(6,2,g.getGc());
		this.boardstate[6][3] = new Square(6,3,g.getGd());
		this.boardstate[6][4] = new Square(6,4,g.getGe());
		this.boardstate[6][5] = new Square(6,5,g.getGf());
		this.boardstate[6][6] = new Square(6,6,g.getGg());
		this.boardstate[6][7] = new Square(6,7,g.getGh());

		this.boardstate[7][0] = new Square(7,0,g.getHa());
		this.boardstate[7][1] = new Square(7,1,g.getHb());
		this.boardstate[7][2] = new Square(7,2,g.getHc());
		this.boardstate[7][3] = new Square(7,3,g.getHd());
		this.boardstate[7][4] = new Square(7,4,g.getHe());
		this.boardstate[7][5] = new Square(7,5,g.getHf());
		this.boardstate[7][6] = new Square(7,6,g.getHg());
		this.boardstate[7][7] = new Square(7,7,g.getHh());
		
		this.whoseTurn = g.getWhoseTurn();
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(boardstate);
		result = prime * result + id;
		result = prime * result + ((whoseTurn == null) ? 0 : whoseTurn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkingGame other = (WorkingGame) obj;
		if (!Arrays.deepEquals(boardstate, other.boardstate))
			return false;
		if (id != other.id)
			return false;
		if (whoseTurn == null) {
			if (other.whoseTurn != null)
				return false;
		} else if (!whoseTurn.equals(other.whoseTurn))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "WorkingGame [id=" + id + ", boardstate=" + Arrays.toString(boardstate) + ", whoseTurn=" + whoseTurn
				+ "]";
	}

	public void printBoard() {
		System.out.println("    0  1  2  3  4  5  6  7");
		for (int i=0 ; i<8 ; i++){
			System.out.print(" "+i+" ");
			for (int j=0 ; j<8 ; j++) {
				String val = this.boardstate[i][j].getValue() == 0 ? " " : ""+this.boardstate[i][j].getValue();
				System.out.print("["+val+"]");
			}
			System.out.print("\n");
		}
		return;
	}
}

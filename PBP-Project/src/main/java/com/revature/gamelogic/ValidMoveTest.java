package com.revature.gamelogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class ValidMoveTest {
	
	static Logger log = Logger.getLogger(ValidMoveTest.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ValidMoveTest vmt = new ValidMoveTest();
		
		Game startingBoard = new Game();		
		
		System.out.println("First Board");
		startingBoard.printBoard();
		System.out.println("========================");

		
		Game availableMoves = new Game(startingBoard.boardstate);
		
		System.out.println("*************** *********** ************\nstartingBoard");
		startingBoard.printBoard();
		System.out.println(availableMoves == startingBoard);
		
		int myTeam = 1; // 1 for white, 2 for black
		for (Square s : vmt.findValidMoves(availableMoves, myTeam)) {
			availableMoves.boardstate[s.idx][s.idy].value = 8;
		}
		System.out.println("Available Moves - Start");
		availableMoves.printBoard();
		System.out.println("========================");

		System.out.println("*************** *********** ************\nstartingBoard");
		startingBoard.printBoard();
		System.out.println(availableMoves == startingBoard);
		
		
		System.out.println("************************************\navailableMoves");
		availableMoves.printBoard();
		
		System.out.println("*************** *********** ************\nstartingBoard");
		startingBoard.printBoard();
		System.out.println(availableMoves == startingBoard);
		
		
		
		Square move = new Square(4,2,myTeam);
		Game afterMove = new Game (vmt.makeMove(startingBoard,move,myTeam).boardstate);
//		startingBoard = vmt.makeMove(startingBoard,move,myTeam);
		System.out.println("Made a move");
		afterMove.printBoard();
//		startingBoard.printBoard();
		System.out.println("========================");
		
//		availableMoves = new Game(afterMove.boardstate);
		availableMoves = new Game(startingBoard.boardstate);
		
		myTeam = 2; // 1 for white, 2 for black
		for (Square s : vmt.findValidMoves(availableMoves, myTeam)) {
			availableMoves.boardstate[s.idx][s.idy].value = 8;
		}
		System.out.println("Available Moves - After Move 1");
		availableMoves.printBoard();
		System.out.println("========================");
		
		myTeam = 2;
		move = new Square(5,2,myTeam);
//		afterMove = vmt.makeMove(startingBoard,move,myTeam);
		startingBoard = vmt.makeMove(startingBoard,move,myTeam);
		System.out.println("Checking available moves");
//		afterMove.printBoard();
		startingBoard.printBoard();
		System.out.println("========================");
	}
	
	public Set<Square> findValidMoves(Game game, int myTeam) {
		Set<Square> validMoves = new HashSet<Square>();
		Square current = new Square();
		int x,y;
		for (int i=0 ; i<8 ; i++){
			for (int j=0 ; j<8 ; j++) {
				current = game.boardstate[i][j];
				if (current.value == 1 || current.value == 2) {
					continue;
				}
//				System.out.println("Looking at ["+i+"]["+j+"]");
				try {
					if (isValidMove(game,current,1,1,myTeam)) {
						validMoves.add( game.boardstate[current.idx][current.idy] );
						continue;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					x = current.idx+1;
					y = current.idy+1;
//					System.out.println("findValidMoves: "+e+": ["+x+"]["+y+"]");
				}
				try {
					if (isValidMove(game,current,1,0,myTeam)) {
						validMoves.add( game.boardstate[current.idx][current.idy] );
						continue;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					x = current.idx+1;
					y = current.idy+0;
//					System.out.println("findValidMoves: "+e+": ["+x+"]["+y+"]");
				}
				try {
					if (isValidMove(game,current,1,-1,myTeam)) {
						validMoves.add( game.boardstate[current.idx][current.idy] );
						continue;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					x = current.idx+1;
					y = current.idy-1;
//					System.out.println("findValidMoves: "+e+": ["+x+"]["+y+"]");
				}
				try {
					if (isValidMove(game,current,0,1,myTeam)) {
						validMoves.add( game.boardstate[current.idx][current.idy] );
						continue;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					x = current.idx+0;
					y = current.idy+1;
//					System.out.println("findValidMoves: "+e+": ["+x+"]["+y+"]");
				}
				try {
					if (isValidMove(game,current,-1,1,myTeam)) {
						validMoves.add( game.boardstate[current.idx][current.idy] );
						continue;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					x = current.idx-1;
					y = current.idy+1;
//					System.out.println("findValidMoves: "+e+": ["+x+"]["+y+"]");				
				}
				try {
					if (isValidMove(game,current,0,-1,myTeam)) {
						validMoves.add( game.boardstate[current.idx][current.idy] );
						continue;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					x = current.idx+0;
					y = current.idy-1;
//					System.out.println("findValidMoves: "+e+": ["+x+"]["+y+"]");			
				}
				try {
					if (isValidMove(game,current,-1,0,myTeam)) {
						validMoves.add( game.boardstate[current.idx][current.idy] );
						continue;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					x = current.idx-1;
					y = current.idy+0;
//					System.out.println("findValidMoves: "+e+": ["+x+"]["+y+"]");				
				}
				try {
					if (isValidMove(game,current,-1,-1,myTeam)) {
						validMoves.add( game.boardstate[current.idx][current.idy] );
						continue;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					x = current.idx-1;
					y = current.idy-1;
//					System.out.println("findValidMoves: "+e+": ["+x+"]["+y+"]");				
				}
//				System.out.println(" : Not valid move\n");
			}
		}
		return validMoves;
	}

	public boolean isValidMove(Game game, Square current, int xplus, int yplus, int myTeam) {
		try {
			Square adjacent = game.boardstate[current.idx+xplus][current.idy+yplus];
			int x = current.idx+xplus;
			int y = current.idy+yplus;
			int otherTeam = (myTeam == 1 ? 2 : 1);
//			System.out.println("     Checking ["+x+"]["+y+"]");
			if (current.value == 0) {
				if (adjacent.value == myTeam) {
					return false;
				}
				else {
					if (adjacent.value == 0) {
						return false;
					}
					else {
						do {
							adjacent = game.boardstate[adjacent.idx+xplus][adjacent.idy+yplus];
							if (adjacent.value == myTeam) {
								return true;
							}
						} while (adjacent.value != 0 && adjacent.value != 8);
						return false;
					}
				}
			}
			else {
				return false;
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		catch (NullPointerException e) {
			System.out.println(e);
			return false;
		}
	}

	// Assumes it receives a valid move.
	public Game makeMove(Game game, Square move, int myTeam) {
		Set<Square> toFlip = new HashSet<Square>();
		//log.trace("In method");
		int i =0;
		toFlip.add(move);
		
		toFlip.addAll(toFlip(game,move, 1, 1,myTeam));
//		System.out.println(i++ +"# Current toFlip:");
//		for (Square s: toFlip)
//			System.out.println(s.toString());
		
		toFlip.addAll(toFlip(game,move, 0, 1,myTeam));
//		System.out.println(i++ +"#Current toFlip:");
//		for (Square s: toFlip)
//			System.out.println(s.toString());
//		
		toFlip.addAll(toFlip(game,move,-1, 1,myTeam));
//		System.out.println(i++ +"#Current toFlip:");
//		for (Square s: toFlip)
//			System.out.println(s.toString());
//		
		
		toFlip.addAll(toFlip(game,move, 1, 0,myTeam));
//		System.out.println(i++ +"#Current toFlip:");
//		for (Square s: toFlip)
//			System.out.println(s.toString());
//		
		toFlip.addAll(toFlip(game,move,-1, 0,myTeam));
//		System.out.println(i++ +"#Current toFlip:");
//		for (Square s: toFlip)
//			System.out.println(s.toString());
		
		
		toFlip.addAll(toFlip(game,move, 1,-1,myTeam));
//		System.out.println(i++ +"#Current toFlip:");
//		for (Square s: toFlip)
//			System.out.println(s.toString());
		
		toFlip.addAll(toFlip(game,move, 0,-1,myTeam));
//		System.out.println(i++ +"#Current toFlip:");
//		for (Square s: toFlip)
//			System.out.println(s.toString());
		
		toFlip.addAll(toFlip(game,move,-1,-1,myTeam));
//		System.out.println(i++ +"#Current toFlip:");
//		for (Square s: toFlip)
//			System.out.println(s.toString());
		
		
		
//		System.out.println("Printing toFlip");
		for (Square s : toFlip) {
//			System.out.println(s.toString());
			game.boardstate[s.idx][s.idy].value = s.value;
		}
		return game; 
	}
	
	public Set<Square> toFlip(Game game, Square move, int xplus, int yplus, int myTeam) {
		Set<Square> flipped = new HashSet<Square>();
		Square temp = game.boardstate[xplus+move.idx][yplus+move.idy];
		int otherTeam = (myTeam == 1 ? 2 : 1);
		try {
			if (temp.value == myTeam) {
				return flipped;
			}
			
// there is something wrong with the logic in the loop. Come back to this.
			
// See current output.
			
			while (true) {
				if (temp.value == myTeam) {
					break;
				}
				if (temp.value == otherTeam) {
					temp.value = myTeam;
					flipped.add(temp);
				}
				if (temp.value == 0) {
					flipped.clear();
//					System.out.println("In 0 case. Here is flipped:");
//					for (Square s : flipped) 
//						System.out.println(s.toString());
					return flipped;
				}	
				temp = game.boardstate[xplus+temp.idx][yplus+temp.idy];
			}
			return flipped;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Went out of bounds.");
			flipped.clear();
			return flipped;
		}
	}
}






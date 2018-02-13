package com.revature.gamelogic;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

public class ValidMoveTest {
	
	static Logger log = Logger.getLogger(ValidMoveTest.class);
	
	static boolean noMoreMoves = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ValidMoveTest vmt = new ValidMoveTest();
		
		Game startingBoard = new Game();	
		vmt.takeATurn(startingBoard);
		
//		System.out.println("First Board");
//		startingBoard.printBoard();
//		System.out.println("========================");
//
//		
//		Game availableMoves = new Game(startingBoard.boardstate);
//		
////		System.out.println("*************** *********** ************\nstartingBoard");
////		startingBoard.printBoard();
////		System.out.println(availableMoves == startingBoard);
//		
//		int myTeam = 1; // 1 for white, 2 for black
//		for (Square s : vmt.findValidMoves(availableMoves, myTeam)) {
//			availableMoves.boardstate[s.idx][s.idy].value = 8;
//		}
//		System.out.println("Available Moves - Start");
//		availableMoves.printBoard();
//		System.out.println("========================");
//		
//		// this is stupid that I need to do this
//		
//
////		System.out.println("*************** *********** ************\nstartingBoard");
////		startingBoard.printBoard();
////		System.out.println(availableMoves == startingBoard);
////		
////		
////		System.out.println("************************************\navailableMoves");
////		availableMoves.printBoard();
////		
////		System.out.println("*************** *********** ************\nstartingBoard");
////		startingBoard.printBoard();
////		System.out.println(availableMoves == startingBoard);
//		
//		
//		
//		Square move = new Square(4,2,myTeam);
//		startingBoard = new Game (vmt.makeMove(startingBoard,move,myTeam).boardstate);
////		startingBoard = vmt.makeMove(startingBoard,move,myTeam);
//		System.out.println("Made a move");
//		startingBoard.printBoard();
////		startingBoard.printBoard();
//		System.out.println("========================");
//		
////		availableMoves = new Game(afterMove.boardstate);
//		availableMoves = new Game(startingBoard.boardstate);
//		
//		myTeam = 2; // 1 for white, 2 for black
//		for (Square s : vmt.findValidMoves(availableMoves, myTeam)) {
//			availableMoves.boardstate[s.idx][s.idy].value = 8;
//		}
//		System.out.println("Available Moves - After Move 1");
//		availableMoves.printBoard();
//		System.out.println("========================");
//		
//		myTeam = 2;
//		move = new Square(3,2,myTeam);
////		afterMove = vmt.makeMove(startingBoard,move,myTeam);
//		startingBoard = vmt.makeMove(startingBoard,move,myTeam);
//		System.out.println("After move 2");
////		afterMove.printBoard();
//		startingBoard.printBoard();
//		System.out.println("========================");
//		
//		myTeam = 1; // 1 for white, 2 for black
//		for (Square s : vmt.findValidMoves(availableMoves, myTeam)) {
//			availableMoves.boardstate[s.idx][s.idy].value = 8;
//		}
//		System.out.println("Available Moves - After Move 2");
//		availableMoves.printBoard();
//		System.out.println("========================");
//		
//		myTeam = 1;
//		move = new Square(2,2,myTeam);
////		afterMove = vmt.makeMove(startingBoard,move,myTeam);
//		startingBoard = vmt.makeMove(startingBoard,move,myTeam);
//		System.out.println("After move 2");
////		afterMove.printBoard();
//		startingBoard.printBoard();
//		System.out.println("========================");
//		
//		myTeam = 2; // 1 for white, 2 for black
//		for (Square s : vmt.findValidMoves(availableMoves, myTeam)) {
//			availableMoves.boardstate[s.idx][s.idy].value = 8;
//		}
//		System.out.println("Available Moves - After Move 2");
//		availableMoves.printBoard();
//		System.out.println("========================");
	}
	
	public void takeATurn(Game game) {
		int i = 1;
		int myTeam = 1;
		Scanner sc = new Scanner(System.in);
		String moveInput;
		boolean mostlyTrue = true;
		String[] moveV2 = new String[2];
		int[] xy =  new int[2];
		
		while (mostlyTrue) {
			System.out.println("Turn "+ i++ +" : (8 indicates available moves for Team "+myTeam+")");
			
			for (Square s : findValidMoves(game, myTeam)) {
				game.boardstate[s.idx][s.idy].value = 8;
			}
			game.printBoard();
			System.out.println("===========================");
			System.out.print("Team "+myTeam+" move ('y,x') > ");
			moveInput = sc.nextLine();
			moveV2 = moveInput.split(",");
			xy[0]=Integer.parseInt(moveV2[0]);
			xy[1]=Integer.parseInt(moveV2[1]);
			game = makeMove(game, new Square(xy[0],xy[1],myTeam), myTeam);
			
			System.out.println("===========================");
			System.out.println();
			
			myTeam = (myTeam == 1 ? 2 : 1);
		}
		sc.close();
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
				}
			}
		}
		if (validMoves.isEmpty()) {
			if (noMoreMoves) {
				noMoreMoves = false;
				game.printBoard();
				endOfGame(game);
				
			}
			else {
				noMoreMoves = true;
				int otherTeam = (myTeam == 1 ? 2 : 1);
				validMoves = findValidMoves(game, otherTeam);
				
				// needs to send these moves to the server 
				// WITH a note that the moves are for the other team
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
		
		for (int i = 0; i < 8 ; i++) {
			for (int j=0 ; j < 8 ; j++) {
				if (game.boardstate[i][j].value == 8) {
					game.boardstate[i][j].value = 0;
				}
			}
		}
		
		Set<Square> toFlip = new HashSet<Square>();
		log.trace("In method");
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
		
//		System.out.println("===========================");
//		System.out.println("Before switch");
//		game.printBoard();
//		System.out.println("===========================");

		
//		System.out.println("Printing toFlip");
		for (Square s : toFlip) {
//			System.out.println(s.toString());
			game.boardstate[s.idx][s.idy].value = s.value;
		}
		return game; 
	}
	
	public Set<Square> toFlip(Game game, Square move, int xplus, int yplus, int myTeam) {
//		game.printBoard();
		Set<Square> flipped = new HashSet<Square>();
//		Square temp = game.boardstate[xplus+move.idx][yplus+move.idy];
		int otherTeam = (myTeam == 1 ? 2 : 1);
		try {
			Square temp = new Square(xplus+move.idx,yplus+move.idy,game.boardstate[xplus+move.idx][yplus+move.idy].value);

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
//				temp = game.boardstate[xplus+temp.idx][yplus+temp.idy];
				temp = new Square(xplus+temp.idx,yplus+temp.idy,game.boardstate[xplus+temp.idx][yplus+temp.idy].value);
			}
			return flipped;
		}
		catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("Went out of bounds.");
			flipped.clear();
			return flipped;
		}
	}

	public void endOfGame(Game game) {
		int teamOneScore = 0;
		int teamTwoScore = 0;
		for (int i=0 ; i < 8 ; i++) {
			for (int j = 0 ; j < 8 ; j++) {
				if (game.boardstate[i][j].value == 1) {
					teamOneScore++;
				}
				else if (game.boardstate[i][j].value == 2) {
					teamTwoScore++;
				}
			}
		}
		System.out.println("Team 1 Score: " + teamOneScore);
		System.out.println("Team 2 Score: " + teamTwoScore);
		System.exit(0);
		// somehow return the data
	}
}






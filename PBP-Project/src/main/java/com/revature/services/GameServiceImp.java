package com.revature.services;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.revature.beans.Game;
import com.revature.beans.Move;
import com.revature.beans.Player;
import com.revature.beans.Team;
import com.revature.beans.UserAccount;
import com.revature.dao.GameDAOImp;
import com.revature.gamelogic.Square;
import com.revature.gamelogic.WorkingGame;

@Component
public class GameServiceImp implements GameService {
	private static boolean noMoreMoves = false;
	private static GameDAOImp gameDAO = new GameDAOImp();
	
	/* Only two teams are needed for all players
	 * Person who creates the game will be assigned white team
	 * Other player in game will be assigned to black
	 * Only works for 1v1 games
	 */
	private static Team white_team, black_team;
	static {
		white_team = new Team();
		white_team.setId(1);
		white_team.setTeamName("white");
		
		black_team = new Team();
		black_team.setId(2);
		black_team.setTeamName("black");
	}


	public WorkingGame makeMove(int xid, int yid, Player player) {
		//TODO
		Move newMove = new Move();
		int team = player.getTeam().getId();
		
		Square newSquare = new Square(xid, yid, team);
		Game game = null;
		
		//Call dao to retrieve game
		//game = GameDao.getGame(player.gameid);
		
		WorkingGame wg = new WorkingGame(/*game*/);
		wg = makeMove(wg, newSquare, team);
		
		//Update gameboard in database
		game = new Game(wg);
		//GameDao.updateGame(WorkingGame);
		
		return wg;
	}
	
	public Set<Square> findValidMoves(Player player){
		//TODO
		int team = player.getTeam().getId();
		//Call dao to retrieve game
		//game = GameDao.getGame(player.gameid);
		
		WorkingGame wg = new WorkingGame(/*game*/);
		
		return findValidMoves(wg, team);
		
	}
	
	// Assumes it receives a valid move.
		public WorkingGame makeMove(WorkingGame game, Square move, int myTeam) {
			
			for (int i = 0; i < 8 ; i++) {
				for (int j=0 ; j < 8 ; j++) {
					if (game.boardstate[i][j].value == 8) {
						game.boardstate[i][j].value = 0;
					}
				}
			}
			
			Set<Square> toFlip = new HashSet<Square>();
			int i =0;
			toFlip.add(move);
			toFlip.addAll(toFlip(game,move, 1, 1,myTeam));
			toFlip.addAll(toFlip(game,move, 0, 1,myTeam));
			toFlip.addAll(toFlip(game,move,-1, 1,myTeam));
			toFlip.addAll(toFlip(game,move, 1, 0,myTeam));
			toFlip.addAll(toFlip(game,move,-1, 0,myTeam));
			toFlip.addAll(toFlip(game,move, 1,-1,myTeam));
			toFlip.addAll(toFlip(game,move, 0,-1,myTeam));
			toFlip.addAll(toFlip(game,move,-1,-1,myTeam));
			for (Square s : toFlip) {
				game.boardstate[s.idx][s.idy].value = s.value;
			}
			return game; 
		}
		
		public Set<Square> toFlip(WorkingGame game, Square move, int xplus, int yplus, int myTeam) {
			Set<Square> flipped = new HashSet<Square>();
			int otherTeam = (myTeam == 1 ? 2 : 1);
			try {
				Square temp = new Square(xplus+move.idx,yplus+move.idy,game.boardstate[xplus+move.idx][yplus+move.idy].value);

				if (temp.value == myTeam) {
					return flipped;
				}
				
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
						return flipped;
					}	
					temp = new Square(xplus+temp.idx,yplus+temp.idy,game.boardstate[xplus+temp.idx][yplus+temp.idy].value);
				}
				return flipped;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				flipped.clear();
				return flipped;
			}
		}
		

		public void endOfGame(WorkingGame game) {
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
		
		public void takeATurn(WorkingGame game) {
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
		
		
		public Set<Square> findValidMoves(WorkingGame game, int myTeam) {
			Set<Square> validMoves = new HashSet<Square>();
			Square current = new Square();
			
			int x,y;
			for (int i=0 ; i<8 ; i++){
				for (int j=0 ; j<8 ; j++) {
					current = game.boardstate[i][j];
					if (current.value == 1 || current.value == 2) {
						continue;
					}
//					System.out.println("Looking at ["+i+"]["+j+"]");
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
			
			//This functionality should be moved to angular
//			if (validMoves.isEmpty()) {
//				if (noMoreMoves) {
//					noMoreMoves = false;
//					game.printBoard();
//					endOfGame(game);
//					
//				}
//				else {
//					noMoreMoves = true;
//					int otherTeam = (myTeam == 1 ? 2 : 1);
//					validMoves = findValidMoves(game, otherTeam);
//					
//					// needs to send these moves to the server 
//					// WITH a note that the moves are for the other team
//				}
//			}
			return validMoves;
		}

		
		public boolean isValidMove(WorkingGame game, Square current, int xplus, int yplus, int myTeam) {
			try {
				Square adjacent = game.boardstate[current.idx+xplus][current.idy+yplus];
				int x = current.idx+xplus;
				int y = current.idy+yplus;
				int otherTeam = (myTeam == 1 ? 2 : 1);
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

		public int[][] getBoardState(Player player) {
			//TODO
			Game game = new Game();
			
			int[][] dummyarray = {{1,2},{3,4}};
			return dummyarray;
			//Should return the int[][] representing the boardstate
			//return gameDao.getGame(Player.getGameId);
		}

		public boolean updateGame(Game game) {
			// TODO Auto-generated method stub
			return false;
		}

		public void createNewGame(Player player) {
			//Set players team to white cause they created the game
			player.setTeam(white_team);
			
			//Add the player to the game object
			Game game = player.getGame();
			game.addPlayer(player);

			//Persist the new player and game
			gameDAO.createNewGameAndPlayer(player);			
		}
		
		
		/* Creates a new game and player on behalf of the user
		 * Returns the newly updated user object containing newly added player
		 */
		public Player createNewGame(UserAccount user) {
			//Create a new player and assign the user and team
			Player player = new Player();
			player.setUser(user);
			player.setTeam(white_team);
			
			//Create a new game. Assign player to game and game to player (dont like this)
			Game newGame = new Game();
			newGame.addPlayer(player);
			newGame.setWhoseTurn(white_team);
			player.setGame(newGame);

			//Persist the new player and game
			gameDAO.createNewGameAndPlayer(player);			
			
			//Update the user object
			user.addPlayer(player);
			
			//return the new player
			return player;
		}
		
		public Player joinGameAsNewUser(UserAccount user, int gameID) {
			Game game = gameDAO.getGame(gameID);
			
			//If no game found matching the id, then not valid and return null
			if(game == null)
				return null;
			
			Set<Player> players = game.getPlayers();
			
			/*
			 * If the game already has two players then this is not a valid request
			 * This would be changed if spectators are allowed or more than 2 players per game
			 */
			if(players.size() >= 2)
				return null;
			/*
			 * Check for if the currentUser already has a player attached to game
			 * If a player is found then return null for this is invalid request
			 */
			for(Player player: players) {
				if(player.getUser().getId() == user.getId()) {
					return null;
				}
			}
			
			//Create new player for user and make team black
			Player player = new Player();
			player.setGame(game);
			player.setTeam(black_team);
			player.setUser(user);
			
			//
			game.addPlayer(player);
			game.setWhoseTurn(black_team);
			
			user.addPlayer(player);
			
			//Persist the new player.This dao call works for just player as well
			gameDAO.createNewGameAndPlayer(player);	
			
			return player;
		}

		public Game getGame(Player player) {
			// TODO Auto-generated method stub
			return null;
		}
}

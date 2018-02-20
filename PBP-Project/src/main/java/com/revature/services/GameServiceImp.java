package com.revature.services;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.revature.beans.Game;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Message;
import com.revature.beans.Player;
import com.revature.beans.Team;
import com.revature.beans.UserAccount;
import com.revature.dao.GameDAO;
import com.revature.dao.MessageDAO;
import com.revature.gamelogic.Square;
import com.revature.gamelogic.WorkingGame;

@Component
public class GameServiceImp implements GameService {
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static GameDAO gameDAO = ac.getBean(GameDAO.class);
	private static MessageDAO mDAO = ac.getBean(MessageDAO.class);

	private static boolean noMoreMoves = false;
	
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
	
	public void makeMove(Square move, Player player) {
		
		//Call overloaded makeMove and retrieve the new WorkingGame
		Game game = player.getGame();
		WorkingGame wg = new WorkingGame(game);
		wg = makeMove(wg, move, move.getValue());
		
		//Flip whose turn it is
		if(wg.getWhoseTurn() == white_team) 
			wg.setWhoseTurn(black_team);
		else
			wg.setWhoseTurn(white_team);
		
		//Create a new game based on the updated WorkingGame and update player
		game = new Game(wg);
		player.setGame(game);
		//Update the player/game
		gameDAO.updateGame(player);		
	}

	public List<Message> getNewMessages(Game game, Timestamp timestamp) {
		return mDAO.getNewMessages(game, timestamp);
	}

	public boolean saveMessage(Message message) {
		if (mDAO.saveMessage(message))
			return true;
		
		return false;
	}

	
	public Set<Square> findValidMoves(Player player){
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
					if (game.getBoardstate()[i][j].getValue() == 8) {
						game.getBoardstate()[i][j].setValue(0);
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
				game.getBoardstate()[s.getIdx()][s.getIdy()].setValue(s.getValue());
			}
			return game; 
		}
		
		public Set<Square> toFlip(WorkingGame game, Square move, int xplus, int yplus, int myTeam) {
			Set<Square> flipped = new HashSet<Square>();
			int otherTeam = (myTeam == 1 ? 2 : 1);
			try {
				Square temp = new Square(xplus+move.getIdx(),yplus+move.getIdy(),game.getBoardstate()[xplus+move.getIdx()][yplus+move.getIdy()].getValue());

				if (temp.getValue() == myTeam) {
					return flipped;
				}
				
				while (true) {
					
					if (temp.getValue() == myTeam) {
						break;
					}
					if (temp.getValue() == otherTeam) {
						temp.setValue(myTeam);
						flipped.add(temp);
					}
					if (temp.getValue() == 0) {
						flipped.clear();
						return flipped;
					}	
					temp = new Square(xplus+temp.getIdx(),yplus+temp.getIdy(),game.getBoardstate()[xplus+temp.getIdx()][yplus+temp.getIdy()].getValue());
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
					if (game.getBoardstate()[i][j].getValue() == 1) {
						teamOneScore++;
					}
					else if (game.getBoardstate()[i][j].getValue() == 2) {
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
					game.getBoardstate()[s.getIdx()][s.getIdy()].setValue(8);
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
					current = game.getBoardstate()[i][j];
					if (current.getValue() == 1 || current.getValue() == 2) {
						continue;
					}
//					System.out.println("Looking at ["+i+"]["+j+"]");
					try {
						if (isValidMove(game,current,1,1,myTeam)) {
							validMoves.add( game.getBoardstate()[current.getIdx()][current.getIdy()] );
							continue;
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {
						x = current.getIdx()+1;
						y = current.getIdy()+1;
					}
					try {
						if (isValidMove(game,current,1,0,myTeam)) {
							validMoves.add( game.getBoardstate()[current.getIdx()][current.getIdy()] );
							continue;
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {
						x = current.getIdx()+1;
						y = current.getIdy()+0;
					}
					try {
						if (isValidMove(game,current,1,-1,myTeam)) {
							validMoves.add( game.getBoardstate()[current.getIdx()][current.getIdy()] );
							continue;
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {
						x = current.getIdx()+1;
						y = current.getIdy()-1;
					}
					try {
						if (isValidMove(game,current,0,1,myTeam)) {
							validMoves.add( game.getBoardstate()[current.getIdx()][current.getIdy()] );
							continue;
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {
						x = current.getIdx()+0;
						y = current.getIdy()+1;
					}
					try {
						if (isValidMove(game,current,-1,1,myTeam)) {
							validMoves.add( game.getBoardstate()[current.getIdx()][current.getIdy()] );
							continue;
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {
						x = current.getIdx()-1;
						y = current.getIdy()+1;
					}
					try {
						if (isValidMove(game,current,0,-1,myTeam)) {
							validMoves.add( game.getBoardstate()[current.getIdx()][current.getIdy()] );
							continue;
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {
						x = current.getIdx()+0;
						y = current.getIdy()-1;
					}
					try {
						if (isValidMove(game,current,-1,0,myTeam)) {
							validMoves.add( game.getBoardstate()[current.getIdx()][current.getIdy()] );
							continue;
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {
						x = current.getIdx()-1;
						y = current.getIdy()+0;
					}
					try {
						if (isValidMove(game,current,-1,-1,myTeam)) {
							validMoves.add( game.getBoardstate()[current.getIdx()][current.getIdy()] );
							continue;
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {
						x = current.getIdx()-1;
						y = current.getIdy()-1;
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
				Square adjacent = game.getBoardstate()[current.getIdx()+xplus][current.getIdy()+yplus];
				int x = current.getIdx()+xplus;
				int y = current.getIdy()+yplus;
				int otherTeam = (myTeam == 1 ? 2 : 1);
				if (current.getValue() == 0) {
					if (adjacent.getValue() == myTeam) {
						return false;
					}
					else {
						if (adjacent.getValue() == 0) {
							return false;
						}
						else {
							do {
								adjacent = game.getBoardstate()[adjacent.getIdx()+xplus][adjacent.getIdy()+yplus];
								if (adjacent.getValue() == myTeam) {
									return true;
								}
							} while (adjacent.getValue() != 0 && adjacent.getValue() != 8);
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
		
		public Game updateGame(Player player) {
			if(gameDAO.updateGame(player))
				return player.getGame();
			
			return null;
		}


		public Game getGame(Player player) {
			return null;
		}

		public Set<Player> getUserPlayers(UserAccount user) {
			return gameDAO.getUserPlayersAndGames(user);
		}

}

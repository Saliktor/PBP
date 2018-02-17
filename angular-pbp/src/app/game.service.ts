import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs/observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/map';
import { Game } from './game';
import { User } from './user';
import { Player } from './player';
import { WorkingGame } from './WorkingGame';

@Injectable()
export class GameService {
  private boardstateURL = 'http://localhost:8080/PBP/game-boardstate';
  private playerSignInURL = 'http://localhost:8080/PBP/game-player';
  private newGameURL = 'http://localhost:8080/PBP/game-new';
  private headers = new Headers({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': true});

  constructor(private http: Http) { }


  /*Retrieves the boardstate for the game currently in session according to what player is signed in on java side. Be sure
   that the player has signed in before this method is called */
  // getGameBoard() {
  //   return this.http.get(this.boardstateURL, { headers: this.headers, withCredentials: true })
  //   .map(resp => {
  //     const gameboard = new Game();
  //     gameboard.boardState = resp.json() as Number[][];
  //     return gameboard;
  //   });
  // }

  createNewGame() {
    const body = this.createNewGameBody();
    return this.http.post(this.newGameURL, body, { headers: this.headers, withCredentials: true})
      .map(resp => {
        return resp.json() as WorkingGame
      });
  }

  /* Creates a new game and player and returns the player as a JSON object string */
  createNewGameBody(): String{
    const currentUser = JSON.parse(localStorage.getItem('currentUser')) as User;
    const newGame = new Game();

    const newPlayer = new Player();
    newPlayer.id = currentUser.id;
    newPlayer.username = currentUser.username;
    newPlayer.password = currentUser.password;
    newPlayer.email = currentUser.email;
    newPlayer.isAdmin = currentUser.isAdmin;
    newPlayer.isBanned = currentUser.isBanned;
    newPlayer.isMuted = currentUser.isMuted;
    newPlayer.game = newGame;

    return JSON.stringify(newPlayer);
  }

  /*Signs in a player to the selected game and user. This is a call to server to get the player attached to the current user and selected
    game. Server should return a player type */
  // playerSignIn(game: Game, user: User) {
  //   // const body = `userID=${user.id}&gameID=${game.gameID}`;
  //   const game1 = new Game();
  //   game1.id = 666;
  //   const player = new Player();
  //   player.id = 88;
  //   player.game = game1;
  //   //player.game = game1;
   
  //   const body = `${JSON.stringify(player)}`;
  //   console.log(JSON.stringify(player));
  //   // const body = `userID=100&gameID=32`;
  //   return this.http.post(this.playerSignInURL, body, { headers: this.headers, withCredentials: true })
  //   .map(resp => {
  //     const player = resp.json();
  //     // localStorage.setItem('currentUser', JSON.stringify(user));
  //     return player;
  //   });
  // }

  /*Retrieve all players associated with the user*/

}

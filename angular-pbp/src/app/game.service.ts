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
  private workingGameURL = 'http://localhost:8080/PBP/game-workinggame';
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
    this.http.post(this.newGameURL, body, { headers: this.headers})
      .map(resp => {
        const player = resp.json() as Player;
        localStorage.setItem('player', JSON.stringify(player));
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

  /* Player signs in to the server to let server know what player the user wishes to become. Server will
    reply with a WorkingGame */
  playerSignIn(player: Player) {
    const body = JSON.stringify(player);
    return this.http.post(this.playerSignInURL, body, { headers: this.headers})
    .map(resp => {
      const workingGame = resp.json() as WorkingGame;

      //Possible scenario of resp being null?

      localStorage.setItem('player', JSON.stringify(player));
      return workingGame;
    });
  }
}

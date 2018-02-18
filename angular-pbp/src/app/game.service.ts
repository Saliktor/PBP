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

  /*Call made by angular to create a new game session
  * Makes a new player containing the new game tied to currentUser
  * Will recieve an updated player from server with proper ids that will save player to session
  */
  /*Call made to server to create a new game session on behalf of the user
  *
  */
  createNewGame() {
    // const body = this.createNewGameBody();
    return this.http.get(this.newGameURL, { headers: this.headers, withCredentials: true})
      .map(resp => {
        const player = resp.json() as Player;
        localStorage.setItem('player', JSON.stringify(player));
        return player;
      });
  }

  /* Creates a new game and player and returns the player as a JSON object string 
  *  Helper Method
  */
  createNewGameBody(): String{
    const currentUser = JSON.parse(localStorage.getItem('currentUser')) as User;
    const newGame = new Game();

    const newPlayer = new Player();
    newPlayer.user = currentUser;
    newPlayer.game = newGame;

    return JSON.stringify(newPlayer);
  }


  getWorkingGame() {
    const body = JSON.parse(localStorage.getItem('player')) as Player;
    return this.http.post(this.workingGameURL, body, { headers: this.headers, withCredentials: true})
      .map(resp => {
        return resp.json() as WorkingGame;
      });
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

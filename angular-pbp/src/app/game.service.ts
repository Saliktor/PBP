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

  /*Call made to server to create a new game session on behalf of the user
  * Adds newly returned player to localstorage for this will be the player and game used
  * Updates the currentUser in localstorage with new player
  */
  createNewGame() {
    return this.http.get(this.newGameURL, { headers: this.headers, withCredentials: true})
      .map(resp => {
        const player = resp.json() as Player;
        localStorage.setItem('player', JSON.stringify(player));
        this.addNewPlayerToUser(player);
        return player;
      });
  }

  /* Adds a new player to list of players attached to currentUser
  * Updates the currentUser in localstorage just in case
  * Helper Method
  */
  addNewPlayerToUser(player: Player){
    const currentUser = JSON.parse(localStorage.getItem('currentUser')) as User;
    const players = currentUser.players;
    players.push(player);
    currentUser.players = players;
    localStorage.setItem('currentUser', JSON.stringify(currentUser));
  }

  /* Call to server to grab the WorkingGame 
  */
  getWorkingGame() {
    return this.http.get(this.workingGameURL, { headers: this.headers, withCredentials: true})
      .map(resp => {
        return resp.json() as WorkingGame;
      });
  }

  /* Player signs in to the server to let server know what player the user wishes to become. Server will
    reply with a WorkingGame */
  // playerSignIn(player: Player) {
  //   const body = JSON.stringify(player);
  //   return this.http.post(this.playerSignInURL, body, { headers: this.headers})
  //   .map(resp => {
  //     const workingGame = resp.json() as WorkingGame;

  //     //Possible scenario of resp being null?

  //     localStorage.setItem('player', JSON.stringify(player));
  //     return workingGame;
  //   });
  // }
}

import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs/observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/map';
import { Game } from './game';
import { User } from './user';
import { Player } from './player';
import { WorkingGame } from './WorkingGame';
import { Square } from './square';

@Injectable()
export class GameService {
  private workingGameURL = 'http://localhost:8080/PBP/game-workinggame';
  private playerSignInURL = 'http://localhost:8080/PBP/game-player';
  private newGameURL = 'http://localhost:8080/PBP/game-new';
  private joinGameURL = 'http://localhost:8080/PBP/game-join';
  private joinGameAsNewUserURL = 'http://localhost:8080/PBP/game-join-new';
  private makeMoveURL = 'http://localhost:8080/PBP/game-makemove';


  private headers = new Headers({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': true});

  // This header is used when creating string literal to send instead of parsing whole object
  private nonObjectHeaders = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': true});

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
  addNewPlayerToUser(player: Player) {
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

  /* Join game session as player
  * Not to be used for new user to a game session
  */
  joinGameSession(player: Player) {
    const body = JSON.stringify(player);
    return this.http.post(this.joinGameURL, body, {headers: this.headers, withCredentials: true})
      .map(resp => {
        return resp.json() as WorkingGame;
      });
  }

  /* Call for when a new user joins game session through game_id invitation
  * Receives new player associated to game session and user
  */
  joinGameSessionNewPlayer(gameID: Number) {
    const body = `gameID=${gameID}`;
    return this.http.post(this.joinGameAsNewUserURL, body, {headers: this.nonObjectHeaders, withCredentials: true})
      .map(resp => {
        return resp.json() as Player;
      });
  }


  /* Call to server to make a move
  * Receives new workinggame
  */
  makeMove(move: Square) {
    console.log('Server makeMove');
    const body = JSON.stringify(move);
    return this.http.post(this.makeMoveURL, body, {headers: this.headers, withCredentials: true})
      .map(resp => {
        const player = resp.json() as Player;
        localStorage.setItem('player', JSON.stringify(player));
        this.updatePlayerInUser(player);
        return player;
      });
  }
  updatePlayerInUser(p:Player) {
    let u = JSON.parse(localStorage.getItem('currentUser')) as User;
    let i = 0;
    console.log(u);
    for (let a of u.players) {
      if (a.id === p.id) {
        console.log("Player overwritten");
        u.players[i] = p;
        break;
      }
      i++;
    }
    localStorage.setItem('currentUser',JSON.stringify(u));
    console.log(u);
  }
}

import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs/observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/map';
import { Game } from './game';

@Injectable()
export class GameService {
  private boardstateURL = 'http://localhost:8080/PBP/game-boardstate';
  private headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': true});

  constructor(private http: Http) { }

  getGameBoard() {
    return this.http.get(this.boardstateURL, { headers: this.headers, withCredentials: true })
    .map(resp => {
      const gameboard = new Game();
      gameboard.boardState = resp.json() as Number[][];
      return gameboard;
    });
  }

}

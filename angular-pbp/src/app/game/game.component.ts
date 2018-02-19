import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { Player } from '../player';
import { User } from '../user';
import { Square } from '../square';
import { WorkingGame } from '../WorkingGame';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  workingGame: WorkingGame;
  player: Player;

  constructor(private gameService: GameService ) {}

  ngOnInit() {
    // const user = JSON.parse(localStorage.getItem('currentUser')) as User;
    // this.joinGameSession(user.players[0]);

    // const square = new Square();
    // square.idx = 5;
    // square.idy = 3;
    // square.value = 1;
    // this.makeMove(square);
  }

  sendMessage() {
    console.log('Send message was called but no implementation');
  }

  /*Call to server to create a new game session */
  createNewGame() {
    return this.gameService.createNewGame().subscribe( player => {
      this.player = player;
      return this.gameService.getWorkingGame().subscribe( workingGame => {
        console.log(workingGame);
        // Update board here
      });
    });
  }

  /*Call to server to retrieve the WorkingGame of game session
  * This is used for players only, not new users to game session
  */
  joinGameSession(player: Player) {
    this.gameService.joinGameSession(player).subscribe( workingGame => {
      this.workingGame = workingGame;
      // Update board here
    });
  }

    /*Call to server to retrieve the WorkingGame of game session
  * This is used for players only, not new users to game session
  */
  joinGameSessionNewPlayer(gameID: Number) {
    this.gameService.joinGameSessionNewPlayer(gameID).subscribe( player => {
      this.player = player;
      this.gameService.getWorkingGame().subscribe( workingGame => {
        this.workingGame = workingGame;
        // Update board here
      });
    });


  }

  makeMove(move: Square) {
    this.gameService.makeMove(move).subscribe( player => {
      this.player = player;
      this.gameService.getWorkingGame().subscribe( workingGame => {
        this.workingGame = workingGame;
        // Update board here
      });
    });

  }





}

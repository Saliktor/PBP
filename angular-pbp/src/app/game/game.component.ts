import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { Player } from '../player';
import { User } from '../user';
import { Square } from '../square';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  constructor(private gameService: GameService ) {}

  ngOnInit() {
    const square = new Square();
    square.idx = 3;
    square.idy = 8;
    square.value = 1;
    this.makeMove(square);
  }

  sendMessage() {
    console.log('Send message was called but no implementation');
  }

  /*Call to server to create a new game session */
  createNewGame() {
    return this.gameService.createNewGame().subscribe( player => {
      console.log(player);
      return this.gameService.getWorkingGame().subscribe( workingGame => {
        console.log(workingGame);
      });
    });
  }

  /*Call to server to retrieve the WorkingGame of game session
  * This is used for players only, not new users to game session
  */
  joinGameSession(player: Player) {
    return this.gameService.joinGameSession(player).subscribe( workingGame => {
      console.log(workingGame);
    });
  }

    /*Call to server to retrieve the WorkingGame of game session
  * This is used for players only, not new users to game session
  */
  joinGameSessionNewPlayer(gameID: Number) {
    this.gameService.joinGameSessionNewPlayer(gameID).subscribe( player => {
      console.log(player);
    });
    return this.gameService.getWorkingGame().subscribe( workingGame => {
      console.log( workingGame );
    });
  }

  makeMove(move: Square) {
    console.log('Making move');
    this.gameService.makeMove(move).subscribe( workingGame => {
      console.log(workingGame);
    });
  }





}

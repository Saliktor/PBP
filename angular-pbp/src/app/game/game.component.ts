import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  constructor(private gameService: GameService ) {}

  ngOnInit() {
    this.createNewGame();
  }

  sendMessage() {
    console.log('Send message was called but no implementation');
  }

  createNewGame() {
    this.gameService.createNewGame().subscribe( player => {
      console.log(player);
      // this.gameService.getWorkingGame().subscribe( workingGame => {
      //   console.log(workingGame);
      // });
    });


  }



}

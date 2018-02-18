import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { Player } from '../player';
import { User } from '../user';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  constructor(private gameService: GameService ) {}

  ngOnInit() {
  }

  sendMessage() {
    console.log('Send message was called but no implementation');
  }

  createNewGame() {
    this.gameService.createNewGame().subscribe( player => {
      console.log(player);
      this.gameService.getWorkingGame().subscribe( workingGame => {
        console.log(workingGame);
      });
    });
  }

  joinGameSession(player: Player){
    this.gameService.joinGameSession(player).subscribe( workingGame =>{
      console.log(workingGame);
    });
  }



}

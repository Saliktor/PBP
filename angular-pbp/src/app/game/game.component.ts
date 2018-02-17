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
    this.gameService.createNewGame().subscribe( workingGame => { console.log(workingGame); });

    // this.gameService.getGameBoard().subscribe( boardstate => {
    //   console.log(boardstate);
    // });

    // this.gameService.playerSignIn(null, null).subscribe( player => {
    //   console.log(player);
    // });
  }

  sendMessage(){
    console.log('Send message was called but no implementation');
  }

}

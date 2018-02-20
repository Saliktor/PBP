import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { Player } from '../player';
import { User } from '../user';
import { Square } from '../square';
import { WorkingGame } from '../WorkingGame';
import { Message } from "../message";
import { Headers, Http } from '@angular/http';
import { GetMessagesService } from '../get-messages.service'

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  workingGame: WorkingGame;
  player: Player;
  public message : Message;
  public messages : Message[];
  public timeStamp: Date;

  constructor(private gameService: GameService, private http: Http, private GetMessagesService: GetMessagesService ) {}

  ngOnInit() {
    const user = JSON.parse(localStorage.getItem('currentUser')) as User;
    this.joinGameSession(user.players[0]);

    this.timeStamp = new Date('18 FEB 2018 03:16:23:520000000 PM');
    console.log(this.timeStamp.toDateString());
    //I think that if I want the chatbox to get recent messages when first seen, I do it here
      this.GetMessagesService.getNewMessages(this.timeStamp)
      // it should return an array of Messages so I need to push, concat the items into my messages array
      //Also I don't think
      .subscribe(messages => this.messages = messages

      );
      this.messages.push(this.message);
      this.message= new Message('');
// const square = new Square(3,2,1);
// square.idx = 3;
// square.idy = 2;
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
        // this.workingGame = workingGame;
        console.log(workingGame);
        this.updateBoardState(workingGame);
      });
    });

  }

  updateBoardState(game: WorkingGame) {

    for (let i = 0; i < 64 ; i++) {
      // console.log(document.getElementById(""+i));
      let pieces = <HTMLScriptElement[]><any>document.getElementById(""+i).childNodes;
      // console.log(pieces);
      switch (game.boardstate[Math.floor(i/8)][i%8].value) {
          case 0:
              //pieces[0].setAttribute("hidden","true");
              pieces[0].style.display = "none";
              pieces[1].style.display = "none";
              pieces[2].style.display = "none";
              break;
          case 1:
              pieces[0].style.display = "block";
              pieces[1].style.display = "none";
              pieces[2].style.display = "none";
              break;
          case 2:
              pieces[0].style.display = "none";
              pieces[1].style.display = "block";
              pieces[2].style.display = "none";
              break;
          case 8:
              pieces[0].style.display = "none";
              pieces[1].style.display = "none";
              pieces[2].style.display = "block";
              break;
          default:
              console.log("Received invalid value in WorkingGame["+(i/8)+"]["+(i%8)+"]");
              break;
      }
    }
  }

  clickMove(i: number) {
    console.log(i + " : " + this.player.team.id + " = " + (this.player.team.id === this.player.game.whoseTurn.id));
    if (this.player.team.id === this.player.game.whoseTurn.id) {
      const newSquare = new Square(Math.floor(i/8),i%8,this.player.team.id);
      this.makeMove(newSquare);
    }
    else {
      console.log("IT'S NOT YOUR TURN.");
    }

  }




}




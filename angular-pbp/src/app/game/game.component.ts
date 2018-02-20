import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { Player } from '../player';
import { User } from '../user';
import { Square } from '../square';
import { WorkingGame } from '../WorkingGame';
import { Message } from "../Message";
import { Headers, Http } from '@angular/http';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  workingGame: WorkingGame;
  player: Player;

  constructor(private gameService: GameService, private http: Http ) {}

  ngOnInit() {
    const user = JSON.parse(localStorage.getItem('currentUser')) as User;
    this.joinGameSession(user.players[0]);
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

  private messageUrl = 'http://localhost:8070/PBP/message/newmessage'
  public message: Message = new Message('');

  saveMessage(message : Message){
    if(message){
      console.log(`messageContent is ${message.messageContent} and timeStamp is ${message.timePosted.getMilliseconds()}`);
    const body = `messageContent=${message.messageContent}&timeStamp=${message.timePosted.getMilliseconds()  }`;
    return this.http.post(this.messageUrl, body ,{ headers: this.headers, withCredentials: true})
    //syntax error need to fix response
    // not sure what needs to happen after I send data to the server and if i get a repsonse back
      .map (resp => {
        console.log(resp);
        const message = resp.json() as Message;
        if(message == null){
            return null;
        }else{
            this.message = message
            return message;
        }
      }
    );
  }else{console.log("Invalid message recieved")}
  }
}


}

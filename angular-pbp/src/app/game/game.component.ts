import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { Player } from '../player';
import { User } from '../user';
import { Game } from '../game';
import { Square } from '../square';
import { WorkingGame } from '../WorkingGame';
import { Message } from "../message";
import { Headers, Http } from '@angular/http';
import { GetMessagesService } from '../get-messages.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  public workingGame: WorkingGame;
  public player: Player;
  public user: User;
  public message : Message;
  public messages : Message[];
  public timeStamp: Date;
  public gameid: number;

//window.setTimeout(this.updateGameInUser(),100);

  constructor(private route: ActivatedRoute, private gameService: GameService, private http: Http, private GetMessagesService: GetMessagesService, private userService: UserService, private router: Router ) {
    this.user = JSON.parse(localStorage.getItem('currentUser')) as User;
    console.log(this.user);
    this.route.params.subscribe(res => {
      console.log("In the lamba");
      this.gameid = res.id;
      let foundGame = false;
      for ( let p of this.user.players ) {
        console.log(p.game.id + " : " + res.id);
        if (p.game.id == res.id) {
          this.player = p;
          console.log("Found the game.");
          this.joinGameSession(p);
          foundGame = true;
        }
      }
      if (!foundGame) {
        console.log("Game not found");
        this.joinGameSessionNewPlayer(res.id);
      }
    });

    this.updateGameInUser();
    // setInterval(this.updateGameInUser(),1000);
    this.message = new Message('');
    this.messages = [
      new Message("This is a test message.",new Date(Date.now()))
    ];

  }

  ngOnInit() {

    // console.log(this.user.players[0]);
    // this.player = this.user.players[0];
    // this.gameid = this.user.players[0].game.id;
    // // find player.game.id from url:id. if not found -> joinGameSessionNewPlayer
    // this.joinGameSession(this.user.players[0]);

    this.timeStamp = new Date("18 Jan 1970");
    console.log("First Print: "+this.timeStamp.toDateString());
    //I think that if I want the chatbox to get recent messages when first seen, I do it here
      this.GetMessagesService.getNewMessages(this.timeStamp, this.gameid)
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
        this.updateBoardState(workingGame);
      });
    });
  }

  /*Call to server to retrieve the WorkingGame of game session
  * This is used for players only, not new users to game session
  */
  joinGameSession(player: Player) {
    this.gameService.joinGameSession(player).subscribe( workingGame => {
      this.workingGame = workingGame;
      this.updateBoardState(workingGame);
    });
  }

    /*Call to server to retrieve the WorkingGame of game session
  * This is used for players only, not new users to game session
  */
  joinGameSessionNewPlayer(gameID: Number) {

    this.gameService.joinGameSessionNewPlayer(gameID).subscribe( player => {
      this.player = player;
      console.log(player);
      this.gameService.getWorkingGame().subscribe( workingGame => {
        this.workingGame = workingGame;
        this.updateBoardState(workingGame);
      });
    });


  }

  makeMove(move: Square) {
    this.gameService.makeMove(move).subscribe( player => {
      this.player = player;
      console.log("***********************************************");
      console.log(player);
      this.gameService.getWorkingGame().subscribe( workingGame => {
        // this.workingGame = workingGame;
        console.log(workingGame);
        this.updateBoardState(workingGame);
      });
    });

  }

  updateBoardState(game: WorkingGame) {
    console.log("Updating Game");
    console.log(game);
    let validMoveExists = false;
    let blackpiece = 0;
    let whitepiece = 0;
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
              whitepiece++;
              break;
          case 2:
              pieces[0].style.display = "none";
              pieces[1].style.display = "block";
              pieces[2].style.display = "none";
              blackpiece++;
              break;
          case 8:
              pieces[0].style.display = "none";
              pieces[1].style.display = "none";
              pieces[2].style.display = "block";
              validMoveExists = true;
              break;
          default:
              console.log("Received invalid value in WorkingGame["+(i/8)+"]["+(i%8)+"]");
              break;
      }
    }
    if (!validMoveExists) {
      document.getElementById("end-game-card").style.display = "block";
      document.getElementById("white-score").innerText = ""+whitepiece;
      document.getElementById("black-score").innerText = ""+blackpiece;
    }
  }

  clickMove(i: number) {
    console.log(i + " : " + this.player.team.id + " = " + (this.player.team.id === this.player.game.whoseTurn.id));
    if (this.player.team.id === this.player.game.whoseTurn.id) {
      const newSquare = new Square(Math.floor(i/8),i%8,this.player.team.id);
      this.makeMove(newSquare);
      this.player = JSON.parse(localStorage.getItem('currentUser')) as Player;
    }
    else {
      console.log("IT'S NOT YOUR TURN.");
    }

  }

  loggedIn(): boolean{
    if (!this.userService.loggedIn()) {
      this.router.navigate(['./login']);
    }
    return true;
  }

  updateGameInUser() {
    this.gameService.getWorkingGame().subscribe( workingGame => {
      // this.workingGame = workingGame;
      console.log("Called updateGameInUser");
      this.updateBoardState(workingGame);
      //setTimeout(this.updateGameInUser(),100);
    });
  }
}




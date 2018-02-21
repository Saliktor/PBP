import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { Message } from '../message';
import { GetMessagesService } from '../get-messages.service';
import { User } from '../user';
import { GameService } from '../game.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public message : Message;
  public messages : Message[];
  public timeStamp: Date;
  public user: User;
  public newGameID: string;

  constructor(private userService: UserService, private router: Router, private GetMessagesService: GetMessagesService, private GameService: GameService) {
    this.user = JSON.parse(localStorage.getItem('currentUser')) as User;
    console.log(this.user);
    this.message = new Message('');
    this.messages = [
      new Message('Welcome to Reversi Simulator', new Date())
    ];
  }
  ngOnInit() {
  }
  loggedIn(): boolean{
    if (!this.userService.loggedIn()) {
      this.router.navigate(['./login']);
    }
    return true;
  }

  loadGame(gameid: number) {
    console.log(gameid);
  }

  createNewGameFromHome() {
    this.GameService.createNewGame().subscribe( player => {
      let game = player.game;
      this.router.navigate(['./game/'+game.id]);
    });
  }

  joinAGame() {
    this.router.navigate(['./game/'+this.newGameID]);
  }
}
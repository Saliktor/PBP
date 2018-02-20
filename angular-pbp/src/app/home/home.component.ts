import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { Message } from '../message';
import { GetMessagesService } from '../get-messages.service';
import { User } from '../user';

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

  constructor(private userService: UserService, private router: Router, private GetMessagesService: GetMessagesService) {
    this.message = new Message('');
    this.messages = [
      new Message('Welcome to Reversi Simulator', new Date())
    ];
  }
  ngOnInit() {
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

      this.user = JSON.parse(localStorage.getItem('currentUser')) as User;
      console.log(this.user);
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
}
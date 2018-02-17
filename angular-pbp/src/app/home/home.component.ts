import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { Message } from '../message';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public message : Message;
  public messages : Message[];
  constructor(private userService: UserService, private router: Router) { 
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

}

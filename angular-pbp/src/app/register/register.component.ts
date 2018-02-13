import { Component, OnInit } from '@angular/core';

import {CurrentUser} from '../current-user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private username: string;
  private password: string;
  private email: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  register(): void {
    console.log(this.username);
    console.log(this.password);
    console.log(this.email);
    this.userService.register(this.username, this.password, this.email)
    .subscribe( user => {
    });
  }
}


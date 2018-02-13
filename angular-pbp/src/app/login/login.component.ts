import { Component, OnInit } from '@angular/core';

import {CurrentUser} from '../current-user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loggedUser: CurrentUser;
  private username: string;
  private password: string;


  constructor(
    private userService: UserService      
    
  ) { }

  ngOnInit() {
    this.userService.login(null, null).subscribe( user => {
      this.loggedUser = user;});
  }




  login(): void {
    console.log(this.username);
    this.userService.login(this.username, this.password)
    .subscribe( user => {
      this.loggedUser = user;
    });
  }

}

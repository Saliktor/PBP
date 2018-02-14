import { Component, OnInit } from '@angular/core';

import {CurrentUser} from '../current-user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loggedUser: CurrentUser = new CurrentUser();
  private username: string = null;
  private password: string = null;
  private alert = true;


  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }


  login(): void {
    if (!this.username || !this.password){
      this.alert = false;
      return;
    } else {
      this.userService.login(this.username, this.password)
      .subscribe( user => {
        this.loggedUser.user = user;
        if (user != null) {
          this.router.navigate(['./home']);
        } else {
          this.username = null;
          this.password = null;
          this.alert = false;
        }
      });
    }
  }

  goToRegister(): void{
    this.router.navigate(['./register']);
  }

}

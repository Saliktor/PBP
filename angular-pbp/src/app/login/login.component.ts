import { Component, OnInit } from '@angular/core';

import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private username: string = null;
  private password: string = null;
  private alert = true;
  private alertMessage = 'Invalid Login';


  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }


  login(): void {
    console.log('login');
    if (!this.username || !this.password){
      this.alert = false;
      this.alertMessage = 'Error: Fields cannot be empty';
    } else {
      this.userService.login(this.username, this.password)
      .subscribe( user => {
        if (user != null) {
          this.router.navigate(['./home']);
          this.userService.getPlayers().subscribe( players => {
            console.log(players);
          });
        } else {
          this.alertMessage = 'Not a valid Username/Password';
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

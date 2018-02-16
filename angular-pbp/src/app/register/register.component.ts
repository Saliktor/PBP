import { Component, OnInit } from '@angular/core';

import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private username: string;
  private password: string;
  private email: string;
  private alert = true;
  private alertMessage = 'Invalid Register';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  register(): void {
    if (!this.username || !this.password || !this.email){
      this.alert = false;
      this.alertMessage = 'Error: Fields cannot be empty';
    } else {
      this.userService.register(this.username, this.password, this.email)
      .subscribe( user => {
          if (user != null){
            this.router.navigate(['./home']);
          } else{
            this.alertMessage = 'Username/Email already used';
            this.username = null;
            this.password = null;
            this.email = null;
            this.alert = false;
          }
      });
    }
  }

  goToLogin() {
    this.router.navigate(['./login']);
  }
}


import { Injectable } from '@angular/core';
import { User } from './user';
import { CurrentUser } from './current-user';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs/observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/map';


@Injectable()
export class UserService {
  private loginUrl = 'http://localhost:8070/PBP/login';
  private registerUrl = 'http://localhost:8070/PBP/register';
  private headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': true});
  public currentUser: CurrentUser = new CurrentUser();

  constructor(private http: Http) { }

  loggedIn(): boolean{
    if (this.currentUser.user == null) {
      return false;
    } else {
      return true;
    }
  }

  getCurrentUser(): CurrentUser{
    return this.currentUser;
  }

  login(username: string, password: string) {
    if (username && password) {
      const body = `username=${username}&password=${password}`;
      return this.http.post(this.loginUrl, body, { headers: this.headers, withCredentials: true })
        .map(resp => {
          const user = resp.json();
          if (user == null) {
            return null;
          } else {
            this.currentUser.user = this.createUser(resp.json());
            return this.currentUser.user;
          }
        });
    }
  }

  createUser(userJsonObject): User {
    const user: User = new User();
    user.id = userJsonObject.id;
    user.username = userJsonObject.username;
    user.password = userJsonObject.password;
    user.email = userJsonObject.email;
    user.isAdmin = userJsonObject.isAdmin === 1 ? true : false;
    user.isBanned = userJsonObject.isBanned === 1 ? true : false;
    user.isMuted = userJsonObject.isAdmin === 1 ? true : false;
    return user;
  }

  register(username: string, password: string, email: string) {
    if (username && password && email) {
      const body = `username=${username}&password=${password}&email=${email}`;
      return this.http.post(this.registerUrl, body, { headers: this.headers, withCredentials: true })
        .map( resp => {
          const user = resp.json();
          if (user == null){
            return null;
          } else {
            this.currentUser.user = this.createUser(resp.json());
            return this.currentUser.user;
          }
        });
    } else {console.log('User Serivce register recieved an empty parameter');}
  }
}

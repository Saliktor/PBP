import { Injectable } from '@angular/core';
import { User } from './user';
import { CurrentUser } from './current-user';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs/observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/map';


@Injectable()
export class UserService {
  private loginUrl = 'http://localhost:8080/PBP/login';
  private registerUrl = 'http://localhost:8080/PBP/register';
  private headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': true});

  constructor(private http: Http) { }

  loggedIn(): boolean{
    const user = JSON.parse(localStorage.getItem('currentUser')) as User;
    if (user == null) {
      return false;
    } else {
      return true;
    }
  }

  getCurrentUser(): User{
    return JSON.parse(localStorage.getItem('currentUser')) as User;
  }

  login(username: string, password: string) {
    if (username && password) {
      const body = `username=${username}&password=${password}`;
      return this.http.post(this.loginUrl, body, { headers: this.headers, withCredentials: true })
        .map(resp => {
          let user = resp.json();
          if (user == null) {
            return null;
          } else {
            user = this.createUser(resp.json());
            localStorage.setItem('currentUser', JSON.stringify(user));
            return user;
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
          let user = resp.json();
          if (user == null){
            return null;
          } else {
            user = this.createUser(resp.json());
            localStorage.setItem('currentUser', JSON.stringify(user));
            return user;
          }
        });
    } else {console.log('User Serivce register recieved an empty parameter');}
  }

  logout(){
    console.log('User Service logout');
    localStorage.setItem('currentUser', null);
    //Do a call to the server to logout and invalidate the session
  }
}

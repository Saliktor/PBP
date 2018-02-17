import { Injectable } from '@angular/core';
import { User } from './user';
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
      return this.http.post(this.loginUrl, body, { headers: this.headers})
        .map(resp => {
          let user = resp.json();
          if (user == null) {
            return null;
          } else {
            user = resp.json() as User;
            localStorage.setItem('currentUser', JSON.stringify(user));
            return user;
          }
        });
    }
  }

  register(username: string, password: string, email: string) {
    if (username && password && email) {
      const body = `username=${username}&password=${password}&email=${email}`;
      return this.http.post(this.registerUrl, body, { headers: this.headers})
        .map( resp => {
          let user = resp.json();
          if (user == null){
            return null;
          } else {
            user = resp.json() as User;
            localStorage.setItem('currentUser', JSON.stringify(user));
            return user;
          }
        });
    } else {console.log('User Serivce register recieved an empty parameter');}
  }

  logout(){
    console.log('User Service logout');
    localStorage.setItem('currentUser', null);
  }
}

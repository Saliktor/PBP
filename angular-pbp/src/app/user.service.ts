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
  private headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded'});
  private user: User;

  constructor(private http: Http) { }

  login(username: string, password: string) {
    if (username && password) {
      const body = `username=${username}&password=${password}`;
      return this.http.post(this.loginUrl, body, { headers: this.headers, withCredentials: true })
        .map(
        resp => {
          console.log(resp);
          const user: CurrentUser = resp.json() as CurrentUser;
          this.user = user.user;
          return user;
        }
        );
    } else {
      return this.http.get(this.loginUrl, {headers: this.headers, withCredentials: true })
      .map(
        resp => {
          const user: CurrentUser = resp.json() as CurrentUser;
          this.user = user.user;
          return user;
        }
      );
    }
  }

  register(username: string, password: string, email: string) {
    if (username && password) {
      const body = `username=${username}&password=${password}&email=${email}`;
      return this.http.post(this.registerUrl, body, { headers: this.headers, withCredentials: true })
        .map(
        resp => {
          console.log(resp);
          const user: CurrentUser = resp.json() as CurrentUser;
          this.user = user.user;
          return user;
        }
        );
    } else {
      return this.http.get(this.registerUrl, {headers: this.headers, withCredentials: true })
      .map(
        resp => {
          const user: CurrentUser = resp.json() as CurrentUser;
          this.user = user.user;
          return user;
        }
      );
    }

  }
}

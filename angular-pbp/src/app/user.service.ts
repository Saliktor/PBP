import { Injectable } from '@angular/core';
import { User } from './user';
import { Headers, Http } from '@angular/http';
import { Message } from './message';
import { Observable } from 'rxjs/observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/map';
import { Player } from './player';


@Injectable()
export class UserService {
  private loginUrl = 'http://localhost:8080/PBP/login';
  private registerUrl = 'http://localhost:8080/PBP/register';
  private getPlayersUrl = 'http://localhost:8080/PBP/login-getplayers';
  private logoutUrl = 'http://localhost:8080/PBP/logout';
  private messageUrl = 'http://localhost:8080/PBP/message/newmessage';
  private headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': true});
  private objectheaders = new Headers({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': true});
  public message = new Message('');


  constructor(private http: Http) { }

  loggedIn(): boolean {
    const user = JSON.parse(localStorage.getItem('currentUser')) as User;
    if (user == null) {
      return false;
    } else {
      return true;
    }
  }

  getPlayers() {
    return this.http.get(this.getPlayersUrl, {headers: this.objectheaders, withCredentials: true})
      .map(resp => {
        const players =  resp.json() as Player[];
        const user = JSON.parse(localStorage.getItem('currentUser')) as User;

        // Assign the players array to the users update the localstorage session
        user.players = players;
        localStorage.setItem('currentUser', JSON.stringify(user));
        return players;
      });
  }

  login(username: string, password: string) {
    if (username && password) {
      const body = `username=${username}&password=${password}`;
      // console.log("before POST");
      return this.http.post(this.loginUrl, body, { headers: this.headers, withCredentials: true})
        .map(resp => {
          let user = resp.json();
          console.log("POST received");
          if (user == null) {
            return null;
          } else {
            console.log(user);
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
      return this.http.post(this.registerUrl, body, { headers: this.headers, withCredentials: true})
        .map( resp => {
          let user = resp.json();
          if (user == null) {
            return null;
          } else {
            user = resp.json() as User;
            localStorage.setItem('currentUser', JSON.stringify(user));
            return user;
          }
        });
    } else {console.log('User Serivce register recieved an empty parameter'); }
  }

  logout() {
    console.log('User Service logout');
    localStorage.clear();
    return this.http.get(this.logoutUrl, { headers: this.headers, withCredentials: true})
        .map( resp => { console.log('Server has invalidated session'); });
  }


  getCurrentUser(): User {
    return JSON.parse(localStorage.getItem('currentUser')) as User;
  }

  saveMessage(message : Message){
    if(message){
      console.log(`messageContent is ${message.messageContent} and timeStamp is ${message.timePosted.getMilliseconds()}`);
    const body = `messageContent=${message.messageContent}&timeStamp=${message.timePosted.getMilliseconds()  }`;
    return this.http.post(this.messageUrl, body ,{ headers: this.headers, withCredentials: true})
    //syntax error need to fix response
    // not sure what needs to happen after I send data to the server and if i get a repsonse back
      .map (resp => {
        console.log(resp);
        const message = resp.json() as Message;
        if(message == null){
            return null;
        }else{
            this.message = message
            return message;
        }
      }
    );
  }
  else{console.log("Invalid message recieved")}
  }
}

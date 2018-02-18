import { Injectable } from '@angular/core';
import {Message} from '../app/message'
import { User } from './user';
import { CurrentUser } from './current-user';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs/observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/map';

@Injectable()
export class GetMessagesService {
  private messageUrl = 'http://localhost:8070/PBP/message/getnewmessages'
  private headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': true});

  constructor(private http: Http) { }

  getNewMessages(timestamp : Date): Observable<Message[]>{

    return this.http.get(this.messageUrl + timestamp, { headers: this.headers, withCredentials: true })
      .map(
          resp => resp.json() as Message[]
      );
  }

}

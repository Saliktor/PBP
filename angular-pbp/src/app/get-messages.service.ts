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
    if(timestamp){
        console.log(`The timestamp date is ${timestamp}`);
        //time stamp for messages i want to recieve
        let s = '18-FEB-18 03.16.23.520000000 PM';
       // date.now  1519067509050
       //Date I'm trying to pass: 
      console.log("Date is: "+Date.parse('18 FEB 2018 03:16:23:520000000 PM'));
        let t = Date.now();
        console.log(t);
        
       
        const body =  `timeStamp=${/*Date.parse( '18 FEB 2018 03:16:23:520000000 PM' )*/ t}`;
        console.log(body);
    return this.http.get(this.messageUrl , { headers: this.headers, withCredentials: true })
      .map(
          resp => {
            console.log('Get messages response:'+resp);
            const messages = resp.json() as Message[];
            console.log(messages);
            return messages;
          }

      );
  }
  }
}

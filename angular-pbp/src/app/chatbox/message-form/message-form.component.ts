import { Component, OnInit, Input } from '@angular/core';
import { Message } from '../../message';
import {CurrentUser} from '../../current-user';
import { UserService } from '../../user.service';
import { MessageItemComponent } from '../message-item/message-item.component';

@Component({
  selector: 'message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {

  @Input('message')
  private message : Message;
 
  @Input('messages')
  private messages : Message[]

  constructor(private userService: UserService) {
    
   }

  ngOnInit() {
  }
  public sendMessage(): void {
 
    console.log(`message at start of send message: ${this.message.content} and time: ${this.message.timestamp}`);
    this.message.timestamp = new Date();
    this.messages.push(this.message);
    
    console.log(`message at start of user service: ${this.message.content} and time: ${this.message.timestamp}`);
    this.userService.saveMessage(this.message)
    .subscribe(user =>{
      
        console.log("Should anything be here");
    });
 this.message = new Message('');

 console.log(`message at end of send message: ${this.message.content} and time: ${this.message.timestamp}`);
    
  }

}

import { Component, OnInit, Input } from '@angular/core';
import {Message} from '../../message';

@Component({
  selector: 'message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit {

 @Input('messages')
  private messages : Message[]; 
  constructor() { }

  ngOnInit() {
  }

}

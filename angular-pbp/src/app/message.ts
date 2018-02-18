import { User } from "./user";

export class Message {
    id : number; 
   // user : User;
    gameId: number;
    timePosted: Date; 
    messageContent: string;
    

    constructor(messageContent: string,  timePosted?: Date){
        this.messageContent = messageContent;
        this.timePosted = timePosted;
     
      }
    
}

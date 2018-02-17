export class Message {
    id : number;
    content: string;
    timestamp: Date;
    // user : User;
    //game: Game;

    constructor(content: string,  timestamp?: Date){
        this.content = content;
        this.timestamp = timestamp;
     
      }
    
}

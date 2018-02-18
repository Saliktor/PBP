export class Message {
    id : number; 
    userId : number;
    gameId: number;
    timestamp: Date; 
    content: string;
    

    constructor(content: string,  timestamp?: Date){
        this.content = content;
        this.timestamp = timestamp;
     
      }
    
}

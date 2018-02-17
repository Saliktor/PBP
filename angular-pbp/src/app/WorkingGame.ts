import { Square } from './square';
import { Team } from './team';

export class WorkingGame{
    id: Number;
    boardstate: Square[][] = new Square[8][8];
    team: Team;
}
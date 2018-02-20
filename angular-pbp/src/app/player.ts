import { User } from './user';
import { Game } from './game';
import { Team } from './team';

export class Player {
    id: Number;
    user: User;
    game: Game;
    team: Team;
}

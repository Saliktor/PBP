import { User } from './user';
import { Game } from './game';

export class Player extends User {
    game: Game;
}
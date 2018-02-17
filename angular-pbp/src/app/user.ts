import { Player } from './player';

export class User {
    id: Number;
    username: string;
    password: string;
    email: string;
    isAdmin: Number;
    isBanned: Number;
    isMuted: Number;
    players: Player[];
}
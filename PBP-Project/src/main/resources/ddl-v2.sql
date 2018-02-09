drop table game cascade constraints;
drop table configuration cascade constraints;
drop table useraccount cascade constraints;
drop table player cascade constraints;
drop table team cascade constraints;
drop table message cascade constraints;
drop table move cascade constraints;

create table useraccount (
    id              number(10) primary key,
    username        varchar2(30) unique not null,
    password        varchar2(50) not null,
    email           varchar2(50) not null,
    isAdmin         number(1) not null,
    isbanned        number(1) not null,
    ismuted         number(1) not null
);

create table game (
    id              number(10) primary key,
    -- 64 columns
);

create table configuration (
    id              number (10) primary key, -- equal to the game id
    -- other config "booleans"
    constraint fk_game foreign key (id) references game(id)
);

create table team (
    id              number(2) primary key,
    teamname        varchar2(10) not null
);

create table player (
    id              number(10) primary key,
    userid          number(10) not null,
    gameid          number(10) not null,
    team            number(2), -- might be not null if we choose to implement it
    constraint fk_teamid foreign key (team) references team(id)
);

create table message (
    id              number(10) primary key,
    gameid          number(10) not null,
    userid          number(10) not null,
    timeposted      timestamp not null,
    messagecontent  varchar2(1000) not null,
    constraint fk_game foreign key (gameid) references game(id),
    constraint fk_user foreign key (userid) references user(id)
);

create table move (
    id              number(10) primary key,
    gameid          number(10) not null,
    playerid        number(10) not null,
    timemade        timestamp not null,
    play            varchar2(2) not null,
    constraint fk_game foreign key (gameid) references game(id),
    constraint fk_player foreign key (playerid) references player(id)
);

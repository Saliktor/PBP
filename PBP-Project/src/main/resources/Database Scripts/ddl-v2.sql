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
    email           varchar2(50) unique not null,
    isAdmin         number(1) not null,
    isbanned        number(1) not null,
    ismuted         number(1) not null
);

create table game (
    id              number(10) primary key,
    aa              number(1), -- the following represent the game state
    ab              number(1), -- null = unused yet in the game
    ac              number(1), -- 1 if white
    ad              number(1), -- 2 if black
    ae              number(1),
    af              number(1),
    ag              number(1),
    ah              number(1),
    ba              number(1),
    bb              number(1),
    bc              number(1),
    bd              number(1),
    be              number(1),
    bf              number(1),
    bg              number(1),
    bh              number(1),
    ca              number(1),
    cb              number(1),
    cc              number(1),
    cd              number(1),
    ce              number(1),
    cf              number(1),
    cg              number(1),
    ch              number(1),
    da              number(1),
    db              number(1),
    dc              number(1),
    dd              number(1),
    de              number(1),
    df              number(1),
    dg              number(1),
    dh              number(1),
    ea              number(1),
    eb              number(1),
    ec              number(1),
    ed              number(1),
    ee              number(1),
    ef              number(1),
    eg              number(1),
    eh              number(1),
    fa              number(1),
    fb              number(1),
    fc              number(1),
    fd              number(1),
    fe              number(1),
    ff              number(1),
    fg              number(1),
    fh              number(1),
    ga              number(1),
    gb              number(1),
    gc              number(1),
    gd              number(1),
    ge              number(1),
    gf              number(1),
    gg              number(1),
    gh              number(1),
    ha              number(1),
    hb              number(1),
    hc              number(1),
    hd              number(1),
    he              number(1),
    hf              number(1),
    hg              number(1),
    hh              number(1)
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
    constraint fk_message_game foreign key (gameid) references game(id),
    constraint fk_message_user foreign key (userid) references useraccount(id)
);

create table move (
    id              number(10) primary key,
    gameid          number(10) not null,
    playerid        number(10) not null,
    timemade        timestamp not null,
    play            varchar2(2) not null,
    constraint fk_move_game foreign key (gameid) references game(id),
    constraint fk_move_player foreign key (playerid) references player(id)
);

drop sequence useraccount_seq;
drop sequence game_seq;
drop sequence move_seq;
drop sequence message_seq;
drop sequence player_seq;
drop sequence team_seq;

create sequence useraccount_seq;
create sequence game_seq;
create sequence move_seq;
create sequence message_seq;
create sequence player_seq;
create sequence team_seq;
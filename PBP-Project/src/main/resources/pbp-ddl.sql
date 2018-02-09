connect pbp_db/p4ssw0rd

drop table  game cascade constraints;
drop table  move cascade constraints;
drop table  useraccount cascade constraints;
drop table  forumthread cascade constraints;
drop table  post cascade constraints;
drop table  user_thread_vote cascade constraints;
drop table  user_post_vote cascade constraints;
drop table  post_edit cascade constraints;
drop table  thread_edit cascade constraints;
drop table  player cascade constraints;

create table game (
    id              number(3) primary key,
    gametype        varchar2(100) not null
    -- number of teams?
    -- This might be a super abstract table because of so many different rule sets.
);

create table  move (
    id              number(10) primary key,
    temp            varchar2(1)
    -- other info here.
);

create table  useraccount (
    id              number(10) primary key,
    firstname       varchar2(30),
    lastname        varchar2(30),
    username        varchar2(30) unique not null,
    password        varchar2(50) not null,
    email           varchar2(50) not null,
    isAdmin         number(1) not null,
    isbanned        number(1) not null,
    ismuted         number(1) not null
);

create table  forumthread (
    id              number(10) primary key,
    body            varchar2(1000),
    title           varchar2(1000) not null,
    threadcreator   number(10) not null,       -- user
    gametype        number(3) not null,
    timecreated     timestamp not null,
    constraint fk_gametype foreign key (gametype) references game(id),
    constraint fk_threadcreator foreign key (threadcreator) references useraccount(id)
);

create table  post (
    id              number(10) primary key,
    body            varchar2(1000) not null,
    repliedto       number(10),             -- if thread is not a child of post, this is null. Otherwise, this should be not null
    repliedfrom     number(10),             -- post
    postcreation    timestamp not null,
    move            number(10) not null,
    threadId        number(10) not null,
    constraint fk_repliedto foreign key (repliedto) references post(id),
    constraint fk_repliedfrom foreign key (repliedfrom) references post(id),
    constraint fk_move foreign key (move) references move(id),
    constraint fk_threadid foreign key (threadid) references forumthread(id)
);

create table  user_thread_vote (
    user_id         number(10) not null,
    thread_id       number(10) not null,
    vote            number(1) not null, -- -1 or +1
    constraint pk_user_thread_vote primary key (user_id,thread_id),
    constraint fk_user_id_thread foreign key (user_id) references useraccount(id),
    constraint fk_thread_id_user foreign key (thread_id) references forumthread(id)
);

create table  user_post_vote (
    user_id         number(10) not null,
    post_id         number(10) not null,
    vote            number(1) not null, -- -1 or +1
    constraint pk_user_post_vote primary key (user_id,post_id),
    constraint fk_user_id_post foreign key (user_id) references useraccount(id),
    constraint fk_post_id_user foreign key (post_id) references post(id)
);

create table  post_edit (
    id              number(10) primary key,
    whoedited       number(10) not null,
    postid          number(10) not null,
    content         varchar2(1000) not null,
    timeedited      timestamp not null,
    constraint fk_whoedited_post foreign key (whoedited) references useraccount(id),
    constraint fk_postid foreign key (postid) references post(id)
);

-- this might not be needed if the thread is a child of post and they use the sequence
create table  thread_edit (
    id              number(10) primary key,
    whoedited       number(10) not null,
    threadid        number(10) not null,
    content         varchar2(1000) not null,
    timeedited      timestamp not null,
    constraint fk_whoedited_thread foreign key (whoedited) references useraccount(id),
    constraint fk_thread_id foreign key (threadid) references forumthread(id)
);

create table  player (
    threadid        number(10) not null,
    userid          number(10) not null,
    team            number(2) not null,   -- 0=no teams , 1=white , 2=black
    constraint pk_player primary key (threadid,userid),
    constraint fk_player_thread_id foreign key (threadid) references forumthread(id),
    constraint fk_player_userid foreign key (userid) references useraccount(id)
);

drop sequence useraccount_seq;
drop sequence thread_edit_seq;
drop sequence post_edit_seq;
drop sequence thread_seq;
drop sequence post_seq;
drop sequence move_seq;

create sequence useraccount_seq;
create sequence thread_edit_seq;
create sequence post_edit_seq;
create sequence thread_seq;
create sequence post_seq;
create sequence move_seq;
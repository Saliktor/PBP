create table game {
    id              number(3) primary key,
    gametype        varchar2(100) not null
    -- number of teams?
    -- This might be a super abstract table because of so many different rule sets.
)

create table move (
    id              number(10) primary key
    -- other info here.
)

create table user (
    id              number(10) primary key,
    firstname       varchar2(30),
    lastname        varchar2(30),
    username        varchar2(30) unique not null,
    password        varchar2(50) not null,
    email           varchar2(50) not null,
    isAdmin         number(1) not null,
    isbanned        number(1) not null,
    ismuted         number(1) not null
)

create table thread (
    id              number(10) primary key,
    body            varchar2(1000),
    title           varchar2(1000) not null,
    threadcreator   number(10) not null,       -- user
    gametype        number(3) not null,
    timecreated     timstamp not null,
    constraint fk_gametype foreign key (gametype) references game(id),
    constraint fk_threadcreator foreign key (threadcreator) references user(id)
)

create table post (
    id              number(10) primary key,
    body            varchar2(1000) not null,
    repliedto       number(10),             -- post
    repliedfrom     number(10),             -- post
    postcreation    timestamp not null,
    postcreator     number(10),             -- user
    team            varchar2(100),
    move            number(10),
    threadId        number(10),
    constraint fk_repliedto foreign key (repliedto) references post(id),
    constraint fk_repliedfrom foreign key (repliedfrom) references post(id),
    constraint fk_postcreator foreign key (postcreator) references user(id),
    constraint fk_move foreign key (move) references move(id),
    constraint fk_threadid foreign key (threadid) references thread(id)
)

create table user_thread_vote (
    user_id         number(10) not null,
    thread_id       number(10) not null,
    vote            number(1) not null, -- -1 or +1
    constraint pk_user_thread_vote primary key (user_id,thread_id)
    constraint fk_user_id foreign key (user_id) references user(id),
    constraint fk_thread_id foreign key (thread_id) references thread(id)
)

create table user_post_vote (
    user_id         number(10) not null,
    post_id         number(10) not null,
    vote            number(1) not null, -- -1 or +1
    constraint pk_user_thread_vote primary key (user_id,post_id),
    constraint fk_user_id foreign key (user_id) references user(id),
    constraint fk_post_id foreign key (post_id) references post(id)
)

create table post_edit (
    id              number(10) primary key,
    whoedited       number(10) not null,
    postid          number(10) not null,
    content         varchar2(1000) not null,
    timeedited      timestamp not null,
    constraint fk_whoedited foreign key (whoedited) references user(id),
    constraint fk_postid foreign key (postid) references post(id)
)

create table thread_edit (
    id              number(10) primary key,
    whoedited       number(10) not null,
    threadid        number(10) not null,
    content         varchar2(1000) not null,
    timeedited      timestamp not null,
    constraint fk_whoedited foreign key (whoedited) references user(id),
    constraint fk_threadid foreign key (threadid) references thread(id)
)

create table player (
    threadid        number(10) not null,
    userid          number(10) not null,
    team            varchar2(10) not null,
    constraint pk_player primary key (threadid,userid),
    constraint fk_threadid foreign key (threadid) references thread(id),
    constraint fk_userid foreign key (userid) references user(id)
)
--BEFORE RUNNING WHOLE SCRIPT BE SURE TO RUN DDL SCRIPT TO DROP TABLES AND RECREATE

-- Init UserAccount --
--Base Users
INSERT INTO useraccount VALUES(USERACCOUNT_SEQ.NEXTVAL, 'user1', 'p4ssw0rd', 'user1@email.com', 0, 0, 0);
INSERT INTO useraccount VALUES(USERACCOUNT_SEQ.NEXTVAL, 'user2', 'p4ssw0rd', 'user2@email.com', 0, 0, 0);
INSERT INTO useraccount VALUES(USERACCOUNT_SEQ.NEXTVAL, 'user3', 'p4ssw0rd', 'user3@email.com', 0, 1, 0);
INSERT INTO useraccount VALUES(USERACCOUNT_SEQ.NEXTVAL, 'user4', 'p4ssw0rd', 'user4@email.com', 0, 0, 1);

--Admin
INSERT INTO useraccount VALUES(USERACCOUNT_SEQ.NEXTVAL, 'admin1', 'p4ssw0rd', 'admin1@email.com', 1, 0, 0);


--Init Configuration--


--Init Game --
-- Fresh Game Board
INSERT INTO game(ID, DD, DE, ED, EE) VALUES(GAME_SEQ.NEXTVAL, 1, 2, 2, 1); 


--Init Team --
INSERT INTO team VALUES(TEAM_SEQ.NEXTVAL, 'team1');
INSERT INTO team VALUES(TEAM_SEQ.NEXTVAL, 'team2');

--Init Players --
INSERT INTO player(ID, USERID, GAMEID) VALUES(PLAYER_SEQ.NEXTVAL, 1, 1);
INSERT INTO player(ID, USERID, GAMEID) VALUES(PLAYER_SEQ.NEXTVAL, 2, 1); 

--Init Messages--

--Init Moves--

commit();

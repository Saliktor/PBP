drop user pbp_db cascade;

create user pbp_db
identified by p4ssw0rd
default tablespace users
temporary tablespace temp
quota 10m on users;

grant connect to pbp_db;
grant resource to pbp_db;
grant create session to pbp_db;
grant create table to pbp_db;
grant create view to pbp_db;

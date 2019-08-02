
---------------------------------
-- 종중 계정
---------------------------------
mysql -uroot -plife200727

---------------------------------
-- database 생성
---------------------------------
create database clan;

---------------------------------
-- 유저 생성
---------------------------------

-- 모든 ip 허용
create user 'clan'@'%' identified by '1';
grant all privileges on clan.* to 'clan'@'%';

create user 'clan'@'localhost' identified by '1';
create user 'clan'@'pmosoft.net' identified by '1';
create user 'clan'@'182.228.242.133' identified by '1'; -- cafe24 보안서버

grant all privileges on clan.* to clan@localhost;
grant all privileges on clan.* to clan@pmosoft.net;
grant all privileges on clan.* to clan@182.228.242.133;


-----------------------------------------------------------------------------------------------






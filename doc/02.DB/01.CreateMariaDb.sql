---------------------------------
-- 계정
---------------------------------
mysql -uroot -plife200727

---------------------------------
-- database 생성
---------------------------------
create database sttl;

---------------------------------
-- 유저 생성
---------------------------------

-- 모든 ip 허용
create user 'sttl'@'%' identified by 'f1234';
grant all privileges on sttl.* to 'sttl'@'%';

create user 'sttl'@'localhost' identified by 'f1234';
create user 'sttl'@'pmosoft.net' identified by 'f1234';
create user 'sttl'@'182.228.242.133' identified by 'f1234'; -- cafe24 보안서버

grant all privileges on sttl.* to sttl@localhost;
grant all privileges on sttl.* to sttl@pmosoft.net;
grant all privileges on sttl.* to sttl@182.228.242.133;


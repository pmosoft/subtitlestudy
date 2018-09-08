# mysql -uroot -plife200727 < backup.sql



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

---------------------------------
-- utf8
---------------------------------
"c:\Program Files\MariaDB 10.2\data\my.ini"
[mysqld]
init_connect="SET collation_connection = utf8_general_ci"
init_connect="SET NAMES utf8"
character-set-server = utf8
collation-server = utf8_general_ci

[client]
default-character-set = utf8
[mysqldump]
default-character-set = utf8
[mysql]
default-character-set = utf8

---------------------------------
-- 대소문자
---------------------------------
show variables like 'lower_case_table_names'
;

vi /etc/my.cnf

---------------------------------
-- 복원
---------------------------------
mysql -usttl -pf1234 < sttl_backup.sql

mysql -uroot -plife200727

-----------------------------------
-- timeout
-----------------------------------
show variables like '%timeout';

------------------------------------------------------------------------
connect_timeout  :  MySQL 서버 접속시에 접속실패를 메시지를 보내기까지 대기하는 시간
delayed_insert_timeout  :  insert시 delay될 경우 대기하는 시간
innodb_lock_wait_timeout  :  innodb에 transaction 처리중 lock이 걸렸을 시 롤백 될때까지 대기하는 시간.
     innodb는 자동으로 데드락을 검색해서 롤백시킨다.
innodb_rollback_on_timeout  :  innodb의 마지막 구문을 롤백시킬지 결정하는 파라미터. 
        timeout은 진행중인 transaction을 중단하고 전체 transaction을 롤백하는 과정에서 발생한다.
interactive_timeout  :  활동중인 커넥션이 닫히기 전까지 서버가 대기하는 시간
net_read_timeout  :  서버가 클라이언트로부터 데이터를 읽어들이는 것을 중단하기까지 대기하는 시간
net_write_timeout  :  서버가 클라이언트에 데이터를 쓰는 것을 중단하기까지 대기하는 시간 
slave_net_timeout  :  마스터/슬레이브로 서버가 클라이언트로부터 데이터를 읽어들이는 것을 중단하기까지 대기하는 시간
table_lock_wait_timeout  :  테이블 락을 중단하기까지 대기하는 시간
wait_timeout  :   활동하지 않는 커넥션을 끊을때까지 서버가 대기하는 시간

그러면 실제로 timeout 값을 수정하고 커맨드라인에서 확인해 보도록 하자.
wait_timeout값에 대한 수정을 하였다. 윈도우는 my.ini 리눅스의 경우 my.cnf 파일의 [mysqld] 부분에서 추가하면 되겠다.
MySQL 서버를 재시작 하고 적용된 값이 나타나는지 확인해보도록 하겠다.

그렇다면 무엇때문에 검색방식에 따라 다르게 표현되는 것일까. 검색해보니 세션 wait_timeout값은 클라이언트의 타입에 따라
wait_timeout 값 혹은 interative_timeout 값으로 초기화 된다고 한다.  
커맨드라인을 통해 접속한 클라이언트는 interative_timeout값을 wait_timeout이 참고하기 때문에 다르게 보이는 것이었다.

* 번외(interactive_timeout)
interactive_timeout 값을 20으로 수정하였다.

이번엔 show processlist;를 지속적으로 수행하여 20초가 되면 정말로 커넥션이 끊기는지 확인해보자.
time 컬럼에 대기시간이 표시되고, kjk3071이라는 user가 20초 이후에는 processlist에서 사라진다는 것을 알수있다.
그리고 이 화면에는 나타나지 않았지만 커넥션 재접속 후에는 해당 커넥션 아이디값이 다시 나타날 것이다.

vi /etc/my.cnf

[mysqld]
wait_timeout=31536000

 
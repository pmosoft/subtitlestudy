-----------------------------
-- 유저
-----------------------------
DROP TABLE STTL.TSYUR00010;

CREATE TABLE STTL.TSYUR00010 (
 USR_EMAIL    VARCHAR(40)    NOT NULL COMMENT '사용자이메일'
,USR_ID       VARCHAR(40)        NULL COMMENT '사용자아이디'
,USR_PW       VARCHAR(100)       NULL COMMENT '사용자암호'
,USR_NM       VARCHAR(40)        NULL COMMENT '사용자명'
,USR_AGE      INT                NULL COMMENT '사용자나이'
,USE_YN       CHAR(1)            NULL COMMENT '사용여부'
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_EMAIL)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='사용자';
;

-----------------------------
-- 유저 학습언어
-----------------------------
DROP TABLE STTL.TSYUR00020;

CREATE TABLE STTL.TSYUR00020 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'
,FLANG_CD     VARCHAR(2)         NULL COMMENT '외국어코드'
,MLANG_CD     VARCHAR(2)         NULL COMMENT '모국어코드'
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='사용자학습언어';
;


-----------------------------
-- 유저자막목록
-----------------------------
DROP TABLE STTL.TSSCM00010;

CREATE TABLE STTL.TSSCM00010 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'
,FSTTL_NM     VARCHAR(100)       NULL COMMENT '외국어자막명'
,MSTTL_NM     VARCHAR(100)       NULL COMMENT '모국어자막명'
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_NM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='유저자막목록';
;

-----------------------------
-- 유저자막내용
-----------------------------
DROP TABLE STTL.TSSCM00020;

CREATE TABLE STTL.TSSCM00020 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'
,STTL_CD      CHAR(1)        NOT NULL COMMENT '자막구분(1:외국어,2:모국어)'
,STTL_NUM     INT            NOT NULL COMMENT '자막순번'
,STTL_STM     VARCHAR(50)    NOT NULL COMMENT '자막시작시각'
,STTL_ETM     VARCHAR(50)        NULL COMMENT '자막종료시각'
,STTL_DESC    VARCHAR(1000)      NULL COMMENT '자막문장내용'
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_NM,STTL_CD,STTL_NUM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='유저자막내용';
;

--CREATE INDEX TSSCM00020_IX01 ON TSSCM00020 (USR_ID,STTL_NM,STTL_CD,STTL_NUM);

-----------------------------
-- 유저자막 책갈피
-----------------------------
DROP TABLE STTL.TSSCM00030;

CREATE TABLE STTL.TSSCM00030 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'
,STTL_CD      CHAR(1)        NOT NULL COMMENT '자막구분(1:외국어,2:모국어)'
,STTL_NUM     INT            NOT NULL COMMENT '자막순번'
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_NM,STTL_CD,STTL_NUM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='자막책갈피';
;

-----------------------------
-- 유저리뷰자막
-----------------------------
DROP TABLE STTL.TSSCM00040;

CREATE TABLE STTL.TSSCM00040 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'
,SERIAL_NO    INT            NOT NULL COMMENT '일련번호'
,FSTTL_DESC   VARCHAR(1000)      NULL COMMENT '외국어자막문장내용'
,MSTTL_DESC   VARCHAR(1000)      NULL COMMENT '모국어자막문장내용'
,REVIEW_CNT   INT                NULL COMMENT '리뷰횟수'
,REVIEW_CD    CHAR(1)            NULL COMMENT '리뷰코드' -- 1:일반 2:심화 3:종료
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_NM,SERIAL_NO)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='유저리뷰자막';
;

ALTER TABLE TSSCM00040 CHANGE COLUMN USE_YN STATUS_CD CHAR(1);
ALTER TABLE TSSCM00040 CHANGE COLUMN STATUS_CD REVIEW_CD CHAR(1);

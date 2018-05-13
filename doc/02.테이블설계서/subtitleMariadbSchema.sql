-----------------------------
-- 유저
-----------------------------
CREATE TABLE STTL.TSYUR00010 (
 USR_EMAIL    VARCHAR(40)    NOT NULL comment '사용자이메일'    
,USR_ID       VARCHAR(40)        NULL COMMENT '사용자아이디'    
,USR_PW       VARCHAR(100)       NULL comment '사용자암호'      
,USR_NM       VARCHAR(40)        NULL comment '사용자명'        
,USR_AGE      INT                NULL comment '사용자나이'      
,USE_YN       CHAR(1)            NULL comment '사용여부'        
,REG_DTM      VARCHAR(14)        NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(20)        NULL COMMENT '등록자'
,UPD_DTM      VARCHAR(14)        NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(14)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_EMAIL)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='사용자';
;

-----------------------------
-- 유저자막
-----------------------------
CREATE TABLE STTL.TSYUR00020 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'    
,STTL_REG_DTM VARCHAR(14)    NOT NULL COMMENT '자막등록일시'
,FSTTL_NM     VARCHAR(100)       NULL COMMENT '외국어자막명'      
,MSTTL_NM     VARCHAR(100)       NULL COMMENT '모국어자막명'      
,REG_DTM      VARCHAR(14)        NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(20)        NULL COMMENT '등록자'
,UPD_DTM      VARCHAR(14)        NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(14)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_REG_DTM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='유저자막';
;

-----------------------------
-- 유저자막내용
-----------------------------
CREATE TABLE STTL.TSYUR00030 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'    
,STTL_REG_DTM VARCHAR(14)    NOT NULL COMMENT '자막등록일시'
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'      
,STTL_TM      INT            NOT NULL COMMENT '자막시간'      
,STTL_SDESC   VARCHAR(1000)      NULL COMMENT '자막문장내용'
,STTL_CD      CHAR(1)            NULL COMMENT '자막구분(1:외국어,2:모국어)' 
,REG_DTM      VARCHAR(14)        NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(20)        NULL COMMENT '등록자'
,UPD_DTM      VARCHAR(14)        NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(14)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_REG_DTM,STTL_NM,STTL_TM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='유저자막내용';
;

-----------------------------
-- 유저통합자막내용
-----------------------------
CREATE TABLE STTL.TSYUR00030 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'    
,STTL_REG_DTM VARCHAR(14)    NOT NULL COMMENT '자막등록일시'
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'      
,STTL_TM      INT            NOT NULL COMMENT '자막시간'      
,FSTTL_SDESC   VARCHAR(1000)     NULL COMMENT '외국어자막문장내용'
,MSTTL_SDESC   VARCHAR(1000)     NULL COMMENT '모국어자막문장내용'
,REG_DTM      VARCHAR(14)        NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(20)        NULL COMMENT '등록자'
,UPD_DTM      VARCHAR(14)        NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(14)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_REG_DTM,STTL_NM,STTL_TM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='유저자막내용';
;

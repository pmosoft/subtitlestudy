 SELECT A.USR_ID          -- 사용자아이디
         , A.STTL_NM         -- 자막명
         , A.SERIAL_NO       -- 일련번호
         , A.FSTTL_DESC      -- 외국어자막문장내용
         , A.MSTTL_DESC      -- 모국어자막문장내용
         , A.REVIEW_CNT      -- 리뷰횟수
         , A.USE_YN          -- 사용여부
         , DATE_FORMAT(A.REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DTM
         , A.REG_USR_ID
         , DATE_FORMAT(A.UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DTM
         , A.UPD_USR_ID
    FROM   STTL.TSSCM00040 A
    WHERE  A.USR_ID  = 'lifedomy@gmail.com'
    ORDER BY A.USR_ID, A.REG_DTM DESC, A.SERIAL_NO DESC
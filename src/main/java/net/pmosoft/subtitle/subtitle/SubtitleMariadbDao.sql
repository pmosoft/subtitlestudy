 SELECT    A.MSTTL_DESC      -- 모국어자막문장내용
    FROM   STTL.TSSCM00040 A
    WHERE  A.USR_ID  = 'lifedomy@gmail.com'
    ORDER BY A.USR_ID, A.REG_DTM DESC, A.SERIAL_NO DESC